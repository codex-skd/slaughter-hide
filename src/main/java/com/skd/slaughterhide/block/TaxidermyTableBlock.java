package com.skd.slaughterhide.block;

import com.mojang.serialization.MapCodec;
import com.skd.slaughterhide.block.entity.TaxidermyTableBlockEntity;
import com.skd.slaughterhide.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TaxidermyTableBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 1);

    public static final MapCodec<TaxidermyTableBlock> CODEC = simpleCodec(TaxidermyTableBlock::new);

    // The model is ~2 blocks wide on purpose; MC can't give a single block
    // collision in a neighbour tile, so the block itself is fully solid and the
    // overhanging half stays cosmetic (walk-through) until this becomes a real
    // 2-block structure.
    private static final VoxelShape SHAPE_NORTH = Shapes.block();
    private static final VoxelShape SHAPE_EAST = Shapes.block();
    private static final VoxelShape SHAPE_SOUTH = Shapes.block();
    private static final VoxelShape SHAPE_WEST = Shapes.block();

    public TaxidermyTableBlock(BlockBehaviour.Properties properties) {
        super(properties.sound(SoundType.WOOD).strength(1.0f, 10.0f).noOcclusion()
                .isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(BLOCKSTATE, 0));
    }

    @Override
    public MapCodec<TaxidermyTableBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, BLOCKSTATE);
    }

    /** The overhang tile (invisible solid half). */
    public static BlockPos extensionPos(BlockState state, BlockPos pos) {
        return pos.relative(state.getValue(FACING).getClockWise());
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        BlockPos ext = context.getClickedPos().relative(facing.getClockWise());
        if (!context.getLevel().getBlockState(ext).canBeReplaced(context)) {
            return null; // no room for the wide half
        }
        return this.defaultBlockState().setValue(FACING, facing).setValue(BLOCKSTATE, 0);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide()) {
            BlockPos ext = extensionPos(state, pos);
            level.setBlock(ext, ModBlocks.TAXIDERMY_TABLE_EXTENSION.get().defaultBlockState()
                    .setValue(TaxidermyTableExtensionBlock.FACING, state.getValue(FACING)), 3);
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide()) {
            BlockPos ext = extensionPos(state, pos);
            if (level.getBlockState(ext).getBlock() instanceof TaxidermyTableExtensionBlock) {
                level.setBlock(ext, Blocks.AIR.defaultBlockState(),
                        Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_KNOWN_SHAPE);
                level.levelEvent(player, 2001, ext, Block.getId(level.getBlockState(ext)));
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        BlockPos ext = extensionPos(state, pos);
        if (level.getBlockState(ext).getBlock() instanceof TaxidermyTableExtensionBlock) {
            level.setBlock(ext, Blocks.AIR.defaultBlockState(),
                    Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_KNOWN_SHAPE);
            level.updateNeighborsAt(ext, this);
        }
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
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof TaxidermyTableBlockEntity taxidermy) {
            taxidermy.serverTick();
        }
        world.scheduleTick(pos, this, 1);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.CONSUME;
        }
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof TaxidermyTableBlockEntity taxidermy) {
            player.openMenu(taxidermy, pos);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TaxidermyTableBlockEntity(pos, state);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }
}
