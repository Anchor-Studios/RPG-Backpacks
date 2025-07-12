package com.anchorstudios.rpgbackpacks.recipes;

import com.anchorstudios.rpgbackpacks.items.BackpackItem;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class BackpackSmithingRecipe extends SmithingTransformRecipe {
    private final Ingredient template;
    private final Ingredient base;
    private final Ingredient addition;
    private final ItemStack result;

    public BackpackSmithingRecipe(ResourceLocation id, Ingredient template, Ingredient base, Ingredient addition, ItemStack result) {
        super(id, template, base, addition, result);
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    // Add getter methods
    public Ingredient getTemplate() {
        return this.template;
    }

    public Ingredient getBase() {
        return this.base;
    }

    public Ingredient getAddition() {
        return this.addition;
    }

    // Correct method signature for 1.20.1
    @Override
    public @NotNull ItemStack assemble(@NotNull Container container, @NotNull RegistryAccess registryAccess) {
        // Get input items from the container
        ItemStack template = container.getItem(0);
        ItemStack base = container.getItem(1);
        ItemStack addition = container.getItem(2);

        ItemStack result = this.getResultItem(registryAccess).copy();

        // Transfer inventory from old backpack to new one
        base.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(oldHandler -> {
            result.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(newHandler -> {
                for (int i = 0; i < Math.min(oldHandler.getSlots(), newHandler.getSlots()); i++) {
                    ItemStack stack = oldHandler.getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        newHandler.insertItem(i, stack.copy(), false);
                    }
                }
            });
        });

        return result;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result.copy();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return BackpackSmithingRecipeSerializer.INSTANCE;
    }
}