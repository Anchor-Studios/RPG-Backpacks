package net.rpgbackpacks.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.rpgbackpacks.init.RpgBackpacksModItems;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.function.Supplier;
import java.util.Map;

public class ReverseDBGUIIsClosedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double slot = 0;
		double i = 0;
		entity.getPersistentData().putBoolean("didjob", true);
		if (entity instanceof LivingEntity lv) {
			CuriosApi.getCuriosHelper().findCurios(lv, RpgBackpacksModItems.DIAMOND_BACKPACK.get()).forEach(item -> {
				ItemStack itemstackiterator = item.stack();
				entity.getPersistentData().putDouble("slti", (itemstackiterator.getOrCreateTag().getCompound("Inventory").getInt("Size")));
				entity.getPersistentData().putDouble("bpi", 0);
				for (int index0 = 0; index0 < (int) entity.getPersistentData().getDouble("slti"); index0++) {
					{
						ItemStack _isc = itemstackiterator;
						final ItemStack _setstack = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt
								? ((Slot) _slt.get((int) entity.getPersistentData().getDouble("bpi"))).getItem()
								: ItemStack.EMPTY).copy();
						final int _sltid = (int) entity.getPersistentData().getDouble("bpi");
						_setstack.setCount(new Object() {
							public int getAmount(int sltid) {
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
									if (stack != null)
										return stack.getCount();
								}
								return 0;
							}
						}.getAmount((int) entity.getPersistentData().getDouble("bpi")));
						_isc.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable itemHandlerModifiable) {
								itemHandlerModifiable.setStackInSlot(_sltid, _setstack);
							}
						});
					}
					entity.getPersistentData().putDouble("bpi", (entity.getPersistentData().getDouble("bpi") + 1));
				}
			});
		}
	}
}
