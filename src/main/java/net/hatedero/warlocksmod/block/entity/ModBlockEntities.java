package net.hatedero.warlocksmod.block.entity;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.block.entity.custom.AbyssReactorBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.mojang.datafixers.types.Type;
import net.hatedero.warlocksmod.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;

import java.util.function.Supplier;

public class ModBlockEntities {
//    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES;
//    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbyssReactorBlockEntity>> ABYSS_REACTOR_BE;
//
//    public ModBlockEntities() {
//    }
//
//    public static void register(IEventBus eventBus) {
//        BLOCK_ENTITIES.register(eventBus);
//    }
//
//    static {
//        BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "warlocksmod");
//        ABYSS_REACTOR_BE = BLOCK_ENTITIES.register("abyss_reactor_be", () -> {
//            return Builder.of(AbyssReactorBlockEntity::new, new Block[]{(Block)ModBlocks.ABYSS_REACTOR_BLOCK.get()}).build((Type)null);
//        });
//    }

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, WarlocksMod.MOD_ID);

    public static final Supplier<BlockEntityType<AbyssReactorBlockEntity>> ABYSS_REACTOR_BLOCK_BE =
            BLOCK_ENTITIES.register("abyss_reactor_block_be", () -> BlockEntityType.Builder.of(
                    AbyssReactorBlockEntity::new, ModBlocks.ABYSS_REACTOR_BLOCK.get()).build(null));
//
//    public static final Supplier<BlockEntityType<CrystallizerBlockEntity>> CRYSTALLIZER_BE =
//            BLOCK_ENTITIES.register("crystallizer_be", () -> BlockEntityType.Builder.of(
//                    CrystallizerBlockEntity::new, ModBlocks.CRYSTALLIZER.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
