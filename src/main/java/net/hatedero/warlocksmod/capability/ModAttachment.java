package net.hatedero.warlocksmod.capability;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.dash.PlayerDash;
import net.hatedero.warlocksmod.doublejump.PlayerDoubleJump;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachment {
    public static final     DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES;
    public static final Supplier<AttachmentType<PlayerDash>> PLAYER_DASH;
    public static final Supplier<AttachmentType<PlayerDoubleJump>> PLAYER_DOUBLE_JUMP;

    public ModAttachment() {
    }

    static {
        ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, WarlocksMod.MOD_ID);
        PLAYER_DASH = ATTACHMENT_TYPES.register("player_dash", () -> {
            return AttachmentType.serializable(PlayerDash::new).copyOnDeath().build();
        });
        PLAYER_DOUBLE_JUMP = ATTACHMENT_TYPES.register("player_double_jump", () -> {
            return AttachmentType.serializable(PlayerDoubleJump::new).copyOnDeath().build();
        });
    }
}
