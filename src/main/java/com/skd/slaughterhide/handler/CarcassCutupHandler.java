package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.CarcassBlockProperty;
import com.skd.slaughterhide.CarcassDefinition;
import com.skd.slaughterhide.block.DrainedCarcassBlock;
import com.skd.slaughterhide.block.entity.CarcassBlockEntity;
import com.skd.slaughterhide.tag.ModItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * State machine for cutting up a drained carcass, exactly like the original's
 * {@code <Mob>CutUp} procedures:
 * <pre>
 *   stage 0  + cleaver       → head drop      → stage 6
 *   stage 6  + skinning knife → skin drop      → stage 7
 *   stage 7  + cleaver       → cut_1 drop     → stage 8
 *   stage 8  + cleaver       → cut_2 drop     → stage 9
 *   stage 9  + cleaver       → cut_3 drop     → block removed
 * </pre>
 * Every hit damages the tool, swings, cracks the block and plays the two
 * original sounds. Loot tables are resolved from the {@link CarcassDefinition}.
 */
public final class CarcassCutupHandler {
    private CarcassCutupHandler() {
    }

    public static void handle(Level level, BlockPos pos, Player player,
                              CarcassBlockEntity blockEntity, CarcassDefinition definition) {
        if (!blockEntity.isDrained()) {
            return;
        }
        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof DrainedCarcassBlock)) {
            return;
        }

        ItemStack stack = player.getMainHandItem();
        boolean cleaver = stack.is(ModItemTags.CLEAVER);
        boolean knife = stack.is(ModItemTags.SKINNING_KNIVES);

        int stage = state.getValue(CarcassBlockProperty.DRAINED_BLOCKSTATE);
        ResourceKey<LootTable> drop;
        int nextStage;
        boolean remove;
        switch (stage) {
            case DrainedCarcassBlock.STAGE_UNTOUCHED -> {
                if (!cleaver) return;
                drop = definition.headDropTable();
                nextStage = DrainedCarcassBlock.STAGE_HEAD_CUT;
                remove = false;
            }
            case DrainedCarcassBlock.STAGE_HEAD_CUT -> {
                if (!knife) return;
                drop = definition.skinDropTable();
                nextStage = DrainedCarcassBlock.STAGE_SKINNED;
                remove = false;
            }
            case DrainedCarcassBlock.STAGE_SKINNED -> {
                if (!cleaver) return;
                drop = definition.cutDropTable(1);
                nextStage = DrainedCarcassBlock.STAGE_CUT_1;
                remove = false;
            }
            case DrainedCarcassBlock.STAGE_CUT_1 -> {
                if (!cleaver) return;
                drop = definition.cutDropTable(2);
                nextStage = DrainedCarcassBlock.STAGE_CUT_2;
                remove = false;
            }
            case DrainedCarcassBlock.STAGE_CUT_2 -> {
                if (!cleaver) return;
                drop = definition.cutDropTable(3);
                nextStage = DrainedCarcassBlock.STAGE_UNTOUCHED;
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
                level.setBlock(pos, state.setValue(CarcassBlockProperty.DRAINED_BLOCKSTATE, nextStage), 3);
                level.levelEvent(2001, pos, Block.getId(state));
            }

            CarcassBleedingHandler.playSound(level, pos, SoundEvents.AXE_STRIP, 0.3f);
            CarcassBleedingHandler.playSound(level, pos, SoundEvents.HONEY_BLOCK_STEP, 0.3f);
            CarcassLoot.spawn(serverLevel, pos, drop);
        }

        player.swing(InteractionHand.MAIN_HAND);
    }
}