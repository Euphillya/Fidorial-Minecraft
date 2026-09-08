package fr.euphyllia.fidorial.server;

import fr.euphyllia.fidorial.server.moderation.CodeOfConductManager;
import fr.euphyllia.fidorial.server.world.WorldConstants;
import fr.fidorial.entity.GameMode;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

public record ServerConfig(
        int port,
        boolean onlineMode,
        boolean encryptOfflineModeConnections,
        int viewDistance,
        int sendDistance,
        int compressionThreshold,
        Path worldPath,
        Path pluginsPath,
        int autoSaveSeconds,
        int regionWorkers,
        int chunkWorkers,
        int lightWorkers,
        int aiWorkers,
        int regionShift,
        GameMode defaultGameMode,
        double spawnX,
        double spawnY,
        double spawnZ,
        String motd,
        int maxPlayers,
        boolean pvp,
        ProxyMode proxyMode,
        @Nullable String velocitySecret,
        boolean useIoUring,
        @Nullable String resourcePackUrl,
        @Nullable String resourcePackHash,
        @Nullable UUID resourcePackId,
        boolean resourcePackForced,
        @Nullable Component resourcePackPrompt,
        boolean enableCodeOfConduct,
        Path codeOfConductPath,
        boolean sparkEnabled,
        Path sparkPath
) {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(ServerConfig.class);
    private static final String DEFAULT_FILE = "fidorial.properties";

    public ServerConfig {
        if (port < 1 || port > 65535) {
            throw new IllegalArgumentException("port hors bornes : " + port);
        }
        if (sendDistance > viewDistance) {
            throw new IllegalArgumentException(
                    "send-distance (" + sendDistance + ") > view-distance (" + viewDistance + ")");
        }
        if (proxyMode == ProxyMode.VELOCITY && (velocitySecret == null || velocitySecret.isBlank())) {
            throw new IllegalArgumentException(
                    "proxy-mode=velocity requires velocity-secret (the content of the proxy's forwarding.secret file)");
        }
        if (resourcePackHash != null && !resourcePackHash.isBlank()
                && !resourcePackHash.matches("[0-9a-f]{40}")) {
            throw new IllegalArgumentException(
                    "resource-pack-hash must be a 40-character lowercase SHA-1 hex string, or empty");
        }
        if (resourcePackId != null && (resourcePackUrl == null || resourcePackUrl.isBlank())) {
            throw new IllegalArgumentException("resource-pack-id set without a resource-pack-url");
        }
    }

    public enum ProxyMode {
        NONE,
        VELOCITY;

        static @Nullable ProxyMode byName(final String raw) {
            for (final ProxyMode mode : values()) {
                if (mode.name().equalsIgnoreCase(raw)) {
                    return mode;
                }
            }
            return null;
        }
    }

    public static ServerConfig defaults() {
        final int cpus = Runtime.getRuntime().availableProcessors();
        return new ServerConfig(
                25565,
                true,
                false,
                10,
                10,
                256,
                Path.of("world"),
                Path.of("plugins"),
                5,
                Math.max(2, cpus / 2),
                Math.max(2, cpus / 8),
                Math.max(2, cpus / 8),
                Math.max(2, cpus / 8),
                5,
                GameMode.SURVIVAL,
                WorldConstants.DEFAULT_SPAWN_X,
                WorldConstants.DEFAULT_SPAWN_Y,
                WorldConstants.DEFAULT_SPAWN_Z,
                "",
                100,
                true,
                ProxyMode.NONE,
                "",
                false,
                "",
                "",
                null,
                false,
                Component.empty(),
                false,
                Path.of(CodeOfConductManager.DEFAULT_FOLDER),
                true,
                Path.of("spark"));
    }

    public static ServerConfig load() throws IOException {
        final Path file = Path.of(DEFAULT_FILE);
        final ServerConfig config = read(file);
        config.write(file);
        return config;
    }

    public static ServerConfig read(final Path file) throws IOException {
        final ServerConfig defaults = defaults();
        if (!Files.isRegularFile(file)) {
            return defaults;
        }
        final Properties props = new Properties();
        try (final InputStream in = Files.newInputStream(file)) {
            props.load(in);
        }
        final String resourcePackUrl = readString(props, "resource-pack-url", "");
        final ServerConfig config = new ServerConfig(
                readInt(props, "port", defaults.port()),
                readBool(props, "online-mode", defaults.onlineMode()),
                readBool(props, "encrypt-offline-mode-connections", defaults.encryptOfflineModeConnections()),
                readInt(props, "view-distance", defaults.viewDistance()),
                readInt(props, "send-distance", defaults.sendDistance()),
                readInt(props, "compression-threshold", defaults.compressionThreshold()),
                Path.of(props.getProperty("world-path", defaults.worldPath().toString())),
                Path.of(props.getProperty("plugins-path", defaults.pluginsPath().toString())),
                readInt(props, "auto-save-seconds", defaults.autoSaveSeconds()),
                readInt(props, "region-workers", defaults.regionWorkers()),
                readInt(props, "chunk-workers", defaults.chunkWorkers()),
                readInt(props, "light-workers", defaults.lightWorkers()),
                readInt(props, "ai-workers", defaults.aiWorkers()),
                readInt(props, "region-section-shift", defaults.regionShift()),
                readGameMode(props, "default-game-mode", defaults.defaultGameMode()),
                readDouble(props, "spawn-x", defaults.spawnX()),
                readDouble(props, "spawn-y", defaults.spawnY()),
                readDouble(props, "spawn-z", defaults.spawnZ()),
                readString(props, "motd", "<red>Fidorial <white>| <blue>Alternative Minecraft Server"),
                readInt(props, "max-players", defaults.maxPlayers()),
                readBool(props, "pvp", defaults.pvp()),
                readProxyMode(props, "proxy-mode", defaults.proxyMode()),
                readString(props, "velocity-secret", "").strip(),
                readBool(props, "use-io-uring", false),
                resourcePackUrl,
                readString(props, "resource-pack-hash", ""),
                resolveResourcePackId(props, resourcePackUrl),
                readBool(props, "resource-pack-forced", false),
                readComponent(props, "resource-pack-prompt", Component.empty()),
                readBool(props, "enable-code-of-conduct", defaults.enableCodeOfConduct()),
                Path.of(props.getProperty(
                        "code-of-conduct-path", defaults.codeOfConductPath().toString())),
                readBool(props, "spark-enabled", defaults.sparkEnabled()),
                Path.of(props.getProperty("spark-path", defaults.sparkPath().toString())));
        LOGGER.info("Configuration loaded from {}", file);
        return config;
    }

    private static int readInt(final Properties props, final String key, final int fallback) {
        final String raw = props.getProperty(key);
        if (raw == null || raw.isBlank()) {
            return fallback;
        }
        try {
            return Integer.parseInt(raw.strip());
        } catch (final NumberFormatException e) {
            LOGGER.warn("{} = '{}' unreadable, default value {} used", key, raw, fallback);
            return fallback;
        }
    }

    private static double readDouble(final Properties props, final String key, final double fallback) {
        final String raw = props.getProperty(key);
        if (raw == null || raw.isBlank()) {
            return fallback;
        }
        try {
            return Double.parseDouble(raw.strip());
        } catch (final NumberFormatException e) {
            LOGGER.warn("{} = '{}' invalid, default value {} used", key, raw, fallback);
            return fallback;
        }
    }

    private static String readString(final Properties props, final String key, final String fallback) {
        final String raw = props.getProperty(key);
        if (raw == null || raw.isBlank()) {
            return fallback;
        }
        return raw;
    }

    private static Component readComponent(final Properties props, final String key, final Component fallback) {
        final String raw = props.getProperty(key);
        if (raw == null || raw.isBlank()) {
            return fallback;
        }
        try {
            return MiniMessage.miniMessage().deserialize(raw.strip());
        } catch (final Exception e) {
            LOGGER.warn("{} = '{}' could not be parsed as MiniMessage, default value used", key, raw, e);
            return fallback;
        }
    }

    private static UUID readUuid(final Properties props, final String key, final UUID fallback) {
        final String raw = props.getProperty(key);
        if (raw == null || raw.isBlank()) {
            return fallback;
        }
        try {
            return UUID.fromString(raw.strip());
        } catch (final IllegalArgumentException e) {
            LOGGER.warn("{} = '{}' is not a valid UUID, default value {} used", key, raw, fallback, e);
            return fallback;
        }
    }

    private static @Nullable UUID resolveResourcePackId(final Properties props, final String resourcePackUrl) {
        if (resourcePackUrl.isBlank()) {
            return null;
        }
        final String raw = props.getProperty("resource-pack-id");
        if (raw == null || raw.isBlank()) {
            return UUID.randomUUID();
        }
        try {
            return UUID.fromString(raw.strip());
        } catch (final IllegalArgumentException e) {
            final UUID generated = UUID.randomUUID();
            LOGGER.warn("resource-pack-id = '{}' is not a valid UUID, generated {} instead", raw, generated, e);
            return generated;
        }
    }

    private static GameMode readGameMode(final Properties props, final String key, final GameMode fallback) {
        final String raw = props.getProperty(key);
        if (raw == null || raw.isBlank()) {
            return fallback;
        }
        final GameMode mode = GameMode.byName(raw.strip());
        if (mode == null) {
            LOGGER.warn("{} = '{}' unknown, default value {} used", key, raw, fallback);
            return fallback;
        }
        return mode;
    }

    private static ProxyMode readProxyMode(final Properties props, final String key, final ProxyMode fallback) {
        final String raw = props.getProperty(key);
        if (raw == null || raw.isBlank()) {
            return fallback;
        }
        final ProxyMode mode = ProxyMode.byName(raw.strip());
        if (mode == null) {
            LOGGER.warn("{} = '{}' unknown (expected: none, velocity), default value {} used", key, raw, fallback);
            return fallback;
        }
        return mode;
    }

    private static boolean readBool(final Properties props, final String key, final boolean fallback) {
        final String raw = props.getProperty(key);
        return raw == null || raw.isBlank() ? fallback : Boolean.parseBoolean(raw.strip());
    }

    public void write(final Path file) throws IOException {
        final Properties props = new Properties();
        props.setProperty("port", Integer.toString(port));
        props.setProperty("online-mode", Boolean.toString(onlineMode));
        props.setProperty("encrypt-offline-mode-connections", Boolean.toString(encryptOfflineModeConnections));
        props.setProperty("view-distance", Integer.toString(viewDistance));
        props.setProperty("send-distance", Integer.toString(sendDistance));
        props.setProperty("compression-threshold", Integer.toString(compressionThreshold));
        props.setProperty("world-path", worldPath.toString());
        props.setProperty("plugins-path", pluginsPath.toString());
        props.setProperty("auto-save-seconds", Integer.toString(autoSaveSeconds));
        props.setProperty("region-workers", Integer.toString(regionWorkers));
        props.setProperty("chunk-workers", Integer.toString(chunkWorkers));
        props.setProperty("light-workers", Integer.toString(lightWorkers));
        props.setProperty("ai-workers", Integer.toString(aiWorkers));
        props.setProperty("region-section-shift", Integer.toString(regionShift));
        props.setProperty("default-game-mode", defaultGameMode.name().toLowerCase(Locale.ROOT));
        props.setProperty("spawn-x", Double.toString(spawnX));
        props.setProperty("spawn-y", Double.toString(spawnY));
        props.setProperty("spawn-z", Double.toString(spawnZ));
        props.setProperty("motd", motd);
        props.setProperty("max-players", Integer.toString(maxPlayers));
        props.setProperty("pvp", Boolean.toString(pvp));
        props.setProperty("proxy-mode", proxyMode.name().toLowerCase(Locale.ROOT));
        props.setProperty("velocity-secret", velocitySecret == null ? "" : velocitySecret);
        props.setProperty("use-io-uring", Boolean.toString(useIoUring));
        props.setProperty("resource-pack-url", resourcePackUrl == null ? "" : resourcePackUrl);
        props.setProperty("resource-pack-hash", resourcePackHash == null ? "" : resourcePackHash);
        props.setProperty("resource-pack-id", resourcePackId == null ? "" : resourcePackId.toString());
        props.setProperty("resource-pack-forced", Boolean.toString(resourcePackForced));
        props.setProperty("resource-pack-prompt", resourcePackPrompt == null ? "" : MiniMessage.miniMessage().serialize(resourcePackPrompt));
        props.setProperty("enable-code-of-conduct", Boolean.toString(enableCodeOfConduct));
        props.setProperty("code-of-conduct-path", codeOfConductPath.toString());
        props.setProperty("spark-enabled", Boolean.toString(sparkEnabled));
        props.setProperty("spark-path", sparkPath.toString());
        try (final OutputStream out = Files.newOutputStream(file)) {
            props.store(out, "Configuration Fidorial");
        }
    }
}
