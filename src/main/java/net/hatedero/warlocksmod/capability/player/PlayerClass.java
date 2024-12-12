package net.hatedero.warlocksmod.capability.player;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IDoubleJump;
import net.hatedero.warlocksmod.capability.playerinterfaces.IClass;
import net.hatedero.warlocksmod.network.message.PlayerClassSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerDoubleJumpSyncMessage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import static net.hatedero.warlocksmod.Config.maxDJC;
import static net.hatedero.warlocksmod.Config.maxDJN;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_CLASS;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DOUBLE_JUMP;

public class PlayerClass implements IClass, INBTSerializable<CompoundTag> {
    String className = "";

    public PlayerClass(){}

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public void setClassName(String n) {
        this.className = n;
    }

    @Override
    public void tick(Player player) {
        updateData(player);
    }

    @Override
    public void updateData(Player player) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerClassSyncMessage(this.className), new CustomPacketPayload[0]);
    }

    @Override
    public void resetData(Player player) {
        player.getData(PLAYER_CLASS).setClassName("");
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("player_class", this.className);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.className = nbt.getString("player_class");
    }


}
