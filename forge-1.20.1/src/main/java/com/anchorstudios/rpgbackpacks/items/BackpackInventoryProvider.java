package com.anchorstudios.rpgbackpacks.items;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class BackpackInventoryProvider implements ICapabilityProvider {
    private final LazyOptional<IItemHandler> optional = LazyOptional.of(this::createHandler);
    private final int size;

    public BackpackInventoryProvider(int size) {
        this.size = size;
    }

    private IItemHandler createHandler() {
        return new ItemStackHandler(size) {
            @Override
            public int getSlotLimit(int slot) {
                return 64;
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, optional);
    }

    public static IItemHandler getInventory(ItemStack stack) {
        return stack.getCapability(ForgeCapabilities.ITEM_HANDLER)
                .orElseThrow(() -> new IllegalStateException("Backpack has no inventory"));
    }
}