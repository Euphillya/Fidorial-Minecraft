package fr.euphyllia.fidorial.server.world.storage;

import com.google.common.hash.Hashing;
import fr.euphyllia.fidorial.server.VersionConstants;
import fr.euphyllia.fidorial.server.world.ChunkGeneratorConfig;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.euphyllia.fidorial.server.world.entity.AnvilEntitySerializer;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.IntArrayBinaryTag;
import net.kyori.adventure.nbt.IntBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class LevelData {

    private static final String DIMENSIONS = "dimensions";
    private static final String WORLD_CLOCKS = "WorldClocks";

    private static final Path GAME_RULES_PATH = Path.of("minecraft", "game_rules.dat");
    private static final Path WORLD_GEN_SETTINGS_PATH = Path.of("minecraft", "world_gen_settings.dat");
    private static final Path CUSTOM_BOSS_EVENTS_PATH = Path.of("minecraft", "custom_boss_events.dat");
    private static final Path WEATHER_PATH = Path.of("minecraft", "weather.dat");
    private static final Path WORLD_CLOCKS_PATH = Path.of("minecraft", "world_clocks.dat");

    public final Map<Key, ChunkGeneratorConfig> generators = new LinkedHashMap<>();

    public String levelName = "Fidorial";
    public long seed = 0L;
    public long time = 0L;
    public long dayTime = 0L;

    public Key spawnDimension = Dimension.OVERWORLD.id();
    public int spawnX = 8;
    public int spawnY = -48;
    public int spawnZ = 8;
    public int spawnYaw = 0;
    public int spawnPitch = 0;

    public int gameType = 0;       // 0 = survival
    public int difficulty = 2;     // 2 = normal
    public boolean hardcore = false;
    public boolean difficultyLocked = false;
    public boolean allowCommands = true;

    public int dataVersion = VersionConstants.DATA_VERSION;
    public String versionName = VersionConstants.MINECRAFT_VERSION_NAME;
    public boolean snapshot = !VersionConstants.IS_RELEASE;
    public boolean wasModded = false;
    public final List<Integer> versionHistory = new ArrayList<>();

    public @Nullable UUID singleplayerUuid;

    public boolean raining = false;
    public int rainTime = 0;
    public boolean thundering = false;
    public int thunderTime = 0;
    public int clearWeatherTime = 0;

    public boolean doDaylightCycle = true;

    public final Map<Key, WorldTime> worldTimes = new LinkedHashMap<>();
    public final Map<Key, BossBarData> bossBars = new LinkedHashMap<>();

    public @Nullable WorldTime worldTime(final Key dimensionId) {
        final WorldTime stored = worldTimes.get(dimensionId);
        if (stored != null) {
            return stored;
        }
        if (Dimension.OVERWORLD.id().equals(dimensionId)) {
            return new WorldTime(time, dayTime, doDaylightCycle);
        }
        return null;
    }

    public void setWorldTime(final Key dimensionId, final long worldAge, final long dayTime, final boolean doDaylightCycle) {
        worldTimes.put(dimensionId, new WorldTime(worldAge, dayTime, doDaylightCycle));
        if (Dimension.OVERWORLD.id().equals(dimensionId)) {
            this.time = worldAge;
            this.dayTime = dayTime;
            this.doDaylightCycle = doDaylightCycle;
        }
    }

    public record WorldTime(long worldAge, long dayTime, boolean doDaylightCycle) {
    }

    public record BossBarData(
            Component name,
            int value,
            int max,
            BossBar.Color color,
            BossBar.Overlay overlay,
            Set<BossBar.Flag> flags,
            boolean visible,
            Set<UUID> players
    ) {
    }

    public static LevelData read(final Path dataDir, final Path levelDat) throws IOException {
        final Map.Entry<String, CompoundBinaryTag> named = BinaryTagIO.reader().readNamed(levelDat, BinaryTagIO.Compression.GZIP);
        final CompoundBinaryTag data = named.getValue().getCompound("Data");
        final LevelData l = new LevelData();

        l.dataVersion = data.getInt("DataVersion");
        l.levelName = data.getString("LevelName");
        l.time = data.getLong("Time");
        l.dayTime = data.getLong("DayTime");
        l.gameType = data.getInt("GameType");
        l.allowCommands = data.getBoolean("allowCommands");
        l.wasModded = data.getBoolean("WasModded");

        final CompoundBinaryTag version = data.getCompound("Version");
        if (version.contains("Name")) {
            l.versionName = version.getString("Name");
        }
        if (version.contains("Snapshot")) {
            l.snapshot = version.getBoolean("Snapshot");
        }
        for (final BinaryTag entry : data.getList("version_history")) {
            if (entry instanceof final IntBinaryTag tag) {
                l.versionHistory.add(tag.value());
            }
        }

        final CompoundBinaryTag difficultySettings = data.getCompound("difficulty_settings");
        l.difficulty = difficultySettings.getInt("difficulty");
        l.hardcore = difficultySettings.getBoolean("hardcore");
        l.difficultyLocked = difficultySettings.getBoolean("locked");

        if (data.contains("singleplayer_uuid")) {
            final int[] uuid = data.getIntArray("singleplayer_uuid");
            if (uuid.length == 4) {
                l.singleplayerUuid = AnvilEntitySerializer.uuidFromInts(uuid);
            }
        }

        final CompoundBinaryTag spawn = data.getCompound("spawn");
        if (spawn.contains("dimension")) {
            l.spawnDimension = Key.key(spawn.getString("dimension"));
        }
        l.spawnYaw = spawn.getInt("yaw");
        l.spawnPitch = spawn.getInt("pitch");
        final int[] spawnPos = spawn.getIntArray("pos");
        if (spawnPos.length == 3) {
            l.spawnX = spawnPos[0];
            l.spawnY = spawnPos[1];
            l.spawnZ = spawnPos[2];
        }

        l.readDimensionData(dataDir);
        return l;
    }

    private void readDimensionData(final Path dataDir) throws IOException {
        readIfPresent(dataDir.resolve(GAME_RULES_PATH), gameRules -> {
            if (gameRules.contains("doDaylightCycle")) {
                doDaylightCycle = !"false".equals(gameRules.getString("doDaylightCycle"));
            }
        });

        readIfPresent(dataDir.resolve(WEATHER_PATH), weather -> {
            raining = weather.getBoolean("raining");
            rainTime = weather.getInt("rain_time");
            thundering = weather.getBoolean("thundering");
            thunderTime = weather.getInt("thunder_time");
            clearWeatherTime = weather.getInt("clear_weather_time");
        });

        readIfPresent(dataDir.resolve(WORLD_GEN_SETTINGS_PATH), wgs -> seed = wgs.getLong("seed"));

        readIfPresent(dataDir.resolve(CUSTOM_BOSS_EVENTS_PATH), this::readCustomBossEvents);

        readIfPresent(dataDir.resolve(WORLD_CLOCKS_PATH), this::readWorldClocks);
    }

    @FunctionalInterface
    private interface DimensionDataReader {
        void accept(CompoundBinaryTag root) throws IOException;
    }

    private static void readIfPresent(final Path path, final DimensionDataReader reader) throws IOException {
        if (!Files.isRegularFile(path)) {
            return;
        }
        final CompoundBinaryTag root = BinaryTagIO.reader().readNamed(path, BinaryTagIO.Compression.GZIP).getValue();
        reader.accept(root);
    }

    private void readWorldClocks(final CompoundBinaryTag root) {
        for (final BinaryTag entry : root.getList(WORLD_CLOCKS)) {
            if (!(entry instanceof final CompoundBinaryTag clock)) continue;
            final String dimension = clock.getString("Dimension");
            if (dimension.isEmpty()) continue;
            final Key key = Key.key(dimension);
            worldTimes.put(key, new WorldTime(
                    clock.getLong("WorldAge"),
                    clock.getLong("DayTime"),
                    !clock.contains("DoDaylightCycle") || clock.getBoolean("DoDaylightCycle")));
        }
    }

    private void readCustomBossEvents(final CompoundBinaryTag events) {
        for (final String id : events.keySet()) {
            if (id.equals("DataVersion")) continue;
            if (!(events.get(id) instanceof final CompoundBinaryTag bar)) continue;

            final Set<UUID> players = new HashSet<>();
            for (final BinaryTag entry : bar.getList("Players")) {
                if (entry instanceof IntArrayBinaryTag iat) {
                    players.add(AnvilEntitySerializer.uuidFromInts(iat.value()));
                }
            }

            final Set<BossBar.Flag> flags = new HashSet<>();
            if (bar.getBoolean("DarkenScreen")) flags.add(BossBar.Flag.DARKEN_SCREEN);
            if (bar.getBoolean("PlayBossMusic")) flags.add(BossBar.Flag.PLAY_BOSS_MUSIC);
            if (bar.getBoolean("CreateWorldFog")) flags.add(BossBar.Flag.CREATE_WORLD_FOG);

            final int max = bar.contains("Max") ? bar.getInt("Max") : 100;
            final int value = bar.getInt("Value");

            bossBars.put(Key.key(id), new BossBarData(
                    GsonComponentSerializer.gson().deserialize(bar.getString("Name")),
                    value,
                    max,
                    BossBar.Color.valueOf(bar.getString("Color").toUpperCase(Locale.ROOT)),
                    BossBar.Overlay.valueOf(bar.getString("Overlay").toUpperCase(Locale.ROOT)),
                    flags,
                    bar.getBoolean("Visible"),
                    players));
        }
    }

    public void write(final Path dataDir, final Path levelDat) throws IOException {
        writeLevelDat(levelDat);
        writeDimensionData(dataDir);
    }

    private void writeLevelDat(final Path levelDat) throws IOException {
        Files.createDirectories(levelDat.getParent());

        final CompoundBinaryTag.Builder data = CompoundBinaryTag.builder();
        data.putInt("DataVersion", dataVersion);

        final CompoundBinaryTag.Builder version = CompoundBinaryTag.builder();
        version.putInt("Id", dataVersion);
        version.putString("Name", versionName);
        version.putString("Series", "main");
        version.putBoolean("Snapshot", snapshot);
        data.put("Version", version.build());

        if (versionHistory.isEmpty() || versionHistory.getLast() != dataVersion) {
            versionHistory.add(dataVersion);
        }
        final ListBinaryTag.Builder<BinaryTag> history = ListBinaryTag.builder();
        for (final int v : versionHistory) {
            history.add(net.kyori.adventure.nbt.IntBinaryTag.intBinaryTag(v));
        }
        data.put("version_history", history.build());

        data.putInt("version", 19133); // Anvil level format version
        data.putBoolean("initialized", true);
        data.putString("LevelName", levelName);
        data.putLong("Time", time);
        data.putLong("DayTime", dayTime);
        data.putLong("LastPlayed", System.currentTimeMillis());
        data.putBoolean("WasModded", wasModded);

        if (singleplayerUuid != null) {
            data.putIntArray("singleplayer_uuid", AnvilEntitySerializer.uuidToInts(singleplayerUuid));
        }

        final CompoundBinaryTag.Builder spawn = CompoundBinaryTag.builder();
        spawn.putString("dimension", spawnDimension.asString());
        spawn.putIntArray("pos", new int[] {spawnX, spawnY, spawnZ});
        spawn.putInt("yaw", spawnYaw);
        spawn.putInt("pitch", spawnPitch);
        data.put("spawn", spawn.build());

        data.putInt("GameType", gameType);

        final CompoundBinaryTag.Builder difficultySettings = CompoundBinaryTag.builder();
        difficultySettings.putByte("difficulty", (byte) difficulty);
        difficultySettings.putBoolean("hardcore", hardcore);
        difficultySettings.putBoolean("locked", difficultyLocked);
        data.put("difficulty_settings", difficultySettings.build());

        data.putBoolean("allowCommands", allowCommands);

        final CompoundBinaryTag.Builder dataPacks = CompoundBinaryTag.builder();
        dataPacks.put("Enabled", ListBinaryTag.builder().add(StringBinaryTag.stringBinaryTag("vanilla")).build());
        dataPacks.put("Disabled", ListBinaryTag.empty());
        data.put("DataPacks", dataPacks.build());

        data.put("ServerBrands", ListBinaryTag.builder().add(StringBinaryTag.stringBinaryTag("Fidorial")).build());

        final CompoundBinaryTag root = CompoundBinaryTag.builder().put("Data", data.build()).build();

        BinaryTagIO.writer().writeNamed(Map.entry("", root), levelDat, BinaryTagIO.Compression.GZIP);
    }

    private void writeDimensionData(final Path dataDir) throws IOException {
        writeDatFile(dataDir.resolve(GAME_RULES_PATH), gameRules ->
                gameRules.putString("doDaylightCycle", Boolean.toString(doDaylightCycle)));

        writeDatFile(dataDir.resolve(WEATHER_PATH), weather -> {
            weather.putBoolean("raining", raining);
            weather.putInt("rain_time", rainTime);
            weather.putBoolean("thundering", thundering);
            weather.putInt("thunder_time", thunderTime);
            weather.putInt("clear_weather_time", clearWeatherTime);
        });

        writeDatFile(dataDir.resolve(WORLD_GEN_SETTINGS_PATH), this::buildWorldGenSettings);
        writeDatFile(dataDir.resolve(CUSTOM_BOSS_EVENTS_PATH), this::buildCustomBossEvents);
        writeDatFile(dataDir.resolve(WORLD_CLOCKS_PATH), this::buildWorldClocksInto);
    }

    @FunctionalInterface
    private interface DimensionDataWriter {
        void accept(CompoundBinaryTag.Builder root);
    }

    private void writeDatFile(final Path path, final DimensionDataWriter payload) throws IOException {
        Files.createDirectories(path.getParent());

        final CompoundBinaryTag.Builder root = CompoundBinaryTag.builder();
        root.putInt("DataVersion", dataVersion);
        payload.accept(root);

        BinaryTagIO.writer().writeNamed(Map.entry("", root.build()), path, BinaryTagIO.Compression.GZIP);
    }

    private void buildWorldClocksInto(final CompoundBinaryTag.Builder root) {
        final ListBinaryTag.Builder<BinaryTag> clocks = ListBinaryTag.builder();
        for (final Map.Entry<Key, WorldTime> entry : worldTimes.entrySet()) {
            final WorldTime value = entry.getValue();
            final CompoundBinaryTag.Builder clock = CompoundBinaryTag.builder();
            clock.putString("Dimension", entry.getKey().asString());
            clock.putLong("WorldAge", value.worldAge());
            clock.putLong("DayTime", value.dayTime());
            clock.putBoolean("DoDaylightCycle", value.doDaylightCycle());
            clocks.add(clock.build());
        }
        root.put(WORLD_CLOCKS, clocks.build());
    }

    // this is the framework for the built-in /bossbars command, which DOES persist, unlike plugin ones
    private void buildCustomBossEvents(final CompoundBinaryTag.Builder root) {
        for (final Map.Entry<Key, BossBarData> entry : bossBars.entrySet()) {
            final BossBarData value = entry.getValue();
            final CompoundBinaryTag.Builder bar = CompoundBinaryTag.builder();

            final ListBinaryTag.Builder<BinaryTag> players = ListBinaryTag.builder();
            for (final UUID uuid : value.players()) {
                players.add(IntArrayBinaryTag.intArrayBinaryTag(AnvilEntitySerializer.uuidToInts(uuid)));
            }
            bar.put("Players", players.build());

            bar.putString("Color", value.color().name().toLowerCase(Locale.ROOT));
            bar.putString("Overlay", value.overlay().name().toLowerCase(Locale.ROOT));
            bar.putBoolean("CreateWorldFog", value.flags().contains(BossBar.Flag.CREATE_WORLD_FOG));
            bar.putBoolean("DarkenScreen", value.flags().contains(BossBar.Flag.DARKEN_SCREEN));
            bar.putBoolean("PlayBossMusic", value.flags().contains(BossBar.Flag.PLAY_BOSS_MUSIC));
            bar.putInt("Max", value.max());
            bar.putInt("Value", value.value());
            bar.putString("Name", GsonComponentSerializer.gson().serialize(value.name()));
            bar.putBoolean("Visible", value.visible());

            root.put(entry.getKey().asString(), bar.build());
        }
    }

    private void buildWorldGenSettings(final CompoundBinaryTag.Builder wgs) {
        wgs.putLong("seed", seed);
        wgs.putBoolean("generate_structures", true);
        wgs.putBoolean("bonus_chest", false);

        final CompoundBinaryTag.Builder dimensions = CompoundBinaryTag.builder();
        for (final Map.Entry<Key, ChunkGeneratorConfig> entry : generators.entrySet()) {
            dimensions.put(entry.getKey().asString(), buildDimension(entry.getKey(), entry.getValue()));
        }
        wgs.put(DIMENSIONS, dimensions.build());
    }

    private CompoundBinaryTag buildDimension(final Key dimensionId, final ChunkGeneratorConfig config) {
        final CompoundBinaryTag.Builder dim = CompoundBinaryTag.builder();
        dim.putString("type", dimensionId.asString());
        dim.put("generator", buildGeneratorSettings(config));
        return dim.build();
    }

    private CompoundBinaryTag buildGeneratorSettings(final ChunkGeneratorConfig config) {
        return switch (config) {
            case ChunkGeneratorConfig.Flat(final BlockState floor, final int floorThickness, final Key biome) -> {
                final CompoundBinaryTag.Builder generator = CompoundBinaryTag.builder();
                generator.putString("type", "minecraft:flat");

                final CompoundBinaryTag.Builder settings = CompoundBinaryTag.builder();
                settings.putBoolean("features", false);
                settings.putBoolean("lakes", false);
                settings.putString("biome", biome.asString());

                final ListBinaryTag.Builder<BinaryTag> layers = ListBinaryTag.builder();
                final CompoundBinaryTag.Builder layer = CompoundBinaryTag.builder();
                layer.putInt("height", floorThickness);
                layer.putString("block", floor.name().asString());
                layers.add(layer.build());
                settings.put("layers", layers.build());
                settings.put("structure_overrides", ListBinaryTag.empty());

                generator.put("settings", settings.build());
                yield generator.build();
            }

            case ChunkGeneratorConfig.Debug _ -> CompoundBinaryTag.builder()
                    .putString("type", "minecraft:debug")
                    .build();

            case ChunkGeneratorConfig.Noise(final Key settings, final Key biomeSourcePreset) -> {
                final CompoundBinaryTag.Builder generator = CompoundBinaryTag.builder();
                generator.putString("type", "minecraft:noise");
                generator.putString("settings", settings.asString());

                final CompoundBinaryTag.Builder biomeSource = CompoundBinaryTag.builder();
                biomeSource.putString("type", "minecraft:multi_noise");
                biomeSource.putString("preset", biomeSourcePreset.asString());
                generator.put("biome_source", biomeSource.build());

                yield generator.build();
            }
        };
    }

    public long hashedSeed() {
        return Hashing.sha256().hashLong(seed).asLong();
    }

    public boolean exists(final Path levelDat) {
        return Files.isRegularFile(levelDat);
    }
}
