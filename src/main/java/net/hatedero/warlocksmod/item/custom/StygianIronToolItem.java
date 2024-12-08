package net.hatedero.warlocksmod.item.custom;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

public class StygianIronToolItem extends DiggerItem {

    private int souls_taken = 0;

    public StygianIronToolItem(Tier tier, TagKey<Block> blocks, Properties properties) {
        super(tier, blocks, properties);
    }

    public int getSouls_taken(){
        return this.souls_taken;
    }

    public void setSouls_taken(int n){
        this.souls_taken = n;
    }


}
