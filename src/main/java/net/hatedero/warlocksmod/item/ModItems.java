package net.hatedero.warlocksmod.item;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.block.ModBlocks;
import net.hatedero.warlocksmod.item.custom.*;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WarlocksMod.MOD_ID);

    public static final DeferredItem<HammerItem> ABYSS_SHARD_HAMMER = ITEMS.register("abyss_shard_hammer",
            () -> new HammerItem(ModToolTiers.ABYSS_SHARD, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.ABYSS_SHARD, 1F, -3.5f)), 2));


    public static final DeferredItem<Item> ABYSS_SHARD = ITEMS.register("abyss_shard",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ABYSSEAN_DUST = ITEMS.register("abyssean_dust",
            () -> new DustItem(new Item.Properties()));

    public static final DeferredItem<Item> SULFUR_POWDER = ITEMS.register("sulfur_powder",
            () -> new FuelItem(new Item.Properties(), 500));

    public static final DeferredItem<Item> SALT_POWDER = ITEMS.register("salt_powder",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> VOLCANIC_DUST = ITEMS.register("volcanic_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CURED_ROTTEN_FLESH = ITEMS.register("cured_rotten_flesh",
            () -> new Item(new Item.Properties().food(ModFoods.CURED_ROTTEN_FLESH)));

    public static final DeferredItem<Item> COOKED_CURED_ROTTEN_FLESH = ITEMS.register("cooked_cured_rotten_flesh",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_CURED_ROTTEN_FLESH)));

    public static final DeferredItem<Item> J_FRUIT = ITEMS.register("j_fruit",
            () -> new Item(new Item.Properties().food(ModFoods.J_FRUIT)));

    public static final DeferredItem<Item> SOULBERRY = ITEMS.register("soulberry",
            () -> new Item(new Item.Properties().food(ModFoods.SOUL_BERRY)));

    public static final DeferredItem<Item> BEEF_SANDWICH = ITEMS.register("beef_sandwich",
            () -> new Item(new Item.Properties().food(ModFoods.SANDWICH)));

    public static final DeferredItem<Item> CHICKEN_SANDWICH = ITEMS.register("chicken_sandwich",
            () -> new Item(new Item.Properties().food(ModFoods.SANDWICH)));

    public static final DeferredItem<Item> PORK_SANDWICH = ITEMS.register("pork_sandwich",
            () -> new Item(new Item.Properties().food(ModFoods.SANDWICH)));

    public static final DeferredItem<Item> LAMB_SANDWICH = ITEMS.register("lamb_sandwich",
            () -> new Item(new Item.Properties().food(ModFoods.SANDWICH)));

    public static final DeferredItem<Item> AMERICAN_SANDWICH = ITEMS.register("american_sandwich",
            () -> new Item(new Item.Properties().food(ModFoods.HEAVY_SANDWICH)));

    public static final DeferredItem<Item> SOUL_TENDRIL = ITEMS.register("soul_tendril",
            () -> new FuelItem(new Item.Properties(), 200));

    public static final DeferredItem<Item> COIN = ITEMS.register("coin",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<CleaverItem> CLEAVER = ITEMS.register("cleaver",
            () -> new CleaverItem(ModToolTiers.ABYSS_SHARD, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.ABYSS_SHARD, 17F, -3.2f)).fireResistant()));

    public static final DeferredItem<DawnbreakerItem> DAWNBREAKER = ITEMS.register("dawnbreaker",
            () -> new DawnbreakerItem(ModToolTiers.ABYSS_SHARD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.ABYSS_SHARD, 13F, 1f)).fireResistant()));

    public static final DeferredItem<Item> DAWNBREAKER_HILT = ITEMS.register("dawnbreaker_hilt",
            () -> new FuelItem(new Item.Properties(), 90000));

    public static final DeferredItem<Item> DAWNBREAKER_BLADE = ITEMS.register("dawnbreaker_blade",
            () -> new FuelItem(new Item.Properties(), 100000));

    public static final DeferredItem<Item> DAWNBREAKER_TIP = ITEMS.register("dawnbreaker_tip",
            () -> new FuelItem(new Item.Properties(), 90000));

    public static final DeferredItem<Item> IMPERIAL_GOLD = ITEMS.register("imperial_gold",
            () -> new Item(new Item.Properties().stacksTo(32).fireResistant()));

