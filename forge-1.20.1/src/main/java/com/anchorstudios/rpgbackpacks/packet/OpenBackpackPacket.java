package com.anchorstudios.rpgbackpacks.packet;

import com.anchorstudios.rpgbackpacks.items.BackpackItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenBackpackPacket {
    public OpenBackpackPacket() {} // Empty constructor

    public OpenBackpackPacket(FriendlyByteBuf buf) {} // Empty decoder

    public void encode(FriendlyByteBuf buf) {} // Empty encoder

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                BackpackItem.openFromChestSlot(player);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}