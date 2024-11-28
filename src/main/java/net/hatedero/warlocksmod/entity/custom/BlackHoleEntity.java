package net.hatedero.warlocksmod.entity.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class BlackHoleEntity extends Projectile {

    private int Strength = 1;
    private int Life = 200;

    public BlackHoleEntity(EntityType<? extends BlackHoleEntity> entityType, Level level) {
        super(entityType, level);
        //this.Life = 200;
    }

    public BlackHoleEntity(EntityType<? extends BlackHoleEntity> entityType, Level level, int Life){
        super(entityType, level);
        this.Life = Life;
    }

    @Override
    public void tick() {
        super.tick();

        if(this.Life <= 0){
            //Minecraft.getInstance().player.sendSystemMessage(Component.literal(String.valueOf(this.Life)));
            this.kill();
        }
        else {
            this.Life=this.Life-1;
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(String.valueOf(this.Life)));
            //getEntitiesInRange(Minecraft.getInstance().player.getServer().overworld());
        }
    }

    public void getEntitiesInRange(ServerLevel level){
        AABB minMax = new AABB(this.getX()-5, this.getY()-5, this.getZ()-5, this.getX()+5, this.getY()+5, this.getZ()+5);
        List<Entity> ent = level.getEntities(this, minMax);
        for (Entity entko : ent) {
            entko.moveTo(this.getOnPos().getCenter());
            Minecraft.getInstance().player.sendSystemMessage(entko.getName());
        }
    }

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
            //DamageSource $$5 = this.damageSources().fireball(this, $$4);
            //entity1.hurt($$5, 6.0F);
            //EnchantmentHelper.doPostAttackEffects(serverlevel, entity1, $$5);
        //}

    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putByte("ExplosionPower", (byte)this.Strength);
        compound.putByte("Life", (byte)this.Life);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("ExplosionPower", 99)) {
            this.Strength = compound.getByte("ExplosionPower");
        }
        if (compound.contains("Life", 99)) {
            this.Life = compound.getByte("Life");
        }
    }
}
