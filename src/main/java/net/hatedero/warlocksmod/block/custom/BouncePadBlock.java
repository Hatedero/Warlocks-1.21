package net.hatedero.warlocksmod.block.custom;

import net.hatedero.warlocksmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class BouncePadBlock extends Block {
    private int strength;
    public int getStrength(){return strength;}
    public void setStrength(int n){this.strength = n;}
    public BouncePadBlock(Properties properties, int str) {
        super(properties);
        setStrength(str);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        //if(entity instanceof Player player) {
            //entity.getDeltaMovement();
            entity.setDeltaMovement(entity.getDeltaMovement().add(0,this.strength,0));
        //}

        super.stepOn(level, pos, state, entity);
    }

//    @Override
//    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
//        if (entity.isSuppressingBounce()) {
//            super.fallOn(level, state, pos, entity, fallDistance);
//        } else {
//            entity.causeFallDamage(fallDistance, 0.0F, level.damageSources().fall());
//        }
//
//    }
//
//    @Override
//    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
//        if (entity.isSuppressingBounce()) {
//            super.updateEntityAfterFallOn(level, entity);
//        } else {
//            this.bounceUp(entity);
//        }
//
//    }
//
//    private void bounceUp(Entity entity) {
//        Vec3 vec3 = entity.getDeltaMovement();
//        if (vec3.y < 0.0) {
//            double d0 = entity instanceof LivingEntity ? 1.0 : 0.8;
//            entity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
//        }
//
//    }
//
//    @Override
//    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
//        double d0 = Math.abs(entity.getDeltaMovement().y);
//        if (d0 < 0.1 && !entity.isSteppingCarefully()) {
//            double d1 = 0.4 + d0 * 0.2;
//            entity.setDeltaMovement(entity.getDeltaMovement().multiply(d1, 1.0, d1));
//        }
//
//        super.stepOn(level, pos, state, entity);
//    }
}
