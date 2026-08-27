package com.skd.slaughterhide.block.entity;

import com.skd.slaughterhide.block.PestleAndMortarBlock;
import com.skd.slaughterhide.init.ModBlockEntities;
import com.skd.slaughterhide.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.skd.slaughterhide.menu.PestleAndMortarMenu;

public class PestleAndMortarBlockEntity extends BlockEntity implements MenuProvider, Container {

    private NonNullList<ItemStack> stacks = NonNullList.withSize(2, ItemStack.EMPTY);
    private double craftingProgress = 0;
    private double craftingTime = 0;

    public PestleAndMortarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PESTLE_AND_MORTAR.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.slaughter_hide.pestle_and_mortar");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new PestleAndMortarMenu(id, playerInv, this, this.getBlockPos());
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
        return 2;
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
        ItemStack input = this.stacks.get(0);

        if (input.isEmpty()) {
            if (this.craftingProgress != 0 || this.craftingTime != 0) {
                this.craftingProgress = 0;
                this.craftingTime = 0;
                this.updateBlockState(level, pos);
            }
            return;
        }

        double targetTime = 0;
        boolean canCraft = false;

        // Butchery's original also crushed wither/icey/sculk bones into their meal
        // variants, but those bones have no obtain path in this mod's 7-mob scope,
        // so only the two reachable recipes are ported.
        if (input.is(ModItems.HOOF.get()) && canAcceptOutput(Items.SLIME_BALL)) {
            targetTime = 150;
            canCraft = true;
        } else if (input.is(ItemTags.create(net.minecraft.resources.Identifier.parse("slaughter_hide:crushable_bone"))) && canAcceptOutput(Items.BONE_MEAL)) {
            targetTime = 150;
            canCraft = true;
        }

        if (canCraft) {
            this.craftingTime = targetTime;
            this.craftingProgress++;
            if (this.craftingProgress >= this.craftingTime) {
                this.doCraft(level, pos);
            }
        }

        this.updateBlockState(level, pos);
    }

    private void doCraft(Level level, BlockPos pos) {
        ItemStack input = this.stacks.get(0);
        ItemStack output = this.stacks.get(1);

        if (input.is(ModItems.HOOF.get())) {
            input.shrink(1);
            int count = output.getCount() + 1;
            this.stacks.set(1, new ItemStack(Items.SLIME_BALL, count));
        } else if (input.is(ItemTags.create(net.minecraft.resources.Identifier.parse("slaughter_hide:crushable_bone")))) {
            input.shrink(1);
            int count = output.getCount() + 3;
            this.stacks.set(1, new ItemStack(Items.BONE_MEAL, count));
        }

        this.craftingProgress = 0;
        this.craftingTime = 0;
        this.setChanged();
    }

    private boolean canAcceptOutput(net.minecraft.world.item.Item outputItem) {
        ItemStack out = this.stacks.get(1);
        return out.isEmpty() || (out.getItem() == outputItem && out.getCount() <= 63);
    }

    private void updateBlockState(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof PestleAndMortarBlock)) {
            return;
        }
        this.setChanged();
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendBlockUpdated(pos, state, state, 3);
        }
    }

    public double getCraftingProgress() {
        return this.craftingProgress;
    }

    public double getCraftingTime() {
        return this.craftingTime;
    }
}
