package fr.euphyllia.fidorial.server.world.storage;

import fr.euphyllia.fidorial.server.util.annotations.NeedsToBeRevisited;

import java.nio.file.Files;
import java.nio.file.Path;

public class WorldPaths {

    private final Path worldRoot;
    private final Layout writeLayout;

    public WorldPaths(final Path worldRoot) {
        this(worldRoot, Layout.MODERN);
    }

    public WorldPaths(final Path worldRoot, final Layout writeLayout) {
        this.worldRoot = worldRoot;
        this.writeLayout = writeLayout;
    }

    public Path worldRoot() {
        return worldRoot;
    }

    public Path levelDat() {
        return worldRoot.resolve("level.dat");
    }

    private Path modernDimensionRoot(final Dimension dim) {
        return worldRoot.resolve("dimensions").resolve(dim.id().namespace()).resolve(dim.id().value());
    }

    private Path legacyDimensionRoot(final Dimension dim) {
        return dim.legacyFolder() == null ? worldRoot : worldRoot.resolve(dim.legacyFolder());
    }

    public Path regionDir(final Dimension dim) {
        final Path base = writeLayout == Layout.MODERN ? modernDimensionRoot(dim) : legacyDimensionRoot(dim);
        return base.resolve("region");
    }

    public Path entitiesDir(final Dimension dim) {
        final Path base = writeLayout == Layout.MODERN ? modernDimensionRoot(dim) : legacyDimensionRoot(dim);
        return base.resolve("entities");
    }

    // Maybe we should store data per-dimension like Paper for multi-world
    @NeedsToBeRevisited("Might not allow for proper multi-dimension support")
    public Path dataDir() {
        return worldRoot.resolve("data");
    }

    public Path poiDir(final Dimension dim) {
        final Path base = writeLayout == Layout.MODERN ? modernDimensionRoot(dim) : legacyDimensionRoot(dim);
        return base.resolve("poi");
    }

    public Path regionDirForRead(final Dimension dim) {
        final Path modern = modernDimensionRoot(dim).resolve("region");
        if (Files.isDirectory(modern)) return modern;
        final Path legacy = legacyDimensionRoot(dim).resolve("region");
        if (Files.isDirectory(legacy)) return legacy;
        return modern;
    }


    public enum Layout {
        MODERN,
        LEGACY
    }

}
