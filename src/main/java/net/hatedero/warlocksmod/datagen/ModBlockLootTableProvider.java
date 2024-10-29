package net.hatedero.warlocksmod.datagen;

import net.hatedero.warlocksmod.block.ModBlocks;
import net.hatedero.warlocksmod.block.custom.SoulberryCropBlock;
import net.hatedero.warlocksmod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        dropSelf(ModBlocks.ABYSS_GRASS.get());
        dropSelf(ModBlocks.ABYSS_DIRT.get());
        dropSelf(ModBlocks.ABYSS_STONE.get());





        dropSelf(ModBlocks.SOUL_TREE_LOG.get());
        dropSelf(ModBlocks.SOUL_TREE_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_SOUL_TREE_LOG.get());
        dropSelf(ModBlocks.STRIPPED_SOUL_TREE_WOOD.get());
        dropSelf(ModBlocks.SOUL_TREE_PLANKS.get());
       // dropSelf(ModBlocks.SOUL_TREE_SAPLING.get());
        //add(ModBlocks.tutorialmod.get(), block ->
               // createLeavesDrops(block, ModBlocks.SOUL_TREE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
//        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
//                .hasBlockStateProperties(ModBlocks.SOULBERRY_CROP.get())
//                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoulberryCropBlock.AGE, 5));
//        add(ModBlocks.SOULBERRY_CROP.get(), createCropDrops(ModBlocks.SOULBERRY_CROP.get(), ModItems.SOUL_BERRY.get(),
//                ModItems.SOULBERRY_SEEDS.get(), lootitemcondition$builder));





        dropSelf(ModBlocks.BLUE_PLUM_LOG.get());
        dropSelf(ModBlocks.BLUE_PLUM_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_BLUE_PLUM_LOG.get());
        dropSelf(ModBlocks.STRIPPED_BLUE_PLUM_WOOD.get());
        dropSelf(ModBlocks.BLUE_PLUM_PLANKS.get());
        //dropSelf(ModBlocks.BLUE_PLUM_SAPLING.get());
        //add(ModBlocks.BLUE_PLUM_LEAVES.get(), block ->
          //      createLeavesDrops(block, ModBlocks.BLUE_PLUM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));





        dropSelf(ModBlocks.SULFUR_BLOCK.get());
        add(ModBlocks.SULFUR_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.SULFUR_ORE.get(), ModItems.SULFUR_POWDER.get(), 2, 5));





        dropSelf(ModBlocks.SALT_BLOCK.get());





        dropSelf(ModBlocks.CRYSTAL_BLOCK.get());





        dropSelf(ModBlocks.ABYSS_GLASS.get());
        dropSelf(ModBlocks.ABYSS_LAMP.get());
        dropSelf(ModBlocks.SOUL_PLANT_BLIGHT.get());





        dropSelf(ModBlocks.BACKROOM_WALL.get());





        dropSelf(ModBlocks.BLUE_TEST.get());
        dropSelf(ModBlocks.WHITE_TEST.get());
        dropSelf(ModBlocks.ABYSS_STONE_BRICKS.get());
        dropSelf(ModBlocks.EVERBRIGHT_BRICKS.get());
        dropSelf(ModBlocks.BLIGHT_BRICKS.get());
        dropSelf(ModBlocks.REINFORCED_ABYSS_STONE_BRICKS.get());
        dropSelf(ModBlocks.REINFORCED_EVERBRIGHT_BRICKS.get());
        dropSelf(ModBlocks.REINFORCED_BLIGHT_BRICKS.get());





        dropSelf(ModBlocks.TALL_DRACAENA.get());
        dropSelf(ModBlocks.DRACAENA.get());





        dropSelf(ModBlocks.BASE_PILLAR.get());
        dropSelf(ModBlocks.PILLAR.get());
        dropSelf(ModBlocks.ABYSS_CHAIN.get());





        dropSelf(ModBlocks.SOUL_PLANT.get());
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
