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
        BlockPos zone = entity.getOnPos();
        BlockPos trail = entity.getOnPos();
        int rangeTrail = 10;

        Vec3 playerSightLigne = enchantedItemInUse.owner().getViewVector(1).normalize().multiply(rangeTrail,rangeTrail,rangeTrail);

//        for(int i = -1; i < 2; i++) {
//            for(int j = -1; j < 2; j++) {
                //temp = entity.getOnPos();
//                zone.offset(i*3,0,j*3);
//                float randomNum = (float) Math.random();
//                for(int i = 0; i > enchantmentLevel; i++) {
//                    if (randomNum <= (1 - (1 / (2 * enchantmentLevel))))
                        EntityType.LIGHTNING_BOLT.spawn(serverLevel, zone, MobSpawnType.TRIGGERED).setDamage(5.0f + (2 *enchantmentLevel));
//                }
//            }
//        }

        //if(enchantmentLevel > 2){
            for(int i = 0; i < rangeTrail; i++){

                EntityType.LIGHTNING_BOLT.spawn(serverLevel, trail.offset(0, 0, i*3), MobSpawnType.TRIGGERED).setDamage(i);
            }
        //}
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
