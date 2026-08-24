package com.skd.slaughterhide.init;

import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.Carcasses;
import com.skd.slaughterhide.SlaughterHide;
import com.skd.slaughterhide.block.CarcassBlock;
import com.skd.slaughterhide.block.CorpseBlock;
import com.skd.slaughterhide.block.DrainedCarcassBlock;
import com.skd.slaughterhide.block.DeepslateSulfurOreBlock;
import com.skd.slaughterhide.block.DioriteBrickSlabBlock;
import com.skd.slaughterhide.block.DioriteBrickStairsBlock;
import com.skd.slaughterhide.block.DioriteBrickWallBlock;
import com.skd.slaughterhide.block.DioriteBricksBlock;
import com.skd.slaughterhide.block.DragonScaleBlock;
import com.skd.slaughterhide.block.HeadMountBlock;
import com.skd.slaughterhide.block.HookBlock;
import com.skd.slaughterhide.block.IronGolemHeadMountBlock;
import com.skd.slaughterhide.block.RavagerHeadBlock;
import com.skd.slaughterhide.block.RavagerHeadMountBlock;
import com.skd.slaughterhide.block.RopeBlock;
import com.skd.slaughterhide.block.BloodSplatterBlock;
import com.skd.slaughterhide.block.PlasticSheetBlock;
import com.skd.slaughterhide.block.PlasticSheetCornerBlock;
import com.skd.slaughterhide.block.SpikeTrapBlock;
import com.skd.slaughterhide.block.SaltBlock;
import com.skd.slaughterhide.block.SaltFormationBaseBlock;
import com.skd.slaughterhide.block.SaltFormationFrustumBlock;
import com.skd.slaughterhide.block.SaltFormationMiddleBlock;
import com.skd.slaughterhide.block.SaltFormationTipBlock;
import com.skd.slaughterhide.block.SkeletonBlock;
import com.skd.slaughterhide.block.SulfurOreBlock;
import com.skd.slaughterhide.block.TrophyHeadBlock;
import com.skd.slaughterhide.block.BoneBarrelBlock;
import com.skd.slaughterhide.block.CodBarrelBlock;
import com.skd.slaughterhide.block.SalmonBarrelBlock;
import com.skd.slaughterhide.block.FloorStandingSignBlock;
import com.skd.slaughterhide.block.ButcherStatueBlock;
import com.skd.slaughterhide.block.ClingFilmBlock;
import com.skd.slaughterhide.block.PhotosBlock;
import com.skd.slaughterhide.block.CookedBloodSausagesBlock;
import com.skd.slaughterhide.block.CookedSausagesBlock;
import com.skd.slaughterhide.block.RawBloodSausagesBlock;
import com.skd.slaughterhide.block.RawSausagesBlock;
import com.skd.slaughterhide.block.BasinBlock;
import com.skd.slaughterhide.block.BrainBlock;
import com.skd.slaughterhide.block.CashRegisterBlock;
import com.skd.slaughterhide.block.JarBlock;
import com.skd.slaughterhide.block.MetalTrayBlock;
import com.skd.slaughterhide.block.SkinRackBlock;
import com.skd.slaughterhide.block.WoodenSpitRotisserieBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Block registrations. The generic carcass family is registered straight from
 * the {@link CarcassDefinition} table instead of one class per mob, so adding
 * a mob means adding a definition entry (plus its assets), nothing else.
 */
public final class ModBlocks {
    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(SlaughterHide.MOD_ID);

    private static final Map<String, DeferredBlock<CarcassBlock>> FRESH = new HashMap<>();
    private static final Map<String, DeferredBlock<DrainedCarcassBlock>> DRAINED = new HashMap<>();
    private static final Map<String, DeferredBlock<Block>> HEADS = new HashMap<>();
    private static final Map<String, DeferredBlock<Block>> MOUNTS = new HashMap<>();
    private static final Map<String, DeferredBlock<Block>> SKELETONS = new HashMap<>();
    private static final Map<String, DeferredBlock<CorpseBlock>> CORPSE = new HashMap<>();

