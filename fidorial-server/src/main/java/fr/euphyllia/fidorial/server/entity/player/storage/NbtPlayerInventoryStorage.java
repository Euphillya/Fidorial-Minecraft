package fr.euphyllia.fidorial.server.entity.player.storage;

import fr.euphyllia.fidorial.server.codecs.container.PlayerInventoryCodec;
import fr.fidorial.inventory.PlayerInventory;
import fr.fidorial.storage.player.PlayerInventoryStorage;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class NbtPlayerInventoryStorage implements PlayerInventoryStorage {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(NbtPlayerInventoryStorage.class);

    private final Path inventoriesDir;
    private final boolean gzip;

    public NbtPlayerInventoryStorage(final Path playerRoot, final boolean gzip) {
        this.inventoriesDir = playerRoot.resolve("inventories");
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
        return inventoriesDir.resolve(uuid.toString());
    }

    @Override
    public PlayerInventory load(final UUID uuid) throws IOException {
        final Path file = fileFor(uuid);
        if (!Files.isRegularFile(file)) {
            return new PlayerInventory();
        }

        byte[] data = Files.readAllBytes(file);

        final boolean isGzip = data.length >= 2
                && data[0] == (byte) 0x1F
                && data[1] == (byte) 0x8B;
        if (isGzip) {
            data = gunzip(data);
        }
        return PlayerInventoryCodec.decode(data);
    }

    @Override
    public void save(final UUID uuid, final PlayerInventory inventory) throws IOException {
        Files.createDirectories(inventoriesDir);

        byte[] data = PlayerInventoryCodec.encode(inventory);
        if (gzip) {
            data = gzip(data);
        }

        final Path file = fileFor(uuid);
        final Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
        Files.write(tmp, data);
        try {
            Files.move(tmp, file,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE);
        } catch (final IOException atomicFailure) {
            Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING);
        }
        LOGGER.debug("Inventory of {} saved ({} bytes{})",
                uuid, data.length, gzip ? ", gzip" : "");
    }

    public boolean gzipEnabled() {
        return gzip;
    }

    public Path inventoriesDir() {
        return inventoriesDir;
    }
}
