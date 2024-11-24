package net.hatedero.warlocksmod.event;

import net.hatedero.warlocksmod.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

import static net.hatedero.warlocksmod.WarlocksMod.MOD_ID;

public class ClientEvents {
    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientNeoForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            if(KeyBinding.DASH_KEY.consumeClick()){
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pressed a key"));
            }
        }
    }

}