    /** Mobs that have drained carcass blockstate JSONs/assets. */
    private static final Set<String> HAS_DRAINED_ASSETS = Set.of(
            "bat", "camel", "chicken", "cow", "dolphin", "donkey",
            "enderman", "fox", "goat", "hoglin", "mule", "ocelot",
            "panda", "pig", "polar_bear", "rabbit", "sheep", "sniffer",
            "strider", "turtle", "wolf", "zoglin"
    );

    /** Mobs that have head blockstate JSONs/assets. */
    private static final Set<String> HAS_HEAD_ASSETS = Set.of(
            "bat", "camel", "chicken", "cow", "dolphin", "donkey", "drowned",
            "enderman", "endermite", "evoker", "fox", "goat", "hoglin", "husk",
            "mule", "ocelot", "panda", "pig", "piglin_brute", "polar_bear",
            "rabbit", "ravager", "sheep", "silverfish", "sniffer", "turtle",
            "vindicator", "witch", "wolf", "zoglin"
    );

    /** Mobs that have head_mount blockstate JSONs/assets. */
    private static final Set<String> HAS_HEAD_MOUNT_ASSETS = Set.of(
            "bat", "camel", "chicken", "cow", "dolphin", "donkey", "drowned",
            "enderman", "endermite", "evoker", "fox", "goat", "hoglin", "husk",
            "mule", "ocelot", "panda", "pig", "piglin", "rabbit", "ravager",
            "sheep", "silverfish", "skeleton", "turtle", "vindicator", "witch",
            "wolf", "zoglin", "zombie"
    );

    /** Mobs that have skeleton blockstate JSONs/assets. */
    private static final Set<String> HAS_SKELETON_ASSETS = Set.of(
            "bat", "camel", "chicken", "cow", "dolphin", "donkey", "fox",
            "goat", "hoglin", "mule", "ocelot", "panda", "pig", "piglin",
            "sheep", "wolf", "polar_bear"
    );

    /** Mobs that have corpse blockstate JSONs/assets. */
    private static final Set<String> HAS_CORPSE_ASSETS = Set.of(
            "drowned", "evoker", "husk", "piglin", "piglin_brute",
            "skeleton", "vindicator", "witch", "zombie"
    );

    /** Global attachment point a carcass item hangs from, see HookPlacementHandler. */
    public static final DeferredBlock<HookBlock> HOOK = register("hook", HookBlock::new);

    /** Alternative attachment point a carcass item can hang from (rope). */
    public static final DeferredBlock<RopeBlock> ROPE = register("rope", RopeBlock::new);

