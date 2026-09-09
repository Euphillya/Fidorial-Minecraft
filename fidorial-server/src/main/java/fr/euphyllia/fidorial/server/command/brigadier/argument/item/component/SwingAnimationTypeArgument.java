package fr.euphyllia.fidorial.server.command.brigadier.argument.item.component;

import com.google.gson.JsonObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import fr.euphyllia.fidorial.server.command.brigadier.argument.util.ExceptionFactory;
import fr.euphyllia.fidorial.server.command.brigadier.packet.registry.ArgumentTypeRegistrar;
import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.fidorial.command.CommandSource;
import fr.fidorial.item.component.SwingAnimation.SwingAnimationType;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public final class SwingAnimationTypeArgument implements ArgumentType<SwingAnimationType> {

    private static final DynamicCommandExceptionType ERROR_INVALID_SWING_ANIMATION = ExceptionFactory.dynamic("argument.swing_animation.invalid");

    public static SwingAnimationTypeArgument swingAnimationType() {
        return new SwingAnimationTypeArgument();
    }

    @Override
    public SwingAnimationType parse(final StringReader reader) throws CommandSyntaxException {
        final int start = reader.getCursor();
        final String value = reader.readUnquotedString();
        for (final SwingAnimationType type : SwingAnimationType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }

        reader.setCursor(start);
        throw ERROR_INVALID_SWING_ANIMATION.createWithContext(reader, value);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        if (!(context.getSource() instanceof CommandSource)) {
            return Suggestions.empty();
        }
        for (final SwingAnimationType type : SwingAnimationType.values()) {
            builder.suggest(type.name().toLowerCase(Locale.ROOT));
        }
        return builder.buildFuture();
    }

    public static final class Info implements ArgumentTypeRegistrar<SwingAnimationTypeArgument, Info.Spec> {

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
        public Spec access(final SwingAnimationTypeArgument argument) {
            return new Spec();
        }

        public record Spec() implements ArgumentTypeRegistrar.Spec<SwingAnimationTypeArgument> {
            @Override
            public SwingAnimationTypeArgument instantiate() {
                return SwingAnimationTypeArgument.swingAnimationType();
            }

            @Override
            public ArgumentTypeRegistrar<SwingAnimationTypeArgument, ?> type() {
                return new Info();
            }
        }
    }
}
