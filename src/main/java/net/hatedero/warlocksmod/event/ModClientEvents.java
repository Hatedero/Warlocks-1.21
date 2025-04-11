package net.hatedero.warlocksmod.event;

import net.hatedero.warlocksmod.WarlocksMod;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;

@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        if((event.getPlayer().getDeltaMovement().horizontalDistance() + event.getPlayer().getDeltaMovement().y) / 2 >= 2f) {
            float fovModifier = (float) (event.getPlayer().getDeltaMovement().horizontalDistance() + event.getPlayer().getDeltaMovement().y) / 4 + 1;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float) ticksUsingItem / 20f;
            if (deltaTicks > 1f) {
                deltaTicks = 1f;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1f - deltaTicks * 0.05f;
            event.setNewFovModifier(fovModifier);
        }
        Minecraft.getInstance().getCameraEntity().setPos(0, -40, 0);
    }
}
