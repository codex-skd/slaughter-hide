package com.skd.slaughterhide;

import com.skd.slaughterhide.client.particle.FreezerSmokeParticle;
import com.skd.slaughterhide.init.ModParticleTypes;
import com.skd.slaughterhide.menu.FreezerScreen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

public class ClientSetup {
	@SubscribeEvent
	public static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
		event.register(com.skd.slaughterhide.init.ModMenus.FREEZER.get(), FreezerScreen::new);
	}

	@SubscribeEvent
	public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ModParticleTypes.FREEZER_SMOKE.get(), FreezerSmokeParticle.Provider::new);
	}
}
