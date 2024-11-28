package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IBlackHole;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayerBlackHoleSyncMessage(int cooldown, int strength, int life) implements CustomPacketPayload {
    public static final Type<PlayerBlackHoleSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "black_hole_sync"));
    public static final StreamCodec<ByteBuf, PlayerBlackHoleSyncMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            PlayerBlackHoleSyncMessage::cooldown,
            ByteBufCodecs.VAR_INT,
            PlayerBlackHoleSyncMessage::strength,
            ByteBufCodecs.VAR_INT,
            PlayerBlackHoleSyncMessage::life,
            PlayerBlackHoleSyncMessage::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public class ClientPayloadHandler {
        public static void handleDataOnMain(final PlayerBlackHoleSyncMessage message, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = Minecraft.getInstance().player;
                if (player != null) {
                    IBlackHole cap = (IBlackHole) player.getData(ModAttachment.PLAYER_BLACK_HOLE);
                    cap.setCooldown(message.cooldown);
                    cap.setStrength(message.strength);
                    cap.setLife(message.life);
                }
            });
        }
    }

    public class ServerPayloadHandler {
        public static void handleDataOnMain(final PlayerBlackHoleSyncMessage data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = context.player();
                ((IBlackHole) player.getData(ModAttachment.PLAYER_BLACK_HOLE)).setCooldown(data.cooldown);
                ((IBlackHole) player.getData(ModAttachment.PLAYER_BLACK_HOLE)).setStrength(data.strength);
                ((IBlackHole) player.getData(ModAttachment.PLAYER_BLACK_HOLE)).setLife(data.life);
            });
        }
    }
}