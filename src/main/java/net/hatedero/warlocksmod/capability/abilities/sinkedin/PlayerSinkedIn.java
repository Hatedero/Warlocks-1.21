package net.hatedero.warlocksmod.capability.abilities.sinkedin;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.ISinkedIn;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IThunderSnap;
import net.hatedero.warlocksmod.network.message.PlayerThunderSnapSyncMessage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_SINKED_IN;

public class PlayerSinkedIn implements ISinkedIn, INBTSerializable<CompoundTag> {
    boolean active = false;
    int cooldownMax = 20;
    int cooldownMin = 0;
    int cooldown = cooldownMax;
    int ActiveTimeMax = 2000;
    int ActiveTimeMin = 0;
    int ActiveTime = ActiveTimeMin;

    public PlayerSinkedIn(){}

    @Override
    public boolean getActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean n) {
        this.active = n;
    }

    @Override
    public int getCooldown() {
        return this.cooldown;
    }

    @Override
    public void setCooldown(int n) {
        this.cooldown = n;
    }

    @Override
    public int getCooldownMax() {
        return this.cooldownMax;
    }

    @Override
    public void setCooldownMax(int n) {
        this.cooldownMax = n;
    }

    @Override
    public int getCooldownMin() {
        return this.cooldownMin;
    }

    @Override
    public void setCooldownMin(int n) {
        this.cooldownMin = n;
    }

    @Override
    public int getActiveTime() {
        return this.ActiveTime;
    }

    @Override
    public void setActiveTime(int n) {
        this.ActiveTime = n;
    }

    @Override
    public int getActiveTimeMax() {
        return this.ActiveTimeMax;
    }

    @Override
    public void setActiveTimeMax(int n) {
        this.ActiveTimeMax = n;
    }

    @Override
    public int getActiveTimeMin() {
        return this.ActiveTimeMin;
    }

    @Override
    public void setActiveTimeMin(int n) {
        this.ActiveTimeMin = n;
    }

    @Override
    public void tick(Player player) {
        if(player.getData(PLAYER_SINKED_IN).getCooldown() > player.getData(PLAYER_SINKED_IN).getCooldownMin()){
            player.getData(PLAYER_SINKED_IN).setCooldown(player.getData(PLAYER_SINKED_IN).getCooldown() - 1);
        }
        updateSinkedInData(player);
    }

    @Override
    public void updateSinkedInData(Player player) {
        //PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerThunderSnapSyncMessage(this.cooldown, this.strength), new CustomPacketPayload[0]);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("sinked_in_active", this.active);
        nbt.putInt("sinked_in_cooldown", this.cooldown);
        nbt.putInt("sinked_in_cooldown_max", this.cooldownMax);
        nbt.putInt("sinked_in_cooldown_min", this.cooldownMin);
        nbt.putInt("sinked_in_active_time", this.ActiveTime);
        nbt.putInt("sinked_in_active_time_max", this.ActiveTimeMax);
        nbt.putInt("sinked_in_active_time_min", this.ActiveTimeMin);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.active = nbt.getBoolean("sinked_in_active");
        this.cooldown = nbt.getInt("sinked_in_cooldown");
        this.cooldownMax = nbt.getInt("sinked_in_cooldown_max");
        this.cooldownMin = nbt.getInt("sinked_in_cooldown_min");
        this.ActiveTime = nbt.getInt("sinked_in_active_time");
        this.ActiveTimeMax = nbt.getInt("sinked_in_active_time_max");
        this.ActiveTimeMin = nbt.getInt("sinked_in_active_time_min");
    }
}
