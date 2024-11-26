package net.hatedero.warlocksmod.capability.abilities.thundersnap;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_THUNDER_SNAP;
import static net.hatedero.warlocksmod.util.KeyBinding.*;

@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerThunderSnapManager {
    public PlayerThunderSnapManager(){}

    @SubscribeEvent
    public static void onCast(InputEvent.Key event){
//        if(Minecraft.getInstance().player instanceof Player player)
//            player.sendSystemMessage(Component.literal(String.valueOf( player.getData(PLAYER_THUNDER_SNAP).getCooldown())));
        if (Minecraft.getInstance().player instanceof Player player && MELEE_ABILITY_KEY.getKey().getValue() == event.getKey() && player.getData(PLAYER_THUNDER_SNAP).getCooldown() <= player.getData(PLAYER_THUNDER_SNAP).getCooldownMin()) {

            player.sendSystemMessage(Component.literal(String.valueOf( player.getData(PLAYER_THUNDER_SNAP).getCooldown())));

            final Minecraft minecraft = Minecraft.getInstance();
            Entity camera = minecraft.getCameraEntity();
            HitResult block = camera.pick(30.0, 0.0F, true);
            Entity entity = minecraft.crosshairPickEntity;
            if (entity != null) {
                EntityType.LIGHTNING_BOLT.spawn(Minecraft.getInstance().getSingleplayerServer().overworld(), entity.getOnPos(), MobSpawnType.TRIGGERED).setDamage(100);
            }
            else {
                BlockPos temp = BlockPos.containing(block.getLocation());
                EntityType.LIGHTNING_BOLT.spawn(Minecraft.getInstance().getSingleplayerServer().overworld(), temp, MobSpawnType.TRIGGERED).setDamage(100);
            }
            player.getData(PLAYER_THUNDER_SNAP).setCooldown(player.getData(PLAYER_THUNDER_SNAP).getCooldownMax());
            //player.sendSystemMessage(Component.literal(String.valueOf( player.getData(PLAYER_THUNDER_SNAP).getCooldown())));
        }

    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        if(player instanceof ServerPlayer serverPlayer){
            ((PlayerThunderSnap)serverPlayer.getData(PLAYER_THUNDER_SNAP)).tick(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity var2 = event.getEntity();
        if (var2 instanceof ServerPlayer player) {
            ((PlayerThunderSnap)player.getData(ModAttachment.PLAYER_THUNDER_SNAP)).setCooldown(player.getData(PLAYER_THUNDER_SNAP).getCooldownMin());
        }

    }
}
