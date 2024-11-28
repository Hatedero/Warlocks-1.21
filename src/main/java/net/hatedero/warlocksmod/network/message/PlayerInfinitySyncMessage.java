package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.thundersnap.PlayerThunderSnap;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IThunderSnap;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayerInfinitySyncMessage(int cooldown, int strength) implements CustomPacketPayload {
    public static final Type<PlayerInfinitySyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "thunder_snap_sync"));
    public static final StreamCodec<ByteBuf, PlayerInfinitySyncMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            PlayerInfinitySyncMessage::cooldown,
            ByteBufCodecs.VAR_INT,
            PlayerInfinitySyncMessage::strength,
            PlayerInfinitySyncMessage::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public class ClientPayloadHandler {
        public static void handleDataOnMain(final PlayerInfinitySyncMessage message, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = Minecraft.getInstance().player;
                if (player != null) {
                    IThunderSnap cap = (IThunderSnap) player.getData(ModAttachment.PLAYER_THUNDER_SNAP);
                    cap.setCooldown(message.cooldown);
                    cap.setStrength(message.strength);
                }
            });
        }
    }

    public class ServerPayloadHandler {
        public static void handleDataOnMain(final PlayerInfinitySyncMessage data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = context.player();
                ((PlayerThunderSnap) player.getData(ModAttachment.PLAYER_THUNDER_SNAP)).setCooldown(data.cooldown);
                ((PlayerThunderSnap) player.getData(ModAttachment.PLAYER_THUNDER_SNAP)).setStrength(data.strength);
            });
        }
    }
}