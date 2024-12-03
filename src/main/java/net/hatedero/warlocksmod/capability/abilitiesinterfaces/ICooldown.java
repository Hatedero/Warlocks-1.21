package net.hatedero.warlocksmod.capability.abilitiesinterfaces;

public interface ICooldown {
    int getCooldown();
    void setCooldown(int n);

    int getCooldownMax();
    void setCooldownMax(int n);
}
