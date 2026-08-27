package com.skd.slaughterhide.menu;

import com.skd.slaughterhide.init.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class TaxidermyTableMenu extends AbstractContainerMenu {
    private final BlockPos pos;
    private final BlockEntity blockEntity;

    public TaxidermyTableMenu(int id, Inventory playerInv, net.minecraft.world.Container container, BlockPos pos) {
        super(ModMenus.TAXIDERMY_TABLE.get(), id);
        this.pos = pos;
        this.blockEntity = container instanceof BlockEntity be ? be : null;

        // Slot 0: Skin at (44, 25)
        this.addSlot(new Slot(container, 0, 44, 25));
        // Slot 1: Head at (80, 12)
        this.addSlot(new Slot(container, 1, 80, 12));
        // Slot 2: Planks at (116, 25)
        this.addSlot(new Slot(container, 2, 116, 25));
        // Slot 3: Output at (80, 56) — no insertion
        this.addSlot(new Slot(container, 3, 80, 56) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        // Player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + (row + 1) * 9, 8 + col * 18, 84 + row * 18));
            }
        }
        // Player hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }

    public TaxidermyTableMenu(int id, Inventory playerInv, net.minecraft.network.FriendlyByteBuf buf) {
        this(id, playerInv, new net.minecraft.world.SimpleContainer(4), buf.readBlockPos());
    }

    @Override
    public boolean stillValid(Player player) {
        if (blockEntity != null) {
            return net.minecraft.world.Container.stillValidBlockEntity(blockEntity, player);
        }
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 4) {
                if (!this.moveItemStackTo(itemstack1, 4, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (!this.moveItemStackTo(itemstack1, 0, 4, false)) {
                if (index < 31 ? !this.moveItemStackTo(itemstack1, 31, this.slots.size(), true) : !this.moveItemStackTo(itemstack1, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        // Machine keeps its contents when the GUI is closed;
        // items are only lost if the block itself is broken.
    }
}
