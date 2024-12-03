package net.hatedero.warlocksmod.capability.abilitiesinterfaces;

import net.minecraft.world.entity.player.Player;

public interface INova extends ICooldown, ICharge, IPlayer{
    float getPower();
    void setPower(float n);
}
