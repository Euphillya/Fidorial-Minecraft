package fr.euphyllia.fidorial.server.entity.player.storage;

import fr.euphyllia.fidorial.server.codecs.container.PlayerEnderChestCodec;
import fr.fidorial.inventory.EnderChestInventory;
import fr.fidorial.storage.player.PlayerEnderChestStorage;
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

/**
 * File-based implementation of {@link PlayerEnderChestStorage}, one file per player in
 * {@code <world>/playerdata/enderchests/}.
 */
public class NbtPlayerEnderChestStorage implements PlayerEnderChestStorage {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(NbtPlayerEnderChestStorage.class);

    private final Path enderChestsDir;
    private final boolean gzip;

    public NbtPlayerEnderChestStorage(final Path playerRoot, final boolean gzip) {
        this.enderChestsDir = playerRoot.resolve("enderchests");
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
        return enderChestsDir.resolve(uuid.toString());
    }

    @Override
    public EnderChestInventory load(final UUID uuid) throws IOException {
        final Path file = fileFor(uuid);
        if (!Files.isRegularFile(file)) {
            return new EnderChestInventory();
        }

        byte[] data = Files.readAllBytes(file);
        final boolean isGzip = data.length >= 2 && data[0] == (byte) 0x1F && data[1] == (byte) 0x8B;
        if (isGzip) {
            data = gunzip(data);
        }
        return PlayerEnderChestCodec.decode(data);
    }

    @Override
    public void save(final UUID uuid, final EnderChestInventory enderChest) throws IOException {
        Files.createDirectories(enderChestsDir);

        byte[] data = PlayerEnderChestCodec.encode(enderChest);
        if (gzip) {
            data = gzip(data);
        }

        final Path file = fileFor(uuid);
        final Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
        Files.write(tmp, data);
        try {
            Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (final IOException atomicFailure) {
            Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING);
        }
        LOGGER.debug("Ender chest for {} saved ({} bytes{})", uuid, data.length, gzip ? ", gzip" : "");
    }

    public Path enderChestsDir() {
        return enderChestsDir;
    }
}
