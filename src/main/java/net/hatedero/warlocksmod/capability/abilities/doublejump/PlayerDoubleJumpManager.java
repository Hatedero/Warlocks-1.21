package net.hatedero.warlocksmod.capability.abilities.doublejump;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.thundersnap.PlayerThunderSnap;
import net.hatedero.warlocksmod.network.message.PlayerDoubleJumpSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerThunderSnapSyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static net.hatedero.warlocksmod.capability.ModAttachment.*;
import static net.hatedero.warlocksmod.util.KeyBinding.DOUBLE_JUMP_KEY;
import static net.hatedero.warlocksmod.util.KeyBinding.RESET_ABILITIES_KEY;

@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerDoubleJumpManager {
    public PlayerDoubleJumpManager(){}

    @SubscribeEvent
    public static void onDoubleJump(InputEvent.Key event){
        if (Minecraft.getInstance().player instanceof Player player && DOUBLE_JUMP_KEY.getKey().getValue() == event.getKey() && !player.onGround()  && player.getData(PLAYER_DOUBLE_JUMP).getCooldown() <= player.getData(PLAYER_DOUBLE_JUMP).getCooldownMin() && player.getData(PLAYER_DOUBLE_JUMP).getNbDoubleJump() > player.getData(PLAYER_DOUBLE_JUMP).getNbDoubleJumpMin() && player.fallDistance >= 0.1) {
            if(!player.hasContainerOpen() && !Minecraft.getInstance().gui.getChat().isChatFocused()) {
            player.setDeltaMovement(player.getDeltaMovement().x, 0, player.getDeltaMovement().z);
            player.setDeltaMovement(player.getDeltaMovement().add(0,0.5,0));
            player.getData(PLAYER_DOUBLE_JUMP).setNbDoubleJump(player.getData(PLAYER_DOUBLE_JUMP).getNbDoubleJump()-1);
            player.getData(PLAYER_DOUBLE_JUMP).setCooldown(player.getData(PLAYER_DOUBLE_JUMP).getCooldownMax());
            PacketDistributor.sendToServer(new PlayerDoubleJumpSyncMessage( player.getData(PLAYER_DOUBLE_JUMP).getCooldown(),  player.getData(PLAYER_DOUBLE_JUMP).getNbDoubleJump()));
            player.resetFallDistance();
            }
        }
        if (Minecraft.getInstance().player instanceof Player player && RESET_ABILITIES_KEY.getKey().getValue() == event.getKey()) {
            if(!player.hasContainerOpen() && !Minecraft.getInstance().gui.getChat().isChatFocused()) {
                player.getData(PLAYER_DOUBLE_JUMP).resetData(player);
                PacketDistributor.sendToServer(new PlayerDoubleJumpSyncMessage( player.getData(PLAYER_DOUBLE_JUMP).getCooldown(),  player.getData(PLAYER_DOUBLE_JUMP).getNbDoubleJump()));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        if(player instanceof ServerPlayer serverPlayer){
            ((PlayerDoubleJump)serverPlayer.getData(ModAttachment.PLAYER_DOUBLE_JUMP)).tick(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity var2 = event.getEntity();
        if (var2 instanceof ServerPlayer player) {
            ((PlayerDoubleJump)player.getData(ModAttachment.PLAYER_DOUBLE_JUMP)).setNbDoubleJump(player.getData(PLAYER_DOUBLE_JUMP).getNbDoubleJumpMax());
            ((PlayerDoubleJump)player.getData(ModAttachment.PLAYER_DOUBLE_JUMP)).setCooldown(player.getData(PLAYER_DOUBLE_JUMP).getCooldownMin());
        }
    }
}
