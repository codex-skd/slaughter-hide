package com.skd.slaughterhide.block.entity;

import com.skd.slaughterhide.block.MeatGrinderBlock;
import com.skd.slaughterhide.init.ModBlockEntities;
import com.skd.slaughterhide.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
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

import com.skd.slaughterhide.menu.MeatGrinderMenu;

public class MeatGrinderBlockEntity extends BlockEntity implements MenuProvider, Container {

    private NonNullList<ItemStack> stacks = NonNullList.withSize(6, ItemStack.EMPTY);
    private double craftingProgress = 0;
    private double craftingTime = 0;

    public MeatGrinderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MEAT_GRINDER.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.slaughter_hide.meat_grinder");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new MeatGrinderMenu(id, playerInv, this, this.getBlockPos());
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
        return 6;
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

        // Slot 0 empty -> reset
        if (input.isEmpty()) {
            if (this.craftingProgress != 0 || this.craftingTime != 0) {
                this.craftingProgress = 0;
                this.craftingTime = 0;
                this.updateBlockState(level, pos);
            }
            return;
        }

        // Check recipes and determine craftingTime target
        double targetTime = 0;
        boolean canCraft = false;

        if (isCarcass(input) || isDrainedCarcass(input)) {
            if (canAddMeatScraps()) {
                targetTime = 150;
                canCraft = true;
            }
        } else if (isRawPork(input) && hasSausageAttachment() && hasIntestines() && isSlot3Empty() && canAddRawSausage()) {
            targetTime = 150;
            canCraft = true;
        } else if (isRawPork(input) && hasSausageAttachment() && hasIntestines() && hasBloodBottle() && canAddRawBloodSausage()) {
            targetTime = 150;
            canCraft = true;
        } else if (isRawMutton(input) && canAddRawLambMince()) {
            targetTime = 150;
            canCraft = true;
        } else if (isRawBeef(input) && canAddRawBeefMince()) {
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

        if (isCarcass(input) || isDrainedCarcass(input)) {
            input.shrink(1);
            int count = this.stacks.get(4).getCount() + Mth.nextInt(RandomSource.create(), 3, 8);
            this.stacks.set(4, new ItemStack(ModItems.MEAT_SCRAPS.get(), count));
        } else if (isRawPork(input) && hasSausageAttachment() && hasIntestines() && isSlot3Empty()) {
            input.shrink(1);
            this.stacks.get(1).shrink(1);
            this.stacks.set(4, new ItemStack(ModItems.RAW_SAUSAGE.get(), this.stacks.get(4).getCount() + 1));
        } else if (isRawPork(input) && hasSausageAttachment() && hasIntestines() && hasBloodBottle()) {
            input.shrink(1);
            this.stacks.get(1).shrink(1);
            this.stacks.get(3).shrink(1);
            this.stacks.get(5).shrink(1);
            this.stacks.set(5, new ItemStack(net.minecraft.world.item.Items.GLASS_BOTTLE, this.stacks.get(5).getCount() + 1));
            this.stacks.set(4, new ItemStack(ModItems.RAW_BLOOD_SAUSAGE.get(), this.stacks.get(4).getCount() + 1));
        } else if (isRawMutton(input)) {
            input.shrink(1);
            this.stacks.set(4, new ItemStack(ModItems.RAW_LAMB_MINCE.get(), this.stacks.get(4).getCount() + 1));
        } else if (isRawBeef(input)) {
            input.shrink(1);
            this.stacks.set(4, new ItemStack(ModItems.RAW_BEEF_MINCE.get(), this.stacks.get(4).getCount() + 1));
        }

        this.craftingProgress = 0;
        this.craftingTime = 0;
        this.setChanged();
    }

