package net.hatedero.warlocksmod.datagen;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WarlocksMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.SULFUR_POWDER.get());
        basicItem(ModItems.SALT_POWDER.get());
        basicItem(ModItems.VOLCANIC_DUST.get());
        basicItem(ModItems.CURED_ROTTEN_FLESH.get());
        basicItem(ModItems.COOKED_CURED_ROTTEN_FLESH.get());
        basicItem(ModItems.J_FRUIT.get());
        basicItem(ModItems.BEEF_SANDWICH.get());
        basicItem(ModItems.CHICKEN_SANDWICH.get());
        basicItem(ModItems.PORK_SANDWICH.get());
        basicItem(ModItems.LAMB_SANDWICH.get());
        basicItem(ModItems.AMERICAN_SANDWICH.get());
        basicItem(ModItems.SOULBERRY.get());
        basicItem(ModItems.SOULBERRY_SEEDS.get());
        basicItem(ModItems.SOUL_TENDRIL.get());
        basicItem(ModItems.ABYSS_SHARD.get());
        basicItem(ModItems.DAWNBREAKER_HILT.get());
        basicItem(ModItems.DAWNBREAKER_BLADE.get());
        basicItem(ModItems.DAWNBREAKER_TIP.get());
        basicItem(ModItems.IMPERIAL_GOLD.get());
        basicItem(ModItems.STYGIAN_IRON.get());
        basicItem(ModItems.CELESTIAL_BRONZE.get());
        //basicItem(ModItems.ABYSSEAN_DUST.get());

        basicItem(ModItems.SUN_FRAGMENT.get());
        basicItem(ModItems.PIECE_OF_ABYSS.get());
        basicItem(ModItems.STILL_THUNDER.get());

        handheldItem(ModItems.ABYSS_SHARD_HAMMER);
        handheldItem(ModItems.STYGIAN_IRON_SWORD);
        //handheldItem(ModItems.DAWNBREAKER);
        //handheldItem(ModItems.CLEAVER);

        trimmedArmorItem(ModItems.ABYSS_SHARD_HELMET);
        trimmedArmorItem(ModItems.ABYSS_SHARD_CHESTPLATE);
        trimmedArmorItem(ModItems.ABYSS_SHARD_LEGGINGS);
        trimmedArmorItem(ModItems.ABYSS_SHARD_BOOTS);
        /*
        basicItem(ModItems.FARAAM_HELMET);
        basicItem(ModItems.FARAAM_CHESTPLATE);
        basicItem(ModItems.FARAAM_LEGGINGS);
        basicItem(ModItems.FARAAM_BOOTS);
        basicItem(ModItems.DESTINY_HELMET);
        basicItem(ModItems.DESTINY_CHESTPLATE);
        basicItem(ModItems.DESTINY_LEGGINGS);
        basicItem(ModItems.DESTINY_BOOTS);*/

//        withExistingParent(ModItems.RAT_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
//        withExistingParent(ModItems.LOSTSOUL_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        /*saplingItem(ModBlocks.SOUL_TREE_SAPLING);
        saplingItem(ModBlocks.BLUE_PLUM_SAPLING);*/
    }

    private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
        final String MOD_ID = WarlocksMod.MOD_ID; // Change this to your mod id

        if(itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}