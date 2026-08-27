package com.skd.slaughterhide.init;

import com.skd.slaughterhide.SlaughterHide;
import com.skd.slaughterhide.fluid.types.BloodFluidType;
import com.skd.slaughterhide.fluid.types.InfectedBloodFluidType;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class ModFluidTypes {
    public static final DeferredRegister<FluidType> REGISTRY =
            DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, SlaughterHide.MOD_ID);

    public static final DeferredHolder<FluidType, FluidType> BLOOD_TYPE =
            REGISTRY.register("blood", BloodFluidType::new);

    public static final DeferredHolder<FluidType, FluidType> INFECTED_BLOOD_TYPE =
            REGISTRY.register("infected_blood", InfectedBloodFluidType::new);

    private ModFluidTypes() {
    }
}
