package net.hatedero.warlocksmod.effect;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class DoubleJumpEffect extends MobEffect {

    public DoubleJumpEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
//        if(livingEntity.getMainHandItem() == ModItems.DAWNBREAKER.toStack() && livingEntity instanceof Player player) {
//            Vec3 playerSightLigne = player.getViewVector(1).normalize().multiply(1.5,1.5,1.5);
//            player.setDeltaMovement(playerSightLigne);
//            player.removeEffect(ModEffects.DASH_EFFECT);
//
//            return true;
//        }



        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}