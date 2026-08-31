package com.skd.slaughterhide.block;

import com.skd.slaughterhide.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

/**
 * A drain grate that accumulates blood. The blockstate tracks fill level 0-3.
 * The grate is only filled by a bleeding carcass placed above it (see
 * {@link com.skd.slaughterhide.handler.CarcassBleedingHandler}) -- it does not
 * fill on its own. When full (3) and right-clicked with a glass bottle, the
 * player receives a {@code bottle_of_blood} and the grate resets to empty (0).
 */
public class BloodGrateBlock extends Block {
    public static final IntegerProperty FILL_LEVEL = IntegerProperty.create("fill_level", 0, 3);

    public BloodGrateBlock(Properties properties) {
        // Metal drain grate: needs a real mining pass in survival, not a one-hit break.
        super(properties.strength(3.0f, 6.0f).sound(SoundType.METAL));
        this.registerDefaultState(this.stateDefinition.any().setValue(FILL_LEVEL, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FILL_LEVEL);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (state.getValue(FILL_LEVEL) >= 3 && stack.is(Items.GLASS_BOTTLE)) {
            if (!level.isClientSide()) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                if (!player.getInventory().add(new ItemStack(ModItems.BOTTLE_OF_BLOOD.get()))) {
                    player.drop(new ItemStack(ModItems.BOTTLE_OF_BLOOD.get()), false);
                }
                level.setBlock(pos, state.setValue(FILL_LEVEL, 0), 3);
                level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    /** Empty-hand right-click: read out the current blood fill level on the action bar. */
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        if (!level.isClientSide() && player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
            int fill = state.getValue(FILL_LEVEL);
            int pct = fill * 100 / 3;
            serverPlayer.sendSystemMessage(
                    Component.translatable("message.slaughter_hide.blood_grate.level", fill, 3, pct + "%"), true);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state) {
        return true;
    }
}
