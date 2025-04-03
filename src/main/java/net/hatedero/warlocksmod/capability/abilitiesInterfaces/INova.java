package net.hatedero.warlocksmod.capability.abilitiesInterfaces;

public interface INova extends ICooldown, ICharge, IPlayer{
    float getPower();
    void setPower(float n);
}
