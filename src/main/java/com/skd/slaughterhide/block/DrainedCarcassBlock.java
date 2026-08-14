package com.skd.slaughterhide.block;

import com.skd.slaughterhide.CarcassBlockProperty;
import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.block.entity.CarcassBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Generic drained carcass block: a resilient copy that can still be picked up
 * before work starts (cut stage 0) but is glued to its spot once cutting has
 * begun (cut stages 6+), where breaking it just scraps a raw beef instead.
 */
public class DrainedCarcassBlock extends Block implements EntityBlock {

    /** Cut stage marking "up to now untouched" on the drained block. */
    public static final int STAGE_UNTOUCHED = 0;
    /** First cut stage, after the head is removed. */
    public static final int STAGE_HEAD_CUT = 6;
    /** After skinning. */
    public static final int STAGE_SKINNED = 7;
    /** After the first meat cut. */
    public static final int STAGE_CUT_1 = 8;
    /** After the second meat cut. */
    public static final int STAGE_CUT_2 = 9;

    private final CarcassDefinition definition;

    public DrainedCarcassBlock(Properties properties, CarcassDefinition definition) {
        super(properties.sound(SoundType.HONEY_BLOCK).strength(1.0f, 10.0f).noOcclusion()
                .isRedstoneConductor((state, level, pos) -> false));
        this.definition = definition;
        registerDefaultState(stateDefinition.any()
                .setValue(CarcassBlockProperty.FACING, Direction.NORTH)
                .setValue(CarcassBlockProperty.DRAINED_BLOCKSTATE, STAGE_UNTOUCHED));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return definition.drainedCarcassShape().apply(state);
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CarcassBlockProperty.FACING, CarcassBlockProperty.DRAINED_BLOCKSTATE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(CarcassBlockProperty.FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(CarcassBlockProperty.FACING, rotation.rotate(state.getValue(CarcassBlockProperty.FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(CarcassBlockProperty.FACING)));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CarcassBlockEntity(pos, state);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player,
                                       ItemStack toolStack, boolean willHarvest, FluidState fluid) {
        boolean removed = super.onDestroyedByPlayer(state, level, pos, player, toolStack, willHarvest, fluid);
        if (removed && !level.isClientSide() && !player.getAbilities().instabuild) {
            if (state.getValue(CarcassBlockProperty.DRAINED_BLOCKSTATE) == STAGE_UNTOUCHED) {
                Block.popResource(level, pos, new ItemStack(this));
            } else {
                // Mid-cut carcass is no longer relocatable; leave a scrap of beef.
                Block.popResource(level, pos, new ItemStack(Items.BEEF));
            }
        }
        return removed;
    }
}