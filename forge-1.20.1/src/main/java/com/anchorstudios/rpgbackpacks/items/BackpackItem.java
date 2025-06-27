package com.anchorstudios.rpgbackpacks.items;

import com.anchorstudios.rpgbackpacks.attributes.ModAttributes;
import com.anchorstudios.rpgbackpacks.items.models.backpack;
import com.anchorstudios.rpgbackpacks.keybinds.OpenBackpack;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BackpackItem extends ArmorItem {

    private final Supplier<Integer> storageSlots;

    public BackpackItem(ArmorMaterial material, Type slot, Properties properties, Supplier<Integer> storageSlots) {
        super(material, slot, properties);
        this.storageSlots = storageSlots;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // Don't auto-equip. Do nothing (for now) — just return the item.
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        // Only show tooltip if this item is worn in the chest slot
        if (Minecraft.getInstance() != null) {
            Player player = Minecraft.getInstance().player;
            if (player != null && player.getItemBySlot(EquipmentSlot.CHEST).equals(stack)) {
                String keyName = "B"; // Default fallback
                if (OpenBackpack.OPEN_BACKPACK != null) {
                    keyName = OpenBackpack.OPEN_BACKPACK.getTranslatedKeyMessage().getString();
                }

                tooltip.add(Component.translatable("tooltip.rpgbackpacks.backpack_open_info", keyName)
                        .withStyle(ChatFormatting.GRAY));
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.CHEST) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

            AttributeModifier modifier = new AttributeModifier(
                    UUID.fromString("1d5f9d32-7a77-4b39-9f07-f055f3cbf1c7"),
                    "Storage Capacity",
                    storageSlots.get(),
                    AttributeModifier.Operation.ADDITION
            );

            builder.put(ModAttributes.STORAGE.get(), modifier);
            return builder.build();
        }

        return ImmutableMultimap.of();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private backpack<LivingEntity> model;

            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                if (slot == EquipmentSlot.CHEST) {
                    if (model == null) {
                        model = new backpack<>(Minecraft.getInstance().getEntityModels().bakeLayer(backpack.LAYER_LOCATION));
                    }
                    return model;
                }
                return defaultModel;
            }
        });
    }


}