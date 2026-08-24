package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.block.BloodSplatterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * Handles right-click interaction on BloodSplatterBlock to cycle its blockstate.
 * Only triggers when the player's main hand is empty (holding air).
 */
public final class BloodSplatterInteractionHandler {
    private BloodSplatterInteractionHandler() {
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
        ItemStack heldItem = player.getMainHandItem();
        if (!heldItem.isEmpty()) {
            return;
        }
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof BloodSplatterBlock)) {
            return;
        }
        int current = state.getValue(BloodSplatterBlock.BLOCKSTATE);
        if (current >= 9) {
            return;
        }
        level.setBlock(pos, state.setValue(BloodSplatterBlock.BLOCKSTATE, current + 1), 3);
        event.setCanceled(true);
        player.swing(InteractionHand.MAIN_HAND);
    }
}