    public static final DeferredBlock<DeepslateSulfurOreBlock> DEEPSLATE_SULFUR_ORE = register("deepslate_sulfur_ore", DeepslateSulfurOreBlock::new);
    public static final DeferredBlock<SulfurOreBlock> SULFUR_ORE = register("sulfur_ore", SulfurOreBlock::new);
    public static final DeferredBlock<DioriteBricksBlock> DIORITE_BRICKS = register("diorite_bricks", DioriteBricksBlock::new);
    public static final DeferredBlock<DioriteBrickSlabBlock> DIORITE_BRICK_SLAB = register("diorite_brick_slab", DioriteBrickSlabBlock::new);
    public static final DeferredBlock<DioriteBrickWallBlock> DIORITE_BRICK_WALL = register("diorite_brick_wall", DioriteBrickWallBlock::new);
    public static final DeferredBlock<DioriteBrickStairsBlock> DIORITE_BRICK_STAIRS = register("diorite_brick_stairs", DioriteBrickStairsBlock::new);
    public static final DeferredBlock<SaltFormationMiddleBlock> SALT_FORMATION_MIDDLE = register("salt_formation_middle", SaltFormationMiddleBlock::new);
    public static final DeferredBlock<SaltFormationTipBlock> SALT_FORMATION_TIP = register("salt_formation_tip", SaltFormationTipBlock::new);
    public static final DeferredBlock<SaltFormationFrustumBlock> SALT_FORMATION_FRUSTUM = register("salt_formation_frustum", SaltFormationFrustumBlock::new);
    public static final DeferredBlock<SaltFormationBaseBlock> SALT_FORMATION_BASE = register("salt_formation_base", SaltFormationBaseBlock::new);
    public static final DeferredBlock<DragonScaleBlock> DRAGON_SCALE_BLOCK = register("dragon_scale_block", DragonScaleBlock::new);
    public static final DeferredBlock<SaltBlock> SALT_BLOCK = register("salt_block", SaltBlock::new);
    public static final DeferredBlock<BoneBarrelBlock> BONE_BARREL = register("bone_barrel", BoneBarrelBlock::new);
    public static final DeferredBlock<CodBarrelBlock> COD_BARREL = register("cod_barrel", CodBarrelBlock::new);
    public static final DeferredBlock<FloorStandingSignBlock> FLOOR_STANDING_SIGN = register("floorstanding_sign", FloorStandingSignBlock::new);
    public static final DeferredBlock<ButcherStatueBlock> BUTCHER_STATUE = register("butcher_statue", ButcherStatueBlock::new);
    public static final DeferredBlock<ClingFilmBlock> CLING_FILM = register("cling_film", ClingFilmBlock::new);
    public static final DeferredBlock<PhotosBlock> PHOTOS = register("photos", PhotosBlock::new);
    public static final DeferredBlock<SalmonBarrelBlock> SALMON_BARREL = register("salmon_barrel", SalmonBarrelBlock::new);
    public static final DeferredBlock<IronGolemHeadMountBlock> IRON_GOLEM_HEAD_MOUNT = register("iron_golem_head_mount", IronGolemHeadMountBlock::new);
    public static final DeferredBlock<CookedBloodSausagesBlock> COOKED_BLOOD_SAUSAGES = register("cooked_blood_sausages", CookedBloodSausagesBlock::new);
    public static final DeferredBlock<CookedSausagesBlock> COOKED_SAUSAGES = register("cooked_sausages", CookedSausagesBlock::new);
    public static final DeferredBlock<RawBloodSausagesBlock> RAW_BLOOD_SAUSAGES = register("raw_blood_sausages", RawBloodSausagesBlock::new);
    public static final DeferredBlock<RawSausagesBlock> RAW_SAUSAGES = register("raw_sausages", RawSausagesBlock::new);
    public static final DeferredBlock<RavagerHeadBlock> RAVAGER_HEAD = register("ravager_head", RavagerHeadBlock::new);
    public static final DeferredBlock<RavagerHeadMountBlock> RAVAGER_HEAD_MOUNT = register("ravager_head_mount", RavagerHeadMountBlock::new);

    public static final DeferredBlock<BloodSplatterBlock> BLOOD_SPLATTER = register("blood_splatter", BloodSplatterBlock::new);
    public static final DeferredBlock<PlasticSheetBlock> PLASTIC_SHEET = register("plastic_sheet", PlasticSheetBlock::new);
    public static final DeferredBlock<PlasticSheetCornerBlock> PLASTIC_SHEET_CORNER = register("plastic_sheet_corner", PlasticSheetCornerBlock::new);
    public static final DeferredBlock<SpikeTrapBlock> SPIKE_TRAP = register("spike_trap", SpikeTrapBlock::new);

    public static final DeferredBlock<BasinBlock> BASIN = register("basin", BasinBlock::new);
    public static final DeferredBlock<BrainBlock> BRAIN = register("brain", BrainBlock::new);
    public static final DeferredBlock<CashRegisterBlock> CASH_REGISTER = register("cash_register_block", CashRegisterBlock::new);
    public static final DeferredBlock<SkinRackBlock> SKIN_RACK = register("skin_rack", SkinRackBlock::new);
    public static final DeferredBlock<WoodenSpitRotisserieBlock> WOODEN_SPIT_ROTISSERIE = register("wooden_spit_rotisserie", WoodenSpitRotisserieBlock::new);
    public static final DeferredBlock<JarBlock> JAR = register("jar", JarBlock::new);
    public static final DeferredBlock<MetalTrayBlock> METAL_TRAY = register("metal_tray", MetalTrayBlock::new);

