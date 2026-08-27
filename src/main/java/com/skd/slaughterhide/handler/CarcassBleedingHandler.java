package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.CarcassBlockProperty;
import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.ServerWorkScheduler;
import com.skd.slaughterhide.block.BloodGrateBlock;
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
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

/**
 * Bleeding step: right-click a fresh carcass with a cleaver. Marks the carcass
 * as bleeding, plays sound and particles, then after the same 900-tick delay as
 * the original -- or instantly when the config toggle is set -- replaces the
 * block with its drained variant.
 *
 * <p>While it bleeds the carcass drips: every {@link #PULSE_INTERVAL} ticks a
 * burst of blood particles falls beneath it and, if a {@code blood_grate} sits
 * within 2 blocks below/around, that grate gains one fill level (capped at 3);
 * otherwise a thin {@code blood_puddle} is (re)placed on the ground beneath.</p>
 */
public final class CarcassBleedingHandler {
    private CarcassBleedingHandler() {
    }

    /** Tick delay before the fresh carcass drains, matching the original. */
    private static final int BLEED_TICKS = 900;

    /** How often, in ticks, a bleeding carcass drips while draining. */
    private static final int PULSE_INTERVAL = 40;

    /** Blood particles per drip pulse. */
    private static final int BLOOD_PARTICLE_COUNT = 6;

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

        if (level instanceof ServerLevel serverLevel) {
            bloodPulse(serverLevel, pos);
            if (SlaughterHideConfig.INSTANT_BLEED.get()) {
                transitionToDrained(serverLevel, pos, definition);
            } else {
                for (int t = PULSE_INTERVAL; t < BLEED_TICKS; t += PULSE_INTERVAL) {
                    ServerWorkScheduler.queue(t, () -> pulseIfStillBleeding(serverLevel, pos));
                }
                ServerWorkScheduler.queue(BLEED_TICKS, () -> recheckAndDrain(serverLevel, pos, definition));
            }
        }
    }

    /** A single drip: particles beneath the carcass + grate fill or a puddle. */
    private static void bloodPulse(ServerLevel level, BlockPos pos) {
        RandomSource rand = level.getRandom();
        for (int i = 0; i < BLOOD_PARTICLE_COUNT; i++) {
            double x = pos.getX() + 0.5 + (rand.nextDouble() - 0.5) * 0.6;
            double y = pos.getY() - 0.1 - rand.nextDouble() * 0.4;
            double z = pos.getZ() + 0.5 + (rand.nextDouble() - 0.5) * 0.6;
            level.sendParticles(ParticleTypes.DRIPPING_DRIPSTONE_LAVA, x, y, z, 1, 0.1, 0.1, 0.1, 0.0);
        }
        placeBloodBlock(level, pos);
    }

    /** Scheduled pulse: only drips while the carcass is still a fresh, bleeding one. */
    private static void pulseIfStillBleeding(ServerLevel level, BlockPos pos) {
        if (!(level.getBlockState(pos).getBlock() instanceof CarcassBlock)) {
            return;
        }
        if (level.getBlockEntity(pos) instanceof CarcassBlockEntity be && be.isBleeding() && !be.isDrained()) {
            bloodPulse(level, pos);
        }
    }

    /**
     * Tries to place a blood_grate within 2 blocks below/around the carcass.
     * If none found, places a blood_puddle on the ground directly beneath.
     */
    private static void placeBloodBlock(ServerLevel level, BlockPos pos) {
        // Search for a blood grate within 2 blocks below/around
        for (int dy = -2; dy <= 0; dy++) {
            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    BlockState checkState = level.getBlockState(checkPos);
                    if (checkState.is(ModBlocks.BLOOD_GRATE.get())) {
                        // Found a blood grate, increment its fill level
                        int current = checkState.getValue(BloodGrateBlock.FILL_LEVEL);
                        if (current < 3) {
                            level.setBlock(checkPos, checkState.setValue(BloodGrateBlock.FILL_LEVEL, current + 1), 3);
                        }
                        return;
                    }
                }
            }
        }
        // No blood grate found, place a blood puddle beneath
        BlockPos below = pos.below();
        if (level.getBlockState(below).isAir()) {
            level.setBlock(below, ModBlocks.BLOOD_PUDDLE.get().defaultBlockState(), 3);
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
        // One last drip as the carcass finishes draining.
        bloodPulse(level, pos);
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