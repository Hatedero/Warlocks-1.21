package net.hatedero.warlocksmod.capability.abilities.infinity;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IInfinity;
import net.hatedero.warlocksmod.effect.ModEffects;
import net.hatedero.warlocksmod.network.message.PlayerInfinitySyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import java.util.List;

import static net.hatedero.warlocksmod.Config.*;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_INFINITY;
import static net.minecraft.core.particles.ParticleTypes.*;

public class PlayerInfinity implements IInfinity, INBTSerializable<CompoundTag> {
    int Range = 10;
    boolean Active = false;
    List<Entity> Selected;
    List<Entity> Authorized;
    int cooldownMax = 20;
    int cooldownMin = 0;
    int cooldown = cooldownMax;
    int ActiveTimeMax = 200;
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
    public List<Entity> getSelected() {
        return this.Selected;
    }

    @Override
    public void setSelected(List<Entity> n) {
        this.Selected = n;
    }

    @Override
    public void addSelected(Entity n) {
        this.Selected.add(n);
    }

    @Override
    public void addSelected(List<Entity> n) {
        for(Entity element : n)
            this.Selected.add(element);
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
        player.getData(PLAYER_INFINITY).setRange(3);
        //player.getData(PLAYER_INFINITY).setActiveTimeMax(200);
        if(player.getData(PLAYER_INFINITY).getActiveTime() >= player.getData(PLAYER_INFINITY).getActiveTimeMax()){
            //player.sendSystemMessage(Component.literal("should turn off"));
            player.getData(PLAYER_INFINITY).setActive(false);
            player.getData(PLAYER_INFINITY).setActiveTime(player.getData(PLAYER_INFINITY).getActiveTimeMin());
        }

        if(player.getData(PLAYER_INFINITY).getActive()){
            //player.level().addParticle(ASH, player.getX() , player.getY() + 5, player.getZ(), 0, 0, 0);
            //Minecraft.getInstance().particleEngine.add(new Particle(player.level(), player.getX() + 2, player.getY(), player.getZ(), 0, 0, 0));
            List<Entity> all = detectAllInRange(player, player.level(), player.getData(PLAYER_INFINITY).getRange());
            for(Entity e : all){
                if(e != player){
                    freezeEntity(e);
                    e.setNoGravity(entityInRange(e, player, player.getData(PLAYER_INFINITY).getRange()));
                }
            }
            player.getData(PLAYER_INFINITY).setActiveTime(player.getData(PLAYER_INFINITY).getActiveTime() + 1);
        }

        else{
            player.getData(PLAYER_INFINITY).setActiveTime(player.getData(PLAYER_INFINITY).getActiveTimeMin());
        }

        if(player.getData(PLAYER_INFINITY).getCooldown() > player.getData(PLAYER_INFINITY).getCooldownMin() && !player.getData(PLAYER_INFINITY).getActive()){
            player.getData(PLAYER_INFINITY).setCooldown(player.getData(PLAYER_INFINITY).getCooldown() - 1);
        }
        updateInfinityData(player);
//        player.sendSystemMessage(Component.literal("cooldown : active time"));
//        player.sendSystemMessage(Component.literal(String.valueOf(player.getData(PLAYER_INFINITY).getCooldown())));
//        player.sendSystemMessage(Component.literal(String.valueOf(player.getData(PLAYER_INFINITY).getActiveTime())));
    }

    @Override
    public List<Entity> detectAllInRange(Player player, Level level, int range) {
        AABB all = new AABB(player.getX()-range, player.getY()-range , player.getZ()-range, player.getX()+range, player.getY()+range, player.getZ()+range);
        List<Entity> entities = level.getEntities(player, all);
        return entities;
    }

    @Override
    public boolean entityInRange(Entity entity, Player player, int range) {
        if(entity.distanceTo(player) <= range && player.getData(PLAYER_INFINITY).getActive())
            return true;
        return false;
    }

    @Override
    public void freezeEntity(Entity e) {
        Vec3 newSpeed = new Vec3(0,0,0);
        if(e instanceof LivingEntity entity){
            entity.setNoGravity(true);
            entity.setOldPosAndRot();
            entity.setOnGroundWithMovement(entity.onGround(), newSpeed);
        }
        else {
            e.setNoGravity(true);
            e.setDeltaMovement(newSpeed);
        }
    }

    @Override
    public void updateInfinityData(Player player) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerInfinitySyncMessage(this.cooldown, this.Range, this.ActiveTime, this.getActive() ? 1:0), new CustomPacketPayload[0]);
    }

    @Override
    public void resetData(Player player) {
        player.getData(PLAYER_INFINITY).setRange(defIR);
        player.getData(PLAYER_INFINITY).setActiveTime(0);
        player.getData(PLAYER_INFINITY).setActiveTimeMax(maxIA);
        player.getData(PLAYER_INFINITY).setActiveTimeMin(0);
        player.getData(PLAYER_INFINITY).setActive(false);
        player.getData(PLAYER_INFINITY).setCooldown(maxIC);
        player.getData(PLAYER_INFINITY).setCooldownMax(maxIC);
        player.getData(PLAYER_INFINITY).setCooldownMin(0);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("infinity_range", this.Range);
        nbt.putBoolean("infinity_active", this.Active);
        nbt.putInt("infinity_cooldown", this.cooldown);
        nbt.putInt("infinity_cooldown_max", this.cooldownMax);
        nbt.putInt("infinity_cooldown_min", this.cooldownMin);
        nbt.putInt("infinity_active_time", this.ActiveTime);
        nbt.putInt("infinity_active_time_max", this.ActiveTimeMax);
        nbt.putInt("infinity_active_time_min", this.ActiveTimeMin);
//        nbt.putInt("infinity_nb_selected", this.Selected.size());
//        for(Entity e : this.Selected)
//            e.save(nbt);
//        for(Entity e : this.Authorized)
//            e.save(nbt);
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
//        for(int i = 0; i < nbt.getInt("infinity_nb_selected"); i++)
//            this.Selected.add();
    }
}
