
package net.rpgbackpacks.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class BackpackLoaderItem extends Item {
	public BackpackLoaderItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
