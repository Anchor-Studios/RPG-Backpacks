package com.anchorstudios.rpgbackpacks.recipes;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe; // Changed from SmithingTransformRecipe
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BackpackSmithingRecipeSerializer implements RecipeSerializer<BackpackSmithingRecipe> {
    public static final BackpackSmithingRecipeSerializer INSTANCE = new BackpackSmithingRecipeSerializer();

    @Override
    public @NotNull BackpackSmithingRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
        Ingredient template = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "template"));
        Ingredient base = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "base"));
        Ingredient addition = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));
        ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result")); // Changed to use ShapedRecipe
        return new BackpackSmithingRecipe(recipeId, template, base, addition, result);
    }

    @Nullable
    @Override
    public BackpackSmithingRecipe fromNetwork(@NotNull ResourceLocation recipeId, @NotNull FriendlyByteBuf buffer) {
        Ingredient template = Ingredient.fromNetwork(buffer);
        Ingredient base = Ingredient.fromNetwork(buffer);
        Ingredient addition = Ingredient.fromNetwork(buffer);
        ItemStack result = buffer.readItem();
        return new BackpackSmithingRecipe(recipeId, template, base, addition, result);
    }

    @Override
    public void toNetwork(@NotNull FriendlyByteBuf buffer, @NotNull BackpackSmithingRecipe recipe) {
        recipe.getTemplate().toNetwork(buffer); // Use getter method
        recipe.getBase().toNetwork(buffer);     // Use getter method
        recipe.getAddition().toNetwork(buffer); // Use getter method
        buffer.writeItem(recipe.getResultItem(null));
    }
}