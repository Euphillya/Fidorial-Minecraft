package fr.euphyllia.fidorial.server.registry;

import fr.euphyllia.fidorial.server.registry.biome.FidorialBiomeRegistry;
import fr.euphyllia.fidorial.server.registry.data.FrozenRegistries;
import fr.euphyllia.fidorial.server.registry.dialog.FidorialDialogRegistry;
import fr.euphyllia.fidorial.server.registry.dimension.FidorialDimensionTypeRegistry;
import fr.euphyllia.fidorial.server.registry.entity.EntityTypeRegistry;
import fr.fidorial.registry.Registry;
import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.SimpleRegistry;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.data.Attribute;
import fr.fidorial.registry.data.BannerPattern;
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
import fr.fidorial.registry.keys.AttributeKeys;
import fr.fidorial.registry.keys.BannerPatternKeys;
import fr.fidorial.registry.keys.BlockTransformerKeys;
import fr.fidorial.registry.keys.BlockTypeKeys;
import fr.fidorial.registry.keys.CatSoundVariantKeys;
import fr.fidorial.registry.keys.CatVariantKeys;
import fr.fidorial.registry.keys.ChatTypeKeys;
import fr.fidorial.registry.keys.ChickenSoundVariantKeys;
import fr.fidorial.registry.keys.ChickenVariantKeys;
import fr.fidorial.registry.keys.CowSoundVariantKeys;
import fr.fidorial.registry.keys.CowVariantKeys;
import fr.fidorial.registry.keys.DamageTypeKeys;
import fr.fidorial.registry.keys.DataComponentTypeKeys;
import fr.fidorial.registry.keys.DecoratedPotPatternKeys;
import fr.fidorial.registry.keys.DialogKeys;
import fr.fidorial.registry.keys.EnchantmentKeys;
import fr.fidorial.registry.keys.FrogVariantKeys;
import fr.fidorial.registry.keys.GameEventKeys;
import fr.fidorial.registry.keys.GameRuleKeys;
import fr.fidorial.registry.keys.InstrumentKeys;
import fr.fidorial.registry.keys.ItemKeys;
import fr.fidorial.registry.keys.JukeboxSongKeys;
import fr.fidorial.registry.keys.MapDecorationTypeKeys;
import fr.fidorial.registry.keys.MenuTypeKeys;
import fr.fidorial.registry.keys.MobEffectKeys;
import fr.fidorial.registry.keys.PaintingVariantKeys;
import fr.fidorial.registry.keys.PigSoundVariantKeys;
import fr.fidorial.registry.keys.PigVariantKeys;
import fr.fidorial.registry.keys.SoundEventKeys;
import fr.fidorial.registry.keys.TimelineKeys;
import fr.fidorial.registry.keys.TrimMaterialKeys;
import fr.fidorial.registry.keys.TrimPatternKeys;
import fr.fidorial.registry.keys.VillagerProfessionKeys;
import fr.fidorial.registry.keys.VillagerTypeKeys;
import fr.fidorial.registry.keys.WolfSoundVariantKeys;
import fr.fidorial.registry.keys.WolfVariantKeys;
import fr.fidorial.registry.keys.WorldClockKeys;
import fr.fidorial.registry.keys.ZombieNautilusVariantKeys;
import net.kyori.adventure.key.Key;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class Registries {

    private static final Key FALLBACK_BIOME = Key.key("plains");

    private final RegistryHolder dynamic;
    private final RegistryHolder frozen;
    private final RegistryHolder network;
    private final Map<RegistryKey<?>, Registry<?>> typedRegistries;
    private final FidorialBiomeRegistry biomes;
    private final FidorialDialogRegistry dialogs;
    private final FidorialDimensionTypeRegistry dimensionTypes;

    private Registries(
            final RegistryHolder dynamic,
            final RegistryHolder frozen,
            final Map<RegistryKey<?>, Registry<?>> typedRegistries,
            final FidorialBiomeRegistry biomes,
            final FidorialDialogRegistry dialogs,
            final FidorialDimensionTypeRegistry dimensionTypes
    ) {
        this.dynamic = dynamic;
        this.frozen = frozen;
        this.network = RegistryHolder.merged(dynamic, frozen);
        this.typedRegistries = Map.copyOf(typedRegistries);
        this.biomes = biomes;
        this.dialogs = dialogs;
        this.dimensionTypes = dimensionTypes;
    }

    public static Registries load() {
        final RegistryDataLoader data = RegistryDataLoader.load();
        final Map<RegistryKey<?>, Registry<?>> registries = new LinkedHashMap<>();
        final RegistryHolder dynamic = RegistryHolder.of(data.dynamic());
        final FidorialBiomeRegistry biomes = FidorialBiomeRegistry.bootstrap(dynamic, FALLBACK_BIOME);
        final FidorialDialogRegistry dialogs = FidorialDialogRegistry.bootstrap(dynamic);
        final FidorialDimensionTypeRegistry dimensionTypes = FidorialDimensionTypeRegistry.bootstrap(dynamic);

        // bootstrap our API registries
        registries.put(RegistryKey.ATTRIBUTE, simple(RegistryKey.ATTRIBUTE, Attribute.class, AttributeKeys.values()));
        registries.put(RegistryKey.BANNER_PATTERN, simple(RegistryKey.BANNER_PATTERN, BannerPattern.class, BannerPatternKeys.values()));
        registries.put(RegistryKey.BIOME, biomes);
        registries.put(RegistryKey.BLOCK, simple(RegistryKey.BLOCK, BlockType.class, BlockTypeKeys.values()));
        registries.put(RegistryKey.BLOCK_TRANSFORMER, simple(RegistryKey.BLOCK_TRANSFORMER, BlockTransformer.class, BlockTransformerKeys.values()));
        registries.put(RegistryKey.CAT_SOUND_VARIANT, simple(RegistryKey.CAT_SOUND_VARIANT, CatSoundVariant.class, CatSoundVariantKeys.values()));
        registries.put(RegistryKey.CAT_VARIANT, simple(RegistryKey.CAT_VARIANT, CatVariant.class, CatVariantKeys.values()));
        registries.put(RegistryKey.CHAT_TYPE, simple(RegistryKey.CHAT_TYPE, ChatType.class, ChatTypeKeys.values()));
        registries.put(RegistryKey.CHICKEN_SOUND_VARIANT, simple(RegistryKey.CHICKEN_SOUND_VARIANT, ChickenSoundVariant.class, ChickenSoundVariantKeys.values()));
        registries.put(RegistryKey.CHICKEN_VARIANT, simple(RegistryKey.CHICKEN_VARIANT, ChickenVariant.class, ChickenVariantKeys.values()));
        registries.put(RegistryKey.COW_SOUND_VARIANT, simple(RegistryKey.COW_SOUND_VARIANT, CowSoundVariant.class, CowSoundVariantKeys.values()));
        registries.put(RegistryKey.COW_VARIANT, simple(RegistryKey.COW_VARIANT, CowVariant.class, CowVariantKeys.values()));
        registries.put(RegistryKey.DAMAGE_TYPE, simple(RegistryKey.DAMAGE_TYPE, DamageType.class, DamageTypeKeys.values()));
        registries.put(RegistryKey.DATA_COMPONENT_TYPE, simple(RegistryKey.DATA_COMPONENT_TYPE, DataComponentType.class, DataComponentTypeKeys.values()));
        registries.put(RegistryKey.DECORATED_POT_PATTERN, simple(RegistryKey.DECORATED_POT_PATTERN, DecoratedPotPattern.class, DecoratedPotPatternKeys.values()));
        registries.put(RegistryKey.DIALOG, simple(RegistryKey.DIALOG, Dialog.class, DialogKeys.values()));
        registries.put(RegistryKey.ENCHANTMENT, simple(RegistryKey.ENCHANTMENT, Enchantment.class, EnchantmentKeys.values()));
        registries.put(RegistryKey.FROG_VARIANT, simple(RegistryKey.FROG_VARIANT, FrogVariant.class, FrogVariantKeys.values()));
        registries.put(RegistryKey.GAME_EVENT, simple(RegistryKey.GAME_EVENT, GameEvent.class, GameEventKeys.values()));
        registries.put(RegistryKey.GAME_RULE, simple(RegistryKey.GAME_RULE, GameRule.class, GameRuleKeys.values()));
        registries.put(RegistryKey.INSTRUMENT, simple(RegistryKey.INSTRUMENT, Instrument.class, InstrumentKeys.values()));
        registries.put(RegistryKey.ITEM, simple(RegistryKey.ITEM, Item.class, ItemKeys.values()));
        registries.put(RegistryKey.JUKEBOX_SONG, simple(RegistryKey.JUKEBOX_SONG, JukeboxSong.class, JukeboxSongKeys.values()));
        registries.put(RegistryKey.MAP_DECORATION_TYPE, simple(RegistryKey.MAP_DECORATION_TYPE, MapDecorationType.class, MapDecorationTypeKeys.values()));
        registries.put(RegistryKey.MENU, simple(RegistryKey.MENU, MenuType.class, MenuTypeKeys.values()));
        registries.put(RegistryKey.MOB_EFFECT, simple(RegistryKey.MOB_EFFECT, MobEffect.class, MobEffectKeys.values()));
        registries.put(RegistryKey.PAINTING_VARIANT, simple(RegistryKey.PAINTING_VARIANT, PaintingVariant.class, PaintingVariantKeys.values()));
        registries.put(RegistryKey.PIG_SOUND_VARIANT, simple(RegistryKey.PIG_SOUND_VARIANT, PigSoundVariant.class, PigSoundVariantKeys.values()));
        registries.put(RegistryKey.PIG_VARIANT, simple(RegistryKey.PIG_VARIANT, PigVariant.class, PigVariantKeys.values()));
        registries.put(RegistryKey.SOUND_EVENT, simple(RegistryKey.SOUND_EVENT, SoundEvent.class, SoundEventKeys.values()));
        registries.put(RegistryKey.TIMELINE, simple(RegistryKey.TIMELINE, Timeline.class, TimelineKeys.values()));
        registries.put(RegistryKey.TRIM_MATERIAL, simple(RegistryKey.TRIM_MATERIAL, TrimMaterial.class, TrimMaterialKeys.values()));
        registries.put(RegistryKey.TRIM_PATTERN, simple(RegistryKey.TRIM_PATTERN, TrimPattern.class, TrimPatternKeys.values()));
        registries.put(RegistryKey.VILLAGER_PROFESSION, simple(RegistryKey.VILLAGER_PROFESSION, VillagerProfession.class, VillagerProfessionKeys.values()));
        registries.put(RegistryKey.VILLAGER_TYPE, simple(RegistryKey.VILLAGER_TYPE, VillagerType.class, VillagerTypeKeys.values()));
        registries.put(RegistryKey.WOLF_SOUND_VARIANT, simple(RegistryKey.WOLF_SOUND_VARIANT, WolfSoundVariant.class, WolfSoundVariantKeys.values()));
        registries.put(RegistryKey.WOLF_VARIANT, simple(RegistryKey.WOLF_VARIANT, WolfVariant.class, WolfVariantKeys.values()));
        registries.put(RegistryKey.WORLD_CLOCK, simple(RegistryKey.WORLD_CLOCK, WorldClock.class, WorldClockKeys.values()));
        registries.put(RegistryKey.ZOMBIE_NAUTILUS_VARIANT, simple(RegistryKey.ZOMBIE_NAUTILUS_VARIANT, ZombieNautilusVariant.class, ZombieNautilusVariantKeys.values()));
        registries.put(RegistryKey.ENTITY_TYPE, new EntityTypeRegistry());

        return new Registries(dynamic, loadFrozen(), registries, biomes, dialogs, dimensionTypes);
    }

    private static RegistryHolder loadFrozen() {

        final Map<Key, fr.euphyllia.fidorial.server.registry.Registry> frozen = new LinkedHashMap<>();

        FrozenRegistries.entries().forEach((name, entries) ->
                frozen.put(name, fr.euphyllia.fidorial.server.registry.Registry.of(name, entries)));

        return RegistryHolder.of(frozen);
    }

    private static <T> SimpleRegistry<T> simple(
            final RegistryKey<T> registryKey,
            final Class<T> type,
            final Stream<TypedKey<T>> keys
    ) {
        return SimpleRegistry.of(registryKey, keys.toList(), KeyStubs.resolver(type));
    }

    public FidorialBiomeRegistry biomes() {
        return biomes;
    }

    public FidorialDialogRegistry dialogs() {
        return dialogs;
    }

    public FidorialDimensionTypeRegistry dimensionTypes() {
        return dimensionTypes;
    }

    public RegistryHolder dynamic() {
        return dynamic;
    }

    public RegistryHolder frozen() {
        return frozen;
    }

    public RegistryHolder network() {
        return network;
    }

    @SuppressWarnings("unchecked")
    public <T> Registry<T> registry(final RegistryKey<T> key) {
        final Registry<T> registry = (Registry<T>) typedRegistries.get(key);

        if (registry == null) {
            throw new IllegalArgumentException("Unknown registry: " + key);
        }

        return registry;
    }
}
