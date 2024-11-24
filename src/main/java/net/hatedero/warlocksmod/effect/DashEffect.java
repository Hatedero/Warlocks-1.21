package net.hatedero.warlocksmod.effect;


import net.hatedero.warlocksmod.item.ModItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class DashEffect extends MobEffect {

    public DashEffect(MobEffectCategory category, int color) {
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