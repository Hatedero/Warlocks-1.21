package net.hatedero.warlocksmod.event;


import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.dash.PlayerDash;
import net.hatedero.warlocksmod.effect.ModEffects;
import net.hatedero.warlocksmod.item.ModItems;
import net.hatedero.warlocksmod.item.custom.HammerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashSet;
import java.util.Set;

//import static net.hatedero.warlocksmod.dash.PlayerDashProvider.PLAYER_COOLDOWN;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DASH;
import static net.hatedero.warlocksmod.util.KeyBinding.DASH_KEY;
import static net.hatedero.warlocksmod.util.KeyBinding.DOUBLE_JUMP_KEY;

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
//    public static void onDash(InputEvent.Key event){
//        if (Minecraft.getInstance().player instanceof Player player && DASH_KEY.getKey().getValue() == event.getKey() && !player.onGround() && player.getData(PLAYER_DASH).getNbDash() > 0) {
//            //
//
//            Vec3 playerSightLigne = player.getViewVector(1).normalize().multiply(1.5, 1.5, 1.5);
//            player.setDeltaMovement(playerSightLigne);
//            player.getData(PLAYER_DASH).setNbDash(player.getData(PLAYER_DASH).getNbDash()-1);
//            //player.sendSystemMessage(Component.literal(String.valueOf(player.getData(PLAYER_DASH).getNbDash() )));
//            //player.getActiveEffects().remove(ModEffects.DASH_EFFECT);
//        }
//    }

//    @SubscribeEvent
//    public static void onPlayerTick(PlayerTickEvent.Pre event){
//        Player player = event.getEntity();
//        if(player instanceof ServerPlayer serverPlayer){
//            ((PlayerDash)serverPlayer.getData(ModAttachment.PLAYER_DASH)).tick(serverPlayer);
//        }
//    }

//    @SubscribeEvent
//    public static void onDoubleJump(InputEvent.Key event){
//        if (Minecraft.getInstance().player instanceof Player player && DOUBLE_JUMP_KEY.getKey().getValue() == event.getKey() && !player.onGround() && player.hasEffect(ModEffects.DOUBLE_JUMP_EFFECT) && player.fallDistance >= 0.1) {
//            //
//
//            player.setDeltaMovement(player.getDeltaMovement().x, 0, player.getDeltaMovement().z);
//            player.setDeltaMovement(player.getDeltaMovement().add(0,0.5,0));
//            player.getActiveEffects().remove(ModEffects.DOUBLE_JUMP_EFFECT);
//        }
//
//    }

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