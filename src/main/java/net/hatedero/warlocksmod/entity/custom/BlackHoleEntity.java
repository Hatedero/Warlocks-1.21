package net.hatedero.warlocksmod.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

import static net.hatedero.warlocksmod.entity.ModEntities.ENTITY_TYPES;

public class BlackHoleEntity extends AbstractHurtingProjectile {
//    protected BlackHoleEntity(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
//        super(entityType, level);
//    }

    private int explosionPower = 1;

    public BlackHoleEntity(EntityType<? extends BlackHoleEntity> entityType, Level level) {
        super(entityType, level);
    }

//    public BlackHoleEntity(Level level, LivingEntity owner, Vec3 movement, int explosionPower) {
//        super(ENTITY_TYPES.BLACK_HOLE, owner, movement, level);
//        //this.explosionPower = explosionPower;
//    }

    protected void onHit(HitResult result) {
        super.onHit(result);
//        if (!this.level().isClientSide) {
//            boolean flag = EventHooks.canEntityGrief(this.level(), this.getOwner());
//            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower, flag, Level.ExplosionInteraction.MOB);
//            this.discard();
//        }

    }

    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
//        Level var3 = this.level();
//        if (var3 instanceof ServerLevel serverlevel) {
//            Entity entity1 = result.getEntity();
//            Entity $$4 = this.getOwner();
//            DamageSource $$5 = this.damageSources().fireball(this, $$4);
//            entity1.hurt($$5, 6.0F);
//            EnchantmentHelper.doPostAttackEffects(serverlevel, entity1, $$5);
//        }

    }

    public void addAdditionalSaveData(CompoundTag compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putByte("ExplosionPower", (byte)this.explosionPower);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
//        if (compound.contains("ExplosionPower", 99)) {
//            this.explosionPower = compound.getByte("ExplosionPower");
//        }

    }
}
