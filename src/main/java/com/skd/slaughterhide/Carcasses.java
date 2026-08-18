package com.skd.slaughterhide;

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
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? wolfHanging(state) : wolfLying(state),
                Carcasses::wolfLying,
                Carcasses::wolfHead,
                Carcasses::wolfHeadMount,
                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? wolfSkeletonHanging(state) : wolfSkeletonLying(state),
                // Wolf drops wolf_pelt
                java.util.List.of(net.minecraft.world.item.Items.BONE));
    }
}