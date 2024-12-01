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

    public static final Holder<MobEffect> CONSUMED_EFFECT = MOB_EFFECTS.register("consumed",
            () -> new ConsumedEffect(MobEffectCategory.BENEFICIAL, 0x113048));

    public static final Holder<MobEffect> CLIMBING_EFFECT = MOB_EFFECTS.register("climbing",
            () -> new ClimbingEffect(MobEffectCategory.BENEFICIAL, 0xC1BAA1));

    public static final Holder<MobEffect> ABYSS_GRASP_EFFECT = MOB_EFFECTS.register("abyss_grasp",
            () -> new AbyssGraspEffect(MobEffectCategory.BENEFICIAL, 0x5b175d));

    public static final Holder<MobEffect> THUNDER_GRACE_EFFECT = MOB_EFFECTS.register("thunder_grace",
            () -> new ThunderGraceEffect(MobEffectCategory.BENEFICIAL, 0xDEFFFE));

    public static final Holder<MobEffect> INFINITY_AFFECTED_EFFECT = MOB_EFFECTS.register("infinity_affected",
            () -> new InfinityAffectedEffect(MobEffectCategory.BENEFICIAL, 0xFBFBFB));

    public static final Holder<MobEffect> PHOENIX_EFFECT = MOB_EFFECTS.register("phoenix",
            () -> new PhoenixEffect(MobEffectCategory.BENEFICIAL, 0xDAA520)
                    .addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "effect.health"), 20.0, AttributeModifier.Operation.ADD_VALUE));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}