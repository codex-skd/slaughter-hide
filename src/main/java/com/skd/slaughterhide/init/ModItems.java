package com.skd.slaughterhide.init;

import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.Carcasses;
import com.skd.slaughterhide.SlaughterHide;
import com.skd.slaughterhide.tag.ModItemTags;
import com.skd.slaughterhide.item.ButcherToolItem;
import com.skd.slaughterhide.item.CarcassPlacementItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
    public static final DeferredItem<Item> IRON_HACKSAW = item("iron_hacksaw", ButcherToolItem::new);
    public static final DeferredItem<Item> IRON_HAMMER = item("iron_hammer", ButcherToolItem::new);

    // Copper tier
    public static final DeferredItem<Item> COPPER_CLEAVER = item("copper_cleaver", ButcherToolItem::new);
    public static final DeferredItem<Item> COPPER_SKINNING_KNIFE = item("copper_skinning_knife", ButcherToolItem::new);
    public static final DeferredItem<Item> COPPER_HACKSAW = item("copper_hacksaw", ButcherToolItem::new);
    public static final DeferredItem<Item> COPPER_HAMMER = item("copper_hammer", ButcherToolItem::new);

    // Gold tier
    public static final DeferredItem<Item> GOLD_CLEAVER = item("gold_cleaver", ButcherToolItem::new);
    public static final DeferredItem<Item> GOLD_SKINNING_KNIFE = item("gold_skinning_knife", ButcherToolItem::new);
    public static final DeferredItem<Item> GOLD_HACKSAW = item("gold_hacksaw", ButcherToolItem::new);
    public static final DeferredItem<Item> GOLD_HAMMER = item("gold_hammer", ButcherToolItem::new);

    // Diamond tier
    public static final DeferredItem<Item> DIAMOND_CLEAVER = item("diamond_cleaver", ButcherToolItem::new);
    public static final DeferredItem<Item> DIAMOND_SKINNING_KNIFE = item("diamond_skinning_knife", ButcherToolItem::new);
    public static final DeferredItem<Item> DIAMOND_HACKSAW = item("diamond_hacksaw", ButcherToolItem::new);
    public static final DeferredItem<Item> DIAMOND_HAMMER = item("diamond_hammer", ButcherToolItem::new);

    // Netherite tier
    public static final DeferredItem<Item> NETHERITE_CLEAVER = item("netherite_cleaver", ButcherToolItem::new);
    public static final DeferredItem<Item> NETHERITE_SKINNING_KNIFE = item("netherite_skinning_knife", ButcherToolItem::new);
    public static final DeferredItem<Item> NETHERITE_HACKSAW = item("netherite_hacksaw", ButcherToolItem::new);
    public static final DeferredItem<Item> NETHERITE_HAMMER = item("netherite_hammer", ButcherToolItem::new);

    // Bone tier

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
    public static final DeferredItem<Item> COOKED_CHICKEN_LEG = item("cooked_chicken_leg", Item::new);
    public static final DeferredItem<Item> COOKED_CHICKEN_WING = item("cooked_chicken_wing", Item::new);
    public static final DeferredItem<Item> BIRD_FOOT = item("bird_foot", Item::new);
    public static final DeferredItem<Item> WISHBONE = item("wishbone", Item::new);
    // Goat: shares the sheep's lamb cuts and the generic hoof (see goat_cut_*_drop loot tables)
    public static final DeferredItem<Item> GOAT_SKIN = item("goat_skin", Item::new);
    // Cooked meats (corresponding smelting/smoking recipes)
    public static final DeferredItem<Item> COOKED_CHUCK_STEAK = item("cooked_chuck_steak", Item::new);
    public static final DeferredItem<Item> COOKED_RIBEYE_STEAK = item("cooked_ribeye_steak", Item::new);
    public static final DeferredItem<Item> COOKED_RUMP_STEAK = item("cooked_rump_steak", Item::new);
    public static final DeferredItem<Item> COOKED_SIRLOIN_STEAK = item("cooked_sirloin_steak", Item::new);
    public static final DeferredItem<Item> COOKED_TBONE_STEAK = item("cooked_tbone_steak", Item::new);
    public static final DeferredItem<Item> COOKED_PORK_SHOULDER = item("cooked_pork_shoulder", Item::new);
    public static final DeferredItem<Item> COOKED_PORK_LOIN = item("cooked_pork_loin", Item::new);
    public static final DeferredItem<Item> COOKED_PORK_LEG = item("cooked_pork_leg", Item::new);
    public static final DeferredItem<Item> COOKED_PORK_BELLY = item("cooked_pork_belly", Item::new);
    public static final DeferredItem<Item> COOKED_HAM = item("cooked_ham", Item::new);
    public static final DeferredItem<Item> COOKED_LEG_OF_LAMB = item("cooked_leg_of_lamb", Item::new);
    public static final DeferredItem<Item> COOKED_LAMB_SHOULDER = item("cooked_lamb_shoulder", Item::new);
    public static final DeferredItem<Item> COOKED_LAMB_RIB = item("cooked_lamb_rib", Item::new);
    public static final DeferredItem<Item> COOKED_LAMB_SIRLOIN = item("cooked_lamb_sirloin", Item::new);
    public static final DeferredItem<Item> COOKED_LAMB_LOIN = item("cooked_lamb_loin", Item::new);
    // Polar Bear
    public static final DeferredItem<Item> RAW_POLAR_BEAR_MEAT = item("raw_polar_bear_meat", Item::new);
    public static final DeferredItem<Item> COOKED_POLAR_BEAR_MEAT = item("cooked_polar_bear_meat", Item::new);

    // Grinder items
    public static final DeferredItem<Item> INTESTINES = item("intestines", Item::new);
    public static final DeferredItem<Item> SAUSAGE_ATTACHMENT = item("sausage_attachment", Item::new);
    public static final DeferredItem<Item> BOTTLE_OF_BLOOD = item("bottle_of_blood", Item::new);
    public static final DeferredItem<Item> MEAT_SCRAPS = item("meat_scraps", Item::new);
    public static final DeferredItem<Item> RAW_SAUSAGE = item("raw_sausage", Item::new);
    public static final DeferredItem<Item> RAW_BLOOD_SAUSAGE = item("raw_blood_sausage", Item::new);
    public static final DeferredItem<Item> RAW_BEEF_MINCE = item("raw_beef_mince", Item::new);
    public static final DeferredItem<Item> RAW_LAMB_MINCE = item("raw_lamb_mince", Item::new);

    // Fluid buckets -- must chain onto the Properties the DeferredRegister passes in
    // (it carries the registry id via setId); a fresh Item.Properties() would crash
    // with "Item id not set" when BucketItem's constructor validates it.
    public static final DeferredItem<Item> BLOOD_BUCKET = REGISTRY.registerItem("blood_bucket",
            props -> new BucketItem(ModFluids.BLOOD.get(),
                    props.craftRemainder(Items.BUCKET).stacksTo(1)),
            Item.Properties::new);
    public static final DeferredItem<Item> INFECTED_BLOOD_BUCKET = REGISTRY.registerItem("infected_blood_bucket",
            props -> new BucketItem(ModFluids.INFECTED_BLOOD.get(),
                    props.craftRemainder(Items.BUCKET).stacksTo(1)),
            Item.Properties::new);


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

    public static final DeferredItem<Item> POLAR_BEAR_CARCASS =
            placementItem("polar_bear_carcass", Carcasses.POLAR_BEAR, false, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> DRAINED_POLAR_BEAR_CARCASS =
            placementItem("drained_polar_bear_carcass", Carcasses.POLAR_BEAR, true, new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> POLAR_BEAR_HEAD = blockItem("polar_bear_head", new Item.Properties());
    public static final DeferredItem<Item> POLAR_BEAR_HEAD_MOUNT = blockItem("polar_bear_head_mount", new Item.Properties());
    public static final DeferredItem<Item> POLAR_BEAR_SKELETON = blockItem("polar_bear_skeleton", new Item.Properties().stacksTo(8));
    public static final DeferredItem<Item> POLAR_BEAR_SKIN = blockItem("polar_bear_skin", new Item.Properties());

    // Fresh/drained carcass items only hang from a Hook (HookPlacementHandler),
    // they don't place a block on right-click like a normal BlockItem.

    // Fresh/drained carcass items only hang from a Hook (HookPlacementHandler),
    // they don't place a block on right-click like a normal BlockItem.

    // Camel

    // Donkey

    // Mule

    // Ocelot

    // Panda

    // Polar Bear

    // Hoglin

    // Zoglin
    // Zoglin has no skeleton

    // Dolphin

    // Bat

    // Silverfish
    // Silverfish has no skeleton

    // Endermite
    // Drained endermite carcass disabled - no assets exist
    // Endermite has no skeleton

    // Cod
    // Cod has no head, head mount, skeleton

    // Salmon
    // Salmon has no head, head mount, skeleton

    // Phantom
    // Phantom has no skin

    // Shulker
    // Shulker has no head, head mount, skeleton

    // Guardian
    // Guardian has no head, head mount, skeleton, skin

    // Elder Guardian
    // Elder Guardian has no head, head mount, skeleton, skin

    // Skeleton Horse
    // Skeleton Horse has no skeleton

    // Zombie Horse
    // Zombie Horse has no skeleton

    // Horse
    // Horse has no head, head mount, skeleton

    // Brown Llama
    // Brown Llama has no skeleton

    // White Llama
    // White Llama has no skeleton

    // Creamy Llama
    // Creamy Llama has no skeleton

    // Gray Llama
    // Gray Llama has no skeleton

    // Squid
    // Squid has no head mount, no skeleton

    // Glow Squid
    // Glow Squid has no head mount, no skeleton

    // Slime
    // Slime has no head, head mount, skeleton

    // Medium Slime
    // Medium Slime has no head, head mount, skeleton

    // Small Slime
    // Small Slime has no head, head mount, skeleton

    // Magma Cube
    // Magma Cube has no head, head mount, skeleton

    // Medium Magma Cube
    // Medium Magma Cube has no head, head mount, skeleton

    // Small Magma Cube
    // Small Magma Cube has no head, head mount, skeleton

    // Corpse blocks (humanoid mobs with organ harvesting)
    // Zombie
    // Zombie has no head, head mount, skeleton

    // Skeleton
    // Skeleton has no head, head mount, skeleton

    // Drowned
    // Drowned has no head, head mount, skeleton

    // Husk
    // Husk has no head, head mount, skeleton

    // Vindicator
    // Vindicator has no head, head mount, skeleton

    // Evoker
    // Evoker has no head, head mount, skeleton

    // Witch
    // Witch has no head, head mount, skeleton

    // Piglin
    // Piglin has no head, head mount, skeleton

    // Piglin Brute
    // Piglin Brute has no head, head mount, skeleton

    // Ravager
    // Ravager has no head, head mount, skeleton

    // Enderman
    // Enderman has no skeleton

    // Strider
    // Strider has no head, head mount, skeleton

    // Sniffer
    // Sniffer has no head mount, skeleton

    // Turtle
    // Turtle has no skeleton

    // Blue Axolotl
    // Pink Axolotl
    // Brown Axolotl
    // Cyan Axolotl
    // Gold Axolotl
    // Basin, Brain, Cash Register, Skin Rack, Wooden Spit Rotisserie, Jar, Metal Tray (Butchery blocks)
    public static final DeferredItem<Item> BASIN = blockItem("basin", new Item.Properties());
    public static final DeferredItem<Item> BRAIN = blockItem("brain", new Item.Properties());
    public static final DeferredItem<Item> CASH_REGISTER = blockItem("cash_register_block", new Item.Properties());
    public static final DeferredItem<Item> SKIN_RACK = blockItem("skin_rack", new Item.Properties());
    public static final DeferredItem<Item> WOODEN_SPIT_ROTISSERIE = blockItem("wooden_spit_rotisserie", new Item.Properties());
    public static final DeferredItem<Item> JAR = blockItem("jar", new Item.Properties());
    public static final DeferredItem<Item> METAL_TRAY = blockItem("metal_tray", new Item.Properties());
    public static final DeferredItem<Item> FREEZER = blockItem("freezer", new Item.Properties());
    public static final DeferredItem<Item> MEAT_GRINDER = blockItem("meat_grinder", new Item.Properties());
    public static final DeferredItem<Item> PESTLE_AND_MORTAR = blockItem("pestle_and_mortar", new Item.Properties());
    public static final DeferredItem<Item> TAXIDERMY_TABLE = blockItem("taxidermy_table", new Item.Properties());
    public static final DeferredItem<Item> BLOOD_GRATE = blockItem("blood_grate", new Item.Properties());
    public static final DeferredItem<Item> BLOOD_PUDDLE = blockItem("blood_puddle", new Item.Properties());
    // Blood fluid block items (these are liquid blocks, so they don't have normal BlockItems)
    // Blood and infected_blood are registered via the blockItem switch above

    // Furniture block-items
    public static final DeferredItem<Item> OAK_COUNTER = blockItem("oak_counter", new Item.Properties());
    public static final DeferredItem<Item> BIRCH_COUNTER = blockItem("birch_counter", new Item.Properties());
    public static final DeferredItem<Item> SPRUCE_COUNTER = blockItem("spruce_counter", new Item.Properties());
    public static final DeferredItem<Item> JUNGLE_COUNTER = blockItem("jungle_counter", new Item.Properties());
    public static final DeferredItem<Item> ACACIA_COUNTER = blockItem("acacia_counter", new Item.Properties());
    public static final DeferredItem<Item> DARK_OAK_COUNTER = blockItem("dark_oak_counter", new Item.Properties());
    public static final DeferredItem<Item> CRIMSON_COUNTER = blockItem("crimson_counter", new Item.Properties());
    public static final DeferredItem<Item> WARPED_COUNTER = blockItem("warped_counter", new Item.Properties());

    public static final DeferredItem<Item> CANOPY_BLACK = blockItem("canopy_black", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_BLUE = blockItem("canopy_blue", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_BROWN = blockItem("canopy_brown", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_CYAN = blockItem("canopy_cyan", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_GRAY = blockItem("canopy_gray", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_GREEN = blockItem("canopy_green", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_LIGHT_BLUE = blockItem("canopy_light_blue", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_LIGHT_GRAY = blockItem("canopy_light_gray", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_LIME = blockItem("canopy_lime", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_MAGENTA = blockItem("canopy_magenta", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_ORANGE = blockItem("canopy_orange", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_PINK = blockItem("canopy_pink", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_PURPLE = blockItem("canopy_purple", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_RED = blockItem("canopy_red", new Item.Properties());
    public static final DeferredItem<Item> CANOPY_YELLOW = blockItem("canopy_yellow", new Item.Properties());

    public static final DeferredItem<Item> OAK_BUTCHERS_TABLE = blockItem("oak_butchers_table", new Item.Properties());
    public static final DeferredItem<Item> BIRCH_BUTCHERS_TABLE = blockItem("birch_butchers_table", new Item.Properties());
    public static final DeferredItem<Item> SPRUCE_BUTCHERS_TABLE = blockItem("spruce_butchers_table", new Item.Properties());
    public static final DeferredItem<Item> JUNGLE_BUTCHERS_TABLE = blockItem("jungle_butchers_table", new Item.Properties());
    public static final DeferredItem<Item> ACACIA_BUTCHERS_TABLE = blockItem("acacia_butchers_table", new Item.Properties());
    public static final DeferredItem<Item> DARK_OAK_BUTCHERS_TABLE = blockItem("dark_oak_butchers_table", new Item.Properties());
    public static final DeferredItem<Item> MANGROVE_BUTCHERS_TABLE = blockItem("mangrove_butchers_table", new Item.Properties());
    public static final DeferredItem<Item> CRIMSON_BUTCHERS_TABLE = blockItem("crimson_butchers_table", new Item.Properties());
    public static final DeferredItem<Item> WARPED_BUTCHERS_TABLE = blockItem("warped_butchers_table", new Item.Properties());
    public static final DeferredItem<Item> METAL_BUTCHERS_TABLE = blockItem("metal_butchers_table", new Item.Properties());

    public static final DeferredItem<Item> OAK_BUTCHER_DISPLAY = blockItem("oak_butcher_display", new Item.Properties());
    public static final DeferredItem<Item> BIRCH_BUTCHER_DISPLAY = blockItem("birch_butcher_display", new Item.Properties());
    public static final DeferredItem<Item> SPRUCE_BUTCHER_DISPLAY = blockItem("spruce_butcher_display", new Item.Properties());
    public static final DeferredItem<Item> JUNGLE_BUTCHER_DISPLAY = blockItem("jungle_butcher_display", new Item.Properties());
    public static final DeferredItem<Item> ACACIA_BUTCHER_DISPLAY = blockItem("acacia_butcher_display", new Item.Properties());
    public static final DeferredItem<Item> DARK_OAK_BUTCHER_DISPLAY = blockItem("dark_oak_butcher_display", new Item.Properties());
    public static final DeferredItem<Item> CRIMSON_BUTCHER_DISPLAY = blockItem("crimson_butcher_display", new Item.Properties());
    public static final DeferredItem<Item> WARPED_BUTCHER_DISPLAY = blockItem("warped_butcher_display", new Item.Properties());

    // Cat variants (11 variants, all share ocelot shapes, have head + head_mount + skin, no skeleton)
    // all_black_cat (variant 0)
    // Black Cat (variant 1)
    // British Shorthair (variant 2)
    // Calico (variant 3)
    // Jellie (variant 4)
    // Persian (variant 5)
    // Ragdoll (variant 6)
    // Red Cat (variant 7)
    // Siamese (variant 8)
    // Tabby (variant 9)
    // White Cat (variant 10)

    public static final DeferredItem<Item> HOOK = blockItem("hook", new Item.Properties());

    /** Alternative attachment point a carcass item can hang from (rope). */
    public static final DeferredItem<Item> ROPE = blockItem("rope", new Item.Properties());

    /** Per-mob carcass item, useful for lookup in generified handlers. */
    private static final Map<String, DeferredItem<Item>> FRESH_BY_MOB = new HashMap<>();
    private static final Map<String, DeferredItem<Item>> DRAINED_BY_MOB = new HashMap<>();
    private static final Map<String, DeferredItem<Item>> HEAD_BY_MOB = new HashMap<>();
    private static final Map<String, DeferredItem<Item>> MOUNT_BY_MOB = new HashMap<>();
    private static final Map<String, DeferredItem<Item>> SKELETON_BY_MOB = new HashMap<>();

    /** Mobs that have drained carcass blockstate JSONs/assets. */
    private static final Set<String> HAS_DRAINED_ASSETS = Set.of(
            "bat", "camel", "chicken", "cow", "dolphin", "donkey",
            "enderman", "fox", "goat", "hoglin", "mule", "ocelot",
            "panda", "pig", "polar_bear", "rabbit", "sheep", "sniffer",
            "strider", "turtle", "wolf", "zoglin"
    );

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
        FRESH_BY_MOB.put(Carcasses.POLAR_BEAR.mobId(), POLAR_BEAR_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.GOAT.mobId(), DRAINED_GOAT_CARCASS);
        DRAINED_BY_MOB.put(Carcasses.POLAR_BEAR.mobId(), DRAINED_POLAR_BEAR_CARCASS);
        HEAD_BY_MOB.put(Carcasses.GOAT.mobId(), GOAT_HEAD);
        HEAD_BY_MOB.put(Carcasses.POLAR_BEAR.mobId(), POLAR_BEAR_HEAD);
        MOUNT_BY_MOB.put(Carcasses.GOAT.mobId(), GOAT_HEAD_MOUNT);
        MOUNT_BY_MOB.put(Carcasses.POLAR_BEAR.mobId(), POLAR_BEAR_HEAD_MOUNT);
        SKELETON_BY_MOB.put(Carcasses.GOAT.mobId(), GOAT_SKELETON);
        SKELETON_BY_MOB.put(Carcasses.POLAR_BEAR.mobId(), POLAR_BEAR_SKELETON);        // Zoglin has no skeleton
        // Silverfish has no skeleton
        // Drained endermite carcass disabled - no assets exist
        // Endermite has no skeleton
        // Cod has no head, head mount, skeleton
        // Salmon has no head, head mount, skeleton
        // Phantom has no skin
        // Shulker has no head, head mount, skeleton
        // Guardian has no head, head mount, skeleton, skin
        // Elder Guardian has no head, head mount, skeleton, skin
        // Skeleton Horse has no skeleton
        // Zombie Horse has no skeleton        // Horse has no head, head mount, skeleton        // Brown Llama has no skeleton        // White Llama has no skeleton        // Creamy Llama has no skeleton        // Gray Llama has no skeleton
        // Squid has no head mount, no skeleton
        // Glow Squid has no head mount, no skeleton
        // Slime has no head, head mount, skeleton
        // Medium Slime has no head, head mount, skeleton
        // Small Slime has no head, head mount, skeleton
        // Magma Cube has no head, head mount, skeleton
        // Medium Magma Cube has no head, head mount, skeleton
        // Small Magma Cube has no head, head mount, skeleton
        // Spider has no skeleton
        // Cave Spider has no skeleton
        // all_black_cat has no skeleton
        // black_cat has no skeleton
        // bshorthair has no skeleton
        // calico has no skeleton
        // jellie has no skeleton
        // persian has no skeleton
        // ragdoll has no skeleton
        // red_cat has no skeleton
        // siamese has no skeleton
        // tabby has no skeleton
        // white_cat has no skeleton
        // blue_axolotl has no skin entry in map (skin items are handled separately)
        // Pufferfish has no head, head mount, skeleton
        // Slime has no head, head mount, skeleton
        // Medium Slime has no head, head mount, skeleton
        // Small Slime has no head, head mount, skeleton
        // Magma Cube has no head, head mount, skeleton
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
            case "polar_bear_head" -> ModBlocks.headFor(Carcasses.POLAR_BEAR.mobId());
            case "polar_bear_head_mount" -> ModBlocks.mountFor(Carcasses.POLAR_BEAR.mobId());
            case "polar_bear_skeleton" -> ModBlocks.skeletonFor(Carcasses.POLAR_BEAR.mobId());
            case "hook" -> ModBlocks.HOOK;
            case "rope" -> ModBlocks.ROPE;
            case "deepslate_sulfur_ore" -> ModBlocks.DEEPSLATE_SULFUR_ORE;
            case "sulfur_ore" -> ModBlocks.SULFUR_ORE;
            case "diorite_brick_slab" -> ModBlocks.DIORITE_BRICK_SLAB;
            case "diorite_brick_wall" -> ModBlocks.DIORITE_BRICK_WALL;
            case "diorite_brick_stairs" -> ModBlocks.DIORITE_BRICK_STAIRS;
            case "diorite_bricks" -> ModBlocks.DIORITE_BRICKS;
            case "salt_formation_middle" -> ModBlocks.SALT_FORMATION_MIDDLE;
            case "salt_formation_tip" -> ModBlocks.SALT_FORMATION_TIP;
            case "salt_formation_frustum" -> ModBlocks.SALT_FORMATION_FRUSTUM;
            case "salt_formation_base" -> ModBlocks.SALT_FORMATION_BASE;
            case "dragon_scale_block" -> ModBlocks.DRAGON_SCALE_BLOCK;
            case "salt_block" -> ModBlocks.SALT_BLOCK;
            case "bone_barrel" -> ModBlocks.BONE_BARREL;
            case "cod_barrel" -> ModBlocks.COD_BARREL;
            case "floorstanding_sign" -> ModBlocks.FLOOR_STANDING_SIGN;
            case "butcher_statue" -> ModBlocks.BUTCHER_STATUE;
            case "cling_film" -> ModBlocks.CLING_FILM;
            case "photos" -> ModBlocks.PHOTOS;            case "cooked_blood_sausages" -> ModBlocks.COOKED_BLOOD_SAUSAGES;
            case "cooked_sausages" -> ModBlocks.COOKED_SAUSAGES;
            case "raw_blood_sausages" -> ModBlocks.RAW_BLOOD_SAUSAGES;
            case "raw_sausages" -> ModBlocks.RAW_SAUSAGES;
            case "salmon_barrel" -> ModBlocks.SALMON_BARREL;
            case "blood_splatter" -> ModBlocks.BLOOD_SPLATTER;
            case "plastic_sheet" -> ModBlocks.PLASTIC_SHEET;
            case "plastic_sheet_corner" -> ModBlocks.PLASTIC_SHEET_CORNER;
            case "spike_trap" -> ModBlocks.SPIKE_TRAP;
            case "blood_grate" -> ModBlocks.BLOOD_GRATE;
            case "blood_puddle" -> ModBlocks.BLOOD_PUDDLE;
            case "basin" -> ModBlocks.BASIN;
            case "blood" -> ModBlocks.BLOOD;
            case "infected_blood" -> ModBlocks.INFECTED_BLOOD;
            case "brain" -> ModBlocks.BRAIN;
            case "cash_register_block" -> ModBlocks.CASH_REGISTER;
            case "skin_rack" -> ModBlocks.SKIN_RACK;
            case "wooden_spit_rotisserie" -> ModBlocks.WOODEN_SPIT_ROTISSERIE;
            case "jar" -> ModBlocks.JAR;
            case "metal_tray" -> ModBlocks.METAL_TRAY;
            case "freezer" -> ModBlocks.FREEZER;
            case "meat_grinder" -> ModBlocks.MEAT_GRINDER;
            case "pestle_and_mortar" -> ModBlocks.PESTLE_AND_MORTAR;
            case "taxidermy_table" -> ModBlocks.TAXIDERMY_TABLE;
            default -> ModBlocks.furnitureFor(name);
        };
        return REGISTRY.registerItem(name,
                props -> {
                    net.minecraft.world.level.block.Block b = block != null ? block.get() : null;
                    return b != null ? new BlockItem(b, props) : new Item(props);
                },
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

    public static Map<String, DeferredItem<Item>> skeletonItems() {
        return SKELETON_BY_MOB;
    }
}