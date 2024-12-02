package net.hatedero.warlocksmod.capability.abilitiesinterfaces;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public interface IChargeable {
    int getPower();
    void setPower(int n);
    boolean getCharging();
    void setCharging(boolean n);
    void setCharging(int n);
    int getChargeTime();
    void setChargeTime(int n);
    int getChargeTimeMax();
    void setChargeTimeMax(int n);
    int getChargeTimeMin();
    void setChargeTimeMin(int n);
    int getCooldown();
    void setCooldown(int n);
    int getCooldownMax();
    void setCooldownMax(int n);
    int getCooldownMin();
    void setCooldownMin(int n);
    void tick(Player player);
    void updateChargeableData(Player player);
    void resetData(Player player);
}