//    public static final DeferredItem<Item> SPEAR = ITEMS.register("spear",
//            () -> new TridentItem((new Item.Properties()).durability(250)));

//    public static final DeferredItem<Item> LOSTSOUL_SPAWN_EGG = ITEMS.register("lostsoul_spawn_egg",
//            () -> new ForgeSpawnEggItem(ModEntities.LOSTSOUL, 0x000000, 0xffffff,
//                    new Item.Properties()));
//
//    public static final DeferredItem<Item> RAT_SPAWN_EGG = ITEMS.register("rat_spawn_egg",
//            () -> new ForgeSpawnEggItem(ModEntities.RAT, 0xeeeeee, 0xffffff,
//                    new Item.Properties()));

    public static final DeferredItem<Item> SOULBERRY_SEEDS = ITEMS.register("soulberry_seeds",
            () -> new ItemNameBlockItem(ModBlocks.SOULBERRY_CROP.get(), new Item.Properties()));

    public static final DeferredItem<ArmorItem> ABYSS_SHARD_HELMET = ITEMS.register("abyss_shard_helmet",
            () -> new ModArmorItem(ModArmorMaterials.ABYSS_SHARD_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(40))));
    public static final DeferredItem<ArmorItem> ABYSS_SHARD_CHESTPLATE = ITEMS.register("abyss_shard_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ABYSS_SHARD_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(40))));
    public static final DeferredItem<ArmorItem> ABYSS_SHARD_LEGGINGS = ITEMS.register("abyss_shard_leggings",
            () -> new ArmorItem(ModArmorMaterials.ABYSS_SHARD_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(40))));
    public static final DeferredItem<ArmorItem> ABYSS_SHARD_BOOTS = ITEMS.register("abyss_shard_boots",
            () -> new ArmorItem(ModArmorMaterials.ABYSS_SHARD_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(40))));
//
//    public static final DeferredItem<Item> FARAAM_HELMET = ITEMS.register("faraam_helmet",
//            () -> new ArmorItem(ModArmorMaterials.FARAAM, ArmorItem.Type.HELMET, new Item.Properties()));
//    public static final DeferredItem<Item> FARAAM_CHESTPLATE = ITEMS.register("faraam_chestplate",
//            () -> new ArmorItem(ModArmorMaterials.FARAAM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//    public static final DeferredItem<Item> FARAAM_LEGGINGS = ITEMS.register("faraam_leggings",
//            () -> new ArmorItem(ModArmorMaterials.FARAAM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//    public static final DeferredItem<Item> FARAAM_BOOTS = ITEMS.register("faraam_boots",
//            () -> new ArmorItem(ModArmorMaterials.FARAAM, ArmorItem.Type.BOOTS, new Item.Properties()));
//
//    public static final DeferredItem<Item> DESTINY_HELMET = ITEMS.register("destiny_helmet",
//            () -> new ArmorItem(ModArmorMaterials.DESTINY, ArmorItem.Type.HELMET, new Item.Properties()));
//    public static final DeferredItem<Item> DESTINY_CHESTPLATE = ITEMS.register("destiny_chestplate",
//            () -> new ArmorItem(ModArmorMaterials.DESTINY, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//    public static final DeferredItem<Item> DESTINY_LEGGINGS = ITEMS.register("destiny_leggings",
//            () -> new ArmorItem(ModArmorMaterials.DESTINY, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//    public static final DeferredItem<Item> DESTINY_BOOTS = ITEMS.register("destiny_boots",
//            () -> new ArmorItem(ModArmorMaterials.DESTINY, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}