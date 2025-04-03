package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.abilities.doublejump.PlayerDoubleJump;
import net.hatedero.warlocksmod.capability.abilitiesInterfaces.IDoubleJump;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayerDoubleJumpSyncMessage(int cooldown, int nbDoubleJump) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<PlayerDoubleJumpSyncMessage> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "double_jump_sync"));
    public static final StreamCodec<ByteBuf, PlayerDoubleJumpSyncMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            PlayerDoubleJumpSyncMessage::cooldown,
            ByteBufCodecs.VAR_INT,
            PlayerDoubleJumpSyncMessage::nbDoubleJump,
            PlayerDoubleJumpSyncMessage::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public class ClientPayloadHandler {
        public static void handleDataOnMain(final PlayerDoubleJumpSyncMessage message, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = Minecraft.getInstance().player;
                if (player != null) {
                    IDoubleJump cap = (IDoubleJump) player.getData(ModAttachment.PLAYER_DOUBLE_JUMP);
                    cap.setCooldown(message.cooldown);
                    cap.setNbDoubleJump(message.nbDoubleJump);
                }
            });
        }
    }

    public class ServerPayloadHandler {
        public static void handleDataOnMain(final PlayerDoubleJumpSyncMessage data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = context.player();
                ((PlayerDoubleJump) player.getData(ModAttachment.PLAYER_DOUBLE_JUMP)).setCooldown(data.cooldown);
                ((PlayerDoubleJump) player.getData(ModAttachment.PLAYER_DOUBLE_JUMP)).setNbDoubleJump(data.nbDoubleJump);
            });
        }
    }
}
