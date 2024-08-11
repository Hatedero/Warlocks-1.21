package net.hatedero.warlocksmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties SOUL_BERRY = new FoodProperties.Builder().nutrition(1)
            .saturationModifier(1f).effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 100), 1f)
            .alwaysEdible()
            .fast()
            .build();

    public static final FoodProperties J_FRUIT = new FoodProperties.Builder().nutrition(1)
            .saturationModifier(1f).effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 600), 1f)
            .alwaysEdible()
            .fast()
            .build();

    public static final FoodProperties CURED_ROTTEN_FLESH = new FoodProperties.Builder().nutrition(3)
            .saturationModifier(1f).effect(() -> new MobEffectInstance(MobEffects.SATURATION, 60), 0.1f)
            .alwaysEdible()
            //.meat
            .build();

    public static final FoodProperties COOKED_CURED_ROTTEN_FLESH = new FoodProperties.Builder().nutrition(6)
            .saturationModifier(1f).effect(() -> new MobEffectInstance(MobEffects.SATURATION, 60), 0.3f)
            .alwaysEdible()
            //.meat
            .build();

    public static final FoodProperties SANDWICH = new FoodProperties.Builder().nutrition(10)
            .saturationModifier(1f)
            .alwaysEdible()
            //.meat()
            .build();

    public static final FoodProperties HEAVY_SANDWICH = new FoodProperties.Builder().nutrition(12)
            .saturationModifier(1f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60), 0.7f)
            .alwaysEdible()
            //.meat()
            .build();
}
