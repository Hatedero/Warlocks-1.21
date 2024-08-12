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
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, WarlocksMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        simpleBlockItem(ModBlocks.ABYSS_GRASS.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/abyss_grass")));
        simpleBlockWithItem(ModBlocks.ABYSS_DIRT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/abyss_dirt")));
        simpleBlockWithItem(ModBlocks.ABYSS_STONE.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/abyss_stone")));

        logBlock(((RotatedPillarBlock) ModBlocks.SOUL_TREE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.SOUL_TREE_WOOD.get()), blockTexture(ModBlocks.SOUL_TREE_LOG.get()), blockTexture(ModBlocks.SOUL_TREE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SOUL_TREE_LOG.get()), blockTexture(ModBlocks.STRIPPED_SOUL_TREE_LOG.get()),
                new ResourceLocation(WarlocksMod.MOD_ID, "block/stripped_soul_tree_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SOUL_TREE_WOOD.get()), blockTexture(ModBlocks.STRIPPED_SOUL_TREE_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_SOUL_TREE_LOG.get()));
        simpleBlockItem(ModBlocks.SOUL_TREE_LOG.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/soul_tree_log")));
        simpleBlockItem(ModBlocks.SOUL_TREE_WOOD.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/soul_tree_wood")));
        simpleBlockItem(ModBlocks.STRIPPED_SOUL_TREE_LOG.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/stripped_soul_tree_log")));
        simpleBlockItem(ModBlocks.STRIPPED_SOUL_TREE_WOOD.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/stripped_soul_tree_wood")));
        simpleBlockWithItem(ModBlocks.SOUL_TREE_PLANKS.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/soul_tree_planks")));
        //leavesBlock(ModBlocks.SOUL_TREE_LEAVES);
        //saplingBlock(ModBlocks.SOUL_TREE_SAPLING);
        makeSoulberryCrop((CropBlock)ModBlocks.SOULBERRY_CROP.get(), "soulberry_stage", "soulberry_stage");

        logBlock(((RotatedPillarBlock) ModBlocks.BLUE_PLUM_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.BLUE_PLUM_WOOD.get()), blockTexture(ModBlocks.BLUE_PLUM_LOG.get()), blockTexture(ModBlocks.BLUE_PLUM_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLUE_PLUM_LOG.get()), blockTexture(ModBlocks.STRIPPED_BLUE_PLUM_LOG.get()),
                new ResourceLocation(WarlocksMod.MOD_ID, "block/stripped_blue_plum_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLUE_PLUM_WOOD.get()), blockTexture(ModBlocks.STRIPPED_BLUE_PLUM_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_BLUE_PLUM_LOG.get()));
        simpleBlockItem(ModBlocks.BLUE_PLUM_LOG);
        simpleBlockItem(ModBlocks.BLUE_PLUM_WOOD);
        simpleBlockItem(ModBlocks.STRIPPED_BLUE_PLUM_LOG);
        simpleBlockItem(ModBlocks.STRIPPED_BLUE_PLUM_WOOD);
        simpleBlockWithItem(ModBlocks.BLUE_PLUM_PLANKS);
        //leavesBlock(ModBlocks.BLUE_PLUM_LEAVES);
        //saplingBlock(ModBlocks.BLUE_PLUM_SAPLING);

        simpleBlockItem(ModBlocks.ABYSS_CHAIN);

        simpleBlockWithItem(ModBlocks.SULFUR_BLOCK);
        //blockWithItem(ModBlocks.SULFUR_ORE);

        simpleBlockWithItem(ModBlocks.SALT_BLOCK);





        simpleBlockWithItem(ModBlocks.CRYSTAL_BLOCK);





        simpleBlockWithItem(ModBlocks.ABYSS_GLASS);
        simpleBlockWithItem(ModBlocks.ABYSS_LAMP);
        simpleBlockWithItem(ModBlocks.SOUL_PLANT_BLIGHT);





        simpleBlockWithItem(ModBlocks.BLUE_TEST);
        simpleBlockWithItem(ModBlocks.WHITE_TEST);
        simpleBlockWithItem(ModBlocks.ABYSS_STONE_BRICKS);
        simpleBlockWithItem(ModBlocks.EVERBRIGHT_BRICKS);
        simpleBlockWithItem(ModBlocks.BLIGHT_BRICKS);
        simpleBlockWithItem(ModBlocks.REINFORCED_ABYSS_STONE_BRICKS);
        simpleBlockWithItem(ModBlocks.REINFORCED_EVERBRIGHT_BRICKS);
        simpleBlockWithItem(ModBlocks.REINFORCED_BLIGHT_BRICKS);





        simpleBlockWithItem(ModBlocks.BACKROOM_WALL);

        simpleBlockWithItem(ModBlocks.BASE_PILLAR.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/base_pillar")));

        simpleBlockWithItem(ModBlocks.PILLAR.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/pillar")));
    }

    public void makeSoulberryCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> soulberryStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] soulberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((SoulberryCropBlock) block).getAgeProperty()),
                new ResourceLocation(WarlocksMod.MOD_ID, "block/" + textureName + state.getValue(((SoulberryCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

//    private void blockItem(RegistryObject<Block> blockRegistryObject) {
//        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(WarlocksMod.MOD_ID +
//                ":block/" + NeoForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
//    }
//
//    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
//        simpleBlockWithItem(blockRegistryObject.get(),
//                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
//                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
//    }
//
//    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
//        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
//    }
//
//    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
//        simpleBlock(blockRegistryObject.get(),
//                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
//    }
}
