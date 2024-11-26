package net.hatedero.warlocksmod.capability.classesinterfaces;

import net.minecraft.world.entity.player.Player;

public interface IDawnBlade {
    String[] getAbilities();

    void setAbilities(String[] abilities);

    void tick(Player player);

    void updateDashData(Player player);
}
