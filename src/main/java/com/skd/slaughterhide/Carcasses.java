package com.skd.slaughterhide;

import com.skd.slaughterhide.init.ModItems;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Registered carcass families. Cells are keyed by {@link EntityType} so a
 * single death listener can dispatch to the right definition without a
 * procedure class per mob.
 */
public final class Carcasses {
    private Carcasses() {
    }

    public static final Map<EntityType<?>, CarcassDefinition> BY_ENTITY = new java.util.HashMap<>();
    public static final Map<String, CarcassDefinition> BY_MOB_ID = new java.util.HashMap<>();

    /** The reference implementation: cows. */
    public static final CarcassDefinition COW = buildCow();
    /** Second fully-playable mob: pigs. */
    public static final CarcassDefinition PIG = buildPig();
    /** Third fully-playable mob: sheep. */
    public static final CarcassDefinition SHEEP = buildSheep();
    /** Fourth fully-playable mob: chicken. */
    public static final CarcassDefinition CHICKEN = buildChicken();
    /** Fifth fully-playable mob: rabbit. */
    public static final CarcassDefinition RABBIT = buildRabbit();
    /** Sixth fully-playable mob: goat. */
    public static final CarcassDefinition GOAT = buildGoat();
    /** Seventh fully-playable mob: fox. */
    public static final CarcassDefinition FOX = buildFox();
    /** Eighth fully-playable mob: wolf. */
    public static final CarcassDefinition WOLF = buildWolf();
    /** Ninth: camel. */
    public static final CarcassDefinition CAMEL = buildCAMEL();
    /** Tenth: donkey. */
    public static final CarcassDefinition DONKEY = buildDONKEY();
    /** Eleventh: mule. */
    public static final CarcassDefinition MULE = buildMULE();
    /** Twelfth: ocelot. */
    public static final CarcassDefinition OCELOT = buildOCELOT();
    /** Thirteenth: panda. */
    public static final CarcassDefinition PANDA = buildPANDA();
    /** Fourteenth: polar_bear. */
    public static final CarcassDefinition POLAR_BEAR = buildPOLAR_BEAR();
    /** Fifteenth: hoglin. */
    public static final CarcassDefinition HOGLIN = buildHOGLIN();
    /** Sixteenth: zoglin. */
    public static final CarcassDefinition ZOGLIN = buildZOGLIN();
    /** Seventeenth: dolphin. */
    public static final CarcassDefinition DOLPHIN = buildDOLPHIN();
    /** Eighteenth: bat. */
    public static final CarcassDefinition BAT = buildBAT();
    /** Nineteenth: silverfish. */
    public static final CarcassDefinition SILVERFISH = buildSILVERFISH();
    /** Twentieth: endermite. */
    public static final CarcassDefinition ENDERMITE = buildENDERMITE();
    /** Twenty-first: bee. */
    public static final CarcassDefinition BEE = buildBEE();
    /** Twenty-second: cod. */
    public static final CarcassDefinition COD = buildCOD();
    /** Twenty-third: salmon. */
    public static final CarcassDefinition SALMON = buildSALMON();
    /** Twenty-fourth: phantom. */
    public static final CarcassDefinition PHANTOM = buildPHANTOM();
    /** Twenty-fifth: shulker. */
    public static final CarcassDefinition SHULKER = buildSHULKER();
    /** Twenty-sixth: guardian. */
    public static final CarcassDefinition GUARDIAN = buildGUARDIAN();
    /** Twenty-seventh: elder_guardian. */
    public static final CarcassDefinition ELDER_GUARDIAN = buildELDER_GUARDIAN();
    /** Twenty-eighth: skeleton_horse. */
    public static final CarcassDefinition SKELETON_HORSE = buildSKELETON_HORSE();
    /** Twenty-ninth: zombie_horse. */
    public static final CarcassDefinition ZOMBIE_HORSE = buildZOMBIE_HORSE();
    /** Thirtieth: horse. */
    public static final CarcassDefinition HORSE = buildHORSE();
    /** Thirty-first: brown_llama. */
    public static final CarcassDefinition BROWN_LLAMA = buildBROWN_LLAMA();
    /** Thirty-second: white_llama. */
    public static final CarcassDefinition WHITE_LLAMA = buildWHITE_LLAMA();
    /** Thirty-third: creamy_llama. */
    public static final CarcassDefinition CREAMY_LLAMA = buildCREAMY_LLAMA();
    /** Thirty-fourth: gray_llama. */
    public static final CarcassDefinition GRAY_LLAMA = buildGRAY_LLAMA();
    /** Thirty-fifth: squid. */
    public static final CarcassDefinition SQUID = buildSQUID();
    /** Thirty-sixth: glowsquid. */
    public static final CarcassDefinition GLOW_SQUID = buildGLOW_SQUID();
    /** Thirty-seventh: creeper. */
    public static final CarcassDefinition CREEPER = buildCREEPER();
    /** Thirty-eighth: spider. */
    public static final CarcassDefinition SPIDER = buildSPIDER();
    /** Thirty-ninth: cave_spider. */
    public static final CarcassDefinition CAVE_SPIDER = buildCAVE_SPIDER();

    // ===== CAT VARIANTS (11 variants, all share ocelot shapes) =====
    /** Cat variant: all_black_cat (variant 0) */
    public static final CarcassDefinition ALL_BLACK_CAT = buildCatVariant("all_black_cat", 0);
    /** Cat variant: black_cat (variant 1) */
    public static final CarcassDefinition BLACK_CAT = buildCatVariant("black_cat", 1);
    /** Cat variant: bshorthair (variant 2) */
    public static final CarcassDefinition BSHORTHAIR_CAT = buildCatVariant("bshorthair", 2);
    /** Cat variant: calico (variant 3) */
    public static final CarcassDefinition CALICO_CAT = buildCatVariant("calico", 3);
    /** Cat variant: jellie (variant 4) */
    public static final CarcassDefinition JELLIE_CAT = buildCatVariant("jellie", 4);
    /** Cat variant: persian (variant 5) */
    public static final CarcassDefinition PERSIAN_CAT = buildCatVariant("persian", 5);
    /** Cat variant: ragdoll (variant 6) */
    public static final CarcassDefinition RAGDOLL_CAT = buildCatVariant("ragdoll", 6);
    /** Cat variant: red_cat (variant 7) */
    public static final CarcassDefinition RED_CAT = buildCatVariant("red_cat", 7);
    /** Cat variant: siamese (variant 8) */
    public static final CarcassDefinition SIAMESE_CAT = buildCatVariant("siamese", 8);
    /** Cat variant: tabby (variant 9) */
    public static final CarcassDefinition TABBY_CAT = buildCatVariant("tabby", 9);
    /** Cat variant: white_cat (variant 10) */
    public static final CarcassDefinition WHITE_CAT = buildCatVariant("white_cat", 10);

    /** Map of cat variant ID -> CarcassDefinition for all 11 cat variants. */
    public static final Map<Integer, CarcassDefinition> CAT_VARIANTS = new java.util.HashMap<>(java.util.Map.ofEntries(
            java.util.Map.entry(0, ALL_BLACK_CAT),
            java.util.Map.entry(1, BLACK_CAT),
            java.util.Map.entry(2, BSHORTHAIR_CAT),
            java.util.Map.entry(3, CALICO_CAT),
            java.util.Map.entry(4, JELLIE_CAT),
            java.util.Map.entry(5, PERSIAN_CAT),
            java.util.Map.entry(6, RAGDOLL_CAT),
            java.util.Map.entry(7, RED_CAT),
            java.util.Map.entry(8, SIAMESE_CAT),
            java.util.Map.entry(9, TABBY_CAT),
            java.util.Map.entry(10, WHITE_CAT)
    ));

    private static void register(CarcassDefinition definition) {
        BY_ENTITY.put(definition.entityType(), definition);
        BY_MOB_ID.put(definition.mobId(), definition);
    }

    static {
        register(COW);
        register(PIG);
        register(SHEEP);
        register(CHICKEN);
        register(RABBIT);
        register(GOAT);
        register(FOX);
        register(WOLF);
        register(CAMEL);
        register(DONKEY);
        register(MULE);
        register(OCELOT);
        register(PANDA);
        register(POLAR_BEAR);
        register(HOGLIN);
        register(ZOGLIN);
        register(DOLPHIN);
        register(BAT);
        register(SILVERFISH);
        register(ENDERMITE);
        register(BEE);
        register(COD);
        register(SALMON);
        register(PHANTOM);
        register(SHULKER);
        register(GUARDIAN);
        register(ELDER_GUARDIAN);
        register(SKELETON_HORSE);
        register(ZOMBIE_HORSE);
        register(HORSE);
        register(BROWN_LLAMA);
        register(WHITE_LLAMA);
        register(CREAMY_LLAMA);
        register(GRAY_LLAMA);
        register(SQUID);
        register(GLOW_SQUID);
        register(CREEPER);
        register(SPIDER);
        register(CAVE_SPIDER);
        // Cat variants: only add to BY_MOB_ID (share EntityType with OCELOT)
        BY_MOB_ID.put(ALL_BLACK_CAT.mobId(), ALL_BLACK_CAT);
        BY_MOB_ID.put(BLACK_CAT.mobId(), BLACK_CAT);
        BY_MOB_ID.put(BSHORTHAIR_CAT.mobId(), BSHORTHAIR_CAT);
        BY_MOB_ID.put(CALICO_CAT.mobId(), CALICO_CAT);
        BY_MOB_ID.put(JELLIE_CAT.mobId(), JELLIE_CAT);
        BY_MOB_ID.put(PERSIAN_CAT.mobId(), PERSIAN_CAT);
        BY_MOB_ID.put(RAGDOLL_CAT.mobId(), RAGDOLL_CAT);
        BY_MOB_ID.put(RED_CAT.mobId(), RED_CAT);
        BY_MOB_ID.put(SIAMESE_CAT.mobId(), SIAMESE_CAT);
        BY_MOB_ID.put(TABBY_CAT.mobId(), TABBY_CAT);
        BY_MOB_ID.put(WHITE_CAT.mobId(), WHITE_CAT);
    }

    public static CarcassDefinition forEntityType(EntityType<?> entityType) {
        return BY_ENTITY.get(entityType);
    }

    public static CarcassDefinition forMobId(String mobId) {
        return mobId == null ? null : BY_MOB_ID.get(mobId);
    }

    private static VoxelShape box(double x1, double y1, double z1, double x2, double y2, double z2) {
        return net.minecraft.world.level.block.Block.box(x1, y1, z1, x2, y2, z2);
    }

    // Shapes below are ported 1:1 from the original Butchery cow blocks so the
    // generic blocks collide exactly like the per-mob classes they replace.

