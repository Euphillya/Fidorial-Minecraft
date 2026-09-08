package fr.fidorial.registrygen.model;

import java.util.List;

/**
 * Represents a collection of supported registries used within a Minecraft-related
 * registry system. Each registry is defined through a {@link RegistryTypeDefinition}.
 * <p>
 * This class provides a predefined list of registry types that are supported by
 * the system. Each registry is described using:
 * - An identifier: The unique string identifier for the registry.
 * - A type name: The descriptive name of the registry type.
 * - A keys class name: A derived name based on the type name, used for key management.
 * - A {@link RegistrySync}: whether the registry is baked into the client, synced
 * during the configuration phase, or never sent at all.
 * <p>
 * This list is the single source of truth for what gets generated: {@code ALL} drives
 * both the generated {@code *Keys} classes and the runtime registry dataset. Nothing
 * in Mojang's {@code registries.json} tells a datapack registry apart from a
 * client-side one, which is why {@link RegistrySync} is declared by hand here.
 *
 * @since 0.1.0
 */
public final class SupportedRegistries {

    public static final RegistryTypeDefinition ARGUMENT_TYPE = registry("minecraft:command_argument_type", "ArgumentType");

    public static final RegistryTypeDefinition BLOCK = registry("minecraft:block", "BlockType");

    public static final RegistryTypeDefinition DIMENSION_TYPE = registry("minecraft:dimension_type", "DimensionType", RegistrySync.DYNAMIC);

    public static final RegistryTypeDefinition ITEM = registry("minecraft:item", "Item");

    public static final List<RegistryTypeDefinition> ALL = List.of(
            registry("minecraft:attribute", "Attribute"),
            registry("minecraft:banner_pattern", "BannerPattern", RegistrySync.DYNAMIC),
            registry("minecraft:worldgen/biome", "Biome", RegistrySync.DYNAMIC),
            BLOCK,
            registry("minecraft:cat_sound_variant", "CatSoundVariant", RegistrySync.DYNAMIC),
            registry("minecraft:cat_variant", "CatVariant", RegistrySync.DYNAMIC),
            registry("minecraft:chat_type", "ChatType", RegistrySync.DYNAMIC),
            registry("minecraft:chicken_sound_variant", "ChickenSoundVariant", RegistrySync.DYNAMIC),
            registry("minecraft:chicken_variant", "ChickenVariant", RegistrySync.DYNAMIC),
            registry("minecraft:cow_sound_variant", "CowSoundVariant", RegistrySync.DYNAMIC),
            registry("minecraft:cow_variant", "CowVariant", RegistrySync.DYNAMIC),
            registry("minecraft:damage_type", "DamageType", RegistrySync.DYNAMIC),
            registry("minecraft:data_component_type", "DataComponentType"),
            registry("minecraft:dialog", "Dialog", RegistrySync.DYNAMIC),
            DIMENSION_TYPE,
            registry("minecraft:enchantment", "Enchantment", RegistrySync.DYNAMIC),
            registry("minecraft:frog_variant", "FrogVariant", RegistrySync.DYNAMIC),
            registry("minecraft:game_event", "GameEvent"),
            registry("minecraft:game_rule", "GameRule"),
            registry("minecraft:instrument", "Instrument", RegistrySync.DYNAMIC),
            ITEM,
            registry("minecraft:jukebox_song", "JukeboxSong", RegistrySync.DYNAMIC),
            registry("minecraft:map_decoration_type", "MapDecorationType"),
            registry("minecraft:menu", "MenuType"),
            registry("minecraft:mob_effect", "MobEffect"),
            registry("minecraft:painting_variant", "PaintingVariant", RegistrySync.DYNAMIC),
            registry("minecraft:pig_sound_variant", "PigSoundVariant", RegistrySync.DYNAMIC),
            registry("minecraft:pig_variant", "PigVariant", RegistrySync.DYNAMIC),
            registry("minecraft:sound_event", "SoundEvent"),
            registry("minecraft:timeline", "Timeline", RegistrySync.DYNAMIC),
            registry("minecraft:trim_material", "TrimMaterial", RegistrySync.DYNAMIC),
            registry("minecraft:trim_pattern", "TrimPattern", RegistrySync.DYNAMIC),
            registry("minecraft:villager_profession", "VillagerProfession"),
            registry("minecraft:villager_type", "VillagerType"),
            registry("minecraft:wolf_sound_variant", "WolfSoundVariant", RegistrySync.DYNAMIC),
            registry("minecraft:wolf_variant", "WolfVariant", RegistrySync.DYNAMIC),
            registry("minecraft:world_clock", "WorldClock", RegistrySync.DYNAMIC),
            registry("minecraft:zombie_nautilus_variant", "ZombieNautilusVariant", RegistrySync.DYNAMIC));

    private SupportedRegistries() {
    }

    private static RegistryTypeDefinition registry(final String identifier, final String qualifiedTypeName) {
        return RegistryTypeDefinition.parse(identifier, qualifiedTypeName, RegistrySync.NONE);
    }

    private static RegistryTypeDefinition registry(final String identifier, final String qualifiedTypeName, final RegistrySync sync) {
        return RegistryTypeDefinition.parse(identifier, qualifiedTypeName, sync);
    }
}
