package net.hatedero.warlocksmod.capability.classes.dawnblade;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.dash.PlayerDash;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.neoforge.client.event.InputEvent;
//import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
//import net.neoforged.neoforge.event.tick.PlayerTickEvent;
//
//import static net.hatedero.warlocksmod.util.KeyBinding.MELEE_ABILITY_KEY;
//
//@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
//public class PlayerDawnBladeManager {
//
//
//    @SubscribeEvent
//    public void onCast(InputEvent.Key event) {
//        if (Minecraft.getInstance().player instanceof Player player && MELEE_ABILITY_KEY.getKey().getValue() == event.getKey()){
//            player.sendSystemMessage(Component.literal("DawnBlade"));
//        }
//    }
//
//    @SubscribeEvent
//    public void onPlayerTick(PlayerTickEvent.Pre event) {
//        Player player = event.getEntity();
//        if(player instanceof ServerPlayer serverPlayer){
//            ((PlayerDawnBlade)serverPlayer.getData(ModAttachment.PLAYER_DAWN_BLADE)).tick(serverPlayer);
//        }
//    }
//
//    @SubscribeEvent
//    public void onPlayerDeath(LivingDeathEvent event) {
//
//    }
//}