    static {
        registerFamily(Carcasses.COW);
        registerFamily(Carcasses.PIG);
        registerFamily(Carcasses.SHEEP);
        registerFamily(Carcasses.CHICKEN);
        registerFamily(Carcasses.RABBIT);
        registerFamily(Carcasses.GOAT);
        registerFamily(Carcasses.FOX);
        registerFamily(Carcasses.WOLF);
        registerFamily(Carcasses.CAMEL);
        registerFamily(Carcasses.DONKEY);
        registerFamily(Carcasses.MULE);
        registerFamily(Carcasses.OCELOT);
        registerFamily(Carcasses.PANDA);
        registerFamily(Carcasses.POLAR_BEAR);
        registerFamily(Carcasses.HOGLIN);
        registerFamily(Carcasses.ZOGLIN);
        registerFamily(Carcasses.DOLPHIN);
        registerFamily(Carcasses.BAT);
        registerFamily(Carcasses.SILVERFISH);
        registerFamily(Carcasses.ENDERMITE);
        registerFamily(Carcasses.COD);
        registerFamily(Carcasses.SALMON);
        registerFamily(Carcasses.PHANTOM);
        registerFamily(Carcasses.SHULKER);
        registerFamily(Carcasses.GUARDIAN);
        registerFamily(Carcasses.ELDER_GUARDIAN);
        registerFamily(Carcasses.SKELETON_HORSE);
        registerFamily(Carcasses.ZOMBIE_HORSE);
        registerFamily(Carcasses.HORSE);
        registerFamily(Carcasses.BROWN_LLAMA);
        registerFamily(Carcasses.WHITE_LLAMA);
        registerFamily(Carcasses.CREAMY_LLAMA);
        registerFamily(Carcasses.GRAY_LLAMA);
        registerFamily(Carcasses.SQUID);
        registerFamily(Carcasses.GLOW_SQUID);
        registerFamily(Carcasses.CREEPER);
        registerFamily(Carcasses.SPIDER);
        registerFamily(Carcasses.CAVE_SPIDER);
        registerFamily(Carcasses.BLUE_AXOLOTL);
        registerFamily(Carcasses.PINK_AXOLOTL);
        registerFamily(Carcasses.BROWN_AXOLOTL);
        registerFamily(Carcasses.CYAN_AXOLOTL);
        registerFamily(Carcasses.GOLD_AXOLOTL);
        registerFamily(Carcasses.PUFFERFISH);
        registerFamily(Carcasses.SLIME);
        registerFamily(Carcasses.MAGMA_CUBE);
        registerFamily(Carcasses.MEDIUM_SLIME);
        registerFamily(Carcasses.SMALL_SLIME);
        registerFamily(Carcasses.MEDIUM_MAGMA_CUBE);
        registerFamily(Carcasses.SMALL_MAGMA_CUBE);
        registerFamily(Carcasses.ENDERMAN);
        registerFamily(Carcasses.STRIDER);
        registerFamily(Carcasses.SNIFFER);
        registerFamily(Carcasses.TURTLE);
        // CorpseBlock humanoids (organ harvesting)
        registerCorpseFamily(Carcasses.ZOMBIE);
        registerCorpseFamily(Carcasses.SKELETON);
        registerCorpseFamily(Carcasses.DROWNED);
        registerCorpseFamily(Carcasses.HUSK);
        registerCorpseFamily(Carcasses.VINDICATOR);
        registerCorpseFamily(Carcasses.EVOKER);
        registerCorpseFamily(Carcasses.WITCH);
        registerCorpseFamily(Carcasses.PIGLIN);
        registerCorpseFamily(Carcasses.PIGLIN_BRUTE);
        registerCorpseFamily(Carcasses.RAVAGER);
        // Cat variants (11 variants, all share ocelot shapes, have head+head_mount+skin, no skeleton, 0 cuts)
        registerFamily(Carcasses.ALL_BLACK_CAT);
        registerFamily(Carcasses.BLACK_CAT);
        registerFamily(Carcasses.BSHORTHAIR_CAT);
        registerFamily(Carcasses.CALICO_CAT);
        registerFamily(Carcasses.JELLIE_CAT);
        registerFamily(Carcasses.PERSIAN_CAT);
        registerFamily(Carcasses.RAGDOLL_CAT);
        registerFamily(Carcasses.RED_CAT);
        registerFamily(Carcasses.SIAMESE_CAT);
        registerFamily(Carcasses.TABBY_CAT);
        registerFamily(Carcasses.WHITE_CAT);
    }

