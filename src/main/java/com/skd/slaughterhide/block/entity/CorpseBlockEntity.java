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
 * Minimal block entity for corpse blocks. Mirrors {@link CarcassBlockEntity}'s
 * clean persistent-data-tag style but without the vestigial container.
 * Corpse progression is encoded entirely in the {@code blockstate}
 * IntegerProperty, so no NBT tags are strictly required; this entity exists
 * to keep the block-entity type registration valid and to provide client
 * sync via {@link #getUpdatePacket}.
 */
public class CorpseBlockEntity extends BlockEntity {

    private static final String TAG_MOB = "mobId";

    public CorpseBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CORPSE.get(), pos, state);
    }

    public String getMobId() {
        return getPersistentData().getString(TAG_MOB).orElse("");
    }

    public void setMobId(String mobId) {
        getPersistentData().putString(TAG_MOB, mobId);
    }

    public void remember(CarcassDefinition definition) {
        setMobId(definition.mobId());
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        return saveWithFullMetadata(lookupProvider);
    }
}
