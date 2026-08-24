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

public class SkinRackBlock extends Block {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 31);
    private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

    public static final MapCodec<SkinRackBlock> CODEC = simpleCodec(SkinRackBlock::new);

    public SkinRackBlock(BlockBehaviour.Properties properties) {
        super(properties.sound(SoundType.WOOD).strength(1.0f, 10.0f).noOcclusion().mapColor(MapColor.WOOD).isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(BLOCKSTATE, 0));
    }

    private Function<BlockState, VoxelShape> makeShapes() {
        return this.getShapeForEachState(state -> switch (state.getValue(FACING)) {
            case NORTH -> Shapes.or(box(-0.4998, 0.0, 1.1, 16.5004, 5.0, 14.6),
                    box(-0.4998, 5.0, 3.1, 16.5004, 10.0, 12.6),
                    box(-0.4998, 10.0, 5.1, 16.5004, 15.0, 10.6));
            case EAST -> Shapes.or(box(1.4, 0.0, -0.4998, 14.9, 5.0, 16.5004),
                    box(3.4, 5.0, -0.4998, 12.9, 10.0, 16.5004),
                    box(5.4, 10.0, -0.4998, 10.9, 15.0, 16.5004));
            case WEST -> Shapes.or(box(1.1, 0.0, -0.5004, 14.6, 5.0, 16.4998),
                    box(3.1, 5.0, -0.5004, 12.6, 10.0, 16.4998),
                    box(5.1, 10.0, -0.5004, 10.6, 15.0, 16.4998));
            default -> Shapes.or(box(-0.5004, 0.0, 1.4, 16.4998, 5.0, 14.9),
                    box(-0.5004, 5.0, 3.4, 16.4998, 10.0, 12.9),
                    box(-0.5004, 10.0, 5.4, 16.4998, 15.0, 10.9));
        });
    }

    @Override
    public MapCodec<SkinRackBlock> codec() {
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