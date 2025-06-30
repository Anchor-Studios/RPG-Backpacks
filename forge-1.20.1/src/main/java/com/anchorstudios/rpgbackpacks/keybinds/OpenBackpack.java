package com.anchorstudios.rpgbackpacks.keybinds;

import com.anchorstudios.rpgbackpacks.items.BackpackItem;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

public class OpenBackpack {
    public static final KeyMapping OPEN_BACKPACK = new KeyMapping(
            "key.rpgbackpacks.open_backpack",
            GLFW.GLFW_KEY_B,
            "key.categories.inventory"
    );

    @Mod.EventBusSubscriber(modid = "rpgbackpacks", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class KeyRegistration {
        @SubscribeEvent
        public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(OPEN_BACKPACK);
        }
    }

    @Mod.EventBusSubscriber(modid = "rpgbackpacks", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class KeyInputHandler {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || mc.screen != null) return;

            if (OPEN_BACKPACK.consumeClick()) {
                Player player = mc.player;

                // Check chest slot first
                ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);
                if (chestItem.getItem() instanceof BackpackItem) {
                    BackpackItem.openScreen(player, chestItem);
                    return;
                }

                // Then check hands
                for (InteractionHand hand : InteractionHand.values()) {
                    ItemStack stack = player.getItemInHand(hand);
                    if (stack.getItem() instanceof BackpackItem) {
                        BackpackItem.openScreen(player, stack);
                        return;
                    }
                }
            }
        }
    }
}