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
import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.world.World;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.ApiStatus;

import java.time.Duration;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Modeled after Paper's <a href="https://github.com/PaperMC/Paper/blob/main/paper-api/src/main/java/io/papermc/paper/command/brigadier/argument/VanillaArgumentProvider.java">ArgumentTypes</a>
 * Originally contributed in <a href="https://github.com/PaperMC/Paper/pull/8235">#8235</a>, licensed under the MIT license.
 *
 * @since 0.1.0
 */
@ApiStatus.Internal
public interface ArgumentProvider {

    Optional<ArgumentProvider> PROVIDER = ServiceLoader.load(ArgumentProvider.class, ArgumentProvider.class.getClassLoader()).findFirst();

    /**
     * @since 0.1.0
     */
    static ArgumentProvider provider() {
        return PROVIDER.orElseThrow();
    }

    /**
     * @since 0.1.0
     */
    ArgumentType<EntitySelectorArgumentResolver> entity();

    /**
     * @since 0.1.0
     */
    ArgumentType<EntitySelectorArgumentResolver> entity(Predicate<Entity> filter);

    /**
     * @since 0.1.0
     */
    ArgumentType<PlayerSelectorArgumentResolver> player();

    /**
     * @since 0.1.0
     */
    ArgumentType<PlayerSelectorArgumentResolver> player(Predicate<Player> filter);

    /**
     * @since 0.1.0
     */
    ArgumentType<EntitySelectorArgumentResolver> entities();

    /**
     * @since 0.1.0
     */
    ArgumentType<EntitySelectorArgumentResolver> entities(Predicate<Entity> filter);

    /**
     * @since 0.1.0
     */
    ArgumentType<PlayerSelectorArgumentResolver> players();

    /**
     * @since 0.1.0
     */
    ArgumentType<PlayerSelectorArgumentResolver> players(Predicate<Player> filter);

    /**
     * @since 0.1.0
     */
    ArgumentType<BlockPosResolver> blockPosition();

    /**
     * @since 0.1.0
     */
    ArgumentType<AngleResolver> angle();

    /**
     * @since 0.1.0
     */
    ArgumentType<ItemStack> itemStack();

    /**
     * @since 0.1.0
     */
    ArgumentType<ItemStackPredicate> itemStackPredicate();

    /**
     * @since 0.1.0
     */
    ArgumentType<NamedTextColor> namedColor();

    /**
     * @since 0.1.0
     */
    ArgumentType<TextColor> hexColor();

    /**
     * @since 0.1.0
     */
    ArgumentType<BossBar.Color> bossBarColor();

    /**
     * @since 0.1.0
     */
    ArgumentType<BossBar.Overlay> bossBarOverlay();

    /**
     * @since 0.1.0
     */
    ArgumentType<BossBar.Flag> bossBarFlag();

    /**
     * @since 0.1.0
     */
    ArgumentType<Component> component();

    /**
     * @since 0.1.0
     */
    ArgumentType<Style> style();

    /**
     * @since 0.1.0
     */
    ArgumentType<Key> key();

    /**
     * @since 0.1.0
     */
    ArgumentType<String> word();

    /**
     * @since 0.1.0
     */
    ArgumentType<String> string();

    /**
     * @since 0.1.0
     */
    ArgumentType<String> greedyString();

    /**
     * @since 0.1.0
     */
    ArgumentType<Boolean> bool();

    /**
     * @since 0.1.0
     */
    ArgumentType<Integer> integer(int min, int max);

    /**
     * @since 0.1.0
     */
    ArgumentType<Long> longArg(long min, long max);

    /**
     * @since 0.1.0
     */
    ArgumentType<Float> floatArg(float min, float max);

    /**
     * @since 0.1.0
     */
    ArgumentType<Double> doubleArg(double min, double max);

    /**
     * @since 0.1.0
     */
    ArgumentType<IntegerRangeProvider> integerRange();

    /**
     * @since 0.1.0
     */
    ArgumentType<DoubleRangeProvider> doubleRange();

    /**
     * @since 0.1.0
     */
    ArgumentType<World> world();

    /**
     * @since 0.1.0
     */
    ArgumentType<GameMode> gameMode();

    /**
     * @since 0.1.0
     */
    ArgumentType<UUID> uuid();

    /**
     * @since 0.1.0
     */
    ArgumentType<Integer> time(int minTicks);

     /**
     * @since 0.1.0
     */
    ArgumentType<Duration> duration();

    /**
     * @since 0.1.0
     */
    <T> ArgumentType<TypedKey<T>> resourceKey(RegistryKey<T> registryKey);

    /**
     * @since 0.1.0
     */
    <T> ArgumentType<T> resource(RegistryKey<T> registryKey);

    /**
     * @since 0.1.0
     */
    <T> ArgumentType<T> serverResource(RegistryKey<T> registryKey);

    /**
     * @since 0.1.0
     */
    ArgumentType<PlayerProfileListResolver> playerProfiles();

    /**
     * @since 0.1.0
     */
    ArgumentType<PlayerProfileListResolver> playerProfiles(Predicate<Player> filter);

    //ArgumentType<BlockState> blockState();

    /**
     * @since 0.1.0
     */
    ArgumentType<PositionResolver> position();

    /**
     * @since 0.1.0
     */
    <N, T> ArgumentType<T> map(ArgumentType<N> nativeType, ArgumentMapper<N, T> mapper);

    /**
     * @since 0.1.0
     */
    <N, T> ArgumentType<T> map(ArgumentType<N> nativeType, ArgumentMapper<N, T> mapper, SuggestionProvider<CommandSource> suggestions);

    /**
     * @since 0.1.0
     */
    <T> ArgumentType<T> withServerSuggestions(ArgumentType<T> type);

    /**
     * @since 0.1.0
     */
    ArgumentType<NbtPathResolver> nbtPath();
}
