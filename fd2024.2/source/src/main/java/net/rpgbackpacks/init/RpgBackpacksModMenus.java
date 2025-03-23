
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.rpgbackpacks.init;

import net.rpgbackpacks.world.inventory.LeatherBackpackGUIMenu;
import net.rpgbackpacks.world.inventory.LBGUIMenu;
import net.rpgbackpacks.world.inventory.IronBackpackGUIMenu;
import net.rpgbackpacks.world.inventory.IBGUIMenu;
import net.rpgbackpacks.world.inventory.GoldenBackpackGUIMenu;
import net.rpgbackpacks.world.inventory.GBGUIMenu;
import net.rpgbackpacks.RpgBackpacksMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

public class RpgBackpacksModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, RpgBackpacksMod.MODID);
	public static final RegistryObject<MenuType<LeatherBackpackGUIMenu>> LEATHER_BACKPACK_GUI = REGISTRY.register("leather_backpack_gui", () -> IForgeMenuType.create(LeatherBackpackGUIMenu::new));
	public static final RegistryObject<MenuType<LBGUIMenu>> LBGUI = REGISTRY.register("lbgui", () -> IForgeMenuType.create(LBGUIMenu::new));
	public static final RegistryObject<MenuType<IBGUIMenu>> IBGUI = REGISTRY.register("ibgui", () -> IForgeMenuType.create(IBGUIMenu::new));
	public static final RegistryObject<MenuType<IronBackpackGUIMenu>> IRON_BACKPACK_GUI = REGISTRY.register("iron_backpack_gui", () -> IForgeMenuType.create(IronBackpackGUIMenu::new));
	public static final RegistryObject<MenuType<GBGUIMenu>> GBGUI = REGISTRY.register("gbgui", () -> IForgeMenuType.create(GBGUIMenu::new));
	public static final RegistryObject<MenuType<GoldenBackpackGUIMenu>> GOLDEN_BACKPACK_GUI = REGISTRY.register("golden_backpack_gui", () -> IForgeMenuType.create(GoldenBackpackGUIMenu::new));
}
