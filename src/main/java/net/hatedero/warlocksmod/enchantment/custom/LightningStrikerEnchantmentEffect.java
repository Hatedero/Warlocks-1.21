package net.hatedero.warlocksmod.enchantment.custom;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
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
        BlockPos trail = entity.getOnPos();
        BlockPos origin = enchantedItemInUse.owner().getOnPos();
        int rangeTrail = 10;

        //Vec3 playerSightLigne = enchantedItemInUse.owner().getViewVector(1).normalize().multiply(rangeTrail,rangeTrail,rangeTrail);

        EntityType.LIGHTNING_BOLT.spawn(serverLevel, entity.getOnPos(), MobSpawnType.TRIGGERED).setDamage(5.0f + (2 *enchantmentLevel));


            for(int i = 1; i < rangeTrail; i++){
                float x = ((trail.getX() + origin.getX())/i)+origin.getX();
                float z = ((trail.getZ() + origin.getZ())/i)+origin.getZ();
                BlockPos newCo = new BlockPos((int) x, trail.getY(), (int) z);

                EntityType.LIGHTNING_BOLT.spawn(serverLevel, newCo, MobSpawnType.TRIGGERED).setDamage(i);
            }

    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
