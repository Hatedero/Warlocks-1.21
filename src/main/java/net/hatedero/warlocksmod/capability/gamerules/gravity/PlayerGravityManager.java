package net.hatedero.warlocksmod.capability.gamerules.gravity;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.dash.PlayerDash;
import net.hatedero.warlocksmod.effect.ModEffects;
import net.hatedero.warlocksmod.network.message.PlayerDashSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerGravitySyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_DASH;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_GRAVITY;
import static net.hatedero.warlocksmod.util.KeyBinding.DASH_KEY;
import static net.hatedero.warlocksmod.util.KeyBinding.GRAVITY_KEY;

@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerGravityManager {
    public PlayerGravityManager(){}

    @SubscribeEvent
    public static void onCast(InputEvent.Key event){
        if (Minecraft.getInstance().player instanceof Player player && GRAVITY_KEY.getKey().getValue() == event.getKey()) {
            if(!player.hasContainerOpen() && !Minecraft.getInstance().gui.getChat().isChatFocused()) {
                //player.getData(PLAYER_GRAVITY).setActive(!player.getData(PLAYER_GRAVITY).getActive());
                player.getData(PLAYER_GRAVITY).setActive(false);
                PacketDistributor.sendToServer(new PlayerGravitySyncMessage(player.getData(PLAYER_GRAVITY).getIntensity(), player.getData(PLAYER_GRAVITY).getActive() ? 1 : 0));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        if(player instanceof ServerPlayer serverPlayer){
            ((PlayerGravity)serverPlayer.getData(ModAttachment.PLAYER_GRAVITY)).tick(serverPlayer);
        }
    }
}
