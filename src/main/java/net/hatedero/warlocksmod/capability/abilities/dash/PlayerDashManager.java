package net.hatedero.warlocksmod.capability.abilities.dash;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DASH;
import static net.hatedero.warlocksmod.util.KeyBinding.DASH_KEY;

@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerDashManager {
    public PlayerDashManager(){}

    @SubscribeEvent
    public static void onDash(InputEvent.Key event){
        if (Minecraft.getInstance().player instanceof Player player && DASH_KEY.getKey().getValue() == event.getKey() && !player.onGround() && player.getData(PLAYER_DASH).getNbDash() > 0) {

            Vec3 playerSightLigne = player.getViewVector(1).normalize().multiply(1.5, 1.5, 1.5);
            player.setDeltaMovement(playerSightLigne);
            player.getData(PLAYER_DASH).setNbDash(player.getData(PLAYER_DASH).getNbDash()-1);
        }

    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        if(player instanceof ServerPlayer serverPlayer){
            ((PlayerDash)serverPlayer.getData(ModAttachment.PLAYER_DASH)).tick(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity var2 = event.getEntity();
        if (var2 instanceof ServerPlayer player) {
            ((PlayerDash)player.getData(ModAttachment.PLAYER_DASH)).setNbDash(1);
        }

    }
}
