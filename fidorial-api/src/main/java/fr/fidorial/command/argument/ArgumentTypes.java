package fr.fidorial.command.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import fr.fidorial.command.CommandSource;
import fr.fidorial.command.argument.custom.ArgumentMapper;
import fr.fidorial.command.argument.predicate.ItemStackPredicate;
import fr.fidorial.command.argument.range.DoubleRangeProvider;
import fr.fidorial.command.argument.range.IntegerRangeProvider;
import fr.fidorial.command.argument.resolvers.AngleResolver;
import fr.fidorial.command.argument.resolvers.BlockPosResolver;
import fr.fidorial.command.argument.resolvers.NbtPathResolver;
import fr.fidorial.command.argument.resolvers.PlayerProfileListResolver;
import fr.fidorial.command.argument.resolvers.PositionResolver;
import fr.fidorial.command.argument.resolvers.selector.EntitySelectorArgumentResolver;
import fr.fidorial.command.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import fr.fidorial.entity.Entity;
import fr.fidorial.entity.GameMode;
import fr.fidorial.entity.Player;
import fr.fidorial.item.ItemStack;
import fr.fidorial.registry.Registry;
import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.world.World;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;

import java.time.Duration;
import java.util.UUID;
import java.util.function.Predicate;

import static fr.fidorial.command.argument.ArgumentProvider.provider;

/**
 * Modeled after Paper's <a href="https://github.com/PaperMC/Paper/blob/main/paper-api/src/main/java/io/papermc/paper/command/brigadier/argument/ArgumentTypes.java">ArgumentTypes</a>
 * Originally contributed in <a href="https://github.com/PaperMC/Paper/pull/8235">#8235</a>, licensed under the MIT license.
 *
 * @since 0.1.0
 */
public final class ArgumentTypes {
    /**
     * Represents a selector that can capture any
     * single entity.
     *
     * @return argument that takes one entity
     * @since 0.1.0
     */
    public static ArgumentType<EntitySelectorArgumentResolver> entity() {
        return provider().entity();
    }

    /**
     * Represents a selector that can capture any single entity
     * matching the given predicate.
     *
     * @param filter predicate the matched entity must satisfy
     * @return argument that takes one entity
     * @since 0.1.0
     */
    public static ArgumentType<EntitySelectorArgumentResolver> entity(final Predicate<Entity> filter) {
        return provider().entity(filter);
    }

    /**
     * Represents a selector that can capture multiple
     * entities.
     *
     * @return argument that takes multiple entities
     * @since 0.1.0
     */
    public static ArgumentType<EntitySelectorArgumentResolver> entities() {
        return provider().entities();
    }

    /**
     * Represents a selector that can capture multiple entities
     * matching the given predicate.
     *
     * @param filter predicate each matched entity must satisfy
     * @return argument that takes multiple entities
     * @since 0.1.0
     */
    public static ArgumentType<EntitySelectorArgumentResolver> entities(final Predicate<Entity> filter) {
        return provider().entities(filter);
    }

    /**
     * Represents a selector that can capture a
     * singular player entity.
     *
     * @return argument that takes one player
     * @since 0.1.0
     */
    public static ArgumentType<PlayerSelectorArgumentResolver> player() {
        return provider().player();
    }

    /**
     * Represents a selector that can capture a singular player
     * entity matching the given predicate.
     *
     * @param filter predicate the matched player must satisfy
     * @return argument that takes one player
     * @since 0.1.0
     */
    public static ArgumentType<PlayerSelectorArgumentResolver> player(final Predicate<Player> filter) {
        return provider().player(filter);
    }

    /**
     * Represents a selector that can capture multiple
     * player entities.
     *
     * @return argument that takes multiple players
     * @since 0.1.0
     */
    public static ArgumentType<PlayerSelectorArgumentResolver> players() {
        return provider().players();
    }

    /**
     * Represents a selector that can capture multiple player
     * entities matching the given predicate.
     *
     * @param filter predicate each matched player must satisfy
     * @return argument that takes multiple players
     * @since 0.1.0
     */
    public static ArgumentType<PlayerSelectorArgumentResolver> players(final Predicate<Player> filter) {
        return provider().players(filter);
    }

    /**
     * A selector argument that provides a list
     * of player profiles.
     *
     * @return player profile argument
     * @since 0.1.0
     */
    public static ArgumentType<PlayerProfileListResolver> playerProfiles() {
        return provider().playerProfiles();
    }

    /**
     * A selector argument that provides a list
     * of player profiles matching the given predicate.
     *
     * @param filter predicate each matched player must satisfy
     * @return player profile argument
     * @since 0.1.0
     */
    public static ArgumentType<PlayerProfileListResolver> playerProfiles(final Predicate<Player> filter) {
        return provider().playerProfiles(filter);
    }

