package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

/**
 * Handles BrainBlock attraction for zombies.
 * When a zombie spawns, it gets a goal to pathfind to nearby brain blocks.
 */
@EventBusSubscriber
public final class BrainAttractHandler {
    private BrainAttractHandler() {
    }

    @SubscribeEvent
    public static void onEntitySpawned(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Zombie zombie)) return;

        // Add goal to seek out brain blocks
        zombie.goalSelector.addGoal(1, new MoveToBlockGoal(zombie, 1.0, 4) {
            @Override
            protected boolean isValidTarget(LevelReader level, BlockPos pos) {
                BlockState state = level.getBlockState(pos);
                return state.getBlock() == ModBlocks.BRAIN.get();
            }
        });
    }
}