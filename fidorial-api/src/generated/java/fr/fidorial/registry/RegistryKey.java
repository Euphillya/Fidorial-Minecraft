package fr.fidorial.registry;

import fr.fidorial.entity.EntityType;
import fr.fidorial.registry.data.Attribute;
import fr.fidorial.registry.data.BannerPattern;
import fr.fidorial.registry.data.Biome;
import fr.fidorial.registry.data.BlockTransformer;
import fr.fidorial.registry.data.BlockType;
import fr.fidorial.registry.data.CatSoundVariant;
import fr.fidorial.registry.data.CatVariant;
import fr.fidorial.registry.data.ChatType;
import fr.fidorial.registry.data.ChickenSoundVariant;
import fr.fidorial.registry.data.ChickenVariant;
import fr.fidorial.registry.data.CowSoundVariant;
import fr.fidorial.registry.data.CowVariant;
import fr.fidorial.registry.data.DamageType;
import fr.fidorial.registry.data.DataComponentType;
import fr.fidorial.registry.data.DecoratedPotPattern;
import fr.fidorial.registry.data.Dialog;
import fr.fidorial.registry.data.DimensionType;
import fr.fidorial.registry.data.Enchantment;
import fr.fidorial.registry.data.FrogVariant;
import fr.fidorial.registry.data.GameEvent;
import fr.fidorial.registry.data.GameRule;
import fr.fidorial.registry.data.Instrument;
import fr.fidorial.registry.data.Item;
import fr.fidorial.registry.data.JukeboxSong;
import fr.fidorial.registry.data.MapDecorationType;
import fr.fidorial.registry.data.MenuType;
import fr.fidorial.registry.data.MobEffect;
import fr.fidorial.registry.data.PaintingVariant;
import fr.fidorial.registry.data.PigSoundVariant;
import fr.fidorial.registry.data.PigVariant;
import fr.fidorial.registry.data.SoundEvent;
import fr.fidorial.registry.data.Timeline;
import fr.fidorial.registry.data.TrimMaterial;
import fr.fidorial.registry.data.TrimPattern;
import fr.fidorial.registry.data.VillagerProfession;
import fr.fidorial.registry.data.VillagerType;
import fr.fidorial.registry.data.WolfSoundVariant;
import fr.fidorial.registry.data.WolfVariant;
import fr.fidorial.registry.data.WorldClock;
import fr.fidorial.registry.data.ZombieNautilusVariant;
import java.util.Objects;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;

/**
 * Identifies a Minecraft registry.
 *
 * @param <T> marker type for entries contained by the registry
 * @param key namespaced registry identifier
 */
public record RegistryKey<T>(Key key) {
    /**
     * Registry key for {@code minecraft:attribute}.
     */
    public static final RegistryKey<Attribute> ATTRIBUTE = of("attribute");

    /**
     * Registry key for {@code minecraft:banner_pattern}.
     */
    public static final RegistryKey<BannerPattern> BANNER_PATTERN = of("banner_pattern");

    /**
     * Registry key for {@code minecraft:worldgen/biome}.
     */
    public static final RegistryKey<Biome> BIOME = of("worldgen/biome");

    /**
     * Registry key for {@code minecraft:block_transformer}.
     */
    public static final RegistryKey<BlockTransformer> BLOCK_TRANSFORMER = of("block_transformer");

    /**
     * Registry key for {@code minecraft:block}.
     */
    public static final RegistryKey<BlockType> BLOCK = of("block");

    /**
     * Registry key for {@code minecraft:cat_sound_variant}.
     */
    public static final RegistryKey<CatSoundVariant> CAT_SOUND_VARIANT = of("cat_sound_variant");

    /**
     * Registry key for {@code minecraft:cat_variant}.
     */
    public static final RegistryKey<CatVariant> CAT_VARIANT = of("cat_variant");

    /**
     * Registry key for {@code minecraft:chat_type}.
     */
    public static final RegistryKey<ChatType> CHAT_TYPE = of("chat_type");

    /**
     * Registry key for {@code minecraft:chicken_sound_variant}.
     */
    public static final RegistryKey<ChickenSoundVariant> CHICKEN_SOUND_VARIANT = of("chicken_sound_variant");

    /**
     * Registry key for {@code minecraft:chicken_variant}.
     */
    public static final RegistryKey<ChickenVariant> CHICKEN_VARIANT = of("chicken_variant");

    /**
     * Registry key for {@code minecraft:cow_sound_variant}.
     */
    public static final RegistryKey<CowSoundVariant> COW_SOUND_VARIANT = of("cow_sound_variant");

    /**
     * Registry key for {@code minecraft:cow_variant}.
     */
    public static final RegistryKey<CowVariant> COW_VARIANT = of("cow_variant");

    /**
     * Registry key for {@code minecraft:damage_type}.
     */
    public static final RegistryKey<DamageType> DAMAGE_TYPE = of("damage_type");

    /**
     * Registry key for {@code minecraft:data_component_type}.
     */
    public static final RegistryKey<DataComponentType> DATA_COMPONENT_TYPE = of("data_component_type");

