package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.block.WoodenSpitRotisserieBlock;
import com.skd.slaughterhide.init.ModItems;
import com.skd.slaughterhide.tag.ModItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * Handles interactions with WoodenSpitRotisserieBlock:
 * - Right-click with cleaver on cooked state (2): harvests cooked meat, resets to empty
 * - Neighbor change (campfire below): advances cooking state 1->2->3 over time
 * - Break drops based on state
 */
public final class WoodenSpitRotisserieInteractionHandler {
    private WoodenSpitRotisserieInteractionHandler() {
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand() != InteractionHand.MAIN_HAND) {
            return;
        }
        LevelAccessor accessor = event.getLevel();
        if (accessor.isClientSide() || !(accessor instanceof Level level)) {
            return;
        }
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof WoodenSpitRotisserieBlock)) {
            return;
        }

        ItemStack heldItem = player.getMainHandItem();
        int currentState = state.getValue(WoodenSpitRotisserieBlock.BLOCKSTATE);

        // Right-click with cleaver on cooked state (state 2) to harvest
        if (currentState == 2 && isCleaver(heldItem)) {
            event.setCanceled(true);
            player.swing(InteractionHand.MAIN_HAND);
            
            // Reset to empty state
            level.setBlock(pos, state.setValue(WoodenSpitRotisserieBlock.BLOCKSTATE, 0), 3);
            playSound(level, pos, "block.honey_block.break");
            
            // Damage the cleaver
            if (!player.getAbilities().instabuild && level instanceof ServerLevel serverLevel) {
                heldItem.hurtAndBreak(1, serverLevel, player, item -> { });
            }
            
            // Spawn cooked pork drops (simplified - in original it used a loot table)
            spawnCookedPorkDrops(level, pos);
            return;
        }

        // Right-click with drained pig carcass on empty spit (state 0) to place it
        if (currentState == 0 && heldItem.is(ModItems.DRAINED_PIG_CARCASS.get())) {
            event.setCanceled(true);
            player.swing(InteractionHand.MAIN_HAND);
            
            // Set to raw state (1)
            level.setBlock(pos, state.setValue(WoodenSpitRotisserieBlock.BLOCKSTATE, 1), 3);
            
            if (!player.getAbilities().instabuild) {
                heldItem.shrink(1);
            }
            return;
        }
    }

    /**
     * Called when a neighbor block changes - checks for campfire below to cook
     */
    public static void onNeighborChange(LevelAccessor level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;
        
        BlockPos below = pos.below();
        if (level.getBlockState(below).getBlock() == Blocks.CAMPFIRE) {
            int currentState = state.getValue(WoodenSpitRotisserieBlock.BLOCKSTATE);
            
            if (currentState == 1 || currentState == 2) {
                // Schedule cooking/burning transition after 1800 ticks (1.5 minutes)
                level.scheduleTick(pos, state.getBlock(), 1800);
            }
        }
    }

    /**
     * Called on scheduled tick to advance cooking state
     */
    public static void onScheduledTick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;
        
        BlockPos below = pos.below();
        if (level.getBlockState(below).getBlock() != Blocks.CAMPFIRE) {
            return; // No heat source, stop cooking
        }
        
        int currentState = state.getValue(WoodenSpitRotisserieBlock.BLOCKSTATE);
        
        if (currentState == 1) {
            // Raw -> Cooked
            level.setBlock(pos, state.setValue(WoodenSpitRotisserieBlock.BLOCKSTATE, 2), 3);
        } else if (currentState == 2) {
            // Cooked -> Burnt
            level.setBlock(pos, state.setValue(WoodenSpitRotisserieBlock.BLOCKSTATE, 3), 3);
        }
    }

    /**
     * Called when block is broken - drops items based on state
     */
    public static void onBroken(LevelAccessor accessor, BlockPos pos, BlockState state, net.minecraft.world.entity.Entity entity) {
        if (accessor.isClientSide() || !(accessor instanceof Level level)) return;
        if (!(entity instanceof Player player)) return;
        if (player.getAbilities().instabuild) return;

        int currentState = state.getValue(WoodenSpitRotisserieBlock.BLOCKSTATE);

        if (currentState == 0) {
            // Empty spit - drops itself
            spawnItem(level, pos, new ItemStack(com.skd.slaughterhide.init.ModBlocks.WOODEN_SPIT_ROTISSERIE.get().asItem()));
        } else if (currentState == 1) {
            // Raw - drops empty spit
            spawnItem(level, pos, new ItemStack(com.skd.slaughterhide.init.ModBlocks.WOODEN_SPIT_ROTISSERIE.get().asItem()));
            playSound(level, pos, "block.honey_block.break");
        } else if (currentState == 2) {
            // Cooked - drops cooked pork items
            // (no standalone "crackling" item exists in this port -- dropped from the original's list)
            playSound(level, pos, "block.honey_block.break");
            spawnCookedPorkDrops(level, pos);
            spawnItem(level, pos, new ItemStack(ModItems.HOOF.get(), 4));
        } else if (currentState == 3) {
            // Burnt - drops charcoal
            playSound(level, pos, "block.sand.break");
            spawnItem(level, pos, new ItemStack(net.minecraft.world.item.Items.CHARCOAL, 3));
        }
    }

    private static boolean isCleaver(ItemStack stack) {
        return stack.is(ModItemTags.CLEAVER);
    }

    private static void spawnCookedPorkDrops(LevelAccessor level, BlockPos pos) {
        if (level instanceof Level lvl && !lvl.isClientSide()) {
            spawnItem(lvl, pos, new ItemStack(ModItems.COOKED_HAM.get()));
            spawnItem(lvl, pos, new ItemStack(ModItems.COOKED_PORK_BELLY.get()));
            spawnItem(lvl, pos, new ItemStack(ModItems.COOKED_PORK_LEG.get(), 4));
            spawnItem(lvl, pos, new ItemStack(ModItems.COOKED_PORK_LOIN.get()));
            spawnItem(lvl, pos, new ItemStack(ModItems.COOKED_PORK_SHOULDER.get()));
        }
    }

    private static void spawnItem(Level level, BlockPos pos, ItemStack stack) {
        net.minecraft.world.entity.item.ItemEntity entity = new net.minecraft.world.entity.item.ItemEntity(
                level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
        entity.setPickUpDelay(10);
        level.addFreshEntity(entity);
    }

    private static void playSound(Level level, BlockPos pos, String soundId) {
        SoundEvent sound = BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse(soundId));
        if (sound != null && !level.isClientSide()) {
            level.playSound(null, pos, sound, SoundSource.NEUTRAL, 1.0f, 1.0f);
        }
    }
}