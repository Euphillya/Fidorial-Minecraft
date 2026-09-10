package fr.fidorial.world.environment;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.util.TriState;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @param fogColor                          distance fog color, packed RGB — {@code minecraft:visual/fog_color}
 * @param fogStartDistance                  distance in blocks at which fog begins — {@code minecraft:visual/fog_start_distance}
 * @param fogEndDistance                     distance in blocks at which fog reaches full density — {@code minecraft:visual/fog_end_distance}
 * @param skyFogEndDistance                 distance in blocks at which sky-visible fog reaches full density — {@code minecraft:visual/sky_fog_end_distance}
 * @param cloudFogEndDistance               distance in blocks at which cloud-visible fog reaches full density — {@code minecraft:visual/cloud_fog_end_distance}
 * @param skyColor                          sky color, packed RGB — {@code minecraft:visual/sky_color}
 * @param sunriseSunsetColor                sunrise/sunset gradient color, packed ARGB — {@code minecraft:visual/sunrise_sunset_color}
 * @param cloudColor                        cloud color, packed ARGB — {@code minecraft:visual/cloud_color}
 * @param cloudHeight                       height, in blocks, at which clouds render — {@code minecraft:visual/cloud_height}
 * @param sunAngle                          sun angle offset, in degrees — {@code minecraft:visual/sun_angle}
 * @param moonAngle                         moon angle offset, in degrees — {@code minecraft:visual/moon_angle}
 * @param starAngle                         star angle offset, in degrees — {@code minecraft:visual/star_angle}
 * @param moonPhase                         moon phase index, within {@code [0, 7]} — {@code minecraft:visual/moon_phase}
 * @param starBrightness                    star brightness, within {@code [0, 1]} — {@code minecraft:visual/star_brightness}
 * @param blockLightTint                    tint applied to block light, packed RGB — {@code minecraft:visual/block_light_tint}
 * @param skyLightColor                     sky light color, packed RGB — {@code minecraft:visual/sky_light_color}
 * @param skyLightFactor                    multiplier applied to sky light, within {@code [0, 1]} — {@code minecraft:visual/sky_light_factor}
 * @param nightVisionColor                  tint applied by Night Vision, packed RGB — {@code minecraft:visual/night_vision_color}
 * @param ambientLightColor                 ambient light color, packed RGB — {@code minecraft:visual/ambient_light_color}
 * @param defaultDripstoneParticle          particle used by dripping dripstone — {@code minecraft:visual/default_dripstone_particle}
 * @param waterFogColor                     underwater fog color, packed RGB — {@code minecraft:visual/water_fog_color}
 * @param waterFogStartDistance             distance in blocks at which underwater fog begins — {@code minecraft:visual/water_fog_start_distance}
 * @param waterFogEndDistance               distance in blocks at which underwater fog reaches full density — {@code minecraft:visual/water_fog_end_distance}
 * @param musicVolume                       volume music fades to, within {@code [0, 1]} — {@code minecraft:audio/music_volume}
 * @param fireflyBushSounds                 whether firefly bushes emit ambient sound — {@code minecraft:audio/firefly_bush_sounds}
 * @param canStartRaid                      whether a raid can begin here — {@code minecraft:gameplay/can_start_raid}
 * @param canPillagerPatrolSpawn            whether pillager patrols spawn here — {@code minecraft:gameplay/can_pillager_patrol_spawn}
 * @param waterEvaporates                   whether placed water evaporates — {@code minecraft:gameplay/water_evaporates}
 * @param bedRule                           when beds are usable to sleep/set spawn — {@code minecraft:gameplay/bed_rule}
 * @param strawBedRule                      when straw beds are usable to sleep/set spawn — {@code minecraft:gameplay/straw_bed_rule}
 * @param respawnAnchorWorks                whether respawn anchors set spawn instead of exploding — {@code minecraft:gameplay/respawn_anchor_works}
 * @param netherPortalSpawnsPiglin          whether entering a nether portal can spawn a piglin — {@code minecraft:gameplay/nether_portal_spawns_piglin}
 * @param fastLava                          whether lava flows and spreads at Nether speed — {@code minecraft:gameplay/fast_lava}
 * @param increasedFireBurnout              whether fire burns out faster — {@code minecraft:gameplay/increased_fire_burnout}
 * @param eyeblossomOpen                    whether eyeblossoms are forced open/closed, or left to their default day cycle — {@code minecraft:gameplay/eyeblossom_open}
 * @param turtleEggHatchChance              chance a turtle egg hatches per random tick, within {@code [0, 1]} — {@code minecraft:gameplay/turtle_egg_hatch_chance}
 * @param piglinsZombify                    whether piglins and hoglins zombify — {@code minecraft:gameplay/piglins_zombify}
 * @param snowGolemMelts                    whether snow golems take damage — {@code minecraft:gameplay/snow_golem_melts}
 * @param creakingActive                    whether creakings are active — {@code minecraft:gameplay/creaking_active}
 * @param surfaceSlimeSpawnChance           chance a slime spawns on the surface, within {@code [0, 1]} — {@code minecraft:gameplay/surface_slime_spawn_chance}
 * @param catWakingUpGiftChance             chance a cat gives a gift on waking up, within {@code [0, 1]} — {@code minecraft:gameplay/cat_waking_up_gift_chance}
 * @param beesStayInHive                    whether bees refuse to leave their hive — {@code minecraft:gameplay/bees_stay_in_hive}
 * @param monstersBurn                      whether monsters burn in daylight — {@code minecraft:gameplay/monsters_burn}
 * @param creatureWorldGenSpawnProbability  probability creatures spawn during world generation, within {@code [0, 1]} — {@code minecraft:gameplay/creature_world_gen_spawn_probability}
 * @param villagerActivity                  forced villager schedule activity — {@code minecraft:gameplay/villager_activity}
 * @param babyVillagerActivity              forced baby villager schedule activity — {@code minecraft:gameplay/baby_villager_activity}
 * @param skyLightLevel                     sky light level, within {@code [0, 15]} — {@code minecraft:gameplay/sky_light_level}
 * @param ambientParticles                  particles randomly spawned around the camera, possibly empty — {@code minecraft:visual/ambient_particles}
 * @param ambientSounds                      looping, mood and additions sounds — {@code minecraft:audio/ambient_sounds}
 * @param backgroundMusic                    music tracks — {@code minecraft:audio/background_music}
 * @since 0.1.0
 */
