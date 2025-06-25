package com.anchorstudios.rpgbackpacks.keybinds;

import com.anchorstudios.rpgbackpacks.RPGBackpacks;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = RPGBackpacks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OpenBackpack {
    // Define the key mapping as a constant
    public static final KeyMapping OPEN_BACKPACK = new KeyMapping(
            "key.rpgbackpacks.open_backpack",
            GLFW.GLFW_KEY_B,
            "key.categories.inventory"
    );

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_BACKPACK);
    }
}