package net.hatedero.warlocksmod.enchantment;


import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.enchantment.custom.FrostStrikerEnchantmentEffect;
import net.hatedero.warlocksmod.enchantment.custom.LightningStrikerEnchantmentEffect;
import net.hatedero.warlocksmod.enchantment.custom.ReapAndTearEnchantmentEffect;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.effects.AllOf;
import net.minecraft.world.item.enchantment.effects.PlaySoundEffect;
import net.minecraft.world.item.enchantment.effects.SummonEntityEffect;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.*;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> LIGHTNING_STRIKER_BOW = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "lightning_striker_bow"));

    public static final ResourceKey<Enchantment> LIGHTNING_STRIKER_SWORD = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "lightning_striker_sword"));

    public static final ResourceKey<Enchantment> FROST_STRIKER = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "frost_striker"));

    public static final ResourceKey<Enchantment> REAP_AND_TEAR = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "reap_and_tear"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);
        HolderGetter<Item> holdergetter2 = context.lookup(Registries.ITEM);

        register(context, LIGHTNING_STRIKER_BOW, Enchantment.enchantment( Enchantment.definition( holdergetter2.getOrThrow(ItemTags.BOW_ENCHANTABLE), 2, 5, Enchantment.constantCost(20), Enchantment.constantCost(50), 4, EquipmentSlotGroup.MAINHAND ) ) .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new LightningStrikerEnchantmentEffect()) );

        register(context, LIGHTNING_STRIKER_SWORD, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        5,
                        3,
                        Enchantment.dynamicCost(5, 7),
                        Enchantment.dynamicCost(25, 7),
                        2,
                        EquipmentSlotGroup.MAINHAND))

                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new LightningStrikerEnchantmentEffect())
                );

        register(context, FROST_STRIKER, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        5,
                        3,
                        Enchantment.dynamicCost(5, 7),
                        Enchantment.dynamicCost(25, 7),
                        2,
                        EquipmentSlotGroup.MAINHAND))

                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new FrostStrikerEnchantmentEffect())
        );

        register(context, REAP_AND_TEAR, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        5,
                        3,
                        Enchantment.dynamicCost(5, 7),
                        Enchantment.dynamicCost(25, 7),
                        2,
                        EquipmentSlotGroup.MAINHAND))

                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new ReapAndTearEnchantmentEffect())
        );
    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key,
                                 Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}