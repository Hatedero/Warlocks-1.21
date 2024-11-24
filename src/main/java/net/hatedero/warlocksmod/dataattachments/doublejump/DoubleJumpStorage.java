package net.hatedero.warlocksmod.dataattachments.doublejump;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public class DoubleJumpStorage {
    private static String PLAYER_DOUBLE_JUMP_COOLDOWN = "WARLOCKSMOD_PLAYER_DOUBLE_JUMP_COOLDOWN";

    public DoubleJumpStorage () {}

    public static Tag writeNBT(IDoubleJump instance){
        CompoundTag nbt = new CompoundTag();
        nbt.putInt(PLAYER_DOUBLE_JUMP_COOLDOWN, instance.getCooldown());

        return nbt;
    }

    public static Tag readNBT(IDoubleJump instance, Tag nbtb){
        CompoundTag nbt = (CompoundTag) nbtb;
        instance.setCooldown(nbt.getInt(PLAYER_DOUBLE_JUMP_COOLDOWN));

        return nbt;
    }
}
