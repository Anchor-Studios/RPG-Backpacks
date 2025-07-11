package com.anchorstudios.rpgbackpacks.items;

import com.anchorstudios.rpgbackpacks.attributes.ModAttributes;
import com.anchorstudios.rpgbackpacks.items.models.backpack;
import com.anchorstudios.rpgbackpacks.keybinds.OpenBackpack;
import com.anchorstudios.rpgbackpacks.screen.BackpackMenu;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
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
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            openScreen(serverPlayer, stack);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    public static void openScreen(Player player, ItemStack backpack) {
        if (player.level().isClientSide || !(player instanceof ServerPlayer serverPlayer)) return;

        NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider(
                (windowId, playerInventory, playerEntity) ->
                        new BackpackMenu(windowId, playerInventory,
                                BackpackInventoryProvider.getInventory(backpack),
                                backpack),
                backpack.getHoverName()
        ), buf -> buf.writeItem(backpack));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        if (Minecraft.getInstance() != null) {
            Player player = Minecraft.getInstance().player;
            if (player != null && (player.getItemBySlot(EquipmentSlot.CHEST).equals(stack))) {
                String keyName = OpenBackpack.OPEN_BACKPACK.getTranslatedKeyMessage().getString();
                tooltip.add(Component.translatable("tooltip.rpgbackpacks.backpack_open_info", keyName)
                        .withStyle(ChatFormatting.GRAY));
            }
        }
    }

    public static boolean isBackpack(ItemStack stack) {
        return stack.getItem() instanceof BackpackItem;
    }

    public static void openFromChestSlot(ServerPlayer player) {
        ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);
        if (chestItem.getItem() instanceof BackpackItem) {
            openScreen(player, chestItem);
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.CHEST) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(ModAttributes.STORAGE.get(), new AttributeModifier(
                    UUID.fromString("1d5f9d32-7a77-4b39-9f07-f055f3cbf1c7"),
                    "Storage Capacity",
                    storageSlots.get(),
                    AttributeModifier.Operation.ADDITION
            ));
            return builder.build();
        }
        return super.getAttributeModifiers(slot, stack);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new BackpackInventoryProvider(storageSlots.get());
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private backpack<LivingEntity> model;

            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack,
                                                          EquipmentSlot slot, HumanoidModel<?> defaultModel) {
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

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(stack.getItem());
        return id != null ?
                String.format("%s:textures/item/%s.png", id.getNamespace(), id.getPath()) :
                "rpgbackpacks:textures/item/leather_backpack.png";
    }
}