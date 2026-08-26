package com.skd.slaughterhide.init;

import com.skd.slaughterhide.SlaughterHide;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.stream.Stream;

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
                        // Fresh carcasses
                        ModItems.freshItems().values().forEach(item -> output.accept(item.get()));
                        // Drained carcasses (only those with assets)
                        ModItems.drainedItems().values().forEach(item -> output.accept(item.get()));
                        // Heads
                        ModItems.headItems().values().forEach(item -> output.accept(item.get()));
                        // Head mounts
                        ModItems.mountItems().values().forEach(item -> output.accept(item.get()));
                        // Skeletons
                        ModItems.skeletonItems().values().forEach(item -> output.accept(item.get()));
                        // Hook and rope
                        output.accept(ModItems.HOOK.get());
                        output.accept(ModItems.ROPE.get());
                        // Freezer
                        output.accept(ModItems.FREEZER.get());
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS = REGISTRY.register(
            "slaughter_hide_items",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.slaughter_hide.slaughter_hide_items"))
                    .icon(() -> new ItemStack(ModItems.IRON_CLEAVER.get()))
                    .displayItems((parameters, output) -> {
                        // Tools (all tiers)
                        Stream.of(
                                ModItems.COPPER_CLEAVER, ModItems.COPPER_SKINNING_KNIFE, ModItems.COPPER_HACKSAW, ModItems.COPPER_HAMMER,
                                ModItems.IRON_CLEAVER, ModItems.IRON_SKINNING_KNIFE, ModItems.IRON_HACKSAW, ModItems.IRON_HAMMER,
                                ModItems.GOLD_CLEAVER, ModItems.GOLD_SKINNING_KNIFE, ModItems.GOLD_HACKSAW, ModItems.GOLD_HAMMER,
                                ModItems.DIAMOND_CLEAVER, ModItems.DIAMOND_SKINNING_KNIFE, ModItems.DIAMOND_HACKSAW, ModItems.DIAMOND_HAMMER,
                                ModItems.NETHERITE_CLEAVER, ModItems.NETHERITE_SKINNING_KNIFE, ModItems.NETHERITE_HACKSAW, ModItems.NETHERITE_HAMMER,
                                ModItems.BONE_CLEAVER, ModItems.BONE_SKINNING_KNIFE, ModItems.BONE_HACKSAW, ModItems.BONE_HAMMER
                        ).forEach(item -> output.accept(item.get()));

                        // Skins (only those that exist)
                        Stream.of(
                                ModItems.COW_SKIN, ModItems.PIG_SKIN, ModItems.SHEEP_SKIN,
                                ModItems.GOAT_SKIN, ModItems.FOX_SKIN,
                                ModItems.CAMEL_SKIN, ModItems.DONKEY_SKIN, ModItems.MULE_SKIN,
                                ModItems.OCELOT_SKIN, ModItems.PANDA_SKIN, ModItems.POLAR_BEAR_SKIN,
                                ModItems.HOGLIN_SKIN, ModItems.ZOGLIN_SKIN, ModItems.DOLPHIN_SKIN,
                                ModItems.BAT_SKIN,
                                ModItems.BLUE_AXOLOTL_SKIN, ModItems.PINK_AXOLOTL_SKIN,
                                ModItems.BROWN_AXOLOTL_SKIN, ModItems.CYAN_AXOLOTL_SKIN,
                                ModItems.GOLD_AXOLOTL_SKIN, ModItems.CREEPER_SKIN,
                                ModItems.ALL_BLACK_CAT_SKIN, ModItems.BLACK_CAT_SKIN,
                                ModItems.BSHORTHAIR_CAT_SKIN, ModItems.CALICO_CAT_SKIN,
                                ModItems.JELLIE_CAT_SKIN, ModItems.PERSIAN_CAT_SKIN,
                                ModItems.RAGDOLL_CAT_SKIN, ModItems.RED_CAT_SKIN,
                                ModItems.SIAMESE_CAT_SKIN, ModItems.TABBY_CAT_SKIN,
                                ModItems.WHITE_CAT_SKIN
                        ).forEach(item -> output.accept(item.get()));

                        // Other items
                        output.accept(ModItems.ANIMAL_FAT.get());
                        output.accept(ModItems.HOOF.get());
                        output.accept(ModItems.RAW_FOX_MEAT.get());
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FOOD = REGISTRY.register(
            "slaughter_hide_food",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.slaughter_hide.slaughter_hide_food"))
                    .icon(() -> new ItemStack(ModItems.RAW_TBONE_STEAK.get()))
                    .displayItems((parameters, output) -> {
                        // All raw meats that exist
                        Stream.of(
                                ModItems.RAW_CHUCK_STEAK, ModItems.RAW_RIBEYE_STEAK, ModItems.RAW_RUMP_STEAK,
                                ModItems.RAW_SIRLOIN_STEAK, ModItems.RAW_TBONE_STEAK,
                                ModItems.RAW_PORK_SHOULDER, ModItems.RAW_PORK_LOIN, ModItems.RAW_PORK_LEG,
                                ModItems.RAW_PORK_BELLY, ModItems.RAW_HAM,
                                ModItems.RAW_LEG_OF_LAMB, ModItems.RAW_LAMB_SHOULDER, ModItems.RAW_LAMB_RIB,
                                ModItems.RAW_LAMB_SIRLOIN, ModItems.RAW_LAMB_LOIN,
                                ModItems.RAW_CHICKEN_LEG, ModItems.RAW_CHICKEN_WING,
                                ModItems.RAW_LEG_OF_LAMB_GOAT, ModItems.RAW_LAMB_SHOULDER_GOAT,
                                ModItems.RAW_LAMB_RIB_GOAT, ModItems.RAW_LAMB_SIRLOIN_GOAT,
                                ModItems.RAW_LAMB_LOIN_GOAT,
                                ModItems.RAW_FOX_MEAT, ModItems.RAW_WOLF_MEAT, ModItems.RAW_CAMEL_MEAT,
                                ModItems.RAW_DONKEY_STEAK, ModItems.RAW_MULE_STEAK,
                                ModItems.RAW_OCELOT_MEAT, ModItems.RAW_PANDA_STEAK,
                                ModItems.RAW_POLAR_BEAR_MEAT, ModItems.RAW_HOGLIN_CHUNK,
                                ModItems.RAW_DOLPHIN_MEAT, ModItems.RAW_BAT_MEAT,
                                ModItems.RAW_SILVERFISH_CHUNKS, ModItems.RAW_ENDERMITE_CHUNKS,
                                ModItems.RAW_ENDERMAN_STEAK, ModItems.RAW_STRIDER_MEAT,
                                ModItems.RAW_SNIFFER_STEAK, ModItems.RAW_TURTLE_MEAT,
                                ModItems.RAW_PUFFERFISH
                        ).filter(item -> item != null).forEach(item -> output.accept(item.get()));

                        // Cooked meats (if they exist)
                        Stream.of(
                                ModItems.COOKED_CHUCK_STEAK, ModItems.COOKED_RIBEYE_STEAK, ModItems.COOKED_RUMP_STEAK,
                                ModItems.COOKED_SIRLOIN_STEAK, ModItems.COOKED_TBONE_STEAK,
                                ModItems.COOKED_PORK_SHOULDER, ModItems.COOKED_PORK_LOIN, ModItems.COOKED_PORK_LEG,
                                ModItems.COOKED_PORK_BELLY, ModItems.COOKED_HAM,
                                ModItems.COOKED_LEG_OF_LAMB, ModItems.COOKED_LAMB_SHOULDER, ModItems.COOKED_LAMB_RIB,
                                ModItems.COOKED_LAMB_SIRLOIN, ModItems.COOKED_LAMB_LOIN
                        ).filter(item -> item != null).forEach(item -> output.accept(item.get()));
                    })
                    .build());

    private ModCreativeTabs() {
    }
}