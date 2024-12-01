package net.hatedero.warlocksmod.capability.abilities.doublejump;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IDoubleJump;
import net.hatedero.warlocksmod.network.message.PlayerDoubleJumpSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerThunderSnapSyncMessage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import static net.hatedero.warlocksmod.Config.maxDJC;
import static net.hatedero.warlocksmod.Config.maxDJN;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DOUBLE_JUMP;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DOUBLE_JUMP;

public class PlayerDoubleJump implements IDoubleJump, INBTSerializable<CompoundTag> {
    int nbDoubleJumpMax = 2;
    int nbDoubleJumpMin = 0;
    int nbDoubleJump = nbDoubleJumpMin;
    int cooldownMax = 10;
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
        }
        if(player.getData(PLAYER_DOUBLE_JUMP).getCooldown() > player.getData(PLAYER_DOUBLE_JUMP).getCooldownMin()){
            player.getData(PLAYER_DOUBLE_JUMP).setCooldown(player.getData(PLAYER_DOUBLE_JUMP).getCooldown() - 1);
        }
        updateDoubleJumpData(player);
    }

    @Override
    public void updateDoubleJumpData(Player player) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerDoubleJumpSyncMessage(this.cooldown, this.nbDoubleJump), new CustomPacketPayload[0]);
    }

    @Override
    public void resetData(Player player) {
        player.getData(PLAYER_DOUBLE_JUMP).setNbDoubleJump(0);
        player.getData(PLAYER_DOUBLE_JUMP).setNbDoubleJumpMax(maxDJN);
        player.getData(PLAYER_DOUBLE_JUMP).setNbDoubleJumpMin(0);
        player.getData(PLAYER_DOUBLE_JUMP).setCooldown(maxDJC);
        player.getData(PLAYER_DOUBLE_JUMP).setCooldownMax(maxDJC);
        player.getData(PLAYER_DOUBLE_JUMP).setCooldownMin(0);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("double_jump_nb_double_jump", this.nbDoubleJump);
        nbt.putInt("double_jump_cooldown", this.cooldown);
        nbt.putInt("double_jump_cooldown_max", this.cooldownMax);
        nbt.putInt("double_jump_cooldown_min", this.cooldownMin);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.nbDoubleJump = nbt.getInt("double_jump_nb_double_jump");
        this.cooldown = nbt.getInt("double_jump_cooldown");
        this.cooldownMax = nbt.getInt("double_jump_cooldown_max");
        this.cooldownMin = nbt.getInt("double_jump_cooldown_min");
    }
}
