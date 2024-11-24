package net.hatedero.warlocksmod.enchantment;


import com.mojang.serialization.MapCodec;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.enchantment.custom.FrostStrikerEnchantmentEffect;
import net.hatedero.warlocksmod.enchantment.custom.LightningStrikerEnchantmentEffect;
import net.hatedero.warlocksmod.enchantment.custom.ReapAndTearEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, WarlocksMod.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> LIGHTNING_STRIKER =
            ENTITY_ENCHANTMENT_EFFECTS.register("lightning_striker", () -> LightningStrikerEnchantmentEffect.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> REAP_AND_TEAR =
            ENTITY_ENCHANTMENT_EFFECTS.register("reap_and_tear", () -> ReapAndTearEnchantmentEffect.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> FROST_STRIKER =
            ENTITY_ENCHANTMENT_EFFECTS.register("frost_striker", () -> FrostStrikerEnchantmentEffect.CODEC);

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}