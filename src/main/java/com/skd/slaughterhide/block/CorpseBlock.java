package com.skd.slaughterhide.block;

import com.skd.slaughterhide.CarcassDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;

/**
 * Generic corpse block for humanoid mobs. Uses a minimal BlockEntity for
 * client sync; progression is encoded in the {@code blockstate} property.
 */
public class CorpseBlock extends Block implements EntityBlock {

    public static final net.minecraft.world.level.block.state.properties.EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    /** 0 = lying/fresh, 1 = hanging (ready to cut) */
    public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 9);

    /** Cutting stages: 1 = hanging, then 2..9 are progressive cuts. */
    public static final int STAGE_HANGING = 1;
    public static final int STAGE_HEAD_CUT = 2;
    public static final int STAGE_SKINNED = 3;
    public static final int STAGE_CUT_1 = 4;
    public static final int STAGE_CUT_2 = 5;
    public static final int STAGE_CUT_3 = 6;
    public static final int STAGE_ORGANS_1 = 7;
    public static final int STAGE_ORGANS_2 = 8;
    public static final int STAGE_ORGANS_3 = 9;

    private final java.util.function.Function<BlockState, VoxelShape> shapes;
    private final CarcassDefinition definition;

    public CorpseBlock(BlockBehaviour.Properties properties, CarcassDefinition definition, java.util.function.Function<BlockState, VoxelShape> shapes) {
        super(properties.sound(SoundType.HONEY_BLOCK).strength(1.0f, 10.0f).noOcclusion()
                .isRedstoneConductor((bs, br, bp) -> false));
        this.definition = definition;
        this.shapes = shapes;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(BLOCKSTATE, 0));
    }

    public CarcassDefinition getDefinition() {
        return definition;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, net.minecraft.core.BlockPos pos, CollisionContext context) {
        return this.shapes.apply(state);
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, net.minecraft.core.BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, BLOCKSTATE);
    }

    @Override
    public BlockState getStateForPlacement(net.minecraft.world.item.context.BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(BLOCKSTATE, 0);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public net.minecraft.world.level.block.entity.BlockEntity newBlockEntity(net.minecraft.core.BlockPos pos, BlockState state) {
        com.skd.slaughterhide.block.entity.CorpseBlockEntity entity = new com.skd.slaughterhide.block.entity.CorpseBlockEntity(pos, state);
        entity.remember(definition);
        return entity;
    }
}