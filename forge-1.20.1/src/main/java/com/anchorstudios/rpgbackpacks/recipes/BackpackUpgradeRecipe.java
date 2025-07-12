package com.anchorstudios.rpgbackpacks.recipes;

import com.anchorstudios.rpgbackpacks.items.BackpackItem;
import com.anchorstudios.rpgbackpacks.items.BackpackItems;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class BackpackUpgradeRecipe extends ShapedRecipe {
    private final ItemStack result;
    private final Ingredient inputBackpack;

    public BackpackUpgradeRecipe(ResourceLocation id, String group, CraftingBookCategory category, int width, int height,
                                 NonNullList<Ingredient> ingredients, ItemStack result,
                                 Ingredient inputBackpack) {
        super(id, group, category, width, height, ingredients, result);
        this.result = result;
        this.inputBackpack = inputBackpack;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingContainer container, @NotNull RegistryAccess registryAccess) {
        // Find the backpack in the crafting grid
        ItemStack oldBackpack = findBackpackInGrid(container);
        if (oldBackpack.isEmpty() || !inputBackpack.test(oldBackpack)) {
            return ItemStack.EMPTY;
        }

        // Create new backpack with same inventory
        ItemStack newBackpack = this.result.copy();
        transferInventory(oldBackpack, newBackpack);

        return newBackpack;
    }

    private ItemStack findBackpackInGrid(CraftingContainer container) {
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (stack.getItem() instanceof BackpackItem) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    private void transferInventory(ItemStack oldBackpack, ItemStack newBackpack) {
        oldBackpack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(oldHandler -> {
            newBackpack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(newHandler -> {
                // Transfer items from old to new backpack
                for (int i = 0; i < Math.min(oldHandler.getSlots(), newHandler.getSlots()); i++) {
                    ItemStack stack = oldHandler.getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        newHandler.insertItem(i, stack.copy(), false);
                    }
                }
            });
        });
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE; // Changed from BackpackRecipeSerializer.INSTANCE
    }

    public static class Serializer implements RecipeSerializer<BackpackUpgradeRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public @NotNull BackpackUpgradeRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            // Get the category (default to MISC if not specified)
            CraftingBookCategory category = CraftingBookCategory.CODEC.byName(GsonHelper.getAsString(json, "category", null), CraftingBookCategory.MISC);

            ShapedRecipe shaped = RecipeSerializer.SHAPED_RECIPE.fromJson(recipeId, json);
            ItemStack result = shaped.getResultItem(RegistryAccess.EMPTY);

            JsonObject inputBackpackJson = GsonHelper.getAsJsonObject(json, "input_backpack");
            Ingredient inputBackpack = Ingredient.fromJson(inputBackpackJson);

            return new BackpackUpgradeRecipe(
                    recipeId,
                    shaped.getGroup(),
                    category,
                    shaped.getWidth(),
                    shaped.getHeight(),
                    shaped.getIngredients(),
                    result,
                    inputBackpack
            );
        }

        @Override
        public BackpackUpgradeRecipe fromNetwork(@NotNull ResourceLocation recipeId, @NotNull FriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            CraftingBookCategory category = buffer.readEnum(CraftingBookCategory.class);
            int width = buffer.readVarInt();
            int height = buffer.readVarInt();

            NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);
            for(int i = 0; i < ingredients.size(); ++i) {
                ingredients.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack result = buffer.readItem();
            Ingredient inputBackpack = Ingredient.fromNetwork(buffer);

            return new BackpackUpgradeRecipe(recipeId, group, category, width, height, ingredients, result, inputBackpack);
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buffer, @NotNull BackpackUpgradeRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeEnum(recipe.category());
            buffer.writeVarInt(recipe.getWidth());
            buffer.writeVarInt(recipe.getHeight());

            for(Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.getResultItem(RegistryAccess.EMPTY));
            recipe.inputBackpack.toNetwork(buffer);
        }
    }
}