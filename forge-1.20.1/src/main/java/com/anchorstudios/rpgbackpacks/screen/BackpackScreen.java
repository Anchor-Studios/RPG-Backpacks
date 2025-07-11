package com.anchorstudios.rpgbackpacks.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BackpackScreen extends AbstractContainerScreen<BackpackMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("rpgbackpacks", "textures/gui/backpack.png");

    // Texture regions (pixel heights)
    private static final int TOP_HEIGHT = 17;
    private static final int MIDDLE_HEIGHT = 18;
    private static final int BOTTOM_HEIGHT = 96;
    private static final int TOTAL_TEXTURE_HEIGHT = 167; // 17 + (3*18) + 96

    public BackpackScreen(BackpackMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageHeight = TOP_HEIGHT + (menu.getRows() * MIDDLE_HEIGHT) + BOTTOM_HEIGHT;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // Draw top texture (first 17 pixels)
        guiGraphics.blit(TEXTURE, x, y,
                0, 0,
                this.imageWidth, TOP_HEIGHT,
                this.imageWidth, TOTAL_TEXTURE_HEIGHT);

        // Draw middle sections (cycle through the 3 available middle segments)
        for (int i = 0; i < menu.getRows(); i++) {
            int middleSegment = i % 3; // Cycle through 0,1,2 for the 3 middle textures
            guiGraphics.blit(TEXTURE, x, y + TOP_HEIGHT + i * MIDDLE_HEIGHT,
                    0, TOP_HEIGHT + (middleSegment * MIDDLE_HEIGHT),
                    this.imageWidth, MIDDLE_HEIGHT,
                    this.imageWidth, TOTAL_TEXTURE_HEIGHT);
        }

        // Draw bottom texture (last 96 pixels)
        guiGraphics.blit(TEXTURE, x, y + TOP_HEIGHT + menu.getRows() * MIDDLE_HEIGHT,
                0, TOTAL_TEXTURE_HEIGHT - BOTTOM_HEIGHT,
                this.imageWidth, BOTTOM_HEIGHT,
                this.imageWidth, TOTAL_TEXTURE_HEIGHT);
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
        guiGraphics.drawString(this.font, this.playerInventoryTitle,
                8, this.imageHeight - 96 + 2, 0x404040, false);
    }
}