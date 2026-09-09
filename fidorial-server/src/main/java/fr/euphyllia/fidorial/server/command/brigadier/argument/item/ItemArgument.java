package fr.euphyllia.fidorial.server.command.brigadier.argument.item;

import com.google.gson.JsonObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
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

public final class ItemArgument<T> implements ArgumentType<T> {

    public static final DynamicCommandExceptionType ERROR_UNKNOWN_ITEM = ExceptionFactory.dynamic("argument.item.id.invalid");

    private final Function<ItemInput, T> converter;

    private ItemArgument(final Function<ItemInput, T> converter) {
        this.converter = converter;
    }

    public static ItemArgument<ItemInput> item() {
        return item(Function.identity());
    }

    public static <T> ItemArgument<T> item(final Function<ItemInput, T> converter) {
        return new ItemArgument<>(converter);
    }

    private boolean exists(final Key key) {
        return ItemKeys.values().anyMatch(item -> item.key().equals(key));
    }

    @Override
    public T parse(final StringReader reader) throws CommandSyntaxException {
        final int start = reader.getCursor();

        final KeyReader.ParsedKey parsed = KeyReader.readKeyStringDetailed(reader);
        final Key key = parsed.hasNamespace()
                ? Key.key(parsed.value())
                : Key.key(Key.MINECRAFT_NAMESPACE, parsed.value());

        if (!exists(key)) {
            reader.setCursor(start);
            throw ERROR_UNKNOWN_ITEM.createWithContext(reader, key.asString());
        }

        // TODO: ("[key=value,...]") is not parsed yet.
        if (reader.canRead() && reader.peek() == '[') {
            while (reader.canRead() && reader.peek() != ']') {
                reader.skip();
            }
            if (reader.canRead()) reader.skip();
        }

        return converter.apply(new ItemInput(key));
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

    public record ItemInput(Key id) {
        public ItemStack createItemStack(final int count) {
            return ItemStack.of(id, count);
        }
    }

    public static final class Info implements ArgumentTypeRegistrar<ItemArgument<?>, Info.Spec> {

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
        public Spec access(final ItemArgument<?> argument) {
            return new Spec();
        }

        public record Spec() implements ArgumentTypeRegistrar.Spec<ItemArgument<?>> {
            @Override
            public ItemArgument<?> instantiate() {
                return ItemArgument.item();
            }

            @Override
            public ArgumentTypeRegistrar<ItemArgument<?>, ?> type() {
                return new Info();
            }
        }
    }
}
