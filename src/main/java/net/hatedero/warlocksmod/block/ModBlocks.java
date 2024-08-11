package net.hatedero.warlocksmod.block;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.block.custom.ModFlammableRotatedPillarBlock;
import net.hatedero.warlocksmod.block.custom.PillarBlock;
import net.hatedero.warlocksmod.block.custom.SoulberryCropBlock;
import net.hatedero.warlocksmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(WarlocksMod.MOD_ID);

    public static final DeferredBlock<Block> ABYSS_GRASS = registerBlock("abyss_grass",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).sound(SoundType.SCULK)));

    public static final DeferredBlock<Block> ABYSS_DIRT = registerBlock("abyss_dirt",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).sound(SoundType.SCULK)));

    public static final DeferredBlock<Block> ABYSS_STONE = registerBlock("abyss_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.SCULK)));





    public static final DeferredBlock<Block> SOUL_TREE_LOG = registerBlock("soul_tree_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3)));
    public static final DeferredBlock<Block> SOUL_TREE_WOOD = registerBlock("soul_tree_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3)));
    public static final DeferredBlock<Block> STRIPPED_SOUL_TREE_LOG = registerBlock("stripped_soul_tree_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3)));
    public static final DeferredBlock<Block> STRIPPED_SOUL_TREE_WOOD = registerBlock("stripped_soul_tree_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3)));
    public static final DeferredBlock<Block> SOUL_TREE_PLANKS = registerBlock("soul_tree_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final DeferredBlock<Block> SOUL_TREE_LEAVES = registerBlock("soul_tree_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).lightLevel((litBlockEmission) -> { return 7; })) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

//    public static final DeferredBlock<Block> SOUL_TREE_SAPLING = registerBlock("soul_tree_sapling",
//            () -> new SaplingBlock(new SoultreeTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).strength(3f).lightLevel((litBlockEmission) -> { return 7; })){
//                @Override
//                protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
//                    return pState.is(Blocks.FARMLAND) || pState.is(BlockTags.DIRT);
//                }
//            });

    public static final DeferredBlock<Block> SOULBERRY_CROP = BLOCKS.register("soulberry_crop",
            () -> new SoulberryCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).noLootTable().noCollission().lightLevel((litBlockEmission) -> { return 7; })));





    public static final DeferredBlock<Block> BLUE_PLUM_LOG = registerBlock("blue_plum_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3)));
    public static final DeferredBlock<Block> BLUE_PLUM_WOOD = registerBlock("blue_plum_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3)));
    public static final DeferredBlock<Block> STRIPPED_BLUE_PLUM_LOG = registerBlock("stripped_blue_plum_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3)));
    public static final DeferredBlock<Block> STRIPPED_BLUE_PLUM_WOOD = registerBlock("stripped_blue_plum_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3)));
    public static final DeferredBlock<Block> BLUE_PLUM_PLANKS = registerBlock("blue_plum_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final DeferredBlock<Block> BLUE_PLUM_LEAVES = registerBlock("blue_plum_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

//    public static final DeferredBlock<Block> BLUE_PLUM_SAPLING = registerBlock("blue_plum_sapling",
//            () -> new SaplingBlock(new BlueplumTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).strength(3f)){
//                @Override
//                protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
//                    return pState.is(Blocks.FARMLAND) || pState.is(BlockTags.DIRT);
//                }
//            });





    public static final DeferredBlock<Block> SULFUR_BLOCK = registerBlock("sulfur_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));

//    public static final DeferredBlock<Block> SULFUR_ORE = registerBlock("sulfur_ore",
//            () -> new DropExperienceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noLootTable()
//                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3,6)));





    public static final DeferredBlock<Block> SALT_BLOCK = registerBlock("salt_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));





    public static final DeferredBlock<Block> CRYSTAL_BLOCK = registerBlock("crystal_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST)));





    public static final DeferredBlock<Block> ABYSS_GLASS = registerBlock("abyss_glass",
            () -> new TintedGlassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TINTED_GLASS).sound(SoundType.AMETHYST).noOcclusion().strength(5.0F, 1200.0F)));

    public static final DeferredBlock<Block> ABYSS_LAMP = registerBlock("abyss_lamp",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP).sound(SoundType.AMETHYST).noOcclusion().strength(5.0F, 1200.0F).lightLevel((litBlockEmission) -> { return 15; })));

    public static final DeferredBlock<Block> SOUL_PLANT_BLIGHT = registerBlock("soul_plant_blight",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PEARLESCENT_FROGLIGHT).sound(SoundType.SCULK).noOcclusion().lightLevel((litBlockEmission) -> { return 15; })));





    public static final DeferredBlock<Block> BACKROOM_WALL = registerBlock("backroom_wall",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL).sound(SoundType.MUD_BRICKS)));





    public static final DeferredBlock<Block> BLUE_TEST = registerBlock("blue_test",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL).sound(SoundType.MUD_BRICKS)));

    public static final DeferredBlock<Block> WHITE_TEST = registerBlock("white_test",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL).sound(SoundType.MUD_BRICKS)));

    public static final DeferredBlock<Block> ABYSS_STONE_BRICKS = registerBlock("abyss_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL).sound(SoundType.MUD_BRICKS)));

    public static final DeferredBlock<Block> EVERBRIGHT_BRICKS = registerBlock("everbright_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL).sound(SoundType.MUD_BRICKS).lightLevel((litBlockEmission) -> { return 15; })));

    public static final DeferredBlock<Block> BLIGHT_BRICKS = registerBlock("blight_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL).sound(SoundType.MUD_BRICKS)));

    public static final DeferredBlock<Block> REINFORCED_ABYSS_STONE_BRICKS = registerBlock("reinforced_abyss_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL).sound(SoundType.MUD_BRICKS).strength(5.0F, 1200.0F)));

    public static final DeferredBlock<Block> REINFORCED_BLIGHT_BRICKS = registerBlock("reinforced_blight_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL).sound(SoundType.MUD_BRICKS).strength(5.0F, 1200.0F)));

    public static final DeferredBlock<Block> REINFORCED_EVERBRIGHT_BRICKS = registerBlock("reinforced_everbright_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL).sound(SoundType.MUD_BRICKS).strength(5.0F, 1200.0F).lightLevel((litBlockEmission) -> { return 15; })));






    public static final DeferredBlock<Block> TALL_DRACAENA = registerBlock("tall_dracaena",
            () -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).sound(SoundType.SCULK).noCollission().noOcclusion().lightLevel((litBlockEmission) -> { return 7; })){
                @Override
                protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
                    return pState.is(Blocks.FARMLAND) || pState.is(BlockTags.DIRT) || pState.is(Blocks.SCULK);
                }
            });

    public static final DeferredBlock<Block> DRACAENA = registerBlock("dracaena",
            () -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).sound(SoundType.SCULK).noCollission().noOcclusion().lightLevel((litBlockEmission) -> { return 7; })){
                @Override
                protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
                    return pState.is(Blocks.FARMLAND) || pState.is(BlockTags.DIRT) || pState.is(Blocks.SCULK);
                }
            });





    public static final DeferredBlock<Block> SOUL_PLANT_BLOCK = registerBlock("soul_plant_block",
            () -> new TwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).sound(SoundType.SCULK).noLootTable().noCollission().noOcclusion().lightLevel((litBlockEmission) -> { return 7; })){
                @Override
                protected GrowingPlantHeadBlock getHeadBlock() {
                    return (GrowingPlantHeadBlock) ModBlocks.SOUL_PLANT.get();
                }
            });

    public static final DeferredBlock<Block> SOUL_PLANT = registerBlock("soul_plant",
            () -> new TwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).sound(SoundType.SCULK).noOcclusion().lightLevel((litBlockEmission) -> { return 7; })){
                @Override
                protected Block getBodyBlock() {
                    return ModBlocks.SOUL_PLANT_BLOCK.get();
                }
            });





    public static final DeferredBlock<Block> ABYSS_CHAIN = registerBlock("abyss_chain",
            () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN).sound(SoundType.CHAIN)));

    public static final DeferredBlock<Block> BASE_PILLAR = registerBlock("base_pillar",
            () -> new PillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).noOcclusion()));

    public static final DeferredBlock<Block> PILLAR = registerBlock("pillar",
            () -> new PillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).noOcclusion()));

//    public static final RegistryObject<Block> MOD_PORTAL = registerBlock("mod_portal",
//            () -> new ModPortalBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.SCULK).noLootTable().noOcclusion().noCollission()));

//    public static final RegistryObject<Block> ABYSS_ORE = registerBlock("abyss_ore",
//            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
//                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3,6)));
//
//    public static final RegistryObject<Block> ABYSS_BLOCK = registerBlock("abyss_block",
//            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SCULK).sound(SoundType.SCULK_SHRIEKER)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}