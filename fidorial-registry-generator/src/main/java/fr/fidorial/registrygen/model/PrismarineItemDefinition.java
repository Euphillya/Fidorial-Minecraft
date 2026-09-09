package fr.fidorial.registrygen.model;

import java.util.List;

/**
 * Per-item metadata sourced from PrismarineJS's {@code minecraft-data} items report.
 *
 * @param name           unnamespaced item identifier, e.g. {@code "diamond_sword"}
 * @param displayName    the English name, e.g. {@code "Diamond Sword"}
 * @param protocolId     Prismarine's numeric id; informational only, the wire id comes
 *                       from Mojang's registry report
 * @param stackSize      how many fit in one slot, {@code 1}-{@code 99}
 * @param maxDurability  total durability, or {@code 0} when the item cannot break
 * @param repairWith     unnamespaced identifiers of materials that repair this item
 *
 * @since 0.1.0
 */
public record PrismarineItemDefinition(
        String name,
        String displayName,
        int protocolId,
        int stackSize,
        int maxDurability,
        List<String> repairWith) {

    public PrismarineItemDefinition {
        repairWith = List.copyOf(repairWith);
    }
}
