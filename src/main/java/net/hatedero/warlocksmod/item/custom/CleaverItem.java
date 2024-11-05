package net.hatedero.warlocksmod.item.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public class CleaverItem extends AxeItem {

    public CleaverItem(Tier tier, Properties properties, int R) {
        super(tier, properties);
        Range = R;
    }

    private static int Range = 0;

    public static int getRange(){
        return Range;
    }
}