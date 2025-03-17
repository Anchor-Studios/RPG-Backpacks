package net.rpgbackpacks.procedures;

import net.minecraft.world.entity.Entity;

public class PlayerJoinsProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putBoolean("didjob", true);
	}
}
