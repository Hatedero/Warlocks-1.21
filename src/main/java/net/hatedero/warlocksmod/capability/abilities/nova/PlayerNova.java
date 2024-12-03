package net.hatedero.warlocksmod.capability.abilities.nova;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.INova;
import net.hatedero.warlocksmod.network.message.PlayerNovaSyncMessage;
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

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_NOVA;

public class PlayerNova implements INova, INBTSerializable<CompoundTag> {
    int cooldownMax = 20;
    int cooldown = cooldownMax;
    int chargeMax = 20;
    int chargeMin = 0;
    int charge = chargeMin;
    int chargePrev = charge;
    float power = 1;

    @Override
    public int getCharge() {
        return this.charge;
    }

    @Override
    public void setCharge(int n) {
        this.charge = n;
    }

    @Override
    public int getChargePrev() {
        return this.chargePrev;
    }

    @Override
    public void setChargePrev(int n) {
        this.chargePrev = n;
    }

    @Override
    public int getChargeMax() {
        return this.chargeMax;
    }

    @Override
    public void setChargeMax(int n) {
        this.chargeMax = n;
    }

    @Override
    public int getChargeMin() {
        return this.chargeMin;
    }

    @Override
    public void setChargeMin(int n) {
        this.chargeMin = n;
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
    public float getPower() {
        return this.power;
    }

    @Override
    public void setPower(float n) {
        this.power = n;
    }

    @Override
    public void tick(Player player) {
        if(player.getData(PLAYER_NOVA).getCooldown() > 0){
            player.getData(PLAYER_NOVA).setCooldown(player.getData(PLAYER_NOVA).getCooldown() - 1);
        }
        //player.sendSystemMessage(Component.literal(String.valueOf(player.getData(PLAYER_NOVA).getCharge())));
        updateData(player);
    }

    @Override
    public void updateData(Player player) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerNovaSyncMessage(this.charge, this.chargeMax, this.chargeMin, this.cooldown, this.cooldownMax, this.power), new CustomPacketPayload[0]);
    }

    @Override
    public void resetData(Player player) {
        player.getData(PLAYER_NOVA).setChargeMax(20);
        player.getData(PLAYER_NOVA).setChargeMin(0);
        player.getData(PLAYER_NOVA).setCharge(player.getData(PLAYER_NOVA).getChargeMax());
        player.getData(PLAYER_NOVA).setChargePrev(player.getData(PLAYER_NOVA).getCharge()-1);
        player.getData(PLAYER_NOVA).setCooldownMax(20);
        player.getData(PLAYER_NOVA).setCooldown(player.getData(PLAYER_NOVA).getCooldownMax());
        player.getData(PLAYER_NOVA).setPower(1);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("nova_charge", this.charge);
        nbt.putInt("nova_charge_max", this.chargeMax);
        nbt.putInt("nova_charge_min", this.chargeMin);
        nbt.putInt("nova_cooldown", this.cooldown);
        nbt.putInt("nova_cooldown_max", this.cooldownMax);
        nbt.putFloat("nova_power", this.power);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.charge = nbt.getInt("nova_charge");
        this.chargeMax = nbt.getInt("nova_charge_max");
        this.chargeMin = nbt.getInt("nova_charge_min");
        this.cooldown = nbt.getInt("nova_cooldown");
        this.cooldownMax = nbt.getInt("nova_cooldown_max");
        this.power = nbt.getFloat("nova_power");
    }
}
