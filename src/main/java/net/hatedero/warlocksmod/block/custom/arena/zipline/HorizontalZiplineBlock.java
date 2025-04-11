package net.hatedero.warlocksmod.block.custom.arena.zipline;

import com.mojang.serialization.MapCodec;
import net.hatedero.warlocksmod.valueCalc;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import static net.hatedero.warlocksmod.Config.maxDJN;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DOUBLE_JUMP;

public class HorizontalZiplineBlock extends HorizontalDirectionalBlock {
    public static VoxelShape SHAPE = Block.box(0, 6, 6, 16, 10, 10);
    public static final MapCodec<HorizontalZiplineBlock> CODEC = simpleCodec(HorizontalZiplineBlock::new);

    public HorizontalZiplineBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);

        if(entity.getDeltaMovement().horizontalDistance() >= 0.3)
        entity.playSound(SoundType.CHAIN.getStepSound());

        if(entity instanceof Player player)
            player.getData(PLAYER_DOUBLE_JUMP).setNbDoubleJump(maxDJN);

        entity.setNoGravity(true);

        entity.setPos(entity.getX(), pos.getY() - entity.getBbHeight() + 0.2, entity.getZ());

        double boost = valueCalc.ziplineSpeed(entity.getDeltaMovement().horizontalDistance());

        switch(state.getValue(FACING)){
            case WEST -> entity.setDeltaMovement(boost, 0.08, 0);

            case EAST -> entity.setDeltaMovement(-1 * boost, 0.08, 0);

            case SOUTH -> entity.setDeltaMovement(0, 0.08, boost);

            case NORTH -> entity.setDeltaMovement(0, 0.08, -1 * boost);
        }

        if(entity.minorHorizontalCollision)
            entity.addDeltaMovement(new Vec3(0, -0.15, 0));

        entity.setNoGravity(false);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch(state.getValue(FACING)){
            case WEST -> SHAPE = Block.box(0, 6, 6, 16, 10, 10);

            case EAST -> SHAPE = Block.box(0, 6, 6, 16, 10, 10);

            case SOUTH -> SHAPE = Block.box(6, 6, 0, 10, 10, 16);

            case NORTH -> SHAPE = Block.box(6, 6, 0, 10, 10, 16);
        }
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
