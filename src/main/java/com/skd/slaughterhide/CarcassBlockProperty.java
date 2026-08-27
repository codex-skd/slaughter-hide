package com.skd.slaughterhide;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

/** Shared state properties for the generic carcass family blocks. */
public final class CarcassBlockProperty {
    private CarcassBlockProperty() {
    }

    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

/**
 * Values used by the fresh carcass (0 = lying/relocatable, 1 = hung/bleeding)
 * and by the skeleton display block (0 = lying, 1 = hung). The drained carcass
 * uses its own property but with the same name and the same 0..9 values as the
 * original; only {0,6,7,8,9} are ever set by the cut state machine.
 */
public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 3);

/** Cut-stage values accepted by the drained carcass block only. */
public static final IntegerProperty DRAINED_BLOCKSTATE = IntegerProperty.create("blockstate", 0, 9);

/**
 * Set on a drained carcass that was bled while hanging from a hook, so it keeps
 * a hanging pose instead of snapping to the lying model.
 */
public static final BooleanProperty HANGING = BooleanProperty.create("hanging");
}