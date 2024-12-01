package net.hatedero.warlocksmod.capability.abilities.dash;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IDash;
import net.hatedero.warlocksmod.network.message.PlayerDashSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerThunderSnapSyncMessage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import static net.hatedero.warlocksmod.Config.maxDC;
import static net.hatedero.warlocksmod.Config.maxDN;
import static net.hatedero.warlocksmod.capability.ModAttachment.*;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DASH;

public class PlayerDash implements IDash, INBTSerializable<CompoundTag> {
    int nbDashMax = 2;
    int nbDashMin = 0;
    int nbDash = nbDashMin;

    int cooldownMax = 10;
    int cooldownMin = 0;
    int cooldown = cooldownMax;

    public PlayerDash(){}

    @Override
    public int getNbDash() {
        return nbDash;
    }

    @Override
    public void setNbDash(int n) {
        this.nbDash = n;
    }
    @Override
    public int getNbDashMax() {
        return nbDashMax;
    }

    @Override
    public void setNbDashMax(int n) {
        this.nbDashMax = n;
    }

    @Override
    public int getNbDashMin() {
        return nbDashMin;
    }

    @Override
    public void setNbDashMin(int n) {
        this.nbDashMin = n;
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
            player.getData(PLAYER_DASH).setNbDash(player.getData(PLAYER_DASH).getNbDashMax());
        }
        if(player.getData(PLAYER_DASH).getCooldown() > player.getData(PLAYER_DASH).getCooldownMin()){
            player.getData(PLAYER_DASH).setCooldown(player.getData(PLAYER_DASH).getCooldown() - 1);
        }
        updateDashData(player);
    }

    @Override
    public void updateDashData(Player player) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerDashSyncMessage(this.cooldown, this.nbDash), new CustomPacketPayload[0]);
    }

    @Override
    public void resetData(Player player) {
        player.getData(PLAYER_DASH).setNbDash(0);
        player.getData(PLAYER_DASH).setNbDashMax(maxDN);
        player.getData(PLAYER_DASH).setNbDashMin(0);
        player.getData(PLAYER_DASH).setCooldown(maxDC);
        player.getData(PLAYER_DASH).setCooldownMax(maxDC);
        player.getData(PLAYER_DASH).setCooldownMin(0);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("dash_nb_dash", this.nbDash);
        nbt.putInt("dash_cooldown", this.cooldown);
        nbt.putInt("dash_cooldown_max", this.cooldownMax);
        nbt.putInt("dash_cooldown_min", this.cooldownMin);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.nbDash = nbt.getInt("dash_nb_dash");
        this.cooldown = nbt.getInt("dash_cooldown");
        this.cooldownMax = nbt.getInt("dash_cooldown_max");
        this.cooldownMin = nbt.getInt("dash_cooldown_min");
    }
}
