package fr.euphyllia.fidorial.server.world.storage.datafixers.util;

import fr.euphyllia.fidorial.server.world.storage.datafixers.DataFixerType;

public record NestedType(String key, DataFixerType nestedType) {
}
