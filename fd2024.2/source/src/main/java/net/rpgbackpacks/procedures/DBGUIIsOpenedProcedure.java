package net.rpgbackpacks.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.rpgbackpacks.init.RpgBackpacksModItems;

import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.function.Supplier;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

public class DBGUIIsOpenedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double slot = 0;
		double i = 0;
		if (entity.getPersistentData().getBoolean("didjob")) {
			if (entity instanceof LivingEntity lv) {
				CuriosApi.getCuriosHelper().findCurios(lv, RpgBackpacksModItems.DIAMOND_BACKPACK.get()).forEach(item -> {
					ItemStack itemstackiterator = item.stack();
					entity.getPersistentData().putDouble("slti", (itemstackiterator.getOrCreateTag().getCompound("Inventory").getInt("Size")));
					entity.getPersistentData().putDouble("bpi", 0);
					for (int index0 = 0; index0 < (int) entity.getPersistentData().getDouble("slti"); index0++) {
						if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
							ItemStack _setstack = (new Object() {
								public ItemStack getItemStack(int sltid, ItemStack _isc) {
									AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
									_isc.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
										_retval.set(capability.getStackInSlot(sltid).copy());
									});
									return _retval.get();
								}
							}.getItemStack((int) entity.getPersistentData().getDouble("bpi"), itemstackiterator)).copy();
							_setstack.setCount((new Object() {
								public ItemStack getItemStack(int sltid, ItemStack _isc) {
									AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
									_isc.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
										_retval.set(capability.getStackInSlot(sltid).copy());
									});
									return _retval.get();
								}
							}.getItemStack((int) entity.getPersistentData().getDouble("bpi"), itemstackiterator)).getCount());
							((Slot) _slots.get((int) entity.getPersistentData().getDouble("bpi"))).set(_setstack);
							_player.containerMenu.broadcastChanges();
						}
						entity.getPersistentData().putDouble("bpi", (entity.getPersistentData().getDouble("bpi") + 1));
					}
				});
			}
			entity.getPersistentData().putBoolean("didjob", false);
		}
	}
}
