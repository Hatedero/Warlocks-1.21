package net.hatedero.warlocksmod.capability.abilities.dash;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.doublejump.PlayerDoubleJump;
import net.hatedero.warlocksmod.effect.ModEffects;
import net.hatedero.warlocksmod.network.message.PlayerDashSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerDoubleJumpSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerThunderSnapSyncMessage;
import net.hatedero.warlocksmod.valueCalc;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Optional;

import static net.hatedero.warlocksmod.capability.ModAttachment.*;
import static net.hatedero.warlocksmod.util.KeyBinding.*;

@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerDashManager {
    public PlayerDashManager(){}

    @SubscribeEvent
    public static void onDash(InputEvent.Key event){
        if (Minecraft.getInstance().player instanceof Player player && MOVEMENT_ABILITY_KEY.getKey().getValue() == event.getKey() && player.getData(PLAYER_CLASS).getClassName().matches("sun_singer") && player.getData(PLAYER_DASH).getCooldown() <= player.getData(PLAYER_DASH).getCooldownMin() && player.getData(PLAYER_DASH).getNbDash() > player.getData(PLAYER_DASH).getNbDashMin()) {
            if(!player.hasContainerOpen() && !Minecraft.getInstance().gui.getChat().isChatFocused()) {
                boolean groundedLaunch = player.onGround();
                player.sendSystemMessage(Component.literal(String.valueOf(player.getDeltaMovement().horizontalDistance())));
                float factor = 1.4F + (((float) player.getDeltaMovement().horizontalDistance()));
                if (!groundedLaunch)
                    factor += 0.1F;
                if (player.hasEffect(ModEffects.PHOENIX_EFFECT))
                    factor += 0.2F;

                Vec3 playerSightLigne = player.getViewVector(1).normalize().multiply(factor, factor, factor);
                playerSightLigne = playerSightLigne.add(player.getDeltaMovement());
                player.setDeltaMovement(playerSightLigne);

                player.sendSystemMessage(Component.literal(String.valueOf(player.getDeltaMovement().horizontalDistance())));
                player.getData(PLAYER_DASH).setNbDash(player.getData(PLAYER_DASH).getNbDash() - 1);
                player.getData(PLAYER_DASH).setCooldown(player.getData(PLAYER_DASH).getCooldownMax());
                PacketDistributor.sendToServer(new PlayerDashSyncMessage(player.getData(PLAYER_DASH).getCooldown(), player.getData(PLAYER_DASH).getNbDash()));
                player.resetFallDistance();
            }
        }
        if (Minecraft.getInstance().player instanceof Player player && RESET_ABILITIES_KEY.getKey().getValue() == event.getKey()) {
            if(!player.hasContainerOpen() && !Minecraft.getInstance().gui.getChat().isChatFocused()) {
                player.getData(PLAYER_DASH).resetData(player);
                PacketDistributor.sendToServer(new PlayerDashSyncMessage(player.getData(PLAYER_DASH).getCooldown(), player.getData(PLAYER_DASH).getNbDash()));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        if(player instanceof ServerPlayer serverPlayer){
            ((PlayerDash)serverPlayer.getData(PLAYER_DASH)).tick(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity var2 = event.getEntity();
        if (var2 instanceof ServerPlayer player) {
            ((PlayerDash)player.getData(PLAYER_DASH)).setNbDash(player.getData(PLAYER_DASH).getNbDashMax());
            ((PlayerDash)player.getData(PLAYER_DASH)).setCooldown(player.getData(PLAYER_DASH).getCooldownMin());
        }
    }
}
