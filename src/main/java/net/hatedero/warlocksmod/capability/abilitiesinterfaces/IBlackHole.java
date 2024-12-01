package net.hatedero.warlocksmod.capability.abilitiesinterfaces;

import net.minecraft.world.entity.player.Player;

public interface IBlackHole {
    int getStrength();
    void setStrength(int n);
    int getLife();
    void setLife(int n);
    void setLifeMin(int n);
    int getLifeMin();
    void setLifeMax(int n);
    int getLifeMax();
    int getCooldown();
    void setCooldown(int n);
    int getCooldownMax();
    void setCooldownMax(int n);
    int getCooldownMin();
    void setCooldownMin(int n);
    void tick(Player player);
    void updateBlackHoleData(Player player);
    void resetData(Player player);
}
