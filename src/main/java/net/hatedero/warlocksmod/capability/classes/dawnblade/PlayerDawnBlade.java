package net.hatedero.warlocksmod.capability.classes.dawnblade;

import net.hatedero.warlocksmod.capability.classesinterfaces.IDawnBlade;
import net.hatedero.warlocksmod.network.message.PlayerDashSyncMessage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DASH;

public class PlayerDawnBlade implements IDawnBlade, INBTSerializable<CompoundTag> {
    String[] abilities = {"fire_snap", "dash", "phoenix_bond", "fragment_of_sun"};

    @Override
    public String[] getAbilities() {
        return abilities;
    }

    @Override
    public void setAbilities(String[] abilities) {

    }

    @Override
    public void tick(Player player) {
        if(player.onGround()) {
            player.getData(PLAYER_DASH).setNbDash(1);
            //PacketDistributor.sendToPlayer((ServerPlayer)player, new PlayerDashSyncMessage(this.nbDash), new CustomPacketPayload[0]);
            //player.sendSystemMessage(Component.literal(String.valueOf(player.getData(PLAYER_DASH).getNbDash() )));
        }
    }

    @Override
    public void updateDashData(Player player) {
        //PacketDistributor.sendToPlayer((ServerPlayer) player, new PlayerDash);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        return null;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {

    }
}
