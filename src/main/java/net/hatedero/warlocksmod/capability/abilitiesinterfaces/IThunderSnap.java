package net.hatedero.warlocksmod.capability.abilitiesinterfaces;

import net.minecraft.world.entity.player.Player;

public interface IThunderSnap {
    int getStrength();
    void setStrength(int n);
    int getCooldown();
    void setCooldown(int n);
    int getCooldownMax();
    void setCooldownMax(int n);

    int getCooldownMin();
    void setCooldownMin(int n);
    void tick(Player player);
    void updateThunderSnapData(Player player);
    void resetData(Player player);
}
