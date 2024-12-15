package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IBlackHole;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IParry;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayerParrySyncMessage(int active, int activeMax, int cooldown, int cooldownMax) implements CustomPacketPayload {
    public static final Type<PlayerParrySyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "parry_sync"));
    public static final StreamCodec<ByteBuf, PlayerParrySyncMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            PlayerParrySyncMessage::active,
            ByteBufCodecs.VAR_INT,
            PlayerParrySyncMessage::activeMax,
            ByteBufCodecs.VAR_INT,
            PlayerParrySyncMessage::cooldown,
            ByteBufCodecs.VAR_INT,
            PlayerParrySyncMessage::cooldownMax,
            PlayerParrySyncMessage::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public class ClientPayloadHandler {
        public static void handleDataOnMain(final PlayerParrySyncMessage message, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = Minecraft.getInstance().player;
                if (player != null) {
                    IParry cap = (IParry) player.getData(ModAttachment.PLAYER_PARRY);
                    cap.setActive(message.active);
                    cap.setActiveMax(message.activeMax);
                    cap.setCooldown(message.cooldown);
                    cap.setCooldownMax(message.cooldownMax());
                }
            });
        }
    }

    public class ServerPayloadHandler {
        public static void handleDataOnMain(final PlayerParrySyncMessage message, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = context.player();
                IParry cap = (IParry) player.getData(ModAttachment.PLAYER_PARRY);
                cap.setActive(message.active);
                cap.setActiveMax(message.activeMax);
                cap.setCooldown(message.cooldown);
                cap.setCooldownMax(message.cooldownMax());
            });
        }
    }
}