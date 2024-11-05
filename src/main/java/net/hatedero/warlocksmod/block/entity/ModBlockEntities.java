package net.hatedero.warlocksmod.block.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.mojang.datafixers.types.Type;
import net.hatedero.warlocksmod.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES;
    //public static final DeferredRegister<BlockEntityType<AbyssReactorBlockEntity>> ABYSS_REACTOR_BE;

    public ModBlockEntities() {
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

    static {
        BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "warlocksmod");
//        ABYSS_REACTOR_BE = BLOCK_ENTITIES.register("abyss_reactor_be", () -> {
//            return AbyssReactorBlockEntity
            //return Builder.m_155273_(AbyssReactorBlockEntity::new, new Block[]{(Block)ModBlocks.ABYSS_REACTOR_BLOCK.get()}).m_58966_((Type)null);
//        });
    }
}
