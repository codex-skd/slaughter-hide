package com.skd.slaughterhide;

import java.util.function.Function;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Data about one mob's carcass family.
 *
 * <p>This is the single place that describes how a mob is butchered: which
 * {@link net.minecraft.world.entity.EntityType} it triggers on, which loot
 * tables feed each cutting stage, which optional display blocks it has
 * (head, head mount, skeleton) and the collision shapes used by the hanging /
 * lying models. Future mobs only need a new entry here plus their assets;
 * no new block/handler classes.</p>
 */
public record CarcassDefinition(
        String mobId,
        EntityType<?> entityType,
        boolean hasHead,
        boolean hasHeadMount,
        boolean hasSkeleton,
        Function<BlockState, VoxelShape> freshCarcassShape,
        Function<BlockState, VoxelShape> drainedCarcassShape,
        Function<BlockState, VoxelShape> headShape,
        Function<BlockState, VoxelShape> headMountShape,
        Function<BlockState, VoxelShape> skeletonShape) {

    /** Registry name for a block of this mob, e.g. {@code cow_carcass}. */
    public Identifier blockId(String suffix) {
        return Identifier.parse(SlaughterHide.MOD_ID + ":" + mobId + "_" + suffix);
    }

    /** Loot table key by convention, e.g. {@code slaughter_hide:blocks/cow_head_drop}. */
    public ResourceKey<LootTable> dropTable(String suffix) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                Identifier.parse(SlaughterHide.MOD_ID + ":blocks/" + mobId + "_" + suffix));
    }

    public ResourceKey<LootTable> headDropTable() {
        return dropTable("head_drop");
    }

    public ResourceKey<LootTable> skinDropTable() {
        return dropTable("skin_drop");
    }

    public ResourceKey<LootTable> cutDropTable(int index) {
        return dropTable("cut_" + index + "_drop");
    }
}