package com.skd.slaughterhide.block;

import com.mojang.serialization.MapCodec;
import com.skd.slaughterhide.handler.BasinUpdateTickHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;

public class BasinBlock extends Block {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 7);
    private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

    public static final MapCodec<BasinBlock> CODEC = simpleCodec(BasinBlock::new);

    public BasinBlock(BlockBehaviour.Properties properties) {
        super(properties.sound(SoundType.STONE).strength(1.0f, 10.0f).noOcclusion().mapColor(MapColor.STONE).isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(BLOCKSTATE, 0));
    }

    private Function<BlockState, VoxelShape> makeShapes() {
        return this.getShapeForEachState(state -> {
            Direction facing = state.getValue(FACING);
            int blockState = state.getValue(BLOCKSTATE);
            if (blockState >= 1 && blockState <= 6) {
                return switch (facing) {
                    case NORTH -> Shapes.or(box(0.0, 3.0, 0.0, 2.0, 12.0, 16.0),
                            box(2.0, 3.0, 2.0, 14.0, 4.0, 14.0),
                            box(14.0, 3.0, 0.0, 16.0, 12.0, 16.0),
                            box(2.0, 3.0, 0.0, 14.0, 12.0, 2.0),
                            box(2.0, 3.0, 14.0, 14.0, 12.0, 16.0),
                            box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0));
                    case EAST -> Shapes.or(box(0.0, 3.0, 0.0, 16.0, 12.0, 2.0),
                            box(2.0, 3.0, 2.0, 14.0, 4.0, 14.0),
                            box(0.0, 3.0, 14.0, 16.0, 12.0, 16.0),
                            box(14.0, 3.0, 2.0, 16.0, 12.0, 14.0),
                            box(0.0, 3.0, 2.0, 2.0, 12.0, 14.0),
                            box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0));
                    case WEST -> Shapes.or(box(0.0, 3.0, 14.0, 16.0, 12.0, 16.0),
                            box(2.0, 3.0, 2.0, 14.0, 4.0, 14.0),
                            box(0.0, 3.0, 0.0, 16.0, 12.0, 2.0),
                            box(0.0, 3.0, 2.0, 2.0, 12.0, 14.0),
                            box(14.0, 3.0, 2.0, 16.0, 12.0, 14.0),
                            box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0));
                    default -> Shapes.or(box(14.0, 3.0, 0.0, 16.0, 12.0, 16.0),
                            box(2.0, 3.0, 2.0, 14.0, 4.0, 14.0),
                            box(0.0, 3.0, 0.0, 2.0, 12.0, 16.0),
                            box(2.0, 3.0, 14.0, 14.0, 12.0, 16.0),
                            box(2.0, 3.0, 0.0, 14.0, 12.0, 2.0),
                            box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0));
                };
            }
            if (blockState == 7) {
                return switch (facing) {
                    case NORTH -> Shapes.or(box(0.0, 3.0, 0.0, 2.0, 12.0, 16.0),
                            box(2.0, 3.0, 2.0, 14.0, 4.0, 14.0),
                            box(14.0, 3.0, 0.0, 16.0, 12.0, 16.0),
                            box(2.0, 3.0, 0.0, 14.0, 12.0, 2.0),
                            box(2.0, 3.0, 14.0, 14.0, 12.0, 16.0),
                            box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0));
                    case EAST -> Shapes.or(box(0.0, 3.0, 0.0, 16.0, 12.0, 2.0),
                            box(2.0, 3.0, 2.0, 14.0, 4.0, 14.0),
                            box(0.0, 3.0, 14.0, 16.0, 12.0, 16.0),
                            box(14.0, 3.0, 2.0, 16.0, 12.0, 14.0),
                            box(0.0, 3.0, 2.0, 2.0, 12.0, 14.0),
                            box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0));
                    case WEST -> Shapes.or(box(0.0, 3.0, 14.0, 16.0, 12.0, 16.0),
                            box(2.0, 3.0, 2.0, 14.0, 4.0, 14.0),
                            box(0.0, 3.0, 0.0, 16.0, 12.0, 2.0),
                            box(0.0, 3.0, 2.0, 2.0, 12.0, 14.0),
                            box(14.0, 3.0, 2.0, 16.0, 12.0, 14.0),
                            box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0));
                    default -> Shapes.or(box(14.0, 3.0, 0.0, 16.0, 12.0, 16.0),
                            box(2.0, 3.0, 2.0, 14.0, 4.0, 14.0),
                            box(0.0, 3.0, 0.0, 2.0, 12.0, 16.0),
                            box(2.0, 3.0, 14.0, 14.0, 12.0, 16.0),
                            box(2.0, 3.0, 0.0, 14.0, 12.0, 2.0),
                            box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0));
                };
            }
            return switch (facing) {
                case NORTH -> Shapes.or(box(0.0, 3.0, 0.0, 2.0, 12.0, 16.0),
                        box(2.0, 3.0, 2.0, 14.0, 4.0, 14.0),
                        box(14.0, 3.0, 0.0, 16.0, 12.0, 16.0),
                        box(2.0, 3.0, 0.0, 14.0, 12.0, 2.0),
                        box(2.0, 3.0, 14.0, 14.0, 12.0, 16.0),
                        box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0));
                case EAST -> Shapes.or(box(0.0, 3.0, 0.0, 16.0, 12.0, 2.0),
                        box(2.0, 3.0, 2.0, 14.0, 4.0, 14.0),
                        box(0.0, 3.0, 14.0, 16.0, 12.0, 16.0),
                        box(14.0, 3.0, 2.0, 16.0, 12.0, 14.0),
                        box(0.0, 3.0, 2.0, 2.0, 12.0, 14.0),
                        box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0));
                case WEST -> Shapes.or(box(0.0, 3.0, 14.0, 16.0, 12.0, 16.0),
                        box(2.0, 3.0, 2.0, 14.0, 4.0, 14.0),
                        box(0.0, 3.0, 0.0, 16.0, 12.0, 2.0),
                        box(0.0, 3.0, 2.0, 2.0, 12.0, 14.0),
                        box(14.0, 3.0, 2.0, 16.0, 12.0, 14.0),
                        box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0));
                default -> Shapes.or(box(14.0, 3.0, 0.0, 16.0, 12.0, 16.0),
                        box(2.0, 3.0, 2.0, 14.0, 4.0, 14.0),
                        box(0.0, 3.0, 0.0, 2.0, 12.0, 16.0),
                        box(2.0, 3.0, 14.0, 14.0, 12.0, 16.0),
                        box(2.0, 3.0, 0.0, 14.0, 12.0, 2.0),
                        box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0));
            };
        });
    }

    @Override
    public MapCodec<BasinBlock> codec() {
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
        builder.add(FACING, BLOCKSTATE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state == null) {
            return null;
        }
        return state.setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(BLOCKSTATE, 0);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 2000);
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(blockstate, world, pos, random);
        BasinUpdateTickHandler.tick(world, pos, blockstate);
        world.scheduleTick(pos, this, 2000);
    }
}