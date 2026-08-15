package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.Carcasses;
import com.skd.slaughterhide.ServerWorkScheduler;
import com.skd.slaughterhide.init.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

/**
 * World placement of carcasses: when a mob with a carcass definition dies, it
 * drops its carcass as a placeable item (matching the original's item + manual
 * placement, not an auto-hung block) and the vanilla beef/leather item drops
 * are swept away so the carcass is the reward. Replaces the original
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
        dropCarcassItem(level, entity, definition);
        sweepVanillaDrops(level, entity);
    }

    /**
     * Drops the carcass as a placeable item, like the original: the player
     * picks it up and places it themselves (a normal {@code BlockItem} use)
     * instead of it appearing pre-hung at the death spot.
     */
    private static void dropCarcassItem(ServerLevel level, Entity entity, CarcassDefinition definition) {
        var carcassItem = ModItems.freshItemFor(definition.mobId());
        if (carcassItem == null) {
            return;
        }
        ItemEntity drop = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(),
                new ItemStack(carcassItem.get()));
        level.addFreshEntity(drop);
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
}