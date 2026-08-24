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
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;

public class CashRegisterBlock extends Block {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

    public static final MapCodec<CashRegisterBlock> CODEC = simpleCodec(CashRegisterBlock::new);

    public CashRegisterBlock(BlockBehaviour.Properties properties) {
        super(properties.sound(SoundType.WOOD).strength(1.0f, 10.0f).noOcclusion().mapColor(MapColor.WOOD).isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false));
    }

    private Function<BlockState, VoxelShape> makeShapes() {
        return this.getShapeForEachState(state -> switch (state.getValue(FACING)) {
            case NORTH -> Shapes.or(box(2.0, 0.00373, 3.01086, 14.0, 3.00373, 13.01086),
                    box(2.0, 3.00373, 7.01086, 14.0, 7.00373, 13.01086),
                    box(2.0, 7.00373, 9.01086, 14.0, 11.00373, 13.01086));
            case EAST -> Shapes.or(box(2.98914, 0.00373, 2.0, 12.98914, 3.00373, 14.0),
                    box(2.98914, 3.00373, 2.0, 8.98914, 7.00373, 14.0),
                    box(2.98914, 7.00373, 2.0, 6.98914, 11.00373, 14.0));
            case WEST -> Shapes.or(box(3.01086, 0.00373, 2.0, 13.01086, 3.00373, 14.0),
                    box(7.01086, 3.00373, 2.0, 13.01086, 7.00373, 14.0),
                    box(9.01086, 7.00373, 2.0, 13.01086, 11.00373, 14.0));
            default -> Shapes.or(box(2.0, 0.00373, 2.98914, 14.0, 3.00373, 12.98914),
                    box(2.0, 3.00373, 2.98914, 14.0, 7.00373, 8.98914),
                    box(2.0, 7.00373, 2.98914, 14.0, 11.00373, 6.98914));
        });
    }

    @Override
    public MapCodec<CashRegisterBlock> codec() {
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
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, OPEN);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state == null) {
            return null;
        }
        return state.setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(OPEN, false);
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