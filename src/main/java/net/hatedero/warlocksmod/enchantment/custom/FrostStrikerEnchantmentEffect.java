package net.hatedero.warlocksmod.enchantment.custom;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public record FrostStrikerEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<FrostStrikerEnchantmentEffect> CODEC = MapCodec.unit(FrostStrikerEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        BlockPos temp;
        for(int i = 0; i < enchantmentLevel ; i++){
            entity.setTicksFrozen(20*enchantmentLevel);

                entity.setSharedFlagOnFire(false);
        }
//        for(int i = -1; i < 2; i++) {
//            for(int j = -1; j < 2; j++) {
//                temp = entity.getOnPos();
//                temp.offset(i,0,j);
//                float randomNum = (float) Math.random();
//                for(int i = 0; i > enchantmentLevel; i++) {
//                    if (randomNum <= (1 - (1 / (2 * enchantmentLevel))))
//                        EntityType.LIGHTNING_BOLT.spawn(serverLevel, entity.getOnPos(), MobSpawnType.TRIGGERED);
//                }
//            }
//        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
