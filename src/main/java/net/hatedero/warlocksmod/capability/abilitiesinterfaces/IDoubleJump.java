package net.hatedero.warlocksmod.capability.abilitiesinterfaces;

import net.minecraft.world.entity.player.Player;

public interface IDoubleJump {
    int getNbDoubleJump();
    void setNbDoubleJump(int n);

    int getNbDoubleJumpMax();
    void setNbDoubleJumpMax(int n);

    int getNbDoubleJumpMin();
    void setNbDoubleJumpMin(int n);

    int getCooldown();
    void setCooldown(int n);

    int getCooldownMax();
    void setCooldownMax(int n);

    int getCooldownMin();
    void setCooldownMin(int n);

    void tick(Player player);
    void updateDoubleJumpData(Player player);
    void resetData(Player player);
}
