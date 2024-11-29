package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.thundersnap.PlayerThunderSnap;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IInfinity;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IThunderSnap;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayerInfinitySyncMessage(int cooldown, int range, int activeTime, int active) implements CustomPacketPayload {
    public static final Type<PlayerInfinitySyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "infinity_sync"));
    public static final StreamCodec<ByteBuf, PlayerInfinitySyncMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            PlayerInfinitySyncMessage::cooldown,
            ByteBufCodecs.VAR_INT,
            PlayerInfinitySyncMessage::range,
            ByteBufCodecs.VAR_INT,
            PlayerInfinitySyncMessage::activeTime,
            ByteBufCodecs.VAR_INT,
            PlayerInfinitySyncMessage::active,
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
                //player.sendSystemMessage(Component.literal("sent a client packet"));
                if (player != null) {
                    IInfinity cap = (IInfinity) player.getData(ModAttachment.PLAYER_INFINITY);
                    cap.setCooldown(message.cooldown);
                    cap.setRange(message.range);
                    cap.setActiveTime(message.activeTime);
                    if(message.active == 0)
                        cap.setActive(false);
                    else
                        cap.setActive(true);
                }
            });
        }
    }

    public class ServerPayloadHandler {
        public static void handleDataOnMain(final PlayerInfinitySyncMessage data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = context.player();
                player.sendSystemMessage(Component.literal("sent a server packet"));
                ((IInfinity) player.getData(ModAttachment.PLAYER_INFINITY)).setCooldown(data.cooldown);
                ((IInfinity) player.getData(ModAttachment.PLAYER_INFINITY)).setRange(data.range);
                ((IInfinity) player.getData(ModAttachment.PLAYER_INFINITY)).setActiveTime(data.activeTime);
                if(data.active == 0)
                    ((IInfinity) player.getData(ModAttachment.PLAYER_INFINITY)).setActive(false);
                else
                    ((IInfinity) player.getData(ModAttachment.PLAYER_INFINITY)).setActive(true);
            });
        }
    }
}