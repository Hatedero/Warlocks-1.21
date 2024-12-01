package net.hatedero.warlocksmod.capability.abilities.blink;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IBlink;
import net.hatedero.warlocksmod.network.message.PlayerBlinkSyncMessage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import static net.hatedero.warlocksmod.Config.maxBC;
import static net.hatedero.warlocksmod.Config.maxBN;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_BLINK;

public class PlayerBlink implements IBlink, INBTSerializable<CompoundTag> {
    int nbBlinkMax = 1;
    int nbBlinkMin = 0;
    int nbBlink = nbBlinkMin;

    int cooldownMax = 40;
    int cooldownMin = 0;
    int cooldown = cooldownMax;

    public PlayerBlink(){}

    @Override
    public int getNbBlink() {
        return nbBlink;
    }

    @Override
    public void setNbBlink(int n) {
        this.nbBlink = n;
    }
    @Override
    public int getNbBlinkMax() {
        return nbBlinkMax;
    }

    @Override
    public void setNbBlinkMax(int n) {
        this.nbBlinkMax = n;
    }

    @Override
    public int getNbBlinkMin() {
        return nbBlinkMin;
    }

    @Override
    public void setNbBlinkMin(int n) {
        this.nbBlinkMin = n;
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
        if(player.onGround() || player.getData(PLAYER_BLINK).getCooldown() >= player.getData(PLAYER_BLINK).getCooldownMin()) {
            player.getData(PLAYER_BLINK).setNbBlink(player.getData(PLAYER_BLINK).getNbBlinkMax());
        }
        if(player.getData(PLAYER_BLINK).getCooldown() > player.getData(PLAYER_BLINK).getCooldownMin()){
            player.getData(PLAYER_BLINK).setCooldown(player.getData(PLAYER_BLINK).getCooldown() - 1);
        }
        updateBlinkData(player);
    }

    @Override
    public void updateBlinkData(Player player) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerBlinkSyncMessage(this.cooldown, this.nbBlink), new CustomPacketPayload[0]);
    }

    @Override
    public void resetData(Player player) {
        player.getData(PLAYER_BLINK).setNbBlink(0);
        player.getData(PLAYER_BLINK).setNbBlinkMax(maxBN);
        player.getData(PLAYER_BLINK).setNbBlinkMin(0);
        player.getData(PLAYER_BLINK).setCooldown(maxBC);
        player.getData(PLAYER_BLINK).setCooldownMax(maxBC);
        player.getData(PLAYER_BLINK).setCooldownMin(0);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("blink_nb_blink", this.nbBlink);
        nbt.putInt("blink_cooldown", this.cooldown);
        nbt.putInt("blink_cooldown_max", this.cooldownMax);
        nbt.putInt("blink_cooldown_min", this.cooldownMin);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.nbBlink = nbt.getInt("blink_nb_blink");
        this.cooldown = nbt.getInt("blink_cooldown");
        this.cooldownMax = nbt.getInt("blink_cooldown_max");
        this.cooldownMin = nbt.getInt("blink_cooldown_min");
    }
}
