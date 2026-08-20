package com.skd.slaughterhide.init;

import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.Carcasses;
import com.skd.slaughterhide.SlaughterHide;
import com.skd.slaughterhide.item.ButcherToolItem;
import com.skd.slaughterhide.item.CarcassPlacementItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Item registrations, limited to the Iron tier of tools plus the raw meat
 * items the cow loot tables reference. Block items are registered under their
 * block names so a placed carcass breaks back into the same item.
 */
public final class ModItems {
    public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(SlaughterHide.MOD_ID);

    public static final DeferredItem<Item> IRON_CLEAVER = item("iron_cleaver", ButcherToolItem::new);
    public static final DeferredItem<Item> IRON_SKINNING_KNIFE = item("iron_skinning_knife", ButcherToolItem::new);

    public static final DeferredItem<Item> COW_SKIN = item("cow_skin", Item::new);
    public static final DeferredItem<Item> PIG_SKIN = item("pig_skin", Item::new);
    public static final DeferredItem<Item> SHEEP_SKIN = item("sheep_skin", Item::new);
    public static final DeferredItem<Item> ANIMAL_FAT = item("animal_fat", Item::new);
    public static final DeferredItem<Item> HOOF = item("hoof", Item::new);
    public static final DeferredItem<Item> RAW_CHUCK_STEAK = item("raw_chuck_steak", Item::new);
    public static final DeferredItem<Item> RAW_RIBEYE_STEAK = item("raw_ribeye_steak", Item::new);
    public static final DeferredItem<Item> RAW_RUMP_STEAK = item("raw_rump_steak", Item::new);
    public static final DeferredItem<Item> RAW_SIRLOIN_STEAK = item("raw_sirloin_steak", Item::new);
    public static final DeferredItem<Item> RAW_TBONE_STEAK = item("raw_tbone_steak", Item::new);
    public static final DeferredItem<Item> RAW_PORK_SHOULDER = item("raw_pork_shoulder", Item::new);
    public static final DeferredItem<Item> RAW_PORK_LOIN = item("raw_pork_loin", Item::new);
    public static final DeferredItem<Item> RAW_PORK_LEG = item("raw_pork_leg", Item::new);
    public static final DeferredItem<Item> RAW_PORK_BELLY = item("raw_pork_belly", Item::new);
    public static final DeferredItem<Item> RAW_HAM = item("raw_ham", Item::new);
    public static final DeferredItem<Item> RAW_LEG_OF_LAMB = item("raw_leg_of_lamb", Item::new);
    public static final DeferredItem<Item> RAW_LAMB_SHOULDER = item("raw_lamb_shoulder", Item::new);
    public static final DeferredItem<Item> RAW_LAMB_RIB = item("raw_lamb_rib", Item::new);
    public static final DeferredItem<Item> RAW_LAMB_SIRLOIN = item("raw_lamb_sirloin", Item::new);
    public static final DeferredItem<Item> RAW_LAMB_LOIN = item("raw_lamb_loin", Item::new);
    public static final DeferredItem<Item> RAW_CHICKEN_LEG = item("raw_chicken_leg", Item::new);
    public static final DeferredItem<Item> RAW_CHICKEN_WING = item("raw_chicken_wing", Item::new);
    public static final DeferredItem<Item> BIRD_FOOT = item("bird_foot", Item::new);
    public static final DeferredItem<Item> WISHBONE = item("wishbone", Item::new);
    // Goat (uses lamb cuts + hooves)
    public static final DeferredItem<Item> GOAT_SKIN = item("goat_skin", Item::new);
    public static final DeferredItem<Item> RAW_LEG_OF_LAMB_GOAT = item("raw_leg_of_lamb", Item::new);
    public static final DeferredItem<Item> RAW_LAMB_SHOULDER_GOAT = item("raw_lamb_shoulder", Item::new);
    public static final DeferredItem<Item> RAW_LAMB_RIB_GOAT = item("raw_lamb_rib", Item::new);
    public static final DeferredItem<Item> RAW_LAMB_SIRLOIN_GOAT = item("raw_lamb_sirloin", Item::new);
    public static final DeferredItem<Item> RAW_LAMB_LOIN_GOAT = item("raw_lamb_loin", Item::new);
    public static final DeferredItem<Item> HOOF_GOAT = item("hoof", Item::new);
    // Fox
    public static final DeferredItem<Item> FOX_SKIN = item("fox_skin", Item::new);
    public static final DeferredItem<Item> RAW_FOX_MEAT = item("raw_fox_meat", Item::new);
    // Wolf
    public static final DeferredItem<Item> WOLF_PELT = item("wolf_pelt", Item::new);
    public static final DeferredItem<Item> RAW_WOLF_MEAT = item("raw_wolf_meat", Item::new);
    // Camel
    public static final DeferredItem<Item> CAMEL_SKIN = item("camel_skin", Item::new);
    public static final DeferredItem<Item> RAW_CAMEL_MEAT = item("raw_camel_meat", Item::new);
    // Donkey
    public static final DeferredItem<Item> DONKEY_SKIN = item("donkey_skin", Item::new);
    public static final DeferredItem<Item> RAW_DONKEY_STEAK = item("raw_donkey_steak", Item::new);
    // Mule
    public static final DeferredItem<Item> MULE_SKIN = item("mule_skin", Item::new);
    public static final DeferredItem<Item> RAW_MULE_STEAK = item("raw_mule_steak", Item::new);
    // Ocelot
    public static final DeferredItem<Item> OCELOT_SKIN = item("ocelot_skin", Item::new);
    public static final DeferredItem<Item> RAW_OCELOT_MEAT = item("raw_ocelot_meat", Item::new);
    // Panda
    public static final DeferredItem<Item> PANDA_SKIN = item("panda_skin", Item::new);
    public static final DeferredItem<Item> RAW_PANDA_STEAK = item("raw_panda_steak", Item::new);
    // Polar Bear
    public static final DeferredItem<Item> POLAR_BEAR_SKIN = item("polar_bear_skin", Item::new);
    public static final DeferredItem<Item> RAW_POLAR_BEAR_MEAT = item("raw_polar_bear_meat", Item::new);
    // Hoglin
    public static final DeferredItem<Item> HOGLIN_SKIN = item("hoglin_skin", Item::new);
    public static final DeferredItem<Item> RAW_HOGLIN_CHUNK = item("raw_hoglin_chunk", Item::new);
    // Zoglin
    public static final DeferredItem<Item> ZOGLIN_SKIN = item("zoglin_skin", Item::new);
    // Dolphin
    public static final DeferredItem<Item> DOLPHIN_SKIN = item("dolphin_skin", Item::new);
    public static final DeferredItem<Item> RAW_DOLPHIN_MEAT = item("raw_dolphin_meat", Item::new);
    // Bat
    public static final DeferredItem<Item> BAT_SKIN = item("bat_skin", Item::new);
    public static final DeferredItem<Item> BAT_WING = item("bat_wing", Item::new);
    public static final DeferredItem<Item> RAW_BAT_MEAT = item("raw_bat_meat", Item::new);
    // Silverfish
    public static final DeferredItem<Item> RAW_SILVERFISH_CHUNKS = item("raw_silverfish_chunks", Item::new);
    // Endermite
    public static final DeferredItem<Item> RAW_ENDERMITE_CHUNKS = item("raw_endermite_chunks", Item::new);

