package com.skd.slaughterhide.block;

import com.skd.slaughterhide.CarcassBlockProperty;
import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.block.entity.CarcassBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
 * Generic fresh carcass block. One class replaces the original per-mob
 * {@code <Mob>CarcassBlock}; the {@link CarcassDefinition} passed at
 * construction carries the mob-specific shapes and loot tables.
 */
public class CarcassBlock extends Block implements EntityBlock {

    private final CarcassDefinition definition;

    public CarcassBlock(Properties properties, CarcassDefinition definition) {
        super(properties.sound(SoundType.HONEY_BLOCK).strength(1.0f, 10.0f).noOcclusion()
                .isRedstoneConductor((state, level, pos) -> false));
        this.definition = definition;
        registerDefaultState(stateDefinition.any()
                .setValue(CarcassBlockProperty.FACING, Direction.NORTH)
                .setValue(CarcassBlockProperty.BLOCKSTATE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return definition.freshCarcassShape().apply(state);
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CarcassBlockProperty.FACING, CarcassBlockProperty.BLOCKSTATE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // blockstate=1 ("hung") so a manually placed carcass is immediately
        // ready to bleed, same as the original's freshly-spawned carcass.
        return defaultBlockState()
                .setValue(CarcassBlockProperty.FACING, context.getHorizontalDirection().getOpposite())
                .setValue(CarcassBlockProperty.BLOCKSTATE, 1);
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
        // The block itself is already mob-specific (one CarcassBlock instance
        // per mob, built with its CarcassDefinition), so the block entity can
        // remember which mob it belongs to right away instead of relying on
        // whoever placed it (world-death handler, player, /setblock...) to
        // call remember() afterwards.
        CarcassBlockEntity blockEntity = new CarcassBlockEntity(pos, state);
        blockEntity.remember(definition);
        return blockEntity;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player,
                                       ItemStack toolStack, boolean willHarvest, FluidState fluid) {
        boolean removed = super.onDestroyedByPlayer(state, level, pos, player, toolStack, willHarvest, fluid);
        if (removed && !level.isClientSide() && !player.getAbilities().instabuild) {
            // The fresh carcass is only relocatable before it has been worked on.
            Block.popResource(level, pos, new ItemStack(this));
        }
        return removed;
    }
}