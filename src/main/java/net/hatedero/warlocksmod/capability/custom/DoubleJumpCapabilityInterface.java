package net.hatedero.warlocksmod.capability.custom;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public interface DoubleJumpCapabilityInterface extends INBTSerializable<CompoundTag> {
    int delay();

    void setDelay(int newDelay);
}
