package com.skd.slaughterhide.block;

import com.mojang.serialization.MapCodec;
import com.skd.slaughterhide.block.entity.TaxidermyTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;

/**
 * Invisible solid half of the two-block {@link TaxidermyTableBlock}. It only
 * exists to give the wide table model real collision in the neighbouring tile.
 * Placed and removed by the main block; it forwards its own break back to the
 * main block and its right-click to the main block's menu.
 */
public class TaxidermyTableExtensionBlock extends Block {
    /** Same facing as the main block. Main is at {@code pos.relative(FACING.getClockWise().getOpposite())}. */
    public static final EnumProperty<Direction> FACING =
            net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING;

    public static final MapCodec<TaxidermyTableExtensionBlock> CODEC = simpleCodec(TaxidermyTableExtensionBlock::new);

    public TaxidermyTableExtensionBlock(BlockBehaviour.Properties properties) {
        super(properties.sound(SoundType.WOOD).strength(1.0f, 10.0f).noOcclusion()
                .isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<TaxidermyTableExtensionBlock> codec() {
        return CODEC;
    }

    /** The wide model on the main block draws over this tile, so render nothing here. */
    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state) {
        return true;
    }

    private static BlockPos mainPos(BlockState state, BlockPos pos) {
        return pos.relative(state.getValue(FACING).getClockWise().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
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
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.CONSUME;
        }
        if (level.getBlockEntity(mainPos(state, pos)) instanceof TaxidermyTableBlockEntity taxidermy) {
            player.openMenu(taxidermy, mainPos(state, pos));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide()) {
            BlockPos main = mainPos(state, pos);
            BlockState mainState = level.getBlockState(main);
            if (mainState.getBlock() instanceof TaxidermyTableBlock) {
                // Let the main block drop the item; remove it without a second drop.
                level.destroyBlock(main, !player.getAbilities().instabuild, player);
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        BlockPos main = mainPos(state, pos);
        if (level.getBlockState(main).getBlock() instanceof TaxidermyTableBlock) {
            level.setBlock(main, Blocks.AIR.defaultBlockState(),
                    Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_KNOWN_SHAPE);
            level.updateNeighborsAt(main, this);
        }
    }

    @Override
    public ItemStack getCloneItemStack(net.minecraft.world.level.LevelReader level, BlockPos pos, BlockState state,
                                       boolean includeData) {
        return new ItemStack(com.skd.slaughterhide.init.ModItems.TAXIDERMY_TABLE.get());
    }
}
