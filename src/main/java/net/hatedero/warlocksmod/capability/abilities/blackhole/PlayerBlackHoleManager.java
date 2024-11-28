package net.hatedero.warlocksmod.capability.abilities.blackhole;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.ModAttachment;
import net.hatedero.warlocksmod.capability.abilities.thundersnap.PlayerThunderSnap;
import net.hatedero.warlocksmod.network.message.PlayerThunderSnapSyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

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
                player.sendSystemMessage(Component.literal("spawn a black hole"));
                final Minecraft minecraft = Minecraft.getInstance();
                Entity camera = minecraft.getCameraEntity();
                HitResult block = camera.pick(5.0, 0.0F, true);

//                Level level = this.level();
//
//                //if (!level.isClientSide) {
//                    PrimedTnt primedtnt = new PrimedTnt(level, (double)player.getX() + (double)0.5F, (double)player.getY(), (double)player.getZ() + (double)0.5F, player);
//                    level.addFreshEntity(primedtnt);
//                    level.playSound((Player)null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
//                    level.gameEvent(player, GameEvent.PRIME_FUSE, player.getOnPos());
//                //}

//            Entity entity = minecraft.crosshairPickEntity;
//            if (entity != null) {
//                EntityType.LIGHTNING_BOLT.spawn(Minecraft.getInstance().getSingleplayerServer().overworld(), entity.getOnPos(), MobSpawnType.TRIGGERED).setDamage(player.getData(PLAYER_THUNDER_SNAP).getStrength());
//            }
//            else {
//                BlockPos temp = BlockPos.containing(block.getLocation());
//                EntityType.LIGHTNING_BOLT.spawn(Minecraft.getInstance().getSingleplayerServer().overworld(), temp, MobSpawnType.TRIGGERED).setDamage(player.getData(PLAYER_THUNDER_SNAP).getStrength());
//            }
//
//            player.getData(PLAYER_THUNDER_SNAP).setCooldown(player.getData(PLAYER_THUNDER_SNAP).getCooldownMax());
//            PacketDistributor.sendToServer(new PlayerThunderSnapSyncMessage( player.getData(PLAYER_THUNDER_SNAP).getCooldown(),  player.getData(PLAYER_THUNDER_SNAP).getStrength()));
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
