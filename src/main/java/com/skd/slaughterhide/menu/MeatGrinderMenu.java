package com.skd.slaughterhide.menu;

import com.skd.slaughterhide.init.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class MeatGrinderMenu extends AbstractContainerMenu {
    private final BlockPos pos;
    private final BlockEntity blockEntity;

    public MeatGrinderMenu(int id, Inventory playerInv, net.minecraft.world.Container container, BlockPos pos) {
        super(ModMenus.MEAT_GRINDER.get(), id);
        this.pos = pos;
        this.blockEntity = container instanceof BlockEntity be ? be : null;

        // Custom slots matching original GUI positions
        // Slot 0: Input (meat/carcass) at (80, 20)
        this.addSlot(new Slot(container, 0, 80, 20));
        // Slot 1: Intestines at (114, 20)
        this.addSlot(new Slot(container, 1, 114, 20));
        // Slot 2: Sausage attachment at (8, 65)
        this.addSlot(new Slot(container, 2, 8, 65));
        // Slot 3: Blood bottle at (134, 20)
        this.addSlot(new Slot(container, 3, 134, 20));
        // Slot 4: Output (meat scraps/sausage) at (114, 48)
        this.addSlot(new Slot(container, 4, 114, 48) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        // Slot 5: Output (glass bottle/blood sausage) at (134, 48)
        this.addSlot(new Slot(container, 5, 134, 48) {
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

    public MeatGrinderMenu(int id, Inventory playerInv, net.minecraft.network.FriendlyByteBuf buf) {
        this(id, playerInv, new net.minecraft.world.SimpleContainer(6), buf.readBlockPos());
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
            if (index < 6) {
                if (!this.moveItemStackTo(itemstack1, 6, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (!this.moveItemStackTo(itemstack1, 0, 6, false)) {
                if (index < 33 ? !this.moveItemStackTo(itemstack1, 33, this.slots.size(), true) : !this.moveItemStackTo(itemstack1, 6, 33, false)) {
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
        // Machine keeps its contents when the GUI is closed (matches the Freezer);
        // items are only lost if the block itself is broken.
    }
}
