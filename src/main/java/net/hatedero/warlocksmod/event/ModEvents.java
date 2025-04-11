package net.hatedero.warlocksmod.event;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.effect.ModEffects;
import net.hatedero.warlocksmod.item.ModItems;
import net.hatedero.warlocksmod.item.custom.HammerItem;
import net.hatedero.warlocksmod.item.custom.weapons.heavy.light.ScytheItem;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.HashSet;
import java.util.Set;

//import static net.hatedero.warlocksmod.capability.abilities.dash.PlayerDashProvider.PLAYER_COOLDOWN;


@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(HammerItem.getRange(), initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    @SubscribeEvent
    public static void wallRunEvent(LivingEvent.LivingJumpEvent event){
        if(event.getEntity() instanceof ServerPlayer player/* && player.getDeltaMovement().horizontalDistance() >= 0.15*/ && player.horizontalCollision) {
            player.sendSystemMessage(Component.literal("wallrun possible"));
            player.addDeltaMovement(player.getDeltaMovement().add(0, 0.07, 0));
        }
    }

    @SubscribeEvent
    public static void onScytheUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof ScytheItem scythe && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(HammerItem.getRange(), initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !scythe.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                //serverPlayer.gameMode.destroyBlock(pos);
//                public UseOnContext(Player player, InteractionHand hand, BlockHitResult hitResult) {
//                    this(player.level(), player, hand, player.getItemInHand(hand), hitResult);
//                }

                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    @SubscribeEvent
    public static void damageDealtToPlayer(LivingIncomingDamageEvent event){
        if(event.getEntity() instanceof Player player && player.hasEffect(ModEffects.CURSED_EFFECT)){
            event.setAmount((float) (event.getAmount() * 1.2));
        }
    }

//    @SubscribeEvent
//    public static void damageDealtWithStygianIronTool(LivingDamageEvent.Post event){
//        if(event.getSource().getDirectEntity().getWeaponItem().is(StygianToolItem) == StygianToolItem stygianToolItem){
//
//        }
//    }

//    @SubscribeEvent
//    public static void tick(EntityTickEvent.Pre event){
//        if(event.getEntity() instanceof Skeleton) {
//            event.getEntity().setDeltaMovement(0, 0, 0);
//        }
//        if(event.getEntity() instanceof Arrow){
            //event.getEntity().setDeltaMovement(0,0,0);
//        }
//    }

    //THIS IS INTERESTING FOR INFINITY

    @SubscribeEvent
    public static void playerDealtDamage(LivingIncomingDamageEvent event){
        if(event.getSource().getDirectEntity() instanceof ServerPlayer player && player.hasEffect(ModEffects.ABYSS_GRASP_EFFECT)){
            player.heal((float) (event.getOriginalAmount() *0.3));
        }
    }

    @SubscribeEvent
    public static void equipmentChange(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player && event.getSlot() == EquipmentSlot.MAINHAND) {
            if (event.getTo().is(ModItems.DAWNBREAKER))
                player.addEffect(new MobEffectInstance(ModEffects.PHOENIX_EFFECT, 200, 0, false, false));
        }
    }

    @SubscribeEvent
    public static void onPotionApplied(MobEffectEvent.Added PStart) {
        if(PStart.getEntity() instanceof Player player && PStart.getEffectInstance().is(ModEffects.FREE_FLIGHT_EFFECT)) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
        if(PStart.getEntity() instanceof Player player && PStart.getEffectInstance().is(ModEffects.CURSED_EFFECT)) {
            player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(player.getAttribute(Attributes.MAX_HEALTH).getValue() * 0.8);
        }
    }

    @SubscribeEvent
    public static void onPotionExpiry(MobEffectEvent.Expired PEnd) {
            if(PEnd.getEntity() instanceof Player player && PEnd.getEffectInstance().is(ModEffects.FREE_FLIGHT_EFFECT)) {
                player.getAbilities().mayfly = player.isCreative();
                player.onUpdateAbilities();
            }
        if(PEnd.getEntity() instanceof Player player && PEnd.getEffectInstance().is(ModEffects.CURSED_EFFECT)) {
            player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(player.getAttribute(Attributes.MAX_HEALTH).getValue() * 1.25);
        }
    }

    @SubscribeEvent
    public static void onPotionCanceled(MobEffectEvent.Remove PEnd) {
            if(PEnd.getEntity() instanceof Player player && PEnd.getEffectInstance().is(ModEffects.FREE_FLIGHT_EFFECT)) {
                player.getAbilities().mayfly = player.isCreative();
                player.onUpdateAbilities();
            }
        if(PEnd.getEntity() instanceof Player player && PEnd.getEffectInstance().is(ModEffects.CURSED_EFFECT)) {
            player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(player.getAttribute(Attributes.MAX_HEALTH).getValue() * 1.25);
        }
    }
}