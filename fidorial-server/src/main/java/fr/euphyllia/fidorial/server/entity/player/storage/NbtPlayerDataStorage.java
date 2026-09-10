package fr.euphyllia.fidorial.server.entity.player.storage;

import fr.euphyllia.fidorial.server.VersionConstants;
import fr.fidorial.entity.GameMode;
import fr.fidorial.storage.player.PlayerDataStorage;
import fr.fidorial.world.Location;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.DoubleBinaryTag;
import net.kyori.adventure.nbt.FloatBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class NbtPlayerDataStorage implements PlayerDataStorage {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(NbtPlayerDataStorage.class);
    private static final String ROOT_NAME = "PlayerData";

    // https://minecraft.wiki/w/Player.dat_format
    private static final String SPAWN_DIMENSION = "SpawnDimension";
    private static final String SPAWN_X = "SpawnX";
    private static final String SPAWN_Y = "SpawnY";
    private static final String SPAWN_Z = "SpawnZ";
    private static final String SPAWN_ANGLE = "SpawnAngle";
    private static final String SPAWN_PITCH = "SpawnPitch";

    private static final String DIMENSION = "Dimension";
    private static final String POS = "Pos";
    private static final String ROTATION = "Rotation";

    private final Path dataDir;
    private final boolean gzip;

    public NbtPlayerDataStorage(final Path playerRoot, final boolean gzip) {
        this.dataDir = playerRoot.resolve("data");
        this.gzip = gzip;
    }

    private static byte[] gzip(final byte[] plain) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(plain.length);
        try (final GZIPOutputStream out = new GZIPOutputStream(baos)) {
            out.write(plain);
        }
        return baos.toByteArray();
    }

    private static byte[] gunzip(final byte[] compressed) throws IOException {
        try (final GZIPInputStream in = new GZIPInputStream(new ByteArrayInputStream(compressed))) {
            return in.readAllBytes();
        }
    }

    private Path fileFor(final UUID uuid) {
        return dataDir.resolve(uuid.toString());
    }

    @Override
    public boolean exists(final UUID uuid) {
        return Files.isRegularFile(fileFor(uuid));
    }

    @Override
    public PlayerData load(final UUID uuid, final PlayerData defaults) throws IOException {
        final Path file = fileFor(uuid);
        if (!Files.isRegularFile(file)) {
            return defaults;
        }

        byte[] data = Files.readAllBytes(file);

        final boolean isGzip = data.length >= 2 && data[0] == (byte) 0x1F && data[1] == (byte) 0x8B;
        if (isGzip) {
            data = gunzip(data);
        }

        final CompoundBinaryTag root = BinaryTagIO.reader().readNamed(new ByteArrayInputStream(data)).getValue();

        GameMode gameMode = defaults.gameMode();
        if (root.contains("playerGameModeId")) {
            final GameMode stored = GameMode.byId(root.getInt("playerGameModeId"));
            if (stored != null) {
                gameMode = stored;
            }
        }

        Key respawnWorld = defaults.respawnWorld();
        Location respawnLocation = defaults.respawnLocation();
        if (root.contains(SPAWN_DIMENSION) && root.contains(SPAWN_X)) {
            final Key parsed = Key.parseable(root.getString(SPAWN_DIMENSION))
                    ? Key.key(root.getString(SPAWN_DIMENSION))
                    : null;
            if (parsed == null) {
                LOGGER.warn("Invalid respawn dimension for {}, respawn point dropped", uuid);
            } else {
                respawnWorld = parsed;
                respawnLocation = new Location(
                        root.getDouble(SPAWN_X),
                        root.getDouble(SPAWN_Y),
                        root.getDouble(SPAWN_Z),
                        root.contains(SPAWN_ANGLE) ? root.getFloat(SPAWN_ANGLE) : 0f,
                        root.contains(SPAWN_PITCH) ? root.getFloat(SPAWN_PITCH) : 0f);
            }
        }

        Key world = defaults.world();
        Location location = defaults.location();
        if (root.contains(DIMENSION) && root.contains(POS)) {
            final String dimension = root.getString(DIMENSION);
            if (!Key.parseable(dimension)) {
                LOGGER.warn("Invalid last-played dimension for {}, spawn location used instead", uuid);
            } else {
                final ListBinaryTag pos = root.getList(POS);
                if (pos.size() != 3) {
                    LOGGER.warn("Malformed last-played position for {}, spawn location used instead", uuid);
                } else {
                    final ListBinaryTag rotation = root.getList(ROTATION);
                    world = Key.key(dimension);
                    location = new Location(
                            doubleAt(pos, 0), doubleAt(pos, 1), doubleAt(pos, 2),
                            floatAt(rotation, 0), floatAt(rotation, 1));
                }
            }
        }

        return new PlayerData(gameMode, respawnWorld, respawnLocation, world, location);
    }

    @Override
    public void save(final UUID uuid, final PlayerData data) throws IOException {
        Files.createDirectories(dataDir);

        final CompoundBinaryTag.Builder root = CompoundBinaryTag.builder();
        root.putInt("DataVersion", VersionConstants.DATA_VERSION);
        root.putInt("playerGameModeId", data.gameMode().id());

        final Key respawnWorld = data.respawnWorld();
        final Location respawnLocation = data.respawnLocation();
        if (respawnWorld != null && respawnLocation != null) {
            root.putString(SPAWN_DIMENSION, respawnWorld.asString());
            root.putDouble(SPAWN_X, respawnLocation.x());
            root.putDouble(SPAWN_Y, respawnLocation.y());
            root.putDouble(SPAWN_Z, respawnLocation.z());
            root.putFloat(SPAWN_ANGLE, respawnLocation.yaw());
            root.putFloat(SPAWN_PITCH, respawnLocation.pitch());
        }

        final Key world = data.world();
        final Location location = data.location();
        if (world != null && location != null) {
            root.putString(DIMENSION, world.asString());
            root.put(POS, doubleList(location.x(), location.y(), location.z()));
            root.put(ROTATION, floatList(location.yaw(), location.pitch()));
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BinaryTagIO.writer().writeNamed(Map.entry(ROOT_NAME, root.build()), baos);
        byte[] bytes = baos.toByteArray();
        if (gzip) {
            bytes = gzip(bytes);
        }

        final Path file = fileFor(uuid);
        final Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
        Files.write(tmp, bytes);
        try {
            Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (final IOException atomicFailure) {
            Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING);
        }
        LOGGER.debug("Data for {} saved ({} bytes{})", uuid, bytes.length, gzip ? ", gzip" : "");
    }

    public Path dataDir() {
        return dataDir;
    }

    private static double doubleAt(final ListBinaryTag list, final int index) {
        return index < list.size() && list.get(index) instanceof final DoubleBinaryTag tag ? tag.value() : 0.0;
    }

    private static float floatAt(final ListBinaryTag list, final int index) {
        return index < list.size() && list.get(index) instanceof final FloatBinaryTag tag ? tag.value() : 0f;
    }

    private static ListBinaryTag doubleList(final double a, final double b, final double c) {
        return ListBinaryTag.builder()
                .add(DoubleBinaryTag.doubleBinaryTag(a))
                .add(DoubleBinaryTag.doubleBinaryTag(b))
                .add(DoubleBinaryTag.doubleBinaryTag(c))
                .build();
    }

    private static ListBinaryTag floatList(final float a, final float b) {
        return ListBinaryTag.builder()
                .add(FloatBinaryTag.floatBinaryTag(a))
                .add(FloatBinaryTag.floatBinaryTag(b))
                .build();
    }
}
