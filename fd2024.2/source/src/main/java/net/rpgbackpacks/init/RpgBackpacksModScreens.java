
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.rpgbackpacks.init;

import net.rpgbackpacks.client.gui.LeatherBackpackGUIScreen;
import net.rpgbackpacks.client.gui.LBGUIScreen;
import net.rpgbackpacks.client.gui.IronBackpackGUIScreen;
import net.rpgbackpacks.client.gui.IBGUIScreen;
import net.rpgbackpacks.client.gui.GoldenBackpackGUIScreen;
import net.rpgbackpacks.client.gui.GBGUIScreen;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RpgBackpacksModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(RpgBackpacksModMenus.LEATHER_BACKPACK_GUI.get(), LeatherBackpackGUIScreen::new);
			MenuScreens.register(RpgBackpacksModMenus.LBGUI.get(), LBGUIScreen::new);
			MenuScreens.register(RpgBackpacksModMenus.IBGUI.get(), IBGUIScreen::new);
			MenuScreens.register(RpgBackpacksModMenus.IRON_BACKPACK_GUI.get(), IronBackpackGUIScreen::new);
			MenuScreens.register(RpgBackpacksModMenus.GBGUI.get(), GBGUIScreen::new);
			MenuScreens.register(RpgBackpacksModMenus.GOLDEN_BACKPACK_GUI.get(), GoldenBackpackGUIScreen::new);
		});
	}
}
