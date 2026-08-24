package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.block.BasinBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * Handles right-click interactions on BasinBlock:
 * - Water bucket on empty basin (state 0) -> fills to state 1
 * - Empty bucket on water-filled basin (state 1) -> drains to state 0, gives water bucket
 * - Empty hand on salt-ready basin (state 7) -> collects salt, resets to state 0
 */
public final class BasinInteractionHandler {
    private BasinInteractionHandler() {
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
        if (!(state.getBlock() instanceof BasinBlock)) {
            return;
        }

        ItemStack heldItem = player.getMainHandItem();
        int currentState = state.getValue(BasinBlock.BLOCKSTATE);

        // Water bucket on empty basin
        if (currentState == 0 && heldItem.getItem() == Items.WATER_BUCKET) {
            if (BasinUpdateTickHandler.tryFillWithWaterBucket(level, pos, state)) {
                event.setCanceled(true);
                player.swing(InteractionHand.MAIN_HAND);
                playSound(level, pos, "item.bucket.empty");
                if (!player.getAbilities().instabuild) {
                    heldItem.shrink(1);
                    if (heldItem.isEmpty()) {
                        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BUCKET));
                    }
                }
            }
            return;
        }

        // Empty bucket on water-filled basin (state 1)
        if (currentState == 1 && heldItem.getItem() == Items.BUCKET) {
            if (BasinUpdateTickHandler.tryEmptyWithBucket(level, pos, state)) {
                event.setCanceled(true);
                player.swing(InteractionHand.MAIN_HAND);
                playSound(level, pos, "item.bucket.fill");
                if (!player.getAbilities().instabuild) {
                    heldItem.shrink(1);
                    if (heldItem.isEmpty()) {
                        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.WATER_BUCKET));
                    }
                }
            }
            return;
        }

        // Empty hand on salt-ready basin (state 7)
        if (currentState == 7 && heldItem.isEmpty()) {
            if (BasinUpdateTickHandler.tryCollectSalt(level, pos, state)) {
                event.setCanceled(true);
                player.swing(InteractionHand.MAIN_HAND);
                playSound(level, pos, "item.brush.brushing.generic");
            }
            return;
        }
    }

    private static void playSound(Level level, BlockPos pos, String soundId) {
        SoundEvent sound = BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse(soundId));
        if (sound != null && !level.isClientSide()) {
            level.playSound(null, pos, sound, SoundSource.NEUTRAL, 1.0f, 1.0f);
        }
    }
}