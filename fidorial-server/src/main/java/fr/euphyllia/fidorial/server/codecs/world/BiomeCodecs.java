package fr.euphyllia.fidorial.server.codecs.world;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.euphyllia.fidorial.server.codecs.CommonCodecs;
import fr.fidorial.world.biome.BiomeDefinition;
import fr.fidorial.world.biome.BiomeEffects;
import fr.fidorial.world.biome.GrassColorModifier;
import fr.fidorial.world.biome.TemperatureModifier;
import fr.fidorial.world.environment.EnvironmentAttributes;
import io.papermc.adventurex.nbt.dfu.BinaryTagOps;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public class BiomeCodecs {

    public static final Codec<TemperatureModifier> TEMPERATURE_MODIFIER =
            byId(TemperatureModifier.values(), TemperatureModifier::id, "temperature modifier");

    public static final Codec<GrassColorModifier> GRASS_COLOR_MODIFIER =
            byId(GrassColorModifier.values(), GrassColorModifier::id, "grass color modifier");

    public static final Codec<Key> PARTICLE_OPTIONS = RecordCodecBuilder.create(instance -> instance.group(
            CommonCodecs.KEY_CODEC.fieldOf("type").forGetter(type -> type)
    ).apply(instance, type -> type));

    public static final Codec<BiomeEffects> EFFECTS = RecordCodecBuilder.create(instance -> instance.group(
            CommonCodecs.RGB_COLOR.fieldOf("water_color").forGetter(BiomeEffects::waterColor),
            CommonCodecs.RGB_COLOR.optionalFieldOf("foliage_color")
                    .forGetter(effects -> Optional.ofNullable(effects.foliageColor())),
            CommonCodecs.RGB_COLOR.optionalFieldOf("grass_color")
                    .forGetter(effects -> Optional.ofNullable(effects.grassColor())),
            CommonCodecs.RGB_COLOR.optionalFieldOf("dry_foliage_color")
                    .forGetter(effects -> Optional.ofNullable(effects.dryFoliageColor())),
            GRASS_COLOR_MODIFIER.optionalFieldOf("grass_color_modifier", GrassColorModifier.NONE)
                    .forGetter(BiomeEffects::grassColorModifier)
    ).apply(instance, (water, foliage, grass, dryFoliage, grassModifier) ->
            new BiomeEffects(
                    water,
                    foliage.orElse(null),
                    grass.orElse(null),
                    dryFoliage.orElse(null),
                    grassModifier)));

    private BiomeCodecs() {
        throw new UnsupportedOperationException("BiomeCodecs cannot be instantiated.");
    }

    public static Codec<BiomeDefinition> codec(final Key key) {
        return RecordCodecBuilder.create(instance -> instance.group(
                Codec.BOOL.fieldOf("has_precipitation").forGetter(BiomeDefinition::hasPrecipitation),
                Codec.FLOAT.fieldOf("temperature").forGetter(BiomeDefinition::temperature),
                TEMPERATURE_MODIFIER.optionalFieldOf("temperature_modifier", TemperatureModifier.NONE)
                        .forGetter(BiomeDefinition::temperatureModifier),
                Codec.FLOAT.fieldOf("downfall").forGetter(BiomeDefinition::downfall),
                EFFECTS.fieldOf("effects").forGetter(BiomeDefinition::effects),
                EnvironmentAttributeCodecs.ATTRIBUTES
                        .optionalFieldOf("attributes", EnvironmentAttributes.EMPTY)
                        .forGetter(BiomeDefinition::attributes)
        ).apply(instance, (precipitation, temperature, modifier, downfall, effects, attributes) ->
                new BiomeDefinition(key, precipitation, temperature, modifier, downfall, effects, attributes)));
    }

    public static CompoundBinaryTag encodeNbt(final BiomeDefinition biome) {
        final BinaryTag tag = codec(biome.key())
                .encodeStart(BinaryTagOps.binaryTagOps(), biome)
                .getOrThrow(message -> new IllegalStateException(
                        "Failed to encode biome " + biome.key().asString() + ": " + message));

        if (!(tag instanceof final CompoundBinaryTag compound)) {
            throw new IllegalStateException("Biome " + biome.key().asString() + " did not encode to a compound tag");
        }

        return compound;
    }

    public static BiomeDefinition fromJson(final Key key, final String json) {
        final JsonElement element;
        try {
            element = JsonParser.parseString(json);
        } catch (final RuntimeException exception) {
            throw new IllegalArgumentException("Biome " + key.asString() + " is not valid JSON", exception);
        }

        return codec(key)
                .parse(JsonOps.INSTANCE, element)
                .getOrThrow(message -> new IllegalArgumentException(
                        "Failed to read biome " + key.asString() + ": " + message));
    }

    private static <E extends Enum<E>> Codec<E> byId(
            final E[] values,
            final Function<E, String> id,
            final String what
    ) {
        return Codec.STRING.comapFlatMap(
                name -> Arrays.stream(values)
                        .filter(value -> id.apply(value).equals(name))
                        .findFirst()
                        .map(DataResult::success)
                        .orElseGet(() -> DataResult.error(() -> "Unknown " + what + ": " + name)),
                id::apply);
    }
}
