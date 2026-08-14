package com.skd.slaughterhide.init;

import com.skd.slaughterhide.SlaughterHide;
import com.skd.slaughterhide.block.entity.CarcassBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
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

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CarcassBlockEntity>> CARCASS =
            register("carcass", CarcassBlockEntity::new, ModBlocks.carcassBlocks());

    private ModBlockEntities() {
    }

    private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(
            String name, BlockEntityType.BlockEntitySupplier<T> supplier, Block[] blocks) {
        return REGISTRY.register(name, () -> new BlockEntityType<>(supplier, blocks));
    }
}