    private static VoxelShape hanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(2.1, -8.79363, 0.90637, 13.7, 8.60637, 11.40637);
            case EAST -> box(4.59363, -8.79363, 2.1, 15.09363, 8.60637, 13.7);
            case WEST -> box(0.90637, -8.79363, 2.3, 11.40637, 8.60637, 13.9);
            default -> box(2.3, -8.79363, 4.59363, 13.9, 8.60637, 15.09363);
        };
    }

    private static VoxelShape lying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(-0.35637, 0.2, 3.64363, 17.04363, 11.8, 14.14363);
            case EAST -> box(1.85637, 0.2, -0.35637, 12.35637, 11.8, 17.04363);
            case WEST -> box(3.64363, 0.2, -1.04363, 14.14363, 11.8, 16.35637);
            default -> box(-1.04363, 0.2, 1.85637, 16.35637, 11.8, 12.35637);
        };
    }

    private static VoxelShape cowHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(
                    box(5.97231, 2.73654, 7.6, 7.97231, 4.73654, 9.6),
                    box(3.97469, -0.01346, 5.00933, 11.97469, 7.98654, 11.00933),
                    box(11.97469, 5.98654, 7.00933, 12.97469, 8.98654, 8.00933),
                    box(2.97469, 5.98654, 7.00933, 3.97469, 8.98654, 8.00933));
            case EAST -> Shapes.or(
                    box(6.4, 2.73654, 5.97231, 8.4, 4.73654, 7.97231),
                    box(4.99067, -0.01346, 3.97469, 10.99067, 7.98654, 11.97469),
                    box(7.99067, 5.98654, 11.97469, 8.99067, 8.98654, 12.97469),
                    box(7.99067, 5.98654, 2.97469, 8.99067, 8.98654, 3.97469));
            case WEST -> Shapes.or(
                    box(7.6, 2.73654, 8.02769, 9.6, 4.73654, 10.02769),
                    box(5.00933, -0.01346, 4.02531, 11.00933, 7.98654, 12.02531),
                    box(7.00933, 5.98654, 3.02531, 8.00933, 8.98654, 4.02531),
                    box(7.00933, 5.98654, 12.02531, 8.00933, 8.98654, 13.02531));
            default -> Shapes.or(
                    box(8.02769, 2.73654, 6.4, 10.02769, 4.73654, 8.4),
                    box(4.02531, -0.01346, 4.99067, 12.02531, 7.98654, 10.99067),
                    box(3.02531, 5.98654, 7.99067, 4.02531, 8.98654, 8.99067),
                    box(12.02531, 5.98654, 7.99067, 13.02531, 8.98654, 8.99067));
        };
    }

    private static VoxelShape cowHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    // Shapes below are ported 1:1 from the original Butchery pig blocks so the
    // generic blocks collide exactly like the per-mob classes they replace.

    private static VoxelShape pigHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(3.02277, -1.02911, 0.79255, 12.82277, 14.77089, 8.59255);
            case EAST -> box(7.40745, -1.02911, 3.02277, 15.20745, 14.77089, 12.82277);
            case WEST -> box(0.79255, -1.02911, 3.17723, 8.59255, 14.77089, 12.97723);
            default -> box(3.17723, -1.02911, 7.40745, 12.97723, 14.77089, 15.20745);
        };
    }

    private static VoxelShape pigLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(0.1527, 0.10082, 5.00518, 15.9527, 9.90082, 12.80518);
            case EAST -> box(3.19482, 0.10082, 0.1527, 10.99482, 9.90082, 15.9527);
            case WEST -> box(5.00518, 0.10082, 0.0473, 12.80518, 9.90082, 15.8473);
            default -> box(0.0473, 0.10082, 3.19482, 15.8473, 9.90082, 10.99482);
        };
    }

    private static VoxelShape pigHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(
                    box(3.99777, 0.01651, 3.99301, 11.99777, 8.01651, 11.99301),
                    box(5.99777, 1.01651, 2.99301, 9.99777, 4.01651, 3.99301));
            case EAST -> Shapes.or(
                    box(4.00699, 0.01651, 3.99777, 12.00699, 8.01651, 11.99777),
                    box(12.00699, 1.01651, 5.99777, 13.00699, 4.01651, 9.99777));
            case WEST -> Shapes.or(
                    box(3.99301, 0.01651, 4.00223, 11.99301, 8.01651, 12.00223),
                    box(2.99301, 1.01651, 6.00223, 3.99301, 4.01651, 10.00223));
            default -> Shapes.or(
                    box(4.00223, 0.01651, 4.00699, 12.00223, 8.01651, 12.00699),
                    box(6.00223, 1.01651, 12.00699, 10.00223, 4.01651, 13.00699));
        };
    }

    private static VoxelShape pigHeadMount(BlockState state) {
        // The original PigHeadMountBlock uses the identical mount box as the cow's.
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    // Shapes below are ported 1:1 from the original Butchery sheep blocks so
    // the generic blocks collide exactly like the per-mob classes they replace.
    // The sheep's carcass/skeleton use the exact same boxes as the pig's, so
    // those definitions reuse pigHanging/pigLying directly.

    private static VoxelShape sheepHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(5.0, 0.0, 4.0, 11.0, 6.0, 12.0);
            case EAST -> box(4.0, 0.0, 5.0, 12.0, 6.0, 11.0);
            case WEST -> box(4.0, 0.0, 5.0, 12.0, 6.0, 11.0);
            default -> box(5.0, 0.0, 4.0, 11.0, 6.0, 12.0);
        };
    }

    private static VoxelShape sheepHeadMount(BlockState state) {
        // The original SheepHeadMountBlock uses the same mount box as cow/pig.
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    private static CarcassDefinition buildSheep() {
        return new CarcassDefinition(
                "sheep",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:sheep")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // The sheep carcass uses the same boxes as the pig's: blockstate 1
                // is hanging, 0 is the relocatable lying pose.
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? pigHanging(state) : pigLying(state),
                Carcasses::pigLying,
                Carcasses::sheepHead,
                Carcasses::sheepHeadMount,
                // Skeleton: blockstate 1 hung, 0 lying, same boxes as the fresh carcass.
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? pigHanging(state) : pigLying(state),
                // The carcass replaces the sheep's vanilla mutton drop.
                java.util.List.of(net.minecraft.world.item.Items.MUTTON));
    }

    private static CarcassDefinition buildPig() {
        return new CarcassDefinition(
                "pig",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:pig")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // Fresh carcass: blockstate 1 is the "hung to bleed" pose, 0 is the relocatable lying pose.
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? pigHanging(state) : pigLying(state),
                // Drained carcass only ever uses cut stages {0,6,7,8,9}; all of those are the lying pose.
                Carcasses::pigLying,
                Carcasses::pigHead,
                Carcasses::pigHeadMount,
                // Skeleton: blockstate 1 hung, 0 lying, same boxes as the fresh pig carcass.
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? pigHanging(state) : pigLying(state),
                // The original sweeps the pig's vanilla porkchop drop; the carcass replaces it.
                java.util.List.of(net.minecraft.world.item.Items.PORKCHOP));
    }

    private static CarcassDefinition buildCow() {
        return new CarcassDefinition(
                "cow",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:cow")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // Fresh carcass: blockstate 1 is the "hung to bleed" pose, 0 is the relocatable lying pose.
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? hanging(state) : lying(state),
                // Drained carcass only ever uses cut stages {0,6,7,8,9}; all of those are the lying pose.
                Carcasses::lying,
                Carcasses::cowHead,
                Carcasses::cowHeadMount,
                // Skeleton: blockstate 1 hung, 0 lying, same boxes as the fresh carcass.
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? hanging(state) : lying(state),
                // The carcass replaces the vanilla beef/leather drops, which are swept on death.
                java.util.List.of(net.minecraft.world.item.Items.BEEF, net.minecraft.world.item.Items.LEATHER));
    }

    // Shapes below are ported 1:1 from the original Butchery chicken blocks.
    private static VoxelShape chickenHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(5.0, 6.64605, 6.64605, 11.0, 14.64605, 12.64605);
            case EAST -> box(3.35395, 6.64605, 5.0, 9.35395, 14.64605, 11.0);
            case WEST -> box(6.64605, 6.64605, 5.0, 12.64605, 14.64605, 11.0);
            default -> box(5.0, 6.64605, 3.35395, 11.0, 14.64605, 9.35395);
        };
    }

    private static VoxelShape chickenLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(5.0, 0.0, 2.0, 11.0, 6.0, 10.0);
            case EAST -> box(6.0, 0.0, 5.0, 14.0, 6.0, 11.0);
            case WEST -> box(2.0, 0.0, 5.0, 10.0, 6.0, 11.0);
            default -> box(5.0, 0.0, 6.0, 11.0, 6.0, 14.0);
        };
    }

    private static VoxelShape chickenHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(
                    box(6.0, 0.0, 6.0, 10.0, 6.0, 9.0),
                    box(6.0, 2.0, 4.0, 10.0, 4.0, 6.0),
                    box(7.0, 0.0, 5.0, 9.0, 2.0, 7.0));
            case EAST -> Shapes.or(
                    box(7.0, 0.0, 6.0, 10.0, 6.0, 10.0),
                    box(10.0, 2.0, 6.0, 12.0, 4.0, 10.0),
                    box(9.0, 0.0, 7.0, 11.0, 2.0, 9.0));
            case WEST -> Shapes.or(
                    box(6.0, 0.0, 6.0, 9.0, 6.0, 10.0),
                    box(4.0, 2.0, 6.0, 6.0, 4.0, 10.0),
                    box(5.0, 0.0, 7.0, 7.0, 2.0, 9.0));
            default -> Shapes.or(
                    box(6.0, 0.0, 7.0, 10.0, 6.0, 10.0),
                    box(6.0, 2.0, 10.0, 10.0, 4.0, 12.0),
                    box(7.0, 0.0, 9.0, 9.0, 2.0, 11.0));
        };
    }

    private static VoxelShape chickenHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    private static VoxelShape chickenSkeletonHanging(BlockState state) {
        return chickenHanging(state);
    }

    private static VoxelShape chickenSkeletonLying(BlockState state) {
        return chickenLying(state);
    }

    private static CarcassDefinition buildChicken() {
        return new CarcassDefinition(
                "chicken",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:chicken")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // Fresh carcass: blockstate 1 is the "hung to bleed" pose, 0 is the relocatable lying pose.
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? chickenHanging(state) : chickenLying(state),
                // Drained carcass only ever uses cut stages {0,6,7,8,9}; all of those are the lying pose.
                Carcasses::chickenLying,
                Carcasses::chickenHead,
                Carcasses::chickenHeadMount,
                // Skeleton: blockstate 1 hung, 0 lying, same boxes as the fresh carcass.
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? chickenSkeletonHanging(state) : chickenSkeletonLying(state),
                // The carcass replaces the chicken's vanilla drops (chicken, feather).
                java.util.List.of(net.minecraft.world.item.Items.CHICKEN, net.minecraft.world.item.Items.FEATHER));
    }

    // Shapes below are ported 1:1 from the original Butchery rabbit blocks.
    private static VoxelShape rabbitHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(4.38211, 2.25, 5.33211, 10.38211, 12.25, 10.33211);
            case EAST -> box(5.66789, 2.25, 4.38211, 10.66789, 12.25, 10.38211);
            case WEST -> box(5.33211, 2.25, 5.61789, 10.33211, 12.25, 11.61789);
            default -> box(5.61789, 2.25, 5.66789, 11.61789, 12.25, 10.66789);
        };
    }

    private static VoxelShape rabbitLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(4.83211, 1.0, 4.16789, 14.83211, 6.0, 10.16789);
            case EAST -> box(5.83211, 1.0, 4.83211, 11.83211, 6.0, 14.83211);
            case WEST -> box(4.16789, 1.0, 1.16789, 10.16789, 6.0, 11.16789);
            default -> box(1.16789, 1.0, 5.83211, 11.16789, 6.0, 11.83211);
        };
    }

    private static VoxelShape rabbitHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(
                    box(5.45558, -0.01078, 5.79289, 10.45558, 3.98922, 10.79289),
                    box(7.45558, 1.48922, 5.29289, 8.45558, 2.48922, 6.29289),
                    box(5.45558, 3.98922, 9.54289, 7.45558, 8.98922, 10.54289),
                    box(8.45558, 3.98922, 9.54289, 10.45558, 8.98922, 10.54289));
            case EAST -> Shapes.or(
                    box(5.20711, -0.01078, 5.45558, 10.20711, 3.98922, 10.45558),
                    box(9.70711, 1.48922, 7.45558, 10.70711, 2.48922, 8.45558),
                    box(5.45711, 3.98922, 5.45558, 6.45711, 8.98922, 7.45558),
                    box(5.45711, 3.98922, 8.45558, 6.45711, 8.98922, 10.45558));
            case WEST -> Shapes.or(
                    box(5.79289, -0.01078, 5.54442, 10.79289, 3.98922, 10.54442),
                    box(5.29289, 1.48922, 7.54442, 6.29289, 2.48922, 8.54442),
                    box(9.54289, 3.98922, 8.54442, 10.54289, 8.98922, 10.54442),
                    box(9.54289, 3.98922, 5.54442, 10.54289, 8.98922, 7.54442));
            default -> Shapes.or(
                    box(5.54442, -0.01078, 5.20711, 10.54442, 3.98922, 10.20711),
                    box(7.54442, 1.48922, 9.70711, 8.54442, 2.48922, 10.70711),
                    box(8.54442, 3.98922, 5.45711, 10.54442, 8.98922, 6.45711),
                    box(5.54442, 3.98922, 5.45711, 7.54442, 8.98922, 6.45711));
        };
    }

    private static VoxelShape rabbitHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    private static VoxelShape rabbitSkeletonHanging(BlockState state) {
        return rabbitHanging(state);
    }

    private static VoxelShape rabbitSkeletonLying(BlockState state) {
        return rabbitLying(state);
    }

    private static CarcassDefinition buildRabbit() {
        return new CarcassDefinition(
                "rabbit",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:rabbit")))
                        .value(),
                true,
                true,
                false,
                true,
                3,
                // Fresh carcass: blockstate 1 is the "hung to bleed" pose, 0 is the relocatable lying pose.
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? rabbitHanging(state) : rabbitLying(state),
                // Drained carcass only ever uses cut stages {0,6,7,8,9}; all of those are the lying pose.
                Carcasses::rabbitLying,
                Carcasses::rabbitHead,
                Carcasses::rabbitHeadMount,
                // Skeleton: blockstate 1 hung, 0 lying, same boxes as the fresh carcass.
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? rabbitSkeletonHanging(state) : rabbitSkeletonLying(state),
                // The carcass replaces the rabbit's vanilla drops (rabbit, rabbit_hide, rabbit_foot).
                java.util.List.of(net.minecraft.world.item.Items.RABBIT, net.minecraft.world.item.Items.RABBIT_HIDE, net.minecraft.world.item.Items.RABBIT_FOOT));
    }

    // Shapes below are ported 1:1 from the original Butchery goat blocks.
    private static VoxelShape goatHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(4.125, -2.1175, 0.8319, 11.825, 12.7075, 10.6319);
            case EAST -> box(5.3681, -2.1175, 4.125, 15.1681, 12.7075, 11.825);
            case WEST -> box(0.8319, -2.1175, 4.175, 10.6319, 12.7075, 11.875);
            default -> box(4.175, -2.1175, 5.3681, 11.875, 12.7075, 15.1681);
        };
    }

    private static VoxelShape goatLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(-0.48566, 2.6, 4.13506, 14.33934, 12.4, 11.83506);
            case EAST -> box(4.16494, 2.6, -0.48566, 11.86494, 12.4, 14.33934);
            case WEST -> box(4.13506, 2.6, 1.66066, 11.83506, 12.4, 16.48566);
            default -> box(1.66066, 2.6, 4.16494, 16.48566, 12.4, 11.86494);
        };
    }

    // Goat head/head_mount/skeleton use sheep shapes (similar size)
    private static VoxelShape goatHead(BlockState state) {
        return Carcasses.sheepHead(state);
    }

    private static VoxelShape goatHeadMount(BlockState state) {
        return Carcasses.sheepHeadMount(state);
    }

    private static VoxelShape goatSkeletonHanging(BlockState state) {
        return goatHanging(state);
    }

    private static VoxelShape goatSkeletonLying(BlockState state) {
        return goatLying(state);
    }

    private static CarcassDefinition buildGoat() {
        return new CarcassDefinition(
                "goat",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:goat")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? goatHanging(state) : goatLying(state),
                Carcasses::goatLying,
                Carcasses::goatHead,
                Carcasses::goatHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? goatSkeletonHanging(state) : goatSkeletonLying(state),
                // Goat drops mutton like sheep
                java.util.List.of(net.minecraft.world.item.Items.MUTTON));
    }

    // Shapes below are ported 1:1 from the original Butchery fox blocks.
    private static VoxelShape foxHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(4.55045, 6.449, 9.10146, 10.55045, 17.449, 15.10146);
            case EAST -> box(0.89854, 6.449, 4.55045, 6.89854, 17.449, 10.55045);
            case WEST -> box(9.10146, 6.449, 5.44955, 15.10146, 17.449, 11.44955);
            default -> box(5.44955, 6.449, 0.89854, 11.44955, 17.449, 6.89854);
        };
    }

    private static VoxelShape foxLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.5, 2.0, 6.0, 12.5, 8.0, 12.0);
            case EAST -> box(4.0, 2.0, 1.5, 10.0, 8.0, 12.5);
            case WEST -> box(6.0, 2.0, 3.5, 12.0, 8.0, 14.5);
            default -> box(3.5, 2.0, 4.0, 14.5, 8.0, 10.0);
        };
    }

    private static VoxelShape foxHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(
                    box(4.0, 0.0, 4.0, 12.0, 6.0, 12.0),
                    box(5.0, 2.0, 3.0, 11.0, 4.0, 5.0));
            case EAST -> Shapes.or(
                    box(4.0, 0.0, 4.0, 12.0, 6.0, 12.0),
                    box(11.0, 2.0, 5.0, 13.0, 4.0, 11.0));
            case WEST -> Shapes.or(
                    box(4.0, 0.0, 4.0, 12.0, 6.0, 12.0),
                    box(3.0, 2.0, 3.0, 5.0, 4.0, 11.0));
            default -> Shapes.or(
                    box(4.0, 0.0, 4.0, 12.0, 6.0, 12.0),
                    box(5.0, 2.0, 11.0, 11.0, 4.0, 13.0));
        };
    }

    private static VoxelShape foxHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    private static VoxelShape foxSkeletonHanging(BlockState state) {
        return foxHanging(state);
    }

    private static VoxelShape foxSkeletonLying(BlockState state) {
        return foxLying(state);
    }

    private static CarcassDefinition buildFox() {
        return new CarcassDefinition(
                "fox",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:fox")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? foxHanging(state) : foxLying(state),
                Carcasses::foxLying,
                Carcasses::foxHead,
                Carcasses::foxHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? foxSkeletonHanging(state) : foxSkeletonLying(state),
                // Fox drops raw_fox_meat
                java.util.List.of(net.minecraft.world.item.Items.SWEET_BERRIES)); // Fox doesn't have a vanilla meat drop, using sweet berries as placeholder
    }

    // Shapes below are ported 1:1 from the original Butchery wolf blocks.
    private static VoxelShape wolfHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(5.20375, -1.24876, 2.64749, 10.80375, 13.35124, 8.24749);
            case EAST -> box(7.75251, -1.24876, 5.20375, 13.35251, 13.35124, 10.80375);
            case WEST -> box(2.64749, -1.24876, 5.19625, 8.24749, 13.35124, 10.79625);
            default -> box(5.19625, -1.24876, 7.75251, 10.79625, 13.35124, 13.35251);
        };
    }

    private static VoxelShape wolfLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(-0.8, 2.14749, 4.64749, 13.8, 7.74749, 10.24749);
            case EAST -> box(5.75251, 2.14749, -0.8, 11.35251, 7.74749, 13.8);
            case WEST -> box(4.64749, 2.14749, 2.2, 10.24749, 7.74749, 16.8);
            default -> box(2.2, 2.14749, 5.75251, 16.8, 7.74749, 11.35251);
        };
    }

    private static VoxelShape wolfHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(
                    box(4.0, 0.0, 4.0, 12.0, 6.0, 12.0),
                    box(5.0, 2.0, 3.0, 11.0, 4.0, 5.0));
            case EAST -> Shapes.or(
                    box(4.0, 0.0, 4.0, 12.0, 6.0, 12.0),
                    box(11.0, 2.0, 5.0, 13.0, 4.0, 11.0));
            case WEST -> Shapes.or(
                    box(4.0, 0.0, 4.0, 12.0, 6.0, 12.0),
                    box(3.0, 2.0, 3.0, 5.0, 4.0, 11.0));
            default -> Shapes.or(
                    box(4.0, 0.0, 4.0, 12.0, 6.0, 12.0),
                    box(5.0, 2.0, 11.0, 11.0, 4.0, 13.0));
        };
    }

    private static VoxelShape wolfHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    private static VoxelShape wolfSkeletonHanging(BlockState state) {
        return wolfHanging(state);
    }

    private static VoxelShape wolfSkeletonLying(BlockState state) {
        return wolfLying(state);
    }

    private static CarcassDefinition buildWolf() {
        return new CarcassDefinition(
                "wolf",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:wolf")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? wolfHanging(state) : wolfLying(state),
                Carcasses::wolfLying,
                Carcasses::wolfHead,
                Carcasses::wolfHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? wolfSkeletonHanging(state) : wolfSkeletonLying(state),
                // Wolf drops wolf_pelt
                java.util.List.of(net.minecraft.world.item.Items.BONE));
    }

    // ===== NEW MOBS (camel through dolphin) =====

    // Shapes below are ported 1:1 from the original Butchery camel blocks.
    private static VoxelShape camelHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(1.0, -6.0, 9.0, 14.0, 3.0, 17.0), box(1.0, 3.0, 5.0, 14.0, 12.0, 13.0), box(1.0, 12.0, 5.0, 14.0, 17.0, 11.0));
            case EAST -> Shapes.or(box(-1.0, -6.0, 1.0, 7.0, 3.0, 14.0), box(3.0, 3.0, 1.0, 11.0, 12.0, 14.0), box(5.0, 12.0, 1.0, 11.0, 17.0, 14.0));
            default -> Shapes.or(box(2.0, -6.0, -1.0, 15.0, 3.0, 7.0), box(2.0, 3.0, 3.0, 15.0, 12.0, 11.0), box(2.0, 12.0, 5.0, 15.0, 17.0, 11.0));
            case WEST -> Shapes.or(box(9.0, -6.0, 2.0, 17.0, 3.0, 15.0), box(5.0, 3.0, 2.0, 13.0, 12.0, 15.0), box(5.0, 12.0, 2.0, 11.0, 17.0, 15.0));
        };
    }

    private static VoxelShape camelLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(-0.35637, 0.2, 3.64363, 17.04363, 11.8, 14.14363);
            case EAST -> box(1.85637, 0.2, -0.35637, 12.35637, 11.8, 17.04363);
            default -> box(-1.04363, 0.2, 1.85637, 16.35637, 11.8, 12.35637);
            case WEST -> box(3.64363, 0.2, -1.04363, 14.14363, 11.8, 16.35637);
        };
    }

    private static VoxelShape camelHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(5.5, 9.0, 1.0, 10.5, 14.0, 7.0), box(4.5, 0.0, 7.0, 11.5, 14.0, 14.0));
            case EAST -> Shapes.or(box(9.0, 9.0, 5.5, 15.0, 14.0, 10.5), box(2.0, 0.0, 4.5, 9.0, 14.0, 11.5));
            default -> Shapes.or(box(5.5, 9.0, 9.0, 10.5, 14.0, 15.0), box(4.5, 0.0, 2.0, 11.5, 14.0, 9.0));
            case WEST -> Shapes.or(box(1.0, 9.0, 5.5, 7.0, 14.0, 10.5), box(7.0, 0.0, 4.5, 14.0, 14.0, 11.5));
        };
    }

    private static VoxelShape camelHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(0.0, -5.0, 13.0, 16.0, 15.0, 16.0);
            case EAST -> box(0.0, -5.0, 0.0, 3.0, 15.0, 16.0);
            default -> box(0.0, -5.0, 0.0, 16.0, 15.0, 3.0);
            case WEST -> box(13.0, -5.0, 0.0, 16.0, 15.0, 16.0);
        };
    }

    private static VoxelShape camelSkeletonHanging(BlockState state) {
        return camelHanging(state);
    }

    private static VoxelShape camelSkeletonLying(BlockState state) {
        return camelLying(state);
    }

    private static CarcassDefinition buildCAMEL() {
        return new CarcassDefinition(
                "camel",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:camel")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? camelHanging(state) : camelLying(state),
                Carcasses::camelLying,
                Carcasses::camelHead,
                Carcasses::camelHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? camelSkeletonHanging(state) : camelSkeletonLying(state),
                // camel drops raw_camel_meat
                java.util.List.of(net.minecraft.world.item.Items.BONE));
    }

    // Shapes below are ported 1:1 from the original Butchery donkey blocks.
    private static VoxelShape donkeyHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(5.0, -2.0, 8.0, 13.0, 5.0, 14.0), box(5.0, 5.0, 5.0, 13.0, 11.0, 11.0), box(5.0, 11.0, 2.0, 13.0, 16.0, 10.0));
            case EAST -> Shapes.or(box(2.0, -2.0, 5.0, 8.0, 5.0, 13.0), box(5.0, 5.0, 5.0, 11.0, 11.0, 13.0), box(6.0, 11.0, 5.0, 14.0, 16.0, 13.0));
            default -> Shapes.or(box(3.0, -2.0, 2.0, 11.0, 5.0, 8.0), box(3.0, 5.0, 5.0, 11.0, 11.0, 11.0), box(3.0, 11.0, 6.0, 11.0, 16.0, 14.0));
            case WEST -> Shapes.or(box(8.0, -2.0, 3.0, 14.0, 5.0, 11.0), box(5.0, 5.0, 3.0, 11.0, 11.0, 11.0), box(2.0, 11.0, 3.0, 10.0, 16.0, 11.0));
        };
    }

    private static VoxelShape donkeyLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(-4.82665, 3.95, 3.17335, 17.27335, 14.05, 13.27335);
            case EAST -> box(2.72665, 3.95, -4.82665, 12.82665, 14.05, 17.27335);
            default -> box(-1.27335, 3.95, 2.72665, 20.82665, 14.05, 12.82665);
            case WEST -> box(3.17335, 3.95, -1.27335, 13.27335, 14.05, 20.82665);
        };
    }

    private static VoxelShape donkeyHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(5.0, -0.00768, 6.99712, 11.0, 4.99232, 13.99712), box(6.0, -0.00768, 1.99712, 10.0, 4.99232, 6.99712), box(8.55, 4.99232, 12.98712, 10.55, 7.99232, 13.98712), box(5.45, 4.99232, 12.98712, 7.45, 7.99232, 13.98712));
            case EAST -> Shapes.or(box(2.00288, -0.00768, 5.0, 9.00288, 4.99232, 11.0), box(9.00288, -0.00768, 6.0, 14.00288, 4.99232, 10.0), box(2.01288, 4.99232, 8.55, 3.01288, 7.99232, 10.55), box(2.01288, 4.99232, 5.45, 3.01288, 7.99232, 7.45));
            default -> Shapes.or(box(5.0, -0.00768, 2.00288, 11.0, 4.99232, 9.00288), box(6.0, -0.00768, 9.00288, 10.0, 4.99232, 14.00288), box(5.45, 4.99232, 2.01288, 7.45, 7.99232, 3.01288), box(8.55, 4.99232, 2.01288, 10.55, 7.99232, 3.01288));
            case WEST -> Shapes.or(box(6.99712, -0.00768, 5.0, 13.99712, 4.99232, 11.0), box(1.99712, -0.00768, 6.0, 6.99712, 4.99232, 10.0), box(12.98712, 4.99232, 5.45, 13.98712, 7.99232, 7.45), box(12.98712, 4.99232, 8.55, 13.98712, 7.99232, 10.55));
        };
    }

    private static VoxelShape donkeyHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
        };
    }

    private static VoxelShape donkeySkeletonHanging(BlockState state) {
        return donkeyHanging(state);
    }

    private static VoxelShape donkeySkeletonLying(BlockState state) {
        return donkeyLying(state);
    }

