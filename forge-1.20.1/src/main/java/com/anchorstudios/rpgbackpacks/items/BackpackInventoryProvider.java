package com.anchorstudios.rpgbackpacks.items;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class BackpackInventoryProvider implements ICapabilitySerializable<CompoundTag> {
    private final ItemStackHandler handler;
    private final LazyOptional<IItemHandler> optional;

    public BackpackInventoryProvider(int size) {
        this.handler = new ItemStackHandler(size) {
            @Override
            public int getSlotLimit(int slot) {
                return 64;
            }

            @Override
            protected void onContentsChanged(int slot) {
                // Mark dirty when contents change
            }
        };
        this.optional = LazyOptional.of(() -> handler);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, optional);
    }

    @Override
    public CompoundTag serializeNBT() {
        return handler.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        handler.deserializeNBT(nbt);
    }

    public static IItemHandler getInventory(ItemStack stack) {
        return stack.getCapability(ForgeCapabilities.ITEM_HANDLER)
                .orElseThrow(() -> new IllegalStateException("Backpack has no inventory"));
    }
}