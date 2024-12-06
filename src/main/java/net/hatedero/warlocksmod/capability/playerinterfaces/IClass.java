package net.hatedero.warlocksmod.capability.playerinterfaces;

import net.hatedero.warlocksmod.capability.abilitiesinterfaces.IPlayer;

public interface IClass extends IPlayer {
    String getClassName();
    void setClassName(String n);
}
