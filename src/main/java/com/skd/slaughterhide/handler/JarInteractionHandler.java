package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.block.JarBlock;
import com.skd.slaughterhide.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * Handles interactions with JarBlock:
 * - Right-click with organ item on empty jar (state 0): places organ, sets blockstate to display that organ
 * - Right-click on occupied jar: shows "You can only fit 1 organ in there!" message
 * - Break drops the organ that was inside based on blockstate
 * 
 * Organ display states (1-19, 0 = empty) per the original mod's design:
 * 1 = Heart, 2 = Intestines, 3 = Kidney, 4 = Liver, 5 = Stomach, 6 = Lungs
 * 7 = Rotten Heart, 8 = Rotten Intestines, 9 = Rotten Kidney, 10 = Rotten Liver, 11 = Rotten Stomach, 12 = Rotten Lungs
 * 13 = Enderman Heart, 14 = Enderman Intestines, 15 = Enderman Kidney, 16 = Enderman Liver, 17 = Enderman Stomach, 18 = Enderman Lungs
 * 19 = Brain (from BrainBlock cross-interaction)
 *
 * KNOWN LIMITATION: states 1-18 need organ items (heart/intestines/kidney/liver/
 * stomach/lungs, their "rotten" variants, and enderman variants) that were never
 * registered anywhere in this port (verified: no such fields exist in ModItems,
 * and the humanoid corpse loot tables actually drop vanilla rotten_flesh, not
 * these). Registering ~18 new items is new content, out of scope for this port
 * pass -- only state 19 (Brain) is wired up here since that item does exist.
 */
public final class JarInteractionHandler {
    private JarInteractionHandler() {
    }

    // Map organ items to their blockstate display values.
    // Only Brain is wired -- see the KNOWN LIMITATION note above the class.
    private static int getOrganState(ItemStack stack) {
        if (stack.is(com.skd.slaughterhide.init.ModBlocks.BRAIN.get().asItem())) return 19;
        return 0;
    }

    // Map blockstate values back to organ items for break drops.
    private static ItemStack getOrganItem(int state) {
        return switch (state) {
            case 19 -> new ItemStack(com.skd.slaughterhide.init.ModBlocks.BRAIN.get().asItem());
            default -> ItemStack.EMPTY;
        };
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
        if (!(state.getBlock() instanceof JarBlock)) {
            return;
        }

        ItemStack heldItem = player.getMainHandItem();
        int currentState = state.getValue(JarBlock.BLOCKSTATE);

        // Empty jar - try to insert organ
        if (currentState == 0 && !heldItem.isEmpty()) {
            int organState = getOrganState(heldItem);
            if (organState > 0) {
                event.setCanceled(true);
                player.swing(InteractionHand.MAIN_HAND);
                level.setBlock(pos, state.setValue(JarBlock.BLOCKSTATE, organState), 3);
                
                if (!player.getAbilities().instabuild) {
                    heldItem.shrink(1);
                }
                return;
            }
        }

        // Occupied jar - show message
        if (currentState != 0 && player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
            serverPlayer.sendSystemMessage(Component.literal("You can only fit 1 organ in there!"), true);
        }
    }

    /**
     * Called when jar is broken - drops the organ that was inside
     */
    public static void onBroken(LevelAccessor level, BlockPos pos, BlockState state, net.minecraft.world.entity.Entity entity) {
        if (level.isClientSide()) return;
        if (!(entity instanceof Player player)) return;
        if (player.getAbilities().instabuild) return;
        
        int currentState = state.getValue(JarBlock.BLOCKSTATE);
        if (currentState > 0) {
            ItemStack organItem = getOrganItem(currentState);
            if (!organItem.isEmpty()) {
                net.minecraft.world.entity.item.ItemEntity itemEntity = new net.minecraft.world.entity.item.ItemEntity(
                        (Level) level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, organItem);
                itemEntity.setPickUpDelay(10);
                ((Level) level).addFreshEntity(itemEntity);
            }
        }
    }
}