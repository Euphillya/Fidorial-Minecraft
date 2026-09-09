package fr.euphyllia.fidorial.server.command.brigadier.argument.item;

import com.google.gson.JsonObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import fr.euphyllia.fidorial.server.command.brigadier.argument.util.ExceptionFactory;
import fr.euphyllia.fidorial.server.command.brigadier.argument.util.KeyReader;
import fr.euphyllia.fidorial.server.command.brigadier.packet.registry.ArgumentTypeRegistrar;
import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.fidorial.item.ItemStack;
import fr.fidorial.registry.TypedKey;
import fr.fidorial.registry.keys.ItemKeys;
import net.kyori.adventure.key.Key;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ItemPredicateArgument<T> implements ArgumentType<T> {

    public static final DynamicCommandExceptionType ERROR_UNKNOWN_ITEM = ExceptionFactory.dynamic("argument.item.id.invalid");

    public static final SimpleCommandExceptionType ERROR_TAGS_UNSUPPORTED = ExceptionFactory.simple("Item tags are not yet supported");

    private final Function<Predicate<ItemStack>, T> converter;

    private ItemPredicateArgument(final Function<Predicate<ItemStack>, T> converter) {
        this.converter = converter;
    }

    public static ItemPredicateArgument<Predicate<ItemStack>> itemPredicate() {
        return itemPredicate(Function.identity());
    }

    public static <T> ItemPredicateArgument<T> itemPredicate(final Function<Predicate<ItemStack>, T> converter) {
        return new ItemPredicateArgument<>(converter);
    }

    private boolean exists(final Key key) {
        return ItemKeys.values().anyMatch(item -> item.key().equals(key));
    }

    @Override
    public T parse(final StringReader reader) throws CommandSyntaxException {
        if (reader.canRead() && reader.peek() == '#') {
            // TBD
            throw ERROR_TAGS_UNSUPPORTED.createWithContext(reader);
        }

        final int start = reader.getCursor();

        final KeyReader.ParsedKey parsed = KeyReader.readKeyStringDetailed(reader);
        final Key key = parsed.hasNamespace()
                ? Key.key(parsed.value())
                : Key.key(Key.MINECRAFT_NAMESPACE, parsed.value());

        if (!exists(key)) {
            reader.setCursor(start);
            throw ERROR_UNKNOWN_ITEM.createWithContext(reader, key.asString());
        }

        // TODO: ("[key=value,...]") are not parsed yet.
        if (reader.canRead() && reader.peek() == '[') {
            while (reader.canRead() && reader.peek() != ']') {
                reader.skip();
            }
            if (reader.canRead()) reader.skip();
        }

        final Predicate<ItemStack> predicate = stack -> !stack.isEmpty() && stack.id().equals(key);

        return converter.apply(predicate);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        final String remaining = builder.getRemaining().toLowerCase(Locale.ROOT);
        ItemKeys.values()
                .map(TypedKey::key)
                .map(Key::asString)
                .filter(id -> id.contains(remaining))
                .forEach(builder::suggest);
        return builder.buildFuture();
    }

    public static final class Info implements ArgumentTypeRegistrar<ItemPredicateArgument<?>, Info.Spec> {

        @Override
        public void serialize(final Spec spec, final PacketBuffer buf) {
        }

        @Override
        public Spec deserialize(final PacketBuffer buf) {
            return new Spec();
        }

        @Override
        public void serializeJson(final Spec spec, final JsonObject json) {
        }

        @Override
        public Spec access(final ItemPredicateArgument<?> argument) {
            return new Spec();
        }

        public record Spec() implements ArgumentTypeRegistrar.Spec<ItemPredicateArgument<?>> {
            @Override
            public ItemPredicateArgument<?> instantiate() {
                return ItemPredicateArgument.itemPredicate();
            }

            @Override
            public ArgumentTypeRegistrar<ItemPredicateArgument<?>, ?> type() {
                return new Info();
            }
        }
    }
}
