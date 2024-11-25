package net.hatedero.warlocksmod.doublejump;

import net.hatedero.warlocksmod.capability.IDoubleJump;
import net.hatedero.warlocksmod.network.message.PlayerDoubleJumpSyncMessage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DOUBLE_JUMP;

public class PlayerDoubleJump implements IDoubleJump, INBTSerializable<CompoundTag> {
    int nbDoubleJump = 0;
    int nbDoubleJumpMax = 2;
    int nbDoubleJumpMin = 0;
    int cooldownMax = 20;
    int cooldownMin = 0;
    int cooldown = cooldownMax;

    public PlayerDoubleJump(){}

    @Override
    public int getNbDoubleJump() {
        return nbDoubleJump;
    }

    @Override
    public void setNbDoubleJump(int n) {
        this.nbDoubleJump = n;
    }

    @Override
    public int getNbDoubleJumpMax() {
        return nbDoubleJumpMax;
    }

    @Override
    public void setNbDoubleJumpMax(int n) {
        this.nbDoubleJumpMax = n;
    }

    @Override
    public int getNbDoubleJumpMin() {
        return nbDoubleJumpMin;
    }

    @Override
    public void setNbDoubleJumpMin(int n) {
        this.nbDoubleJumpMin = n;
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }

    @Override
    public void setCooldown(int n) {
        this.cooldown = n;
    }

    @Override
    public int getCooldownMax() {
        return cooldownMax;
    }

    @Override
    public void setCooldownMax(int n) {
        this.cooldownMax = n;
    }

    @Override
    public int getCooldownMin() {
        return cooldownMin;
    }

    @Override
    public void setCooldownMin(int n) {
        this.cooldownMin = n;
    }

    @Override
    public void tick(Player player) {
        if(player.onGround()) {
            player.getData(PLAYER_DOUBLE_JUMP).setNbDoubleJump(nbDoubleJumpMax);
            player.getData(PLAYER_DOUBLE_JUMP).setCooldown(cooldownMin);
            PacketDistributor.sendToPlayer((ServerPlayer)player, new PlayerDoubleJumpSyncMessage(this.nbDoubleJump), new CustomPacketPayload[0]);
        }
        if(player.getData(PLAYER_DOUBLE_JUMP).getCooldown() > player.getData(PLAYER_DOUBLE_JUMP).getCooldownMin()){
            player.getData(PLAYER_DOUBLE_JUMP).setCooldown(player.getData(PLAYER_DOUBLE_JUMP).getCooldown() - 1);
        }
    }

    @Override
    public void updateDoubleJumpData(Player player) {
        //PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerDoubleJump());
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("nb_double_jump", this.nbDoubleJump);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.nbDoubleJump = nbt.getInt("nb_double_jump");
    }
}
