package net.hatedero.warlocksmod.datagen;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.item.ModItems;
import net.minecraft.data.PackOutput;
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
        /*basicItem(ModItems.ABYSS_WALKER_HELMET);
        basicItem(ModItems.ABYSS_WALKER_CHESTPLATE);
        basicItem(ModItems.ABYSS_WALKER_LEGGINGS);
        basicItem(ModItems.ABYSS_WALKER_BOOTS);
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
}