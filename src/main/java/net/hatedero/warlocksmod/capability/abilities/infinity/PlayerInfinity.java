package net.hatedero.warlocksmod.capability.abilities.infinity;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IInfinity;
import net.hatedero.warlocksmod.network.message.PlayerInfinitySyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
    int ActiveTimeMax = 2000;
    int ActiveTimeMin = 0;
    int ActiveTime = ActiveTimeMin;

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
        return this.Active;
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
//        if(!player.getData(PLAYER_INFINITY).getActive()){
//            player.sendSystemMessage(Component.literal("on"));
//        } else {
//            player.sendSystemMessage(Component.literal("off"));
//        }
        if(player.getData(PLAYER_INFINITY).getActive()){
//            player.sendSystemMessage(Component.literal("on"));

//        AABB all = new AABB(player.getX()-this.Range, player.getY()-this.Range, player.getZ()-this.Range, player.getX()+this.Range, player.getY()+this.Range, player.getZ()+this.Range);
//        Level level = Minecraft.getInstance().getSingleplayerServer().overworld();
//        Player Localplayer = Minecraft.getInstance().player;
//        List<Entity> entities = level.getEntities(Localplayer, all);
//        for(Entity e : entities){
//            if(e != Localplayer)
//            Localplayer.sendSystemMessage(e.getName());
//        }

//        Level level = player.level();
//        int range = 10;
//        AABB minMax = new AABB(player.getX()-this.Range, player.getY()-this.Range, player.getZ()-this.Range, player.getX()+this.Range, player.getY()+this.Range, player.getZ()+this.Range);
//        List<Entity> ent = level.getEntities(player, minMax);
//        for (Entity entko : ent) {
//            if(entko != player) {
//                //entko.moveTo(player.getOnPos().above(3).getCenter());
//                entko.setNoGravity(true);
//                entko.setDeltaMovement(0, 0, 0);
//                player.sendSystemMessage(entko.getName());
//            }
//        }

            List<Entity> all = detectAllInRange(player, player.level(), 3);
            for(Entity e : all){
                if(e != player){
                    //float newSpeed = 3 - e.distanceTo(player);
                    float newSpeed = 0;
                    e.setDeltaMovement(newSpeed, newSpeed, newSpeed);
                    e.setNoGravity(entityInRange(e, player, 3));
                    player.sendSystemMessage(e.getName());
                }
            }
//            if(player.getData(PLAYER_INFINITY).getActiveTime() >= player.getData(PLAYER_INFINITY).getActiveTimeMax()){
//                player.getData(PLAYER_INFINITY).setActive(false);
//            } else {
//                player.getData(PLAYER_INFINITY).setActiveTime(player.getData(PLAYER_INFINITY).getActiveTime() + 1);
//            }
        }
//        else{
            if(player.getData(PLAYER_INFINITY).getCooldown() > player.getData(PLAYER_INFINITY).getCooldownMin()){
                player.getData(PLAYER_INFINITY).setCooldown(player.getData(PLAYER_INFINITY).getCooldown() - 1);
            }
//            if(player.getData(PLAYER_INFINITY).getActiveTime() > player.getData(PLAYER_INFINITY).getActiveTimeMin()){
//                player.getData(PLAYER_INFINITY).setActiveTime(player.getData(PLAYER_INFINITY).getActiveTimeMin());
//            }
//        }
        updateInfinityData(player);
    }

    @Override
    public List<Entity> detectAllInRange(Player player, Level level, int range) {
        AABB all = new AABB(player.getX()-range, player.getY()-range , player.getZ()-range, player.getX()+range, player.getY()+range, player.getZ()+range);
        List<Entity> entities = level.getEntities(player, all);
        for(Entity e : entities)
            player.sendSystemMessage(e.getName());
        return entities;
    }

    @Override
    public boolean entityInRange(Entity entity, Player player, int range) {
        if(entity.distanceTo(player) <= range && player.getData(PLAYER_INFINITY).getActive())
            return true;
        return false;
    }

    @Override
    public void updateInfinityData(Player player) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerInfinitySyncMessage(this.cooldown, this.Range, this.ActiveTime, this.getActive() ? 1:0), new CustomPacketPayload[0]);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("infinity_range", this.Range);
        nbt.putBoolean("infinity_active", this.Active);
        //nbt.put("infinity_authorized", this.Authorized);
        nbt.putInt("infinity_cooldown", this.cooldown);
        nbt.putInt("infinity_cooldown_max", this.cooldownMax);
        nbt.putInt("infinity_cooldown_min", this.cooldownMin);
        nbt.putInt("infinity_active_time", this.ActiveTime);
        nbt.putInt("infinity_active_time_max", this.ActiveTimeMax);
        nbt.putInt("infinity_active_time_min", this.ActiveTimeMin);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.Range = nbt.getInt("infinity_Range");
        this.Active = nbt.getBoolean("infinity_active");
        this.cooldown = nbt.getInt("infinity_cooldown");
        this.cooldownMax = nbt.getInt("infinity_cooldown_max");
        this.cooldownMin = nbt.getInt("infinity_cooldown_min");
        this.ActiveTime = nbt.getInt("infinity_active_time");
        this.ActiveTimeMax = nbt.getInt("infinity_active_time_max");
        this.ActiveTimeMin = nbt.getInt("infinity_active_time_min");
        //this.ActiveTimeMin = nbt.getInt("infinity_authorized");
    }
}
