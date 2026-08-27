package com.skd.slaughterhide.init;

import com.skd.slaughterhide.SlaughterHide;
import com.skd.slaughterhide.fluid.BloodFluid;
import com.skd.slaughterhide.fluid.InfectedBloodFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModFluids {
    public static final DeferredRegister<Fluid> REGISTRY =
            DeferredRegister.create(BuiltInRegistries.FLUID, SlaughterHide.MOD_ID);

    public static final DeferredHolder<Fluid, FlowingFluid> BLOOD =
            REGISTRY.register("blood", BloodFluid.Source::new);

    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_BLOOD =
            REGISTRY.register("flowing_blood", BloodFluid.Flowing::new);

    public static final DeferredHolder<Fluid, FlowingFluid> INFECTED_BLOOD =
            REGISTRY.register("infected_blood", InfectedBloodFluid.Source::new);

    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_INFECTED_BLOOD =
            REGISTRY.register("flowing_infected_blood", InfectedBloodFluid.Flowing::new);

    private ModFluids() {
    }
}
