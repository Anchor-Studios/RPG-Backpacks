package com.anchorstudios.rpgbackpacks;

import com.anchorstudios.rpgbackpacks.attributes.ModAttributes;
import com.anchorstudios.rpgbackpacks.creativetabs.RPGBackpacksCreativeTab;
import com.anchorstudios.rpgbackpacks.items.BackpackItems;
import com.anchorstudios.rpgbackpacks.keybinds.OpenBackpack;
import com.anchorstudios.rpgbackpacks.screen.BackpackMenu;
import com.anchorstudios.rpgbackpacks.screen.BackpackScreen;
import com.anchorstudios.rpgbackpacks.screen.ModMenuTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        // Don't need to register the event bus here since we're using @EventBusSubscriber
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("RPG Backpacks common setup complete");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.BACKPACK.get(), BackpackScreen::new);

        // No need to register keybind events here - handled by @EventBusSubscriber
    }
}