package com.skd.slaughterhide.init;

import com.skd.slaughterhide.SlaughterHide;
import com.skd.slaughterhide.menu.FreezerMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModMenus {
    public static final DeferredRegister<MenuType<?>> REGISTRY =
            DeferredRegister.create(Registries.MENU, SlaughterHide.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<FreezerMenu>> FREEZER =
            REGISTRY.register("freezer",
                    () -> IMenuTypeExtension.create(FreezerMenu::new));

    private ModMenus() {
    }
}
