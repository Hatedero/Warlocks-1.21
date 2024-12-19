package net.hatedero.warlocksmod.block.custom;

import net.hatedero.warlocksmod.item.ModItems;
import net.hatedero.warlocksmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PositionColoredBlock extends Block {
    private int color = 0;
    public PositionColoredBlock(Properties properties) {
        super(properties);
    }

    public int getColor(){
        return this.color;
    }

    public void setColor(int n){
        this.color = n;
    }

    public PositionColoredBlock(Properties properties, int c) {
        super(properties);
        this.color = c;
    }
}