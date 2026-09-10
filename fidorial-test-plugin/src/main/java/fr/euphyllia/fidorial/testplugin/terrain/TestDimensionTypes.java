package fr.euphyllia.fidorial.testplugin.terrain;

import fr.fidorial.world.dimension.CardinalLight;
import fr.fidorial.world.dimension.DimensionTypeDefinition;
import fr.fidorial.world.dimension.DimensionTypeRegistry;
import fr.fidorial.world.dimension.IntProvider;
import fr.fidorial.world.dimension.Skybox;
import fr.fidorial.world.dimension.TimelineReference;
import fr.fidorial.world.environment.BedRule;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

import java.util.List;
import java.util.Optional;

public final class TestDimensionTypes {

    public static final DimensionTypeDefinition VOLCANIC_DEPTHS = DimensionTypeDefinition
            .builder(Key.key("fidorial", "volcanic_depths"))
            .coordinateScale(8.0)
            .hasSkylight(false)
            .hasCeiling(true)
            .hasEnderDragonFight(false)
            .ambientLight(0.15F)
            .hasFixedTime(true)
            .monsterSpawnBlockLightLimit(15)
            .monsterSpawnLightLevel(IntProvider.constant(7))
            .logicalHeight(128)
            .bounds(0, 256)
            .infiniburn(Key.key("infiniburn_nether"))
            .skybox(Skybox.NONE)
            .cardinalLight(CardinalLight.NETHER)
            .attributes(attributes -> attributes
                    .skyColor(0xDB0000)
                    .fogColor(0x240006)
                    .respawnAnchorWorks(true)
                    .canStartRaid(false)
                    .piglinsZombify(false)
                    .bedRule(new BedRule(
                            BedRule.AccessCondition.NEVER,
                            BedRule.AccessCondition.WHEN_DARK,
                            false,
                            true,
                            Optional.of(
                                    Component.translatable("fidorial.bed.sleep.not_allowed")
                            ))
                    )
            )
            .addTimeline(TimelineReference.tag(Key.key("in_nether")))
            .build();

    public static final DimensionTypeDefinition FROZEN_VOID = DimensionTypeDefinition
            .builder(Key.key("fidorial", "frozen_void"))
            .coordinateScale(1.0)
            .hasSkylight(true)
            .hasCeiling(false)
            .hasEnderDragonFight(false)
            .ambientLight(0.4F)
            .hasFixedTime(true)
            .monsterSpawnBlockLightLimit(0)
            .monsterSpawnLightLevel(IntProvider.constant(15))
            .logicalHeight(192)
            .bounds(0, 192)
            .infiniburn(Key.key("infiniburn_end"))
            .skybox(Skybox.END)
            .defaultClock(Key.key("the_end"))
            .addTimeline(TimelineReference.tag(Key.key("in_end")))
            .attributes(attributes -> attributes
                    .respawnAnchorWorks(false)
                    .skyColor(0x0A0F2C)
                    .fogColor(0x1C2450)
            )
            .build();

    public static final DimensionTypeDefinition SKYLANDS = DimensionTypeDefinition
            .builder(Key.key("fidorial", "skylands"))
            .coordinateScale(4.0)
            .hasSkylight(true)
            .hasCeiling(false)
            .hasEnderDragonFight(false)
            .ambientLight(0.0F)
            .hasFixedTime(false)
            .monsterSpawnBlockLightLimit(0)
            .monsterSpawnLightLevel(IntProvider.biasedToBottom(0, 7))
            .logicalHeight(384)
            .bounds(-16, 384)
            .infiniburn(Key.key("infiniburn_overworld"))
            .defaultClock(Key.key("overworld"))
            .addTimeline(TimelineReference.tag(Key.key("in_overworld")))
            .attributes(attributes -> attributes
                    .respawnAnchorWorks(false)
                    .skyColor(0x8FD3FF)
                    .fogColor(0xD8F1FF)
            )
            .build();

    public static final DimensionTypeDefinition TOXIC_SWAMPLAND = DimensionTypeDefinition
            .builder(Key.key("fidorial", "toxic_swampland"))
            .coordinateScale(1.0)
            .hasSkylight(true)
            .hasCeiling(false)
            .hasEnderDragonFight(false)
            .ambientLight(0.0F)
            .hasFixedTime(false)
            .monsterSpawnBlockLightLimit(3)
            .monsterSpawnLightLevel(IntProvider.uniform(0, 10))
            .logicalHeight(384)
            .bounds(-64, 384)
            .infiniburn(Key.key("infiniburn_overworld"))
            .defaultClock(Key.key("overworld"))
            .addTimeline(TimelineReference.tag(Key.key("in_overworld")))
            .attributes(attributes -> attributes
                    .respawnAnchorWorks(false)
                    .waterEvaporates(false)
                    .skyColor(0x5C6B3E)
                    .fogColor(0x40492B)
            )
            .build();

    public static final DimensionTypeDefinition IRRADIATED_WASTELAND = DimensionTypeDefinition
            .builder(Key.key("fidorial", "irradiated_wasteland"))
            .coordinateScale(1.0)
            .hasSkylight(true)
            .hasCeiling(false)
            .hasEnderDragonFight(false)
            .ambientLight(0.0F)
            .hasFixedTime(false)
            .monsterSpawnBlockLightLimit(7)
            .monsterSpawnLightLevel(IntProvider.uniform(0, 12))
            .logicalHeight(384)
            .bounds(-64, 384)
            .infiniburn(Key.key("infiniburn_overworld"))
            .defaultClock(Key.key("overworld"))
            .addTimeline(TimelineReference.tag(Key.key("in_overworld")))
            .cardinalLight(CardinalLight.NETHER)
            .attributes(attributes -> attributes
                    .respawnAnchorWorks(false)
                    .waterEvaporates(true)
                    .skyColor(0x8C7A5E)
                    .fogColor(0x6B5A42)
                    .skyLightColor(0xB8B36A)
                    .skyLightFactor(0.7F)
                    .cloudColor(0xFF8A8063)
                    .ambientLightColor(0x9A9668)
                    .waterFogColor(0x4F5540)
            )
            .build();

    private static final List<DimensionTypeDefinition> ALL =
            List.of(VOLCANIC_DEPTHS, FROZEN_VOID, SKYLANDS, TOXIC_SWAMPLAND, IRRADIATED_WASTELAND);

    private TestDimensionTypes() {
        throw new UnsupportedOperationException("TestDimensionTypes cannot be instantiated.");
    }

    public static List<DimensionTypeDefinition> all() {
        return ALL;
    }

    public static void registerAll(final DimensionTypeRegistry dimensions, final ComponentLogger logger) {
        for (final DimensionTypeDefinition dimensionType : ALL) {
            dimensions.overwrite(dimensionType);
            logger.info("[TestPlugin] dimension type {} registered (network id {})",
                    dimensionType.key().asString(), dimensions.networkId(dimensionType.key()));
        }

        logger.info("[TestPlugin] {} dimension types registered, {} of them defined by the server",
                dimensions.totalRegistered(), dimensions.definitions().size());
    }

    public static void unregisterAll(final DimensionTypeRegistry dimensions) {
        for (final DimensionTypeDefinition dimensionType : ALL) {
            dimensions.unregister(dimensionType.key());
        }
    }
}
