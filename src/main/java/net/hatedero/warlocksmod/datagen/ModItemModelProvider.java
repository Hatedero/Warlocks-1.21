package net.hatedero.warlocksmod.datagen;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.block.ModBlocks;
import net.hatedero.warlocksmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

//import net.minecraftforge.registries.RegistryObject;

import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
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
        basicItem(ModItems.SOUL_BERRY.get());
        basicItem(ModItems.SOULBERRY_SEEDS.get());
        basicItem(ModItems.SOUL_TENDRIL.get());
        basicItem(ModItems.ABYSS_SHARD.get());
//        simpleItem(ModItems.ABYSS_WALKER_HELMET);
//        simpleItem(ModItems.ABYSS_WALKER_CHESTPLATE);
//        simpleItem(ModItems.ABYSS_WALKER_LEGGINGS);
//        simpleItem(ModItems.ABYSS_WALKER_BOOTS);
//        simpleItem(ModItems.FARAAM_HELMET);
//        simpleItem(ModItems.FARAAM_CHESTPLATE);
//        simpleItem(ModItems.FARAAM_LEGGINGS);
//        simpleItem(ModItems.FARAAM_BOOTS);
//        simpleItem(ModItems.DESTINY_HELMET);
//        simpleItem(ModItems.DESTINY_CHESTPLATE);
//        simpleItem(ModItems.DESTINY_LEGGINGS);
//        simpleItem(ModItems.DESTINY_BOOTS);

//        withExistingParent(ModItems.RAT_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
//        withExistingParent(ModItems.LOSTSOUL_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

//        saplingItem(ModBlocks.SOUL_TREE_SAPLING);
//        saplingItem(ModBlocks.BLUE_PLUM_SAPLING);
    }

//    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
//        return withExistingParent(item.getId().getPath(),
//                new ResourceLocation("item/generated")).texture("layer0",
//                new ResourceLocation(WarlocksMod.MOD_ID,"block/" + item.getId().getPath()));
//    }
//
//    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
//        return withExistingParent(item.getId().getPath(),
//                new ResourceLocation("item/generated")).texture("layer0",
//                new ResourceLocation(WarlocksMod.MOD_ID, "item/" + item.getId().getPath()));
//    }


}