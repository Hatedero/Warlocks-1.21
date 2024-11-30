package net.hatedero.warlocksmod.event;


import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.effect.ModEffects;
import net.hatedero.warlocksmod.entity.ModEntities;
import net.hatedero.warlocksmod.entity.client.ModModelLayers;
import net.hatedero.warlocksmod.entity.custom.BlackHoleEntity;
import net.hatedero.warlocksmod.item.ModItems;
import net.hatedero.warlocksmod.item.custom.HammerItem;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.HashSet;
import java.util.Set;

import static net.minecraft.world.entity.ai.attributes.Attributes.GRAVITY;

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

//    @SubscribeEvent
//    public static void damageDealtToPlayer(LivingIncomingDamageEvent event){
//        if(event.getEntity() instanceof Player player){
//            player.sendSystemMessage(Component.literal("hurt by "));
//            player.sendSystemMessage(event.getSource().getDirectEntity().getName());
//            if(event.getSource().getDirectEntity() instanceof LightningBolt l){
//                player.sendSystemMessage(Component.literal("lightning"));
//            }
//        }
//    }

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
    }

    @SubscribeEvent
    public static void onPotionExpiry(MobEffectEvent.Expired PEnd) {
            if(PEnd.getEntity() instanceof Player player && PEnd.getEffectInstance().is(ModEffects.FREE_FLIGHT_EFFECT)) {
                player.getAbilities().mayfly = player.isCreative();
                player.onUpdateAbilities();
            }
    }

    @SubscribeEvent
    public static void onPotionCanceled(MobEffectEvent.Remove PEnd) {
            if(PEnd.getEntity() instanceof Player player && PEnd.getEffectInstance().is(ModEffects.FREE_FLIGHT_EFFECT)) {
                player.getAbilities().mayfly = player.isCreative();
                player.onUpdateAbilities();
            }
    }
}