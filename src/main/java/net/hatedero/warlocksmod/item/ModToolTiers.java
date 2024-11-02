package net.hatedero.warlocksmod.item;

import net.hatedero.warlocksmod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier ABYSS_SHARD = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_ABYSS_SHARD_TOOL,
            1000, 4f, 3f, 28, () -> Ingredient.of(ModItems.ABYSS_SHARD));

}