    /**
     * Registry key for {@code minecraft:dialog}.
     */
    public static final RegistryKey<Dialog> DIALOG = of("dialog");

    /**
     * Registry key for {@code minecraft:decorated_pot_pattern}.
     */
    public static final RegistryKey<DecoratedPotPattern> DECORATED_POT_PATTERN = of("decorated_pot_pattern");

    /**
     * Registry key for {@code minecraft:dimension_type}.
     */
    public static final RegistryKey<DimensionType> DIMENSION_TYPE = of("dimension_type");

    /**
     * Registry key for {@code minecraft:enchantment}.
     */
    public static final RegistryKey<Enchantment> ENCHANTMENT = of("enchantment");

    /**
     * Registry key for {@code minecraft:frog_variant}.
     */
    public static final RegistryKey<FrogVariant> FROG_VARIANT = of("frog_variant");

    /**
     * Registry key for {@code minecraft:game_event}.
     */
    public static final RegistryKey<GameEvent> GAME_EVENT = of("game_event");

    /**
     * Registry key for {@code minecraft:game_rule}.
     */
    public static final RegistryKey<GameRule> GAME_RULE = of("game_rule");

    /**
     * Registry key for {@code minecraft:instrument}.
     */
    public static final RegistryKey<Instrument> INSTRUMENT = of("instrument");

    /**
     * Registry key for {@code minecraft:item}.
     */
    public static final RegistryKey<Item> ITEM = of("item");

    /**
     * Registry key for {@code minecraft:jukebox_song}.
     */
    public static final RegistryKey<JukeboxSong> JUKEBOX_SONG = of("jukebox_song");

    /**
     * Registry key for {@code minecraft:map_decoration_type}.
     */
    public static final RegistryKey<MapDecorationType> MAP_DECORATION_TYPE = of("map_decoration_type");

    /**
     * Registry key for {@code minecraft:menu}.
     */
    public static final RegistryKey<MenuType> MENU = of("menu");

    /**
     * Registry key for {@code minecraft:mob_effect}.
     */
    public static final RegistryKey<MobEffect> MOB_EFFECT = of("mob_effect");

    /**
     * Registry key for {@code minecraft:painting_variant}.
     */
    public static final RegistryKey<PaintingVariant> PAINTING_VARIANT = of("painting_variant");

    /**
     * Registry key for {@code minecraft:pig_sound_variant}.
     */
    public static final RegistryKey<PigSoundVariant> PIG_SOUND_VARIANT = of("pig_sound_variant");

    /**
     * Registry key for {@code minecraft:pig_variant}.
     */
    public static final RegistryKey<PigVariant> PIG_VARIANT = of("pig_variant");

    /**
     * Registry key for {@code minecraft:sound_event}.
     */
    public static final RegistryKey<SoundEvent> SOUND_EVENT = of("sound_event");

    /**
     * Registry key for {@code minecraft:timeline}.
     */
    public static final RegistryKey<Timeline> TIMELINE = of("timeline");

    /**
     * Registry key for {@code minecraft:trim_material}.
     */
    public static final RegistryKey<TrimMaterial> TRIM_MATERIAL = of("trim_material");

    /**
     * Registry key for {@code minecraft:trim_pattern}.
     */
    public static final RegistryKey<TrimPattern> TRIM_PATTERN = of("trim_pattern");

    /**
     * Registry key for {@code minecraft:villager_profession}.
     */
    public static final RegistryKey<VillagerProfession> VILLAGER_PROFESSION = of("villager_profession");

    /**
     * Registry key for {@code minecraft:villager_type}.
     */
    public static final RegistryKey<VillagerType> VILLAGER_TYPE = of("villager_type");

    /**
     * Registry key for {@code minecraft:wolf_sound_variant}.
     */
    public static final RegistryKey<WolfSoundVariant> WOLF_SOUND_VARIANT = of("wolf_sound_variant");

    /**
     * Registry key for {@code minecraft:wolf_variant}.
     */
    public static final RegistryKey<WolfVariant> WOLF_VARIANT = of("wolf_variant");

    /**
     * Registry key for {@code minecraft:world_clock}.
     */
    public static final RegistryKey<WorldClock> WORLD_CLOCK = of("world_clock");

    /**
     * Registry key for {@code minecraft:zombie_nautilus_variant}.
     */
    public static final RegistryKey<ZombieNautilusVariant> ZOMBIE_NAUTILUS_VARIANT = of("zombie_nautilus_variant");

    /**
     * Registry key for {@code minecraft:entity_type}.
     */
    public static final RegistryKey<EntityType> ENTITY_TYPE = new RegistryKey<>(Key.key("entity_type"));

    public RegistryKey {
        Objects.requireNonNull(key, "key");
    }

    private static <T> RegistryKey<T> of(@KeyPattern final String path) {
        return new RegistryKey<>(Key.key(path));
    }

    public static <T> RegistryKey<T> of(final Key key) {
        return new RegistryKey<>(key);
    }

    @Override
    public String toString() {
        return "RegistryKey[" + key + "]";
    }
}
