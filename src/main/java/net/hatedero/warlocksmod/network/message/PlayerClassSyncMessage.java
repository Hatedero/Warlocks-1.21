package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.playerInterfaces.IClass;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayerClassSyncMessage(String className) implements CustomPacketPayload {
    public static final Type<PlayerClassSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "class_sync"));
    public static final StreamCodec<ByteBuf, PlayerClassSyncMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            PlayerClassSyncMessage::className,
            PlayerClassSyncMessage::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public class ClientPayloadHandler {
        public static void handleDataOnMain(final PlayerClassSyncMessage message, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = Minecraft.getInstance().player;
                if (player != null) {
                    IClass cap = (IClass) player.getData(ModAttachment.PLAYER_CLASS);
                    cap.setClassName(message.className);
                }
            });
        }
    }

    public class ServerPayloadHandler {
        public static void handleDataOnMain(final PlayerClassSyncMessage data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = context.player();
                if (player != null) {
                    IClass cap = (IClass) player.getData(ModAttachment.PLAYER_CLASS);
                    cap.setClassName(data.className);
                }
            });
        }
    }
}
