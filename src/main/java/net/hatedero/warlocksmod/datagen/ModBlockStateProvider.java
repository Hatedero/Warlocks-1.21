package net.hatedero.warlocksmod.datagen;


import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.block.ModBlocks;
import net.hatedero.warlocksmod.block.custom.SoulberryCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, WarlocksMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //blockItem(ModBlocks.ABYSS_GRASS);
        blockWithItem(ModBlocks.ABYSS_DIRT);
        blockWithItem(ModBlocks.ABYSS_STONE);
        blockWithItem(ModBlocks.BOUNCE_UP);
        //blockItem(ModBlocks.BASIC_CRUSHER);
        //blockWithItem(ModBlocks.ABYSS_CORE);
        blockWithItem(ModBlocks.ABYSS_REACTOR_BLOCK);

        logBlock(((RotatedPillarBlock) ModBlocks.SOUL_TREE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.SOUL_TREE_WOOD.get()), blockTexture(ModBlocks.SOUL_TREE_LOG.get()), blockTexture(ModBlocks.SOUL_TREE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SOUL_TREE_LOG.get()), blockTexture(ModBlocks.STRIPPED_SOUL_TREE_LOG.get()),
                modLoc("block/stripped_soul_tree_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SOUL_TREE_WOOD.get()), blockTexture(ModBlocks.STRIPPED_SOUL_TREE_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_SOUL_TREE_LOG.get()));
        blockItem(ModBlocks.SOUL_TREE_LOG);
        blockItem(ModBlocks.SOUL_TREE_WOOD);
        blockItem(ModBlocks.STRIPPED_SOUL_TREE_LOG);
        blockItem(ModBlocks.STRIPPED_SOUL_TREE_WOOD);
        blockWithItem(ModBlocks.SOUL_TREE_PLANKS);
        //leavesBlock(ModBlocks.SOUL_TREE_LEAVES);
        //saplingBlock(ModBlocks.SOUL_TREE_SAPLING);
        //makeSoulberryCrop((CropBlock)ModBlocks.SOULBERRY_CROP.get(), "soulberry_stage", "soulberry_stage");

        logBlock(((RotatedPillarBlock) ModBlocks.BLUE_PLUM_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.BLUE_PLUM_WOOD.get()), blockTexture(ModBlocks.BLUE_PLUM_LOG.get()), blockTexture(ModBlocks.BLUE_PLUM_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLUE_PLUM_LOG.get()), blockTexture(ModBlocks.STRIPPED_BLUE_PLUM_LOG.get()),
                modLoc("block/stripped_blue_plum_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLUE_PLUM_WOOD.get()), blockTexture(ModBlocks.STRIPPED_BLUE_PLUM_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_BLUE_PLUM_LOG.get()));
        blockItem(ModBlocks.BLUE_PLUM_LOG);
        blockItem(ModBlocks.BLUE_PLUM_WOOD);
        blockItem(ModBlocks.STRIPPED_BLUE_PLUM_LOG);
        blockItem(ModBlocks.STRIPPED_BLUE_PLUM_WOOD);
        blockWithItem(ModBlocks.BLUE_PLUM_PLANKS);
        //leavesBlock(ModBlocks.BLUE_PLUM_LEAVES);
        //saplingBlock(ModBlocks.BLUE_PLUM_SAPLING);

        //blockItem(ModBlocks.ABYSS_CHAIN);

        blockWithItem(ModBlocks.SULFUR_BLOCK);
        blockWithItem(ModBlocks.SULFUR_ORE);

        blockWithItem(ModBlocks.SALT_BLOCK);





        blockWithItem(ModBlocks.CRYSTAL_BLOCK);





        //blockWithItem(ModBlocks.ABYSS_GLASS);
        blockWithItem(ModBlocks.ABYSS_LAMP);
        blockWithItem(ModBlocks.SOUL_PLANT_BLIGHT);





        blockWithItem(ModBlocks.BLUE_TEST);
        blockWithItem(ModBlocks.WHITE_TEST);
        blockWithItem(ModBlocks.ABYSS_STONE_BRICKS);
        blockWithItem(ModBlocks.EVERBRIGHT_BRICKS);
        blockWithItem(ModBlocks.BLIGHT_BRICKS);
        blockWithItem(ModBlocks.REINFORCED_ABYSS_STONE_BRICKS);
        blockWithItem(ModBlocks.REINFORCED_EVERBRIGHT_BRICKS);
        blockWithItem(ModBlocks.REINFORCED_BLIGHT_BRICKS);





        blockWithItem(ModBlocks.BACKROOM_WALL);

        /*simpleBlockWithItem(ModBlocks.BASE_PILLAR.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/base_pillar")));

        simpleBlockWithItem(ModBlocks.PILLAR.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/pillar")));
    */

        makeCrop(((CropBlock) ModBlocks.SOULBERRY_CROP.get()), "soulberry_stage", "soulberry_stage");
    }


    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("warlocksmod:block/" + deferredBlock.getId().getPath()));
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((SoulberryCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "block/" + textureName + state.getValue(((SoulberryCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

}