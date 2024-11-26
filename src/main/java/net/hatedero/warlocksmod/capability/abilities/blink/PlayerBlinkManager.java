package net.hatedero.warlocksmod.capability.abilities.blink;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.network.message.PlayerBlinkSyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_BLINK;
import static net.hatedero.warlocksmod.util.KeyBinding.BLINK_KEY;

@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerBlinkManager {
    public PlayerBlinkManager(){}

    @SubscribeEvent
    public static void onBlink(InputEvent.Key event){
        if (Minecraft.getInstance().player instanceof Player player && BLINK_KEY.getKey().getValue() == event.getKey() && player.getData(PLAYER_BLINK).getCooldown() <= player.getData(PLAYER_BLINK).getCooldownMin() && player.getData(PLAYER_BLINK).getNbBlink() > player.getData(PLAYER_BLINK).getNbBlinkMin()) {
            boolean groundedLaunch = player.onGround();
            final Minecraft minecraft = Minecraft.getInstance();
            Entity camera = minecraft.getCameraEntity();
            HitResult block = camera.pick(20.0, 0.0F, false);
            player.setPos(block.getLocation());
            if(!groundedLaunch)
                player.setDeltaMovement(player.getViewVector(1).multiply(1.1, 1.1, 1.1));
            player.getData(PLAYER_BLINK).setNbBlink(player.getData(PLAYER_BLINK).getNbBlink()-1);
            player.getData(PLAYER_BLINK).setCooldown(player.getData(PLAYER_BLINK).getCooldownMax());
            PacketDistributor.sendToServer(new PlayerBlinkSyncMessage(player.getData(PLAYER_BLINK).getCooldown(),  player.getData(PLAYER_BLINK).getNbBlink()));
            player.resetFallDistance();
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        if(player instanceof ServerPlayer serverPlayer){
            ((PlayerBlink)serverPlayer.getData(ModAttachment.PLAYER_BLINK)).tick(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity var2 = event.getEntity();
        if (var2 instanceof ServerPlayer player) {
            ((PlayerBlink)player.getData(ModAttachment.PLAYER_BLINK)).setNbBlink(player.getData(PLAYER_BLINK).getNbBlinkMax());
            ((PlayerBlink)player.getData(ModAttachment.PLAYER_BLINK)).setCooldown(player.getData(PLAYER_BLINK).getCooldownMin());
        }
    }
}
