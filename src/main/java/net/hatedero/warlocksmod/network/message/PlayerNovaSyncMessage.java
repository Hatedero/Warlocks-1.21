package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.thundersnap.PlayerThunderSnap;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.INova;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IThunderSnap;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayerNovaSyncMessage(int charge, int chargeMax, int chargeMin, int cooldown, int cooldownMax, float power) implements CustomPacketPayload {
    public static final Type<PlayerNovaSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "nova_sync"));
    public static final StreamCodec<ByteBuf, PlayerNovaSyncMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            PlayerNovaSyncMessage::charge,
            ByteBufCodecs.VAR_INT,
            PlayerNovaSyncMessage::chargeMax,
            ByteBufCodecs.VAR_INT,
            PlayerNovaSyncMessage::chargeMin,
            ByteBufCodecs.VAR_INT,
            PlayerNovaSyncMessage::cooldown,
            ByteBufCodecs.VAR_INT,
            PlayerNovaSyncMessage::cooldownMax,
            ByteBufCodecs.FLOAT,
            PlayerNovaSyncMessage::power,
            PlayerNovaSyncMessage::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public class ClientPayloadHandler {
        public static void handleDataOnMain(final PlayerNovaSyncMessage message, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = Minecraft.getInstance().player;
                if (player != null) {
                    INova cap = (INova) player.getData(ModAttachment.PLAYER_NOVA);
                    cap.setCharge(message.charge);
                    cap.setChargeMax(message.chargeMax);
                    cap.setChargeMin(message.chargeMin);
                    cap.setCooldown(message.cooldown);
                    cap.setCooldownMax(message.cooldownMax);
                    cap.setPower(message.power);
                }
            });
        }
    }

    public class ServerPayloadHandler {
        public static void handleDataOnMain(final PlayerNovaSyncMessage data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = context.player();
                INova cap = (INova) player.getData(ModAttachment.PLAYER_NOVA);
                cap.setCharge(data.charge);
                cap.setChargeMax(data.chargeMax);
                cap.setChargeMin(data.chargeMin);
                cap.setCooldown(data.cooldown);
                cap.setCooldownMax(data.cooldownMax);
                cap.setPower(data.power);
            });
        }
    }
}