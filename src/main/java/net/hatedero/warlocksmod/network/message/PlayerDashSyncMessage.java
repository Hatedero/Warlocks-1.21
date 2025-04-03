package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.abilities.dash.PlayerDash;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayerDashSyncMessage(int cooldown, int nbDash) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<PlayerDashSyncMessage> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "dash_sync"));
    public static final StreamCodec<ByteBuf, PlayerDashSyncMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            PlayerDashSyncMessage::cooldown,
            ByteBufCodecs.VAR_INT,
            PlayerDashSyncMessage::nbDash,
            PlayerDashSyncMessage::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public class ClientPayloadHandler {
        public static void handleDataOnMain(final PlayerDashSyncMessage message, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = Minecraft.getInstance().player;
                if (player != null) {
                    PlayerDash cap = (PlayerDash) player.getData(ModAttachment.PLAYER_DASH);
                    cap.setCooldown(message.cooldown);
                    cap.setNbDash(message.nbDash);
                }
            });
        }
    }

    public class ServerPayloadHandler {
        public static void handleDataOnMain(final PlayerDashSyncMessage data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = context.player();
                ((PlayerDash) player.getData(ModAttachment.PLAYER_DASH)).setCooldown(data.cooldown);
                ((PlayerDash) player.getData(ModAttachment.PLAYER_DASH)).setNbDash(data.nbDash);
            });
        }
    }
}