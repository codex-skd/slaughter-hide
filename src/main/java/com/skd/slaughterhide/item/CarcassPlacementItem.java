package com.skd.slaughterhide.item;

import com.skd.slaughterhide.CarcassDefinition;
import net.minecraft.world.item.Item;

/**
 * A carcass item (fresh or drained) that can only be hung from a
 * {@link com.skd.slaughterhide.block.HookBlock}, not placed like a normal
 * block. Replaces the plain {@code BlockItem} registration this project used
 * before — right-clicking a hook with one of these in hand is handled by
 * {@link com.skd.slaughterhide.handler.HookPlacementHandler}, matching the
 * original's requirement that a carcass be hung from a hook rather than set
 * down anywhere.
 */
public class CarcassPlacementItem extends Item {
    private final CarcassDefinition definition;
    private final boolean drained;

    public CarcassPlacementItem(Properties properties, CarcassDefinition definition, boolean drained) {
        super(properties);
        this.definition = definition;
        this.drained = drained;
    }

    public CarcassDefinition definition() {
        return definition;
    }

    public boolean isDrained() {
        return drained;
    }
}
