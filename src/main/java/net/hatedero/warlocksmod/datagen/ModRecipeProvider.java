package net.hatedero.warlocksmod.datagen;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.block.ModBlocks;
import net.hatedero.warlocksmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
//import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static List<ItemLike> SULFUR_SMELTABLE = List.of(ModItems.SULFUR_POWDER.get(),
            ModBlocks.SULFUR_ORE.get());

    private static List<ItemLike> CURED_ROTTEN_FLESH_COOKABLE = List.of(ModItems.COOKED_CURED_ROTTEN_FLESH.get(),
            ModItems.CURED_ROTTEN_FLESH.get());

    private static List<ItemLike> SALT_COOKABLE = List.of(ModItems.SALT_POWDER.get(),
            Items.WATER_BUCKET);

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreBlasting(pWriter, SULFUR_SMELTABLE, RecipeCategory.MISC, ModItems.SULFUR_POWDER.get(), 0.25f, 100, "sulfur");
        itemCooking(pWriter, SULFUR_SMELTABLE, RecipeCategory.MISC, ModItems.SULFUR_POWDER.get(), 0.25f, 200, "sulfur");
        foodSmoking(pWriter, CURED_ROTTEN_FLESH_COOKABLE, RecipeCategory.MISC, ModItems.COOKED_CURED_ROTTEN_FLESH.get(), 0.25f, 100, "rotten_flesh");
        itemCooking(pWriter, CURED_ROTTEN_FLESH_COOKABLE, RecipeCategory.MISC, ModItems.COOKED_CURED_ROTTEN_FLESH.get(), 0.25f, 200, "rotten_flesh");
        itemCooking(pWriter, SALT_COOKABLE, RecipeCategory.MISC, ModItems.SALT_POWDER.get(), 0.25f, 200, "salt");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SULFUR_BLOCK.get().asItem())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SULFUR_POWDER.get())
                .unlockedBy(getHasName(ModItems.SULFUR_POWDER.get()), has(ModItems.SULFUR_POWDER.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SULFUR_POWDER.get())
                .requires(ModBlocks.SULFUR_BLOCK.get().asItem())
                .unlockedBy(getHasName(ModBlocks.SULFUR_BLOCK.get().asItem()), has(ModBlocks.SULFUR_BLOCK.get().asItem()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SALT_BLOCK.get().asItem())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SALT_POWDER.get())
                .unlockedBy(getHasName(ModItems.SALT_POWDER.get()), has(ModItems.SALT_POWDER.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SALT_POWDER.get())
                .requires(ModBlocks.SALT_BLOCK.get().asItem())
                .unlockedBy(getHasName(ModBlocks.SALT_BLOCK.get().asItem()), has(ModBlocks.SALT_BLOCK.get().asItem()))
                .save(pWriter);






//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ABYSS_WALKER_HELMET.get())
//                .requires(ModItems.ABYSS_SHARD.get())
//                .requires(Items.DIAMOND_HELMET)
//
//                .unlockedBy(getHasName(Items.DIAMOND_HELMET), has(Items.DIAMOND_HELMET))
//                .save(pWriter);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ABYSS_WALKER_CHESTPLATE.get())
//                .requires(ModItems.ABYSS_SHARD.get())
//                .requires(Items.DIAMOND_CHESTPLATE)
//
//                .unlockedBy(getHasName(Items.DIAMOND_CHESTPLATE), has(Items.DIAMOND_CHESTPLATE))
//                .save(pWriter);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ABYSS_WALKER_LEGGINGS.get())
//                .requires(ModItems.ABYSS_SHARD.get())
//                .requires(Items.DIAMOND_LEGGINGS)
//
//                .unlockedBy(getHasName(Items.DIAMOND_LEGGINGS), has(Items.DIAMOND_LEGGINGS))
//                .save(pWriter);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ABYSS_WALKER_BOOTS.get())
//                .requires(ModItems.ABYSS_SHARD.get())
//                .requires(Items.DIAMOND_BOOTS)
//
//                .unlockedBy(getHasName(Items.DIAMOND_BOOTS), has(Items.DIAMOND_BOOTS))
//                .save(pWriter);
//
//
//
//
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FARAAM_HELMET.get())
//                .requires(Items.LEATHER)
//                .requires(Items.IRON_HELMET)
//
//                .unlockedBy(getHasName(Items.IRON_HELMET), has(Items.IRON_HELMET))
//                .save(pWriter);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FARAAM_CHESTPLATE.get())
//                .requires(Items.LEATHER)
//                .requires(Items.IRON_CHESTPLATE)
//
//                .unlockedBy(getHasName(Items.IRON_CHESTPLATE), has(Items.IRON_CHESTPLATE))
//                .save(pWriter);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FARAAM_LEGGINGS.get())
//                .requires(Items.LEATHER)
//                .requires(Items.IRON_LEGGINGS)
//
//                .unlockedBy(getHasName(Items.IRON_LEGGINGS), has(Items.IRON_LEGGINGS))
//                .save(pWriter);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FARAAM_BOOTS.get())
//                .requires(Items.LEATHER)
//                .requires(Items.IRON_BOOTS)
//
//                .unlockedBy(getHasName(Items.IRON_BOOTS), has(Items.IRON_BOOTS))
//                .save(pWriter);
//
//
//
//
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DESTINY_HELMET.get())
//                .requires(Items.GOLD_INGOT)
//                .requires(Items.LEATHER_HELMET)
//
//                .unlockedBy(getHasName(Items.LEATHER_HELMET), has(Items.LEATHER_HELMET))
//                .save(pWriter);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DESTINY_CHESTPLATE.get())
//                .requires(Items.GOLD_INGOT)
//                .requires(Items.LEATHER_CHESTPLATE)
//
//                .unlockedBy(getHasName(Items.LEATHER_CHESTPLATE), has(Items.LEATHER_CHESTPLATE))
//                .save(pWriter);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DESTINY_LEGGINGS.get())
//                .requires(Items.GOLD_INGOT)
//                .requires(Items.LEATHER_LEGGINGS)
//
//                .unlockedBy(getHasName(Items.LEATHER_LEGGINGS), has(Items.LEATHER_LEGGINGS))
//                .save(pWriter);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DESTINY_BOOTS.get())
//                .requires(Items.GOLD_INGOT)
//                .requires(Items.LEATHER_BOOTS)
//
//                .unlockedBy(getHasName(Items.LEATHER_BOOTS), has(Items.LEATHER_BOOTS))
//                .save(pWriter);





        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ABYSS_SHARD.get())
                .requires(Items.OBSIDIAN)
                .requires(Items.QUARTZ)
                .requires(ModBlocks.SOUL_TREE_LOG.get().asItem())

                .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .save(pWriter);

//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CLEAVER.get())
//                .requires(ModItems.ABYSS_SHARD.get())
//                .requires(Items.NETHERITE_AXE)
//
//                .unlockedBy(getHasName(Items.NETHERITE_AXE), has(Items.NETHERITE_AXE))
//                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.ABYSS_CHAIN.get().asItem())
                .requires(ModItems.ABYSS_SHARD.get())
                .requires(Items.CHAIN)

                .unlockedBy(getHasName(Items.CHAIN), has(Items.CHAIN))
                .save(pWriter);
    }

    protected static void itemCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        Cooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        Cooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void foodSmoking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        Cooking(pFinishedRecipeConsumer, RecipeSerializer.SMOKING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smoking");
    }

    protected static void Cooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, WarlocksMod.MOD_ID + ":" +getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }

}