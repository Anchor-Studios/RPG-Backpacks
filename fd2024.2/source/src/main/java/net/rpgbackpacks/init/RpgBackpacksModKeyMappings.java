
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.rpgbackpacks.init;

import org.lwjgl.glfw.GLFW;

import net.rpgbackpacks.network.OpenBackpackMessage;
import net.rpgbackpacks.RpgBackpacksMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class RpgBackpacksModKeyMappings {
	public static final KeyMapping OPEN_BACKPACK = new KeyMapping("key.rpg_backpacks.open_backpack", GLFW.GLFW_KEY_B, "key.categories.gameplay") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				RpgBackpacksMod.PACKET_HANDLER.sendToServer(new OpenBackpackMessage(0, 0));
				OpenBackpackMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(OPEN_BACKPACK);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				OPEN_BACKPACK.consumeClick();
			}
		}
	}
}
