package net.hatedero.warlocksmod.datagen;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.block.ModBlocks;
import net.hatedero.warlocksmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WarlocksMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.NEEDS_STYGIAN_IRON_TOOL)
                .addTags(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.SULFUR_ORE.get(), ModBlocks.SULFUR_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ABYSS_CORE.get())
                //.add(ModBlocks.BASIC_CRUSHER.get())
                .add(ModBlocks.SULFUR_ORE.get(), ModBlocks.SULFUR_BLOCK.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.SOUL_TREE_LOG.get())
                .add(ModBlocks.SOUL_TREE_WOOD.get())
                .add(ModBlocks.STRIPPED_SOUL_TREE_LOG.get())
                .add(ModBlocks.STRIPPED_SOUL_TREE_WOOD.get())
                .add(ModBlocks.BLUE_PLUM_LOG.get())
                .add(ModBlocks.BLUE_PLUM_WOOD.get())
                .add(ModBlocks.STRIPPED_BLUE_PLUM_LOG.get())
                .add(ModBlocks.STRIPPED_BLUE_PLUM_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.SOUL_TREE_PLANKS.get())
                .add(ModBlocks.BLUE_PLUM_PLANKS.get());

        this.tag(BlockTags.DIRT)
                .add(ModBlocks.ABYSS_GRASS.get())
                .add(ModBlocks.ABYSS_DIRT.get());

        this.tag(BlockTags.BASE_STONE_OVERWORLD)
                .add(ModBlocks.ABYSS_STONE.get());

        this.tag(BlockTags.CLIMBABLE)
                .add(ModBlocks.SOUL_PLANT_BLOCK.get())
                .add(ModBlocks.SOUL_PLANT.get())
                .add(ModBlocks.ABYSS_CHAIN.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ABYSS_CORE.get());
                //.add(ModBlocks.BASIC_CRUSHER.get());

    }
}