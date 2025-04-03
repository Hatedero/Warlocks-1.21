package net.hatedero.warlocksmod.capability.abilitiesInterfaces;

import net.minecraft.world.entity.player.Player;

public interface IBlink {
    int getNbBlink();
    void setNbBlink(int n);
    int getNbBlinkMax();
    void setNbBlinkMax(int n);

    int getNbBlinkMin();
    void setNbBlinkMin(int n);

    int getCooldown();
    void setCooldown(int n);

    int getCooldownMax();
    void setCooldownMax(int n);

    int getCooldownMin();
    void setCooldownMin(int n);
    void tick(Player player);
    void updateBlinkData(Player player);
    void resetData(Player player);
}
