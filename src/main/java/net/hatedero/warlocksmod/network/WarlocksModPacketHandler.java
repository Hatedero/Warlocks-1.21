package net.hatedero.warlocksmod.network;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.network.message.PlayerDashSyncMessage;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(
        modid = WarlocksMod.MOD_ID,
        bus = EventBusSubscriber.Bus.MOD
)
public class WarlocksModPacketHandler {
    private static final String PROTOCOL_VERSION = "0.1.3";
    public WarlocksModPacketHandler(){}

    @SubscribeEvent
    public static void register (RegisterPayloadHandlersEvent event){
        PayloadRegistrar registrar = event.registrar("0.1.3");
        //registrar.playBidirectional(DrinkByHandMessage.TYPE, DrinkByHandMessage.STREAM_CODEC, new DirectionalPayloadHandler(DrinkByHandMessage::clientHandle, DrinkByHandMessage::serverHandle));
        registrar.playBidirectional(PlayerDashSyncMessage.TYPE, PlayerDashSyncMessage.STREAM_CODEC, new DirectionalPayloadHandler(PlayerDashSyncMessage::clientHandle, PlayerDashSyncMessage::serverHandle));
    }
}
