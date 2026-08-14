package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.CarcassDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

/** Small shared helpers for resolving and dropping the cut-stage loot tables. */
public final class CarcassLoot {
    private CarcassLoot() {
    }

    public static void spawn(ServerLevel level, BlockPos pos, ResourceKey<LootTable> key) {
        List<ItemStack> stacks = level.getServer().reloadableRegistries()
                .getLootTable(key)
                .getRandomItems(new LootParams.Builder(level).create(LootContextParamSets.EMPTY));
        for (ItemStack stack : stacks) {
            ItemEntity entity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
            entity.setPickUpDelay(10);
            level.addFreshEntity(entity);
        }
    }
}