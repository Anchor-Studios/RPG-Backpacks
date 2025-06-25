package com.anchorstudios.rpgbackpacks.items;

import com.anchorstudios.rpgbackpacks.Config;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraftforge.eventbus.api.IEventBus;
import com.anchorstudios.rpgbackpacks.RPGBackpacks;

public class BackpackItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RPGBackpacks.MODID);

    public static final RegistryObject<Item> LEATHER_BACKPACK = ITEMS.register("leather_backpack", () ->
            new BackpackItem(new BackpackArmorMaterial(), ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1),
                    () -> Config.COMMON.leatherBackpackSlots.get()));

    public static final RegistryObject<Item> IRON_BACKPACK = ITEMS.register("iron_backpack", () ->
            new BackpackItem(new BackpackArmorMaterial(), ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1),
                    () -> Config.COMMON.ironBackpackSlots.get()));

    public static final RegistryObject<Item> GOLDEN_BACKPACK = ITEMS.register("golden_backpack", () ->
            new BackpackItem(new BackpackArmorMaterial(), ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1),
                    () -> Config.COMMON.goldenBackpackSlots.get()));

    public static final RegistryObject<Item> DIAMOND_BACKPACK = ITEMS.register("diamond_backpack", () ->
            new BackpackItem(new BackpackArmorMaterial(), ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1),
                    () -> Config.COMMON.diamondBackpackSlots.get()));

    public static final RegistryObject<Item> NETHERITE_BACKPACK = ITEMS.register("netherite_backpack", () ->
            new BackpackItem(new BackpackArmorMaterial(), ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1),
                    () -> Config.COMMON.netheriteBackpackSlots.get()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}