package net.hatedero.warlocksmod.capability.abilitiesinterfaces;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public interface IInfinity {
    int getRange();
    void setRange(int n);
    boolean getActive();
    void setActive(boolean n);
    List<Entity> getSelected();
    void setSelected(List<Entity> n);
    void addSelected(Entity n);
    void addSelected(List<Entity> n);
    List<Entity> getAuthorized();
    void setAuthorized(List<Entity> n);
    void addAuthorized(Entity n);
    void addAuthorized(List<Entity> n);
    int getCooldown();
    void setCooldown(int n);
    int getCooldownMax();
    void setCooldownMax(int n);
    int getCooldownMin();
    void setCooldownMin(int n);
    int getActiveTime();
    void setActiveTime(int n);
    int getActiveTimeMax();
    void setActiveTimeMax(int n);
    int getActiveTimeMin();
    void setActiveTimeMin(int n);
    void tick(Player player);
    List<Entity> detectAllInRange(Player player, Level level, int range);
    boolean entityInRange(Entity entity, Player player, int range);
    void freezeEntity(Entity entity);
    void updateInfinityData(Player player);
}
