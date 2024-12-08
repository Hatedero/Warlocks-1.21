package net.hatedero.warlocksmod.item.custom;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class StygianIronWeaponItem extends SwordItem {

    private int souls_taken = 0;

    public StygianIronWeaponItem(Tier tier, Properties properties) {
        super(tier, properties);
    }


    public int getSouls_taken(){
        return this.souls_taken;
    }

    public void setSouls_taken(int n){
        this.souls_taken = n;
    }


}
