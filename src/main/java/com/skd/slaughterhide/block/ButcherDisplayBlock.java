package com.skd.slaughterhide.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
import java.util.function.Supplier;

public class ButcherDisplayBlock extends Block {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    private final Function<BlockState, VoxelShape> shapes = this.makeShapes();
    private final Supplier<Block> topBlock;

    public ButcherDisplayBlock(BlockBehaviour.Properties properties, Supplier<Block> topBlock) {
        super(properties.strength(1.0f, 10.0f).noOcclusion()
                .isRedstoneConductor((bs, br, bp) -> false));
        this.topBlock = topBlock;
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
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        if (pos.getY() >= level.getMaxY() || !level.getBlockState(pos.above()).canBeReplaced(context)) {
            return null; // no room for the upper half
        }
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide()) {
            level.setBlock(pos.above(), this.topBlock.get().defaultBlockState()
                    .setValue(ButcherDisplayTopBlock.FACING, state.getValue(FACING)), 3);
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide()) {
            BlockPos top = pos.above();
            if (level.getBlockState(top).getBlock() instanceof ButcherDisplayTopBlock) {
                level.setBlock(top, Blocks.AIR.defaultBlockState(),
                        Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_KNOWN_SHAPE);
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        BlockPos top = pos.above();
        if (level.getBlockState(top).getBlock() instanceof ButcherDisplayTopBlock) {
            level.setBlock(top, Blocks.AIR.defaultBlockState(),
                    Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_KNOWN_SHAPE);
            level.updateNeighborsAt(top, this);
        }
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
