package fr.fidorial.registry.keys;

import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.data.MapDecorationType;
import java.util.List;
import java.util.stream.Stream;
import net.kyori.adventure.key.KeyPattern;

/**
 * Typed keys for entries in the {@code minecraft:map_decoration_type} registry.
 */
public final class MapDecorationTypeKeys {
    /**
     * Key for {@code minecraft:abandoned_camp}.
     */
    public static final TypedKey<MapDecorationType> ABANDONED_CAMP = create("abandoned_camp");

    /**
     * Key for {@code minecraft:ancient_city}.
     */
    public static final TypedKey<MapDecorationType> ANCIENT_CITY = create("ancient_city");

    /**
     * Key for {@code minecraft:banner_black}.
     */
    public static final TypedKey<MapDecorationType> BANNER_BLACK = create("banner_black");

    /**
     * Key for {@code minecraft:banner_blue}.
     */
    public static final TypedKey<MapDecorationType> BANNER_BLUE = create("banner_blue");

    /**
     * Key for {@code minecraft:banner_brown}.
     */
    public static final TypedKey<MapDecorationType> BANNER_BROWN = create("banner_brown");

    /**
     * Key for {@code minecraft:banner_cyan}.
     */
    public static final TypedKey<MapDecorationType> BANNER_CYAN = create("banner_cyan");

    /**
     * Key for {@code minecraft:banner_gray}.
     */
    public static final TypedKey<MapDecorationType> BANNER_GRAY = create("banner_gray");

    /**
     * Key for {@code minecraft:banner_green}.
     */
    public static final TypedKey<MapDecorationType> BANNER_GREEN = create("banner_green");

    /**
     * Key for {@code minecraft:banner_light_blue}.
     */
    public static final TypedKey<MapDecorationType> BANNER_LIGHT_BLUE = create("banner_light_blue");

    /**
     * Key for {@code minecraft:banner_light_gray}.
     */
    public static final TypedKey<MapDecorationType> BANNER_LIGHT_GRAY = create("banner_light_gray");

    /**
     * Key for {@code minecraft:banner_lime}.
     */
    public static final TypedKey<MapDecorationType> BANNER_LIME = create("banner_lime");

    /**
     * Key for {@code minecraft:banner_magenta}.
     */
    public static final TypedKey<MapDecorationType> BANNER_MAGENTA = create("banner_magenta");

    /**
     * Key for {@code minecraft:banner_orange}.
     */
    public static final TypedKey<MapDecorationType> BANNER_ORANGE = create("banner_orange");

    /**
     * Key for {@code minecraft:banner_pink}.
     */
    public static final TypedKey<MapDecorationType> BANNER_PINK = create("banner_pink");

    /**
     * Key for {@code minecraft:banner_purple}.
     */
    public static final TypedKey<MapDecorationType> BANNER_PURPLE = create("banner_purple");

    /**
     * Key for {@code minecraft:banner_red}.
     */
    public static final TypedKey<MapDecorationType> BANNER_RED = create("banner_red");

    /**
     * Key for {@code minecraft:banner_white}.
     */
    public static final TypedKey<MapDecorationType> BANNER_WHITE = create("banner_white");

    /**
     * Key for {@code minecraft:banner_yellow}.
     */
    public static final TypedKey<MapDecorationType> BANNER_YELLOW = create("banner_yellow");

    /**
     * Key for {@code minecraft:blue_marker}.
     */
    public static final TypedKey<MapDecorationType> BLUE_MARKER = create("blue_marker");

    /**
     * Key for {@code minecraft:desert_pyramid}.
     */
    public static final TypedKey<MapDecorationType> DESERT_PYRAMID = create("desert_pyramid");

    /**
     * Key for {@code minecraft:frame}.
     */
    public static final TypedKey<MapDecorationType> FRAME = create("frame");

    /**
     * Key for {@code minecraft:jungle_temple}.
     */
    public static final TypedKey<MapDecorationType> JUNGLE_TEMPLE = create("jungle_temple");

    /**
     * Key for {@code minecraft:mansion}.
     */
    public static final TypedKey<MapDecorationType> MANSION = create("mansion");

    /**
     * Key for {@code minecraft:mineshaft}.
     */
    public static final TypedKey<MapDecorationType> MINESHAFT = create("mineshaft");

    /**
     * Key for {@code minecraft:monument}.
     */
    public static final TypedKey<MapDecorationType> MONUMENT = create("monument");

