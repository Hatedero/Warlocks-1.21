package net.hatedero.warlocksmod.capability.abilitiesinterfaces;

import net.minecraft.world.entity.player.Player;

public interface ISinkedIn {
    boolean getActive();
    void setActive(boolean n);
    int getCooldown();
    void setCooldown(int n);
    int getCooldownMax();
    void setCooldownMax(int n);
    int getCooldownMin();
    void setCooldownMin(int n);
    int getActiveTime();
    void setActiveTime(int n);
    int getActiveTimeMax();
    void setActiveTimeMax(int n);
    int getActiveTimeMin();
    void setActiveTimeMin(int n);
    void tick(Player player);
    void updateSinkedInData(Player player);
    void resetData(Player player);
}
