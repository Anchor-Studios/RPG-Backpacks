package com.anchorstudios.rpgbackpacks;

import com.anchorstudios.rpgbackpacks.attributes.ModAttributes;
import com.anchorstudios.rpgbackpacks.creativetabs.RPGBackpacksCreativeTab;
import com.anchorstudios.rpgbackpacks.items.BackpackItems;
import com.anchorstudios.rpgbackpacks.keybinds.OpenBackpack;
import com.anchorstudios.rpgbackpacks.packet.NetworkHandler;
import com.anchorstudios.rpgbackpacks.screen.BackpackMenu;
import com.anchorstudios.rpgbackpacks.screen.BackpackScreen;
import com.anchorstudios.rpgbackpacks.screen.ModMenuTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

@Mod(RPGBackpacks.MODID)
public class RPGBackpacks {
    public static final String MODID = "rpgbackpacks";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RPGBackpacks(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register config first
        context.registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);

        // Register everything else
        ModAttributes.ATTRIBUTES.register(modEventBus);
        ModMenuTypes.MENUS.register(modEventBus);
        BackpackItems.register(modEventBus);
        RPGBackpacksCreativeTab.register(modEventBus);
        NetworkHandler.register();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        // Register our mod event bus for additional events
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("RPG Backpacks common setup complete");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.BACKPACK.get(), BackpackScreen::new);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(OpenBackpack.OPEN_BACKPACK);
        }
    }
}