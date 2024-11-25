package net.hatedero.warlocksmod.capability.classesinterfaces;

import net.minecraft.world.entity.player.Player;

public interface IVoidWalker {
    int getNbDash();
    void setNbDash(int n);
    void tick(Player player);
    void updateDashData(Player player);
}
