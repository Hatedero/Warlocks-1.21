package net.hatedero.warlocksmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.IKeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_WARLOCKS = "key.category.warlocksmod.warlocksmod";
    public static final String KEY_DASH = "key.warlocksmod.dash";
    public static final String KEY_DOUBLE_JUMP = "key.warlocksmod.doublejump";
    public static KeyMapping DASH_KEY = new KeyMapping(KEY_DASH, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_SHIFT, KEY_CATEGORY_WARLOCKS);

    public static KeyMapping DOUBLE_JUMP_KEY = new KeyMapping(KEY_DOUBLE_JUMP, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_SPACE, KEY_CATEGORY_WARLOCKS);

}
