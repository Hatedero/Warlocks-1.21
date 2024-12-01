package net.hatedero.warlocksmod.capability.abilities.infinity;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.thundersnap.PlayerThunderSnap;
import net.hatedero.warlocksmod.network.message.PlayerInfinitySyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerThunderSnapSyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_INFINITY;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_THUNDER_SNAP;
import static net.hatedero.warlocksmod.util.KeyBinding.INFINITY_KEY;

@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerInfinityManager {
    public PlayerInfinityManager(){}

    @SubscribeEvent
    public static void onCast(InputEvent.Key event){
        if (Minecraft.getInstance().player instanceof Player player && INFINITY_KEY.getKey().getValue() == event.getKey() && player.getData(PLAYER_INFINITY).getCooldown() <= player.getData(PLAYER_INFINITY).getCooldownMin()) {
            if(!player.hasContainerOpen() && !Minecraft.getInstance().gui.getChat().isChatFocused()) {
                player.getData(PLAYER_INFINITY).setActive(!player.getData(PLAYER_INFINITY).getActive());
                player.getData(PLAYER_INFINITY).setCooldown(player.getData(PLAYER_INFINITY).getCooldownMax());
                PacketDistributor.sendToServer(new PlayerInfinitySyncMessage(player.getData(PLAYER_INFINITY).getCooldown(), player.getData(PLAYER_INFINITY).getRange(), player.getData(PLAYER_INFINITY).getActiveTime(), player.getData(PLAYER_INFINITY).getActive() ? 1:0));
                if(player.getData(PLAYER_INFINITY).getActive() == true){
                    player.sendSystemMessage(Component.literal("on"));
                } else {
                    player.sendSystemMessage(Component.literal("off"));
                }

            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        if(player instanceof ServerPlayer serverPlayer){
            ((PlayerInfinity)serverPlayer.getData(PLAYER_INFINITY)).tick(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity var2 = event.getEntity();
        if (var2 instanceof ServerPlayer player) {
            ((PlayerInfinity)player.getData(ModAttachment.PLAYER_INFINITY)).setCooldown(player.getData(PLAYER_INFINITY).getCooldownMin());
            //PacketDistributor.sendToServer(new PlayerThunderSnapSyncMessage( player.getData(PLAYER_THUNDER_SNAP).getCooldown(),  player.getData(PLAYER_THUNDER_SNAP).getStrength()));
        }
    }

    @SubscribeEvent
    public static void onPlayerAboutToBeDamaged(LivingDamageEvent.Pre event){
        if(event.getEntity() instanceof Player player && player.getData(PLAYER_INFINITY).getActive()) {
            event.setNewDamage(0);
        }
    }
}
