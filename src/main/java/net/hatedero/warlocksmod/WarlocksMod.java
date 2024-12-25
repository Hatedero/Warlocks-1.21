package net.hatedero.warlocksmod;

import net.hatedero.warlocksmod.block.ModBlocks;
import net.hatedero.warlocksmod.block.custom.AbyssTakenBlock;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.effect.ModEffects;
import net.hatedero.warlocksmod.enchantment.ModEnchantmentEffects;
import net.hatedero.warlocksmod.entity.ModEntities;
import net.hatedero.warlocksmod.entity.black_hole.BlackHoleRenderer;
import net.hatedero.warlocksmod.item.ModCreativeModeTabs;
import net.hatedero.warlocksmod.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import net.hatedero.warlocksmod.item.ModItems;

import java.awt.*;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(WarlocksMod.MOD_ID)
public class WarlocksMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "warlocksmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public WarlocksMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEntities.register(modEventBus);

        ModEffects.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);

        ModAttachment.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.BLACK_HOLE.get(), BlackHoleRenderer::new);
        }

        @SubscribeEvent
        public static void blockColorHandlerEvent(final RegisterColorHandlersEvent.Block event)
        {
            valueCalc t = new valueCalc();

            event.register(
                    (state, world, pos, tintIndex) -> {
                        return world != null && pos != null ? BiomeColors.getAverageWaterColor(world, pos) : -1;
                    }
            , ModBlocks.UNIFORM_OCEAN_BLOCK.get());

            event.register(
                    (state, world, pos, tintIndex) -> {
                        return world != null && pos != null ? t.getAbyssTakenBlockColorFromHeight(pos) : -1;
                    }
                    , ModBlocks.ABYSS_DIRT.get(), ModBlocks.ABYSS_GRASS.get());
        }

        @SubscribeEvent
        public static void itemColorHandlerEvent(final RegisterColorHandlersEvent.Item event)
        {
            valueCalc t = new valueCalc();
            event.register((stack, tintIndex) -> {
                int color = BiomeColors.getAverageWaterColor(Minecraft.getInstance().player.level(), Minecraft.getInstance().player.blockPosition());
                return color;
            }, ModBlocks.UNIFORM_OCEAN_BLOCK);

            event.register((stack, tintIndex) -> {
                int color = t.getAbyssTakenBlockColorFromHeight(Minecraft.getInstance().player.blockPosition());
                return color;
            }, ModBlocks.ABYSS_DIRT, ModBlocks.ABYSS_GRASS);

        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
//            event.register(KeyBinding.DASH_KEY);
            event.register(KeyBinding.DOUBLE_JUMP_KEY);
//            event.register(KeyBinding.BLINK_KEY);
//            event.register(KeyBinding.BLACK_HOLE_KEY);
            event.register(KeyBinding.INFINITY_KEY);
            event.register(KeyBinding.GRAVITY_KEY);
            event.register(KeyBinding.RESET_ABILITIES_KEY);
            event.register(KeyBinding.SUPER_KEY);
            event.register(KeyBinding.MELEE_ABILITY_KEY);
            event.register(KeyBinding.MOVEMENT_ABILITY_KEY);
        }
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, path);
    }
}
