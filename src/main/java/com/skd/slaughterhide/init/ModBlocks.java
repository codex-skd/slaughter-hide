package com.skd.slaughterhide.init;

import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.Carcasses;
import com.skd.slaughterhide.SlaughterHide;
import com.skd.slaughterhide.block.CarcassBlock;
import com.skd.slaughterhide.block.DrainedCarcassBlock;
import com.skd.slaughterhide.block.HeadMountBlock;
import com.skd.slaughterhide.block.SkeletonBlock;
import com.skd.slaughterhide.block.TrophyHeadBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
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

    static {
        registerFamily(Carcasses.COW);
    }

    private ModBlocks() {
    }

    private static void registerFamily(CarcassDefinition definition) {
        String mob = definition.mobId();
        DeferredBlock<CarcassBlock> fresh = register(mob + "_carcass",
                props -> new CarcassBlock(props, definition));
        DeferredBlock<DrainedCarcassBlock> drained = register("drained_" + mob + "_carcass",
                props -> new DrainedCarcassBlock(props, definition));
        FRESH.put(mob, fresh);
        DRAINED.put(mob, drained);
        if (definition.hasHead()) {
            HEADS.put(mob, register(mob + "_head", props -> new TrophyHeadBlock(props, definition)));
        }
        if (definition.hasHeadMount()) {
            MOUNTS.put(mob, register(mob + "_head_mount", props -> new HeadMountBlock(props, definition)));
        }
        if (definition.hasSkeleton()) {
            SKELETONS.put(mob, register(mob + "_skeleton", props -> new SkeletonBlock(props, definition)));
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
}