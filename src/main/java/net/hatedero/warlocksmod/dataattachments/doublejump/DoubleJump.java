package net.hatedero.warlocksmod.dataattachments.doublejump;

public class DoubleJump implements IDoubleJump{
    int cooldown = 20;
    @Override
    public int getCooldown() {
        return cooldown;
    }

    @Override
    public void setCooldown(int newCooldown) {
        cooldown = newCooldown;
    }
}
