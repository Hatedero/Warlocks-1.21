package net.hatedero.warlocksmod.capability.abilitiesinterfaces;

import net.minecraft.world.entity.player.Player;

public interface IDash {
    int getNbDash();
    void setNbDash(int n);
    void tick(Player player);
    void updateDashData(Player player);
}
