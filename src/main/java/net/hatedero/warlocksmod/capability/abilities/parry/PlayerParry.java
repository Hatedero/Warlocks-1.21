package net.hatedero.warlocksmod.capability.abilities.parry;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IDoubleJump;
import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IParry;
import net.hatedero.warlocksmod.network.message.PlayerDoubleJumpSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerParrySyncMessage;
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
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DOUBLE_JUMP;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_PARRY;

public class PlayerParry implements IParry, INBTSerializable<CompoundTag> {

    int cooldownMax = 20;
    int cooldown = cooldownMax;
    int activeMax = 20;
    int active=0;


    public PlayerParry(){}
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
    public int getActive() {
        return this.active;
    }

    @Override
    public void setActive(int n) {
        this.active = n;
    }

    @Override
    public int getActiveMax() {
        return this.activeMax;
    }

    @Override
    public void setActiveMax(int n) {
        this.activeMax = n;
    }

    @Override
    public void tick(Player player) {
        if(player.getData(PLAYER_PARRY).getActive() == 0 && player.getData(PLAYER_PARRY).getCooldown() > 0){
            player.getData(PLAYER_PARRY).setCooldown(player.getData(PLAYER_PARRY).getCooldown() - 1);
            //player.sendSystemMessage(Component.literal("Cooldown -"));
        }
        if(player.getData(PLAYER_PARRY).getActive() > 0){
            player.getData(PLAYER_PARRY).setActive(player.getData(PLAYER_PARRY).getActive() + 1);
            //player.sendSystemMessage(Component.literal("Active +"));
        }
        if(player.getData(PLAYER_PARRY).getActive() >= player.getData(PLAYER_PARRY).getActiveMax()) {
            player.getData(PLAYER_PARRY).setActive(0);
            //player.sendSystemMessage(Component.literal("Active -"));
        }
        updateData(player);
    }

    @Override
    public void updateData(Player player) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerParrySyncMessage(this.active, this.activeMax, this.cooldown, this.cooldownMax), new CustomPacketPayload[0]);
    }

    @Override
    public void resetData(Player player) {
        player.getData(PLAYER_PARRY).setActive(0);
        player.getData(PLAYER_PARRY).setActiveMax(20);
        player.getData(PLAYER_PARRY).setCooldown(20);
        player.getData(PLAYER_PARRY).setCooldownMax(20);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("parry_active", this.active);
        nbt.putInt("parry_active_max", this.activeMax);
        nbt.putInt("parry_cooldown", this.cooldown);
        nbt.putInt("parry_cooldown_max", this.cooldownMax);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.active = nbt.getInt("parry_active");
        this.activeMax = nbt.getInt("parry_active_max");
        this.cooldown = nbt.getInt("parry_cooldown");
        this.cooldownMax = nbt.getInt("parry_cooldown_max");
    }

}
