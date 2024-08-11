package net.hatedero.warlocksmod.block.custom;

import net.hatedero.warlocksmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
//import net.neoforged.neoforge.common.ToolAction;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModFlammableRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

//    @Override
//    public getToolModifiedState(BlockState state, UseOnContext context, boolean simulate) {
//        if(context.getItemInHand().getItem() instanceof AxeItem){
//            if(state.is(ModBlocks.SOUL_TREE_LOG.get())) {
//                return ModBlocks.STRIPPED_SOUL_TREE_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
//            }
//        }
//        return getToolModifiedState(state, context, simulate);
//    }
//
//    @Override
//    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
//        return super.getToolModifiedState(state, context, itemAbility, simulate);
//    }
}