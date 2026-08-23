package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.CarcassBlockProperty;
import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.block.RopeBlock;
import com.skd.slaughterhide.block.entity.CarcassBlockEntity;
import com.skd.slaughterhide.init.ModBlocks;
import com.skd.slaughterhide.item.CarcassPlacementItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public final class RopePlacementHandler {
    private RopePlacementHandler() {
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
        BlockState ropeState = level.getBlockState(pos);
        if (!(ropeState.getBlock() instanceof RopeBlock)) {
            return;
        }

        CarcassPlacementItem placementItem = asPlacementItem(player.getMainHandItem().getItem());
        InteractionHand heldIn = InteractionHand.MAIN_HAND;
        if (placementItem == null) {
            placementItem = asPlacementItem(player.getOffhandItem().getItem());
            heldIn = InteractionHand.OFF_HAND;
        }
        if (placementItem == null) {
            return;
        }

        BlockPos beside = pos.relative(ropeState.getValue(RopeBlock.FACING).getOpposite());
        if (!level.getBlockState(beside).isAir()) {
            return;
        }

        event.setCanceled(true);

        Direction facing = ropeState.getValue(RopeBlock.FACING);
        CarcassDefinition definition = placementItem.definition();
        var target = placementItem.isDrained()
                ? ModBlocks.drainedFor(definition.mobId())
                : ModBlocks.freshFor(definition.mobId());
        if (target == null) {
            return;
        }
        Block carcassBlock = target.get();
        BlockState toPlace = carcassBlock.defaultBlockState().setValue(CarcassBlockProperty.FACING, facing);
        if (!placementItem.isDrained()) {
            toPlace = toPlace.setValue(CarcassBlockProperty.BLOCKSTATE, 1);
        }
        level.setBlock(beside, toPlace, 3);
        if (level.getBlockEntity(beside) instanceof CarcassBlockEntity blockEntity) {
            blockEntity.remember(definition);
            if (placementItem.isDrained()) {
                blockEntity.setDrained(true);
            }
            level.sendBlockUpdated(beside, toPlace, toPlace, 3);
        }

        player.swing(InteractionHand.MAIN_HAND);
        SoundEvent chainHit = BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("minecraft:block.chain.hit"));
        if (chainHit != null && !level.isClientSide()) {
            level.playSound(null, pos, chainHit, SoundSource.NEUTRAL, 1.0f, 1.0f);
        }

        if (!player.getAbilities().instabuild) {
            ItemStack held = heldIn == InteractionHand.MAIN_HAND ? player.getMainHandItem() : player.getOffhandItem();
            held.shrink(1);
        }
    }

    private static CarcassPlacementItem asPlacementItem(net.minecraft.world.item.Item item) {
        return item instanceof CarcassPlacementItem placement ? placement : null;
    }
}