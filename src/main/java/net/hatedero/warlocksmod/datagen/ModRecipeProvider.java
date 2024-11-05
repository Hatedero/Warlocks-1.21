package net.hatedero.warlocksmod.datagen;


import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.block.ModBlocks;
import net.hatedero.warlocksmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> SULFUR_SMELTABLE = List.of(
                ModBlocks.SULFUR_ORE.get());

        List<ItemLike> CURED_ROTTEN_FLESH_COOKABLE = List.of(
                ModItems.CURED_ROTTEN_FLESH.get());

        List<ItemLike> SALT_COOKABLE = List.of(
                Items.WATER_BUCKET);

        itemSmelting(recipeOutput, SALT_COOKABLE, RecipeCategory.MISC, ModItems.SALT_POWDER.get(), 0.25f, 200, "salt");

        foodGeneral(recipeOutput, CURED_ROTTEN_FLESH_COOKABLE, RecipeCategory.MISC, ModItems.COOKED_CURED_ROTTEN_FLESH.get(), 0.25f, 100, "rotten_flesh");

        oreGeneral(recipeOutput, SULFUR_SMELTABLE, RecipeCategory.MISC, ModItems.SULFUR_POWDER.get(), 0.25f, 100, "sulfur");

        fullGeneral(ModBlocks.SALT_BLOCK.get(), ModItems.SALT_POWDER.get(), "salt", recipeOutput);

        fullGeneral(ModBlocks.SULFUR_BLOCK.get(), ModItems.SULFUR_POWDER.get(), "sulfur", recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CURED_ROTTEN_FLESH.get(), 1)
                .requires(ModItems.SALT_POWDER)
                .requires(Items.ROTTEN_FLESH)
                .unlockedBy("has_salt", has(ModItems.SALT_POWDER)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.ABYSS_GLASS.get(), 1)
                .requires(ModItems.ABYSS_SHARD)
                .requires(Blocks.GLASS)
                .unlockedBy("has_abyss_shard", has(ModItems.ABYSS_SHARD)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ABYSS_CORE.get())
                .pattern("GPG")
                .pattern("PSP")
                .pattern("GPG")
                .define('S', Blocks.SCULK_SHRIEKER)
                .define('G', ModBlocks.ABYSS_GLASS)
                .define('P', Blocks.PISTON)
                .unlockedBy("has_abyss_shard", has(ModItems.ABYSS_SHARD)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ABYSS_SHARD_HAMMER.get())
                .pattern("SSS")
                .pattern("STS")
                .pattern(" T ")
                .define('S', ModItems.ABYSS_SHARD)
                .define('T', Items.STICK)
                .unlockedBy("has_abyss_shard", has(ModItems.ABYSS_SHARD)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CLEAVER.get())
                .pattern(" SS")
                .pattern(" SS")
                .pattern("TS ")
                .define('S', ModItems.ABYSS_SHARD)
                .define('T', Items.STICK)
                .unlockedBy("has_abyss_shard", has(ModItems.ABYSS_SHARD)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ABYSS_SHARD.get(), 1)
                .requires(Items.SCULK)
                .requires(Items.SOUL_SAND)
                .unlockedBy("has_soul_sand", has(Blocks.SOUL_SAND)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.ABYSS_CHAIN.get(), 1)
                .requires(Items.CHAIN)
                .requires(ModItems.ABYSS_SHARD)
                .unlockedBy("has_abyss_shard", has(ModItems.ABYSS_SHARD)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ABYSS_SHARD_HELMET.get(), 1)
                .requires(Items.DIAMOND_HELMET)
                .requires(ModItems.ABYSS_SHARD)
                .unlockedBy("has_abyss_shard", has(ModItems.ABYSS_SHARD)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ABYSS_SHARD_CHESTPLATE.get(), 1)
                .requires(Items.DIAMOND_CHESTPLATE)
                .requires(ModItems.ABYSS_SHARD)
                .unlockedBy("has_abyss_shard", has(ModItems.ABYSS_SHARD)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ABYSS_SHARD_LEGGINGS.get(), 1)
                .requires(Items.DIAMOND_LEGGINGS)
                .requires(ModItems.ABYSS_SHARD)
                .unlockedBy("has_abyss_shard", has(ModItems.ABYSS_SHARD)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ABYSS_SHARD_BOOTS.get(), 1)
                .requires(Items.DIAMOND_BOOTS)
                .requires(ModItems.ABYSS_SHARD)
                .unlockedBy("has_abyss_shard", has(ModItems.ABYSS_SHARD)).save(recipeOutput);

        /*
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FARAAM_HELMET.get())
                .requires(Items.LEATHER)
                .requires(Items.IRON_HELMET)

                .unlockedBy(getHasName(Items.IRON_HELMET), has(Items.IRON_HELMET));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FARAAM_CHESTPLATE.get())
                .requires(Items.LEATHER)
                .requires(Items.IRON_CHESTPLATE)

                .unlockedBy(getHasName(Items.IRON_CHESTPLATE), has(Items.IRON_CHESTPLATE));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FARAAM_LEGGINGS.get())
                .requires(Items.LEATHER)
                .requires(Items.IRON_LEGGINGS)

                .unlockedBy(getHasName(Items.IRON_LEGGINGS), has(Items.IRON_LEGGINGS));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FARAAM_BOOTS.get())
                .requires(Items.LEATHER)
                .requires(Items.IRON_BOOTS)

                .unlockedBy(getHasName(Items.IRON_BOOTS), has(Items.IRON_BOOTS));





        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DESTINY_HELMET.get())
                .requires(Items.GOLD_INGOT)
                .requires(Items.LEATHER_HELMET)

                .unlockedBy(getHasName(Items.LEATHER_HELMET), has(Items.LEATHER_HELMET));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DESTINY_CHESTPLATE.get())
                .requires(Items.GOLD_INGOT)
                .requires(Items.LEATHER_CHESTPLATE)

                .unlockedBy(getHasName(Items.LEATHER_CHESTPLATE), has(Items.LEATHER_CHESTPLATE));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DESTINY_LEGGINGS.get())
                .requires(Items.GOLD_INGOT)
                .requires(Items.LEATHER_LEGGINGS)

                .unlockedBy(getHasName(Items.LEATHER_LEGGINGS), has(Items.LEATHER_LEGGINGS));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DESTINY_BOOTS.get())
                .requires(Items.GOLD_INGOT)
                .requires(Items.LEATHER_BOOTS)

                .unlockedBy(getHasName(Items.LEATHER_BOOTS), has(Items.LEATHER_BOOTS));
*/


        /*ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CLEAVER.get())
                .requires(ModItems.ABYSS_SHARD.get())
                .requires(Items.NETHERITE_AXE)

                .unlockedBy(getHasName(Items.NETHERITE_AXE), has(Items.NETHERITE_AXE));
         */

    }

    protected static void fullGeneral(ItemLike block, ItemLike item, String name, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block)
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', item)
                .unlockedBy("has_" + name, has(item)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item, 9)
                .requires(block)
                .unlockedBy("has_" + name + "_block", has(block)).save(recipeOutput);
    }

    protected static void foodGeneral(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        foodSmoking(recipeOutput, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup);
        foodCampFire(recipeOutput, pIngredients, pCategory, pResult, pExperience, (int) (pCookingTime *1.5), pGroup);
        foodCooking(recipeOutput, pIngredients, pCategory, pResult, pExperience, pCookingTime*2, pGroup);
    }

    protected static void oreGeneral(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreBlasting(recipeOutput, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup);
        oreSmelting(recipeOutput, pIngredients, pCategory, pResult, pExperience, pCookingTime*2, pGroup);
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void itemSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        itemCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void foodCooking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        itemCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_cooking");
    }
    protected static void foodSmoking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        itemCooking(recipeOutput, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smoking");
    }

    protected static void foodCampFire(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        itemCooking(recipeOutput, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_camp_fire");
    }


    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, WarlocksMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static <T extends AbstractCookingRecipe> void itemCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, WarlocksMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}