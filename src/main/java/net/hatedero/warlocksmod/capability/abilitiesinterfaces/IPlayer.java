package net.hatedero.warlocksmod.capability.abilitiesinterfaces;

import net.minecraft.world.entity.player.Player;

public interface IPlayer {
    void tick(Player player);
    void updateData(Player player);
    void resetData(Player player);
}
