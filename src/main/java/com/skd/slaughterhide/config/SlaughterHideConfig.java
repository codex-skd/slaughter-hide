package com.skd.slaughterhide.config;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

/** Common config. Mirrors the original's {@code INSTANT_BLEED} toggle. */
public final class SlaughterHideConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.ConfigValue<Boolean> INSTANT_BLEED;

    static {
        BUILDER.push("general");
        INSTANT_BLEED = BUILDER.comment("Instantly bleed carcasses.")
                .define("instant_bleeding", false);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    private SlaughterHideConfig() {
    }

    public static void registerCommon(FMLConstructModEvent event) {
        event.enqueueWork(() -> {
            ModContainer container = net.neoforged.fml.ModList.get()
                    .getModContainerById("slaughter_hide").orElseThrow();
            container.registerConfig(ModConfig.Type.COMMON, SPEC, "SlaughterHide.toml");
        });
    }
}