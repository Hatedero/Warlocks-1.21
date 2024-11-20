//package net.hatedero.warlocksmod.block.custom;
//
//import com.mojang.serialization.MapCodec;
//import net.hatedero.warlocksmod.block.entity.AbyssReactorBlockEntity;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.level.block.BaseEntityBlock;
//import net.minecraft.world.level.block.ConduitBlock;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.phys.shapes.VoxelShape;
//import org.jetbrains.annotations.Nullable;
//
//public class AbyssReactorBlock extends BaseEntityBlock {
//
//    public AbyssReactorBlock(BlockBehaviour.Properties pProperties) {
//        super(pProperties);
//    }
//
//    public static final VoxelShape SHAPE = AbyssReactorBlock.box(5.0, 5.0, 5.0, 11.0, 11.0, 11.0);
//
//    @Override
//    protected MapCodec<? extends BaseEntityBlock> codec() {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
//        return new AbyssReactorBlockEntity(blockPos, blockState);
//    }
//}
