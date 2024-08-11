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
        simpleItem(ModItems.SULFUR_POWDER);
        simpleItem(ModItems.SALT_POWDER);
        simpleItem(ModItems.VOLCANIC_DUST);
        simpleItem(ModItems.CURED_ROTTEN_FLESH);
        simpleItem(ModItems.COOKED_CURED_ROTTEN_FLESH);
        simpleItem(ModItems.J_FRUIT);
        simpleItem(ModItems.BEEF_SANDWICH);
        simpleItem(ModItems.CHICKEN_SANDWICH);
        simpleItem(ModItems.PORK_SANDWICH);
        simpleItem(ModItems.LAMB_SANDWICH);
        simpleItem(ModItems.AMERICAN_SANDWICH);
        simpleItem(ModItems.SOUL_BERRY);
        simpleItem(ModItems.SOULBERRY_SEEDS);
        simpleItem(ModItems.SOUL_TENDRIL);
        simpleItem(ModItems.ABYSS_SHARD);
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

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(WarlocksMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}