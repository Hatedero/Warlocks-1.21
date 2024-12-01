package net.hatedero.warlocksmod.capability.gamerules.gravity;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IDash;
import net.hatedero.warlocksmod.capability.gamerulesinterfaces.IGravity;
import net.hatedero.warlocksmod.network.message.PlayerDashSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerGravitySyncMessage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DASH;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_GRAVITY;
import static net.minecraft.world.entity.ai.attributes.Attributes.GRAVITY;

public class PlayerGravity implements IGravity, INBTSerializable<CompoundTag> {
    float Intensity = 0.08F;
    boolean Active = false;

    public PlayerGravity(){}

    @Override
    public float getIntensity() {
        return Intensity;
    }

    @Override
    public void setIntensity(float n) {
        this.Intensity = n;
    }

    @Override
    public boolean getActive() {
        return Active;
    }

    @Override
    public void setActive(boolean n) {
        this.Active = n;
    }

    @Override
    public void setActive(int n) {
        if(n == 1)
            this.Active = true;
        else
            this.Active = false;
    }

    @Override
    public void tick(Player player) {
        if(player.getData(PLAYER_GRAVITY).getActive()) {
            player.getAttribute(GRAVITY).setBaseValue(player.getData(PLAYER_GRAVITY).getIntensity());
            //player.getAttribute(GRAVITY).setBaseValue(0.08/10);
        }
        updateGravityData(player);
    }

    @Override
    public void updateGravityData(Player player) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerGravitySyncMessage(player.getData(PLAYER_GRAVITY).getIntensity(), player.getData(PLAYER_GRAVITY).getActive() ? 1 : 0), new CustomPacketPayload[0]);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("gravity_intensity", this.Intensity);
        nbt.putBoolean("gravity_active", this.Active);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.Intensity = nbt.getFloat("gravity_intensity");
        this.Active = nbt.getBoolean("gravity_active");
    }
}
