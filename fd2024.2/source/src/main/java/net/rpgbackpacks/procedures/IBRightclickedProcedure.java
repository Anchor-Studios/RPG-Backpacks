package net.rpgbackpacks.procedures;

import net.rpgbackpacks.network.RpgBackpacksModVariables;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;

public class IBRightclickedProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		{
			String _setval = itemstack.getDisplayName().getString();
			entity.getCapability(RpgBackpacksModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.bptitle = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (itemstack.getItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()) {
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
		} else if (itemstack.getItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem()) {
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.OFF_HAND, true);
		} else {
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
		}
	}
}
