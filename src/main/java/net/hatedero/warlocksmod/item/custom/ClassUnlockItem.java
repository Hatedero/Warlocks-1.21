package net.hatedero.warlocksmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.hatedero.warlocksmod.capability.ModAttachment.PLAYER_CLASS;

public class ClassUnlockItem extends Item {
    String classUnlocked;
    public ClassUnlockItem(Properties properties) {
        super(properties);
    }

    public ClassUnlockItem(Properties properties, String className) {
        super(properties);
        this.classUnlocked = className;
    }

    public void setClassUnlocked(String classUnlocked) {
        this.classUnlocked = classUnlocked;
    }

    public String getClassUnlocked() {
        return this.classUnlocked;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if(player instanceof ServerPlayer sp){
            sp.getData(PLAYER_CLASS).setClassName(this.classUnlocked);
            sp.sendSystemMessage(Component.literal("Player changed class to : "));
            sp.sendSystemMessage(Component.literal(player.getData(PLAYER_CLASS).getClassName()));
            sp.getData(PLAYER_CLASS).updateData(sp);
        }
        return super.use(level, player, usedHand);
    }
}
