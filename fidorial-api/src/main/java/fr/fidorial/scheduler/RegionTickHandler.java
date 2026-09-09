package fr.fidorial.scheduler;

import net.kyori.adventure.key.Key;

@FunctionalInterface
public interface RegionTickHandler {
    void tick(Key world, int sectionX, int sectionZ, long currentTick);
}
