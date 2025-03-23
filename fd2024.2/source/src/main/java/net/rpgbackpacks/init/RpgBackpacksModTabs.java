
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.rpgbackpacks.init;

import net.rpgbackpacks.RpgBackpacksMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

public class RpgBackpacksModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RpgBackpacksMod.MODID);
	public static final RegistryObject<CreativeModeTab> RPG_BACKPACKS = REGISTRY.register("rpg_backpacks",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.rpg_backpacks.rpg_backpacks")).icon(() -> new ItemStack(RpgBackpacksModItems.IRON_BACKPACK.get())).displayItems((parameters, tabData) -> {
				tabData.accept(RpgBackpacksModItems.LEATHER_BACKPACK.get());
				tabData.accept(RpgBackpacksModItems.IRON_BACKPACK.get());
				tabData.accept(RpgBackpacksModItems.GOLDEN_BACKPACK.get());
				tabData.accept(RpgBackpacksModItems.DIAMOND_BACKPACK.get());
				tabData.accept(RpgBackpacksModItems.NETHERITE_BACKPACK.get());
			}).withSearchBar().build());
}
