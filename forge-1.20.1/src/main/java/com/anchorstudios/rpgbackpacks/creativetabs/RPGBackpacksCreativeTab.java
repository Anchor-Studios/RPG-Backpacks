package com.anchorstudios.rpgbackpacks.creativetabs;

import com.anchorstudios.rpgbackpacks.RPGBackpacks;
import com.anchorstudios.rpgbackpacks.items.BackpackItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.anchorstudios.rpgbackpacks.RPGBackpacks.MODID;

public class RPGBackpacksCreativeTab {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> BACKPACKS_TAB = TABS.register("backpacks_tab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.rpgbackpacks.backpacks_tab"))
                    .icon(() -> new ItemStack(BackpackItems.DIAMOND_BACKPACK.get()))
                    .displayItems((params, output) -> {
                        output.accept(BackpackItems.LEATHER_BACKPACK.get());
                        output.accept(BackpackItems.IRON_BACKPACK.get());
                        output.accept(BackpackItems.GOLDEN_BACKPACK.get());
                        output.accept(BackpackItems.DIAMOND_BACKPACK.get());
                        output.accept(BackpackItems.NETHERITE_BACKPACK.get());
                    })
                    .build()
    );

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }
}
