package com.skd.slaughterhide.menu;

import com.skd.slaughterhide.init.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.block.state.BlockState;

import com.skd.slaughterhide.block.FreezerBlock;

public class FreezerMenu extends ChestMenu {
    private final BlockPos pos;

    public FreezerMenu(int id, Inventory playerInv, net.minecraft.world.Container container, BlockPos pos) {
        super(ModMenus.FREEZER.get(), id, playerInv, container, 3);
        this.pos = pos;
    }

    public FreezerMenu(int id, Inventory playerInv, net.minecraft.network.FriendlyByteBuf buf) {
        this(id, playerInv, new net.minecraft.world.SimpleContainer(27), buf.readBlockPos());
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        if (!player.level().isClientSide() && pos != null) {
            BlockState state = player.level().getBlockState(pos);
            if (state.getBlock() instanceof FreezerBlock) {
                player.level().setBlock(pos, state.setValue(FreezerBlock.BLOCKSTATE, 0), 3);
                player.level().playSound(null, pos, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundSource.NEUTRAL, 1.0f, 1.0f);
            }
        }
    }
}
