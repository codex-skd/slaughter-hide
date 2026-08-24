package com.skd.slaughterhide.block;

import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.Carcasses;
import com.skd.slaughterhide.CarcassBlockProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Iron golem head mount block, with a repaired variant.
 */
public class IronGolemHeadMountBlock extends HeadMountBlock {
    public static final BooleanProperty REPAIRED = BooleanProperty.create("repaired");

    public IronGolemHeadMountBlock(Properties properties) {
        super(properties, Carcasses.IRON_GOLEM);
        registerDefaultState(stateDefinition.any()
                .setValue(CarcassBlockProperty.FACING, Direction.NORTH)
                .setValue(REPAIRED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(REPAIRED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // Shape is same regardless of repaired state; rely on definition
        return super.getShape(state, level, pos, context);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(CarcassBlockProperty.FACING, context.getHorizontalDirection().getOpposite())
                .setValue(REPAIRED, false);
    }
}