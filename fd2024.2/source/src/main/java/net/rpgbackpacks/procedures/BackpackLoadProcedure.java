package net.rpgbackpacks.procedures;

import net.rpgbackpacks.init.RpgBackpacksModItems;
import net.rpgbackpacks.RpgBackpacksMod;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;

public class BackpackLoadProcedure {
	public static void execute(LevelAccessor world, ItemStack itemstack) {
		if (!(52 == itemstack.getOrCreateTag().getDouble("bploadvalue"))) {
			{
				ItemStack _isc = itemstack;
				final ItemStack _setstack = new ItemStack(RpgBackpacksModItems.BACKPACK_LOADER.get()).copy();
				final int _sltid = 0;
				_setstack.setCount(1);
				_isc.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable itemHandlerModifiable) {
						itemHandlerModifiable.setStackInSlot(_sltid, _setstack);
					}
				});
			}
			RpgBackpacksMod.queueServerWork(1, () -> {
				{
					ItemStack _isc = itemstack;
					final ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
					final int _sltid = 0;
					_setstack.setCount(1);
					_isc.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable itemHandlerModifiable) {
							itemHandlerModifiable.setStackInSlot(_sltid, _setstack);
						}
					});
				}
			});
			itemstack.getOrCreateTag().putDouble("bploadvalue", 52);
		}
	}
}
