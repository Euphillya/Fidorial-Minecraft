package fr.euphyllia.fidorial.server.command.brigadier.argument;

import com.google.common.collect.Range;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.command.brigadier.argument.bossbar.BossBarColorArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.bossbar.BossBarFlagArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.bossbar.BossBarOverlayArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.chat.ComponentArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.chat.HexColorArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.chat.NamedColorArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.chat.StyleArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.custom.ForcedSuggestionsArgumentType;
import fr.euphyllia.fidorial.server.command.brigadier.argument.custom.MappedArgumentType;
import fr.euphyllia.fidorial.server.command.brigadier.argument.entity.EntityArgumentInternal;
import fr.euphyllia.fidorial.server.command.brigadier.argument.entity.UuidArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.generic.DurationArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.generic.TimeArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.item.ItemArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.item.ItemPredicateArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.location.AngleArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.location.BlockPositionArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.location.DimensionArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.location.Vec3Argument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.nbt.NbtDataArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.player.GameModeArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.player.PlayerProfileArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.range.RangeArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.range.RangeBounds;
import fr.euphyllia.fidorial.server.command.brigadier.argument.resource.KeyArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.resource.ResourceArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.resource.ResourceKeyArgument;
import fr.fidorial.command.CommandSource;
import fr.fidorial.command.argument.ArgumentProvider;
import fr.fidorial.command.argument.custom.ArgumentMapper;
import fr.fidorial.command.argument.predicate.ItemStackPredicate;
import fr.fidorial.command.argument.range.DoubleRangeProvider;
import fr.fidorial.command.argument.range.IntegerRangeProvider;
import fr.fidorial.command.argument.range.RangeProvider;
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
import fr.fidorial.entity.PlayerProfile;
import fr.fidorial.item.ItemStack;
import fr.fidorial.registry.Registry;
import fr.fidorial.registry.RegistryKey;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.world.World;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public class ArgumentProviderImpl implements ArgumentProvider {

    @Override
    public ArgumentType<EntitySelectorArgumentResolver> entity() {
        return EntityArgumentInternal.entity(selector -> source -> List.of(selector.findSingleEntity(source)));
    }

    @Override
    public ArgumentType<EntitySelectorArgumentResolver> entity(final Predicate<Entity> filter) {
        return withServerSuggestions(EntityArgumentInternal.entity(filter, selector -> source -> List.of(selector.findSingleEntity(source))));
    }

    @Override
    public ArgumentType<EntitySelectorArgumentResolver> entities() {
        return EntityArgumentInternal.entities(selector -> source -> selector.findEntities(source).stream()
                .map(Entity.class::cast)
                .toList());
    }

    @Override
    public ArgumentType<EntitySelectorArgumentResolver> entities(final Predicate<Entity> filter) {
        return withServerSuggestions(EntityArgumentInternal.entities(filter, selector -> source -> selector.findEntities(source).stream()
                .map(Entity.class::cast)
                .toList()));
    }

    @Override
    public ArgumentType<PlayerSelectorArgumentResolver> player() {
        return EntityArgumentInternal.player(selector -> source -> List.of(selector.findSinglePlayer(source)));
    }

    @Override
    public ArgumentType<PlayerSelectorArgumentResolver> player(final Predicate<Player> filter) {
        return withServerSuggestions(EntityArgumentInternal.player(filter, selector -> source -> List.of(selector.findSinglePlayer(source))));
    }

    @Override
    public ArgumentType<PlayerSelectorArgumentResolver> players() {
        return EntityArgumentInternal.players(selector -> selector::findPlayers);
    }

    @Override
    public ArgumentType<PlayerSelectorArgumentResolver> players(final Predicate<Player> filter) {
        return withServerSuggestions(EntityArgumentInternal.players(filter, selector -> selector::findPlayers));
    }

    @Override
    public ArgumentType<BlockPosResolver> blockPosition() {
        return BlockPositionArgument.blockPosition();
    }

    @Override
    public ArgumentType<AngleResolver> angle() {
        return AngleArgument.angle();
    }

    @Override
    public ArgumentType<ItemStack> itemStack() {
        return ItemArgument.item(input -> {
            final var internal = input.createItemStack(1);
            return new ItemStack(internal.id(), internal.count());
        });
    }

    @Override
    public ArgumentType<ItemStackPredicate> itemStackPredicate() {
        return ItemPredicateArgument.itemPredicate(predicate -> apiStack -> predicate.test(
                ItemStack.of(apiStack.id(), apiStack.count())));
    }

    @Override
    public ArgumentType<NamedTextColor> namedColor() {
        return NamedColorArgument.namedColor();
    }

    @Override
    public ArgumentType<TextColor> hexColor() {
        return HexColorArgument.hexColor(TextColor::color);
    }

    @Override
    public ArgumentType<BossBar.Color> bossBarColor() {
        return BossBarColorArgument.bossBarColor();
    }

    @Override
    public ArgumentType<BossBar.Overlay> bossBarOverlay() {
        return BossBarOverlayArgument.bossBarOverlay();
    }

    @Override
    public ArgumentType<BossBar.Flag> bossBarFlag() {
        return BossBarFlagArgument.bossBarFlag();
    }

    @Override
    public ArgumentType<Component> component() {
        return ComponentArgument.textComponent();
    }

    @Override
    public ArgumentType<Style> style() {
        return StyleArgument.style();
    }

    @Override
    public ArgumentType<Key> key() {
        return KeyArgument.key();
    }

    @Override
    public ArgumentType<String> word() {
        return StringArgumentType.word();
    }

    @Override
    public ArgumentType<String> string() {
        return StringArgumentType.string();
    }

    @Override
    public ArgumentType<String> greedyString() {
        return StringArgumentType.greedyString();
    }

    @Override
    public ArgumentType<Boolean> bool() {
        return BoolArgumentType.bool();
    }

    @Override
    public ArgumentType<Integer> integer(final int min, final int max) {
        return IntegerArgumentType.integer(min, max);
    }

    @Override
    public ArgumentType<Long> longArg(final long min, final long max) {
        return LongArgumentType.longArg(min, max);
    }

    @Override
    public ArgumentType<Float> floatArg(final float min, final float max) {
        return FloatArgumentType.floatArg(min, max);
    }

    @Override
    public ArgumentType<Double> doubleArg(final double min, final double max) {
        return DoubleArgumentType.doubleArg(min, max);
    }

    @Override
    public ArgumentType<IntegerRangeProvider> integerRange() {
        return RangeArgument.intRange(bounds -> toRange(bounds, range -> () -> range));
    }

    @Override
    public ArgumentType<DoubleRangeProvider> doubleRange() {
        return RangeArgument.floatRange(bounds -> toRange(bounds, range -> () -> range));
    }

    private static <N extends Number & Comparable<N>, R extends RangeProvider<N>> R toRange(
            final RangeBounds<N> bounds,
            final Function<Range<N>, R> factory
    ) {
        final var min = bounds.min();
        final var max = bounds.max();

        final Range<N> range;

        if (min.isEmpty() && max.isEmpty()) {
            range = Range.all();
        } else if (min.isPresent() && max.isPresent()) {
            range = Range.closed(min.get(), max.get());
        } else if (min.isPresent()) {
            range = Range.atLeast(min.get());
        } else {
            range = Range.atMost(max.get());
        }

        return factory.apply(range);
    }

    @Override
    public ArgumentType<World> world() {
        return DimensionArgument.dimension(key -> FidorialServer.getInstance().worldManager().world(key));
    }

    @Override
    public ArgumentType<GameMode> gameMode() {
        return GameModeArgument.gameMode();
    }

    @Override
    public ArgumentType<UUID> uuid() {
        return UuidArgument.uuid();
    }

    @Override
    public ArgumentType<Integer> time(final int minTicks) {
        return TimeArgument.time(minTicks);
    }

    @Override
    public ArgumentType<Duration> duration() {
        return DurationArgument.duration();
    }

    @Override
    public <T> ArgumentType<TypedKey<T>> resourceKey(final RegistryKey<T> registryKey) {
        return ResourceKeyArgument.resourceKey(registryKey);
    }

    @Override
    public <T> ArgumentType<T> resource(final RegistryKey<T> registryKey) {
        return ResourceArgument.resource(registryKey);
    }

    @Override
    public ArgumentType<PlayerProfileListResolver> playerProfiles() {
        return PlayerProfileArgument.playerProfile((Function<PlayerProfileArgument.Result, PlayerProfileListResolver>) result -> source -> result.getNames(source).stream()
                .map(PlayerProfile::new)
                .toList());
    }

    @Override
    public ArgumentType<PlayerProfileListResolver> playerProfiles(final Predicate<Player> filter) {
        return withServerSuggestions(PlayerProfileArgument.playerProfile(filter, result -> source -> result.getNames(source).stream()
                .map(PlayerProfile::new)
                .toList()));
    }

    //@Override
    //public ArgumentType<BlockState> blockState() {
    //return null;
    //}

    @Override
    public ArgumentType<PositionResolver> position() {
        return Vec3Argument.vec3();
    }

    @Override
    public <N, T> ArgumentType<T> map(final ArgumentType<N> nativeType, final ArgumentMapper<N, T> mapper) {
        return new MappedArgumentType<>(nativeType, mapper);
    }

    @Override
    public <N, T> ArgumentType<T> map(final ArgumentType<N> nativeType, final ArgumentMapper<N, T> mapper, final SuggestionProvider<CommandSource> suggestions) {
        return new MappedArgumentType<>(nativeType, mapper, suggestions);
    }

    @Override
    public <T> ArgumentType<T> withServerSuggestions(final ArgumentType<T> type) {
        return new ForcedSuggestionsArgumentType<>(type);
    }

    @Override
    public <T> ArgumentType<T> serverResource(final RegistryKey<T> registryKey) {
        final Registry<T> registryLookup = FidorialServer.getInstance().registries().registry(registryKey);
        final ResourceKeyArgument<T> nativeType = ResourceKeyArgument.resourceKey(registryKey);

        return withServerSuggestions(new MappedArgumentType<>(nativeType, (typedKey, reader) ->
                registryLookup.find(typedKey)
                        .orElseThrow(() -> ResourceArgument.ERROR_UNKNOWN_RESOURCE
                                .createWithContext(reader, typedKey.key().asString(), registryKey.key().asString()))));
    }

    @Override
    public ArgumentType<NbtPathResolver> nbtPath() {
        return NbtDataArgument.nbtPath();
    }
}
