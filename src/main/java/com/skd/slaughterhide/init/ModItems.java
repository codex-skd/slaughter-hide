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