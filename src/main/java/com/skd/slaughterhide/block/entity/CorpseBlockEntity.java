package com.skd.slaughterhide.block.entity;

import com.skd.slaughterhide.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.MenuProvider;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;

import javax.annotation.Nullable;

/**
 * Block entity for corpse blocks that can store organs/items during butchering.
 * Unlike the standard carcass block entity, this is a full container (9 slots)
 * for storing organs during the butchering process.
 */
public class CorpseBlockEntity extends BlockEntity implements Container, WorldlyContainer, MenuProvider {

    private NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);

    public CorpseBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CORPSE.get(), pos, state);
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return items.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack result = ContainerHelper.removeItem(items, index, count);
        if (!result.isEmpty()) {
            setChanged();
        }
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack result = items.get(index);
        if (!result.isEmpty()) {
            items.set(index, ItemStack.EMPTY);
        }
        return result;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        items.set(index, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        if (level.getBlockEntity(worldPosition) != this) {
            return false;
        }
        return player.distanceToSqr((double) worldPosition.getX() + 0.5, (double) worldPosition.getY() + 0.5, (double) worldPosition.getZ() + 0.5) <= 64.0;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ChestMenu(MenuType.GENERIC_9x3, containerId, playerInventory, this, 3);
    }

    @Nullable
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return createMenu(containerId, playerInventory, null);
    }

    public net.minecraft.network.chat.Component getDisplayName() {
        return net.minecraft.network.chat.Component.literal("corpse");
    }

    public net.minecraft.network.chat.Component getDefaultName() {
        return net.minecraft.network.chat.Component.literal("corpse");
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(input, items);
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
    }
}