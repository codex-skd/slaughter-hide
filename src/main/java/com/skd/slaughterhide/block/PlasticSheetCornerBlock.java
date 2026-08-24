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

public class PlasticSheetCornerBlock extends Block {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 1);
    private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

    public static final MapCodec<PlasticSheetCornerBlock> CODEC = simpleCodec(PlasticSheetCornerBlock::new);

    public PlasticSheetCornerBlock(BlockBehaviour.Properties properties) {
        super(properties.sound(SoundType.WOOL).strength(1.0f, 10.0f).noOcclusion().mapColor(MapColor.CLAY).isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(BLOCKSTATE, 0));
    }

    private Function<BlockState, VoxelShape> makeShapes() {
        return this.getShapeForEachState(state -> switch (state.getValue(FACING)) {
            case NORTH -> Shapes.or(box(0.0, 0.0, 15.0, 15.0, 16.0, 16.0), box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0));
            case EAST -> Shapes.or(box(0.0, 0.0, 0.0, 1.0, 16.0, 15.0), box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0));
            case WEST -> Shapes.or(box(15.0, 0.0, 1.0, 16.0, 16.0, 16.0), box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0));
            default -> Shapes.or(box(1.0, 0.0, 0.0, 16.0, 16.0, 1.0), box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0));
        });
    }

    @Override
    public MapCodec<PlasticSheetCornerBlock> codec() {
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
        world.scheduleTick(pos, this, 1);
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(blockstate, world, pos, random);
        BlockState above = world.getBlockState(pos.above());
        int newState = above.getBlock() instanceof PlasticSheetCornerBlock ? 0 : 1;
        if (blockstate.getValue(BLOCKSTATE) != newState) {
            world.setBlock(pos, blockstate.setValue(BLOCKSTATE, newState), 3);
        }
        world.scheduleTick(pos, this, 1);
    }
}