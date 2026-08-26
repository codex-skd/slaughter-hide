package com.skd.slaughterhide.init;

import com.skd.slaughterhide.SlaughterHide;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY =
			DeferredRegister.create(Registries.PARTICLE_TYPE, SlaughterHide.MOD_ID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FREEZER_SMOKE =
			REGISTRY.register("freezersmoke", () -> new SimpleParticleType(false));

	private ModParticleTypes() {
	}
}
