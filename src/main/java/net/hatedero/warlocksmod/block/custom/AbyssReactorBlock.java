package net.hatedero.warlocksmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.hatedero.warlocksmod.block.entity.custom.AbyssReactorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.ConduitBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class AbyssReactorBlock extends BaseEntityBlock {

    public AbyssReactorBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    public static final MapCodec<AbyssReactorBlock> CODEC = simpleCodec(AbyssReactorBlock::new);


    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AbyssReactorBlockEntity(pPos, pState);
    }


}
