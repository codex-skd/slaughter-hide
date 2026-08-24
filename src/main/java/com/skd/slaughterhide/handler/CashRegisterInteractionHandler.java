package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.block.CashRegisterBlock;
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
 * Handles right-click interactions on CashRegisterBlock:
 * - Empty hand: toggles drawer open/closed with sounds
 *
 * KNOWN LIMITATION: the original mod also opens a coin-loot-table payout when
 * right-clicked with a "coin" item, but no COIN item (or its loot table) was
 * ever registered anywhere in this port -- registering new economy items is
 * out of scope for this port pass, so only the drawer open/close half of the
 * original mechanic is wired up here.
 */
public final class CashRegisterInteractionHandler {
    private CashRegisterInteractionHandler() {
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
        if (!(state.getBlock() instanceof CashRegisterBlock)) {
            return;
        }

        ItemStack heldItem = player.getMainHandItem();
        boolean isOpen = state.getValue(CashRegisterBlock.OPEN);

        // Empty hand - toggle drawer
        if (heldItem.isEmpty()) {
            event.setCanceled(true);
            player.swing(InteractionHand.MAIN_HAND);
            level.setBlock(pos, state.setValue(CashRegisterBlock.OPEN, !isOpen), 3);
            if (isOpen) {
                playSound(level, pos, "butchery:drawer_close");
            } else {
                playSound(level, pos, "butchery:drawer_open");
                playSound(level, pos, "butchery:cha_ching");
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