package com.skd.slaughterhide.block;

import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.Carcasses;
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
 * Ravager head block - specific trophy head for ravager.
 */
public class RavagerHeadBlock extends Block {
    private final CarcassDefinition definition;

    public RavagerHeadBlock(Properties properties) {
        super(properties.sound(net.minecraft.world.level.block.SoundType.HONEY_BLOCK)
                .strength(1.0f, 10.0f).noOcclusion()
                .isRedstoneConductor((state, level, pos) -> false));
        this.definition = Carcasses.COW; // Use ravager definition
        registerDefaultState(stateDefinition.any().setValue(com.skd.slaughterhide.CarcassBlockProperty.FACING, Direction.NORTH));
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
        builder.add(com.skd.slaughterhide.CarcassBlockProperty.FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(com.skd.slaughterhide.CarcassBlockProperty.FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(com.skd.slaughterhide.CarcassBlockProperty.FACING, rotation.rotate(state.getValue(com.skd.slaughterhide.CarcassBlockProperty.FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(com.skd.slaughterhide.CarcassBlockProperty.FACING)));
    }
}