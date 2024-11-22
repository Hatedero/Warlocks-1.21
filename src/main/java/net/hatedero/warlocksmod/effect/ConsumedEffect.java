package net.hatedero.warlocksmod.effect;


import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

public class ConsumedEffect extends MobEffect {
    public ConsumedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if(livingEntity.horizontalCollision) {
//            Vec3 initialVec = livingEntity.getDeltaMovement();
//            Vec3 climbVec = new Vec3(initialVec.x, 0.2D, initialVec.z);
//            livingEntity.setDeltaMovement(climbVec.scale(0.96D));


            return true;
        }



        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}