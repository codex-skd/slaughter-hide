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
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * Bleeding step: right-click a fresh carcass with a cleaver. Marks the carcass
 * as bleeding, plays sound and particles, then after the same 900-tick delay as
 * the original -- or instantly when the config toggle is set -- replaces the
 * block with its drained variant.
 *
 * <p>Includes visible blood effects using smoke particles spawned around the
 * carcass, matching the original's blood pooling concept without requiring
 * BloodDropletEntity (not yet available in this NeoForge 26.2 port pass).</p>
 */
public final class CarcassBleedingHandler {
    private CarcassBleedingHandler() {
    }

    /** Tick delay before the fresh carcass drains, matching the original. */
    private static final int BLEED_TICKS = 900;

    /** Number of smoke particle clusters spawned per bleeding event. */
    private static final int BLOOD_PARTICLE_COUNT = 12;

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

    /**
     * Spawns smoke particles visualizing the blood effect around the carcass
     * position, simulating blood droplets and pooling.
     */
    private static void spawnBloodParticles(Level level, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel) {
            RandomSource rand = serverLevel.getRandom();
            for (int i = 0; i < BLOOD_PARTICLE_COUNT; i++) {
                double offsetX = rand.nextDouble() - 0.5;
                double offsetZ = rand.nextDouble() - 0.5;
                double offsetY = rand.nextDouble() * 0.5;
                Vec3 posVec = new Vec3(pos.getX() + 0.5 + offsetX, pos.getY() + offsetY, pos.getZ() + 0.5 + offsetZ);
                serverLevel.sendParticles(ParticleTypes.SMOKE, posVec.x, posVec.y, posVec.z, 1, 0.3, 0.3, 0.3, 0.1f);
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
        // Blood particles fade when the carcass is fully drained
        spawnBloodParticles(level, pos);
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