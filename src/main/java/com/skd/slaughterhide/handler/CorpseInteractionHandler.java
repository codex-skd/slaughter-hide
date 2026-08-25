package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.block.CorpseBlock;
import com.skd.slaughterhide.block.entity.CorpseBlockEntity;
import com.skd.slaughterhide.tag.ModItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * Routes right-clicks on corpse blocks through the cutting state machine,
 * mirroring how {@link CarcassCutupHandler} handles drained carcasses.
 * Progression is encoded in the {@link CorpseBlock#BLOCKSTATE} property:
 * <pre>
 *   stage 1  + cleaver       → head drop      → stage 2
 *   stage 2  + skinning knife → skin drop      → stage 3
 *   stage 3  + cleaver       → cut_1 drop     → stage 4
 *   stage 4  + cleaver       → cut_2 drop     → stage 5
 *   stage 5  + cleaver       → cut_3 drop     → stage 6
 *   stage 6  + cleaver       → organs_1 drop  → stage 7
 *   stage 7  + cleaver       → organs_2 drop  → stage 8
 *   stage 8  + cleaver       → organs_3 drop  → block removed
 * </pre>
 * The first three stages use per-mob loot tables; the organ stages use the
 * shared {@code slaughter_hide:blocks/organs_drop_N} tables.
 */
public final class CorpseInteractionHandler {
    private CorpseInteractionHandler() {
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand() != InteractionHand.MAIN_HAND) {
            return;
        }
        LevelAccessor accessor = event.getLevel();
        if (accessor.isClientSide()) {
            return;
        }
        if (!(accessor instanceof Level level)) {
            return;
        }
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        BlockPos pos = event.getPos();
        if (!(level.getBlockEntity(pos) instanceof CorpseBlockEntity blockEntity)) {
            return;
        }
        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof CorpseBlock corpseBlock)) {
            return;
        }
        CarcassDefinition definition = corpseBlock.getDefinition();
        if (definition == null) {
            return;
        }

        ItemStack stack = player.getMainHandItem();
        boolean cleaver = stack.is(ModItemTags.CLEAVER);
        boolean knife = stack.is(ModItemTags.SKINNING_KNIVES);

        int stage = state.getValue(CorpseBlock.BLOCKSTATE);
        ResourceKey<LootTable> drop;
        int nextStage;
        boolean remove;
        switch (stage) {
            case CorpseBlock.STAGE_HANGING -> {
                if (!cleaver) return;
                drop = definition.headDropTable();
                nextStage = CorpseBlock.STAGE_HEAD_CUT;
                remove = false;
            }
            case CorpseBlock.STAGE_HEAD_CUT -> {
                if (!knife) return;
                drop = definition.skinDropTable();
                nextStage = CorpseBlock.STAGE_SKINNED;
                remove = false;
            }
            case CorpseBlock.STAGE_SKINNED -> {
                if (!cleaver) return;
                drop = definition.cutDropTable(1);
                nextStage = CorpseBlock.STAGE_CUT_1;
                remove = false;
            }
            case CorpseBlock.STAGE_CUT_1 -> {
                if (!cleaver) return;
                drop = definition.cutDropTable(2);
                nextStage = CorpseBlock.STAGE_CUT_2;
                remove = false;
            }
            case CorpseBlock.STAGE_CUT_2 -> {
                if (!cleaver) return;
                drop = definition.cutDropTable(3);
                nextStage = CorpseBlock.STAGE_CUT_3;
                remove = false;
            }
            case CorpseBlock.STAGE_CUT_3 -> {
                if (!cleaver) return;
                drop = organsDrop(1);
                nextStage = CorpseBlock.STAGE_ORGANS_1;
                remove = false;
            }
            case CorpseBlock.STAGE_ORGANS_1 -> {
                if (!cleaver) return;
                drop = organsDrop(2);
                nextStage = CorpseBlock.STAGE_ORGANS_2;
                remove = false;
            }
            case CorpseBlock.STAGE_ORGANS_2 -> {
                if (!cleaver) return;
                drop = organsDrop(3);
                nextStage = CorpseBlock.STAGE_ORGANS_3;
                remove = true;
            }
            default -> {
                return;
            }
        }

        if (level instanceof ServerLevel serverLevel) {
            stack.hurtAndBreak(1, serverLevel, player, ignored -> {
            });

            if (remove) {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                level.levelEvent(2001, pos, Block.getId(Blocks.BONE_BLOCK.defaultBlockState()));
            } else {
                level.setBlock(pos, state.setValue(CorpseBlock.BLOCKSTATE, nextStage), 3);
                level.levelEvent(2001, pos, Block.getId(state));
            }

            CarcassBleedingHandler.playSound(level, pos, SoundEvents.AXE_STRIP, 0.3f);
            CarcassBleedingHandler.playSound(level, pos, SoundEvents.HONEY_BLOCK_STEP, 0.3f);
            CarcassLoot.spawn(serverLevel, pos, drop);
        }

        player.swing(InteractionHand.MAIN_HAND);
    }

    private static ResourceKey<LootTable> organsDrop(int index) {
        return net.minecraft.resources.ResourceKey.create(
                net.minecraft.core.registries.Registries.LOOT_TABLE,
                net.minecraft.resources.Identifier.parse("slaughter_hide:blocks/organs_drop_" + index));
    }
}
