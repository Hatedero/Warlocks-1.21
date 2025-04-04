package net.hatedero.warlocksmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORIES_WARLOCKS = "key.categories.warlocksmod.warlocksmod";
    public static final String KEY_DOUBLE_JUMP = "key.warlocksmod.doublejump";
    public static final String KEY_INFINITY = "key.warlocksmod.infinity";
    public static final String KEY_GRAVITY = "key.warlocksmod.gravity";
    public static final String KEY_RESET_ABILITIES = "key.warlocksmod.resetabilities";
    public static final String KEY_SUPER= "key.warlocksmod.super";
    public static final String KEY_MELEE_ABILITY = "key.warlocksmod.melee_ability";
    public static final String KEY_MOVEMENT_ABILITY= "key.warlocksmod.movement_ability";

    public static KeyMapping DOUBLE_JUMP_KEY = new KeyMapping(KEY_DOUBLE_JUMP, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_SPACE, KEY_CATEGORIES_WARLOCKS);



    public static KeyMapping INFINITY_KEY = new KeyMapping(KEY_INFINITY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, KEY_CATEGORIES_WARLOCKS);

    public static KeyMapping GRAVITY_KEY = new KeyMapping(KEY_GRAVITY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, KEY_CATEGORIES_WARLOCKS);

    public static KeyMapping RESET_ABILITIES_KEY = new KeyMapping(KEY_RESET_ABILITIES, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORIES_WARLOCKS);



    public static KeyMapping SUPER_KEY = new KeyMapping(KEY_SUPER, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, KEY_CATEGORIES_WARLOCKS);

    public static KeyMapping MELEE_ABILITY_KEY = new KeyMapping(KEY_MELEE_ABILITY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_E, KEY_CATEGORIES_WARLOCKS);

    public static KeyMapping MOVEMENT_ABILITY_KEY = new KeyMapping(KEY_MOVEMENT_ABILITY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_E, KEY_CATEGORIES_WARLOCKS);

}
