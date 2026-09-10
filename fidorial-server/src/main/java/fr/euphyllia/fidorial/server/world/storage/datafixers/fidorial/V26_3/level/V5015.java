package fr.euphyllia.fidorial.server.world.storage.datafixers.fidorial.V26_3.level;

import ca.spottedleaf.converter.DataConverter;
import ca.spottedleaf.converter.types.MapType;
import org.jspecify.annotations.Nullable;

/**
 * Migrates the old flat spawn/difficulty layout to the nested
 * {@code spawn} / {@code difficulty_settings} compounds.
 * <a href="https://github.com/Fidorial/Fidorial/pull/95/changes/5d01b818cb9780b222e75d615b09eb9419dafaa4">the PR commit making this change</a>
 */
public final class V5015 extends DataConverter<MapType, MapType> {

    public static final int TO_VERSION = 5015;

    public V5015() {
        super(TO_VERSION);
    }

    @Override
    @Nullable public MapType convert(final @Nullable MapType data, final long sourceVersion, final long toVersion) {
        if (data == null) {
            return null;
        }

        if (!data.hasKey("spawn") && data.hasKey("SpawnX")) {
            final MapType spawn = data.createEmptyMap();
            spawn.setString("dimension", "minecraft:overworld");
            spawn.setInts("pos", new int[] {
                    data.getInt("SpawnX", 8),
                    data.getInt("SpawnY", -48),
                    data.getInt("SpawnZ", 8)
            });
            spawn.setInt("yaw", Math.round(data.getFloat("SpawnAngle", 0f)));
            spawn.setInt("pitch", 0);
            data.setMap("spawn", spawn);

            data.remove("SpawnX");
            data.remove("SpawnY");
            data.remove("SpawnZ");
            data.remove("SpawnAngle");
        }

        if (!data.hasKey("difficulty_settings") && data.hasKey("Difficulty")) {
            final MapType difficultySettings = data.createEmptyMap();
            difficultySettings.setByte("difficulty", data.getByte("Difficulty", (byte) 2));
            difficultySettings.setBoolean("hardcore", data.getBoolean("hardcore", false));
            difficultySettings.setBoolean("locked", data.getBoolean("DifficultyLocked", false));
            data.setMap("difficulty_settings", difficultySettings);

            data.remove("Difficulty");
            data.remove("hardcore");
            data.remove("DifficultyLocked");
        }

        return data;
    }
}
