package net.hatedero.warlocksmod;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = WarlocksMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    private static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    private static final ModConfigSpec.IntValue NB_BLINK_MAX = BUILDER
            .comment("Max Blink number")
            .defineInRange("magicNumber", 1, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    // a list of strings that are treated as resource locations for items
    private static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);


    //THUNDER SNAP CONFIG
    private static final ModConfigSpec.IntValue MAX_TS_C = BUILDER
            .comment("Max Thunder Strike Cooldown")
            .defineInRange("maxTSC", 20, 1, Integer.MAX_VALUE);
    private static final ModConfigSpec.IntValue DEF_TS_S = BUILDER
            .comment("Default Thunder Strike Strength")
            .defineInRange("defTSS", 1000, 0, Integer.MAX_VALUE);

    //INFINITY CONFIG
    private static final ModConfigSpec.IntValue DEF_I_R = BUILDER
            .comment("Default Infinity Range")
            .defineInRange("defIR", 3, 0, Integer.MAX_VALUE);
    private static final ModConfigSpec.IntValue MAX_I_C = BUILDER
            .comment("Max Infinity Cooldown")
            .defineInRange("defTSS", 200, 1, Integer.MAX_VALUE);
    private static final ModConfigSpec.IntValue MAX_I_A = BUILDER
            .comment("Max Infinity Active")
            .defineInRange("maxIA", 200, 0, Integer.MAX_VALUE);

    //DOUBLE JUMP CONFIG
    private static final ModConfigSpec.IntValue MAX_DJ_C = BUILDER
            .comment("Max Double Jump Cooldown")
            .defineInRange("maxDJC", 10, 1, Integer.MAX_VALUE);
    private static final ModConfigSpec.IntValue MAX_DJ_N = BUILDER
            .comment("Max Double Jump Number")
            .defineInRange("maxDJN", 2, 0, Integer.MAX_VALUE);

    //DASH CONFIG
    private static final ModConfigSpec.IntValue MAX_D_C = BUILDER
            .comment("Max Dash Cooldown")
            .defineInRange("maxDC", 10, 1, Integer.MAX_VALUE);
    private static final ModConfigSpec.IntValue MAX_D_N = BUILDER
            .comment("Max Dash Number")
            .defineInRange("maxDN", 2, 0, Integer.MAX_VALUE);

    //BLINK CONFIG
    private static final ModConfigSpec.IntValue MAX_B_C = BUILDER
            .comment("Max Blink Cooldown")
            .defineInRange("maxBC", 40, 1, Integer.MAX_VALUE);
    private static final ModConfigSpec.IntValue MAX_B_N = BUILDER
            .comment("Max Blink Number")
            .defineInRange("maxBN", 1, 0, Integer.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean logDirtBlock;
    public static int magicNumber;
    public static int nbBlinkMax;
    public static String magicNumberIntroduction;
    public static Set<Item> items;

    //THUNDER SNAP CONFIG
    public static int maxTSC;
    public static int defTSS;


    //INFINITY CONFIG
    public static int defIR;
    public static int maxIC;
    public static int maxIA;


    //DOUBLE JUMP CONFIG
    public static int maxDJC;
    public static int maxDJN;


    //DASH CONFIG
    public static int maxDC;
    public static int maxDN;


    //BLINK CONFIG
    public static int maxBC;
    public static int maxBN;


    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        logDirtBlock = LOG_DIRT_BLOCK.get();
        magicNumber = MAGIC_NUMBER.get();
        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();
        nbBlinkMax = NB_BLINK_MAX.get();

        // convert the list of strings into a set of items
        items = ITEM_STRINGS.get().stream()
                .map(itemName -> BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemName)))
                .collect(Collectors.toSet());

        //THUNDER SNAP CONFIG
        maxTSC = MAX_TS_C.get();
        defTSS = DEF_TS_S.get();

        //INFINITY CONFIG
        defIR = DEF_I_R.get();
        maxIC = MAX_I_C.get();
        maxIA = MAX_I_A.get();

        //DOUBLE JUMP CONFIG
        maxDJC = MAX_DJ_C.get();
        maxDJN = MAX_DJ_N.get();

        //DASH CONFIG
        maxDC = MAX_D_C.get();
        maxDN = MAX_D_N.get();

        //BLINK CONFIG
        maxBC = MAX_B_C.get();
        maxBN = MAX_B_N.get();
    }
}
