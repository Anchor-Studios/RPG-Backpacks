package com.anchorstudios.rpgbackpacks.keybinds;

import com.anchorstudios.rpgbackpacks.RPGBackpacks;
import com.anchorstudios.rpgbackpacks.items.BackpackItem;
import com.anchorstudios.rpgbackpacks.packet.OpenBackpackPacket;
import com.anchorstudios.rpgbackpacks.packet.NetworkHandler;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = RPGBackpacks.MODID, value = Dist.CLIENT)
public class OpenBackpack {
    public static final KeyMapping OPEN_BACKPACK = new KeyMapping(
            "key.rpgbackpacks.open_backpack",
            GLFW.GLFW_KEY_B, // Default key is 'B'
            "key.categories.inventory"
    );

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player == null) return;

        if (OPEN_BACKPACK.consumeClick()) {
            // Check chest slot for backpack
            ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);
            if (chestItem.getItem() instanceof BackpackItem) {
                // Send packet to server to open backpack
                NetworkHandler.INSTANCE.sendToServer(new OpenBackpackPacket());
            }
        }
    }
}