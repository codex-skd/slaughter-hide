package com.skd.slaughterhide.block;

import com.mojang.serialization.MapCodec;
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

public class WoodenSpitRotisserieBlock extends Block {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 3);
    private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

    public static final MapCodec<WoodenSpitRotisserieBlock> CODEC = simpleCodec(WoodenSpitRotisserieBlock::new);

    public WoodenSpitRotisserieBlock(BlockBehaviour.Properties properties) {
        super(properties.sound(SoundType.WOOD).strength(1.0f, 10.0f).noOcclusion().mapColor(MapColor.WOOD).isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(BLOCKSTATE, 0));
    }

    private Function<BlockState, VoxelShape> makeShapes() {
        return this.getShapeForEachState(state -> {
            Direction facing = state.getValue(FACING);
            int blockState = state.getValue(BLOCKSTATE);
            
            if (blockState == 1 || blockState == 2 || blockState == 3) {
                // States 1, 2, 3 have the same shape (with meat on spit)
                return switch (facing) {
                    case NORTH -> Shapes.or(box(-15.0, -16.0, 0.0, -9.0, 14.0, 18.0),
                            box(25.0, -16.0, 0.0, 31.0, 14.0, 18.0),
                            box(-16.0, 8.0, 5.5, 32.0, 14.0, 11.5),
                            box(0.0527, 1.23717, 3.44153, 16.0527, 9.23717, 13.44153));
                    case EAST -> Shapes.or(box(-2.0, -16.0, -15.0, 16.0, 14.0, -9.0),
                            box(-2.0, -16.0, 25.0, 16.0, 14.0, 31.0),
                            box(4.5, 8.0, -16.0, 10.5, 14.0, 32.0),
                            box(2.55847, 1.23717, 0.0527, 12.55847, 9.23717, 16.0527));
                    case WEST -> Shapes.or(box(0.0, -16.0, 25.0, 18.0, 14.0, 31.0),
                            box(0.0, -16.0, -15.0, 18.0, 14.0, -9.0),
                            box(5.5, 8.0, -16.0, 11.5, 14.0, 32.0),
                            box(3.44153, 1.23717, -0.0527, 13.44153, 9.23717, 15.9473));
                    default -> Shapes.or(box(25.0, -16.0, -2.0, 31.0, 14.0, 16.0),
                            box(-15.0, -16.0, -2.0, -9.0, 14.0, 16.0),
                            box(-16.0, 8.0, 4.5, 32.0, 14.0, 10.5),
                            box(-0.0527, 1.23717, 2.55847, 15.9473, 9.23717, 12.55847));
                };
            }
            
            // State 0 - empty spit
            return switch (facing) {
                case NORTH -> Shapes.or(box(-15.0, -16.0, 0.0, -9.0, 14.0, 18.0),
                        box(25.0, -16.0, 0.0, 31.0, 14.0, 18.0),
                        box(-16.0, 8.0, 5.5, 32.0, 14.0, 11.5));
                case EAST -> Shapes.or(box(-2.0, -16.0, -15.0, 16.0, 14.0, -9.0),
                        box(-2.0, -16.0, 25.0, 16.0, 14.0, 31.0),
                        box(4.5, 8.0, -16.0, 10.5, 14.0, 32.0));
                case WEST -> Shapes.or(box(0.0, -16.0, 25.0, 18.0, 14.0, 31.0),
                        box(0.0, -16.0, -15.0, 18.0, 14.0, -9.0),
                        box(5.5, 8.0, -16.0, 11.5, 14.0, 32.0));
                default -> Shapes.or(box(25.0, -16.0, -2.0, 31.0, 14.0, 16.0),
                        box(-15.0, -16.0, -2.0, -9.0, 14.0, 16.0),
                        box(-16.0, 8.0, 4.5, 32.0, 14.0, 10.5));
            };
        });
    }

    @Override
    public MapCodec<WoodenSpitRotisserieBlock> codec() {
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
}