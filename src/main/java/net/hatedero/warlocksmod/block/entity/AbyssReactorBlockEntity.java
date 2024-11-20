//package net.hatedero.warlocksmod.block.entity;
//
//import com.google.common.collect.Lists;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.state.BlockState;
//
//import javax.annotation.Nullable;
//import java.util.List;
//import java.util.UUID;
//
//public class AbyssReactorBlockEntity extends BlockEntity {
//
//    public int tickCount;
//    private float activeRotation;
//    private boolean isActive;
//    private boolean isHunting;
//    private final List<BlockPos> effectBlocks = Lists.newArrayList();
//    @Nullable
//    private LivingEntity destroyTarget;
//    @Nullable
//    private UUID destroyTargetUUID;
//    private long nextAmbientSoundActivation;
//
//    public AbyssReactorBlockEntity(BlockPos pPos, BlockState pBlockState) {
//        super((BlockEntityType)ModBlockEntities.ABYSS_REACTOR_BE.get(), pPos, pBlockState);
//    }
//
//    private static void updateHunting(AbyssReactorBlockEntity pBlockEntity, List<BlockPos> pPositions) {
//        pBlockEntity.setHunting(pPositions.size() >= 42);
//    }
//
//    public boolean isActive() {
//        return this.isActive;
//    }
//
//    public boolean isHunting() {
//        return this.isHunting;
//    }
//
//    private void setHunting(boolean pIsHunting) {
//        this.isHunting = pIsHunting;
//    }
//
//    public float getActiveRotation(float pPartialTick) {
//        return (this.activeRotation + pPartialTick) * -0.0375F;
//    }
//}
