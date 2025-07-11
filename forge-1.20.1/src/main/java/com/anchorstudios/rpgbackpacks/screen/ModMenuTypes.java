package com.anchorstudios.rpgbackpacks.screen;

import com.anchorstudios.rpgbackpacks.RPGBackpacks;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, RPGBackpacks.MODID);

    public static final RegistryObject<MenuType<BackpackMenu>> BACKPACK = MENUS.register("backpack",
            () -> IForgeMenuType.create((windowId, inv, data) -> {
                // Read the item stack from the network data
                ItemStack stack = data.readItem();
                // Get the capability from the item stack
                IItemHandler handler = stack.getCapability(ForgeCapabilities.ITEM_HANDLER)
                        .orElse(new ItemStackHandler(27)); // Default size if capability is missing
                return new BackpackMenu(windowId, inv, handler, stack); // Pass the stack as the 4th parameter
            }));
}