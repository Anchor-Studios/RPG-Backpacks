
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.rpgbackpacks.init;

import net.rpgbackpacks.item.LeatherBackpackItem;
import net.rpgbackpacks.item.IronBackpackItem;
import net.rpgbackpacks.item.GoldenBackpackItem;
import net.rpgbackpacks.RpgBackpacksMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

public class RpgBackpacksModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, RpgBackpacksMod.MODID);
	public static final RegistryObject<Item> LEATHER_BACKPACK = REGISTRY.register("leather_backpack", () -> new LeatherBackpackItem());
	public static final RegistryObject<Item> IRON_BACKPACK = REGISTRY.register("iron_backpack", () -> new IronBackpackItem());
	public static final RegistryObject<Item> GOLDEN_BACKPACK = REGISTRY.register("golden_backpack", () -> new GoldenBackpackItem());
	// Start of user code block custom items
	// End of user code block custom items
}
