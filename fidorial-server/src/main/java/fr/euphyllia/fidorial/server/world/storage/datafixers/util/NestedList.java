package fr.euphyllia.fidorial.server.world.storage.datafixers.util;

import fr.euphyllia.fidorial.server.world.storage.datafixers.DataFixerType;

public record NestedList(String key, DataFixerType nestedType) {
}
