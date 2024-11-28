package net.hatedero.warlocksmod.capability.abilities.blackhole;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IBlackHole;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IThunderSnap;
import net.hatedero.warlocksmod.network.message.PlayerBlackHoleSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerThunderSnapSyncMessage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_BLACK_HOLE;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_THUNDER_SNAP;

public class PlayerBlackHole implements IBlackHole, INBTSerializable<CompoundTag> {
    int strength = 10;
    int lifeMin = 0;
    int lifeMax = 200;
    int life = lifeMax;
    int cooldownMax = 10;
    int cooldownMin = 0;
    int cooldown = cooldownMax;

    public PlayerBlackHole(){}

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public void setStrength(int n) {
        this.strength = n;
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public void setLife(int n) {
        this.life = n;
    }

    @Override
    public void setLifeMin(int n) {
        this.lifeMin = n;
    }

    @Override
    public int getLifeMin() {
        return this.lifeMin;
    }

    @Override
    public void setLifeMax(int n) {
        this.lifeMax = n;
    }

    @Override
    public int getLifeMax() {
        return this.lifeMax;
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
        if(player.getData(PLAYER_BLACK_HOLE).getCooldown() > player.getData(PLAYER_BLACK_HOLE).getCooldownMin()){
            player.getData(PLAYER_BLACK_HOLE).setCooldown(player.getData(PLAYER_BLACK_HOLE).getCooldown() - 1);
        }
        updateBlackHoleData(player);
    }

    @Override
    public void updateBlackHoleData(Player player) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerBlackHoleSyncMessage(this.cooldown, this.strength, this.life), new CustomPacketPayload[0]);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("black_hole_strength", this.strength);
        nbt.putInt("black_hole_life", this.life);
        nbt.putInt("black_hole_life_max", this.lifeMax);
        nbt.putInt("black_hole_life_min", this.lifeMin);
        nbt.putInt("black_hole_cooldown", this.cooldown);
        nbt.putInt("black_hole_cooldown_max", this.cooldownMax);
        nbt.putInt("black_hole_cooldown_min", this.cooldownMin);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.strength = nbt.getInt("black_hole_strength");
        this.cooldown = nbt.getInt("black_hole_life");
        this.cooldownMax = nbt.getInt("black_hole_life_max");
        this.cooldownMin = nbt.getInt("black_hole_life_min");
        this.cooldown = nbt.getInt("black_hole_cooldown");
        this.cooldownMax = nbt.getInt("black_hole_cooldown_max");
        this.cooldownMin = nbt.getInt("black_hole_cooldown_min");
    }
}