private static CarcassDefinition buildDONKEY() {
        return new CarcassDefinition(
                "donkey",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:donkey")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? donkeyHanging(state) : donkeyLying(state),
                Carcasses::donkeyLying,
                Carcasses::donkeyHead,
                Carcasses::donkeyHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? donkeySkeletonHanging(state) : donkeySkeletonLying(state),
                // donkey drops raw_donkey_steak
                java.util.List.of(net.minecraft.world.item.Items.LEATHER));
    }

    // Shapes below are ported 1:1 from the original Butchery mule blocks.
    private static VoxelShape muleHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(5.0, -2.0, 8.0, 13.0, 5.0, 14.0), box(5.0, 5.0, 5.0, 13.0, 11.0, 11.0), box(5.0, 11.0, 2.0, 13.0, 16.0, 10.0));
            case EAST -> Shapes.or(box(2.0, -2.0, 5.0, 8.0, 5.0, 13.0), box(5.0, 5.0, 5.0, 11.0, 11.0, 13.0), box(6.0, 11.0, 5.0, 14.0, 16.0, 13.0));
            default -> Shapes.or(box(3.0, -2.0, 2.0, 11.0, 5.0, 8.0), box(3.0, 5.0, 5.0, 11.0, 11.0, 11.0), box(3.0, 11.0, 6.0, 11.0, 16.0, 14.0));
            case WEST -> Shapes.or(box(8.0, -2.0, 3.0, 14.0, 5.0, 11.0), box(5.0, 5.0, 3.0, 11.0, 11.0, 11.0), box(2.0, 11.0, 3.0, 10.0, 16.0, 11.0));
        };
    }

    private static VoxelShape muleLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(-4.82665, 3.95, 3.17335, 17.27335, 14.05, 13.27335);
            case EAST -> box(2.72665, 3.95, -4.82665, 12.82665, 14.05, 17.27335);
            default -> box(-1.27335, 3.95, 2.72665, 20.82665, 14.05, 12.82665);
            case WEST -> box(3.17335, 3.95, -1.27335, 13.27335, 14.05, 20.82665);
        };
    }

    private static VoxelShape muleHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(5.0, -0.00768, 6.99712, 11.0, 4.99232, 13.99712), box(6.0, -0.00768, 1.99712, 10.0, 4.99232, 6.99712), box(8.55, 4.99232, 12.98712, 10.55, 7.99232, 13.98712), box(5.45, 4.99232, 12.98712, 7.45, 7.99232, 13.98712));
            case EAST -> Shapes.or(box(2.00288, -0.00768, 5.0, 9.00288, 4.99232, 11.0), box(9.00288, -0.00768, 6.0, 14.00288, 4.99232, 10.0), box(2.01288, 4.99232, 8.55, 3.01288, 7.99232, 10.55), box(2.01288, 4.99232, 5.45, 3.01288, 7.99232, 7.45));
            default -> Shapes.or(box(5.0, -0.00768, 2.00288, 11.0, 4.99232, 9.00288), box(6.0, -0.00768, 9.00288, 10.0, 4.99232, 14.00288), box(5.45, 4.99232, 2.01288, 7.45, 7.99232, 3.01288), box(8.55, 4.99232, 2.01288, 10.55, 7.99232, 3.01288));
            case WEST -> Shapes.or(box(6.99712, -0.00768, 5.0, 13.99712, 4.99232, 11.0), box(1.99712, -0.00768, 6.0, 6.99712, 4.99232, 10.0), box(12.98712, 4.99232, 5.45, 13.98712, 7.99232, 7.45), box(12.98712, 4.99232, 8.55, 13.98712, 7.99232, 10.55));
        };
    }

    private static VoxelShape muleHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
        };
    }

    private static VoxelShape muleSkeletonHanging(BlockState state) {
        return muleHanging(state);
    }

    private static VoxelShape muleSkeletonLying(BlockState state) {
        return muleLying(state);
    }

    private static CarcassDefinition buildMULE() {
        return new CarcassDefinition(
                "mule",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:mule")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? muleHanging(state) : muleLying(state),
                Carcasses::muleLying,
                Carcasses::muleHead,
                Carcasses::muleHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? muleSkeletonHanging(state) : muleSkeletonLying(state),
                // mule drops raw_mule_steak
                java.util.List.of(net.minecraft.world.item.Items.LEATHER));
    }

    // Shapes below are ported 1:1 from the original Butchery ocelot blocks.
    private static VoxelShape ocelotHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(6.3, 5.0, 9.0, 9.8, 9.0, 13.0), box(6.3, 9.0, 7.0, 9.8, 13.0, 11.0), box(6.3, 13.0, 6.0, 9.8, 17.0, 10.0));
            case EAST -> Shapes.or(box(3.0, 5.0, 6.3, 7.0, 9.0, 9.8), box(5.0, 9.0, 6.3, 9.0, 13.0, 9.8), box(6.0, 13.0, 6.3, 10.0, 17.0, 9.8));
            default -> Shapes.or(box(6.2, 5.0, 3.0, 9.7, 9.0, 7.0), box(6.2, 9.0, 5.0, 9.7, 13.0, 9.0), box(6.2, 13.0, 6.0, 9.7, 17.0, 10.0));
            case WEST -> Shapes.or(box(9.0, 5.0, 6.2, 13.0, 9.0, 9.7), box(7.0, 9.0, 6.2, 11.0, 13.0, 9.7), box(6.0, 13.0, 6.2, 10.0, 17.0, 9.7));
        };
    }

    private static VoxelShape ocelotLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(-0.125, 0.0, 7.875, 15.875, 4.0, 13.875);
            case EAST -> box(2.125, 0.0, -0.125, 8.125, 4.0, 15.875);
            default -> box(0.125, 0.0, 2.125, 16.125, 4.0, 8.125);
            case WEST -> box(7.875, 0.0, 0.125, 13.875, 4.0, 16.125);
        };
    }

    private static VoxelShape ocelotHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(5.5, 0.0, 6.0, 10.5, 4.0, 11.0), box(6.5, 0.02, 5.0, 9.5, 2.02, 7.0), box(9.0, 4.0, 9.0, 10.0, 5.0, 11.0), box(6.0, 4.0, 9.0, 7.0, 5.0, 11.0));
            case EAST -> Shapes.or(box(5.0, 0.0, 5.5, 10.0, 4.0, 10.5), box(9.0, 0.02, 6.5, 11.0, 2.02, 9.5), box(5.0, 4.0, 9.0, 7.0, 5.0, 10.0), box(5.0, 4.0, 6.0, 7.0, 5.0, 7.0));
            default -> Shapes.or(box(5.5, 0.0, 5.0, 10.5, 4.0, 10.0), box(6.5, 0.02, 9.0, 9.5, 2.02, 11.0), box(6.0, 4.0, 5.0, 7.0, 5.0, 7.0), box(9.0, 4.0, 5.0, 10.0, 5.0, 7.0));
            case WEST -> Shapes.or(box(6.0, 0.0, 5.5, 11.0, 4.0, 10.5), box(5.0, 0.02, 6.5, 7.0, 2.02, 9.5), box(9.0, 4.0, 6.0, 11.0, 5.0, 7.0), box(9.0, 4.0, 9.0, 11.0, 5.0, 10.0));
        };
    }

    private static VoxelShape ocelotHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
        };
    }

    private static VoxelShape ocelotSkeletonHanging(BlockState state) {
        return ocelotHanging(state);
    }

    private static VoxelShape ocelotSkeletonLying(BlockState state) {
        return ocelotLying(state);
    }

    private static CarcassDefinition buildOCELOT() {
        return new CarcassDefinition(
                "ocelot",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:ocelot")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? ocelotHanging(state) : ocelotLying(state),
                Carcasses::ocelotLying,
                Carcasses::ocelotHead,
                Carcasses::ocelotHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? ocelotSkeletonHanging(state) : ocelotSkeletonLying(state),
                // ocelot drops raw_ocelot_meat
                java.util.List.of(net.minecraft.world.item.Items.BONE));
    }

    // Shapes below are ported 1:1 from the original Butchery panda blocks.
    private static VoxelShape pandaHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(0.0, -3.0, 6.0, 17.0, 8.0, 14.0), box(0.0, 8.0, 1.0, 17.0, 18.0, 10.0));
            case EAST -> Shapes.or(box(2.0, -3.0, 0.0, 10.0, 8.0, 17.0), box(6.0, 8.0, 0.0, 15.0, 18.0, 17.0));
            default -> Shapes.or(box(-1.0, -3.0, 2.0, 16.0, 8.0, 10.0), box(-1.0, 8.0, 6.0, 16.0, 18.0, 15.0));
            case WEST -> Shapes.or(box(6.0, -3.0, -1.0, 14.0, 8.0, 16.0), box(1.0, 8.0, -1.0, 10.0, 18.0, 16.0));
        };
    }

    private static VoxelShape pandaLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(-4.44525, 0.03068, 1.026, 21.55475, 19.03068, 14.026);
            case EAST -> box(1.974, 0.03068, -4.44525, 14.974, 19.03068, 21.55475);
            default -> box(-5.55475, 0.03068, 1.974, 20.44525, 19.03068, 14.974);
            case WEST -> box(1.026, 0.03068, -5.55475, 14.026, 19.03068, 20.44525);
        };
    }

    private static VoxelShape pandaHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(1.4779, 0.04591, 4.43691, 14.4779, 10.04591, 13.43691), box(4.4779, 0.04591, 2.43691, 11.4779, 5.04591, 4.43691), box(11.4779, 8.09311, 7.43691, 16.4779, 12.09311, 8.43691), box(-0.5221, 8.09311, 7.43691, 4.4779, 12.09311, 8.43691));
            case EAST -> Shapes.or(box(2.56309, 0.04591, 1.4779, 11.56309, 10.04591, 14.4779), box(11.56309, 0.04591, 4.4779, 13.56309, 5.04591, 11.4779), box(7.56309, 8.09311, 11.4779, 8.56309, 12.09311, 16.4779), box(7.56309, 8.09311, -0.5221, 8.56309, 12.09311, 4.4779));
            default -> Shapes.or(box(1.5221, 0.04591, 2.56309, 14.5221, 10.04591, 11.56309), box(4.5221, 0.04591, 11.56309, 11.5221, 5.04591, 13.56309), box(-0.4779, 8.09311, 7.56309, 4.5221, 12.09311, 8.56309), box(11.5221, 8.09311, 7.56309, 16.5221, 12.09311, 8.56309));
            case WEST -> Shapes.or(box(4.43691, 0.04591, 1.5221, 13.43691, 10.04591, 14.5221), box(2.43691, 0.04591, 4.5221, 4.43691, 5.04591, 11.5221), box(7.43691, 8.09311, -0.4779, 8.43691, 12.09311, 4.5221), box(7.43691, 8.09311, 11.5221, 8.43691, 12.09311, 16.5221));
        };
    }

    private static VoxelShape pandaHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(0.0, -5.0, 13.0, 16.0, 15.0, 16.0);
            case EAST -> box(0.0, -5.0, 0.0, 3.0, 15.0, 16.0);
            default -> box(0.0, -5.0, 0.0, 16.0, 15.0, 3.0);
            case WEST -> box(13.0, -5.0, 0.0, 16.0, 15.0, 16.0);
        };
    }

    private static VoxelShape pandaSkeletonHanging(BlockState state) {
        return pandaHanging(state);
    }

    private static VoxelShape pandaSkeletonLying(BlockState state) {
        return pandaLying(state);
    }

    private static CarcassDefinition buildPANDA() {
        return new CarcassDefinition(
                "panda",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:panda")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? pandaHanging(state) : pandaLying(state),
                Carcasses::pandaLying,
                Carcasses::pandaHead,
                Carcasses::pandaHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? pandaSkeletonHanging(state) : pandaSkeletonLying(state),
                // panda drops raw_panda_steak
                java.util.List.of(net.minecraft.world.item.Items.BAMBOO));
    }

    // Shapes below are ported 1:1 from the original Butchery polar_bear blocks.
    private static VoxelShape polar_bearHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(1.5, -2.9, 2.4, 14.5, 10.1, 12.4), box(2.4, 13.07183, 1.75293, 13.4, 17.57183, 8.75293), box(2.4, 10.07183, 2.75293, 13.4, 13.17183, 11.15293));
            case EAST -> Shapes.or(box(3.6, -2.9, 1.5, 13.6, 10.1, 14.5), box(7.24707, 13.07183, 2.4, 14.24707, 17.57183, 13.4), box(4.84707, 10.07183, 2.4, 13.24707, 13.17183, 13.4));
            default -> Shapes.or(box(1.5, -2.9, 3.6, 14.5, 10.1, 13.6), box(2.6, 13.07183, 7.24707, 13.6, 17.57183, 14.24707), box(2.6, 10.07183, 4.84707, 13.6, 13.17183, 13.24707));
            case WEST -> Shapes.or(box(2.4, -2.9, 1.5, 12.4, 10.1, 14.5), box(1.75293, 13.07183, 2.6, 8.75293, 17.57183, 13.6), box(2.75293, 10.07183, 2.6, 11.15293, 13.17183, 13.6));
        };
    }

    private static VoxelShape polar_bearLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(1.4, 0.3, 5.5, 14.4, 13.3, 15.5), box(2.4, 13.3, 5.5, 13.4, 21.3, 12.5));
            case EAST -> Shapes.or(box(0.5, 0.3, 1.4, 10.5, 13.3, 14.4), box(3.5, 13.3, 2.4, 10.5, 21.3, 13.4));
            default -> Shapes.or(box(1.6, 0.3, 0.5, 14.6, 13.3, 10.5), box(2.6, 13.3, 3.5, 13.6, 21.3, 10.5));
            case WEST -> Shapes.or(box(5.5, 0.3, 1.6, 15.5, 13.3, 14.6), box(5.5, 13.3, 2.6, 12.5, 21.3, 13.6));
        };
    }

    private static VoxelShape polar_bearHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(4.5, -0.003, 5.99253, 11.5, 6.997, 12.99253), box(5.5, -0.003, 2.99253, 10.5, 2.997, 5.99253), box(3.5, 5.997, 7.99253, 5.5, 7.997, 8.99253), box(10.5, 5.997, 7.99253, 12.5, 7.997, 8.99253));
            case EAST -> Shapes.or(box(3.00747, -0.003, 4.5, 10.00747, 6.997, 11.5), box(10.00747, -0.003, 5.5, 13.00747, 2.997, 10.5), box(7.00747, 5.997, 3.5, 8.00747, 7.997, 5.5), box(7.00747, 5.997, 10.5, 8.00747, 7.997, 12.5));
            default -> Shapes.or(box(4.5, -0.003, 3.00747, 11.5, 6.997, 10.00747), box(5.5, -0.003, 10.00747, 10.5, 2.997, 13.00747), box(10.5, 5.997, 7.00747, 12.5, 7.997, 8.00747), box(3.5, 5.997, 7.00747, 5.5, 7.997, 8.00747));
            case WEST -> Shapes.or(box(5.99253, -0.003, 4.5, 12.99253, 6.997, 11.5), box(2.99253, -0.003, 5.5, 5.99253, 2.997, 10.5), box(7.99253, 5.997, 10.5, 8.99253, 7.997, 12.5), box(7.99253, 5.997, 3.5, 8.99253, 7.997, 5.5));
        };
    }

    private static VoxelShape polar_bearHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
        };
    }

    private static VoxelShape polar_bearSkeletonHanging(BlockState state) {
        return polar_bearHanging(state);
    }

    private static VoxelShape polar_bearSkeletonLying(BlockState state) {
        return polar_bearLying(state);
    }

    private static CarcassDefinition buildPOLAR_BEAR() {
        return new CarcassDefinition(
                "polar_bear",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:polar_bear")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? polar_bearHanging(state) : polar_bearLying(state),
                Carcasses::polar_bearLying,
                Carcasses::polar_bearHead,
                Carcasses::polar_bearHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? polar_bearSkeletonHanging(state) : polar_bearSkeletonLying(state),
                // polar_bear drops raw_polar_bear_meat
                java.util.List.of(net.minecraft.world.item.Items.COD));
    }

    // Shapes below are ported 1:1 from the original Butchery hoglin blocks.
    private static VoxelShape hoglinHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(1.5, 10.0, -1.0, 15.5, 16.0, 10.0), box(1.5, 0.0, 3.0, 15.5, 10.0, 13.0));
            case EAST -> Shapes.or(box(6.0, 10.0, 1.5, 17.0, 16.0, 15.5), box(3.0, 0.0, 1.5, 13.0, 10.0, 15.5));
            default -> Shapes.or(box(0.5, 10.0, 6.0, 14.5, 16.0, 17.0), box(0.5, 0.0, 3.0, 14.5, 10.0, 13.0));
            case WEST -> Shapes.or(box(-1.0, 10.0, 0.5, 10.0, 16.0, 14.5), box(3.0, 0.0, 0.5, 13.0, 10.0, 14.5));
        };
    }

    private static VoxelShape hoglinLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(-10.70051, 5.0, -0.20051, 15.29949, 19.0, 15.79949);
            case EAST -> box(0.20051, 5.0, -10.70051, 16.20051, 19.0, 15.29949);
            default -> box(0.70051, 5.0, 0.20051, 26.70051, 19.0, 16.20051);
            case WEST -> box(-0.20051, 5.0, 0.70051, 15.79949, 19.0, 26.70051);
        };
    }

    private static VoxelShape hoglinHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(0.99313, 0.02963, -1.33089, 14.99313, 6.02963, 17.66911), box(13.99313, 0.62763, 4.53308, 15.99313, 11.62763, 6.53308), box(-0.00687, 0.62763, 4.53308, 1.99313, 11.62763, 6.53308), box(14.77385, 2.55075, 10.63761, 15.77385, 6.55075, 16.63761), box(0.21242, 2.42722, 10.94591, 1.21242, 6.42722, 16.94591));
            case EAST -> Shapes.or(box(-1.66911, 0.02963, 0.99313, 17.33089, 6.02963, 14.99313), box(9.46692, 0.62763, 13.99313, 11.46692, 11.62763, 15.99313), box(9.46692, 0.62763, -0.00687, 11.46692, 11.62763, 1.99313), box(-0.63761, 2.55075, 14.77385, 5.36239, 6.55075, 15.77385), box(-0.94591, 2.42722, 0.21242, 5.05409, 6.42722, 1.21242));
            default -> Shapes.or(box(1.00687, 0.02963, -1.66911, 15.00687, 6.02963, 17.33089), box(0.00687, 0.62763, 9.46692, 2.00687, 11.62763, 11.46692), box(14.00687, 0.62763, 9.46692, 16.00687, 11.62763, 11.46692), box(0.22615, 2.55075, -0.63761, 1.22615, 6.55075, 5.36239), box(14.78758, 2.42722, -0.94591, 15.78758, 6.42722, 5.05409));
            case WEST -> Shapes.or(box(-1.33089, 0.02963, 1.00687, 17.66911, 6.02963, 15.00687), box(4.53308, 0.62763, 0.00687, 6.53308, 11.62763, 2.00687), box(4.53308, 0.62763, 14.00687, 6.53308, 11.62763, 16.00687), box(10.63761, 2.55075, 0.22615, 16.63761, 6.55075, 1.22615), box(10.94591, 2.42722, 14.78758, 16.94591, 6.42722, 15.78758));
        };
    }

    private static VoxelShape hoglinHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(0.0, -5.0, 13.0, 16.0, 15.0, 16.0);
            case EAST -> box(0.0, -5.0, 0.0, 3.0, 15.0, 16.0);
            default -> box(0.0, -5.0, 0.0, 16.0, 15.0, 3.0);
            case WEST -> box(13.0, -5.0, 0.0, 16.0, 15.0, 16.0);
        };
    }

    private static VoxelShape hoglinSkeletonHanging(BlockState state) {
        return hoglinHanging(state);
    }

    private static VoxelShape hoglinSkeletonLying(BlockState state) {
        return hoglinLying(state);
    }

    private static CarcassDefinition buildHOGLIN() {
        return new CarcassDefinition(
                "hoglin",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:hoglin")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? hoglinHanging(state) : hoglinLying(state),
                Carcasses::hoglinLying,
                Carcasses::hoglinHead,
                Carcasses::hoglinHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? hoglinSkeletonHanging(state) : hoglinSkeletonLying(state),
                // hoglin drops raw_hoglin_chunk
                java.util.List.of(net.minecraft.world.item.Items.PORKCHOP));
    }

    // Shapes below are ported 1:1 from the original Butchery zoglin blocks.
    private static VoxelShape zoglinHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(1.5, 10.0, -1.0, 15.5, 16.0, 10.0), box(1.5, 0.0, 3.0, 15.5, 10.0, 13.0));
            case EAST -> Shapes.or(box(6.0, 10.0, 1.5, 17.0, 16.0, 15.5), box(3.0, 0.0, 1.5, 13.0, 10.0, 15.5));
            default -> Shapes.or(box(0.5, 10.0, 6.0, 14.5, 16.0, 17.0), box(0.5, 0.0, 3.0, 14.5, 10.0, 13.0));
            case WEST -> Shapes.or(box(-1.0, 10.0, 0.5, 10.0, 16.0, 14.5), box(3.0, 0.0, 0.5, 13.0, 10.0, 14.5));
        };
    }

    private static VoxelShape zoglinLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(-10.70051, 5.0, -0.20051, 15.29949, 19.0, 15.79949);
            case EAST -> box(0.20051, 5.0, -10.70051, 16.20051, 19.0, 15.29949);
            default -> box(0.70051, 5.0, 0.20051, 26.70051, 19.0, 16.20051);
            case WEST -> box(-0.20051, 5.0, 0.70051, 15.79949, 19.0, 26.70051);
        };
    }

    private static VoxelShape zoglinHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(0.99313, 0.02963, -1.33089, 14.99313, 6.02963, 17.66911), box(13.99313, 0.62763, 4.53308, 15.99313, 11.62763, 6.53308), box(-0.00687, 0.62763, 4.53308, 1.99313, 11.62763, 6.53308), box(14.77385, 2.55075, 10.63761, 15.77385, 6.55075, 16.63761), box(0.21242, 2.42722, 10.94591, 1.21242, 6.42722, 16.94591));
            case EAST -> Shapes.or(box(-1.66911, 0.02963, 0.99313, 17.33089, 6.02963, 14.99313), box(9.46692, 0.62763, 13.99313, 11.46692, 11.62763, 15.99313), box(9.46692, 0.62763, -0.00687, 11.46692, 11.62763, 1.99313), box(-0.63761, 2.55075, 14.77385, 5.36239, 6.55075, 15.77385), box(-0.94591, 2.42722, 0.21242, 5.05409, 6.42722, 1.21242));
            default -> Shapes.or(box(1.00687, 0.02963, -1.66911, 15.00687, 6.02963, 17.33089), box(0.00687, 0.62763, 9.46692, 2.00687, 11.62763, 11.46692), box(14.00687, 0.62763, 9.46692, 16.00687, 11.62763, 11.46692), box(0.22615, 2.55075, -0.63761, 1.22615, 6.55075, 5.36239), box(14.78758, 2.42722, -0.94591, 15.78758, 6.42722, 5.05409));
            case WEST -> Shapes.or(box(-1.33089, 0.02963, 1.00687, 17.66911, 6.02963, 15.00687), box(4.53308, 0.62763, 0.00687, 6.53308, 11.62763, 2.00687), box(4.53308, 0.62763, 14.00687, 6.53308, 11.62763, 16.00687), box(10.63761, 2.55075, 0.22615, 16.63761, 6.55075, 1.22615), box(10.94591, 2.42722, 14.78758, 16.94591, 6.42722, 15.78758));
        };
    }

    private static VoxelShape zoglinHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(0.0, -5.0, 13.0, 16.0, 15.0, 16.0);
            case EAST -> box(0.0, -5.0, 0.0, 3.0, 15.0, 16.0);
            default -> box(0.0, -5.0, 0.0, 16.0, 15.0, 3.0);
            case WEST -> box(13.0, -5.0, 0.0, 16.0, 15.0, 16.0);
        };
    }

    // zoglin has no skeleton

    private static CarcassDefinition buildZOGLIN() {
        return new CarcassDefinition(
                "zoglin",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:zoglin")))
                        .value(),
                true,
                true,
                false,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? zoglinHanging(state) : zoglinLying(state),
                Carcasses::zoglinLying,
                Carcasses::zoglinHead,
                Carcasses::zoglinHeadMount,
                Carcasses::zoglinLying,  // no skeleton
                // zoglin drops minecraft:rotten_flesh
                java.util.List.of(net.minecraft.world.item.Items.ROTTEN_FLESH));
    }

    // Shapes below are ported 1:1 from the original Butchery dolphin blocks.
    private static VoxelShape dolphinHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(4.0, -1.8, 4.0, 12.0, 11.2, 11.0), box(6.0, 8.0, 6.0, 10.0, 19.0, 11.0));
            case EAST -> Shapes.or(box(5.0, -1.8, 4.0, 12.0, 11.2, 12.0), box(5.0, 8.0, 6.0, 10.0, 19.0, 10.0));
            default -> Shapes.or(box(4.0, -1.8, 5.0, 12.0, 11.2, 12.0), box(6.0, 8.0, 5.0, 10.0, 19.0, 10.0));
            case WEST -> Shapes.or(box(4.0, -1.8, 4.0, 11.0, 11.2, 12.0), box(6.0, 8.0, 6.0, 11.0, 19.0, 10.0));
        };
    }

    private static VoxelShape dolphinLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(3.0, 0.0, 6.0, 16.0, 7.0, 14.0);
            case EAST -> box(2.0, 0.0, 3.0, 10.0, 7.0, 16.0);
            default -> box(0.0, 0.0, 2.0, 13.0, 7.0, 10.0);
            case WEST -> box(6.0, 0.0, 0.0, 14.0, 7.0, 13.0);
        };
    }

    private static VoxelShape dolphinHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(4.01135, 0.001, 6.96254, 12.01135, 7.001, 12.96254), box(7.01135, 2.001, 12.96254, 9.01135, 4.001, 13.96254), box(7.01135, 0.001, 2.96254, 9.01135, 2.001, 6.96254));
            case EAST -> Shapes.or(box(3.03746, 0.001, 4.01135, 9.03746, 7.001, 12.01135), box(2.03746, 2.001, 7.01135, 3.03746, 4.001, 9.01135), box(9.03746, 0.001, 7.01135, 13.03746, 2.001, 9.01135));
            default -> Shapes.or(box(3.98865, 0.001, 3.03746, 11.98865, 7.001, 9.03746), box(6.98865, 2.001, 2.03746, 8.98865, 4.001, 3.03746), box(6.98865, 0.001, 9.03746, 8.98865, 2.001, 13.03746));
            case WEST -> Shapes.or(box(6.96254, 0.001, 3.98865, 12.96254, 7.001, 11.98865), box(12.96254, 2.001, 6.98865, 13.96254, 4.001, 8.98865), box(2.96254, 0.001, 6.98865, 6.96254, 2.001, 8.98865));
        };
    }

    private static VoxelShape dolphinHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
        };
    }

    private static VoxelShape dolphinSkeletonHanging(BlockState state) {
        return dolphinHanging(state);
    }

    private static VoxelShape dolphinSkeletonLying(BlockState state) {
        return dolphinLying(state);
    }

    private static CarcassDefinition buildDOLPHIN() {
        return new CarcassDefinition(
                "dolphin",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:dolphin")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? dolphinHanging(state) : dolphinLying(state),
                Carcasses::dolphinLying,
                Carcasses::dolphinHead,
                Carcasses::dolphinHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? dolphinSkeletonHanging(state) : dolphinSkeletonLying(state),
                // dolphin drops raw_dolphin_meat
                java.util.List.of(net.minecraft.world.item.Items.COD));
    }

    // Shapes below are ported 1:1 from the original Butchery bat blocks.
    private static VoxelShape batHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(7.25, 11.45594, 7.13094, 8.75, 12.95594, 8.63094), box(7.25, 12.95594, 7.13094, 8.75, 15.95594, 8.63094));
            case EAST -> Shapes.or(box(7.36906, 11.45594, 7.25, 8.86906, 12.95594, 8.75), box(7.36906, 12.95594, 7.25, 8.86906, 15.95594, 8.75));
            case WEST -> Shapes.or(box(7.13094, 11.45594, 7.25, 8.63094, 12.95594, 8.75), box(7.13094, 12.95594, 7.25, 8.63094, 15.95594, 8.75));
            default -> Shapes.or(box(7.25, 11.45594, 7.36906, 8.75, 12.95594, 8.86906), box(7.25, 12.95594, 7.36906, 8.75, 15.95594, 8.86906));
        };
    }

    private static VoxelShape batLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(7.25, -0.04994, 6.62506, 8.75, 1.45006, 9.62506);
            case EAST -> box(6.37494, -0.04994, 7.25, 9.37494, 1.45006, 8.75);
            case WEST -> box(6.62506, -0.04994, 7.25, 9.62506, 1.45006, 8.75);
            default -> box(7.25, -0.04994, 6.37494, 8.75, 1.45006, 9.37494);
        };
    }

    private static VoxelShape batHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(7.25, -0.01244, 7.31256, 8.75, 1.48756, 8.81256), box(8.25, 1.23756, 7.56256, 9.0, 2.23756, 7.81256), box(7.0, 1.23756, 7.56256, 7.75, 2.23756, 7.81256));
            case EAST -> Shapes.or(box(7.18744, -0.01244, 7.25, 8.68744, 1.48756, 8.75), box(8.18744, 1.23756, 8.25, 8.43744, 2.23756, 9.0), box(8.18744, 1.23756, 7.0, 8.43744, 2.23756, 7.75));
            case WEST -> Shapes.or(box(7.31256, -0.01244, 7.25, 8.81256, 1.48756, 8.75), box(7.56256, 1.23756, 7.0, 7.81256, 2.23756, 7.75), box(7.56256, 1.23756, 8.25, 7.81256, 2.23756, 9.0));
            default -> Shapes.or(box(7.25, -0.01244, 7.18744, 8.75, 1.48756, 8.68744), box(7.0, 1.23756, 8.18744, 7.75, 2.23756, 8.43744), box(8.25, 1.23756, 8.18744, 9.0, 2.23756, 8.43744));
        };
    }

    private static VoxelShape batHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(5.0, 0.0, 5.7, 11.0, 7.0, 11.0);
            case EAST -> box(5.0, 0.0, 5.0, 10.3, 7.0, 11.0);
            case WEST -> box(5.7, 0.0, 5.0, 11.0, 7.0, 11.0);
            default -> box(5.0, 0.0, 5.0, 11.0, 7.0, 10.3);
        };
    }

    private static VoxelShape batSkeletonHanging(BlockState state) {
        return batHanging(state);
    }

    private static VoxelShape batSkeletonLying(BlockState state) {
        return batLying(state);
    }

    private static CarcassDefinition buildBAT() {
        return new CarcassDefinition(
                "bat",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:bat")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? batHanging(state) : batLying(state),
                Carcasses::batLying,
                Carcasses::batHead,
                Carcasses::batHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? batSkeletonHanging(state) : batSkeletonLying(state),
                // bat drops raw_bat_meat
                java.util.List.of(net.minecraft.world.item.Items.BONE));
    }

    // Shapes below are ported 1:1 from the original Butchery silverfish blocks.
    private static VoxelShape silverfishHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(-0.5, 0.0, 4.0, 16.5, 5.0, 11.0);
            case EAST -> box(5.0, 0.0, -0.5, 12.0, 5.0, 16.5);
            case WEST -> box(4.0, 0.0, -0.5, 11.0, 5.0, 16.5);
            default -> box(-0.5, 0.0, 5.0, 16.5, 5.0, 12.0);
        };
    }

    private static VoxelShape silverfishLying(BlockState state) {
        return silverfishHanging(state);
    }

    private static VoxelShape silverfishHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(6.55, 0.0, 7.0, 9.55, 2.0, 9.0);
            case EAST -> box(7.0, 0.0, 6.55, 9.0, 2.0, 9.55);
            case WEST -> box(7.0, 0.0, 6.45, 9.0, 2.0, 9.45);
            default -> box(6.45, 0.0, 7.0, 9.45, 2.0, 9.0);
        };
    }

    private static VoxelShape silverfishHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(5.0, 0.0, 5.7, 11.0, 7.0, 11.0);
            case EAST -> box(5.0, 0.0, 5.0, 10.3, 7.0, 11.0);
            case WEST -> box(5.7, 0.0, 5.0, 11.0, 7.0, 11.0);
            default -> box(5.0, 0.0, 5.0, 11.0, 7.0, 10.3);
        };
    }

    // silverfish has no skeleton

    private static CarcassDefinition buildSILVERFISH() {
        return new CarcassDefinition(
                "silverfish",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:silverfish")))
                        .value(),
                true,
                true,
                false,
                false,
                3,
                Carcasses::silverfishHanging,
                Carcasses::silverfishLying,
                Carcasses::silverfishHead,
                Carcasses::silverfishHeadMount,
                Carcasses::silverfishLying,  // no skeleton
                // silverfish drops raw_silverfish_chunks
                java.util.List.of(net.minecraft.world.item.Items.BONE));
    }

    // Shapes below are ported 1:1 from the original Butchery endermite blocks.
    private static VoxelShape endermiteHanging(BlockState state) {
        int blockstate = state.getValue(CarcassBlockProperty.BLOCKSTATE);
        if (blockstate == 1) {
            return switch (state.getValue(CarcassBlockProperty.FACING)) {
                case NORTH -> Shapes.or(box(5.5, 0.0, 5.0, 10.5, 4.0, 11.0), box(4.5, 0.0, 6.5, 5.5, 3.0, 9.5), box(3.5, 0.0, 7.5, 4.5, 2.0, 8.5));
                case EAST -> Shapes.or(box(5.0, 0.0, 5.5, 11.0, 4.0, 10.5), box(6.5, 0.0, 4.5, 9.5, 3.0, 5.5), box(7.5, 0.0, 3.5, 8.5, 2.0, 4.5));
                case WEST -> Shapes.or(box(5.0, 0.0, 5.5, 11.0, 4.0, 10.5), box(6.5, 0.0, 10.5, 9.5, 3.0, 11.5), box(7.5, 0.0, 11.5, 8.5, 2.0, 12.5));
                default -> Shapes.or(box(5.5, 0.0, 5.0, 10.5, 4.0, 11.0), box(10.5, 0.0, 6.5, 11.5, 3.0, 9.5), box(11.5, 0.0, 7.5, 12.5, 2.0, 8.5));
            };
        }
        if (blockstate == 2) {
            return switch (state.getValue(CarcassBlockProperty.FACING)) {
                case NORTH -> Shapes.or(box(5.5, 0.0, 5.0, 10.5, 4.0, 11.0), box(4.5, 0.0, 6.5, 5.5, 3.0, 9.5));
                case EAST -> Shapes.or(box(5.0, 0.0, 5.5, 11.0, 4.0, 10.5), box(6.5, 0.0, 4.5, 9.5, 3.0, 5.5));
                case WEST -> Shapes.or(box(5.0, 0.0, 5.5, 11.0, 4.0, 10.5), box(6.5, 0.0, 10.5, 9.5, 3.0, 11.5));
                default -> Shapes.or(box(5.5, 0.0, 5.0, 10.5, 4.0, 11.0), box(10.5, 0.0, 6.5, 11.5, 3.0, 9.5));
            };
        }
        if (blockstate == 3) {
            return switch (state.getValue(CarcassBlockProperty.FACING)) {
                case NORTH -> box(5.5, 0.0, 5.0, 10.5, 4.0, 11.0);
                case EAST -> box(5.0, 0.0, 5.5, 11.0, 4.0, 10.5);
                case WEST -> box(5.0, 0.0, 5.5, 11.0, 4.0, 10.5);
                default -> box(5.5, 0.0, 5.0, 10.5, 4.0, 11.0);
            };
        }
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(10.5, 0.0, 6.0, 12.5, 3.0, 10.0), box(5.5, 0.0, 5.0, 10.5, 4.0, 11.0), box(4.5, 0.0, 6.5, 5.5, 3.0, 9.5), box(3.5, 0.0, 7.5, 4.5, 2.0, 8.5));
            case EAST -> Shapes.or(box(6.0, 0.0, 10.5, 10.0, 3.0, 12.5), box(5.0, 0.0, 5.5, 11.0, 4.0, 10.5), box(6.5, 0.0, 4.5, 9.5, 3.0, 5.5), box(7.5, 0.0, 3.5, 8.5, 2.0, 4.5));
            case WEST -> Shapes.or(box(6.0, 0.0, 3.5, 10.0, 3.0, 5.5), box(5.0, 0.0, 5.5, 11.0, 4.0, 10.5), box(6.5, 0.0, 10.5, 9.5, 3.0, 11.5), box(7.5, 0.0, 11.5, 8.5, 2.0, 12.5));
            default -> Shapes.or(box(3.5, 0.0, 6.0, 5.5, 3.0, 10.0), box(5.5, 0.0, 5.0, 10.5, 4.0, 11.0), box(10.5, 0.0, 6.5, 11.5, 3.0, 9.5), box(11.5, 0.0, 7.5, 12.5, 2.0, 8.5));
        };
    }

    private static VoxelShape endermiteLying(BlockState state) {
        return endermiteHanging(state);
    }

    private static VoxelShape endermiteHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(6.0, 0.0, 7.0, 10.0, 3.0, 9.0);
            case EAST -> box(7.0, 0.0, 6.0, 9.0, 3.0, 10.0);
            case WEST -> box(7.0, 0.0, 6.0, 9.0, 3.0, 10.0);
            default -> box(6.0, 0.0, 7.0, 10.0, 3.0, 9.0);
        };
    }

    private static VoxelShape endermiteHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(5.0, 0.0, 5.7, 11.0, 7.0, 11.0);
            case EAST -> box(5.0, 0.0, 5.0, 10.3, 7.0, 11.0);
            case WEST -> box(5.7, 0.0, 5.0, 11.0, 7.0, 11.0);
            default -> box(5.0, 0.0, 5.0, 11.0, 7.0, 10.3);
        };
    }

    // endermite has no skeleton

    private static CarcassDefinition buildENDERMITE() {
        return new CarcassDefinition(
                "endermite",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:endermite")))
                        .value(),
                true,
                true,
                false,
                false,
                3,
                Carcasses::endermiteHanging,
                Carcasses::endermiteLying,
                Carcasses::endermiteHead,
                Carcasses::endermiteHeadMount,
                Carcasses::endermiteLying,  // no skeleton
                // endermite drops raw_endermite_chunks
                java.util.List.of(net.minecraft.world.item.Items.BONE));
    }

    // Shapes below are ported 1:1 from the original Butchery bee blocks.
    private static VoxelShape beeHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(4.5, 0.0, 6.0, 11.5, 7.0, 10.0);
            case EAST -> box(4.5, 0.0, 4.5, 11.5, 7.0, 11.5);
            case WEST -> box(4.5, 0.0, 4.5, 11.5, 7.0, 11.5);
            default -> box(4.5, 0.0, 6.0, 11.5, 7.0, 10.0);
        };
    }

    private static VoxelShape beeLying(BlockState state) {
        return beeHanging(state);
    }

    private static VoxelShape beeHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(4.5, 0.0, 6.0, 11.5, 7.0, 10.0);
            case EAST -> box(6.0, 0.0, 4.5, 10.0, 7.0, 11.5);
            case WEST -> box(6.0, 0.0, 4.5, 10.0, 7.0, 11.5);
            default -> box(4.5, 0.0, 6.0, 11.5, 7.0, 10.0);
        };
    }

    // bee has no head mount, no skeleton

    private static CarcassDefinition buildBEE() {
        return new CarcassDefinition(
                "bee",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:bee")))
                        .value(),
                true,
                false,
                false,
                false,
                2,
                Carcasses::beeHanging,
                Carcasses::beeLying,
                Carcasses::beeHead,
                Carcasses::beeLying,  // no head mount
                Carcasses::beeLying,  // no skeleton
                // bee drops honey_stomach
                java.util.List.of(net.minecraft.world.item.Items.HONEY_BOTTLE));
    }

    // ===== NEW MOBS (cod through horse) =====

    // Shapes below are ported 1:1 from the original Butchery cod blocks.
    private static VoxelShape codHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(3.85, 0.3, 3.75, 12.85, 19.3, 10.75);
            case EAST -> box(5.25, 0.3, 3.85, 12.25, 19.3, 12.85);
            case WEST -> box(3.75, 0.3, 3.15, 10.75, 19.3, 12.15);
            default -> box(3.15, 0.3, 5.25, 12.15, 19.3, 12.25);
        };
    }

    private static VoxelShape codLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(3.75, 0.05, 5.0, 13.75, 2.05, 9.0);
            case EAST -> box(7.0, 0.05, 3.75, 11.0, 2.05, 13.75);
            case WEST -> box(5.0, 0.05, 2.25, 9.0, 2.05, 12.25);
            default -> box(2.25, 0.05, 7.0, 12.25, 2.05, 11.0);
        };
    }

    // cod has no head, head mount, skeleton, skin

    private static CarcassDefinition buildCOD() {
        return new CarcassDefinition(
                "cod",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:cod")))
                        .value(),
                false,
                false,
                false,
                false,
                1,
                Carcasses::codHanging,
                Carcasses::codLying,
                Carcasses::codLying,  // no head
                Carcasses::codLying,  // no head mount
                Carcasses::codLying,  // no skeleton
                // cod drops cod
                java.util.List.of(net.minecraft.world.item.Items.COD));
    }

    // Shapes below are ported 1:1 from the original Butchery salmon blocks.
    private static VoxelShape salmonHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.42231, -10.01219, 2.2655, 14.42231, 19.98781, 13.2655);
            case EAST -> box(2.7345, -10.01219, 1.42231, 13.7345, 19.98781, 14.42231);
            case WEST -> box(2.2655, -10.01219, 1.57769, 13.2655, 19.98781, 14.57769);
            default -> box(1.57769, -10.01219, 2.7345, 14.57769, 19.98781, 13.7345);
        };
    }

    private static VoxelShape salmonLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(0.05, 0.05, 6.0, 16.05, 3.05, 11.0);
            case EAST -> box(5.0, 0.05, 0.05, 10.0, 3.05, 16.05);
            case WEST -> box(6.0, 0.05, -0.05, 11.0, 3.05, 15.95);
            default -> box(-0.05, 0.05, 5.0, 15.95, 3.05, 10.0);
        };
    }

    // salmon has no head, head mount, skeleton, skin

    private static CarcassDefinition buildSALMON() {
        return new CarcassDefinition(
                "salmon",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:salmon")))
                        .value(),
                false,
                false,
                false,
                false,
                1,
                Carcasses::salmonHanging,
                Carcasses::salmonLying,
                Carcasses::salmonLying,  // no head
                Carcasses::salmonLying,  // no head mount
                Carcasses::salmonLying,  // no skeleton
                // salmon drops salmon
                java.util.List.of(net.minecraft.world.item.Items.SALMON));
    }

    // Shapes below are ported 1:1 from the original Butchery phantom blocks.
    private static VoxelShape phantomHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(5.5, 3.48171, 6.70847, 10.5, 12.48171, 9.70847), box(-0.5, 3.48171, 7.70847, 5.5, 12.48171, 9.70847), box(10.5, 3.48171, 7.70847, 16.5, 12.48171, 9.70847), box(6.5, 12.46765, 7.71055, 9.5, 18.46765, 9.71055));
            case EAST -> Shapes.or(box(6.29153, 3.48171, 5.5, 9.29153, 12.48171, 10.5), box(6.29153, 3.48171, -0.5, 8.29153, 12.48171, 5.5), box(6.29153, 3.48171, 10.5, 8.29153, 12.48171, 16.5), box(6.28945, 12.46765, 6.5, 8.28945, 18.46765, 9.5));
            case WEST -> Shapes.or(box(6.70847, 3.48171, 5.5, 9.70847, 12.48171, 10.5), box(7.70847, 3.48171, 10.5, 9.70847, 12.48171, 16.5), box(7.70847, 3.48171, -0.5, 9.70847, 12.48171, 5.5), box(7.71055, 12.46765, 6.5, 9.71055, 18.46765, 9.5));
            default -> Shapes.or(box(5.5, 3.48171, 6.29153, 10.5, 12.48171, 9.29153), box(10.5, 3.48171, 6.29153, 16.5, 12.48171, 8.29153), box(-0.5, 3.48171, 6.29153, 5.5, 12.48171, 8.29153), box(6.5, 12.46765, 6.28945, 9.5, 18.46765, 8.28945));
        };
    }

    private static VoxelShape phantomLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(5.5, 0.05, 3.0, 10.5, 3.05, 12.0), box(-0.5, 0.05, 3.0, 5.5, 2.05, 12.0), box(10.5, 0.05, 3.0, 16.5, 2.05, 12.0));
            case EAST -> Shapes.or(box(4.0, 0.05, 5.5, 13.0, 3.05, 10.5), box(4.0, 0.05, -0.5, 13.0, 2.05, 5.5), box(4.0, 0.05, 10.5, 13.0, 2.05, 16.5));
            case WEST -> Shapes.or(box(3.0, 0.05, 5.5, 12.0, 3.05, 10.5), box(3.0, 0.05, 10.5, 12.0, 2.05, 16.5), box(3.0, 0.05, -0.5, 12.0, 2.05, 5.5));
            default -> Shapes.or(box(5.5, 0.05, 4.0, 10.5, 3.05, 13.0), box(10.5, 0.05, 4.0, 16.5, 2.05, 13.0), box(-0.5, 0.05, 4.0, 5.5, 2.05, 13.0));
        };
    }

    private static VoxelShape phantomHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(4.5, 0.00502, 5.49791, 11.5, 3.00502, 10.49791);
            case EAST -> box(5.50209, 0.00502, 4.5, 10.50209, 3.00502, 11.5);
            case WEST -> box(5.49791, 0.00502, 4.5, 10.49791, 3.00502, 11.5);
            default -> box(4.5, 0.00502, 5.50209, 11.5, 3.00502, 10.50209);
        };
    }

    private static VoxelShape phantomHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    private static VoxelShape phantomSkeletonHanging(BlockState state) {
        return phantomHanging(state);
    }

    private static VoxelShape phantomSkeletonLying(BlockState state) {
        return phantomLying(state);
    }

    private static CarcassDefinition buildPHANTOM() {
        return new CarcassDefinition(
                "phantom",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:phantom")))
                        .value(),
                true,
                true,
                true,
                false,
                6,
                Carcasses::phantomHanging,
                Carcasses::phantomLying,
                Carcasses::phantomHead,
                Carcasses::phantomHeadMount,
                Carcasses::phantomSkeletonHanging,
                // phantom drops phantom_membrane
                java.util.List.of(net.minecraft.world.item.Items.PHANTOM_MEMBRANE));
    }

    // Shapes below are ported 1:1 from the original Butchery shulker blocks.
    private static VoxelShape shulkerHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(3.0, 0.0, 2.0, 13.0, 16.0, 14.0);
            case EAST -> box(2.0, 0.0, 3.0, 14.0, 16.0, 13.0);
            case WEST -> box(2.0, 0.0, 3.0, 14.0, 16.0, 13.0);
            default -> box(3.0, 0.0, 2.0, 13.0, 16.0, 14.0);
        };
    }

    private static VoxelShape shulkerLying(BlockState state) {
        return shulkerHanging(state);
    }

    // shulker has no head, head mount, skeleton, skin

    private static CarcassDefinition buildSHULKER() {
        return new CarcassDefinition(
                "shulker",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:shulker")))
                        .value(),
                false,
                false,
                false,
                false,
                3,
                Carcasses::shulkerHanging,
                Carcasses::shulkerLying,
                Carcasses::shulkerLying,  // no head
                Carcasses::shulkerLying,  // no head mount
                Carcasses::shulkerLying,  // no skeleton
                // shulker drops shulker_shell
                java.util.List.of(net.minecraft.world.item.Items.SHULKER_SHELL));
    }

    // Shapes below are ported 1:1 from the original Butchery guardian blocks.
    private static VoxelShape guardianHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(2.0, -7.4, 4.0, 14.0, 4.6, 16.0);
            case EAST -> box(0.0, -7.4, 2.0, 12.0, 4.6, 14.0);
            case WEST -> box(4.0, -7.4, 2.0, 16.0, 4.6, 14.0);
            default -> box(2.0, -7.4, 0.0, 14.0, 4.6, 12.0);
        };
    }

    private static VoxelShape guardianLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(2.0, 2.0, 2.0, 14.0, 14.0, 14.0);
            case EAST -> box(2.0, 2.0, 2.0, 14.0, 14.0, 14.0);
            case WEST -> box(2.0, 2.0, 2.0, 14.0, 14.0, 14.0);
            default -> box(2.0, 2.0, 2.0, 14.0, 14.0, 14.0);
        };
    }

    // guardian has no head, head mount, skeleton, skin (has spike_drop instead)

    private static CarcassDefinition buildGUARDIAN() {
        return new CarcassDefinition(
                "guardian",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:guardian")))
                        .value(),
                false,
                false,
                false,
                false,
                3,
                Carcasses::guardianHanging,
                Carcasses::guardianLying,
                Carcasses::guardianLying,  // no head
                Carcasses::guardianLying,  // no head mount
                Carcasses::guardianLying,  // no skeleton
                // guardian drops prismarine_shard
                java.util.List.of(net.minecraft.world.item.Items.PRISMARINE_SHARD));
    }

    // Shapes below are ported 1:1 from the original Butchery elder_guardian blocks.
    private static VoxelShape elder_guardianHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0), box(0.0, 16.0, 0.0, 16.0, 32.0, 16.0));
            case EAST -> Shapes.or(box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0), box(0.0, 16.0, 0.0, 16.0, 32.0, 16.0));
            case WEST -> Shapes.or(box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0), box(0.0, 16.0, 0.0, 16.0, 32.0, 16.0));
            default -> Shapes.or(box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0), box(0.0, 16.0, 0.0, 16.0, 32.0, 16.0));
        };
    }

    private static VoxelShape elder_guardianLying(BlockState state) {
        return elder_guardianHanging(state);
    }

    // elder_guardian has no head, head mount, skeleton, skin

    private static CarcassDefinition buildELDER_GUARDIAN() {
        return new CarcassDefinition(
                "elder_guardian",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:elder_guardian")))
                        .value(),
                false,
                false,
                false,
                false,
                4,
                Carcasses::elder_guardianHanging,
                Carcasses::elder_guardianLying,
                Carcasses::elder_guardianLying,  // no head
                Carcasses::elder_guardianLying,  // no head mount
                Carcasses::elder_guardianLying,  // no skeleton
                // elder_guardian drops prismarine_crystals
                java.util.List.of(net.minecraft.world.item.Items.PRISMARINE_CRYSTALS));
    }

    // Shapes below are ported 1:1 from the original Butchery skeleton_horse blocks.
    // Need to extract shapes from SkeletonhorsecarcassBlock
    private static VoxelShape skeleton_horseHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(3.0, 0.0, 2.0, 13.0, 16.0, 14.0);
            case EAST -> box(2.0, 0.0, 3.0, 14.0, 16.0, 13.0);
            case WEST -> box(2.0, 0.0, 3.0, 14.0, 16.0, 13.0);
            default -> box(3.0, 0.0, 2.0, 13.0, 16.0, 14.0);
        };
    }

    private static VoxelShape skeleton_horseLying(BlockState state) {
        return skeleton_horseHanging(state);
    }

    // Need skeleton_horse head/headmount shapes
    private static VoxelShape skeleton_horseHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(5.0, 0.0, 4.0, 11.0, 6.0, 12.0);
            case EAST -> box(4.0, 0.0, 5.0, 12.0, 6.0, 11.0);
            case WEST -> box(4.0, 0.0, 5.0, 12.0, 6.0, 11.0);
            default -> box(5.0, 0.0, 4.0, 11.0, 6.0, 12.0);
        };
    }

    private static VoxelShape skeleton_horseHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    private static CarcassDefinition buildSKELETON_HORSE() {
        return new CarcassDefinition(
                "skeleton_horse",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:skeleton_horse")))
                        .value(),
                true,
                true,
                false,
                true,
                3,
                Carcasses::skeleton_horseHanging,
                Carcasses::skeleton_horseLying,
                Carcasses::skeleton_horseHead,
                Carcasses::skeleton_horseHeadMount,
                Carcasses::skeleton_horseLying,  // no skeleton
                // skeleton_horse drops bone
                java.util.List.of(net.minecraft.world.item.Items.BONE));
    }

    // Shapes below are ported 1:1 from the original Butchery zombie_horse blocks.
    private static VoxelShape zombie_horseHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(3.0, 0.0, 2.0, 13.0, 16.0, 14.0);
            case EAST -> box(2.0, 0.0, 3.0, 14.0, 16.0, 13.0);
            case WEST -> box(2.0, 0.0, 3.0, 14.0, 16.0, 13.0);
            default -> box(3.0, 0.0, 2.0, 13.0, 16.0, 14.0);
        };
    }

    private static VoxelShape zombie_horseLying(BlockState state) {
        return zombie_horseHanging(state);
    }

    // Need zombie_horse head/headmount shapes
    private static VoxelShape zombie_horseHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(5.0, 0.0, 4.0, 11.0, 6.0, 12.0);
            case EAST -> box(4.0, 0.0, 5.0, 12.0, 6.0, 11.0);
            case WEST -> box(4.0, 0.0, 5.0, 12.0, 6.0, 11.0);
            default -> box(5.0, 0.0, 4.0, 11.0, 6.0, 12.0);
        };
    }

    private static VoxelShape zombie_horseHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    private static CarcassDefinition buildZOMBIE_HORSE() {
        return new CarcassDefinition(
                "zombie_horse",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:zombie_horse")))
                        .value(),
                true,
                true,
                false,
                true,
                3,
                Carcasses::zombie_horseHanging,
                Carcasses::zombie_horseLying,
                Carcasses::zombie_horseHead,
                Carcasses::zombie_horseHeadMount,
                Carcasses::zombie_horseLying,  // no skeleton
                // zombie_horse drops rotten_flesh
                java.util.List.of(net.minecraft.world.item.Items.ROTTEN_FLESH));
    }

    // Shapes below are ported 1:1 from the original Butchery horse blocks.
    private static VoxelShape horseHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(3.0, 0.0, 2.0, 13.0, 16.0, 14.0);
            case EAST -> box(2.0, 0.0, 3.0, 14.0, 16.0, 13.0);
            case WEST -> box(2.0, 0.0, 3.0, 14.0, 16.0, 13.0);
            default -> box(3.0, 0.0, 2.0, 13.0, 16.0, 14.0);
        };
    }

    private static VoxelShape horseLying(BlockState state) {
        return horseHanging(state);
    }

    // horse has no head, head mount, skeleton, skin

    private static CarcassDefinition buildHORSE() {
        return new CarcassDefinition(
                "horse",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:horse")))
                        .value(),
                false,
                false,
                false,
                false,
                3,
                Carcasses::horseHanging,
                Carcasses::horseLying,
                Carcasses::horseLying,  // no head
                Carcasses::horseLying,  // no head mount
                Carcasses::horseLying,  // no skeleton
                // horse drops leather
                java.util.List.of(net.minecraft.world.item.Items.LEATHER));
    }

    // Shapes below are ported 1:1 from the original Butchery llama blocks.
    // All 4 variants (brown, white, creamy, gray) share the same shapes.
    private static VoxelShape llamaHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.6325, -3.10669, 8.73919, 13.6325, 14.89331, 18.73919);
            case EAST -> box(-2.73919, -3.10669, 1.6325, 7.26081, 14.89331, 13.6325);
            case WEST -> box(8.73919, -3.10669, 2.3675, 18.73919, 14.89331, 14.3675);
            default -> box(2.3675, -3.10669, -2.73919, 14.3675, 14.89331, 7.26081);
        };
    }

    private static VoxelShape llamaLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(-4.25, 4.0, 1.75, 13.75, 14.0, 13.75);
            case EAST -> box(2.25, 4.0, -4.25, 14.25, 14.0, 13.75);
            case WEST -> box(1.75, 4.0, 2.25, 13.75, 14.0, 20.25);
            default -> box(2.25, 4.0, 2.25, 20.25, 14.0, 14.25);
        };
    }

    private static VoxelShape llamaHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(6.03535, 4.26585, 1.55178, 10.03535, 8.26585, 10.55178), box(4.04035, 0.26585, 5.55178, 12.01535, 9.26585, 11.55178), box(4.03535, 9.26585, 7.55178, 7.03535, 12.26585, 9.55178), box(9.03535, 9.26585, 7.55178, 12.03535, 12.26585, 9.55178));
            case EAST -> Shapes.or(box(5.44822, 4.26585, 6.03535, 14.44822, 8.26585, 10.03535), box(4.44822, 0.26585, 4.04035, 10.44822, 9.26585, 12.01535), box(6.44822, 9.26585, 4.03535, 8.44822, 12.26585, 7.03535), box(6.44822, 9.26585, 9.03535, 8.44822, 12.26585, 12.03535));
            case WEST -> Shapes.or(box(1.55178, 4.26585, 5.96465, 10.55178, 8.26585, 9.96465), box(5.55178, 0.26585, 3.98465, 11.55178, 9.26585, 11.95965), box(7.55178, 9.26585, 8.96465, 9.55178, 12.26585, 11.96465), box(7.55178, 9.26585, 3.96465, 9.55178, 12.26585, 6.96465));
            default -> Shapes.or(box(5.96465, 4.26585, 5.44822, 9.96465, 8.26585, 14.44822), box(3.98465, 0.26585, 4.44822, 11.95965, 9.26585, 10.44822), box(8.96465, 9.26585, 6.44822, 11.96465, 12.26585, 8.44822), box(3.96465, 9.26585, 6.44822, 6.96465, 12.26585, 8.44822));
        };
    }

    private static VoxelShape llamaHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    // llama variants have no skeleton

    private static CarcassDefinition buildBROWN_LLAMA() {
        return new CarcassDefinition(
                "brown_llama",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:llama")))
                        .value(),
                true,
                true,
                false,
                true,
                3,
                Carcasses::llamaHanging,
                Carcasses::llamaLying,
                Carcasses::llamaHead,
                Carcasses::llamaHeadMount,
                Carcasses::llamaLying,  // no skeleton
                // brown_llama drops raw_llama_steak
                java.util.List.of(net.minecraft.world.item.Items.LEATHER));
    }

    private static CarcassDefinition buildWHITE_LLAMA() {
        return new CarcassDefinition(
                "white_llama",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:llama")))
                        .value(),
                true,
                true,
                false,
                true,
                3,
                Carcasses::llamaHanging,
                Carcasses::llamaLying,
                Carcasses::llamaHead,
                Carcasses::llamaHeadMount,
                Carcasses::llamaLying,  // no skeleton
                // white_llama drops raw_llama_steak
                java.util.List.of(net.minecraft.world.item.Items.LEATHER));
    }

    private static CarcassDefinition buildCREAMY_LLAMA() {
        return new CarcassDefinition(
                "creamy_llama",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:llama")))
                        .value(),
                true,
                true,
                false,
                true,
                3,
                Carcasses::llamaHanging,
                Carcasses::llamaLying,
                Carcasses::llamaHead,
                Carcasses::llamaHeadMount,
                Carcasses::llamaLying,  // no skeleton
                // creamy_llama drops raw_llama_steak
                java.util.List.of(net.minecraft.world.item.Items.LEATHER));
    }

    private static CarcassDefinition buildGRAY_LLAMA() {
        return new CarcassDefinition(
                "gray_llama",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:llama")))
                        .value(),
                true,
                true,
                false,
                true,
                3,
                Carcasses::llamaHanging,
                Carcasses::llamaLying,
                Carcasses::llamaHead,
                Carcasses::llamaHeadMount,
                Carcasses::llamaLying,  // no skeleton
                // gray_llama drops raw_llama_steak
                java.util.List.of(net.minecraft.world.item.Items.LEATHER));
    }

    // ===== CAT VARIANT BUILDER (all variants share ocelot shapes) =====
    private static CarcassDefinition buildCatVariant(String mobId, int variant) {
        return new CarcassDefinition(
                mobId,
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:cat")))
                        .value(),
                true,
                true,
                false,
                true,
                0,  // cats have no meat cuts, only head + skin
                Carcasses::ocelotHanging,
                Carcasses::ocelotLying,
                Carcasses::ocelotHead,
                Carcasses::ocelotHeadMount,
                Carcasses::ocelotLying,  // no skeleton
                // cats don't have vanilla drops
                java.util.List.of());
    }

    /** Returns the carcass definition for a specific cat variant (0-10). */
    public static CarcassDefinition forCatVariant(int variant) {
        return CAT_VARIANTS.getOrDefault(variant, CAT_VARIANTS.get(9)); // default to tabby
    }
