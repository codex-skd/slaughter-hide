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

    private static void register(CarcassDefinition definition) {
        BY_ENTITY.put(definition.entityType(), definition);
        BY_MOB_ID.put(definition.mobId(), definition);
    }

    static {
        register(COW);
        register(PIG);
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
}