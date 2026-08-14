package com.skd.slaughterhide.block.entity;

import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Generic block entity shared by every carcass block (fresh, drained, and the
 * display blocks of every future mob). Holds which mob the carcass belongs to
 * plus the bleeding/drained flags as typed accessors instead of scattered NBT
 * string lookups. The original's unused 9-slot chest inventory is intentionally
 * dropped: it was a vestigial MCreator template feature the meat mechanic never
 * reads.
 *
 * <p>State is backed by the embeddable persistent-data tag exposed by NeoForge
 * so it survives save/load without us having to re-implement NBT serialization,
 * and is shipped to clients via {@link #getUpdateTag}.</p>
 */
public class CarcassBlockEntity extends BlockEntity {

    private static final String TAG_MOB = "mobId";
    private static final String TAG_BLEEDING = "isBleeding";
    private static final String TAG_DRAINED = "isDrained";

    public CarcassBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CARCASS.get(), pos, state);
    }

    public String getMobId() {
        return getPersistentData().getString(TAG_MOB).orElse("");
    }

    public void setMobId(String mobId) {
        getPersistentData().putString(TAG_MOB, mobId);
    }

    public boolean isBleeding() {
        return getPersistentData().getBooleanOr(TAG_BLEEDING, false);
    }

    public void setBleeding(boolean bleeding) {
        getPersistentData().putBoolean(TAG_BLEEDING, bleeding);
    }

    public boolean isDrained() {
        return getPersistentData().getBooleanOr(TAG_DRAINED, false);
    }

    public void setDrained(boolean drained) {
        getPersistentData().putBoolean(TAG_DRAINED, drained);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        return saveWithFullMetadata(lookupProvider);
    }

    /** Persist any CarcassDefinition lookup needed by handlers; no-op when unset. */
    public void remember(CarcassDefinition definition) {
        setMobId(definition.mobId());
    }
}