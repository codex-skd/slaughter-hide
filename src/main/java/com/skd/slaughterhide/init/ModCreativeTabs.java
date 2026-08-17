package com.skd.slaughterhide.init;

import com.skd.slaughterhide.SlaughterHide;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Creative tabs mirroring the original three groups: blocks, items, food.
 * Advancements/item dust are not part of this pass.
 */
public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SlaughterHide.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS = REGISTRY.register(
            "slaughter_hide_blocks",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.slaughter_hide.slaughter_hide_blocks"))
                    .icon(() -> new ItemStack(ModItems.COW_CARCASS.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.COW_CARCASS.get());
                        output.accept(ModItems.DRAINED_COW_CARCASS.get());
                        output.accept(ModItems.COW_HEAD.get());
                        output.accept(ModItems.COW_HEAD_MOUNT.get());
                        output.accept(ModItems.COW_SKELETON.get());
                        output.accept(ModItems.HOOK.get());
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS = REGISTRY.register(
            "slaughter_hide_items",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.slaughter_hide.slaughter_hide_items"))
                    .icon(() -> new ItemStack(ModItems.IRON_CLEAVER.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.IRON_CLEAVER.get());
                        output.accept(ModItems.IRON_SKINNING_KNIFE.get());
                        output.accept(ModItems.COW_SKIN.get());
                        output.accept(ModItems.ANIMAL_FAT.get());
                        output.accept(ModItems.HOOF.get());
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FOOD = REGISTRY.register(
            "slaughter_hide_food",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.slaughter_hide.slaughter_hide_food"))
                    .icon(() -> new ItemStack(ModItems.RAW_TBONE_STEAK.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.RAW_CHUCK_STEAK.get());
                        output.accept(ModItems.RAW_RIBEYE_STEAK.get());
                        output.accept(ModItems.RAW_RUMP_STEAK.get());
                        output.accept(ModItems.RAW_SIRLOIN_STEAK.get());
                        output.accept(ModItems.RAW_TBONE_STEAK.get());
                    })
                    .build());

    private ModCreativeTabs() {
    }
}