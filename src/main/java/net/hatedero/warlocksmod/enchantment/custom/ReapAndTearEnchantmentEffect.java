package net.hatedero.warlocksmod.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public class ReapAndTearEnchantmentEffect implements EnchantmentEntityEffect {

    public static final MapCodec<ReapAndTearEnchantmentEffect> CODEC = MapCodec.unit(ReapAndTearEnchantmentEffect::new);
    @Override
    public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (entity instanceof LivingEntity){

            //Double newHealth = ((LivingEntity) entity).getHealth() - (((LivingEntity) entity).getMaxHealth()*0,1F);

            ((LivingEntity) entity).setHealth(((LivingEntity) entity).getHealth() -   (((LivingEntity) entity).getMaxHealth() * (0.05F * i)));
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