    /**
     * A block position argument.
     *
     * @return block position argument
     * @since 0.1.0
     */
    public static ArgumentType<BlockPosResolver> blockPosition() {
        return provider().blockPosition();
    }

    /**
     * An angle argument.
     *
     * @return angle argument
     * @since 0.1.0
     */
    public static ArgumentType<AngleResolver> angle() {
        return provider().angle();
    }

    /**
     * A block state argument which will provide rich parsing for specifying
     * the specific block variant and then the block entity NBT if applicable.
     *
     * @return argument
     */
    //public static ArgumentType<BlockState> blockState() {
    //return provider().blockState();
    //}

    /**
     * An ItemStack argument which provides rich parsing for
     * specifying item material and item NBT information.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<ItemStack> itemStack() {
        return provider().itemStack();
    }

    /**
     * An item predicate argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<ItemStackPredicate> itemPredicate() {
        return provider().itemStackPredicate();
    }

    /**
     * An argument for parsing {@link NamedTextColor}s.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<NamedTextColor> namedColor() {
        return provider().namedColor();
    }

    /**
     * A hex color argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<TextColor> hexColor() {
        return provider().hexColor();
    }

    /**
     * A boss bar color argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<BossBar.Color> bossBarColor() {
        return provider().bossBarColor();
    }

    /**
     * A boss bar overlay argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<BossBar.Overlay> bossBarOverlay() {
        return provider().bossBarOverlay();
    }

    /**
     * A boss bar flag argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<BossBar.Flag> bossBarFlag() {
        return provider().bossBarFlag();
    }

    /**
     * A component argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Component> component() {
        return provider().component();
    }

    /**
     * A key argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Key> key() {
        return provider().key();
    }

    /**
     * A style argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Style> style() {
        return provider().style();
    }

    /**
     * A boolean argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Boolean> bool() {
        return provider().bool();
    }

    /**
     * A single unquoted word string argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<String> word() {
        return provider().word();
    }

    /**
     * A string argument, either a single word or a quoted phrase.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<String> string() {
        return provider().string();
    }

    /**
     * A string argument that consumes the remainder of the input.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<String> greedyString() {
        return provider().greedyString();
    }

    /**
     * An unbounded integer argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Integer> integer() {
        return integer(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * An integer argument bounded between {@code min} and {@code max}, inclusive.
     *
     * @param min the minimum value, inclusive
     * @param max the maximum value, inclusive
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Integer> integer(final int min, final int max) {
        return provider().integer(min, max);
    }

    /**
     * An unbounded long argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Long> longArg() {
        return longArg(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * A long argument bounded between {@code min} and {@code max}, inclusive.
     *
     * @param min the minimum value, inclusive
     * @param max the maximum value, inclusive
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Long> longArg(final long min, final long max) {
        return provider().longArg(min, max);
    }

    /**
     * An unbounded float argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Float> floatArg() {
        return floatArg(-Float.MAX_VALUE, Float.MAX_VALUE);
    }

    /**
     * A float argument bounded between {@code min} and {@code max}, inclusive.
     *
     * @param min the minimum value, inclusive
     * @param max the maximum value, inclusive
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Float> floatArg(final float min, final float max) {
        return provider().floatArg(min, max);
    }

    /**
     * An unbounded double argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Double> doubleArg() {
        return doubleArg(-Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     * A double argument bounded between {@code min} and {@code max}, inclusive.
     *
     * @param min the minimum value, inclusive
     * @param max the maximum value, inclusive
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Double> doubleArg(final double min, final double max) {
        return provider().doubleArg(min, max);
    }

    /**
     * An inclusive range of integers that may be unbounded on either end.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<IntegerRangeProvider> integerRange() {
        return provider().integerRange();
    }

    /**
     * An inclusive range of doubles that may be unbounded on either end.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<DoubleRangeProvider> doubleRange() {
        return provider().doubleRange();
    }

    /**
     * A world argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<World> world() {
        return provider().world();
    }

    /**
     * A game mode argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<GameMode> gameMode() {
        return provider().gameMode();
    }

    /**
     * A uuid argument.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<UUID> uuid() {
        return provider().uuid();
    }

    /**
     * A time argument, returning the number of ticks.
     * <p>Examples:
     * <ul>
     * <li> "1d"
     * <li> "5s"
     * <li> "2"
     * <li> "6t"
     * </ul>
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Integer> time() {
        return time(0);
    }

    /**
     * A time argument, returning the number of ticks.
     * <p>Examples:
     * <ul>
     * <li> "1d"
     * <li> "5s"
     * <li> "2"
     * <li> "6t"
     * </ul>
     *
     * @param mintime The minimum time required for this argument.
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<Integer> time(final int mintime) {
        return provider().time(mintime);
    }

    /**
     * A duration argument, accepting a chain of amount/unit pairs.
     *
     * <p>The supported units are {@code w} (weeks), {@code d} (days), {@code h} (hours),
     * {@code m} (minutes) and {@code s} (seconds); a bare number is rejected, so that the unit is
     * always explicit. Examples:
     * <ul>
     * <li> "30m"
     * <li> "12h"
     * <li> "7d"
     * <li> "1w3d12h"
     * </ul>
     *
     * @return argument
     */
    public static ArgumentType<Duration> duration() {
        return provider().duration();
    }

