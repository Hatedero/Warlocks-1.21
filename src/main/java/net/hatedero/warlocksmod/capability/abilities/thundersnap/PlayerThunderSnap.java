package net.hatedero.warlocksmod.capability.abilities.thundersnap;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IThunderSnap;
import net.hatedero.warlocksmod.network.message.PlayerThunderSnapSyncMessage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_THUNDER_SNAP;

public class PlayerThunderSnap implements IThunderSnap, INBTSerializable<CompoundTag> {
    int strength = 10;
    int cooldownMax = 40;
    int cooldownMin = 0;
    int cooldown = cooldownMax;

    public PlayerThunderSnap(){}

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public void setStrength(int n) {

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
        if(player.getData(PLAYER_THUNDER_SNAP).getCooldown() > player.getData(PLAYER_THUNDER_SNAP).getCooldownMin()){
            player.getData(PLAYER_THUNDER_SNAP).setCooldown(player.getData(PLAYER_THUNDER_SNAP).getCooldown() - 1);
        }
        updateThunderSnapData(player);
    }

    @Override
    public void updateThunderSnapData(Player player) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerThunderSnapSyncMessage(this.cooldown, this.strength), new CustomPacketPayload[0]);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("thunder_snap_strength", this.strength);
        nbt.putInt("thunder_snap_cooldown", this.cooldown);
        nbt.putInt("thunder_snap_cooldown_max", this.cooldownMax);
        nbt.putInt("thunder_snap_cooldown_min", this.cooldownMin);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.strength = nbt.getInt("thunder_snap_strength");
        this.cooldown = nbt.getInt("thunder_snap_cooldown");
        this.cooldownMax = nbt.getInt("thunder_snap_cooldown_max");
        this.cooldownMin = nbt.getInt("thunder_snap_cooldown_min");
    }
}
