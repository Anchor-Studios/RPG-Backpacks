package com.anchorstudios.rpgbackpacks.screen;

import com.anchorstudios.rpgbackpacks.screen.BackpackMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BackpackScreen extends AbstractContainerScreen<BackpackMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("rpgbackpacks", "textures/gui/backpack.png");

    public BackpackScreen(BackpackMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageHeight = 114 + menu.getRows() * 18;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // Draw top texture
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, 17);

        // Draw each row
        for (int i = 0; i < menu.getRows(); i++) {
            guiGraphics.blit(TEXTURE, x, y + 17 + i * 18, 0, 17, this.imageWidth, 18);
        }

        // Draw bottom texture
        guiGraphics.blit(TEXTURE, x, y + 17 + menu.getRows() * 18, 0, 35, this.imageWidth, 97);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, 8, 6, 0x404040, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 96 + 2, 0x404040, false);
    }
}