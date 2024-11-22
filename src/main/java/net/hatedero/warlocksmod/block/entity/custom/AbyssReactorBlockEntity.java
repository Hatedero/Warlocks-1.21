package net.hatedero.warlocksmod.block.entity.custom;

import net.hatedero.warlocksmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class AbyssReactorBlockEntity extends BlockEntity {
    public AbyssReactorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public AbyssReactorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ABYSS_REACTOR_BLOCK_BE.get(), pPos, pBlockState);
    }
}
