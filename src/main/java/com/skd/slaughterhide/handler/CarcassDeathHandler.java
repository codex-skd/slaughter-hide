package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.CarcassBlockProperty;
import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.Carcasses;
import com.skd.slaughterhide.ServerWorkScheduler;
import com.skd.slaughterhide.block.CarcassBlock;
import com.skd.slaughterhide.block.entity.CarcassBlockEntity;
import com.skd.slaughterhide.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

/**
 * World placement of carcasses: when a mob with a carcass definition dies, its
 * carcass block appears at the death spot and the vanilla beef/leather item
 * drops are swept away so the carcass is the reward. Replaces the original
 * {@code <Mob>carcassdropProcedure} per-mob event classes with one dispatcher.
 */
public final class CarcassDeathHandler {
    private CarcassDeathHandler() {
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity.level().isClientSide()) {
            return;
        }
        if (!(entity.level() instanceof ServerLevel level)) {
            return;
        }
        CarcassDefinition definition = Carcasses.forEntityType(entity.getType());
        if (definition == null) {
            return;
        }
        // The original keeps babies out of the butcher's chain.
        if (entity instanceof LivingEntity living && living.isBaby()) {
            return;
        }
        placeCarcass(level, entity, definition);
        sweepVanillaDrops(level, entity);
    }

    private static void placeCarcass(ServerLevel level, Entity entity, CarcassDefinition definition) {
        var fresh = ModBlocks.freshFor(definition.mobId());
        if (fresh == null) {
            return;
        }
        BlockPos pos = findPlacementPos(level, entity.blockPosition());
        if (pos == null) {
            return;
        }
        BlockState state = fresh.get().defaultBlockState()
                .setValue(CarcassBlockProperty.FACING, entity.getDirection().getOpposite())
                .setValue(CarcassBlockProperty.BLOCKSTATE, 1);
        level.setBlock(pos, state, 3);
        if (level.getBlockEntity(pos) instanceof CarcassBlockEntity blockEntity) {
            blockEntity.remember(definition);
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }

    /**
     * Shelves the original's beef/leather item drops so the carcass (which
     * yields the real meat cuts) replaces them, as the original does by killing
     * those item entities a tick after death.
     */
    private static void sweepVanillaDrops(ServerLevel level, Entity entity) {
        AABB area = new AABB(entity.blockPosition()).inflate(6.0);
        ServerWorkScheduler.queue(1, () -> {
            for (ItemEntity item : level.getEntitiesOfClass(ItemEntity.class, area)) {
                if (item.getItem().is(Items.BEEF) || item.getItem().is(Items.LEATHER)) {
                    item.discard();
                }
            }
        });
    }

    private static BlockPos findPlacementPos(ServerLevel level, BlockPos origin) {
        BlockPos.MutableBlockPos cursor = origin.mutable();
        for (int i = 0; i < 5; i++) {
            BlockState existing = level.getBlockState(cursor);
            if ((existing.isAir() || existing.canBeReplaced()) && !existing.liquid()) {
                return cursor.immutable();
            }
            cursor.move(Direction.UP);
        }
        return null;
    }
}