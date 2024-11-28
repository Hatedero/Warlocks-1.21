package net.hatedero.warlocksmod.network;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.network.message.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handlers.ClientPayloadHandler;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(
        modid = WarlocksMod.MOD_ID,
        bus = EventBusSubscriber.Bus.MOD
)
public class WarlocksModPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public WarlocksModPacketHandler(){}

    @SubscribeEvent
    public static void register (RegisterPayloadHandlersEvent event){
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playBidirectional(
                PlayerThunderSnapSyncMessage.TYPE,
                PlayerThunderSnapSyncMessage.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        PlayerThunderSnapSyncMessage.ClientPayloadHandler::handleDataOnMain,
                        PlayerThunderSnapSyncMessage.ServerPayloadHandler::handleDataOnMain
                )
        );
        registrar.playBidirectional(
                PlayerDoubleJumpSyncMessage.TYPE,
                PlayerDoubleJumpSyncMessage.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        PlayerDoubleJumpSyncMessage.ClientPayloadHandler::handleDataOnMain,
                        PlayerDoubleJumpSyncMessage.ServerPayloadHandler::handleDataOnMain
                )
        );
        registrar.playBidirectional(
                PlayerDashSyncMessage.TYPE,
                PlayerDashSyncMessage.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        PlayerDashSyncMessage.ClientPayloadHandler::handleDataOnMain,
                        PlayerDashSyncMessage.ServerPayloadHandler::handleDataOnMain
                )
        );
        registrar.playBidirectional(
                PlayerBlinkSyncMessage.TYPE,
                PlayerBlinkSyncMessage.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        PlayerBlinkSyncMessage.ClientPayloadHandler::handleDataOnMain,
                        PlayerBlinkSyncMessage.ServerPayloadHandler::handleDataOnMain
                )
        );
        registrar.playBidirectional(
                PlayerBlackHoleSyncMessage.TYPE,
                PlayerBlackHoleSyncMessage.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        PlayerBlackHoleSyncMessage.ClientPayloadHandler::handleDataOnMain,
                        PlayerBlackHoleSyncMessage.ServerPayloadHandler::handleDataOnMain
                )
        );
    }
}
