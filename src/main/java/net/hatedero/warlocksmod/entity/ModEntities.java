package net.hatedero.warlocksmod.entity;

import net.hatedero.warlocksmod.WarlocksMod;
//import net.hatedero.warlocksmod.entity.custom.BlueProjectileEntity;
import net.hatedero.warlocksmod.entity.black_hole.BlackHole;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, WarlocksMod.MOD_ID);

//    public static final Supplier<EntityType<BlueProjectileEntity>> BLUE_PROJECTILE =
//            ENTITY_TYPES.register("blue_projectile", () -> EntityType.Builder.of(BlueProjectileEntity::new, MobCategory.CREATURE)
//                    .sized(0.75f, 0.95f).build("blue_projectile"));

//    public static final Supplier<EntityType<BlackHoleEntity>> BLACK_HOLE =
//            ENTITY_TYPES.register("black_hole", () -> EntityType.Builder.<BlackHoleEntity>of(BlackHoleEntity::new, MobCategory.AMBIENT)
//                    .sized(0.5f, 0.5f).build("black_hole"));

    public static final DeferredHolder<EntityType<?>, EntityType<BlackHole>> BLACK_HOLE =
            ENTITY_TYPES.register("black_hole", () -> EntityType.Builder.<BlackHole>of(BlackHole::new, MobCategory.MISC)
                    .sized(11, 11)
                    .clientTrackingRange(64)
                    .build("black_hole"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
