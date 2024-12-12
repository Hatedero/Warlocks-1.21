package net.hatedero.warlocksmod.capability.player;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.doublejump.PlayerDoubleJump;
import net.hatedero.warlocksmod.network.message.PlayerDoubleJumpSyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_CLASS;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DOUBLE_JUMP;
import static net.hatedero.warlocksmod.util.KeyBinding.DOUBLE_JUMP_KEY;
import static net.hatedero.warlocksmod.util.KeyBinding.RESET_ABILITIES_KEY;

@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerClassManager {
    public PlayerClassManager(){}

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        if(player instanceof ServerPlayer serverPlayer){
            ((PlayerClass)serverPlayer.getData(PLAYER_CLASS)).tick(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onJoin(ClientPlayerNetworkEvent.LoggingIn event) {
        //event.getPlayer().getData(PLAYER_CLASS).updateData(event.getPlayer());
        //event.getPlayer().sendSystemMessage(Component.literal(event.getPlayer().getData(PLAYER_CLASS).getClassName()));
    }
}
