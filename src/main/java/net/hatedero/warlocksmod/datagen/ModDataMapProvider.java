package net.hatedero.warlocksmod.datagen;

import net.hatedero.warlocksmod.block.ModBlocks;
import net.hatedero.warlocksmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)

                .add(ModItems.SULFUR_POWDER.getId(), new FurnaceFuel(1200), false)
                .add(ModBlocks.SULFUR_BLOCK.getId(), new FurnaceFuel(12000), false)
                .add(ModItems.SOUL_TENDRIL.getId(), new FurnaceFuel(600), false)
                .add(ModItems.DAWNBREAKER_HILT.getId(), new FurnaceFuel(90000), false)
                .add(ModItems.DAWNBREAKER_BLADE.getId(), new FurnaceFuel(100000), false)
                .add(ModItems.DAWNBREAKER_TIP.getId(), new FurnaceFuel(90000), false)
        ;

        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModItems.SOULBERRY_SEEDS.getId(), new Compostable(0.25f), false)
                .add(ModItems.SOULBERRY.getId(), new Compostable(0.45f), false);
    }
}