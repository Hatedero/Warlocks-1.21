package net.hatedero.warlocksmod.capability.abilities.infinity;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IInfinity;
import net.hatedero.warlocksmod.network.message.PlayerInfinitySyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import java.util.List;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_INFINITY;

public class PlayerInfinity implements IInfinity, INBTSerializable<CompoundTag> {
    int Range = 10;
    boolean Active = false;
    List<Entity> Authorized;
    int cooldownMax = 20;
    int cooldownMin = 0;
    int cooldown = cooldownMax;
    int ActiveTimeMax = 200;
    int ActiveTimeMin = 0;
    int ActiveTime = ActiveTimeMax;

    public PlayerInfinity(){}

    @Override
    public int getRange() {
        return this.Range;
    }

    @Override
    public void setRange(int n) {
        this.Range = n;
    }

    @Override
    public boolean getActive() {
        return false;
    }

    @Override
    public void setActive(boolean n) {
        this.Active = n;
    }

    @Override
    public List<Entity> getAuthorized() {
        return this.Authorized;
    }

    @Override
    public void setAuthorized(List<Entity> n) {
        this.Authorized = n;
    }

    @Override
    public void addAuthorized(Entity n) {
        this.Authorized.add(n);
    }

    @Override
    public void addAuthorized(List<Entity> n) {
        for(Entity element : n)
            this.Authorized.add(element);
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
    public int getActiveTime() {
        return ActiveTime;
    }

    @Override
    public void setActiveTime(int n) {
        this.ActiveTime = n;
    }

    @Override
    public int getActiveTimeMax() {
        return ActiveTimeMax;
    }

    @Override
    public void setActiveTimeMax(int n) {
        this.ActiveTimeMax = n;
    }

    @Override
    public int getActiveTimeMin() {
        return ActiveTimeMin;
    }

    @Override
    public void setActiveTimeMin(int n) {
        this.ActiveTimeMin = n;
    }

    @Override
    public void tick(Player player) {
        if(player.getData(PLAYER_INFINITY).getActiveTime() > player.getData(PLAYER_INFINITY).getActiveTimeMin()){
            player.getData(PLAYER_INFINITY).setActiveTime(player.getData(PLAYER_INFINITY).getActiveTime() - 1);
        }
        updateInfinityData(player);
    }

    @Override
    public List<Entity> detectAllInRange(Player player, Level level) {
        AABB all = new AABB(player.getX()-this.Range, player.getY()-this.Range, player.getZ()-this.Range, player.getX()+this.Range, player.getY()+this.Range, player.getZ()+this.Range);
        List<Entity> entities = level.getEntities(player, all);
        return entities;
    }

    @Override
    public void updateInfinityData(Player player) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerInfinitySyncMessage(this.ActiveTime, this.Range), new CustomPacketPayload[0]);
    }

//    int Range = 10;
//    boolean Active = false;
//    List<Entity> Authorized;
//    int cooldownMax = 20;
//    int cooldownMin = 0;
//    int cooldown = cooldownMax;
//    int ActiveTimeMax = 200;
//    int ActiveTimeMin = 0;
//    int ActiveTime = ActiveTimeMax;
    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("infinity_range", this.Range);
        nbt.putInt("infinity_active", this.Active);
        nbt.putInt("infinity_authorized", this.Authorized);
        nbt.putInt("infinity_cooldown", this.ActiveTimeMin);
        nbt.putInt("infinity_cooldown_max", this.ActiveTimeMin);
        nbt.putInt("infinity_cooldown_min", this.ActiveTimeMin);
        nbt.putInt("infinity_active_time", this.ActiveTimeMin);
        nbt.putInt("infinity_active_time_max", this.ActiveTimeMin);
        nbt.putInt("infinity_active_time_min", this.ActiveTimeMin);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.Range = nbt.getInt("infinity_Range");
        this.ActiveTime = nbt.getInt("infinity_ActiveTime");
        this.ActiveTimeMax = nbt.getInt("infinity_ActiveTime_max");
        this.ActiveTimeMin = nbt.getInt("infinity_ActiveTime_min");
    }
}
