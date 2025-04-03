package net.hatedero.warlocksmod.capability.abilitiesInterfaces;

import net.minecraft.world.entity.player.Player;

public interface IDash {
    int getNbDash();
    void setNbDash(int n);
    int getNbDashMax();
    void setNbDashMax(int n);

    int getNbDashMin();
    void setNbDashMin(int n);

    int getCooldown();
    void setCooldown(int n);

    int getCooldownMax();
    void setCooldownMax(int n);

    int getCooldownMin();
    void setCooldownMin(int n);
    void tick(Player player);
    void updateDashData(Player player);
    void resetData(Player player);
}
