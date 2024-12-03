package net.hatedero.warlocksmod.capability.abilities.nova;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.thundersnap.PlayerThunderSnap;
import net.hatedero.warlocksmod.network.message.PlayerNovaSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerThunderSnapSyncMessage;
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
import net.neoforged.neoforge.network.PacketDistributor;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_NOVA;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_THUNDER_SNAP;
import static net.hatedero.warlocksmod.util.KeyBinding.*;

@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerNovaManager {
    public PlayerNovaManager(){}

    @SubscribeEvent
    public static void onCast(InputEvent.Key event){
        if (Minecraft.getInstance().player instanceof Player player && player.isLocalPlayer() && SUPER_KEY.getKey().getValue() == event.getKey() && player.getData(PLAYER_NOVA).getCooldown() <= 0) {
            if(!player.hasContainerOpen() && !Minecraft.getInstance().gui.getChat().isChatFocused()) {
                if (player.getData(PLAYER_NOVA).getCharge() < player.getData(PLAYER_NOVA).getChargeMax() && player.getData(PLAYER_NOVA).getCharge() != player.getData(PLAYER_NOVA).getChargePrev()) {
                    player.sendSystemMessage(Component.literal("Charging."));
                    player.sendSystemMessage(Component.literal(String.valueOf(player.getData(PLAYER_NOVA).getCharge())));
                    player.getData(PLAYER_NOVA).setChargePrev(player.getData(PLAYER_NOVA).getCharge());
                    player.getData(PLAYER_NOVA).setCharge(player.getData(PLAYER_NOVA).getCharge() + 1);
                    player.getData(PLAYER_NOVA).setCooldown(10);
                    PacketDistributor.sendToServer(new PlayerNovaSyncMessage(player.getData(PLAYER_NOVA).charge, player.getData(PLAYER_NOVA).chargeMax, player.getData(PLAYER_NOVA).chargeMin, player.getData(PLAYER_NOVA).cooldown, player.getData(PLAYER_NOVA).cooldownMax, player.getData(PLAYER_NOVA).power));
                } else {
                    //if()
                    player.sendSystemMessage(Component.literal("Super activated and cooldown, charge to max."));
                    player.getData(PLAYER_NOVA).setCooldown(player.getData(PLAYER_NOVA).getCooldownMax());
                    player.getData(PLAYER_NOVA).setCharge(player.getData(PLAYER_NOVA).getChargeMin());
                    //player.getData(PLAYER_NOVA).setCharge(player.getData(PLAYER_NOVA).getChargeMin() -1);
                    PacketDistributor.sendToServer(new PlayerNovaSyncMessage(player.getData(PLAYER_NOVA).charge, player.getData(PLAYER_NOVA).chargeMax, player.getData(PLAYER_NOVA).chargeMin, player.getData(PLAYER_NOVA).cooldown, player.getData(PLAYER_NOVA).cooldownMax, player.getData(PLAYER_NOVA).power));
                }
            }
        }
        if (Minecraft.getInstance().player instanceof Player player && RESET_ABILITIES_KEY.getKey().getValue() == event.getKey()) {
            if(!player.hasContainerOpen() && !Minecraft.getInstance().gui.getChat().isChatFocused()) {
                player.sendSystemMessage(Component.literal("Reset player abilities value."));
                player.getData(PLAYER_NOVA).resetData(player);
                PacketDistributor.sendToServer(new PlayerNovaSyncMessage(player.getData(PLAYER_NOVA).charge, player.getData(PLAYER_NOVA).chargeMax, player.getData(PLAYER_NOVA).chargeMin, player.getData(PLAYER_NOVA).cooldown, player.getData(PLAYER_NOVA).cooldownMax, player.getData(PLAYER_NOVA).power));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        if(player instanceof ServerPlayer serverPlayer){
            ((PlayerNova)serverPlayer.getData(PLAYER_NOVA)).tick(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity var2 = event.getEntity();
        if (var2 instanceof ServerPlayer player) {
            //((PlayerNova)player.getData(ModAttachment.PLAYER_NOVA)).setCooldown(player.getData(PLAYER_THUNDER_SNAP).getCooldownMin());
            //PacketDistributor.sendToServer(new PlayerThunderSnapSyncMessage( player.getData(PLAYER_THUNDER_SNAP).getCooldown(),  player.getData(PLAYER_THUNDER_SNAP).getStrength()));
        }
    }
}
