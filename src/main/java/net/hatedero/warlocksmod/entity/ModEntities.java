package net.hatedero.warlocksmod.entity;

import net.hatedero.warlocksmod.WarlocksMod;
//import net.hatedero.warlocksmod.entity.custom.BlueProjectileEntity;
import net.hatedero.warlocksmod.entity.custom.BlackHoleEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, WarlocksMod.MOD_ID);

//    public static final Supplier<EntityType<PenguinEntity>> PENGUIN =
//            ENTITY_TYPES.register("penguin", () -> EntityType.Builder.of(PenguinEntity::new, MobCategory.CREATURE)
//                    .sized(0.75f, 0.95f).build("penguin"));

//    public static final Supplier<EntityType<BlueProjectileEntity>> BLUE_PROJECTILE =
//            ENTITY_TYPES.register("blue_projectile", () -> EntityType.Builder.of(BlueProjectileEntity::new, MobCategory.CREATURE)
//                    .sized(0.75f, 0.95f).build("blue_projectile"));

    public static final Supplier<EntityType<BlackHoleEntity>> BLACK_HOLE =
            ENTITY_TYPES.register("black_hole", () -> EntityType.Builder.<BlackHoleEntity>of(BlackHoleEntity::new, MobCategory.AMBIENT)
                    .sized(0.5f, 0.5f).build("black_hole"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