    // Fresh/drained carcass items only hang from a Hook (HookPlacementHandler),
    // they don't place a block on right-click like a normal BlockItem.
    public static final DeferredItem<Item> COW_CARCASS =
            placementItem("cow_carcass", Carcasses.COW, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_COW_CARCASS =
            placementItem("drained_cow_carcass", Carcasses.COW, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> COW_HEAD = blockItem("cow_head", new Item.Properties());
    public static final DeferredItem<Item> COW_HEAD_MOUNT = blockItem("cow_head_mount", new Item.Properties());
    public static final DeferredItem<Item> COW_SKELETON = blockItem("cow_skeleton", new Item.Properties().stacksTo(8));

    // Fresh/drained carcass items only hang from a Hook (HookPlacementHandler),
    // they don't place a block on right-click like a normal BlockItem.
    public static final DeferredItem<Item> PIG_CARCASS =
            placementItem("pig_carcass", Carcasses.PIG, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_PIG_CARCASS =
            placementItem("drained_pig_carcass", Carcasses.PIG, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> PIG_HEAD = blockItem("pig_head", new Item.Properties());
    public static final DeferredItem<Item> PIG_HEAD_MOUNT = blockItem("pig_head_mount", new Item.Properties());
    public static final DeferredItem<Item> PIG_SKELETON = blockItem("pig_skeleton", new Item.Properties().stacksTo(8));

    // Fresh/drained carcass items only hang from a Hook (HookPlacementHandler),
    // they don't place a block on right-click like a normal BlockItem.
    public static final DeferredItem<Item> SHEEP_CARCASS =
            placementItem("sheep_carcass", Carcasses.SHEEP, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_SHEEP_CARCASS =
            placementItem("drained_sheep_carcass", Carcasses.SHEEP, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> SHEEP_HEAD = blockItem("sheep_head", new Item.Properties());
    public static final DeferredItem<Item> SHEEP_HEAD_MOUNT = blockItem("sheep_head_mount", new Item.Properties());
    public static final DeferredItem<Item> SHEEP_SKELETON = blockItem("sheep_skeleton", new Item.Properties().stacksTo(8));

    // Fresh/drained carcass items only hang from a Hook (HookPlacementHandler),
    // they don't place a block on right-click like a normal BlockItem.
    public static final DeferredItem<Item> CHICKEN_CARCASS =
            placementItem("chicken_carcass", Carcasses.CHICKEN, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_CHICKEN_CARCASS =
            placementItem("drained_chicken_carcass", Carcasses.CHICKEN, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> CHICKEN_HEAD = blockItem("chicken_head", new Item.Properties());
    public static final DeferredItem<Item> CHICKEN_HEAD_MOUNT = blockItem("chicken_head_mount", new Item.Properties());
    public static final DeferredItem<Item> CHICKEN_SKELETON = blockItem("chicken_skeleton", new Item.Properties().stacksTo(8));

    // Fresh/drained carcass items only hang from a Hook (HookPlacementHandler),
    // they don't place a block on right-click like a normal BlockItem.
    public static final DeferredItem<Item> RABBIT_CARCASS =
            placementItem("rabbit_carcass", Carcasses.RABBIT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_RABBIT_CARCASS =
            placementItem("drained_rabbit_carcass", Carcasses.RABBIT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> RABBIT_HEAD = blockItem("rabbit_head", new Item.Properties());
    public static final DeferredItem<Item> RABBIT_HEAD_MOUNT = blockItem("rabbit_head_mount", new Item.Properties());

    // Fresh/drained carcass items only hang from a Hook (HookPlacementHandler),
    // they don't place a block on right-click like a normal BlockItem.
    public static final DeferredItem<Item> GOAT_CARCASS =
            placementItem("goat_carcass", Carcasses.GOAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_GOAT_CARCASS =
            placementItem("drained_goat_carcass", Carcasses.GOAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> GOAT_HEAD = blockItem("goat_head", new Item.Properties());
    public static final DeferredItem<Item> GOAT_HEAD_MOUNT = blockItem("goat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> GOAT_SKELETON = blockItem("goat_skeleton", new Item.Properties().stacksTo(8));

    // Fresh/drained carcass items only hang from a Hook (HookPlacementHandler),
    // they don't place a block on right-click like a normal BlockItem.
    public static final DeferredItem<Item> FOX_CARCASS =
            placementItem("fox_carcass", Carcasses.FOX, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_FOX_CARCASS =
            placementItem("drained_fox_carcass", Carcasses.FOX, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> FOX_HEAD = blockItem("fox_head", new Item.Properties());
    public static final DeferredItem<Item> FOX_HEAD_MOUNT = blockItem("fox_head_mount", new Item.Properties());
    public static final DeferredItem<Item> FOX_SKELETON = blockItem("fox_skeleton", new Item.Properties().stacksTo(8));

    // Fresh/drained carcass items only hang from a Hook (HookPlacementHandler),
    // they don't place a block on right-click like a normal BlockItem.
    public static final DeferredItem<Item> WOLF_CARCASS =
            placementItem("wolf_carcass", Carcasses.WOLF, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_WOLF_CARCASS =
            placementItem("drained_wolf_carcass", Carcasses.WOLF, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> WOLF_HEAD = blockItem("wolf_head", new Item.Properties());
    public static final DeferredItem<Item> WOLF_HEAD_MOUNT = blockItem("wolf_head_mount", new Item.Properties());
    public static final DeferredItem<Item> WOLF_SKELETON = blockItem("wolf_skeleton", new Item.Properties().stacksTo(8));

    // Camel
    public static final DeferredItem<Item> CAMEL_CARCASS =
            placementItem("camel_carcass", Carcasses.CAMEL, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_CAMEL_CARCASS =
            placementItem("drained_camel_carcass", Carcasses.CAMEL, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> CAMEL_HEAD = blockItem("camel_head", new Item.Properties());
    public static final DeferredItem<Item> CAMEL_HEAD_MOUNT = blockItem("camel_head_mount", new Item.Properties());
    public static final DeferredItem<Item> CAMEL_SKELETON = blockItem("camel_skeleton", new Item.Properties().stacksTo(8));

    // Donkey
    public static final DeferredItem<Item> DONKEY_CARCASS =
            placementItem("donkey_carcass", Carcasses.DONKEY, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_DONKEY_CARCASS =
            placementItem("drained_donkey_carcass", Carcasses.DONKEY, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DONKEY_HEAD = blockItem("donkey_head", new Item.Properties());
    public static final DeferredItem<Item> DONKEY_HEAD_MOUNT = blockItem("donkey_head_mount", new Item.Properties());
    public static final DeferredItem<Item> DONKEY_SKELETON = blockItem("donkey_skeleton", new Item.Properties().stacksTo(8));

    // Mule
    public static final DeferredItem<Item> MULE_CARCASS =
            placementItem("mule_carcass", Carcasses.MULE, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_MULE_CARCASS =
            placementItem("drained_mule_carcass", Carcasses.MULE, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> MULE_HEAD = blockItem("mule_head", new Item.Properties());
    public static final DeferredItem<Item> MULE_HEAD_MOUNT = blockItem("mule_head_mount", new Item.Properties());
    public static final DeferredItem<Item> MULE_SKELETON = blockItem("mule_skeleton", new Item.Properties().stacksTo(8));

    // Ocelot
    public static final DeferredItem<Item> OCELOT_CARCASS =
            placementItem("ocelot_carcass", Carcasses.OCELOT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_OCELOT_CARCASS =
            placementItem("drained_ocelot_carcass", Carcasses.OCELOT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> OCELOT_HEAD = blockItem("ocelot_head", new Item.Properties());
    public static final DeferredItem<Item> OCELOT_HEAD_MOUNT = blockItem("ocelot_head_mount", new Item.Properties());
    public static final DeferredItem<Item> OCELOT_SKELETON = blockItem("ocelot_skeleton", new Item.Properties().stacksTo(8));

    // Panda
    public static final DeferredItem<Item> PANDA_CARCASS =
            placementItem("panda_carcass", Carcasses.PANDA, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_PANDA_CARCASS =
            placementItem("drained_panda_carcass", Carcasses.PANDA, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> PANDA_HEAD = blockItem("panda_head", new Item.Properties());
    public static final DeferredItem<Item> PANDA_HEAD_MOUNT = blockItem("panda_head_mount", new Item.Properties());
    public static final DeferredItem<Item> PANDA_SKELETON = blockItem("panda_skeleton", new Item.Properties().stacksTo(8));

    // Polar Bear
    public static final DeferredItem<Item> POLAR_BEAR_CARCASS =
            placementItem("polar_bear_carcass", Carcasses.POLAR_BEAR, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_POLAR_BEAR_CARCASS =
            placementItem("drained_polar_bear_carcass", Carcasses.POLAR_BEAR, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> POLAR_BEAR_HEAD = blockItem("polar_bear_head", new Item.Properties());
    public static final DeferredItem<Item> POLAR_BEAR_HEAD_MOUNT = blockItem("polar_bear_head_mount", new Item.Properties());
    public static final DeferredItem<Item> POLAR_BEAR_SKELETON = blockItem("polar_bear_skeleton", new Item.Properties().stacksTo(8));

    // Hoglin
    public static final DeferredItem<Item> HOGLIN_CARCASS =
            placementItem("hoglin_carcass", Carcasses.HOGLIN, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_HOGLIN_CARCASS =
            placementItem("drained_hoglin_carcass", Carcasses.HOGLIN, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> HOGLIN_HEAD = blockItem("hoglin_head", new Item.Properties());
    public static final DeferredItem<Item> HOGLIN_HEAD_MOUNT = blockItem("hoglin_head_mount", new Item.Properties());
    public static final DeferredItem<Item> HOGLIN_SKELETON = blockItem("hoglin_skeleton", new Item.Properties().stacksTo(8));

    // Zoglin
    public static final DeferredItem<Item> ZOGLIN_CARCASS =
            placementItem("zoglin_carcass", Carcasses.ZOGLIN, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_ZOGLIN_CARCASS =
            placementItem("drained_zoglin_carcass", Carcasses.ZOGLIN, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> ZOGLIN_HEAD = blockItem("zoglin_head", new Item.Properties());
    public static final DeferredItem<Item> ZOGLIN_HEAD_MOUNT = blockItem("zoglin_head_mount", new Item.Properties());
    // Zoglin has no skeleton

    // Dolphin
    public static final DeferredItem<Item> DOLPHIN_CARCASS =
            placementItem("dolphin_carcass", Carcasses.DOLPHIN, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_DOLPHIN_CARCASS =
            placementItem("drained_dolphin_carcass", Carcasses.DOLPHIN, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DOLPHIN_HEAD = blockItem("dolphin_head", new Item.Properties());
    public static final DeferredItem<Item> DOLPHIN_HEAD_MOUNT = blockItem("dolphin_head_mount", new Item.Properties());
    public static final DeferredItem<Item> DOLPHIN_SKELETON = blockItem("dolphin_skeleton", new Item.Properties().stacksTo(8));

    // Bat
    public static final DeferredItem<Item> BAT_CARCASS =
            placementItem("bat_carcass", Carcasses.BAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_BAT_CARCASS =
            placementItem("drained_bat_carcass", Carcasses.BAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> BAT_HEAD = blockItem("bat_head", new Item.Properties());
    public static final DeferredItem<Item> BAT_HEAD_MOUNT = blockItem("bat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> BAT_SKELETON = blockItem("bat_skeleton", new Item.Properties().stacksTo(8));

    // Silverfish
    public static final DeferredItem<Item> SILVERFISH_CARCASS =
            placementItem("silverfish_carcass", Carcasses.SILVERFISH, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_SILVERFISH_CARCASS =
            placementItem("drained_silverfish_carcass", Carcasses.SILVERFISH, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> SILVERFISH_HEAD = blockItem("silverfish_head", new Item.Properties());
    public static final DeferredItem<Item> SILVERFISH_HEAD_MOUNT = blockItem("silverfish_head_mount", new Item.Properties());
    // Silverfish has no skeleton

    // Endermite
    public static final DeferredItem<Item> ENDERMITE_CARCASS =
            placementItem("endermite_carcass", Carcasses.ENDERMITE, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_ENDERMITE_CARCASS =
            placementItem("drained_endermite_carcass", Carcasses.ENDERMITE, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> ENDERMITE_HEAD = blockItem("endermite_head", new Item.Properties());
    public static final DeferredItem<Item> ENDERMITE_HEAD_MOUNT = blockItem("endermite_head_mount", new Item.Properties());
    // Endermite has no skeleton

    // Cod
    public static final DeferredItem<Item> COD_CARCASS =
            placementItem("cod_carcass", Carcasses.COD, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_COD_CARCASS =
            placementItem("drained_cod_carcass", Carcasses.COD, true, new Item.Properties().stacksTo(8));
    // Cod has no head, head mount, skeleton

    // Salmon
    public static final DeferredItem<Item> SALMON_CARCASS =
            placementItem("salmon_carcass", Carcasses.SALMON, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_SALMON_CARCASS =
            placementItem("drained_salmon_carcass", Carcasses.SALMON, true, new Item.Properties().stacksTo(8));
    // Salmon has no head, head mount, skeleton

    // Phantom
    public static final DeferredItem<Item> PHANTOM_CARCASS =
            placementItem("phantom_carcass", Carcasses.PHANTOM, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_PHANTOM_CARCASS =
            placementItem("drained_phantom_carcass", Carcasses.PHANTOM, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> PHANTOM_HEAD = blockItem("phantom_head", new Item.Properties());
    public static final DeferredItem<Item> PHANTOM_HEAD_MOUNT = blockItem("phantom_head_mount", new Item.Properties());
    public static final DeferredItem<Item> PHANTOM_SKELETON = blockItem("phantom_skeleton", new Item.Properties().stacksTo(8));
    // Phantom has no skin

    // Shulker
    public static final DeferredItem<Item> SHULKER_CARCASS =
            placementItem("shulker_carcass", Carcasses.SHULKER, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_SHULKER_CARCASS =
            placementItem("drained_shulker_carcass", Carcasses.SHULKER, true, new Item.Properties().stacksTo(8));
    // Shulker has no head, head mount, skeleton

    // Guardian
    public static final DeferredItem<Item> GUARDIAN_CARCASS =
            placementItem("guardian_carcass", Carcasses.GUARDIAN, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_GUARDIAN_CARCASS =
            placementItem("drained_guardian_carcass", Carcasses.GUARDIAN, true, new Item.Properties().stacksTo(8));
    // Guardian has no head, head mount, skeleton, skin

    // Elder Guardian
    public static final DeferredItem<Item> ELDER_GUARDIAN_CARCASS =
            placementItem("elder_guardian_carcass", Carcasses.ELDER_GUARDIAN, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_ELDER_GUARDIAN_CARCASS =
            placementItem("drained_elder_guardian_carcass", Carcasses.ELDER_GUARDIAN, true, new Item.Properties().stacksTo(8));
    // Elder Guardian has no head, head mount, skeleton, skin

    // Skeleton Horse
    public static final DeferredItem<Item> SKELETON_HORSE_CARCASS =
            placementItem("skeleton_horse_carcass", Carcasses.SKELETON_HORSE, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_SKELETON_HORSE_CARCASS =
            placementItem("drained_skeleton_horse_carcass", Carcasses.SKELETON_HORSE, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> SKELETON_HORSE_HEAD = blockItem("skeleton_horse_head", new Item.Properties());
    public static final DeferredItem<Item> SKELETON_HORSE_HEAD_MOUNT = blockItem("skeleton_horse_head_mount", new Item.Properties());
    // Skeleton Horse has no skeleton

    // Zombie Horse
    public static final DeferredItem<Item> ZOMBIE_HORSE_CARCASS =
            placementItem("zombie_horse_carcass", Carcasses.ZOMBIE_HORSE, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_ZOMBIE_HORSE_CARCASS =
            placementItem("drained_zombie_horse_carcass", Carcasses.ZOMBIE_HORSE, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> ZOMBIE_HORSE_HEAD = blockItem("zombie_horse_head", new Item.Properties());
    public static final DeferredItem<Item> ZOMBIE_HORSE_HEAD_MOUNT = blockItem("zombie_horse_head_mount", new Item.Properties());
    // Zombie Horse has no skeleton

    // Horse
    public static final DeferredItem<Item> HORSE_CARCASS =
            placementItem("horse_carcass", Carcasses.HORSE, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_HORSE_CARCASS =
            placementItem("drained_horse_carcass", Carcasses.HORSE, true, new Item.Properties().stacksTo(8));
    // Horse has no head, head mount, skeleton

    // Brown Llama
    public static final DeferredItem<Item> BROWN_LLAMA_CARCASS =
            placementItem("brown_llama_carcass", Carcasses.BROWN_LLAMA, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_BROWN_LLAMA_CARCASS =
            placementItem("drained_brown_llama_carcass", Carcasses.BROWN_LLAMA, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> BROWN_LLAMA_HEAD = blockItem("brown_llama_head", new Item.Properties());
    public static final DeferredItem<Item> BROWN_LLAMA_HEAD_MOUNT = blockItem("brown_llama_head_mount", new Item.Properties());
    // Brown Llama has no skeleton

    // White Llama
    public static final DeferredItem<Item> WHITE_LLAMA_CARCASS =
            placementItem("white_llama_carcass", Carcasses.WHITE_LLAMA, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_WHITE_LLAMA_CARCASS =
            placementItem("drained_white_llama_carcass", Carcasses.WHITE_LLAMA, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> WHITE_LLAMA_HEAD = blockItem("white_llama_head", new Item.Properties());
    public static final DeferredItem<Item> WHITE_LLAMA_HEAD_MOUNT = blockItem("white_llama_head_mount", new Item.Properties());
    // White Llama has no skeleton

    // Creamy Llama
    public static final DeferredItem<Item> CREAMY_LLAMA_CARCASS =
            placementItem("creamy_llama_carcass", Carcasses.CREAMY_LLAMA, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_CREAMY_LLAMA_CARCASS =
            placementItem("drained_creamy_llama_carcass", Carcasses.CREAMY_LLAMA, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> CREAMY_LLAMA_HEAD = blockItem("creamy_llama_head", new Item.Properties());
    public static final DeferredItem<Item> CREAMY_LLAMA_HEAD_MOUNT = blockItem("creamy_llama_head_mount", new Item.Properties());
    // Creamy Llama has no skeleton

    // Gray Llama
    public static final DeferredItem<Item> GRAY_LLAMA_CARCASS =
            placementItem("gray_llama_carcass", Carcasses.GRAY_LLAMA, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_GRAY_LLAMA_CARCASS =
            placementItem("drained_gray_llama_carcass", Carcasses.GRAY_LLAMA, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> GRAY_LLAMA_HEAD = blockItem("gray_llama_head", new Item.Properties());
    public static final DeferredItem<Item> GRAY_LLAMA_HEAD_MOUNT = blockItem("gray_llama_head_mount", new Item.Properties());
    // Gray Llama has no skeleton

    // Squid
    public static final DeferredItem<Item> SQUID_CARCASS =
            placementItem("squid_carcass", Carcasses.SQUID, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_SQUID_CARCASS =
            placementItem("drained_squid_carcass", Carcasses.SQUID, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> SQUID_HEAD = blockItem("squid_head", new Item.Properties());
    // Squid has no head mount, no skeleton

    // Glow Squid
    public static final DeferredItem<Item> GLOW_SQUID_CARCASS =
            placementItem("glow_squid_carcass", Carcasses.GLOW_SQUID, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_GLOW_SQUID_CARCASS =
            placementItem("drained_glow_squid_carcass", Carcasses.GLOW_SQUID, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> GLOW_SQUID_HEAD = blockItem("glow_squid_head", new Item.Properties());
    // Glow Squid has no head mount, no skeleton

    // Spider
    public static final DeferredItem<Item> SPIDER_CARCASS =
            placementItem("spider_carcass", Carcasses.SPIDER, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_SPIDER_CARCASS =
            placementItem("drained_spider_carcass", Carcasses.SPIDER, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> SPIDER_HEAD = blockItem("spider_head", new Item.Properties());
    public static final DeferredItem<Item> SPIDER_HEAD_MOUNT = blockItem("spider_head_mount", new Item.Properties());
    // Spider has no skeleton

    // Cave Spider
    public static final DeferredItem<Item> CAVE_SPIDER_CARCASS =
            placementItem("cave_spider_carcass", Carcasses.CAVE_SPIDER, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_CAVE_SPIDER_CARCASS =
            placementItem("drained_cave_spider_carcass", Carcasses.CAVE_SPIDER, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> CAVE_SPIDER_HEAD = blockItem("cave_spider_head", new Item.Properties());
    public static final DeferredItem<Item> CAVE_SPIDER_HEAD_MOUNT = blockItem("cave_spider_head_mount", new Item.Properties());
    // Cave Spider has no skeleton

    // Creeper
    public static final DeferredItem<Item> CREEPER_CARCASS =
            placementItem("creeper_carcass", Carcasses.CREEPER, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_CREEPER_CARCASS =
            placementItem("drained_creeper_carcass", Carcasses.CREEPER, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> CREEPER_HEAD = blockItem("creeper_head", new Item.Properties());
    public static final DeferredItem<Item> CREEPER_HEAD_MOUNT = blockItem("creeper_head_mount", new Item.Properties());
    public static final DeferredItem<Item> CREEPER_SKELETON = blockItem("creeper_skeleton", new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> CREEPER_SKIN = item("creeper_skin", Item::new);
    public static final DeferredItem<Item> RAW_CREEPER_STEAK = item("raw_creeper_steak", Item::new);
    public static final DeferredItem<Item> RAW_CREEPER_LEG = item("raw_creeper_leg", Item::new);

    // Cat variants (11 variants, all share ocelot shapes, have head + head_mount + skin, no skeleton)
    // all_black_cat (variant 0)
    public static final DeferredItem<Item> ALL_BLACK_CAT_CARCASS =
            placementItem("all_black_cat_carcass", Carcasses.ALL_BLACK_CAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_ALL_BLACK_CAT_CARCASS =
            placementItem("drained_all_black_cat_carcass", Carcasses.ALL_BLACK_CAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> ALL_BLACK_CAT_HEAD = blockItem("all_black_cat_head", new Item.Properties());
    public static final DeferredItem<Item> ALL_BLACK_CAT_HEAD_MOUNT = blockItem("all_black_cat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> ALL_BLACK_CAT_SKIN = item("all_black_cat_skin", Item::new);
    // Black Cat (variant 1)
    public static final DeferredItem<Item> BLACK_CAT_CARCASS =
            placementItem("black_cat_carcass", Carcasses.BLACK_CAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_BLACK_CAT_CARCASS =
            placementItem("drained_black_cat_carcass", Carcasses.BLACK_CAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> BLACK_CAT_HEAD = blockItem("black_cat_head", new Item.Properties());
    public static final DeferredItem<Item> BLACK_CAT_HEAD_MOUNT = blockItem("black_cat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> BLACK_CAT_SKIN = item("black_cat_skin", Item::new);
    // British Shorthair (variant 2)
    public static final DeferredItem<Item> BSHORTHAIR_CAT_CARCASS =
            placementItem("bshorthair_cat_carcass", Carcasses.BSHORTHAIR_CAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_BSHORTHAIR_CAT_CARCASS =
            placementItem("drained_bshorthair_cat_carcass", Carcasses.BSHORTHAIR_CAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> BSHORTHAIR_CAT_HEAD = blockItem("bshorthair_cat_head", new Item.Properties());
    public static final DeferredItem<Item> BSHORTHAIR_CAT_HEAD_MOUNT = blockItem("bshorthair_cat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> BSHORTHAIR_CAT_SKIN = item("bshorthair_cat_skin", Item::new);
    // Calico (variant 3)
    public static final DeferredItem<Item> CALICO_CAT_CARCASS =
            placementItem("calico_cat_carcass", Carcasses.CALICO_CAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_CALICO_CAT_CARCASS =
            placementItem("drained_calico_cat_carcass", Carcasses.CALICO_CAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> CALICO_CAT_HEAD = blockItem("calico_cat_head", new Item.Properties());
    public static final DeferredItem<Item> CALICO_CAT_HEAD_MOUNT = blockItem("calico_cat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> CALICO_CAT_SKIN = item("calico_cat_skin", Item::new);
    // Jellie (variant 4)
    public static final DeferredItem<Item> JELLIE_CAT_CARCASS =
            placementItem("jellie_cat_carcass", Carcasses.JELLIE_CAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_JELLIE_CAT_CARCASS =
            placementItem("drained_jellie_cat_carcass", Carcasses.JELLIE_CAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> JELLIE_CAT_HEAD = blockItem("jellie_cat_head", new Item.Properties());
    public static final DeferredItem<Item> JELLIE_CAT_HEAD_MOUNT = blockItem("jellie_cat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> JELLIE_CAT_SKIN = item("jellie_cat_skin", Item::new);
    // Persian (variant 5)
    public static final DeferredItem<Item> PERSIAN_CAT_CARCASS =
            placementItem("persian_cat_carcass", Carcasses.PERSIAN_CAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_PERSIAN_CAT_CARCASS =
            placementItem("drained_persian_cat_carcass", Carcasses.PERSIAN_CAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> PERSIAN_CAT_HEAD = blockItem("persian_cat_head", new Item.Properties());
    public static final DeferredItem<Item> PERSIAN_CAT_HEAD_MOUNT = blockItem("persian_cat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> PERSIAN_CAT_SKIN = item("persian_cat_skin", Item::new);
    // Ragdoll (variant 6)
    public static final DeferredItem<Item> RAGDOLL_CAT_CARCASS =
            placementItem("ragdoll_cat_carcass", Carcasses.RAGDOLL_CAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_RAGDOLL_CAT_CARCASS =
            placementItem("drained_ragdoll_cat_carcass", Carcasses.RAGDOLL_CAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> RAGDOLL_CAT_HEAD = blockItem("ragdoll_cat_head", new Item.Properties());
    public static final DeferredItem<Item> RAGDOLL_CAT_HEAD_MOUNT = blockItem("ragdoll_cat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> RAGDOLL_CAT_SKIN = item("ragdoll_cat_skin", Item::new);
    // Red Cat (variant 7)
    public static final DeferredItem<Item> RED_CAT_CARCASS =
            placementItem("red_cat_carcass", Carcasses.RED_CAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_RED_CAT_CARCASS =
            placementItem("drained_red_cat_carcass", Carcasses.RED_CAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> RED_CAT_HEAD = blockItem("red_cat_head", new Item.Properties());
    public static final DeferredItem<Item> RED_CAT_HEAD_MOUNT = blockItem("red_cat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> RED_CAT_SKIN = item("red_cat_skin", Item::new);
    // Siamese (variant 8)
    public static final DeferredItem<Item> SIAMESE_CAT_CARCASS =
            placementItem("siamese_cat_carcass", Carcasses.SIAMESE_CAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_SIAMESE_CAT_CARCASS =
            placementItem("drained_siamese_cat_carcass", Carcasses.SIAMESE_CAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> SIAMESE_CAT_HEAD = blockItem("siamese_cat_head", new Item.Properties());
    public static final DeferredItem<Item> SIAMESE_CAT_HEAD_MOUNT = blockItem("siamese_cat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> SIAMESE_CAT_SKIN = item("siamese_cat_skin", Item::new);
    // Tabby (variant 9)
    public static final DeferredItem<Item> TABBY_CAT_CARCASS =
            placementItem("tabby_cat_carcass", Carcasses.TABBY_CAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_TABBY_CAT_CARCASS =
            placementItem("drained_tabby_cat_carcass", Carcasses.TABBY_CAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> TABBY_CAT_HEAD = blockItem("tabby_cat_head", new Item.Properties());
    public static final DeferredItem<Item> TABBY_CAT_HEAD_MOUNT = blockItem("tabby_cat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> TABBY_CAT_SKIN = item("tabby_cat_skin", Item::new);
    // White Cat (variant 10)
    public static final DeferredItem<Item> WHITE_CAT_CARCASS =
            placementItem("white_cat_carcass", Carcasses.WHITE_CAT, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_WHITE_CAT_CARCASS =
            placementItem("drained_white_cat_carcass", Carcasses.WHITE_CAT, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> WHITE_CAT_HEAD = blockItem("white_cat_head", new Item.Properties());
    public static final DeferredItem<Item> WHITE_CAT_HEAD_MOUNT = blockItem("white_cat_head_mount", new Item.Properties());
    public static final DeferredItem<Item> WHITE_CAT_SKIN = item("white_cat_skin", Item::new);

    public static final DeferredItem<Item> HOOK = blockItem("hook", new Item.Properties());

    /** Per-mob carcass item, useful for lookup in generified handlers. */
    private static final Map<String, DeferredItem<Item>> FRESH_BY_MOB = new HashMap<>();
    private static final Map<String, DeferredItem<Item>> DRAINED_BY_MOB = new HashMap<>();
    private static final Map<String, DeferredItem<Item>> HEAD_BY_MOB = new HashMap<>();
    private static final Map<String, DeferredItem<Item>> MOUNT_BY_MOB = new HashMap<>();
    private static final Map<String, DeferredItem<Item>> SKELETON_BY_MOB = new HashMap<>();

    static {
        FRESH_BY_MOB.put(Carcasses.COW.mobId(), COW_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.COW.mobId(), DRAINED_COW_CARCASS);
        HEAD_BY_MOB.put(Carcasses.COW.mobId(), COW_HEAD);
        MOUNT_BY_MOB.put(Carcasses.COW.mobId(), COW_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.COW.mobId(), COW_SKELETON);
        FRESH_BY_MOB.put(Carcasses.PIG.mobId(), PIG_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.PIG.mobId(), DRAINED_PIG_CARCASS);
        HEAD_BY_MOB.put(Carcasses.PIG.mobId(), PIG_HEAD);
        MOUNT_BY_MOB.put(Carcasses.PIG.mobId(), PIG_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.PIG.mobId(), PIG_SKELETON);
        FRESH_BY_MOB.put(Carcasses.SHEEP.mobId(), SHEEP_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.SHEEP.mobId(), DRAINED_SHEEP_CARCASS);
        HEAD_BY_MOB.put(Carcasses.SHEEP.mobId(), SHEEP_HEAD);
        MOUNT_BY_MOB.put(Carcasses.SHEEP.mobId(), SHEEP_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.SHEEP.mobId(), SHEEP_SKELETON);
        FRESH_BY_MOB.put(Carcasses.CHICKEN.mobId(), CHICKEN_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.CHICKEN.mobId(), DRAINED_CHICKEN_CARCASS);
        HEAD_BY_MOB.put(Carcasses.CHICKEN.mobId(), CHICKEN_HEAD);
        MOUNT_BY_MOB.put(Carcasses.CHICKEN.mobId(), CHICKEN_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.CHICKEN.mobId(), CHICKEN_SKELETON);
        FRESH_BY_MOB.put(Carcasses.RABBIT.mobId(), RABBIT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.RABBIT.mobId(), DRAINED_RABBIT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.RABBIT.mobId(), RABBIT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.RABBIT.mobId(), RABBIT_HEAD_MOUNT);
        FRESH_BY_MOB.put(Carcasses.GOAT.mobId(), GOAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.GOAT.mobId(), DRAINED_GOAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.GOAT.mobId(), GOAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.GOAT.mobId(), GOAT_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.GOAT.mobId(), GOAT_SKELETON);
        FRESH_BY_MOB.put(Carcasses.FOX.mobId(), FOX_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.FOX.mobId(), DRAINED_FOX_CARCASS);
        HEAD_BY_MOB.put(Carcasses.FOX.mobId(), FOX_HEAD);
        MOUNT_BY_MOB.put(Carcasses.FOX.mobId(), FOX_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.FOX.mobId(), FOX_SKELETON);
        FRESH_BY_MOB.put(Carcasses.WOLF.mobId(), WOLF_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.WOLF.mobId(), DRAINED_WOLF_CARCASS);
        HEAD_BY_MOB.put(Carcasses.WOLF.mobId(), WOLF_HEAD);
        MOUNT_BY_MOB.put(Carcasses.WOLF.mobId(), WOLF_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.WOLF.mobId(), WOLF_SKELETON);
        FRESH_BY_MOB.put(Carcasses.CAMEL.mobId(), CAMEL_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.CAMEL.mobId(), DRAINED_CAMEL_CARCASS);
        HEAD_BY_MOB.put(Carcasses.CAMEL.mobId(), CAMEL_HEAD);
        MOUNT_BY_MOB.put(Carcasses.CAMEL.mobId(), CAMEL_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.CAMEL.mobId(), CAMEL_SKELETON);
        FRESH_BY_MOB.put(Carcasses.DONKEY.mobId(), DONKEY_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.DONKEY.mobId(), DRAINED_DONKEY_CARCASS);
        HEAD_BY_MOB.put(Carcasses.DONKEY.mobId(), DONKEY_HEAD);
        MOUNT_BY_MOB.put(Carcasses.DONKEY.mobId(), DONKEY_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.DONKEY.mobId(), DONKEY_SKELETON);
        FRESH_BY_MOB.put(Carcasses.MULE.mobId(), MULE_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.MULE.mobId(), DRAINED_MULE_CARCASS);
        HEAD_BY_MOB.put(Carcasses.MULE.mobId(), MULE_HEAD);
        MOUNT_BY_MOB.put(Carcasses.MULE.mobId(), MULE_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.MULE.mobId(), MULE_SKELETON);
        FRESH_BY_MOB.put(Carcasses.OCELOT.mobId(), OCELOT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.OCELOT.mobId(), DRAINED_OCELOT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.OCELOT.mobId(), OCELOT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.OCELOT.mobId(), OCELOT_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.OCELOT.mobId(), OCELOT_SKELETON);
        FRESH_BY_MOB.put(Carcasses.PANDA.mobId(), PANDA_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.PANDA.mobId(), DRAINED_PANDA_CARCASS);
        HEAD_BY_MOB.put(Carcasses.PANDA.mobId(), PANDA_HEAD);
        MOUNT_BY_MOB.put(Carcasses.PANDA.mobId(), PANDA_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.PANDA.mobId(), PANDA_SKELETON);
        FRESH_BY_MOB.put(Carcasses.POLAR_BEAR.mobId(), POLAR_BEAR_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.POLAR_BEAR.mobId(), DRAINED_POLAR_BEAR_CARCASS);
        HEAD_BY_MOB.put(Carcasses.POLAR_BEAR.mobId(), POLAR_BEAR_HEAD);
        MOUNT_BY_MOB.put(Carcasses.POLAR_BEAR.mobId(), POLAR_BEAR_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.POLAR_BEAR.mobId(), POLAR_BEAR_SKELETON);
        FRESH_BY_MOB.put(Carcasses.HOGLIN.mobId(), HOGLIN_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.HOGLIN.mobId(), DRAINED_HOGLIN_CARCASS);
        HEAD_BY_MOB.put(Carcasses.HOGLIN.mobId(), HOGLIN_HEAD);
        MOUNT_BY_MOB.put(Carcasses.HOGLIN.mobId(), HOGLIN_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.HOGLIN.mobId(), HOGLIN_SKELETON);
        FRESH_BY_MOB.put(Carcasses.ZOGLIN.mobId(), ZOGLIN_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.ZOGLIN.mobId(), DRAINED_ZOGLIN_CARCASS);
        HEAD_BY_MOB.put(Carcasses.ZOGLIN.mobId(), ZOGLIN_HEAD);
        MOUNT_BY_MOB.put(Carcasses.ZOGLIN.mobId(), ZOGLIN_HEAD_MOUNT);
        // Zoglin has no skeleton
        FRESH_BY_MOB.put(Carcasses.DOLPHIN.mobId(), DOLPHIN_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.DOLPHIN.mobId(), DRAINED_DOLPHIN_CARCASS);
        HEAD_BY_MOB.put(Carcasses.DOLPHIN.mobId(), DOLPHIN_HEAD);
        MOUNT_BY_MOB.put(Carcasses.DOLPHIN.mobId(), DOLPHIN_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.DOLPHIN.mobId(), DOLPHIN_SKELETON);
        FRESH_BY_MOB.put(Carcasses.BAT.mobId(), BAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.BAT.mobId(), DRAINED_BAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.BAT.mobId(), BAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.BAT.mobId(), BAT_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.BAT.mobId(), BAT_SKELETON);
        FRESH_BY_MOB.put(Carcasses.SILVERFISH.mobId(), SILVERFISH_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.SILVERFISH.mobId(), DRAINED_SILVERFISH_CARCASS);
        HEAD_BY_MOB.put(Carcasses.SILVERFISH.mobId(), SILVERFISH_HEAD);
        MOUNT_BY_MOB.put(Carcasses.SILVERFISH.mobId(), SILVERFISH_HEAD_MOUNT);
        // Silverfish has no skeleton
        FRESH_BY_MOB.put(Carcasses.ENDERMITE.mobId(), ENDERMITE_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.ENDERMITE.mobId(), DRAINED_ENDERMITE_CARCASS);
        HEAD_BY_MOB.put(Carcasses.ENDERMITE.mobId(), ENDERMITE_HEAD);
        MOUNT_BY_MOB.put(Carcasses.ENDERMITE.mobId(), ENDERMITE_HEAD_MOUNT);
        // Endermite has no skeleton
        FRESH_BY_MOB.put(Carcasses.COD.mobId(), COD_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.COD.mobId(), DRAINED_COD_CARCASS);
        // Cod has no head, head mount, skeleton
        FRESH_BY_MOB.put(Carcasses.SALMON.mobId(), SALMON_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.SALMON.mobId(), DRAINED_SALMON_CARCASS);
        // Salmon has no head, head mount, skeleton
        FRESH_BY_MOB.put(Carcasses.PHANTOM.mobId(), PHANTOM_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.PHANTOM.mobId(), DRAINED_PHANTOM_CARCASS);
        HEAD_BY_MOB.put(Carcasses.PHANTOM.mobId(), PHANTOM_HEAD);
        MOUNT_BY_MOB.put(Carcasses.PHANTOM.mobId(), PHANTOM_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.PHANTOM.mobId(), PHANTOM_SKELETON);
        // Phantom has no skin
        FRESH_BY_MOB.put(Carcasses.SHULKER.mobId(), SHULKER_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.SHULKER.mobId(), DRAINED_SHULKER_CARCASS);
        // Shulker has no head, head mount, skeleton
        FRESH_BY_MOB.put(Carcasses.GUARDIAN.mobId(), GUARDIAN_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.GUARDIAN.mobId(), DRAINED_GUARDIAN_CARCASS);
        // Guardian has no head, head mount, skeleton, skin
        FRESH_BY_MOB.put(Carcasses.ELDER_GUARDIAN.mobId(), ELDER_GUARDIAN_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.ELDER_GUARDIAN.mobId(), DRAINED_ELDER_GUARDIAN_CARCASS);
        // Elder Guardian has no head, head mount, skeleton, skin
        FRESH_BY_MOB.put(Carcasses.SKELETON_HORSE.mobId(), SKELETON_HORSE_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.SKELETON_HORSE.mobId(), DRAINED_SKELETON_HORSE_CARCASS);
        HEAD_BY_MOB.put(Carcasses.SKELETON_HORSE.mobId(), SKELETON_HORSE_HEAD);
        MOUNT_BY_MOB.put(Carcasses.SKELETON_HORSE.mobId(), SKELETON_HORSE_HEAD_MOUNT);
        // Skeleton Horse has no skeleton
        FRESH_BY_MOB.put(Carcasses.ZOMBIE_HORSE.mobId(), ZOMBIE_HORSE_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.ZOMBIE_HORSE.mobId(), DRAINED_ZOMBIE_HORSE_CARCASS);
        HEAD_BY_MOB.put(Carcasses.ZOMBIE_HORSE.mobId(), ZOMBIE_HORSE_HEAD);
        MOUNT_BY_MOB.put(Carcasses.ZOMBIE_HORSE.mobId(), ZOMBIE_HORSE_HEAD_MOUNT);
        // Zombie Horse has no skeleton
        FRESH_BY_MOB.put(Carcasses.HORSE.mobId(), HORSE_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.HORSE.mobId(), DRAINED_HORSE_CARCASS);
        // Horse has no head, head mount, skeleton
        FRESH_BY_MOB.put(Carcasses.BROWN_LLAMA.mobId(), BROWN_LLAMA_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.BROWN_LLAMA.mobId(), DRAINED_BROWN_LLAMA_CARCASS);
        HEAD_BY_MOB.put(Carcasses.BROWN_LLAMA.mobId(), BROWN_LLAMA_HEAD);
        MOUNT_BY_MOB.put(Carcasses.BROWN_LLAMA.mobId(), BROWN_LLAMA_HEAD_MOUNT);
        // Brown Llama has no skeleton
        FRESH_BY_MOB.put(Carcasses.WHITE_LLAMA.mobId(), WHITE_LLAMA_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.WHITE_LLAMA.mobId(), DRAINED_WHITE_LLAMA_CARCASS);
        HEAD_BY_MOB.put(Carcasses.WHITE_LLAMA.mobId(), WHITE_LLAMA_HEAD);
        MOUNT_BY_MOB.put(Carcasses.WHITE_LLAMA.mobId(), WHITE_LLAMA_HEAD_MOUNT);
        // White Llama has no skeleton
        FRESH_BY_MOB.put(Carcasses.CREAMY_LLAMA.mobId(), CREAMY_LLAMA_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.CREAMY_LLAMA.mobId(), DRAINED_CREAMY_LLAMA_CARCASS);
        HEAD_BY_MOB.put(Carcasses.CREAMY_LLAMA.mobId(), CREAMY_LLAMA_HEAD);
        MOUNT_BY_MOB.put(Carcasses.CREAMY_LLAMA.mobId(), CREAMY_LLAMA_HEAD_MOUNT);
        // Creamy Llama has no skeleton
        FRESH_BY_MOB.put(Carcasses.GRAY_LLAMA.mobId(), GRAY_LLAMA_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.GRAY_LLAMA.mobId(), DRAINED_GRAY_LLAMA_CARCASS);
        HEAD_BY_MOB.put(Carcasses.GRAY_LLAMA.mobId(), GRAY_LLAMA_HEAD);
        MOUNT_BY_MOB.put(Carcasses.GRAY_LLAMA.mobId(), GRAY_LLAMA_HEAD_MOUNT);
        // Gray Llama has no skeleton
        FRESH_BY_MOB.put(Carcasses.SQUID.mobId(), SQUID_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.SQUID.mobId(), DRAINED_SQUID_CARCASS);
        HEAD_BY_MOB.put(Carcasses.SQUID.mobId(), SQUID_HEAD);
        // Squid has no head mount, no skeleton
        FRESH_BY_MOB.put(Carcasses.GLOW_SQUID.mobId(), GLOW_SQUID_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.GLOW_SQUID.mobId(), DRAINED_GLOW_SQUID_CARCASS);
        HEAD_BY_MOB.put(Carcasses.GLOW_SQUID.mobId(), GLOW_SQUID_HEAD);
        // Glow Squid has no head mount, no skeleton
        FRESH_BY_MOB.put(Carcasses.SPIDER.mobId(), SPIDER_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.SPIDER.mobId(), DRAINED_SPIDER_CARCASS);
        HEAD_BY_MOB.put(Carcasses.SPIDER.mobId(), SPIDER_HEAD);
        MOUNT_BY_MOB.put(Carcasses.SPIDER.mobId(), SPIDER_HEAD_MOUNT);
        // Spider has no skeleton
        FRESH_BY_MOB.put(Carcasses.CAVE_SPIDER.mobId(), CAVE_SPIDER_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.CAVE_SPIDER.mobId(), DRAINED_CAVE_SPIDER_CARCASS);
        HEAD_BY_MOB.put(Carcasses.CAVE_SPIDER.mobId(), CAVE_SPIDER_HEAD);
        MOUNT_BY_MOB.put(Carcasses.CAVE_SPIDER.mobId(), CAVE_SPIDER_HEAD_MOUNT);
        // Cave Spider has no skeleton
        FRESH_BY_MOB.put(Carcasses.ALL_BLACK_CAT.mobId(), ALL_BLACK_CAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.ALL_BLACK_CAT.mobId(), DRAINED_ALL_BLACK_CAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.ALL_BLACK_CAT.mobId(), ALL_BLACK_CAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.ALL_BLACK_CAT.mobId(), ALL_BLACK_CAT_HEAD_MOUNT);
        // all_black_cat has no skeleton
        FRESH_BY_MOB.put(Carcasses.BLACK_CAT.mobId(), BLACK_CAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.BLACK_CAT.mobId(), DRAINED_BLACK_CAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.BLACK_CAT.mobId(), BLACK_CAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.BLACK_CAT.mobId(), BLACK_CAT_HEAD_MOUNT);
        // black_cat has no skeleton
        FRESH_BY_MOB.put(Carcasses.BSHORTHAIR_CAT.mobId(), BSHORTHAIR_CAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.BSHORTHAIR_CAT.mobId(), DRAINED_BSHORTHAIR_CAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.BSHORTHAIR_CAT.mobId(), BSHORTHAIR_CAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.BSHORTHAIR_CAT.mobId(), BSHORTHAIR_CAT_HEAD_MOUNT);
        // bshorthair has no skeleton
        FRESH_BY_MOB.put(Carcasses.CALICO_CAT.mobId(), CALICO_CAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.CALICO_CAT.mobId(), DRAINED_CALICO_CAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.CALICO_CAT.mobId(), CALICO_CAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.CALICO_CAT.mobId(), CALICO_CAT_HEAD_MOUNT);
        // calico has no skeleton
        FRESH_BY_MOB.put(Carcasses.JELLIE_CAT.mobId(), JELLIE_CAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.JELLIE_CAT.mobId(), DRAINED_JELLIE_CAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.JELLIE_CAT.mobId(), JELLIE_CAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.JELLIE_CAT.mobId(), JELLIE_CAT_HEAD_MOUNT);
        // jellie has no skeleton
        FRESH_BY_MOB.put(Carcasses.PERSIAN_CAT.mobId(), PERSIAN_CAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.PERSIAN_CAT.mobId(), DRAINED_PERSIAN_CAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.PERSIAN_CAT.mobId(), PERSIAN_CAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.PERSIAN_CAT.mobId(), PERSIAN_CAT_HEAD_MOUNT);
        // persian has no skeleton
        FRESH_BY_MOB.put(Carcasses.RAGDOLL_CAT.mobId(), RAGDOLL_CAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.RAGDOLL_CAT.mobId(), DRAINED_RAGDOLL_CAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.RAGDOLL_CAT.mobId(), RAGDOLL_CAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.RAGDOLL_CAT.mobId(), RAGDOLL_CAT_HEAD_MOUNT);
        // ragdoll has no skeleton
        FRESH_BY_MOB.put(Carcasses.RED_CAT.mobId(), RED_CAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.RED_CAT.mobId(), DRAINED_RED_CAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.RED_CAT.mobId(), RED_CAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.RED_CAT.mobId(), RED_CAT_HEAD_MOUNT);
        // red_cat has no skeleton
        FRESH_BY_MOB.put(Carcasses.SIAMESE_CAT.mobId(), SIAMESE_CAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.SIAMESE_CAT.mobId(), DRAINED_SIAMESE_CAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.SIAMESE_CAT.mobId(), SIAMESE_CAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.SIAMESE_CAT.mobId(), SIAMESE_CAT_HEAD_MOUNT);
        // siamese has no skeleton
        FRESH_BY_MOB.put(Carcasses.TABBY_CAT.mobId(), TABBY_CAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.TABBY_CAT.mobId(), DRAINED_TABBY_CAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.TABBY_CAT.mobId(), TABBY_CAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.TABBY_CAT.mobId(), TABBY_CAT_HEAD_MOUNT);
        // tabby has no skeleton
        FRESH_BY_MOB.put(Carcasses.WHITE_CAT.mobId(), WHITE_CAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.WHITE_CAT.mobId(), DRAINED_WHITE_CAT_CARCASS);
        HEAD_BY_MOB.put(Carcasses.WHITE_CAT.mobId(), WHITE_CAT_HEAD);
        MOUNT_BY_MOB.put(Carcasses.WHITE_CAT.mobId(), WHITE_CAT_HEAD_MOUNT);
        // white_cat has no skeleton
        FRESH_BY_MOB.put(Carcasses.CREEPER.mobId(), CREEPER_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.CREEPER.mobId(), DRAINED_CREEPER_CARCASS);
        HEAD_BY_MOB.put(Carcasses.CREEPER.mobId(), CREEPER_HEAD);
        MOUNT_BY_MOB.put(Carcasses.CREEPER.mobId(), CREEPER_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.CREEPER.mobId(), CREEPER_SKELETON);
    }

    private ModItems() {
    }

    private static <I extends Item> DeferredItem<I> item(String name, Function<Item.Properties, ? extends I> factory) {
        return REGISTRY.registerItem(name, factory, Item.Properties::new);
    }

    private static DeferredItem<Item> blockItem(String name, Item.Properties properties) {
        Supplier<? extends net.minecraft.world.level.block.Block> block = switch (name) {
            case "cow_head" -> ModBlocks.headFor(Carcasses.COW.mobId());
            case "cow_head_mount" -> ModBlocks.mountFor(Carcasses.COW.mobId());
            case "cow_skeleton" -> ModBlocks.skeletonFor(Carcasses.COW.mobId());
            case "pig_head" -> ModBlocks.headFor(Carcasses.PIG.mobId());
            case "pig_head_mount" -> ModBlocks.mountFor(Carcasses.PIG.mobId());
            case "pig_skeleton" -> ModBlocks.skeletonFor(Carcasses.PIG.mobId());
            case "sheep_head" -> ModBlocks.headFor(Carcasses.SHEEP.mobId());
            case "sheep_head_mount" -> ModBlocks.mountFor(Carcasses.SHEEP.mobId());
            case "sheep_skeleton" -> ModBlocks.skeletonFor(Carcasses.SHEEP.mobId());
            case "chicken_head" -> ModBlocks.headFor(Carcasses.CHICKEN.mobId());
            case "chicken_head_mount" -> ModBlocks.mountFor(Carcasses.CHICKEN.mobId());
            case "chicken_skeleton" -> ModBlocks.skeletonFor(Carcasses.CHICKEN.mobId());
            case "rabbit_head" -> ModBlocks.headFor(Carcasses.RABBIT.mobId());
            case "rabbit_head_mount" -> ModBlocks.mountFor(Carcasses.RABBIT.mobId());
            case "goat_head" -> ModBlocks.headFor(Carcasses.GOAT.mobId());
            case "goat_head_mount" -> ModBlocks.mountFor(Carcasses.GOAT.mobId());
            case "goat_skeleton" -> ModBlocks.skeletonFor(Carcasses.GOAT.mobId());
            case "fox_head" -> ModBlocks.headFor(Carcasses.FOX.mobId());
            case "fox_head_mount" -> ModBlocks.mountFor(Carcasses.FOX.mobId());
            case "fox_skeleton" -> ModBlocks.skeletonFor(Carcasses.FOX.mobId());
            case "wolf_head" -> ModBlocks.headFor(Carcasses.WOLF.mobId());
            case "wolf_head_mount" -> ModBlocks.mountFor(Carcasses.WOLF.mobId());
            case "wolf_skeleton" -> ModBlocks.skeletonFor(Carcasses.WOLF.mobId());
            case "camel_head" -> ModBlocks.headFor(Carcasses.CAMEL.mobId());
            case "camel_head_mount" -> ModBlocks.mountFor(Carcasses.CAMEL.mobId());
            case "camel_skeleton" -> ModBlocks.skeletonFor(Carcasses.CAMEL.mobId());
            case "donkey_head" -> ModBlocks.headFor(Carcasses.DONKEY.mobId());
            case "donkey_head_mount" -> ModBlocks.mountFor(Carcasses.DONKEY.mobId());
            case "donkey_skeleton" -> ModBlocks.skeletonFor(Carcasses.DONKEY.mobId());
            case "mule_head" -> ModBlocks.headFor(Carcasses.MULE.mobId());
            case "mule_head_mount" -> ModBlocks.mountFor(Carcasses.MULE.mobId());
            case "mule_skeleton" -> ModBlocks.skeletonFor(Carcasses.MULE.mobId());
            case "ocelot_head" -> ModBlocks.headFor(Carcasses.OCELOT.mobId());
            case "ocelot_head_mount" -> ModBlocks.mountFor(Carcasses.OCELOT.mobId());
            case "ocelot_skeleton" -> ModBlocks.skeletonFor(Carcasses.OCELOT.mobId());
            case "panda_head" -> ModBlocks.headFor(Carcasses.PANDA.mobId());
            case "panda_head_mount" -> ModBlocks.mountFor(Carcasses.PANDA.mobId());
            case "panda_skeleton" -> ModBlocks.skeletonFor(Carcasses.PANDA.mobId());
            case "polar_bear_head" -> ModBlocks.headFor(Carcasses.POLAR_BEAR.mobId());
            case "polar_bear_head_mount" -> ModBlocks.mountFor(Carcasses.POLAR_BEAR.mobId());
            case "polar_bear_skeleton" -> ModBlocks.skeletonFor(Carcasses.POLAR_BEAR.mobId());
            case "hoglin_head" -> ModBlocks.headFor(Carcasses.HOGLIN.mobId());
            case "hoglin_head_mount" -> ModBlocks.mountFor(Carcasses.HOGLIN.mobId());
            case "hoglin_skeleton" -> ModBlocks.skeletonFor(Carcasses.HOGLIN.mobId());
            case "zoglin_head" -> ModBlocks.headFor(Carcasses.ZOGLIN.mobId());
            case "zoglin_head_mount" -> ModBlocks.mountFor(Carcasses.ZOGLIN.mobId());
            // zoglin has no skeleton
            case "dolphin_head" -> ModBlocks.headFor(Carcasses.DOLPHIN.mobId());
            case "dolphin_head_mount" -> ModBlocks.mountFor(Carcasses.DOLPHIN.mobId());
            case "dolphin_skeleton" -> ModBlocks.skeletonFor(Carcasses.DOLPHIN.mobId());
            case "bat_head" -> ModBlocks.headFor(Carcasses.BAT.mobId());
            case "bat_head_mount" -> ModBlocks.mountFor(Carcasses.BAT.mobId());
            case "bat_skeleton" -> ModBlocks.skeletonFor(Carcasses.BAT.mobId());
            case "silverfish_head" -> ModBlocks.headFor(Carcasses.SILVERFISH.mobId());
            case "silverfish_head_mount" -> ModBlocks.mountFor(Carcasses.SILVERFISH.mobId());
            // silverfish has no skeleton
            case "endermite_head" -> ModBlocks.headFor(Carcasses.ENDERMITE.mobId());
            case "endermite_head_mount" -> ModBlocks.mountFor(Carcasses.ENDERMITE.mobId());
            // endermite has no skeleton
            case "phantom_head" -> ModBlocks.headFor(Carcasses.PHANTOM.mobId());
            case "phantom_head_mount" -> ModBlocks.mountFor(Carcasses.PHANTOM.mobId());
            case "phantom_skeleton" -> ModBlocks.skeletonFor(Carcasses.PHANTOM.mobId());
            case "skeleton_horse_head" -> ModBlocks.headFor(Carcasses.SKELETON_HORSE.mobId());
            case "skeleton_horse_head_mount" -> ModBlocks.mountFor(Carcasses.SKELETON_HORSE.mobId());
            case "zombie_horse_head" -> ModBlocks.headFor(Carcasses.ZOMBIE_HORSE.mobId());
            case "zombie_horse_head_mount" -> ModBlocks.mountFor(Carcasses.ZOMBIE_HORSE.mobId());
            case "brown_llama_head" -> ModBlocks.headFor(Carcasses.BROWN_LLAMA.mobId());
            case "brown_llama_head_mount" -> ModBlocks.mountFor(Carcasses.BROWN_LLAMA.mobId());
            // Brown Llama has no skeleton
            case "white_llama_head" -> ModBlocks.headFor(Carcasses.WHITE_LLAMA.mobId());
            case "white_llama_head_mount" -> ModBlocks.mountFor(Carcasses.WHITE_LLAMA.mobId());
            // White Llama has no skeleton
            case "creamy_llama_head" -> ModBlocks.headFor(Carcasses.CREAMY_LLAMA.mobId());
            case "creamy_llama_head_mount" -> ModBlocks.mountFor(Carcasses.CREAMY_LLAMA.mobId());
            // Creamy Llama has no skeleton
            case "gray_llama_head" -> ModBlocks.headFor(Carcasses.GRAY_LLAMA.mobId());
            case "gray_llama_head_mount" -> ModBlocks.mountFor(Carcasses.GRAY_LLAMA.mobId());
            // Gray Llama has no skeleton
            case "squid_head" -> ModBlocks.headFor(Carcasses.SQUID.mobId());
            // Squid has no head mount
            case "glow_squid_head" -> ModBlocks.headFor(Carcasses.GLOW_SQUID.mobId());
            // Glow Squid has no head mount
            case "spider_head" -> ModBlocks.headFor(Carcasses.SPIDER.mobId());
            case "spider_head_mount" -> ModBlocks.mountFor(Carcasses.SPIDER.mobId());
            // Spider has no skeleton
            case "cave_spider_head" -> ModBlocks.headFor(Carcasses.CAVE_SPIDER.mobId());
            case "cave_spider_head_mount" -> ModBlocks.mountFor(Carcasses.CAVE_SPIDER.mobId());
            // Cave Spider has no skeleton
            case "all_black_cat_head" -> ModBlocks.headFor(Carcasses.ALL_BLACK_CAT.mobId());
            case "all_black_cat_head_mount" -> ModBlocks.mountFor(Carcasses.ALL_BLACK_CAT.mobId());
            // all_black_cat has no skeleton
            case "black_cat_head" -> ModBlocks.headFor(Carcasses.BLACK_CAT.mobId());
            case "black_cat_head_mount" -> ModBlocks.mountFor(Carcasses.BLACK_CAT.mobId());
            // black_cat has no skeleton
            case "bshorthair_cat_head" -> ModBlocks.headFor(Carcasses.BSHORTHAIR_CAT.mobId());
            case "bshorthair_cat_head_mount" -> ModBlocks.mountFor(Carcasses.BSHORTHAIR_CAT.mobId());
            // bshorthair has no skeleton
            case "calico_cat_head" -> ModBlocks.headFor(Carcasses.CALICO_CAT.mobId());
            case "calico_cat_head_mount" -> ModBlocks.mountFor(Carcasses.CALICO_CAT.mobId());
            // calico has no skeleton
            case "jellie_cat_head" -> ModBlocks.headFor(Carcasses.JELLIE_CAT.mobId());
            case "jellie_cat_head_mount" -> ModBlocks.mountFor(Carcasses.JELLIE_CAT.mobId());
            // jellie has no skeleton
            case "persian_cat_head" -> ModBlocks.headFor(Carcasses.PERSIAN_CAT.mobId());
            case "persian_cat_head_mount" -> ModBlocks.mountFor(Carcasses.PERSIAN_CAT.mobId());
            // persian has no skeleton
            case "ragdoll_cat_head" -> ModBlocks.headFor(Carcasses.RAGDOLL_CAT.mobId());
            case "ragdoll_cat_head_mount" -> ModBlocks.mountFor(Carcasses.RAGDOLL_CAT.mobId());
            // ragdoll has no skeleton
            case "red_cat_head" -> ModBlocks.headFor(Carcasses.RED_CAT.mobId());
            case "red_cat_head_mount" -> ModBlocks.mountFor(Carcasses.RED_CAT.mobId());
            // red_cat has no skeleton
            case "siamese_cat_head" -> ModBlocks.headFor(Carcasses.SIAMESE_CAT.mobId());
            case "siamese_cat_head_mount" -> ModBlocks.mountFor(Carcasses.SIAMESE_CAT.mobId());
            // siamese has no skeleton
            case "tabby_cat_head" -> ModBlocks.headFor(Carcasses.TABBY_CAT.mobId());
            case "tabby_cat_head_mount" -> ModBlocks.mountFor(Carcasses.TABBY_CAT.mobId());
            // tabby has no skeleton
            case "white_cat_head" -> ModBlocks.headFor(Carcasses.WHITE_CAT.mobId());
            case "white_cat_head_mount" -> ModBlocks.mountFor(Carcasses.WHITE_CAT.mobId());
            // white_cat has no skeleton
            case "creeper_head" -> ModBlocks.headFor(Carcasses.CREEPER.mobId());
            case "creeper_head_mount" -> ModBlocks.mountFor(Carcasses.CREEPER.mobId());
            case "creeper_skeleton" -> ModBlocks.skeletonFor(Carcasses.CREEPER.mobId());
            case "hook" -> ModBlocks.HOOK;
            default -> throw new IllegalArgumentException("No block registered for item " + name);
        };
        return REGISTRY.registerItem(name,
                props -> new BlockItem(block.get(), props),
                () -> properties);
    }

    /** Fresh/drained carcass item that only hangs from a hook, see {@link CarcassPlacementItem}. */
    private static DeferredItem<Item> placementItem(String name, CarcassDefinition definition, boolean drained,
                                                      Item.Properties properties) {
        return REGISTRY.registerItem(name,
                props -> new CarcassPlacementItem(props, definition, drained),
                () -> properties);
    }

    public static DeferredItem<Item> freshItemFor(String mobId) {
        return FRESH_BY_MOB.get(mobId);
    }

    public static Map<String, DeferredItem<Item>> freshItems() {
        return FRESH_BY_MOB;
    }

    public static Map<String, DeferredItem<Item>> drainedItems() {
        return DRAINED_BY_MOB;
    }

    public static Map<String, DeferredItem<Item>> headItems() {
        return HEAD_BY_MOB;
    }

    public static Map<String, DeferredItem<Item>> mountItems() {
        return MOUNT_BY_MOB;
    }
}