package fr.euphyllia.fidorial.server.codecs.world;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.euphyllia.fidorial.server.codecs.CommonCodecs;
import fr.euphyllia.fidorial.server.codecs.adventure.ComponentCodecs;
import fr.fidorial.world.environment.AdditionsSound;
import fr.fidorial.world.environment.AmbientParticle;
import fr.fidorial.world.environment.AmbientSounds;
import fr.fidorial.world.environment.Attribute;
import fr.fidorial.world.environment.BackgroundMusic;
import fr.fidorial.world.environment.BedRule;
import fr.fidorial.world.environment.EnvironmentAttributes;
import fr.fidorial.world.environment.Modifier;
import fr.fidorial.world.environment.MoodSound;
import fr.fidorial.world.environment.MusicTrack;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.util.TriState;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EnvironmentAttributeCodecs {

    public static final Codec<Modifier> MODIFIER = Codec.STRING.comapFlatMap(
            name -> Arrays.stream(Modifier.values())
                    .filter(modifier -> modifier.id().equals(name))
                    .findFirst()
                    .map(DataResult::success)
                    .orElseGet(() -> DataResult.error(() -> "Unknown attribute modifier: " + name)),
            Modifier::id);

    public static final Codec<BedRule.AccessCondition> SLEEP_ACCESS = Codec.STRING.comapFlatMap(
            id -> BedRule.AccessCondition.fromId(id)
                    .map(DataResult::success)
                    .orElseGet(() -> DataResult.error(() -> "Unknown bed rule value: " + id)),
            BedRule.AccessCondition::id);

    public static final Codec<BedRule> BED_RULE = RecordCodecBuilder.create(instance -> instance.group(
            SLEEP_ACCESS.fieldOf("can_sleep").forGetter(BedRule::sleepAllowed),
            SLEEP_ACCESS.fieldOf("can_set_spawn").forGetter(BedRule::spawnAllowed),
            Codec.BOOL.optionalFieldOf("destroy_on_use", false).forGetter(BedRule::destroysOnUse),
            Codec.BOOL.optionalFieldOf("destroy_on_leave", false).forGetter(BedRule::destroysOnLeave),
            ComponentCodecs.COMPONENT_CODEC.optionalFieldOf("error_message").forGetter(BedRule::failureMessage)
    ).apply(instance, BedRule::new));

    public static final Codec<AmbientParticle> AMBIENT_PARTICLE = RecordCodecBuilder.create(instance -> instance.group(
            BiomeCodecs.PARTICLE_OPTIONS.fieldOf("particle").forGetter(AmbientParticle::type),
            Codec.FLOAT.fieldOf("probability").forGetter(AmbientParticle::probability)
    ).apply(instance, AmbientParticle::new));

    public static final Codec<MoodSound> MOOD_SOUND = RecordCodecBuilder.create(instance -> instance.group(
            CommonCodecs.KEY_CODEC.fieldOf("sound").forGetter(MoodSound::sound),
            Codec.INT.fieldOf("tick_delay").forGetter(MoodSound::tickDelay),
            Codec.INT.fieldOf("block_search_extent").forGetter(MoodSound::blockSearchExtent),
            Codec.DOUBLE.fieldOf("offset").forGetter(MoodSound::offset)
    ).apply(instance, MoodSound::new));

    public static final Codec<AdditionsSound> ADDITIONS_SOUND = RecordCodecBuilder.create(instance -> instance.group(
            CommonCodecs.KEY_CODEC.fieldOf("sound").forGetter(AdditionsSound::sound),
            Codec.FLOAT.fieldOf("tick_chance").forGetter(AdditionsSound::tickChance)
    ).apply(instance, AdditionsSound::new));

    public static final Codec<AmbientSounds> AMBIENT_SOUNDS = RecordCodecBuilder.create(instance -> instance.group(
            CommonCodecs.KEY_CODEC.optionalFieldOf("loop").forGetter(sounds -> Optional.ofNullable(sounds.loop())),
            MOOD_SOUND.optionalFieldOf("mood").forGetter(sounds -> Optional.ofNullable(sounds.mood())),
            ADDITIONS_SOUND.optionalFieldOf("additions").forGetter(sounds -> Optional.ofNullable(sounds.additions()))
    ).apply(instance, (loop, mood, additions) ->
            new AmbientSounds(loop.orElse(null), mood.orElse(null), additions.orElse(null))));

    public static final Codec<MusicTrack> MUSIC_TRACK = RecordCodecBuilder.create(instance -> instance.group(
            CommonCodecs.KEY_CODEC.fieldOf("sound").forGetter(MusicTrack::sound),
            Codec.INT.fieldOf("min_delay").forGetter(MusicTrack::minDelay),
            Codec.INT.fieldOf("max_delay").forGetter(MusicTrack::maxDelay),
            Codec.BOOL.optionalFieldOf("replace_current_music", false).forGetter(MusicTrack::replaceCurrentMusic)
    ).apply(instance, MusicTrack::new));

    public static final Codec<BackgroundMusic> BACKGROUND_MUSIC = RecordCodecBuilder.create(instance -> instance.group(
            MUSIC_TRACK.optionalFieldOf("default").forGetter(music -> Optional.ofNullable(music.normal())),
            MUSIC_TRACK.optionalFieldOf("underwater").forGetter(music -> Optional.ofNullable(music.underwater())),
            MUSIC_TRACK.optionalFieldOf("creative").forGetter(music -> Optional.ofNullable(music.creative()))
    ).apply(instance, (normal, underwater, creative) ->
            new BackgroundMusic(normal.orElse(null), underwater.orElse(null), creative.orElse(null))));

    private record VisualPartA(
            @Nullable Attribute<Integer> fogColor,
            @Nullable Attribute<Float> fogStartDistance,
            @Nullable Attribute<Float> fogEndDistance,
            @Nullable Attribute<Float> skyFogEndDistance,
            @Nullable Attribute<Float> cloudFogEndDistance,
            @Nullable Attribute<Integer> skyColor,
            @Nullable Attribute<Integer> sunriseSunsetColor,
            @Nullable Attribute<Integer> cloudColor,
            @Nullable Attribute<Float> cloudHeight,
            @Nullable Attribute<Float> sunAngle,
            @Nullable Attribute<Float> moonAngle,
            @Nullable Attribute<Float> starAngle
    ) { }

    private record VisualPartB(
            @Nullable Attribute<Integer> moonPhase,
            @Nullable Attribute<Float> starBrightness,
            @Nullable Attribute<Integer> blockLightTint,
            @Nullable Attribute<Integer> skyLightColor,
            @Nullable Attribute<Float> skyLightFactor,
            @Nullable Attribute<Integer> nightVisionColor,
            @Nullable Attribute<Integer> ambientLightColor,
            @Nullable Attribute<Key> defaultDripstoneParticle,
            @Nullable Attribute<Integer> waterFogColor,
            @Nullable Attribute<Float> waterFogStartDistance,
            @Nullable Attribute<Float> waterFogEndDistance
    ) { }

    private record GameplayPartA(
            @Nullable Attribute<Float> musicVolume,
            @Nullable Attribute<Boolean> fireflyBushSounds,
            @Nullable Attribute<Boolean> canStartRaid,
            @Nullable Attribute<Boolean> canPillagerPatrolSpawn,
            @Nullable Attribute<Boolean> waterEvaporates,
            @Nullable Attribute<BedRule> bedRule,
            @Nullable Attribute<BedRule> strawBedRule,
            @Nullable Attribute<Boolean> respawnAnchorWorks,
            @Nullable Attribute<Boolean> netherPortalSpawnsPiglin,
            @Nullable Attribute<Boolean> fastLava,
            @Nullable Attribute<Boolean> increasedFireBurnout,
            @Nullable Attribute<TriState> eyeblossomOpen,
            @Nullable Attribute<Float> turtleEggHatchChance,
            @Nullable Attribute<Boolean> piglinsZombify,
            @Nullable Attribute<Boolean> snowGolemMelts,
            @Nullable Attribute<Boolean> creakingActive
    ) { }

    private record GameplayPartB(
            @Nullable Attribute<Float> surfaceSlimeSpawnChance,
            @Nullable Attribute<Float> catWakingUpGiftChance,
            @Nullable Attribute<Boolean> beesStayInHive,
            @Nullable Attribute<Boolean> monstersBurn,
            @Nullable Attribute<Float> creatureWorldGenSpawnProbability,
            @Nullable Attribute<Key> villagerActivity,
            @Nullable Attribute<Key> babyVillagerActivity,
            @Nullable Attribute<Float> skyLightLevel
    ) { }

    private static final MapCodec<VisualPartA> VISUAL_A = RecordCodecBuilder.mapCodec(instance -> instance.group(
            modifiable(CommonCodecs.RGB_COLOR).optionalFieldOf("visual/fog_color")
                    .forGetter(v -> Optional.ofNullable(v.fogColor())),
            modifiable(Codec.FLOAT).optionalFieldOf("visual/fog_start_distance")
                    .forGetter(v -> Optional.ofNullable(v.fogStartDistance())),
            modifiable(Codec.FLOAT).optionalFieldOf("visual/fog_end_distance")
                    .forGetter(v -> Optional.ofNullable(v.fogEndDistance())),
            modifiable(Codec.FLOAT).optionalFieldOf("visual/sky_fog_end_distance")
                    .forGetter(v -> Optional.ofNullable(v.skyFogEndDistance())),
            modifiable(Codec.FLOAT).optionalFieldOf("visual/cloud_fog_end_distance")
                    .forGetter(v -> Optional.ofNullable(v.cloudFogEndDistance())),
            modifiable(CommonCodecs.RGB_COLOR).optionalFieldOf("visual/sky_color")
                    .forGetter(v -> Optional.ofNullable(v.skyColor())),
            modifiable(CommonCodecs.ARGB_COLOR).optionalFieldOf("visual/sunrise_sunset_color")
                    .forGetter(v -> Optional.ofNullable(v.sunriseSunsetColor())),
            modifiable(CommonCodecs.ARGB_COLOR).optionalFieldOf("visual/cloud_color")
                    .forGetter(v -> Optional.ofNullable(v.cloudColor())),
            modifiable(Codec.FLOAT).optionalFieldOf("visual/cloud_height")
                    .forGetter(v -> Optional.ofNullable(v.cloudHeight())),
            modifiable(Codec.FLOAT).optionalFieldOf("visual/sun_angle")
                    .forGetter(v -> Optional.ofNullable(v.sunAngle())),
            modifiable(Codec.FLOAT).optionalFieldOf("visual/moon_angle")
                    .forGetter(v -> Optional.ofNullable(v.moonAngle())),
            modifiable(Codec.FLOAT).optionalFieldOf("visual/star_angle")
                    .forGetter(v -> Optional.ofNullable(v.starAngle()))
    ).apply(instance, (fogColor, fogStartDistance, fogEndDistance, skyFogEndDistance, cloudFogEndDistance, skyColor,
                       sunriseSunsetColor, cloudColor, cloudHeight, sunAngle, moonAngle, starAngle) ->
            new VisualPartA(
                    fogColor.orElse(null),
                    fogStartDistance.orElse(null),
                    fogEndDistance.orElse(null),
                    skyFogEndDistance.orElse(null),
                    cloudFogEndDistance.orElse(null),
                    skyColor.orElse(null),
                    sunriseSunsetColor.orElse(null),
                    cloudColor.orElse(null),
                    cloudHeight.orElse(null),
                    sunAngle.orElse(null),
                    moonAngle.orElse(null),
                    starAngle.orElse(null))));

    private static final MapCodec<VisualPartB> VISUAL_B = RecordCodecBuilder.mapCodec(instance -> instance.group(
            modifiable(Codec.INT).optionalFieldOf("visual/moon_phase")
                    .forGetter(v -> Optional.ofNullable(v.moonPhase())),
            modifiable(Codec.FLOAT).optionalFieldOf("visual/star_brightness")
                    .forGetter(v -> Optional.ofNullable(v.starBrightness())),
            modifiable(CommonCodecs.RGB_COLOR).optionalFieldOf("visual/block_light_tint")
                    .forGetter(v -> Optional.ofNullable(v.blockLightTint())),
            modifiable(CommonCodecs.RGB_COLOR).optionalFieldOf("visual/sky_light_color")
                    .forGetter(v -> Optional.ofNullable(v.skyLightColor())),
            modifiable(Codec.FLOAT).optionalFieldOf("visual/sky_light_factor")
                    .forGetter(v -> Optional.ofNullable(v.skyLightFactor())),
            modifiable(CommonCodecs.RGB_COLOR).optionalFieldOf("visual/night_vision_color")
                    .forGetter(v -> Optional.ofNullable(v.nightVisionColor())),
            modifiable(CommonCodecs.RGB_COLOR).optionalFieldOf("visual/ambient_light_color")
                    .forGetter(v -> Optional.ofNullable(v.ambientLightColor())),
            modifiable(BiomeCodecs.PARTICLE_OPTIONS).optionalFieldOf("visual/default_dripstone_particle")
                    .forGetter(v -> Optional.ofNullable(v.defaultDripstoneParticle())),
            modifiable(CommonCodecs.RGB_COLOR).optionalFieldOf("visual/water_fog_color")
                    .forGetter(v -> Optional.ofNullable(v.waterFogColor())),
            modifiable(Codec.FLOAT).optionalFieldOf("visual/water_fog_start_distance")
                    .forGetter(v -> Optional.ofNullable(v.waterFogStartDistance())),
            modifiable(Codec.FLOAT).optionalFieldOf("visual/water_fog_end_distance")
                    .forGetter(v -> Optional.ofNullable(v.waterFogEndDistance()))
    ).apply(instance, (moonPhase, starBrightness, blockLightTint, skyLightColor, skyLightFactor, nightVisionColor,
                       ambientLightColor, defaultDripstoneParticle, waterFogColor, waterFogStartDistance,
                       waterFogEndDistance) ->
            new VisualPartB(
                    moonPhase.orElse(null),
                    starBrightness.orElse(null),
                    blockLightTint.orElse(null),
                    skyLightColor.orElse(null),
                    skyLightFactor.orElse(null),
                    nightVisionColor.orElse(null),
                    ambientLightColor.orElse(null),
                    defaultDripstoneParticle.orElse(null),
                    waterFogColor.orElse(null),
                    waterFogStartDistance.orElse(null),
                    waterFogEndDistance.orElse(null))));

    private static final MapCodec<GameplayPartA> GAMEPLAY_A = RecordCodecBuilder.mapCodec(instance -> instance.group(
            modifiable(Codec.FLOAT).optionalFieldOf("audio/music_volume")
                    .forGetter(g -> Optional.ofNullable(g.musicVolume())),
            modifiable(Codec.BOOL).optionalFieldOf("audio/firefly_bush_sounds")
                    .forGetter(g -> Optional.ofNullable(g.fireflyBushSounds())),
            modifiable(Codec.BOOL).optionalFieldOf("gameplay/can_start_raid")
                    .forGetter(g -> Optional.ofNullable(g.canStartRaid())),
            modifiable(Codec.BOOL).optionalFieldOf("gameplay/can_pillager_patrol_spawn")
                    .forGetter(g -> Optional.ofNullable(g.canPillagerPatrolSpawn())),
            modifiable(Codec.BOOL).optionalFieldOf("gameplay/water_evaporates")
                    .forGetter(g -> Optional.ofNullable(g.waterEvaporates())),
            modifiable(BED_RULE).optionalFieldOf("gameplay/bed_rule")
                    .forGetter(g -> Optional.ofNullable(g.bedRule())),
            modifiable(BED_RULE).optionalFieldOf("gameplay/straw_bed_rule")
                    .forGetter(g -> Optional.ofNullable(g.strawBedRule())),
            modifiable(Codec.BOOL).optionalFieldOf("gameplay/respawn_anchor_works")
                    .forGetter(g -> Optional.ofNullable(g.respawnAnchorWorks())),
            modifiable(Codec.BOOL).optionalFieldOf("gameplay/nether_portal_spawns_piglin")
                    .forGetter(g -> Optional.ofNullable(g.netherPortalSpawnsPiglin())),
            modifiable(Codec.BOOL).optionalFieldOf("gameplay/fast_lava")
                    .forGetter(g -> Optional.ofNullable(g.fastLava())),
            modifiable(Codec.BOOL).optionalFieldOf("gameplay/increased_fire_burnout")
                    .forGetter(g -> Optional.ofNullable(g.increasedFireBurnout())),
            modifiable(CommonCodecs.TRI_STATE).optionalFieldOf("gameplay/eyeblossom_open")
                    .forGetter(g -> Optional.ofNullable(g.eyeblossomOpen())),
            modifiable(Codec.FLOAT).optionalFieldOf("gameplay/turtle_egg_hatch_chance")
                    .forGetter(g -> Optional.ofNullable(g.turtleEggHatchChance())),
            modifiable(Codec.BOOL).optionalFieldOf("gameplay/piglins_zombify")
                    .forGetter(g -> Optional.ofNullable(g.piglinsZombify())),
            modifiable(Codec.BOOL).optionalFieldOf("gameplay/snow_golem_melts")
                    .forGetter(g -> Optional.ofNullable(g.snowGolemMelts())),
            modifiable(Codec.BOOL).optionalFieldOf("gameplay/creaking_active")
                    .forGetter(g -> Optional.ofNullable(g.creakingActive()))
    ).apply(instance, (musicVolume, fireflyBushSounds, canStartRaid, canPillagerPatrolSpawn, waterEvaporates,
                       bedRule, strawBedRule, respawnAnchorWorks, netherPortalSpawnsPiglin, fastLava,
                       increasedFireBurnout, eyeblossomOpen, turtleEggHatchChance, piglinsZombify, snowGolemMelts,
                       creakingActive) ->
            new GameplayPartA(
                    musicVolume.orElse(null),
                    fireflyBushSounds.orElse(null),
                    canStartRaid.orElse(null),
                    canPillagerPatrolSpawn.orElse(null),
                    waterEvaporates.orElse(null),
                    bedRule.orElse(null),
                    strawBedRule.orElse(null),
                    respawnAnchorWorks.orElse(null),
                    netherPortalSpawnsPiglin.orElse(null),
                    fastLava.orElse(null),
                    increasedFireBurnout.orElse(null),
                    eyeblossomOpen.orElse(null),
                    turtleEggHatchChance.orElse(null),
                    piglinsZombify.orElse(null),
                    snowGolemMelts.orElse(null),
                    creakingActive.orElse(null))));

    private static final MapCodec<GameplayPartB> GAMEPLAY_B = RecordCodecBuilder.mapCodec(instance -> instance.group(
            modifiable(Codec.FLOAT).optionalFieldOf("gameplay/surface_slime_spawn_chance")
                    .forGetter(g -> Optional.ofNullable(g.surfaceSlimeSpawnChance())),
            modifiable(Codec.FLOAT).optionalFieldOf("gameplay/cat_waking_up_gift_chance")
                    .forGetter(g -> Optional.ofNullable(g.catWakingUpGiftChance())),
            modifiable(Codec.BOOL).optionalFieldOf("gameplay/bees_stay_in_hive")
                    .forGetter(g -> Optional.ofNullable(g.beesStayInHive())),
            modifiable(Codec.BOOL).optionalFieldOf("gameplay/monsters_burn")
                    .forGetter(g -> Optional.ofNullable(g.monstersBurn())),
            modifiable(Codec.FLOAT).optionalFieldOf("gameplay/creature_world_gen_spawn_probability")
                    .forGetter(g -> Optional.ofNullable(g.creatureWorldGenSpawnProbability())),
            modifiable(CommonCodecs.KEY_CODEC).optionalFieldOf("gameplay/villager_activity")
                    .forGetter(g -> Optional.ofNullable(g.villagerActivity())),
            modifiable(CommonCodecs.KEY_CODEC).optionalFieldOf("gameplay/baby_villager_activity")
                    .forGetter(g -> Optional.ofNullable(g.babyVillagerActivity())),
            modifiable(Codec.FLOAT).optionalFieldOf("gameplay/sky_light_level")
                    .forGetter(g -> Optional.ofNullable(g.skyLightLevel()))
    ).apply(instance, (surfaceSlimeSpawnChance, catWakingUpGiftChance, beesStayInHive, monstersBurn,
                       creatureWorldGenSpawnProbability, villagerActivity, babyVillagerActivity, skyLightLevel) ->
            new GameplayPartB(
                    surfaceSlimeSpawnChance.orElse(null),
                    catWakingUpGiftChance.orElse(null),
                    beesStayInHive.orElse(null),
                    monstersBurn.orElse(null),
                    creatureWorldGenSpawnProbability.orElse(null),
                    villagerActivity.orElse(null),
                    babyVillagerActivity.orElse(null),
                    skyLightLevel.orElse(null))));

    public static final Codec<EnvironmentAttributes> ATTRIBUTES = RecordCodecBuilder.create(instance -> instance.group(
            VISUAL_A.forGetter(a -> new VisualPartA(
                    a.fogColor(), a.fogStartDistance(), a.fogEndDistance(), a.skyFogEndDistance(),
                    a.cloudFogEndDistance(), a.skyColor(), a.sunriseSunsetColor(), a.cloudColor(), a.cloudHeight(),
                    a.sunAngle(), a.moonAngle(), a.starAngle())),
            VISUAL_B.forGetter(a -> new VisualPartB(
                    a.moonPhase(), a.starBrightness(), a.blockLightTint(), a.skyLightColor(), a.skyLightFactor(),
                    a.nightVisionColor(), a.ambientLightColor(), a.defaultDripstoneParticle(), a.waterFogColor(),
                    a.waterFogStartDistance(), a.waterFogEndDistance())),
            GAMEPLAY_A.forGetter(a -> new GameplayPartA(
                    a.musicVolume(), a.fireflyBushSounds(), a.canStartRaid(), a.canPillagerPatrolSpawn(),
                    a.waterEvaporates(), a.bedRule(), a.strawBedRule(), a.respawnAnchorWorks(),
                    a.netherPortalSpawnsPiglin(), a.fastLava(), a.increasedFireBurnout(), a.eyeblossomOpen(),
                    a.turtleEggHatchChance(), a.piglinsZombify(), a.snowGolemMelts(), a.creakingActive())),
            GAMEPLAY_B.forGetter(a -> new GameplayPartB(
                    a.surfaceSlimeSpawnChance(), a.catWakingUpGiftChance(), a.beesStayInHive(), a.monstersBurn(),
                    a.creatureWorldGenSpawnProbability(), a.villagerActivity(), a.babyVillagerActivity(),
                    a.skyLightLevel())),
            AMBIENT_PARTICLE.listOf().optionalFieldOf("visual/ambient_particles", List.of())
                    .forGetter(EnvironmentAttributes::ambientParticles),
            AMBIENT_SOUNDS.optionalFieldOf("audio/ambient_sounds")
                    .forGetter(a -> Optional.ofNullable(a.ambientSounds())),
            BACKGROUND_MUSIC.optionalFieldOf("audio/background_music")
                    .forGetter(a -> Optional.ofNullable(a.backgroundMusic()))
    ).apply(instance, (visualA, visualB, gameplayA, gameplayB, ambientParticles, ambientSounds, backgroundMusic) ->
            new EnvironmentAttributes(
                    visualA.fogColor(),
                    visualA.fogStartDistance(),
                    visualA.fogEndDistance(),
                    visualA.skyFogEndDistance(),
                    visualA.cloudFogEndDistance(),
                    visualA.skyColor(),
                    visualA.sunriseSunsetColor(),
                    visualA.cloudColor(),
                    visualA.cloudHeight(),
                    visualA.sunAngle(),
                    visualA.moonAngle(),
                    visualA.starAngle(),
                    visualB.moonPhase(),
                    visualB.starBrightness(),
                    visualB.blockLightTint(),
                    visualB.skyLightColor(),
                    visualB.skyLightFactor(),
                    visualB.nightVisionColor(),
                    visualB.ambientLightColor(),
                    visualB.defaultDripstoneParticle(),
                    visualB.waterFogColor(),
                    visualB.waterFogStartDistance(),
                    visualB.waterFogEndDistance(),
                    gameplayA.musicVolume(),
                    gameplayA.fireflyBushSounds(),
                    gameplayA.canStartRaid(),
                    gameplayA.canPillagerPatrolSpawn(),
                    gameplayA.waterEvaporates(),
                    gameplayA.bedRule(),
                    gameplayA.strawBedRule(),
                    gameplayA.respawnAnchorWorks(),
                    gameplayA.netherPortalSpawnsPiglin(),
                    gameplayA.fastLava(),
                    gameplayA.increasedFireBurnout(),
                    gameplayA.eyeblossomOpen(),
                    gameplayA.turtleEggHatchChance(),
                    gameplayA.piglinsZombify(),
                    gameplayA.snowGolemMelts(),
                    gameplayA.creakingActive(),
                    gameplayB.surfaceSlimeSpawnChance(),
                    gameplayB.catWakingUpGiftChance(),
                    gameplayB.beesStayInHive(),
                    gameplayB.monstersBurn(),
                    gameplayB.creatureWorldGenSpawnProbability(),
                    gameplayB.villagerActivity(),
                    gameplayB.babyVillagerActivity(),
                    gameplayB.skyLightLevel(),
                    ambientParticles,
                    ambientSounds.orElse(null),
                    backgroundMusic.orElse(null))));

    private EnvironmentAttributeCodecs() {
        throw new UnsupportedOperationException("EnvironmentAttributeCodecs cannot be instantiated.");
    }

    public static <T> Codec<Attribute<T>> modifiable(final Codec<T> value) {
        final Codec<Attribute<T>> expanded = RecordCodecBuilder.create(instance -> instance.group(
                MODIFIER.fieldOf("modifier").forGetter(Attribute::modifier),
                value.fieldOf("argument").forGetter(Attribute::value)
        ).apply(instance, (modifier, argument) -> new Attribute<>(argument, modifier)));

        return Codec.either(value, expanded).xmap(
                either -> either.map(Attribute::of, attribute -> attribute),
                attribute -> attribute.modifier() == Modifier.OVERRIDE
                        ? Either.left(attribute.value())
                        : Either.right(attribute));
    }
}
