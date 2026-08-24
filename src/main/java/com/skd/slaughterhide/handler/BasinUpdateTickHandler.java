package com.skd.slaughterhide.handler;

import com.skd.slaughterhide.block.BasinBlock;
import com.skd.slaughterhide.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.Random;

public final class BasinUpdateTickHandler {
    private BasinUpdateTickHandler() {
    }

    public static void tick(ServerLevel level, BlockPos pos, BlockState state) {
        int currentState = state.getValue(BasinBlock.BLOCKSTATE);
        RandomSource random = level.getRandom();

        // Simplified tick logic: basin slowly fills with water when exposed to sky during day, then evaporates producing salt
        if (currentState == 0) {
            // Empty basin - chance to collect rain water
            if (level.isBrightOutside() && level.canSeeSkyFromBelowWater(pos) && level.isRaining() && random.nextFloat() < 0.05f) {
                setBlockState(level, pos, state, 1);
            }
        } else if (currentState >= 1 && currentState <= 6) {
            // Water evaporating stages - only during day, not raining, sky exposed
            if (level.isBrightOutside() && level.canSeeSkyFromBelowWater(pos) && !level.isRaining()) {
                // Random chance to advance evaporation
                if (random.nextFloat() < 0.1f) {
                    int nextState = currentState + 1;
                    setBlockState(level, pos, state, nextState);
                    // At state 7, salt is "produced"
                    if (nextState == 7) {
                        // Salt is ready to collect at state 7
                    }
                }
            } else if (level.isRaining() && currentState < 7) {
                // Rain refills
                if (random.nextFloat() < 0.05f) {
                    setBlockState(level, pos, state, Math.min(currentState + 1, 7));
                }
            }
        } else if (currentState == 7) {
            // Full salt - stays at 7 until collected
        }
    }

    public static boolean tryFillWithWaterBucket(Level level, BlockPos pos, BlockState state) {
        int currentState = state.getValue(BasinBlock.BLOCKSTATE);
        if (currentState == 0) {
            setBlockState(level, pos, state, 1);
            return true;
        }
        return false;
    }

    public static boolean tryEmptyWithBucket(Level level, BlockPos pos, BlockState state) {
        int currentState = state.getValue(BasinBlock.BLOCKSTATE);
        if (currentState == 1) {
            setBlockState(level, pos, state, 0);
            return true;
        }
        return false;
    }

    public static boolean tryCollectSalt(Level level, BlockPos pos, BlockState state) {
        int currentState = state.getValue(BasinBlock.BLOCKSTATE);
        if (currentState == 7) {
            setBlockState(level, pos, state, 0);
            // Drop salt item
            if (!level.isClientSide()) {
                // No standalone "salt" item exists in this port (only the salt_block
                // decorative block from an earlier session) -- drop that instead.
                ItemStack saltStack = new ItemStack(ModBlocks.SALT_BLOCK.get().asItem());
                saltStack.setCount(1 + level.getRandom().nextInt(2)); // 1-2 salt
                net.minecraft.world.entity.item.ItemEntity entity = new net.minecraft.world.entity.item.ItemEntity(level, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, saltStack);
                entity.setPickUpDelay(10);
                level.addFreshEntity(entity);
            }
            return true;
        }
        return false;
    }

    private static void setBlockState(LevelAccessor level, BlockPos pos, BlockState state, int newState) {
        IntegerProperty property = (IntegerProperty) state.getBlock().getStateDefinition().getProperty("blockstate");
        if (property != null && property.getPossibleValues().contains(newState)) {
            level.setBlock(pos, state.setValue(property, newState), 3);
        }
    }
}