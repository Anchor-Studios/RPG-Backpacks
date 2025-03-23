package net.rpgbackpacks.init;

import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import net.rpgbackpacks.client.renderer.LeatherBackpackRenderer;
import net.rpgbackpacks.client.renderer.IronBackpackRenderer;
import net.rpgbackpacks.client.renderer.GoldenBackpackRenderer;
import net.rpgbackpacks.client.model.Modelbackpack;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RpgBackpacksModCuriosRenderers {
	@SubscribeEvent
	public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions evt) {
		evt.registerLayerDefinition(RpgBackpacksModLayerDefinitions.LEATHER_BACKPACK, Modelbackpack::createBodyLayer);
		evt.registerLayerDefinition(RpgBackpacksModLayerDefinitions.IRON_BACKPACK, Modelbackpack::createBodyLayer);
		evt.registerLayerDefinition(RpgBackpacksModLayerDefinitions.GOLDEN_BACKPACK, Modelbackpack::createBodyLayer);
	}

	@SubscribeEvent
	public static void clientSetup(final FMLClientSetupEvent evt) {
		CuriosRendererRegistry.register(RpgBackpacksModItems.LEATHER_BACKPACK.get(), LeatherBackpackRenderer::new);
		CuriosRendererRegistry.register(RpgBackpacksModItems.IRON_BACKPACK.get(), IronBackpackRenderer::new);
		CuriosRendererRegistry.register(RpgBackpacksModItems.GOLDEN_BACKPACK.get(), GoldenBackpackRenderer::new);
	}
}