public record EnvironmentAttributes(
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
        @Nullable Attribute<Float> starAngle,
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
        @Nullable Attribute<Float> waterFogEndDistance,
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
        @Nullable Attribute<Boolean> creakingActive,
        @Nullable Attribute<Float> surfaceSlimeSpawnChance,
        @Nullable Attribute<Float> catWakingUpGiftChance,
        @Nullable Attribute<Boolean> beesStayInHive,
        @Nullable Attribute<Boolean> monstersBurn,
        @Nullable Attribute<Float> creatureWorldGenSpawnProbability,
        @Nullable Attribute<Key> villagerActivity,
        @Nullable Attribute<Key> babyVillagerActivity,
        @Nullable Attribute<Float> skyLightLevel,
        List<AmbientParticle> ambientParticles,
        @Nullable AmbientSounds ambientSounds,
        @Nullable BackgroundMusic backgroundMusic
) {

    /**
     * Sets nothing, letting the dimension and the vanilla defaults decide everything.
     */
    public static final EnvironmentAttributes EMPTY = builder().build();

    public EnvironmentAttributes {
        ambientParticles = List.copyOf(ambientParticles);
    }

    /**
     * {@return a new, empty builder}
     */
    @Contract(value = "-> new", pure = true)
    public static Builder builder() {
        return new Builder();
    }

    /**
     * {@return a new builder pre-filled with the values of {@code attributes}}
     *
     * @param attributes the attributes to copy
     */
    @Contract(value = "_ -> new", pure = true)
    public static Builder builder(final EnvironmentAttributes attributes) {
        return new Builder(attributes);
    }

    /**
     * Computes the sky color the vanilla generator derives from a given temperature.
     *
     * @param temperature the biome temperature
     * @return the packed RGB sky color
     */
    public static int skyColorFor(final float temperature) {
        final float scaled = Math.clamp(temperature / 3F, -1F, 1F);
        return Color.HSBtoRGB(0.62222224F - scaled * 0.05F, 0.5F + scaled * 0.1F, 1.0F) & 0xFFFFFF;
    }

    /**
     * {@return whether this sets no attribute at all}
     */
    public boolean isEmpty() {
        return EMPTY.equals(this);
    }

    /**
     * Mutable builder for {@link EnvironmentAttributes}.
     *
     * @since 0.1.0
     */
    public static final class Builder {
        private final List<AmbientParticle> ambientParticles = new ArrayList<>();
        private @Nullable Attribute<Integer> fogColor;
        private @Nullable Attribute<Float> fogStartDistance;
        private @Nullable Attribute<Float> fogEndDistance;
        private @Nullable Attribute<Float> skyFogEndDistance;
        private @Nullable Attribute<Float> cloudFogEndDistance;
        private @Nullable Attribute<Integer> skyColor;
        private @Nullable Attribute<Integer> sunriseSunsetColor;
        private @Nullable Attribute<Integer> cloudColor;
        private @Nullable Attribute<Float> cloudHeight;
        private @Nullable Attribute<Float> sunAngle;
        private @Nullable Attribute<Float> moonAngle;
        private @Nullable Attribute<Float> starAngle;
        private @Nullable Attribute<Integer> moonPhase;
        private @Nullable Attribute<Float> starBrightness;
        private @Nullable Attribute<Integer> blockLightTint;
        private @Nullable Attribute<Integer> skyLightColor;
        private @Nullable Attribute<Float> skyLightFactor;
        private @Nullable Attribute<Integer> nightVisionColor;
        private @Nullable Attribute<Integer> ambientLightColor;
        private @Nullable Attribute<Key> defaultDripstoneParticle;
        private @Nullable Attribute<Integer> waterFogColor;
        private @Nullable Attribute<Float> waterFogStartDistance;
        private @Nullable Attribute<Float> waterFogEndDistance;
        private @Nullable Attribute<Float> musicVolume;
        private @Nullable Attribute<Boolean> fireflyBushSounds;
        private @Nullable Attribute<Boolean> canStartRaid;
        private @Nullable Attribute<Boolean> canPillagerPatrolSpawn;
        private @Nullable Attribute<Boolean> waterEvaporates;
        private @Nullable Attribute<BedRule> bedRule;
        private @Nullable Attribute<BedRule> strawBedRule;
        private @Nullable Attribute<Boolean> respawnAnchorWorks;
        private @Nullable Attribute<Boolean> netherPortalSpawnsPiglin;
        private @Nullable Attribute<Boolean> fastLava;
        private @Nullable Attribute<Boolean> increasedFireBurnout;
        private @Nullable Attribute<TriState> eyeblossomOpen;
        private @Nullable Attribute<Float> turtleEggHatchChance;
        private @Nullable Attribute<Boolean> piglinsZombify;
        private @Nullable Attribute<Boolean> snowGolemMelts;
        private @Nullable Attribute<Boolean> creakingActive;
        private @Nullable Attribute<Float> surfaceSlimeSpawnChance;
        private @Nullable Attribute<Float> catWakingUpGiftChance;
        private @Nullable Attribute<Boolean> beesStayInHive;
        private @Nullable Attribute<Boolean> monstersBurn;
        private @Nullable Attribute<Float> creatureWorldGenSpawnProbability;
        private @Nullable Attribute<Key> villagerActivity;
        private @Nullable Attribute<Key> babyVillagerActivity;
        private @Nullable Attribute<Float> skyLightLevel;
        private @Nullable AmbientSounds ambientSounds;
        private @Nullable BackgroundMusic backgroundMusic;

        private Builder() {
        }

        private Builder(final EnvironmentAttributes attributes) {
            this.fogColor = attributes.fogColor;
            this.fogStartDistance = attributes.fogStartDistance;
            this.fogEndDistance = attributes.fogEndDistance;
            this.skyFogEndDistance = attributes.skyFogEndDistance;
            this.cloudFogEndDistance = attributes.cloudFogEndDistance;
            this.skyColor = attributes.skyColor;
            this.sunriseSunsetColor = attributes.sunriseSunsetColor;
            this.cloudColor = attributes.cloudColor;
            this.cloudHeight = attributes.cloudHeight;
            this.sunAngle = attributes.sunAngle;
            this.moonAngle = attributes.moonAngle;
            this.starAngle = attributes.starAngle;
            this.moonPhase = attributes.moonPhase;
            this.starBrightness = attributes.starBrightness;
            this.blockLightTint = attributes.blockLightTint;
            this.skyLightColor = attributes.skyLightColor;
            this.skyLightFactor = attributes.skyLightFactor;
            this.nightVisionColor = attributes.nightVisionColor;
            this.ambientLightColor = attributes.ambientLightColor;
            this.defaultDripstoneParticle = attributes.defaultDripstoneParticle;
            this.waterFogColor = attributes.waterFogColor;
            this.waterFogStartDistance = attributes.waterFogStartDistance;
            this.waterFogEndDistance = attributes.waterFogEndDistance;
            this.musicVolume = attributes.musicVolume;
            this.fireflyBushSounds = attributes.fireflyBushSounds;
            this.canStartRaid = attributes.canStartRaid;
            this.canPillagerPatrolSpawn = attributes.canPillagerPatrolSpawn;
            this.waterEvaporates = attributes.waterEvaporates;
            this.bedRule = attributes.bedRule;
            this.strawBedRule = attributes.strawBedRule;
            this.respawnAnchorWorks = attributes.respawnAnchorWorks;
            this.netherPortalSpawnsPiglin = attributes.netherPortalSpawnsPiglin;
            this.fastLava = attributes.fastLava;
            this.increasedFireBurnout = attributes.increasedFireBurnout;
            this.eyeblossomOpen = attributes.eyeblossomOpen;
            this.turtleEggHatchChance = attributes.turtleEggHatchChance;
            this.piglinsZombify = attributes.piglinsZombify;
            this.snowGolemMelts = attributes.snowGolemMelts;
            this.creakingActive = attributes.creakingActive;
            this.surfaceSlimeSpawnChance = attributes.surfaceSlimeSpawnChance;
            this.catWakingUpGiftChance = attributes.catWakingUpGiftChance;
            this.beesStayInHive = attributes.beesStayInHive;
            this.monstersBurn = attributes.monstersBurn;
            this.creatureWorldGenSpawnProbability = attributes.creatureWorldGenSpawnProbability;
            this.villagerActivity = attributes.villagerActivity;
            this.babyVillagerActivity = attributes.babyVillagerActivity;
            this.skyLightLevel = attributes.skyLightLevel;
            this.ambientParticles.addAll(attributes.ambientParticles);
            this.ambientSounds = attributes.ambientSounds;
            this.backgroundMusic = attributes.backgroundMusic;
        }

        /**
         * Sets {@code minecraft:visual/fog_color} as a plain override.
         *
         * @param value distance fog color, packed RGB, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder fogColor(final @Nullable Integer value) {
            this.fogColor = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/fog_color}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder fogColor(final Integer value, final Modifier modifier) {
            this.fogColor = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/fog_start_distance} as a plain override.
         *
         * @param value distance in blocks at which fog begins, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder fogStartDistance(final @Nullable Float value) {
            this.fogStartDistance = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/fog_start_distance}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder fogStartDistance(final Float value, final Modifier modifier) {
            this.fogStartDistance = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/fog_end_distance} as a plain override.
         *
         * @param value distance in blocks at which fog reaches full density, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder fogEndDistance(final @Nullable Float value) {
            this.fogEndDistance = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/fog_end_distance}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder fogEndDistance(final Float value, final Modifier modifier) {
            this.fogEndDistance = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/sky_fog_end_distance} as a plain override.
         *
         * @param value distance in blocks at which sky-visible fog reaches full density, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder skyFogEndDistance(final @Nullable Float value) {
            this.skyFogEndDistance = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/sky_fog_end_distance}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder skyFogEndDistance(final Float value, final Modifier modifier) {
            this.skyFogEndDistance = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/cloud_fog_end_distance} as a plain override.
         *
         * @param value distance in blocks at which cloud-visible fog reaches full density, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder cloudFogEndDistance(final @Nullable Float value) {
            this.cloudFogEndDistance = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/cloud_fog_end_distance}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder cloudFogEndDistance(final Float value, final Modifier modifier) {
            this.cloudFogEndDistance = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/sky_color} as a plain override.
         *
         * @param value sky color, packed RGB, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder skyColor(final @Nullable Integer value) {
            this.skyColor = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/sky_color}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder skyColor(final Integer value, final Modifier modifier) {
            this.skyColor = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/sunrise_sunset_color} as a plain override.
         *
         * @param value sunrise/sunset gradient color, packed ARGB, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder sunriseSunsetColor(final @Nullable Integer value) {
            this.sunriseSunsetColor = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/sunrise_sunset_color}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder sunriseSunsetColor(final Integer value, final Modifier modifier) {
            this.sunriseSunsetColor = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/cloud_color} as a plain override.
         *
         * @param value cloud color, packed ARGB, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder cloudColor(final @Nullable Integer value) {
            this.cloudColor = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/cloud_color}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder cloudColor(final Integer value, final Modifier modifier) {
            this.cloudColor = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/cloud_height} as a plain override.
         *
         * @param value height, in blocks, at which clouds render, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder cloudHeight(final @Nullable Float value) {
            this.cloudHeight = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/cloud_height}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder cloudHeight(final Float value, final Modifier modifier) {
            this.cloudHeight = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/sun_angle} as a plain override.
         *
         * @param value sun angle offset, in degrees, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder sunAngle(final @Nullable Float value) {
            this.sunAngle = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/sun_angle}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder sunAngle(final Float value, final Modifier modifier) {
            this.sunAngle = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/moon_angle} as a plain override.
         *
         * @param value moon angle offset, in degrees, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder moonAngle(final @Nullable Float value) {
            this.moonAngle = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/moon_angle}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder moonAngle(final Float value, final Modifier modifier) {
            this.moonAngle = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/star_angle} as a plain override.
         *
         * @param value star angle offset, in degrees, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder starAngle(final @Nullable Float value) {
            this.starAngle = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/star_angle}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder starAngle(final Float value, final Modifier modifier) {
            this.starAngle = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/moon_phase} as a plain override.
         *
         * @param value moon phase index, within {@code [0, 7]}, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder moonPhase(final @Nullable Integer value) {
            this.moonPhase = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/moon_phase}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder moonPhase(final Integer value, final Modifier modifier) {
            this.moonPhase = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/star_brightness} as a plain override.
         *
         * @param value star brightness, within {@code [0, 1]}, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder starBrightness(final @Nullable Float value) {
            this.starBrightness = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/star_brightness}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder starBrightness(final Float value, final Modifier modifier) {
            this.starBrightness = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/block_light_tint} as a plain override.
         *
         * @param value tint applied to block light, packed RGB, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder blockLightTint(final @Nullable Integer value) {
            this.blockLightTint = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/block_light_tint}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder blockLightTint(final Integer value, final Modifier modifier) {
            this.blockLightTint = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/sky_light_color} as a plain override.
         *
         * @param value sky light color, packed RGB, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder skyLightColor(final @Nullable Integer value) {
            this.skyLightColor = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/sky_light_color}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder skyLightColor(final Integer value, final Modifier modifier) {
            this.skyLightColor = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/sky_light_factor} as a plain override.
         *
         * @param value multiplier applied to sky light, within {@code [0, 1]}, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder skyLightFactor(final @Nullable Float value) {
            this.skyLightFactor = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/sky_light_factor}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder skyLightFactor(final Float value, final Modifier modifier) {
            this.skyLightFactor = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/night_vision_color} as a plain override.
         *
         * @param value tint applied by Night Vision, packed RGB, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder nightVisionColor(final @Nullable Integer value) {
            this.nightVisionColor = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/night_vision_color}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder nightVisionColor(final Integer value, final Modifier modifier) {
            this.nightVisionColor = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/ambient_light_color} as a plain override.
         *
         * @param value ambient light color, packed RGB, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder ambientLightColor(final @Nullable Integer value) {
            this.ambientLightColor = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/ambient_light_color}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder ambientLightColor(final Integer value, final Modifier modifier) {
            this.ambientLightColor = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/default_dripstone_particle} as a plain override.
         *
         * @param value particle used by dripping dripstone, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder defaultDripstoneParticle(final @Nullable Key value) {
            this.defaultDripstoneParticle = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/default_dripstone_particle}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder defaultDripstoneParticle(final Key value, final Modifier modifier) {
            this.defaultDripstoneParticle = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/water_fog_color} as a plain override.
         *
         * @param value underwater fog color, packed RGB, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder waterFogColor(final @Nullable Integer value) {
            this.waterFogColor = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/water_fog_color}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder waterFogColor(final Integer value, final Modifier modifier) {
            this.waterFogColor = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/water_fog_start_distance} as a plain override.
         *
         * @param value distance in blocks at which underwater fog begins, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder waterFogStartDistance(final @Nullable Float value) {
            this.waterFogStartDistance = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/water_fog_start_distance}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder waterFogStartDistance(final Float value, final Modifier modifier) {
            this.waterFogStartDistance = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/water_fog_end_distance} as a plain override.
         *
         * @param value distance in blocks at which underwater fog reaches full density, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder waterFogEndDistance(final @Nullable Float value) {
            this.waterFogEndDistance = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:visual/water_fog_end_distance}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder waterFogEndDistance(final Float value, final Modifier modifier) {
            this.waterFogEndDistance = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:audio/music_volume} as a plain override.
         *
         * @param value volume music fades to, within {@code [0, 1]}, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder musicVolume(final @Nullable Float value) {
            this.musicVolume = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:audio/music_volume}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder musicVolume(final Float value, final Modifier modifier) {
            this.musicVolume = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:audio/firefly_bush_sounds} as a plain override.
         *
         * @param value whether firefly bushes emit ambient sound, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder fireflyBushSounds(final @Nullable Boolean value) {
            this.fireflyBushSounds = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:audio/firefly_bush_sounds}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder fireflyBushSounds(final Boolean value, final Modifier modifier) {
            this.fireflyBushSounds = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/can_start_raid} as a plain override.
         *
         * @param value whether a raid can begin here, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder canStartRaid(final @Nullable Boolean value) {
            this.canStartRaid = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/can_start_raid}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder canStartRaid(final Boolean value, final Modifier modifier) {
            this.canStartRaid = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/can_pillager_patrol_spawn} as a plain override.
         *
         * @param value whether pillager patrols spawn here, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder canPillagerPatrolSpawn(final @Nullable Boolean value) {
            this.canPillagerPatrolSpawn = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/can_pillager_patrol_spawn}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder canPillagerPatrolSpawn(final Boolean value, final Modifier modifier) {
            this.canPillagerPatrolSpawn = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/water_evaporates} as a plain override.
         *
         * @param value whether placed water evaporates, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder waterEvaporates(final @Nullable Boolean value) {
            this.waterEvaporates = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/water_evaporates}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder waterEvaporates(final Boolean value, final Modifier modifier) {
            this.waterEvaporates = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/bed_rule} as a plain override.
         *
         * @param value when beds are usable to sleep/set spawn, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder bedRule(final @Nullable BedRule value) {
            this.bedRule = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/bed_rule}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder bedRule(final BedRule value, final Modifier modifier) {
            this.bedRule = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/straw_bed_rule} as a plain override.
         *
         * @param value when straw beds are usable to sleep/set spawn, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder strawBedRule(final @Nullable BedRule value) {
            this.strawBedRule = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/straw_bed_rule}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder strawBedRule(final BedRule value, final Modifier modifier) {
            this.strawBedRule = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/respawn_anchor_works} as a plain override.
         *
         * @param value whether respawn anchors set spawn instead of exploding, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder respawnAnchorWorks(final @Nullable Boolean value) {
            this.respawnAnchorWorks = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/respawn_anchor_works}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder respawnAnchorWorks(final Boolean value, final Modifier modifier) {
            this.respawnAnchorWorks = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/nether_portal_spawns_piglin} as a plain override.
         *
         * @param value whether entering a nether portal can spawn a piglin, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder netherPortalSpawnsPiglin(final @Nullable Boolean value) {
            this.netherPortalSpawnsPiglin = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/nether_portal_spawns_piglin}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder netherPortalSpawnsPiglin(final Boolean value, final Modifier modifier) {
            this.netherPortalSpawnsPiglin = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/fast_lava} as a plain override.
         *
         * @param value whether lava flows and spreads at Nether speed, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder fastLava(final @Nullable Boolean value) {
            this.fastLava = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/fast_lava}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder fastLava(final Boolean value, final Modifier modifier) {
            this.fastLava = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/increased_fire_burnout} as a plain override.
         *
         * @param value whether fire burns out faster, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder increasedFireBurnout(final @Nullable Boolean value) {
            this.increasedFireBurnout = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/increased_fire_burnout}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder increasedFireBurnout(final Boolean value, final Modifier modifier) {
            this.increasedFireBurnout = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/eyeblossom_open} as a plain override.
         *
         * @param value whether eyeblossoms are forced open/closed, or {@link TriState#NOT_SET} to leave their default day cycle, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder eyeblossomOpen(final @Nullable TriState value) {
            this.eyeblossomOpen = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/eyeblossom_open}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder eyeblossomOpen(final TriState value, final Modifier modifier) {
            this.eyeblossomOpen = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/turtle_egg_hatch_chance} as a plain override.
         *
         * @param value chance a turtle egg hatches per random tick, within {@code [0, 1]}, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder turtleEggHatchChance(final @Nullable Float value) {
            this.turtleEggHatchChance = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/turtle_egg_hatch_chance}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder turtleEggHatchChance(final Float value, final Modifier modifier) {
            this.turtleEggHatchChance = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/piglins_zombify} as a plain override.
         *
         * @param value whether piglins and hoglins zombify, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder piglinsZombify(final @Nullable Boolean value) {
            this.piglinsZombify = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/piglins_zombify}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder piglinsZombify(final Boolean value, final Modifier modifier) {
            this.piglinsZombify = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/snow_golem_melts} as a plain override.
         *
         * @param value whether snow golems take damage, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder snowGolemMelts(final @Nullable Boolean value) {
            this.snowGolemMelts = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/snow_golem_melts}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder snowGolemMelts(final Boolean value, final Modifier modifier) {
            this.snowGolemMelts = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/creaking_active} as a plain override.
         *
         * @param value whether creakings are active, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder creakingActive(final @Nullable Boolean value) {
            this.creakingActive = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/creaking_active}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder creakingActive(final Boolean value, final Modifier modifier) {
            this.creakingActive = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/surface_slime_spawn_chance} as a plain override.
         *
         * @param value chance a slime spawns on the surface, within {@code [0, 1]}, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder surfaceSlimeSpawnChance(final @Nullable Float value) {
            this.surfaceSlimeSpawnChance = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/surface_slime_spawn_chance}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder surfaceSlimeSpawnChance(final Float value, final Modifier modifier) {
            this.surfaceSlimeSpawnChance = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/cat_waking_up_gift_chance} as a plain override.
         *
         * @param value chance a cat gives a gift on waking up, within {@code [0, 1]}, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder catWakingUpGiftChance(final @Nullable Float value) {
            this.catWakingUpGiftChance = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/cat_waking_up_gift_chance}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder catWakingUpGiftChance(final Float value, final Modifier modifier) {
            this.catWakingUpGiftChance = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/bees_stay_in_hive} as a plain override.
         *
         * @param value whether bees refuse to leave their hive, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder beesStayInHive(final @Nullable Boolean value) {
            this.beesStayInHive = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/bees_stay_in_hive}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder beesStayInHive(final Boolean value, final Modifier modifier) {
            this.beesStayInHive = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/monsters_burn} as a plain override.
         *
         * @param value whether monsters burn in daylight, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder monstersBurn(final @Nullable Boolean value) {
            this.monstersBurn = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/monsters_burn}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder monstersBurn(final Boolean value, final Modifier modifier) {
            this.monstersBurn = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/creature_world_gen_spawn_probability} as a plain override.
         *
         * @param value probability creatures spawn during world generation, within {@code [0, 1]}, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder creatureWorldGenSpawnProbability(final @Nullable Float value) {
            this.creatureWorldGenSpawnProbability = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/creature_world_gen_spawn_probability}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder creatureWorldGenSpawnProbability(final Float value, final Modifier modifier) {
            this.creatureWorldGenSpawnProbability = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/villager_activity} as a plain override.
         *
         * @param value forced villager schedule activity, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder villagerActivity(final @Nullable Key value) {
            this.villagerActivity = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/villager_activity}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder villagerActivity(final Key value, final Modifier modifier) {
            this.villagerActivity = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/baby_villager_activity} as a plain override.
         *
         * @param value forced baby villager schedule activity, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder babyVillagerActivity(final @Nullable Key value) {
            this.babyVillagerActivity = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/baby_villager_activity}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder babyVillagerActivity(final Key value, final Modifier modifier) {
            this.babyVillagerActivity = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/sky_light_level} as a plain override.
         *
         * @param value sky light level, within {@code [0, 15]}, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder skyLightLevel(final @Nullable Float value) {
            this.skyLightLevel = value == null ? null : Attribute.of(value);
            return this;
        }

        /**
         * Sets {@code minecraft:gameplay/sky_light_level}, deriving from the dimension's value.
         *
         * @param value    the modifier argument
         * @param modifier how to combine it
         * @return this builder
         */
        @Contract("_, _ -> this")
        public Builder skyLightLevel(final Float value, final Modifier modifier) {
            this.skyLightLevel = Attribute.of(value, modifier);
            return this;
        }

        /**
         * Adds one ambient particle to {@code minecraft:visual/ambient_particles}.
         *
         * @param particle the particle
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder addAmbientParticle(final AmbientParticle particle) {
            this.ambientParticles.add(Objects.requireNonNull(particle, "particle"));
            return this;
        }

        /**
         * Replaces the whole ambient particle list.
         *
         * @param particles the particles
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder ambientParticles(final List<AmbientParticle> particles) {
            this.ambientParticles.clear();
            this.ambientParticles.addAll(particles);
            return this;
        }

        /**
         * Sets {@code minecraft:audio/ambient_sounds}.
         *
         * @param value looping, mood and additions sounds, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder ambientSounds(final @Nullable AmbientSounds value) {
            this.ambientSounds = value;
            return this;
        }

        /**
         * Sets {@code minecraft:audio/background_music}.
         *
         * @param value music tracks, or {@code null} to leave unset
         * @return this builder
         */
        @Contract("_ -> this")
        public Builder backgroundMusic(final @Nullable BackgroundMusic value) {
            this.backgroundMusic = value;
            return this;
        }

        /**
         * {@return the immutable attributes described by this builder}
         */
        @Contract(value = "-> new", pure = true)
        public EnvironmentAttributes build() {
            return new EnvironmentAttributes(
                    fogColor,
                    fogStartDistance,
                    fogEndDistance,
                    skyFogEndDistance,
                    cloudFogEndDistance,
                    skyColor,
                    sunriseSunsetColor,
                    cloudColor,
                    cloudHeight,
                    sunAngle,
                    moonAngle,
                    starAngle,
                    moonPhase,
                    starBrightness,
                    blockLightTint,
                    skyLightColor,
                    skyLightFactor,
                    nightVisionColor,
                    ambientLightColor,
                    defaultDripstoneParticle,
                    waterFogColor,
                    waterFogStartDistance,
                    waterFogEndDistance,
                    musicVolume,
                    fireflyBushSounds,
                    canStartRaid,
                    canPillagerPatrolSpawn,
                    waterEvaporates,
                    bedRule,
                    strawBedRule,
                    respawnAnchorWorks,
                    netherPortalSpawnsPiglin,
                    fastLava,
                    increasedFireBurnout,
                    eyeblossomOpen,
                    turtleEggHatchChance,
                    piglinsZombify,
                    snowGolemMelts,
                    creakingActive,
                    surfaceSlimeSpawnChance,
                    catWakingUpGiftChance,
                    beesStayInHive,
                    monstersBurn,
                    creatureWorldGenSpawnProbability,
                    villagerActivity,
                    babyVillagerActivity,
                    skyLightLevel,
                    List.copyOf(ambientParticles),
                    ambientSounds,
                    backgroundMusic);
        }
    }
}
