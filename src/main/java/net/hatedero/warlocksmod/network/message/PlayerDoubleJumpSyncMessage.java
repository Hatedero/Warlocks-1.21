package net.hatedero.warlocksmod.network.message;

import io.netty.buffer.ByteBuf;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.IDoubleJump;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record PlayerDoubleJumpSyncMessage(int nbDoubleJump) implements CustomPacketPayload {
    public static final Type<PlayerDoubleJumpSyncMessage> TYPE = new Type(WarlocksMod.asResource("doublejumpsync"));
    public static final StreamCodec<ByteBuf, PlayerDoubleJumpSyncMessage> STREAM_CODEC;
    public PlayerDoubleJumpSyncMessage(int nbDoubleJump ) {
        this.nbDoubleJump = nbDoubleJump;
    }

    public static void serverHandle(PlayerDoubleJumpSyncMessage message, IPayloadContext context) {
    }

    public static void clientHandle(PlayerDoubleJumpSyncMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                IDoubleJump cap = (IDoubleJump)player.getData(ModAttachment.PLAYER_DOUBLE_JUMP);
                cap.setNbDoubleJump(message.nbDoubleJump);
            }

        });
    }
    @NotNull
    public CustomPacketPayload.@NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public int WarlocksMod() {
        return this.nbDoubleJump;
    }

    static {
        STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, PlayerDoubleJumpSyncMessage::nbDoubleJump, PlayerDoubleJumpSyncMessage::new);
    }

    public static void clientHandle(CustomPacketPayload customPacketPayload, IPayloadContext iPayloadContext) {
        iPayloadContext.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                IDoubleJump cap = (IDoubleJump)player.getData(ModAttachment.PLAYER_DOUBLE_JUMP);
                cap.setNbDoubleJump(2);
                cap.setCooldown(0);
            }

        });
    }

    public static void serverHandle(CustomPacketPayload customPacketPayload, IPayloadContext iPayloadContext) {

    }
}
