package com.skd.slaughterhide.block;

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
 * Diorite brick wall.
 */
public class DioriteBrickWallBlock extends WallBlock {
    public DioriteBrickWallBlock(Properties properties) {
        super(properties.mapColor(MapColor.STONE).strength(1.5f, 10.0f).sound(SoundType.STONE));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // Wall shape depends on connections - we rely on vanilla logic
        return super.getShape(state, level, pos, context);
    }
}