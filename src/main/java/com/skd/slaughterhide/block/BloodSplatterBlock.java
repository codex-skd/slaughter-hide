package com.skd.slaughterhide.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;

public class BloodSplatterBlock extends FaceAttachedHorizontalDirectionalBlock {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<AttachFace> FACE = FaceAttachedHorizontalDirectionalBlock.FACE;
    public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 9);
    private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

    public static final MapCodec<BloodSplatterBlock> CODEC = simpleCodec(BloodSplatterBlock::new);

    public BloodSplatterBlock(BlockBehaviour.Properties properties) {
        super(properties.sound(SoundType.SLIME_BLOCK).strength(1.0f, 10.0f).noOcclusion().mapColor(MapColor.COLOR_RED).isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(FACE, AttachFace.WALL)
                .setValue(BLOCKSTATE, 0));
    }

    private Function<BlockState, VoxelShape> makeShapes() {
        return this.getShapeForEachState(state -> {
            Direction facing = state.getValue(FACING);
            AttachFace face = state.getValue(FACE);
            switch (facing) {
                case NORTH -> {
                    return switch (face) {
                        case FLOOR -> box(0.0, 0.0, 0.0, 16.0, 0.2, 16.0);
                        case WALL -> box(0.0, 0.0, 15.8, 16.0, 16.0, 16.0);
                        case CEILING -> box(0.0, 15.8, 0.0, 16.0, 16.0, 16.0);
                    };
                }
                case EAST -> {
                    return switch (face) {
                        case FLOOR -> box(0.0, 0.0, 0.0, 16.0, 0.2, 16.0);
                        case WALL -> box(0.0, 0.0, 0.0, 0.2, 16.0, 16.0);
                        case CEILING -> box(0.0, 15.8, 0.0, 16.0, 16.0, 16.0);
                    };
                }
                case WEST -> {
                    return switch (face) {
                        case FLOOR -> box(0.0, 0.0, 0.0, 16.0, 0.2, 16.0);
                        case WALL -> box(15.8, 0.0, 0.0, 16.0, 16.0, 16.0);
                        case CEILING -> box(0.0, 15.8, 0.0, 16.0, 16.0, 16.0);
                    };
                }
                default -> {
                    return switch (face) {
                        case FLOOR -> box(0.0, 0.0, 0.0, 16.0, 0.2, 16.0);
                        case WALL -> box(0.0, 0.0, 0.0, 16.0, 16.0, 0.2);
                        case CEILING -> box(0.0, 15.8, 0.0, 16.0, 16.0, 16.0);
                    };
                }
            }
        });
    }

    @Override
    public MapCodec<BloodSplatterBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.shapes.apply(state);
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, FACE, BLOCKSTATE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state == null) {
            return null;
        }
        return state.setValue(FACE, this.faceForDirection(context.getNearestLookingDirection()))
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(BLOCKSTATE, 0);
    }

    private AttachFace faceForDirection(Direction direction) {
        if (direction.getAxis() == Direction.Axis.Y) {
            return direction == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR;
        }
        return AttachFace.WALL;
    }
}