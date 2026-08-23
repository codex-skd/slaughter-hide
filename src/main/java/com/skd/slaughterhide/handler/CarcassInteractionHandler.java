package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.Carcasses;
import com.skd.slaughterhide.block.CarcassBlock;
import com.skd.slaughterhide.block.DrainedCarcassBlock;
import com.skd.slaughterhide.block.entity.CarcassBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * Routes right-clicks on carcass blocks to the bleeding or cut-up handler,
 * mirroring how the original wired cleaver/knife interactions through
 * {@code PlayerInteractEvent.RightClickBlock}. Logic only runs on the server;
 * the result reaches clients through block updates, sounds and particles.
 */
public final class CarcassInteractionHandler {
    private CarcassInteractionHandler() {
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand() != InteractionHand.MAIN_HAND) {
            return;
        }
        LevelAccessor accessor = event.getLevel();
        if (accessor.isClientSide()) {
            return;
        }
        if (!(accessor instanceof Level level)) {
            return;
        }
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        BlockPos pos = event.getPos();
        if (!(level.getBlockEntity(pos) instanceof CarcassBlockEntity blockEntity)) {
            return;
        }
        CarcassDefinition definition = Carcasses.forMobId(blockEntity.getMobId());
        if (definition == null) {
            return;
        }
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof CarcassBlock) {
            CarcassBleedingHandler.handle(level, pos, player, blockEntity, definition);
        } else if (state.getBlock() instanceof DrainedCarcassBlock) {
            CarcassCutupHandler.handle(level, pos, player, blockEntity, definition);
        }
    }
}