    private ModBlocks() {
    }

    private static void registerFamily(CarcassDefinition definition) {
        String mob = definition.mobId();
        DeferredBlock<CarcassBlock> fresh = register(mob + "_carcass",
                props -> new CarcassBlock(props, definition));
        FRESH.put(mob, fresh);
        if (HAS_DRAINED_ASSETS.contains(mob)) {
            DeferredBlock<DrainedCarcassBlock> drained = register("drained_" + mob + "_carcass",
                    props -> new DrainedCarcassBlock(props, definition));
            DRAINED.put(mob, drained);
        }
        if (HAS_HEAD_ASSETS.contains(mob)) {
            HEADS.put(mob, register(mob + "_head", props -> new TrophyHeadBlock(props, definition)));
        }
        if (HAS_HEAD_MOUNT_ASSETS.contains(mob)) {
            MOUNTS.put(mob, register(mob + "_head_mount", props -> new HeadMountBlock(props, definition)));
        }
        if (HAS_SKELETON_ASSETS.contains(mob)) {
            SKELETONS.put(mob, register(mob + "_skeleton", props -> new SkeletonBlock(props, definition)));
        }
    }

    private static void registerCorpseFamily(CarcassDefinition definition) {
        String mob = definition.mobId();
        if (HAS_CORPSE_ASSETS.contains(mob)) {
            DeferredBlock<CorpseBlock> corpse = register(mob + "_corpse",
                    props -> new CorpseBlock(props, definition.corpseShapes()));
            CORPSE.put(mob, corpse);
        }
    }

    private static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> factory) {
        return REGISTRY.registerBlock(name, factory);
    }

    public static DeferredBlock<CarcassBlock> freshFor(String mobId) {
        return FRESH.get(mobId);
    }

    public static DeferredBlock<DrainedCarcassBlock> drainedFor(String mobId) {
        return DRAINED.get(mobId);
    }

    public static DeferredBlock<RopeBlock> ropeFor(String mobId) {
        return ROPE;
    }

    public static DeferredBlock<Block> headFor(String mobId) {
        return HEADS.get(mobId);
    }

    public static DeferredBlock<Block> mountFor(String mobId) {
        return MOUNTS.get(mobId);
    }

    public static DeferredBlock<Block> skeletonFor(String mobId) {
        return SKELETONS.get(mobId);
    }

    public static Map<String, DeferredBlock<Block>> allHeads() {
        return HEADS;
    }

    public static Map<String, DeferredBlock<CarcassBlock>> allFresh() {
        return FRESH;
    }

    public static Map<String, DeferredBlock<DrainedCarcassBlock>> allDrained() {
        return DRAINED;
    }

    /** Every fresh and drained carcass block, for the shared block entity type. */
    public static Block[] carcassBlocks() {
        return java.util.stream.Stream.concat(FRESH.values().stream(), DRAINED.values().stream())
                .map(DeferredBlock::get)
                .toArray(Block[]::new);
    }

    /** Every corpse block, for the corpse block entity type. */
    public static Block[] corpseBlocks() {
        return CORPSE.values().stream()
                .map(DeferredBlock::get)
                .toArray(Block[]::new);
    }

    public static DeferredBlock<CorpseBlock> corpseFor(String mobId) {
        return CORPSE.get(mobId);
    }
}