    /**
     * Key for {@code minecraft:ocean_ruin_warm}.
     */
    public static final TypedKey<MapDecorationType> OCEAN_RUIN_WARM = create("ocean_ruin_warm");

    /**
     * Key for {@code minecraft:player}.
     */
    public static final TypedKey<MapDecorationType> PLAYER = create("player");

    /**
     * Key for {@code minecraft:player_off_limits}.
     */
    public static final TypedKey<MapDecorationType> PLAYER_OFF_LIMITS = create("player_off_limits");

    /**
     * Key for {@code minecraft:player_off_map}.
     */
    public static final TypedKey<MapDecorationType> PLAYER_OFF_MAP = create("player_off_map");

    /**
     * Key for {@code minecraft:red_marker}.
     */
    public static final TypedKey<MapDecorationType> RED_MARKER = create("red_marker");

    /**
     * Key for {@code minecraft:red_x}.
     */
    public static final TypedKey<MapDecorationType> RED_X = create("red_x");

    /**
     * Key for {@code minecraft:swamp_hut}.
     */
    public static final TypedKey<MapDecorationType> SWAMP_HUT = create("swamp_hut");

    /**
     * Key for {@code minecraft:target_point}.
     */
    public static final TypedKey<MapDecorationType> TARGET_POINT = create("target_point");

    /**
     * Key for {@code minecraft:target_x}.
     */
    public static final TypedKey<MapDecorationType> TARGET_X = create("target_x");

    /**
     * Key for {@code minecraft:trial_chambers}.
     */
    public static final TypedKey<MapDecorationType> TRIAL_CHAMBERS = create("trial_chambers");

    /**
     * Key for {@code minecraft:village_desert}.
     */
    public static final TypedKey<MapDecorationType> VILLAGE_DESERT = create("village_desert");

    /**
     * Key for {@code minecraft:village_plains}.
     */
    public static final TypedKey<MapDecorationType> VILLAGE_PLAINS = create("village_plains");

    /**
     * Key for {@code minecraft:village_savanna}.
     */
    public static final TypedKey<MapDecorationType> VILLAGE_SAVANNA = create("village_savanna");

    /**
     * Key for {@code minecraft:village_snowy}.
     */
    public static final TypedKey<MapDecorationType> VILLAGE_SNOWY = create("village_snowy");

    /**
     * Key for {@code minecraft:village_taiga}.
     */
    public static final TypedKey<MapDecorationType> VILLAGE_TAIGA = create("village_taiga");

    private static final List<TypedKey<MapDecorationType>> VALUES = List.of(
        ABANDONED_CAMP,
        ANCIENT_CITY,
        BANNER_BLACK,
        BANNER_BLUE,
        BANNER_BROWN,
        BANNER_CYAN,
        BANNER_GRAY,
        BANNER_GREEN,
        BANNER_LIGHT_BLUE,
        BANNER_LIGHT_GRAY,
        BANNER_LIME,
        BANNER_MAGENTA,
        BANNER_ORANGE,
        BANNER_PINK,
        BANNER_PURPLE,
        BANNER_RED,
        BANNER_WHITE,
        BANNER_YELLOW,
        BLUE_MARKER,
        DESERT_PYRAMID,
        FRAME,
        JUNGLE_TEMPLE,
        MANSION,
        MINESHAFT,
        MONUMENT,
        OCEAN_RUIN_WARM,
        PLAYER,
        PLAYER_OFF_LIMITS,
        PLAYER_OFF_MAP,
        RED_MARKER,
        RED_X,
        SWAMP_HUT,
        TARGET_POINT,
        TARGET_X,
        TRIAL_CHAMBERS,
        VILLAGE_DESERT,
        VILLAGE_PLAINS,
        VILLAGE_SAVANNA,
        VILLAGE_SNOWY,
        VILLAGE_TAIGA
    );

    private MapDecorationTypeKeys() {
        throw new UnsupportedOperationException("MapDecorationTypeKeys cannot be instantiated.");
    }

    private static TypedKey<MapDecorationType> create(@KeyPattern final String value) {
        return TypedKey.create(RegistryKey.MAP_DECORATION_TYPE, value);
    }

    /**
     * Returns a stream containing all keys declared by this class.
     *
     * @return a stream of registry keys
     */
    public static Stream<TypedKey<MapDecorationType>> values() {
        return VALUES.stream();
    }
}
