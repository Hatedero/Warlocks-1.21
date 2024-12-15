package net.hatedero.warlocksmod.capability.abilities.parry;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.doublejump.PlayerDoubleJump;
import net.hatedero.warlocksmod.effect.ModEffects;
import net.hatedero.warlocksmod.item.custom.DawnbreakerItem;
import net.hatedero.warlocksmod.network.message.PlayerDoubleJumpSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerInfinitySyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerParrySyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static net.hatedero.warlocksmod.capability.ModAttachment.*;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_INFINITY;
import static net.hatedero.warlocksmod.util.KeyBinding.DOUBLE_JUMP_KEY;
import static net.hatedero.warlocksmod.util.KeyBinding.RESET_ABILITIES_KEY;

@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerParryManager {
    public PlayerParryManager(){}

    @SubscribeEvent
    public static void onCast(PlayerInteractEvent.RightClickItem event){
        if (Minecraft.getInstance().player instanceof LocalPlayer player && event.getItemStack().getItem() instanceof DawnbreakerItem item && player.getData(PLAYER_PARRY).getCooldown() <= 0) {
            if(!player.hasContainerOpen() && !Minecraft.getInstance().gui.getChat().isChatFocused()) {
                player.getData(PLAYER_PARRY).setActive(1);
                player.getData(PLAYER_PARRY).setCooldown(player.getData(PLAYER_PARRY).getCooldownMax());
                player.sendSystemMessage(Component.literal("parry activated"));
                PacketDistributor.sendToServer(new PlayerParrySyncMessage(player.getData(PLAYER_PARRY).getActive(), player.getData(PLAYER_PARRY).getActiveMax(), player.getData(PLAYER_PARRY).getCooldown(), player.getData(PLAYER_PARRY).getCooldownMax()));
            }
        }
    }

    @SubscribeEvent
    public static void onDamageParried(LivingIncomingDamageEvent event){
        if(event.getEntity() instanceof Player player && player.getData(PLAYER_PARRY).getActive() > 0){
            event.setCanceled(true);
            Level level = Minecraft.getInstance().getSingleplayerServer().overworld();
            level.playSound(player, player.getOnPos(), SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.PLAYERS, 1f, 1f);
            player.sendSystemMessage(Component.literal("parried"));
            if(event.getSource().getEntity() instanceof LivingEntity agr){
                agr.addEffect(new MobEffectInstance(ModEffects.STUNNED_EFFECT, 20, 0, false, true));
            }
            player.getData(PLAYER_PARRY).setActive(0);
            player.getData(PLAYER_PARRY).setCooldown(0);
            PacketDistributor.sendToServer(new PlayerParrySyncMessage(player.getData(PLAYER_PARRY).getActive(), player.getData(PLAYER_PARRY).getActiveMax(), player.getData(PLAYER_PARRY).getCooldown(), player.getData(PLAYER_PARRY).getCooldownMax()));
        }
    }

    @SubscribeEvent
    public static void onResetKeyHit(InputEvent.Key event){
        if (Minecraft.getInstance().player instanceof Player player && RESET_ABILITIES_KEY.getKey().getValue() == event.getKey()) {
            if(!player.hasContainerOpen() && !Minecraft.getInstance().gui.getChat().isChatFocused()) {
                player.getData(PLAYER_DOUBLE_JUMP).resetData(player);
                PacketDistributor.sendToServer(new PlayerParrySyncMessage(player.getData(PLAYER_PARRY).getActive(), player.getData(PLAYER_PARRY).getActiveMax(), player.getData(PLAYER_PARRY).getCooldown(), player.getData(PLAYER_PARRY).getCooldownMax()));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        if(player instanceof ServerPlayer serverPlayer){
            ((PlayerParry)serverPlayer.getData(ModAttachment.PLAYER_PARRY)).tick(serverPlayer);
        }
    }
}
