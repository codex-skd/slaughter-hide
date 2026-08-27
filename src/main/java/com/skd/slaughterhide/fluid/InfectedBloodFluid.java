package com.skd.slaughterhide.fluid;

import com.skd.slaughterhide.init.ModBlocks;
import com.skd.slaughterhide.init.ModFluidTypes;
import com.skd.slaughterhide.init.ModFluids;
import com.skd.slaughterhide.init.ModItems;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;

public abstract class InfectedBloodFluid extends BaseFlowingFluid {
    public static final BaseFlowingFluid.Properties PROPERTIES =
            new BaseFlowingFluid.Properties(
                    () -> (FluidType) ModFluidTypes.INFECTED_BLOOD_TYPE.get(),
                    () -> (Fluid) ModFluids.INFECTED_BLOOD.get(),
                    () -> (Fluid) ModFluids.FLOWING_INFECTED_BLOOD.get())
                    .explosionResistance(10.0f)
                    .bucket(() -> ModItems.INFECTED_BLOOD_BUCKET.get())
                    .block(() -> ModBlocks.INFECTED_BLOOD.get());

    protected InfectedBloodFluid() {
        super(PROPERTIES);
    }

    @Override
    public ParticleOptions getDripParticle() {
        return ParticleTypes.EXPLOSION;
    }

    public static class Flowing extends InfectedBloodFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends InfectedBloodFluid {
        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
