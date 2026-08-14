package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.CarcassBlockProperty;
import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.ServerWorkScheduler;
import com.skd.slaughterhide.block.CarcassBlock;
import com.skd.slaughterhide.block.DrainedCarcassBlock;
import com.skd.slaughterhide.block.entity.CarcassBlockEntity;
import com.skd.slaughterhide.config.SlaughterHideConfig;
import com.skd.slaughterhide.init.ModBlocks;
import com.skd.slaughterhide.tag.ModItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Bleeding step: right-click a fresh carcass with a cleaver. Marks the carcass
 * as bleeding, plays sound and particles, then after the same 900-tick delay as
 * the original -- or instantly when the config toggle is set -- replaces the
 * block with its drained variant.
 *
 * <p>Replaces the original {@code <Mob>carcassbleedingProcedure} and fills no
 * blood pull/grate (that part of the original is a separate mechanic using the
 * Blood/Bloodgrate blocks, not yet ported in this pass).</p>
 */
public final class CarcassBleedingHandler {
    private CarcassBleedingHandler() {
    }

    /** Tick delay before the fresh carcass drains, matching the original. */
    private static final int BLEED_TICKS = 900;

    public static void handle(Level level, BlockPos pos, Player player,
                              CarcassBlockEntity blockEntity, CarcassDefinition definition) {
        ItemStack stack = player.getMainHandItem();
        if (!stack.is(ModItemTags.CLEAVER)) {
            return;
        }
        if (blockEntity.isBleeding() || blockEntity.isDrained()) {
            return;
        }

        blockEntity.setBleeding(true);
        level.setBlock(pos, level.getBlockState(pos).setValue(CarcassBlockProperty.BLOCKSTATE, 1), 3);
        sync(level, pos);

        player.swing(InteractionHand.MAIN_HAND);
        playSound(level, pos, SoundEvents.PLAYER_ATTACK_SWEEP, 1.0f);
        playSound(level, pos, SoundEvents.HONEY_BLOCK_HIT, 1.0f);
        spawnBloodParticles(level, pos);

        if (level instanceof ServerLevel serverLevel) {
            if (SlaughterHideConfig.INSTANT_BLEED.get()) {
                transitionToDrained(serverLevel, pos, definition);
            } else {
                ServerWorkScheduler.queue(BLEED_TICKS, () -> recheckAndDrain(serverLevel, pos, definition));
            }
        }
    }

    private static void recheckAndDrain(ServerLevel level, BlockPos pos, CarcassDefinition definition) {
        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof CarcassBlock)) {
            return; // carcass was broken, moved or otherwise worked meanwhile
        }
        transitionToDrained(level, pos, definition);
    }

    private static void transitionToDrained(ServerLevel level, BlockPos pos, CarcassDefinition definition) {
        Direction facing = level.getBlockState(pos).getValue(CarcassBlockProperty.FACING);
        var drained = ModBlocks.drainedFor(definition.mobId());
        if (drained == null) {
            return;
        }
        BlockState drainedState = drained.get().defaultBlockState()
                .setValue(CarcassBlockProperty.FACING, facing);
        level.setBlock(pos, drainedState, 3);

        if (level.getBlockEntity(pos) instanceof CarcassBlockEntity blockEntity) {
            blockEntity.remember(definition);
            blockEntity.setBleeding(false);
            blockEntity.setDrained(true);
            blockEntity.setChanged();
            sync(level, pos);
        }
        playSound(level, pos, SoundEvents.HONEY_BLOCK_HIT, 1.0f);
        spawnBloodParticles(level, pos);
    }

    private static void spawnBloodParticles(Level level, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel) {
            double x = pos.getX() + 0.5;
            double z = pos.getZ() + 0.5;
            serverLevel.sendParticles(ParticleTypes.DRIPPING_LAVA, x, pos.getY() - 0.5, z, 4, 0.25, 0.1, 0.25, 0.05);
        }
    }

    static void playSound(Level level, BlockPos pos, net.minecraft.sounds.SoundEvent sound, float volume) {
        if (!level.isClientSide()) {
            level.playSound(null, pos, sound, SoundSource.NEUTRAL, volume, 1.0f);
        }
    }

    static void sync(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (level instanceof ServerLevel) {
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }
}