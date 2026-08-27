package com.skd.slaughterhide.init;

import com.skd.slaughterhide.SlaughterHide;
import com.skd.slaughterhide.menu.FreezerMenu;
import com.skd.slaughterhide.menu.MeatGrinderMenu;
import com.skd.slaughterhide.menu.PestleAndMortarMenu;
import com.skd.slaughterhide.menu.TaxidermyTableMenu;
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

    public static final DeferredHolder<MenuType<?>, MenuType<MeatGrinderMenu>> MEAT_GRINDER =
            REGISTRY.register("meat_grinder",
                    () -> IMenuTypeExtension.create(MeatGrinderMenu::new));

    public static final DeferredHolder<MenuType<?>, MenuType<PestleAndMortarMenu>> PESTLE_AND_MORTAR =
            REGISTRY.register("pestle_and_mortar",
                    () -> IMenuTypeExtension.create(PestleAndMortarMenu::new));

    public static final DeferredHolder<MenuType<?>, MenuType<TaxidermyTableMenu>> TAXIDERMY_TABLE =
            REGISTRY.register("taxidermy_table",
                    () -> IMenuTypeExtension.create(TaxidermyTableMenu::new));

    private ModMenus() {
    }
}
