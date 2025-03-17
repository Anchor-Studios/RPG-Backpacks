package net.rpgbackpacks.procedures;

import net.rpgbackpacks.network.RpgBackpacksModVariables;

import net.minecraft.world.entity.Entity;

public class DisplayNameReturnProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return ((entity.getCapability(RpgBackpacksModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RpgBackpacksModVariables.PlayerVariables())).bptitle).substring(1,
				(int) (((entity.getCapability(RpgBackpacksModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RpgBackpacksModVariables.PlayerVariables())).bptitle).length() - 1));
	}
}
