package com.skd.slaughterhide.block;

import com.skd.slaughterhide.CarcassBlockProperty;
import com.skd.slaughterhide.CarcassDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Generic severed-head trophy block (dropped by the first cutting stage).
 * Parametrised by the mob so future heads only need a definition entry.
 */
public class TrophyHeadBlock extends Block {

    private final CarcassDefinition definition;

    public TrophyHeadBlock(Properties properties, CarcassDefinition definition) {
        super(properties.sound(net.minecraft.world.level.block.SoundType.HONEY_BLOCK)
                .strength(1.0f, 10.0f).noOcclusion()
                .isRedstoneConductor((state, level, pos) -> false));
        this.definition = definition;
        registerDefaultState(stateDefinition.any().setValue(CarcassBlockProperty.FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return definition.headShape().apply(state);
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CarcassBlockProperty.FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(CarcassBlockProperty.FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(CarcassBlockProperty.FACING, rotation.rotate(state.getValue(CarcassBlockProperty.FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(CarcassBlockProperty.FACING)));
    }
}