    private void updateBlockState(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof MeatGrinderBlock)) {
            return;
        }

        int newState = determineVisualState();
        if (state.getValue(MeatGrinderBlock.BLOCKSTATE) != newState) {
            level.setBlock(pos, state.setValue(MeatGrinderBlock.BLOCKSTATE, newState), 3);
        }
    }

    private int determineVisualState() {
        boolean hasMeat = !this.stacks.get(0).isEmpty();
        boolean hasAttachment = this.stacks.get(2).getItem() == ModItems.SAUSAGE_ATTACHMENT.get();
        boolean hasIntestines = this.stacks.get(1).getItem() == ModItems.INTESTINES.get();

        if (hasAttachment && hasIntestines && hasMeat) return 5;
        if (hasAttachment && hasIntestines) return 4;
        if (hasAttachment && hasMeat) return 3;
        if (hasAttachment) return 2;
        if (hasMeat) return 1;
        return 0;
    }

    private boolean isCarcass(ItemStack stack) {
        if (stack.isEmpty() || !(stack.getItem() instanceof net.minecraft.world.item.BlockItem bi)) {
            return false;
        }
        return com.skd.slaughterhide.init.ModBlocks.freshFor(
                getMobIdFromBlock(bi.getBlock())) != null;
    }

    private boolean isDrainedCarcass(ItemStack stack) {
        if (stack.isEmpty() || !(stack.getItem() instanceof net.minecraft.world.item.BlockItem bi)) {
            return false;
        }
        String blockId = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(bi.getBlock()).getPath();
        return blockId.startsWith("drained_") && blockId.endsWith("_carcass");
    }

    private String getMobIdFromBlock(net.minecraft.world.level.block.Block block) {
        String id = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(block).getPath();
        if (id.endsWith("_carcass")) {
            return id.substring(0, id.length() - "_carcass".length());
        }
        return "";
    }

    private boolean isRawPork(ItemStack stack) {
        return stack.is(ModItems.RAW_PORK_SHOULDER.get()) || stack.is(ModItems.RAW_PORK_LOIN.get())
                || stack.is(ModItems.RAW_PORK_LEG.get()) || stack.is(ModItems.RAW_PORK_BELLY.get())
                || stack.is(ModItems.RAW_HAM.get());
    }

    private boolean isRawMutton(ItemStack stack) {
        return stack.is(ModItems.RAW_LEG_OF_LAMB.get()) || stack.is(ModItems.RAW_LAMB_SHOULDER.get())
                || stack.is(ModItems.RAW_LAMB_RIB.get()) || stack.is(ModItems.RAW_LAMB_SIRLOIN.get())
                || stack.is(ModItems.RAW_LAMB_LOIN.get());
    }

    private boolean isRawBeef(ItemStack stack) {
        return stack.is(ModItems.RAW_CHUCK_STEAK.get()) || stack.is(ModItems.RAW_RIBEYE_STEAK.get())
                || stack.is(ModItems.RAW_RUMP_STEAK.get()) || stack.is(ModItems.RAW_SIRLOIN_STEAK.get())
                || stack.is(ModItems.RAW_TBONE_STEAK.get());
    }

    private boolean hasSausageAttachment() {
        return this.stacks.get(2).getItem() == ModItems.SAUSAGE_ATTACHMENT.get();
    }

    private boolean hasIntestines() {
        return this.stacks.get(1).getItem() == ModItems.INTESTINES.get();
    }

    private boolean isSlot3Empty() {
        return this.stacks.get(3).isEmpty();
    }

    private boolean hasBloodBottle() {
        return this.stacks.get(3).getItem() == ModItems.BOTTLE_OF_BLOOD.get()
                || this.stacks.get(5).getItem() == ModItems.BOTTLE_OF_BLOOD.get();
    }

    private boolean canAddMeatScraps() {
        ItemStack out = this.stacks.get(4);
        return out.isEmpty() || (out.getItem() == ModItems.MEAT_SCRAPS.get() && out.getCount() <= 63);
    }

    private boolean canAddRawSausage() {
        ItemStack out = this.stacks.get(4);
        return out.isEmpty() || (out.getItem() == ModItems.RAW_SAUSAGE.get() && out.getCount() <= 63);
    }

    private boolean canAddRawBloodSausage() {
        ItemStack out = this.stacks.get(4);
        return out.isEmpty() || (out.getItem() == ModItems.RAW_BLOOD_SAUSAGE.get() && out.getCount() <= 63);
    }

    private boolean canAddRawLambMince() {
        ItemStack out = this.stacks.get(4);
        return out.isEmpty() || (out.getItem() == ModItems.RAW_LAMB_MINCE.get() && out.getCount() <= 63);
    }

    private boolean canAddRawBeefMince() {
        ItemStack out = this.stacks.get(4);
        return out.isEmpty() || (out.getItem() == ModItems.RAW_BEEF_MINCE.get() && out.getCount() <= 63);
    }

    public double getCraftingProgress() {
        return this.craftingProgress;
    }

    public double getCraftingTime() {
        return this.craftingTime;
    }
}
