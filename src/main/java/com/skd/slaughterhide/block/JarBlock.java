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

public class JarBlock extends Block {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 19);
    private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

    public static final MapCodec<JarBlock> CODEC = simpleCodec(JarBlock::new);

    public JarBlock(BlockBehaviour.Properties properties) {
        super(properties.sound(SoundType.GLASS).strength(0.3f).noOcclusion().mapColor(MapColor.COLOR_LIGHT_BLUE).isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(BLOCKSTATE, 0));
    }

    private Function<BlockState, VoxelShape> makeShapes() {
        return this.getShapeForEachState(state -> switch (state.getValue(FACING)) {
            case NORTH -> Block.box(4.0, 0.1, 4.0, 12.0, 9.1, 12.001);
            case EAST -> Block.box(3.999, 0.1, 4.0, 12.0, 9.1, 12.0);
            case WEST -> Block.box(4.0, 0.1, 4.0, 12.001, 9.1, 12.0);
            default -> Block.box(4.0, 0.1, 3.999, 12.0, 9.1, 12.0);
        });
    }

    @Override
    public MapCodec<JarBlock> codec() {
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