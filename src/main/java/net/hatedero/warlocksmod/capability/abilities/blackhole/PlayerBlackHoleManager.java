package net.hatedero.warlocksmod.capability.abilities.blackhole;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.thundersnap.PlayerThunderSnap;
import net.hatedero.warlocksmod.entity.ModEntities;
import net.hatedero.warlocksmod.entity.custom.BlackHoleEntity;
import net.hatedero.warlocksmod.network.message.PlayerBlackHoleSyncMessage;
import net.hatedero.warlocksmod.network.message.PlayerThunderSnapSyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_BLACK_HOLE;
import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_THUNDER_SNAP;
import static net.hatedero.warlocksmod.util.KeyBinding.BLACK_HOLE_KEY;
import static net.hatedero.warlocksmod.util.KeyBinding.MELEE_ABILITY_KEY;

@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerBlackHoleManager {
    public PlayerBlackHoleManager(){}

    @SubscribeEvent
    public static void onCast(InputEvent.Key event){
        if (Minecraft.getInstance().player instanceof Player player && BLACK_HOLE_KEY.getKey().getValue() == event.getKey() && player.getData(PLAYER_BLACK_HOLE).getCooldown() <= player.getData(PLAYER_BLACK_HOLE).getCooldownMin()) {
            if(!player.hasContainerOpen() && !Minecraft.getInstance().gui.getChat().isChatFocused()) {
                //player.sendSystemMessage(Component.literal("spawn a black hole"));
                final Minecraft minecraft = Minecraft.getInstance();
                Entity camera = minecraft.getCameraEntity();
                HitResult block = camera.pick(5.0, 0.0F, true);

                Level level = Minecraft.getInstance().getSingleplayerServer().overworld();

                AABB minMax = new AABB(player.getX()-10, player.getY()-10, player.getZ()-10, player.getX()+10, player.getY()+10, player.getZ()+10);
                List<Entity> ent = level.getEntities(player, minMax);
                for (Entity entko : ent) {
                    if(entko != player) {
                        entko.moveTo(player.getOnPos().above(3).getCenter());
                        player.sendSystemMessage(entko.getName());
                    }
                }

//                //if (!level.isClientSide) {
//                BlackHoleEntity blue = new BlackHoleEntity(ModEntities.BLACK_HOLE.get(), level, player.getData(PLAYER_BLACK_HOLE).getLifeMax());
//                level.addFreshEntity(blue);
//                blue.moveTo(player.getOnPos().getCenter().add(0, 2, 0));
//                blue.addDeltaMovement(player.getViewVector(1).normalize().multiply(1.5, 1.5, 1.5));

                    //player.getX(), player.getY(), player.getZ(), player.getViewVector(1).normalize().multiply(1.5, 1.5, 1.5),
                //level.playSound((Player)null, blue.getX(), blue.getY(), blue.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                    //level.gameEvent(player, GameEvent.PRIME_FUSE, player.getOnPos());
//                //}

            player.getData(PLAYER_BLACK_HOLE).setCooldown(player.getData(PLAYER_BLACK_HOLE).getCooldownMax());
            PacketDistributor.sendToServer(new PlayerBlackHoleSyncMessage( player.getData(PLAYER_BLACK_HOLE).getCooldown(),  player.getData(PLAYER_BLACK_HOLE).getStrength(),  player.getData(PLAYER_BLACK_HOLE).getLife()));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        if(player instanceof ServerPlayer serverPlayer){
            ((PlayerBlackHole)serverPlayer.getData(PLAYER_BLACK_HOLE)).tick(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity var2 = event.getEntity();
        if (var2 instanceof ServerPlayer player) {
            ((PlayerBlackHole)player.getData(ModAttachment.PLAYER_BLACK_HOLE)).setCooldown(player.getData(PLAYER_BLACK_HOLE).getCooldownMin());
            //PacketDistributor.sendToServer(new PlayerThunderSnapSyncMessage( player.getData(PLAYER_THUNDER_SNAP).getCooldown(),  player.getData(PLAYER_THUNDER_SNAP).getStrength()));
        }
    }
}
