package net.rpgbackpacks.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.rpgbackpacks.world.inventory.NetheriteBackpackGUIMenu;
import net.rpgbackpacks.world.inventory.LeatherBackpackGUIMenu;
import net.rpgbackpacks.world.inventory.IronBackpackGUIMenu;
import net.rpgbackpacks.world.inventory.GoldenBackpackGUIMenu;
import net.rpgbackpacks.world.inventory.DiamondBackpackGUIMenu;
import net.rpgbackpacks.network.RpgBackpacksModVariables;
import net.rpgbackpacks.init.RpgBackpacksModItems;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import io.netty.buffer.Unpooled;

public class OpenBackpackOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(RpgBackpacksModItems.LEATHER_BACKPACK.get(), lv).isPresent() : false) {
			if (entity instanceof LivingEntity lv) {
				CuriosApi.getCuriosHelper().findCurios(lv, RpgBackpacksModItems.LEATHER_BACKPACK.get()).forEach(item -> {
					ItemStack itemstackiterator = item.stack();
					{
						String _setval = itemstackiterator.getDisplayName().getString();
						entity.getCapability(RpgBackpacksModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.bptitle = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				});
			}
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("LeatherBackpackGUI");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new LeatherBackpackGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		} else if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(RpgBackpacksModItems.IRON_BACKPACK.get(), lv).isPresent() : false) {
			if (entity instanceof LivingEntity lv) {
				CuriosApi.getCuriosHelper().findCurios(lv, RpgBackpacksModItems.IRON_BACKPACK.get()).forEach(item -> {
					ItemStack itemstackiterator = item.stack();
					{
						String _setval = itemstackiterator.getDisplayName().getString();
						entity.getCapability(RpgBackpacksModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.bptitle = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				});
			}
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("IronBackpackGUI");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new IronBackpackGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		} else if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(RpgBackpacksModItems.GOLDEN_BACKPACK.get(), lv).isPresent() : false) {
			if (entity instanceof LivingEntity lv) {
				CuriosApi.getCuriosHelper().findCurios(lv, RpgBackpacksModItems.GOLDEN_BACKPACK.get()).forEach(item -> {
					ItemStack itemstackiterator = item.stack();
					{
						String _setval = itemstackiterator.getDisplayName().getString();
						entity.getCapability(RpgBackpacksModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.bptitle = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				});
			}
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("GoldenBackpackGUI");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new GoldenBackpackGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		} else if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(RpgBackpacksModItems.DIAMOND_BACKPACK.get(), lv).isPresent() : false) {
			if (entity instanceof LivingEntity lv) {
				CuriosApi.getCuriosHelper().findCurios(lv, RpgBackpacksModItems.DIAMOND_BACKPACK.get()).forEach(item -> {
					ItemStack itemstackiterator = item.stack();
					{
						String _setval = itemstackiterator.getDisplayName().getString();
						entity.getCapability(RpgBackpacksModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.bptitle = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				});
			}
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("DiamondBackpackGUI");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new DiamondBackpackGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		} else if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(RpgBackpacksModItems.NETHERITE_BACKPACK.get(), lv).isPresent() : false) {
			if (entity instanceof LivingEntity lv) {
				CuriosApi.getCuriosHelper().findCurios(lv, RpgBackpacksModItems.NETHERITE_BACKPACK.get()).forEach(item -> {
					ItemStack itemstackiterator = item.stack();
					{
						String _setval = itemstackiterator.getDisplayName().getString();
						entity.getCapability(RpgBackpacksModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.bptitle = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				});
			}
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("NetheriteBackpackGUI");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new NetheriteBackpackGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
	}
}
