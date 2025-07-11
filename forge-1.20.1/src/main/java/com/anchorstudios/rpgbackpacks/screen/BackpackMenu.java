package com.anchorstudios.rpgbackpacks.screen;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BackpackMenu extends AbstractContainerMenu {
    private final IItemHandler itemHandler;
    private final int rows;

    public BackpackMenu(int windowId, Inventory playerInventory, IItemHandler itemHandler) {
        super(ModMenuTypes.BACKPACK.get(), windowId);
        this.itemHandler = itemHandler;
        this.rows = itemHandler.getSlots() / 9;

        // Add backpack slots
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new SlotItemHandler(itemHandler, j + i * 9, 8 + j * 18, 18 + i * 18));
            }
        }

        // Add player inventory (moved up by 18 pixels/1 slot)
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 85 + i * 18 + (rows > 3 ? (rows - 3) * 18 : 0)));
            }
        }

        // Add player hotbar (moved up by 18 pixels/1 slot)
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 143 + (rows > 3 ? (rows - 3) * 18 : 0)));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < this.rows * 9) {
                if (!this.moveItemStackTo(itemstack1, this.rows * 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.rows * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public int getRows() {
        return rows;
    }
}