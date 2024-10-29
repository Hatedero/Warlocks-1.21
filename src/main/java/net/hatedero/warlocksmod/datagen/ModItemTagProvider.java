package net.hatedero.warlocksmod.datagen;


import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.block.ModBlocks;
import net.hatedero.warlocksmod.item.ModItems;
import net.hatedero.warlocksmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, WarlocksMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.LOGS)
                .add(ModBlocks.SOUL_TREE_LOG.get().asItem())
                .add(ModBlocks.SOUL_TREE_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_SOUL_TREE_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_SOUL_TREE_WOOD.get().asItem())
                .add(ModBlocks.BLUE_PLUM_LOG.get().asItem())
                .add(ModBlocks.BLUE_PLUM_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_BLUE_PLUM_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_BLUE_PLUM_WOOD.get().asItem());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.SOUL_TREE_LOG.get().asItem())
                .add(ModBlocks.SOUL_TREE_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_SOUL_TREE_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_SOUL_TREE_WOOD.get().asItem())
                .add(ModBlocks.BLUE_PLUM_LOG.get().asItem())
                .add(ModBlocks.BLUE_PLUM_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_BLUE_PLUM_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_BLUE_PLUM_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.SOUL_TREE_PLANKS.get().asItem())
                .add(ModBlocks.BLUE_PLUM_PLANKS.get().asItem());

        /*this.tag(ItemTags.SWORDS)
                .add(ModItems.CLEAVER.get().asItem());*/

        this.tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(ModBlocks.ABYSS_STONE.get().asItem());

        this.tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(ModBlocks.ABYSS_STONE.get().asItem());

        this.tag(ItemTags.COALS)
                .add(ModItems.SULFUR_POWDER.get().asItem());
    }
}