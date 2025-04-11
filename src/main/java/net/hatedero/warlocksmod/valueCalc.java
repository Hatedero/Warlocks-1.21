package net.hatedero.warlocksmod;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

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

    public static Vec3 DirectEntityTo(Entity main, Vec3 target){
        Vec3 r = new Vec3(target.x-main.getX(), target.y-main.getY(), target.z-main.getZ());
        return r;
    }

    public static double ziplineSpeed(double init){
        if (init < 0.1)
            return init + 0.02;
        else if (init < 0.3)
            return init + 0.03;
        else if (init < 0.8)
            return init + 0.04;
        else if (init < 1)
            return init;
        else
            return init - (init * 0.25);
    }
}
