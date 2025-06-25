package com.anchorstudios.rpgbackpacks.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.crafting.Ingredient;

public class BackpackArmorMaterial implements ArmorMaterial {

    @Override
    public int getDurabilityForType(Type type) {
        return 0; // No durability
    }

    @Override
    public int getDefenseForType(Type type) {
        return 0; // No protection
    }

    @Override
    public int getEnchantmentValue() {
        return 0; // No enchantability
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER; // Or any custom sound
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }

    @Override
    public String getName() {
        return "backpack"; // Used in texture path: assets/.../textures/models/armor/backpack_layer_1.png
    }

    @Override
    public float getToughness() {
        return 0.0f;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0f;
    }
}