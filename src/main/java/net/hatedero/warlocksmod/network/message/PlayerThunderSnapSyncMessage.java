package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IDoubleJump;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IThunderSnap;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record PlayerThunderSnapSyncMessage(int cooldown, int strength) implements CustomPacketPayload {
    public static final Type<PlayerThunderSnapSyncMessage> TYPE = new Type(WarlocksMod.asResource("thundersnapsync"));
    public static final StreamCodec<ByteBuf, PlayerThunderSnapSyncMessage> STREAM_CODEC;
    public PlayerThunderSnapSyncMessage(int cooldown, int strength ) {
        this.cooldown = cooldown;
        this.strength = strength;
    }

    public static void serverHandle(PlayerThunderSnapSyncMessage message, IPayloadContext context) {
    }

    public static void clientHandle(PlayerThunderSnapSyncMessage message, IPayloadContext context) {

        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                IThunderSnap cap = (IThunderSnap)player.getData(ModAttachment.PLAYER_THUNDER_SNAP);
                cap.setCooldown(message.cooldown);
                cap.setStrength(message.strength);
            }
        });
    }
    @NotNull
    public CustomPacketPayload.@NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public int cooldown() {
        return this.cooldown;
    }

    public int strength() {
        return this.strength;
    }

    static {
        STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, PlayerThunderSnapSyncMessage::cooldown, ByteBufCodecs.INT, PlayerThunderSnapSyncMessage::strength, PlayerThunderSnapSyncMessage::new);
    }

    public static void clientHandle(CustomPacketPayload message, IPayloadContext iPayloadContext) {
        iPayloadContext.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                IThunderSnap cap = (IThunderSnap)player.getData(ModAttachment.PLAYER_THUNDER_SNAP);
                cap.setCooldown(0);
                cap.setStrength(5);
            }

        });
    }

    public static void serverHandle(CustomPacketPayload customPacketPayload, IPayloadContext iPayloadContext) {

    }
}
