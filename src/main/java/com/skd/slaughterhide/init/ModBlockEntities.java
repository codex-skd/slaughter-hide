package com.skd.slaughterhide.init;

import com.skd.slaughterhide.SlaughterHide;
import com.skd.slaughterhide.block.entity.CarcassBlockEntity;
import com.skd.slaughterhide.block.entity.CorpseBlockEntity;
import com.skd.slaughterhide.block.entity.FreezerBlockEntity;
import com.skd.slaughterhide.block.entity.MeatGrinderBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * One generic block entity type serves every carcass block (fresh and drained,
 * all mobs), mirroring the original's per-mob block entity classes but bound to
 * the whole family at once.
 */
public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, SlaughterHide.MOD_ID);

    // The supplier runs during the BlockEntityType RegisterEvent, which NeoForge fires
    // after the Block RegisterEvent — ModBlocks.carcassBlocks() (which resolves
    // DeferredBlock::get) MUST stay inside this lambda, not in a static field
    // initializer, or it throws "Trying to access unbound value" during mod
    // construction (blocks aren't registered yet at that point).
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CarcassBlockEntity>> CARCASS =
            REGISTRY.register("carcass",
                    () -> new BlockEntityType<>(CarcassBlockEntity::new, ModBlocks.carcassBlocks()));

    // Corpse block entity removed with farm-only scope (no humanoid corpses)

    // Freezer block entity (27-slot cold-storage container)
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FreezerBlockEntity>> FREEZER =
            REGISTRY.register("freezer",
                    () -> new BlockEntityType<>(FreezerBlockEntity::new,
                            new Block[]{ModBlocks.FREEZER.get()}));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MeatGrinderBlockEntity>> MEAT_GRINDER =
            REGISTRY.register("meat_grinder",
                    () -> new BlockEntityType<>(MeatGrinderBlockEntity::new,
                            new Block[]{ModBlocks.MEAT_GRINDER.get()}));

    private ModBlockEntities() {
    }
}