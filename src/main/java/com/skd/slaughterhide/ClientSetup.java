package com.skd.slaughterhide;

import com.skd.slaughterhide.client.particle.FreezerSmokeParticle;
import com.skd.slaughterhide.init.ModFluidTypes;
import com.skd.slaughterhide.init.ModFluids;
import com.skd.slaughterhide.init.ModParticleTypes;
import com.skd.slaughterhide.menu.FreezerScreen;
import com.skd.slaughterhide.menu.MeatGrinderScreen;
import com.skd.slaughterhide.menu.PestleAndMortarScreen;
import com.skd.slaughterhide.menu.TaxidermyTableScreen;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.joml.Vector4f;

import javax.annotation.Nullable;

public class ClientSetup {
	@SubscribeEvent
	public static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
		event.register(com.skd.slaughterhide.init.ModMenus.FREEZER.get(), FreezerScreen::new);
		event.register(com.skd.slaughterhide.init.ModMenus.MEAT_GRINDER.get(), MeatGrinderScreen::new);
		event.register(com.skd.slaughterhide.init.ModMenus.PESTLE_AND_MORTAR.get(), PestleAndMortarScreen::new);
		event.register(com.skd.slaughterhide.init.ModMenus.TAXIDERMY_TABLE.get(), TaxidermyTableScreen::new);
	}

	@SubscribeEvent
	public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ModParticleTypes.FREEZER_SMOKE.get(), FreezerSmokeParticle.Provider::new);
	}

	@SubscribeEvent
	public static void onRegisterFluidModels(RegisterFluidModelsEvent event) {
		event.register(
				new FluidModel.Unbaked(
						new Material(Identifier.parse(SlaughterHide.MOD_ID + ":block/blood_still")),
						new Material(Identifier.parse(SlaughterHide.MOD_ID + ":block/blood_flow")),
						null, null),
				ModFluids.BLOOD, ModFluids.FLOWING_BLOOD);
		event.register(
				new FluidModel.Unbaked(
						new Material(Identifier.parse(SlaughterHide.MOD_ID + ":block/infected_blood_still")),
						new Material(Identifier.parse(SlaughterHide.MOD_ID + ":block/infected_blood_flow")),
						null, null),
				ModFluids.INFECTED_BLOOD, ModFluids.FLOWING_INFECTED_BLOOD);
	}

	@SubscribeEvent
	public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {
			@Override
			public void modifyFogColor(Camera camera, float partialTick, ClientLevel level,
										int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
				fluidFogColor.set(0.38431373f, 0.0f, 0.0f, fluidFogColor.w);
			}

			@Override
			public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment,
										float renderDistance, float partialTick, FogData fogData) {
				fogData.environmentalStart = 0.0f;
				fogData.environmentalEnd = Math.min(48.0f, renderDistance);
			}
		}, new Holder[]{ModFluidTypes.BLOOD_TYPE});

		event.registerFluidType(new IClientFluidTypeExtensions() {
			@Override
			public void modifyFogColor(Camera camera, float partialTick, ClientLevel level,
										int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
				fluidFogColor.set(0.15f, 0.3f, 0.1f, fluidFogColor.w);
			}

			@Override
			public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment,
										float renderDistance, float partialTick, FogData fogData) {
				fogData.environmentalStart = 0.0f;
				fogData.environmentalEnd = Math.min(48.0f, renderDistance);
			}
		}, new Holder[]{ModFluidTypes.INFECTED_BLOOD_TYPE});
	}
}
