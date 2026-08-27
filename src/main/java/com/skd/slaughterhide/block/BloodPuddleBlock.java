package com.skd.slaughterhide.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * A thin decorative blood puddle block. It sits on the ground as a thin layer,
 * has no collision, and dries up over time via random ticks.
 */
public class BloodPuddleBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 0.1, 16.0);

    public BloodPuddleBlock(Properties properties) {
        super(properties.randomTicks());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        // Puddle dries up over time
        if (random.nextInt(4) == 0) {
            level.removeBlock(pos, false);
        }
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state) {
        return true;
    }
}
