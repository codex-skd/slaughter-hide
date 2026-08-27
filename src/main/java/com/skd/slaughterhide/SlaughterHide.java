package com.skd.slaughterhide;

import com.skd.slaughterhide.config.SlaughterHideConfig;
import com.skd.slaughterhide.handler.BloodSplatterInteractionHandler;
import com.skd.slaughterhide.handler.CarcassDeathHandler;
import com.skd.slaughterhide.handler.CarcassInteractionHandler;
import com.skd.slaughterhide.handler.CorpseInteractionHandler;
import com.skd.slaughterhide.handler.HookPlacementHandler;
import com.skd.slaughterhide.init.ModBlockEntities;
import com.skd.slaughterhide.init.ModBlocks;
import com.skd.slaughterhide.init.ModCreativeTabs;
import com.skd.slaughterhide.init.ModFluidTypes;
import com.skd.slaughterhide.init.ModFluids;
import com.skd.slaughterhide.init.ModItems;
import com.skd.slaughterhide.init.ModMenus;
import com.skd.slaughterhide.init.ModParticleTypes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(SlaughterHide.MOD_ID)
public class SlaughterHide {
    public static final String MOD_ID = "slaughter_hide";

    public SlaughterHide(IEventBus modEventBus) {
        ModFluidTypes.REGISTRY.register(modEventBus);
        ModFluids.REGISTRY.register(modEventBus);
        ModBlocks.REGISTRY.register(modEventBus);
        ModItems.REGISTRY.register(modEventBus);
        ModBlockEntities.REGISTRY.register(modEventBus);
        ModMenus.REGISTRY.register(modEventBus);
        ModParticleTypes.REGISTRY.register(modEventBus);
        ModCreativeTabs.REGISTRY.register(modEventBus);
        modEventBus.addListener(SlaughterHideConfig::registerCommon);
        modEventBus.addListener(ClientSetup::onRegisterMenuScreens);
        modEventBus.addListener(ClientSetup::onRegisterFluidModels);
        modEventBus.addListener(ClientSetup::onRegisterClientExtensions);

        NeoForge.EVENT_BUS.register(CarcassDeathHandler.class);
        NeoForge.EVENT_BUS.register(CarcassInteractionHandler.class);
        NeoForge.EVENT_BUS.register(CorpseInteractionHandler.class);
        NeoForge.EVENT_BUS.register(HookPlacementHandler.class);
        NeoForge.EVENT_BUS.register(BloodSplatterInteractionHandler.class);
        NeoForge.EVENT_BUS.register(ServerWorkScheduler.class);
    }
}
