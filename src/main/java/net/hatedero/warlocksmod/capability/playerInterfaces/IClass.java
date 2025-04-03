package net.hatedero.warlocksmod.capability.playerInterfaces;

import net.hatedero.warlocksmod.capability.abilitiesInterfaces.IPlayer;

public interface IClass extends IPlayer {
    String getClassName();
    void setClassName(String n);
}
