package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IDash;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record PlayerDashSyncMessage(int nbDash) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<PlayerDashSyncMessage> TYPE = new CustomPacketPayload.Type(WarlocksMod.asResource("dashsync"));
    public static final StreamCodec<ByteBuf, PlayerDashSyncMessage> STREAM_CODEC;
    public PlayerDashSyncMessage(int nbDash ) {
        this.nbDash = nbDash;
    }

    public static void serverHandle(PlayerDashSyncMessage message, IPayloadContext context) {
    }

    public static void clientHandle(PlayerDashSyncMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                IDash cap = (IDash)player.getData(ModAttachment.PLAYER_DASH);
                cap.setNbDash(message.nbDash);
            }

        });
    }
    @NotNull
    public CustomPacketPayload.@NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public int WarlocksMod() {
        return this.nbDash;
    }

    static {
        STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, PlayerDashSyncMessage::nbDash, PlayerDashSyncMessage::new);
    }

    public static void clientHandle(CustomPacketPayload customPacketPayload, IPayloadContext iPayloadContext) {
        iPayloadContext.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                IDash cap = (IDash)player.getData(ModAttachment.PLAYER_DASH);
                cap.setNbDash(1);
            }

        });
    }

    public static void serverHandle(CustomPacketPayload customPacketPayload, IPayloadContext iPayloadContext) {

    }
}
