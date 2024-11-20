package net.hatedero.warlocksmod.event;


import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.effect.ModEffects;
import net.hatedero.warlocksmod.item.ModItems;
import net.hatedero.warlocksmod.item.custom.HammerItem;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.item.ItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.joml.Vector3d;

import java.util.HashSet;
import java.util.Set;

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
    public static void onRightClickWithDawnbreaker(PlayerInteractEvent.RightClickItem rc){
        if (rc.getEntity() instanceof Player player && player.getMainHandItem().is(ModItems.DAWNBREAKER)) {
            player.addEffect(new MobEffectInstance(ModEffects.FREE_FLIGHT_EFFECT, 200, 0, false, false));
            //Vector3d vector = player.getLookAngle().normalize().multiply(1,1,1);

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

    private static float getFrictionInfluencedSpeed(float friction, LivingEntity entity) {
        return entity.onGround() ? entity.getSpeed() * (0.21600002F / (friction * friction * friction)) : getFlyingSpeed(entity);
    }

    protected static float getFlyingSpeed(LivingEntity entity) {
        return entity.getControllingPassenger() instanceof Player ? entity.getSpeed() * 0.1F : 0.02F;
    }

}