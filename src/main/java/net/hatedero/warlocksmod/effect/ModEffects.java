package net.hatedero.warlocksmod.effect;


import net.hatedero.warlocksmod.WarlocksMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, WarlocksMod.MOD_ID);

    public static final Holder<MobEffect> FREE_FLIGHT_EFFECT = MOB_EFFECTS.register("free_flight",
            () -> new FreeFlightEffect(MobEffectCategory.BENEFICIAL, 0x36ebab));

    public static final Holder<MobEffect> DASH_EFFECT = MOB_EFFECTS.register("dash",
            () -> new DashEffect(MobEffectCategory.BENEFICIAL, 0x36ebab));

    public static final Holder<MobEffect> DOUBLE_JUMP_EFFECT = MOB_EFFECTS.register("doublejump",
            () -> new DoubleJumpEffect(MobEffectCategory.BENEFICIAL, 0x36ebab));

    public static final Holder<MobEffect> CONSUMED_EFFECT = MOB_EFFECTS.register("consumed",
            () -> new ConsumedEffect(MobEffectCategory.BENEFICIAL, 0x113048));

    public static final Holder<MobEffect> PHOENIX_EFFECT = MOB_EFFECTS.register("phoenix",
            () -> new PhoenixEffect(MobEffectCategory.BENEFICIAL, 0xDAA520)
                    .addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "effect.health"), 20.0, AttributeModifier.Operation.ADD_VALUE));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}