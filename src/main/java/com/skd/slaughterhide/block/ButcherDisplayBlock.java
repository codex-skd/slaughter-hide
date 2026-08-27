package com.skd.slaughterhide.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;

public class ButcherDisplayBlock extends Block {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

    public ButcherDisplayBlock(BlockBehaviour.Properties properties) {
        super(properties.strength(1.0f, 10.0f).noOcclusion()
                .isRedstoneConductor((bs, br, bp) -> false));
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    // The original display was a multi-block structure; this port is a single
    // decorative block, so the collision shape stays within one block (a
    // half-height counter with a thin backboard) instead of poking into the
    // block above and making it un-walkable.
    private Function<BlockState, VoxelShape> makeShapes() {
        return this.getShapeForEachState(state -> switch (state.getValue(FACING)) {
            case NORTH -> Shapes.or(
                    Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
                    Block.box(0.0, 0.0, 13.0, 16.0, 16.0, 16.0));
            case EAST -> Shapes.or(
                    Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 3.0, 16.0, 16.0));
            case WEST -> Shapes.or(
                    Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
                    Block.box(13.0, 0.0, 0.0, 16.0, 16.0, 16.0));
            default -> Shapes.or(
                    Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 3.0));
        });
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.shapes.apply(state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.shapes.apply(state);
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}
