package net.hatedero.warlocksmod.enchantment.custom;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record LightningStrikerEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<LightningStrikerEnchantmentEffect> CODEC = MapCodec.unit(LightningStrikerEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if(enchantedItemInUse.owner() instanceof Player player) {

            Vec3 dist = player.position().subtract(entity.position());
            BlockPos trail = entity.getOnPos();

            Vec3 U = dist.normalize().scale(1);

            int rangeTrail = 10;
            int factor = 5;

            EntityType.LIGHTNING_BOLT.spawn(serverLevel, entity.getOnPos(), MobSpawnType.TRIGGERED).setDamage(5.0f + (2 *enchantmentLevel));
                for(int i = 0; i < rangeTrail; i++){
                    BlockPos newCo = BlockPos.containing(U.multiply(i*-factor,1,i*-factor).add(trail.getX(), trail.getY(), trail.getZ()));
                    EntityType.LIGHTNING_BOLT.spawn(serverLevel, newCo, MobSpawnType.TRIGGERED).setDamage(rangeTrail-i);
                }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
