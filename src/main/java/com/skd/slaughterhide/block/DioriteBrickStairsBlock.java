package com.skd.slaughterhide.block;

import com.skd.slaughterhide.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Diorite brick stairs.
 */
public class DioriteBrickStairsBlock extends StairBlock {
    public DioriteBrickStairsBlock(Properties properties) {
        super(ModBlocks.DIORITE_BRICKS.get().defaultBlockState(), properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // Stair shape depends on state - we rely on vanilla logic
        return super.getShape(state, level, pos, context);
    }
}