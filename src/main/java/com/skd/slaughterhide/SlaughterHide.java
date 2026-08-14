package com.skd.slaughterhide;

import com.skd.slaughterhide.config.SlaughterHideConfig;
import com.skd.slaughterhide.handler.CarcassDeathHandler;
import com.skd.slaughterhide.handler.CarcassInteractionHandler;
import com.skd.slaughterhide.init.ModBlockEntities;
import com.skd.slaughterhide.init.ModBlocks;
import com.skd.slaughterhide.init.ModCreativeTabs;
import com.skd.slaughterhide.init.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(SlaughterHide.MOD_ID)
public class SlaughterHide {
    public static final String MOD_ID = "slaughter_hide";

    public SlaughterHide(IEventBus modEventBus) {
        ModBlocks.REGISTRY.register(modEventBus);
        ModItems.REGISTRY.register(modEventBus);
        ModBlockEntities.REGISTRY.register(modEventBus);
        ModCreativeTabs.REGISTRY.register(modEventBus);
        modEventBus.addListener(SlaughterHideConfig::registerCommon);

        NeoForge.EVENT_BUS.register(CarcassDeathHandler.class);
        NeoForge.EVENT_BUS.register(CarcassInteractionHandler.class);
        NeoForge.EVENT_BUS.register(ServerWorkScheduler.class);
    }
}