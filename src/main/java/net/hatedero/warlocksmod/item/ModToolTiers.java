package net.hatedero.warlocksmod.item;

import net.hatedero.warlocksmod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier ABYSS_SHARD = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_ABYSS_SHARD_TOOL,
            1000, 4f, 3f, 28, () -> Ingredient.of(ModItems.ABYSS_SHARD));

    public static final Tier STYGIAN_IRON = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_STYGIAN_IRON_TOOL,
            500, 4f, 2f, 17, () -> Ingredient.of(ModItems.STYGIAN_IRON));

    public static final Tier CELESTIAL_BRONZE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_CELESTIAL_BRONZE_TOOL,
            500, 5f, 1f, 26, () -> Ingredient.of(ModItems.CELESTIAL_BRONZE));

}