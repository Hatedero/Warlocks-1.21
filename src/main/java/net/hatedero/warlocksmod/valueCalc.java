package net.hatedero.warlocksmod;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class valueCalc {

    public int getAbyssTakenBlockColorFromHeight(BlockPos pos){

        int hb = Minecraft.getInstance().level.getMaxBuildHeight();
        int lb = abs(Minecraft.getInstance().level.getMinBuildHeight());
        int range = hb+lb;

        float value = pos.getY() + lb;

        if (value < 0) value = 0;
        if (value > range) value = range;

        float perc = (value/range);

        int result = 0;
        if(perc >= 1-0.1)
            result = 1118481;
        else if (perc >= 1-0.7 && perc < 1-0.1)
            result = 2236962;
        else if (perc >= 1-0.95 && perc < 1-0.7)
            result = 11184810;
        else
            result = 16777215;

        return result;
    }
}
