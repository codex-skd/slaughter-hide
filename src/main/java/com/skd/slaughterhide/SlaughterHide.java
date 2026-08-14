package com.skd.slaughterhide;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(SlaughterHide.MOD_ID)
public class SlaughterHide {
    public static final String MOD_ID = "slaughter_hide";

    public SlaughterHide(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register(this);
    }
}
