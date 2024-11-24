package net.hatedero.warlocksmod.dash;

import net.hatedero.warlocksmod.capability.IDash;
import net.hatedero.warlocksmod.network.message.PlayerDashSyncMessage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DASH;

public class PlayerDash implements IDash, INBTSerializable<CompoundTag> {
    int nbDash = 0;

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
    public void tick(Player player) {
        if(player.onGround()) {
            player.getData(PLAYER_DASH).setNbDash(1);
            PacketDistributor.sendToPlayer((ServerPlayer)player, new PlayerDashSyncMessage(this.nbDash), new CustomPacketPayload[0]);
            //player.sendSystemMessage(Component.literal(String.valueOf(player.getData(PLAYER_DASH).getNbDash() )));
        }
    }

    @Override
    public void updateDashData(Player player) {
        //PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerDash);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("nb_dash", this.nbDash);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.nbDash = nbt.getInt("nb_dash");
    }


//    private int nbDash;
//    private final int MIN_COOLDOWN = 0;
//    private final int MAX_COOLDOWN = 20;
//
//    public int getCooldown(){return nbDash;}
//
//    public void addCooldown(int add){Math.min(nbDash + add, MAX_COOLDOWN);}
//    public void subCooldown(int add){Math.max(nbDash - add, MIN_COOLDOWN);}
//
//    public void copyFrom(PlayerDash source){
//        this.nbDash = source.nbDash;
//    }
//
//    public void saveNBTData(CompoundTag nbt){
//        nbt.putInt("nbDash", nbDash);
//    }
//
//    public void loadNBTData(CompoundTag nbt){
//        nbDash = nbt.getInt("nbDash");
//    }
//
//    @Override
//    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
//        CompoundTag nbt = new CompoundTag();
//        createPlayerDash().saveNBTData(nbt);
//        return nbt;
//    }
//
//    @Override
//    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
//        createPlayerDash().loadNBTData(compoundTag);
//    }
}
