package com.skd.slaughterhide.block.entity;

import com.skd.slaughterhide.block.TaxidermyTableBlock;
import com.skd.slaughterhide.init.ModBlockEntities;
import com.skd.slaughterhide.init.ModBlocks;
import com.skd.slaughterhide.init.ModItems;
import com.skd.slaughterhide.menu.TaxidermyTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class TaxidermyTableBlockEntity extends BlockEntity implements MenuProvider, Container {

    private NonNullList<ItemStack> stacks = NonNullList.withSize(4, ItemStack.EMPTY);
    private double craftingProgress = 0;
    private double craftingTime = 0;

    public TaxidermyTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TAXIDERMY_TABLE.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.slaughter_hide.taxidermy_table");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new TaxidermyTableMenu(id, playerInv, this, this.getBlockPos());
    }

    @Override
    public void loadAdditional(ValueInput valueInput) {
        super.loadAdditional(valueInput);
        ContainerHelper.loadAllItems(valueInput, this.stacks);
        this.craftingProgress = valueInput.getDoubleOr("craftingProgress", 0.0);
        this.craftingTime = valueInput.getDoubleOr("craftingTime", 0.0);
    }

    @Override
    public void saveAdditional(ValueOutput valueOutput) {
        super.saveAdditional(valueOutput);
        ContainerHelper.saveAllItems(valueOutput, this.stacks);
        valueOutput.putDouble("craftingProgress", this.craftingProgress);
        valueOutput.putDouble("craftingTime", this.craftingTime);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        return this.saveWithFullMetadata(lookupProvider);
    }

    @Override
    public int getContainerSize() {
        return 4;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.stacks) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.stacks.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack result = this.stacks.get(index).split(count);
        if (!result.isEmpty()) {
            this.setChanged();
        }
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return this.stacks.set(index, ItemStack.EMPTY);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        this.stacks.set(index, stack);
        this.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        this.stacks.clear();
        this.setChanged();
    }

    public void serverTick() {
        if (this.level == null || this.level.isClientSide()) {
            return;
        }

        Level level = this.level;
        BlockPos pos = this.getBlockPos();

        // Check if any input slot is non-empty
        boolean hasInput = !this.stacks.get(0).isEmpty() || !this.stacks.get(1).isEmpty() || !this.stacks.get(2).isEmpty();

        if (!hasInput) {
            if (this.craftingProgress != 0 || this.craftingTime != 0) {
                this.craftingProgress = 0;
                this.craftingTime = 0;
                this.updateBlockState(level, pos);
            }
            return;
        }

        // Check recipes and determine craftingTime target
        ItemStack result = matchRecipe();
        double targetTime = 0;
        boolean canCraft = false;

        if (!result.isEmpty() && canAcceptResult(result)) {
            targetTime = 150;
            canCraft = true;
        }

        if (canCraft) {
            this.craftingTime = targetTime;
            this.craftingProgress++;
            if (this.craftingProgress >= this.craftingTime) {
                this.doCraft(result);
            }
        } else {
            if (this.craftingProgress != 0 || this.craftingTime != 0) {
                this.craftingProgress = 0;
                this.craftingTime = 0;
            }
        }

        this.updateBlockState(level, pos);
    }

    private ItemStack matchRecipe() {
        ItemStack slot0 = this.stacks.get(0);
        ItemStack slot1 = this.stacks.get(1);
        ItemStack slot2 = this.stacks.get(2);

        // cow_skin + cow_head + planks -> cow_head_mount
        if (slot0.is(ModItems.COW_SKIN.get()) && slot1.is(ModItems.COW_HEAD.get()) && isPlanks(slot2)) {
            return new ItemStack(ModItems.COW_HEAD_MOUNT.get());
        }
        // pig_skin + pig_head + planks -> pig_head_mount
        if (slot0.is(ModItems.PIG_SKIN.get()) && slot1.is(ModItems.PIG_HEAD.get()) && isPlanks(slot2)) {
            return new ItemStack(ModItems.PIG_HEAD_MOUNT.get());
        }
        // sheep_skin + sheep_head + planks -> sheep_head_mount
        if (slot0.is(ModItems.SHEEP_SKIN.get()) && slot1.is(ModItems.SHEEP_HEAD.get()) && isPlanks(slot2)) {
            return new ItemStack(ModItems.SHEEP_HEAD_MOUNT.get());
        }
        // goat_skin + goat_head + planks -> goat_head_mount
        if (slot0.is(ModItems.GOAT_SKIN.get()) && slot1.is(ModItems.GOAT_HEAD.get()) && isPlanks(slot2)) {
            return new ItemStack(ModItems.GOAT_HEAD_MOUNT.get());
        }
        // polar_bear_skin + polar_bear_head + planks -> polar_bear_head_mount
        if (slot0.is(ModItems.POLAR_BEAR_SKIN.get()) && slot1.is(ModItems.POLAR_BEAR_HEAD.get()) && isPlanks(slot2)) {
            return new ItemStack(ModItems.POLAR_BEAR_HEAD_MOUNT.get());
        }
        // chicken_head + planks + planks -> chicken_head_mount
        if (slot0.is(ModItems.CHICKEN_HEAD.get()) && isPlanks(slot1) && isPlanks(slot2)) {
            return new ItemStack(ModItems.CHICKEN_HEAD_MOUNT.get());
        }
        // rabbit_head + planks + planks -> rabbit_head_mount
        if (slot0.is(ModItems.RABBIT_HEAD.get()) && isPlanks(slot1) && isPlanks(slot2)) {
            return new ItemStack(ModItems.RABBIT_HEAD_MOUNT.get());
        }

        return ItemStack.EMPTY;
    }

    private boolean isPlanks(ItemStack stack) {
        return !stack.isEmpty() && stack.is(net.minecraft.tags.ItemTags.PLANKS);
    }

    private boolean canAcceptResult(ItemStack result) {
        ItemStack output = this.stacks.get(3);
        return output.isEmpty() || (output.is(result.getItem()) && output.getCount() + result.getCount() <= output.getMaxStackSize());
    }

    private void doCraft(ItemStack result) {
        // Consume inputs
        this.stacks.get(0).shrink(1);
        this.stacks.get(1).shrink(1);
        this.stacks.get(2).shrink(1);

        // Add output
        ItemStack output = this.stacks.get(3);
        if (output.isEmpty()) {
            this.stacks.set(3, result.copy());
        } else {
            output.grow(result.getCount());
        }

        this.craftingProgress = 0;
        this.craftingTime = 0;
        this.setChanged();
    }

    private void updateBlockState(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof TaxidermyTableBlock)) {
            return;
        }

        int newState = determineVisualState();
        if (state.getValue(TaxidermyTableBlock.BLOCKSTATE) != newState) {
            level.setBlock(pos, state.setValue(TaxidermyTableBlock.BLOCKSTATE, newState), 3);
        }
    }

    private int determineVisualState() {
        boolean hasInput = !this.stacks.get(0).isEmpty() || !this.stacks.get(1).isEmpty() || !this.stacks.get(2).isEmpty();
        return hasInput ? 1 : 0;
    }

    public double getCraftingProgress() {
        return this.craftingProgress;
    }

    public double getCraftingTime() {
        return this.craftingTime;
    }
}