// Shapes below are ported 1:1 from the original Butchery squid blocks.
    private static VoxelShape squidHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(2.0, 3.1, 0.7, 14.0, 19.1, 12.7);
            case EAST -> box(3.3, 3.1, 2.0, 15.3, 19.1, 14.0);
            case WEST -> box(0.7, 3.1, 2.0, 12.7, 19.1, 14.0);
            default -> box(2.0, 3.1, 3.3, 14.0, 19.1, 15.3);
        };
    }

    private static VoxelShape squidLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(5.0, 0.0, 2.0, 11.0, 6.0, 10.0);
            case EAST -> box(6.0, 0.0, 5.0, 14.0, 6.0, 11.0);
            case WEST -> box(2.0, 0.0, 5.0, 10.0, 6.0, 11.0);
            default -> box(5.0, 0.0, 6.0, 11.0, 6.0, 14.0);
        };
    }

    private static VoxelShape squidHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(2.0, 0.1, 2.1, 14.0, 16.1, 14.1);
            case EAST -> box(1.9, 0.1, 2.0, 13.9, 16.1, 14.0);
            case WEST -> box(2.1, 0.1, 2.0, 14.1, 16.1, 14.0);
            default -> box(2.0, 0.1, 1.9, 14.0, 16.1, 13.9);
        };
    }

    // squid has no head mount, no skeleton, no skin

    private static CarcassDefinition buildSQUID() {
        return new CarcassDefinition(
                "squid",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:squid")))
                        .value(),
                true,
                false,
                false,
                false,
                2,
                Carcasses::squidHanging,
                Carcasses::squidLying,
                Carcasses::squidHead,
                Carcasses::squidLying,  // no head mount
                Carcasses::squidLying,  // no skeleton
                // squid drops ink_sac
                java.util.List.of(net.minecraft.world.item.Items.INK_SAC));
    }

    // Shapes below are ported 1:1 from the original Butchery glowsquid blocks.
    private static VoxelShape glow_squidHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(2.0, 3.1, 0.7, 14.0, 19.1, 12.7);
            case EAST -> box(3.3, 3.1, 2.0, 15.3, 19.1, 14.0);
            case WEST -> box(0.7, 3.1, 2.0, 12.7, 19.1, 14.0);
            default -> box(2.0, 3.1, 3.3, 14.0, 19.1, 15.3);
        };
    }

    private static VoxelShape glow_squidLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(5.0, 0.0, 2.0, 11.0, 6.0, 10.0);
            case EAST -> box(6.0, 0.0, 5.0, 14.0, 6.0, 11.0);
            case WEST -> box(2.0, 0.0, 5.0, 10.0, 6.0, 11.0);
            default -> box(5.0, 0.0, 6.0, 11.0, 6.0, 14.0);
        };
    }

    private static VoxelShape glow_squidHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(2.0, 0.1, 2.1, 14.0, 16.1, 14.1);
            case EAST -> box(1.9, 0.1, 2.0, 13.9, 16.1, 14.0);
            case WEST -> box(2.1, 0.1, 2.0, 14.1, 16.1, 14.0);
            default -> box(2.0, 0.1, 1.9, 14.0, 16.1, 13.9);
        };
    }

    // glowsquid has no head mount, no skeleton, no skin

    private static CarcassDefinition buildGLOW_SQUID() {
        return new CarcassDefinition(
                "glow_squid",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:glow_squid")))
                        .value(),
                true,
                false,
                false,
                false,
                2,
                Carcasses::glow_squidHanging,
                Carcasses::glow_squidLying,
                Carcasses::glow_squidHead,
                Carcasses::glow_squidLying,  // no head mount
                Carcasses::glow_squidLying,  // no skeleton
                // glowsquid drops glow_ink_sac
                java.util.List.of(net.minecraft.world.item.Items.GLOW_INK_SAC));
    }

    // Shapes below are ported 1:1 from the original Butchery creeper blocks.
    private static VoxelShape creeperHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(4.73558, 7.43269, 5.69711, 12.73558, 19.43269, 9.69711);
            case EAST -> box(6.30289, 7.43269, 4.73558, 10.30289, 19.43269, 12.73558);
            case WEST -> box(5.69711, 7.43269, 3.26442, 9.69711, 19.43269, 11.26442);
            default -> box(3.26442, 7.43269, 6.30289, 11.26442, 19.43269, 10.30289);
        };
    }

    private static VoxelShape creeperLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(2.3, 0.25961, 3.95961, 14.3, 4.25961, 11.95961);
            case EAST -> box(4.04039, 0.25961, 2.3, 12.04039, 4.25961, 14.3);
            case WEST -> box(3.95961, 0.25961, 1.7, 11.95961, 4.25961, 13.7);
            default -> box(1.7, 0.25961, 4.04039, 13.7, 4.25961, 12.04039);
        };
    }

    // Creeper head uses vanilla minecraft:creeper_head item, no custom head block needed
    private static VoxelShape creeperHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    private static VoxelShape creeperSkeletonHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(4.73558, 7.43269, 5.69711, 12.73558, 19.43269, 9.69711);
            case EAST -> box(6.30289, 7.43269, 4.73558, 10.30289, 19.43269, 12.73558);
            case WEST -> box(5.69711, 7.43269, 3.26442, 9.69711, 19.43269, 11.26442);
            default -> box(3.26442, 7.43269, 6.30289, 11.26442, 19.43269, 10.30289);
        };
    }

    private static VoxelShape creeperSkeletonLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(2.3, 0.25961, 3.95961, 14.3, 4.25961, 11.95961);
            case EAST -> box(4.04039, 0.25961, 2.3, 12.04039, 4.25961, 14.3);
            case WEST -> box(3.95961, 0.25961, 1.7, 11.95961, 4.25961, 13.7);
            default -> box(1.7, 0.25961, 4.04039, 13.7, 4.25961, 12.04039);
        };
    }

    private static CarcassDefinition buildCREEPER() {
        return new CarcassDefinition(
                "creeper",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:creeper")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                Carcasses::creeperHanging,
                Carcasses::creeperLying,
                Carcasses::ocelotLying,  // uses vanilla minecraft:creeper_head item, no custom head block
                Carcasses::creeperHeadMount,
                Carcasses::creeperSkeletonHanging,
                // creeper drops raw_creeper_steak
                java.util.List.of(net.minecraft.world.item.Items.TNT));
    }

    // Shapes below are ported 1:1 from the original Butchery spider blocks.
    // Both spider and cave_spider share these shapes.
    private static VoxelShape spiderHanging(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> Shapes.or(box(5.3, 20.0, 4.0, 10.8, 24.5, 8.0), box(5.7, 12.5, 5.2, 10.2, 19.3, 9.2), box(4.3, 5.3, 1.2, 11.8, 14.3, 10.0));
            case EAST -> Shapes.or(box(8.0, 20.0, 5.3, 12.0, 24.5, 10.8), box(6.8, 12.5, 5.7, 10.8, 19.3, 10.2), box(6.0, 5.3, 4.3, 14.8, 14.3, 11.8));
            case WEST -> Shapes.or(box(4.0, 20.0, 5.2, 8.0, 24.5, 10.7), box(5.2, 12.5, 5.8, 9.2, 19.3, 10.3), box(1.2, 5.3, 4.2, 10.0, 14.3, 11.7));
            default -> Shapes.or(box(5.2, 20.0, 8.0, 10.7, 24.5, 12.0), box(5.8, 12.5, 6.8, 10.3, 19.3, 10.8), box(4.2, 5.3, 6.0, 11.7, 14.3, 14.8));
        };
    }

    private static VoxelShape spiderLying(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(5.87276, 0.075, 5.57276, 10.37276, 4.575, 10.07276);
            case EAST -> box(5.92724, 0.075, 5.87276, 10.42724, 4.575, 10.37276);
            case WEST -> box(5.57276, 0.075, 5.62724, 10.07276, 4.575, 10.12724);
            default -> box(5.62724, 0.075, 5.92724, 10.12724, 4.575, 10.42724);
        };
    }

    private static VoxelShape spiderHead(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
            case EAST -> box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
            case WEST -> box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
            default -> box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
        };
    }

    private static VoxelShape spiderHeadMount(BlockState state) {
        return switch (state.getValue(CarcassBlockProperty.FACING)) {
            case NORTH -> box(1.0, 0.0, 12.0, 15.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 1.0, 4.0, 16.0, 15.0);
            case WEST -> box(12.0, 0.0, 1.0, 16.0, 16.0, 15.0);
            default -> box(1.0, 0.0, 0.0, 15.0, 16.0, 4.0);
        };
    }

    // spider has no skeleton

    private static CarcassDefinition buildSPIDER() {
        return new CarcassDefinition(
                "spider",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:spider")))
                        .value(),
                true,
                true,
                false,
                false,
                3,
                Carcasses::spiderHanging,
                Carcasses::spiderLying,
                Carcasses::spiderHead,
                Carcasses::spiderHeadMount,
                Carcasses::spiderLying,  // no skeleton
                // spider drops spider_eye
                java.util.List.of(net.minecraft.world.item.Items.SPIDER_EYE));
    }

    // cave_spider uses the same shapes as spider
    private static VoxelShape cave_spiderHanging(BlockState state) {
        return spiderHanging(state);
    }

    private static VoxelShape cave_spiderLying(BlockState state) {
        return spiderLying(state);
    }

    private static VoxelShape cave_spiderHead(BlockState state) {
        return spiderHead(state);
    }

    private static VoxelShape cave_spiderHeadMount(BlockState state) {
        return spiderHeadMount(state);
    }

    // cave_spider has no skeleton

    private static CarcassDefinition buildCAVE_SPIDER() {
        return new CarcassDefinition(
                "cave_spider",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:cave_spider")))
                        .value(),
                true,
                true,
                false,
                false,
                3,
                Carcasses::cave_spiderHanging,
                Carcasses::cave_spiderLying,
                Carcasses::cave_spiderHead,
                Carcasses::cave_spiderHeadMount,
                Carcasses::cave_spiderLying,  // no skeleton
                // cave_spider drops string
                java.util.List.of(net.minecraft.world.item.Items.STRING));
    }
}