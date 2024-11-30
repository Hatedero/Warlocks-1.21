package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.dash.PlayerDash;
import net.hatedero.warlocksmod.capability.gamerules.gravity.PlayerGravity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayerGravitySyncMessage(float Intensity, int Active) implements CustomPacketPayload {
    public static final Type<PlayerGravitySyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "gravity_sync"));
    public static final StreamCodec<ByteBuf, PlayerGravitySyncMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT,
            PlayerGravitySyncMessage::Intensity,
            ByteBufCodecs.VAR_INT,
            PlayerGravitySyncMessage::Active,
            PlayerGravitySyncMessage::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public class ClientPayloadHandler {
        public static void handleDataOnMain(final PlayerGravitySyncMessage message, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = Minecraft.getInstance().player;
                if (player != null) {
                    PlayerGravity cap = (PlayerGravity) player.getData(ModAttachment.PLAYER_GRAVITY);
                    cap.setIntensity(message.Intensity);
                    cap.setActive(message.Active);
                }
            });
        }
    }

    public class ServerPayloadHandler {
        public static void handleDataOnMain(final PlayerGravitySyncMessage data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = context.player();
                ((PlayerGravity) player.getData(ModAttachment.PLAYER_GRAVITY)).setIntensity(data.Intensity);
                ((PlayerGravity) player.getData(ModAttachment.PLAYER_GRAVITY)).setActive(data.Active);
            });
        }
    }
}