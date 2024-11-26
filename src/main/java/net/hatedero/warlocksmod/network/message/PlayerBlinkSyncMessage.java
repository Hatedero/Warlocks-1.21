package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.blink.PlayerBlink;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayerBlinkSyncMessage(int cooldown, int nbBlink) implements CustomPacketPayload {
    public static final Type<PlayerBlinkSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "blink_sync"));
    public static final StreamCodec<ByteBuf, PlayerBlinkSyncMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            PlayerBlinkSyncMessage::cooldown,
            ByteBufCodecs.VAR_INT,
            PlayerBlinkSyncMessage::nbBlink,
            PlayerBlinkSyncMessage::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public class ClientPayloadHandler {
        public static void handleDataOnMain(final PlayerBlinkSyncMessage message, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = Minecraft.getInstance().player;
                if (player != null) {
                    PlayerBlink cap = (PlayerBlink) player.getData(ModAttachment.PLAYER_BLINK);
                    cap.setCooldown(message.cooldown);
                    cap.setNbBlink(message.nbBlink);
                }
            });
        }
    }

    public class ServerPayloadHandler {
        public static void handleDataOnMain(final PlayerBlinkSyncMessage data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = context.player();
                ((PlayerBlink) player.getData(ModAttachment.PLAYER_BLINK)).setCooldown(data.cooldown);
                ((PlayerBlink) player.getData(ModAttachment.PLAYER_BLINK)).setNbBlink(data.nbBlink);
            });
        }
    }
}