    /**
     * An argument for a typed key for a {@link Registry}, without resolving
     * it against the registry at parse time. The referenced entry is not
     * required to exist — validation/resolution is deferred to whoever
     * consumes the argument.
     *
     * @param registryKey the registry's key
     * @return argument
     * @param <T> the registry value type
     * @since 0.1.0
     */
    public static <T> ArgumentType<TypedKey<T>> resourceKey(final RegistryKey<T> registryKey) {
        return provider().resourceKey(registryKey);
    }

    /**
     * An argument for a value from a {@link Registry}, resolved against
     * the registry at parse time. Parsing fails immediately if no entry
     * with the given key exists in the registry, and suggestions are
     * populated from the registry's current contents.
     *
     * @param registryKey the registry's key
     * @return argument
     * @param <T> the registry value type
     * @since 0.1.0
     */
    public static <T> ArgumentType<T> resource(final RegistryKey<T> registryKey) {
        return provider().resource(registryKey);
    }

    /**
     * An argument for a value from a {@link Registry}, resolved against
     * the server registry at parse time. Parsing fails immediately if no
     * entry with the given key exists in the registry, and suggestions
     * are requested from the server rather than computed by the client.
     *
     * @param registryKey the registry's key
     * @return argument
     * @param <T> the registry value type
     * @since 0.1.0
     * @apiNote This argument is sent to the client as a {@link #resourceKey(RegistryKey)} argument, so the client doesn't
     * participate in registry resolution and instead asks the server both to validate it and to supply suggestions.
     */
    public static <T> ArgumentType<T> serverResource(final RegistryKey<T> registryKey) {
        return provider().serverResource(registryKey);
    }

    /**
     * A 3D position argument, accepting absolute, relative ({@code ~}),
     * and mixed coordinates for x, y, and z.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<PositionResolver> position() {
        return provider().position();
    }

    /**
     * Reuses {@code nativeType}'s client-side grammar, highlighting, and suggestions,
     * converting the parsed value into a custom result type.
     *
     * @param nativeType the native type providing grammar and suggestions
     * @param mapper converts a parsed native value into the result type
     * @return argument
     * @param <N> the native value type
     * @param <T> the mapped result type
     * @since 0.1.0
     */
    public static <N, T> ArgumentType<T> map(final ArgumentType<N> nativeType, final ArgumentMapper<N, T> mapper) {
        return provider().map(nativeType, mapper);
    }

    /**
     * Reuses {@code nativeType}'s client-side grammar and highlighting, converting
     * the parsed value into a custom result type and replacing the native type's
     * suggestions with {@code suggestions}.
     * <p>
     * Use this when the native grammar fits but its default completions don't apply
     * to the mapped domain (e.g. mapping a plain {@code word()} into an enum, where
     * the enum's own values should be suggested instead of arbitrary words).
     *
     * @param nativeType the native type providing grammar and highlighting
     * @param mapper converts a parsed native value into the result type
     * @param suggestions replaces the native type's client-side suggestions
     * @return argument
     * @param <N> the native value type
     * @param <T> the mapped result type
     * @see #map(ArgumentType, ArgumentMapper)
     * @since 0.1.0
     */
    public static <N, T> ArgumentType<T> map(
            final ArgumentType<N> nativeType,
            final ArgumentMapper<N, T> mapper,
            final SuggestionProvider<CommandSource> suggestions
    ) {
        return provider().map(nativeType, mapper, suggestions);
    }

    /**
     * Wraps a custom {@link ArgumentType} so the client asks the server for
     * suggestions instead of relying on client-side vanilla grammar. Use this for
     * argument types with hand-rolled {@code parse}/{@code listSuggestions} logic.
     *
     * @param type the custom argument type
     * @return argument that forces server-side suggestions
     * @param <T> the parsed value type
     * @since 0.1.0
     */
    public static <T> ArgumentType<T> withServerSuggestions(final ArgumentType<T> type) {
        return provider().withServerSuggestions(type);
    }

    /**
     * An NBT path argument, resolvable against any root {@link BinaryTag}.
     *
     * @return argument
     * @since 0.1.0
     */
    public static ArgumentType<NbtPathResolver> nbtPath() {
        return provider().nbtPath();
    }
}
