package net.hatedero.warlocksmod.dataattachments;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.dataattachments.doublejump.DoubleJump;
import net.hatedero.warlocksmod.dataattachments.doublejump.DoubleJumpProvider;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class DataAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES;
    public static final Supplier<AttachmentType<DoubleJump>> PLAYER_ATTACHMENTS;

    public DataAttachments() {}

    public static <T> Supplier<AttachmentType<T>> register(String name, Supplier<T> def, IAttachmentSerializer<Tag, T> provider) {
        return ATTACHMENT_TYPES.register(name, () -> {
            return AttachmentType.builder(def).serialize(provider).build();
        });
//        ATTACHMENT_TYPES.register(eventBus);
    }

    public static void register(IEventBus eventBus) {
//        return ATTACHMENT_TYPES.register(name, () -> {
//            return AttachmentType.builder(def).serialize(provider).build();
//        });
        ATTACHMENT_TYPES.register(eventBus);
    }

    static {
        ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, WarlocksMod.MOD_ID);
        PLAYER_ATTACHMENTS = register("player_double_jump", DoubleJump::new, new DoubleJumpProvider());
    }
}
