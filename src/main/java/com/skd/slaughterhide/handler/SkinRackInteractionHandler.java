package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.block.SkinRackBlock;
import com.skd.slaughterhide.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
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
 * Handles right-click interactions on SkinRackBlock:
 * - Empty hand on occupied slot: does nothing (or could remove skin)
 * - Skin item on empty rack (state 0): places skin, sets blockstate to corresponding value
 * - Skin item on occupied rack: does nothing (only one skin per rack in original)
 */
public final class SkinRackInteractionHandler {
    private SkinRackInteractionHandler() {
    }

    // Map skin items to their blockstate values (1-31, 0 is empty)
    private static int getSkinState(ItemStack stack) {
        // Only mob-skin items that actually exist in this port are mapped here.
        // The original mod also has horse/llama/mooshroom-variant skins, but
        // those mobs' skins were never ported in earlier sessions -- left out
        // rather than invented.
        if (stack.is(ModItems.BAT_SKIN.get())) return 1;
        if (stack.is(ModItems.CREEPER_SKIN.get())) return 6;
        if (stack.is(ModItems.DOLPHIN_SKIN.get())) return 8;
        if (stack.is(ModItems.DONKEY_SKIN.get())) return 9;
        if (stack.is(ModItems.FOX_SKIN.get())) return 10;
        if (stack.is(ModItems.HOGLIN_SKIN.get())) return 12;
        if (stack.is(ModItems.MULE_SKIN.get())) return 13;
        if (stack.is(ModItems.OCELOT_SKIN.get())) return 14;
        if (stack.is(ModItems.PANDA_SKIN.get())) return 15;
        if (stack.is(ModItems.PIG_SKIN.get())) return 16;
        if (stack.is(ModItems.SHEEP_SKIN.get())) return 18;
        if (stack.is(ModItems.POLAR_BEAR_SKIN.get())) return 21;
        if (stack.is(ModItems.COW_SKIN.get())) return 28;
        if (stack.is(ModItems.ZOGLIN_SKIN.get())) return 29;
        if (stack.is(ModItems.CAMEL_SKIN.get())) return 31;
        return 0;
    }

    private static ItemStack getSkinItem(int state) {
        return switch (state) {
            case 1 -> new ItemStack(ModItems.BAT_SKIN.get());
            case 6 -> new ItemStack(ModItems.CREEPER_SKIN.get());
            case 8 -> new ItemStack(ModItems.DOLPHIN_SKIN.get());
            case 9 -> new ItemStack(ModItems.DONKEY_SKIN.get());
            case 10 -> new ItemStack(ModItems.FOX_SKIN.get());
            case 12 -> new ItemStack(ModItems.HOGLIN_SKIN.get());
            case 13 -> new ItemStack(ModItems.MULE_SKIN.get());
            case 14 -> new ItemStack(ModItems.OCELOT_SKIN.get());
            case 15 -> new ItemStack(ModItems.PANDA_SKIN.get());
            case 16 -> new ItemStack(ModItems.PIG_SKIN.get());
            case 18 -> new ItemStack(ModItems.SHEEP_SKIN.get());
            case 21 -> new ItemStack(ModItems.POLAR_BEAR_SKIN.get());
            case 28 -> new ItemStack(ModItems.COW_SKIN.get());
            case 29 -> new ItemStack(ModItems.ZOGLIN_SKIN.get());
            case 31 -> new ItemStack(ModItems.CAMEL_SKIN.get());
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
        if (!(state.getBlock() instanceof SkinRackBlock)) {
            return;
        }

        ItemStack heldItem = player.getMainHandItem();
        int currentState = state.getValue(SkinRackBlock.BLOCKSTATE);

        // Only allow placing on empty rack (state 0)
        if (currentState == 0 && !heldItem.isEmpty()) {
            int skinState = getSkinState(heldItem);
            if (skinState > 0) {
                event.setCanceled(true);
                player.swing(InteractionHand.MAIN_HAND);
                level.setBlock(pos, state.setValue(SkinRackBlock.BLOCKSTATE, skinState), 3);
                playSound(level, pos, "block.wool.place");
                if (!player.getAbilities().instabuild) {
                    heldItem.shrink(1);
                }
            }
        }
    }

    /**
     * Called when the skin rack is broken - drops the appropriate skin based on blockstate
     */
    public static void onBroken(LevelAccessor level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;
        int currentState = state.getValue(SkinRackBlock.BLOCKSTATE);
        if (currentState > 0) {
            ItemStack skinItem = getSkinItem(currentState);
            if (!skinItem.isEmpty()) {
                net.minecraft.world.entity.item.ItemEntity entity = new net.minecraft.world.entity.item.ItemEntity(
                        (Level) level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, skinItem);
                entity.setPickUpDelay(10);
                ((Level) level).addFreshEntity(entity);
            }
        }
    }

    private static void playSound(Level level, BlockPos pos, String soundId) {
        SoundEvent sound = BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse(soundId));
        if (sound != null && !level.isClientSide()) {
            level.playSound(null, pos, sound, SoundSource.NEUTRAL, 1.0f, 1.0f);
        }
    }
}