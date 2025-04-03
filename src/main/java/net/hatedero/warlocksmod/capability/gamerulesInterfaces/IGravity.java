package net.hatedero.warlocksmod.capability.gamerulesInterfaces;

import net.minecraft.world.entity.player.Player;

public interface IGravity {
    float getIntensity();
    void setIntensity(float n);
    boolean getActive();
    void setActive(boolean n);
    void setActive(int n);
    void tick(Player player);
    void updateGravityData(Player player);
}
