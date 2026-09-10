package fr.fidorial.registry.keys;

import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.data.DecoratedPotPattern;
import java.util.List;
import java.util.stream.Stream;
import net.kyori.adventure.key.KeyPattern;

/**
 * Typed keys for entries in the {@code minecraft:decorated_pot_pattern} registry.
 */
public final class DecoratedPotPatternKeys {
    /**
     * Key for {@code minecraft:angler}.
     */
    public static final TypedKey<DecoratedPotPattern> ANGLER = create("angler");

    /**
     * Key for {@code minecraft:archer}.
     */
    public static final TypedKey<DecoratedPotPattern> ARCHER = create("archer");

    /**
     * Key for {@code minecraft:arms_up}.
     */
    public static final TypedKey<DecoratedPotPattern> ARMS_UP = create("arms_up");

    /**
     * Key for {@code minecraft:blade}.
     */
    public static final TypedKey<DecoratedPotPattern> BLADE = create("blade");

    /**
     * Key for {@code minecraft:brewer}.
     */
    public static final TypedKey<DecoratedPotPattern> BREWER = create("brewer");

    /**
     * Key for {@code minecraft:burn}.
     */
    public static final TypedKey<DecoratedPotPattern> BURN = create("burn");

    /**
     * Key for {@code minecraft:danger}.
     */
    public static final TypedKey<DecoratedPotPattern> DANGER = create("danger");

    /**
     * Key for {@code minecraft:explorer}.
     */
    public static final TypedKey<DecoratedPotPattern> EXPLORER = create("explorer");

    /**
     * Key for {@code minecraft:flow}.
     */
    public static final TypedKey<DecoratedPotPattern> FLOW = create("flow");

    /**
     * Key for {@code minecraft:friend}.
     */
    public static final TypedKey<DecoratedPotPattern> FRIEND = create("friend");

    /**
     * Key for {@code minecraft:guster}.
     */
    public static final TypedKey<DecoratedPotPattern> GUSTER = create("guster");

    /**
     * Key for {@code minecraft:heart}.
     */
    public static final TypedKey<DecoratedPotPattern> HEART = create("heart");

    /**
     * Key for {@code minecraft:heartbreak}.
     */
    public static final TypedKey<DecoratedPotPattern> HEARTBREAK = create("heartbreak");

    /**
     * Key for {@code minecraft:howl}.
     */
    public static final TypedKey<DecoratedPotPattern> HOWL = create("howl");

    /**
     * Key for {@code minecraft:miner}.
     */
    public static final TypedKey<DecoratedPotPattern> MINER = create("miner");

    /**
     * Key for {@code minecraft:mourner}.
     */
    public static final TypedKey<DecoratedPotPattern> MOURNER = create("mourner");

    /**
     * Key for {@code minecraft:plenty}.
     */
    public static final TypedKey<DecoratedPotPattern> PLENTY = create("plenty");

    /**
     * Key for {@code minecraft:prize}.
     */
    public static final TypedKey<DecoratedPotPattern> PRIZE = create("prize");

    /**
     * Key for {@code minecraft:scrape}.
     */
    public static final TypedKey<DecoratedPotPattern> SCRAPE = create("scrape");

    /**
     * Key for {@code minecraft:sheaf}.
     */
    public static final TypedKey<DecoratedPotPattern> SHEAF = create("sheaf");

    /**
     * Key for {@code minecraft:shelter}.
     */
    public static final TypedKey<DecoratedPotPattern> SHELTER = create("shelter");

    /**
     * Key for {@code minecraft:skull}.
     */
    public static final TypedKey<DecoratedPotPattern> SKULL = create("skull");

    /**
     * Key for {@code minecraft:snort}.
     */
    public static final TypedKey<DecoratedPotPattern> SNORT = create("snort");

    private static final List<TypedKey<DecoratedPotPattern>> VALUES = List.of(
        ANGLER,
        ARCHER,
        ARMS_UP,
        BLADE,
        BREWER,
        BURN,
        DANGER,
        EXPLORER,
        FLOW,
        FRIEND,
        GUSTER,
        HEART,
        HEARTBREAK,
        HOWL,
        MINER,
        MOURNER,
        PLENTY,
        PRIZE,
        SCRAPE,
        SHEAF,
        SHELTER,
        SKULL,
        SNORT
    );

    private DecoratedPotPatternKeys() {
        throw new UnsupportedOperationException("DecoratedPotPatternKeys cannot be instantiated.");
    }

    private static TypedKey<DecoratedPotPattern> create(@KeyPattern final String value) {
        return TypedKey.create(RegistryKey.DECORATED_POT_PATTERN, value);
    }

    /**
     * Returns a stream containing all keys declared by this class.
     *
     * @return a stream of registry keys
     */
    public static Stream<TypedKey<DecoratedPotPattern>> values() {
        return VALUES.stream();
    }
}
