package net.hatedero.warlocksmod.capability;

import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.capability.abilities.blackhole.PlayerBlackHole;
import net.hatedero.warlocksmod.capability.abilities.blink.PlayerBlink;
import net.hatedero.warlocksmod.capability.abilities.dash.PlayerDash;
import net.hatedero.warlocksmod.capability.abilities.doublejump.PlayerDoubleJump;
//import net.hatedero.warlocksmod.capability.abilities.infinity.PlayerInfinity;
import net.hatedero.warlocksmod.capability.abilities.infinity.PlayerInfinity;
import net.hatedero.warlocksmod.capability.abilities.thundersnap.PlayerThunderSnap;
import net.hatedero.warlocksmod.capability.abilities.thundersnap.PlayerThunderSnapManager;
import net.hatedero.warlocksmod.capability.classes.dawnblade.PlayerDawnBlade;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachment {
    public static final     DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES;
    public static final Supplier<AttachmentType<PlayerDash>> PLAYER_DASH;
    public static final Supplier<AttachmentType<PlayerDoubleJump>> PLAYER_DOUBLE_JUMP;
    public static final Supplier<AttachmentType<PlayerThunderSnap>> PLAYER_THUNDER_SNAP;
    public static final Supplier<AttachmentType<PlayerBlackHole>> PLAYER_BLACK_HOLE;
    public static final Supplier<AttachmentType<PlayerInfinity>> PLAYER_INFINITY;
    public static final Supplier<AttachmentType<PlayerBlink>> PLAYER_BLINK;

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
        PLAYER_THUNDER_SNAP = ATTACHMENT_TYPES.register("player_thunder_snap", () -> {
            return AttachmentType.serializable(PlayerThunderSnap::new).copyOnDeath().build();
        });
        PLAYER_BLINK = ATTACHMENT_TYPES.register("player_blink", () -> {
            return AttachmentType.serializable(PlayerBlink::new).copyOnDeath().build();
        });
        PLAYER_BLACK_HOLE = ATTACHMENT_TYPES.register("player_black_hole", () -> {
            return AttachmentType.serializable(PlayerBlackHole::new).copyOnDeath().build();
        });
        PLAYER_INFINITY = ATTACHMENT_TYPES.register("player_infinity", () -> {
            return AttachmentType.serializable(PlayerInfinity::new).copyOnDeath().build();
        });
    }

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
