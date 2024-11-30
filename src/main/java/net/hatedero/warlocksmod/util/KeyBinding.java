package net.hatedero.warlocksmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORIES_WARLOCKS = "key.categories.warlocksmod.warlocksmod";
    public static final String KEY_DASH = "key.warlocksmod.dash";
    public static final String KEY_BLINK= "key.warlocksmod.blink";
    public static final String KEY_DOUBLE_JUMP = "key.warlocksmod.doublejump";
    public static final String KEY_MELEE_ABILITY = "key.warlocksmod.meleeability";
    public static final String KEY_BLACK_HOLE = "key.warlocksmod.blackhole";
    public static final String KEY_INFINITY = "key.warlocksmod.infinity";
    public static final String KEY_GRAVITY = "key.warlocksmod.gravity";
    public static KeyMapping DASH_KEY = new KeyMapping(KEY_DASH, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_SHIFT, KEY_CATEGORIES_WARLOCKS);

    public static KeyMapping DOUBLE_JUMP_KEY = new KeyMapping(KEY_DOUBLE_JUMP, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_SPACE, KEY_CATEGORIES_WARLOCKS);

    public static KeyMapping MELEE_ABILITY_KEY = new KeyMapping(KEY_MELEE_ABILITY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_E, KEY_CATEGORIES_WARLOCKS);

    public static KeyMapping BLINK_KEY = new KeyMapping(KEY_BLINK, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_SHIFT, KEY_CATEGORIES_WARLOCKS);

    public static KeyMapping BLACK_HOLE_KEY = new KeyMapping(KEY_BLACK_HOLE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KEY_CATEGORIES_WARLOCKS);

    public static KeyMapping INFINITY_KEY = new KeyMapping(KEY_INFINITY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, KEY_CATEGORIES_WARLOCKS);

    public static KeyMapping GRAVITY_KEY = new KeyMapping(KEY_GRAVITY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, KEY_CATEGORIES_WARLOCKS);

}
