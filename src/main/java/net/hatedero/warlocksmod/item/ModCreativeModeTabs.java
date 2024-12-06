package net.hatedero.warlocksmod.item;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WarlocksMod.MOD_ID);

    public static final Supplier<CreativeModeTab> WARLOCKS_DEV_TAB = CREATIVE_MODE_TAB.register("warlocks_dev_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ABYSS_SHARD.get()))
                    .title(Component.translatable("creativetab.warlocksmod.warlocks_dev"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModBlocks.ABYSS_GRASS.get());
                        output.accept(ModBlocks.ABYSS_DIRT.get());
                        output.accept(ModBlocks.ABYSS_STONE.get());
                        output.accept(ModBlocks.ABYSS_CORE.get());
                        output.accept(ModBlocks.CRUSHER.get());
                        output.accept(ModBlocks.ABYSS_REACTOR_BLOCK.get());
                        output.accept(ModBlocks.BOUNCE_UP.get());

                        output.accept(ModItems.SUN_FRAGMENT.get());
                        output.accept(ModItems.PIECE_OF_ABYSS.get());
                        output.accept(ModItems.STILL_THUNDER.get());

                        output.accept(ModBlocks.SOUL_TREE_LOG.get());
                        output.accept(ModBlocks.SOUL_TREE_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_SOUL_TREE_LOG.get());
                        output.accept(ModBlocks.STRIPPED_SOUL_TREE_WOOD.get());
                        output.accept(ModBlocks.SOUL_TREE_PLANKS.get());
                        output.accept(ModBlocks.SOUL_TREE_LEAVES.get());
                        output.accept(ModBlocks.SOUL_TREE_SAPLING.get());
                        output.accept(ModBlocks.SOULBERRY_CROP.get());





                        output.accept(ModBlocks.BLUE_PLUM_LOG.get());
                        output.accept(ModBlocks.BLUE_PLUM_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_BLUE_PLUM_LOG.get());
                        output.accept(ModBlocks.STRIPPED_BLUE_PLUM_WOOD.get());
                        output.accept(ModBlocks.BLUE_PLUM_PLANKS.get());
                        //output.accept(ModBlocks.BLUE_PLUM_LEAVES.get());
                        //output.accept(ModBlocks.BLUE_PLUM_SAPLING.get());





                        output.accept(ModBlocks.SULFUR_BLOCK.get());
                        //output.accept(ModBlocks.SULFUR_ORE.get());





                        output.accept(ModBlocks.SALT_BLOCK.get());





                        output.accept(ModBlocks.CRYSTAL_BLOCK.get());





                        output.accept(ModBlocks.ABYSS_GLASS.get());
                        output.accept(ModBlocks.ABYSS_LAMP.get());
                        output.accept(ModBlocks.SOUL_PLANT_BLIGHT.get());





                        output.accept(ModBlocks.BACKROOM_WALL.get());





//                        output.accept(ModBlocks.BLUE_TEST.get());
//                        output.accept(ModBlocks.WHITE_TEST.get());
                        output.accept(ModBlocks.ABYSS_STONE_BRICKS.get());
                        output.accept(ModBlocks.EVERBRIGHT_BRICKS.get());
                        output.accept(ModBlocks.BLIGHT_BRICKS.get());
                        output.accept(ModBlocks.REINFORCED_ABYSS_STONE_BRICKS.get());
                        output.accept(ModBlocks.REINFORCED_EVERBRIGHT_BRICKS.get());
                        output.accept(ModBlocks.REINFORCED_BLIGHT_BRICKS.get());





                        output.accept(ModBlocks.DRACAENA.get());
                        output.accept(ModBlocks.TALL_DRACAENA.get());





                        output.accept(ModBlocks.SOUL_PLANT.get());





                        output.accept(ModBlocks.ABYSS_CHAIN.get());
                        output.accept(ModBlocks.BASE_PILLAR.get());
                        output.accept(ModBlocks.PILLAR.get());

                        output.accept(ModItems.ABYSS_SHARD.get());
                        output.accept(ModItems.ABYSSEAN_DUST.get());
                        output.accept(ModItems.ABYSS_SHARD_HAMMER.get());
                        output.accept(ModItems.SULFUR_POWDER.get());

//                        pOutput.accept(ModItems.RAT_SPAWN_EGG.get());
//                        pOutput.accept(ModItems.LOSTSOUL_SPAWN_EGG.get());

//                        pOutput.accept(ModItems.COIN.get());
                        output.accept(ModItems.SOULBERRY.get());
                        output.accept(ModItems.SOUL_TENDRIL.get());
                        output.accept(ModItems.SOULBERRY_SEEDS.get());

                        output.accept(ModItems.CLEAVER.get());
                        output.accept(ModItems.STYGIAN_IRON_SWORD.get());
                        output.accept(ModItems.DAWNBREAKER.get());
                        output.accept(ModItems.DAWNBREAKER_HILT.get());
                        output.accept(ModItems.DAWNBREAKER_BLADE.get());
                        output.accept(ModItems.DAWNBREAKER_TIP.get());
                        output.accept(ModItems.IMPERIAL_GOLD.get());
                        output.accept(ModItems.STYGIAN_IRON.get());
                        output.accept(ModItems.CELESTIAL_BRONZE.get());
                        //output.accept(ModItems.SPEAR.get());

                        output.accept(ModItems.SALT_POWDER.get());
                        output.accept(ModItems.CURED_ROTTEN_FLESH.get());
                        output.accept(ModItems.COOKED_CURED_ROTTEN_FLESH.get());

                        output.accept(ModItems.VOLCANIC_DUST.get());

                        output.accept(ModItems.J_FRUIT.get());
                        output.accept(ModItems.BEEF_SANDWICH.get());
                        output.accept(ModItems.CHICKEN_SANDWICH.get());
                        output.accept(ModItems.PORK_SANDWICH.get());
                        output.accept(ModItems.LAMB_SANDWICH.get());
                        output.accept(ModItems.AMERICAN_SANDWICH.get());

                        output.accept(ModItems.ABYSS_SHARD_HELMET.get());
                        output.accept(ModItems.ABYSS_SHARD_CHESTPLATE.get());
                        output.accept(ModItems.ABYSS_SHARD_LEGGINGS.get());
                        output.accept(ModItems.ABYSS_SHARD_BOOTS.get());
//
//                        output.accept(ModItems.DESTINY_HELMET.get());
//                        output.accept(ModItems.DESTINY_CHESTPLATE.get());
//                        output.accept(ModItems.DESTINY_LEGGINGS.get());
//                        output.accept(ModItems.DESTINY_BOOTS.get());
//
//                        output.accept(ModItems.FARAAM_HELMET.get());
//                        output.accept(ModItems.FARAAM_CHESTPLATE.get());
//                        output.accept(ModItems.FARAAM_LEGGINGS.get());
//                        output.accept(ModItems.FARAAM_BOOTS.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}