package com.skd.slaughterhide.block;

import com.mojang.serialization.MapCodec;
import com.skd.slaughterhide.init.ModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.skd.slaughterhide.block.entity.FreezerBlockEntity;

public class FreezerBlock extends BaseEntityBlock {
	public static final EnumProperty<Direction> FACING = net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING;
	// Original declares 0-5 (6 visual states); only 0 (closed) and 1 (lid open) are ever set by the GUI open/close logic.
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 5);

	public static final MapCodec<FreezerBlock> CODEC = simpleCodec(FreezerBlock::new);

	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

	public FreezerBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.METAL).strength(1.0f, 10.0f).mapColor(MapColor.METAL).noOcclusion()
				.isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(BLOCKSTATE, 0));
	}

	@Override
	public MapCodec<FreezerBlock> codec() {
		return CODEC;
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState state) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
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

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
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
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new FreezerBlockEntity(pos, state);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (level.isClientSide()) {
			return InteractionResult.CONSUME;
		}
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof FreezerBlockEntity freezer) {
			// Original: the GUI always opens; the lid animation (blockstate 0->1, trapdoor sound,
			// freezersmoke particles) is skipped while sneaking.
			if (!player.isShiftKeyDown()) {
				level.setBlock(pos, state.setValue(BLOCKSTATE, 1), 3);
				level.playSound(null, pos, SoundEvents.IRON_TRAPDOOR_OPEN, SoundSource.NEUTRAL, 1.0f, 1.0f);
				spawnOpenParticles((ServerLevel) level, pos);
			}
			player.openMenu(freezer, pos);
		}
		return InteractionResult.SUCCESS;
	}

	private static void spawnOpenParticles(ServerLevel level, BlockPos pos) {
		SimpleParticleType particle = ModParticleTypes.FREEZER_SMOKE.get();
		double x = pos.getX() + 0.5;
		double y = pos.getY();
		double z = pos.getZ() + 0.5;
		level.sendParticles(particle, x + 0.1, y + 0.5, z + 0.1, 5, 0.1, 0.3, 0.2, 0.0);
		level.sendParticles(particle, x, y + 1.0, z, 5, 0.2, 0.2, 0.2, 0.0);
		level.sendParticles(particle, x - 0.3, y + 0.5, z, 5, 0.1, 0.3, 0.2, 0.0);
		level.sendParticles(particle, x, y + 1.0, z - 0.3, 5, 0.2, 0.2, 0.2, 0.0);
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
