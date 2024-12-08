package net.hatedero.warlocksmod.util;

import net.hatedero.warlocksmod.WarlocksMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_ABYSS_SHARD_TOOL = createTag("needs_abyss_shard_tool");
        public static final TagKey<Block> NEEDS_CELESTIAL_BRONZE_TOOL = createTag("needs_celestial_bronze_tool");
        public static final TagKey<Block> NEEDS_STYGIAN_IRON_TOOL = createTag("needs_stygian_iron_tool");
        public static final TagKey<Block> INCORRECT_FOR_ABYSS_SHARD_TOOL = createTag("incorrect_for_abyss_shard_tool");
        public static final TagKey<Block> INCORRECT_FOR_CELESTIAL_BRONZE_TOOL = createTag("incorrect_for_celestial_bronze_tool");
        public static final TagKey<Block> INCORRECT_FOR_STYGIAN_IRON_TOOL = createTag("incorrect_for_stygian_iron_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> CRUSHABLE_ITEMS = createTag("crushable_items");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, name));
        }
    }
}