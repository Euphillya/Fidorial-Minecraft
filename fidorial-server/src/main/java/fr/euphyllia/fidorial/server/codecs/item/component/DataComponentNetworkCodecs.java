package fr.euphyllia.fidorial.server.codecs.item.component;

import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.registry.Registry;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.fidorial.item.component.ItemLore;
import fr.fidorial.item.component.SwingAnimation;
import fr.fidorial.item.data.DataComponentType;
import fr.fidorial.item.data.DataComponentTypes;
import io.netty.handler.codec.DecoderException;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DataComponentNetworkCodecs {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(DataComponentNetworkCodecs.class);

    private static final int MAX_COMPONENT_TEXT_LENGTH = 262_144;

    public interface Codec<T> {

        void write(PacketBuffer buf, RegistryHolder frozen, T value);

        T read(PacketBuffer buf, RegistryHolder frozen);
    }

    private static final Map<DataComponentType<?>, Codec<?>> CODECS = new LinkedHashMap<>();

    static {
        register(DataComponentTypes.MAX_STACK_SIZE, varIntCodec());
        register(DataComponentTypes.MAX_DAMAGE, varIntCodec());
        register(DataComponentTypes.DAMAGE, varIntCodec());
        register(DataComponentTypes.ITEM_MODEL, identifierCodec());
        register(DataComponentTypes.CUSTOM_NAME, textCodec());
        register(DataComponentTypes.ITEM_NAME, textCodec());
        register(DataComponentTypes.LORE, loreCodec());
        register(DataComponentTypes.ATTACK_ANIMATION, swingAnimationCodec());
        register(DataComponentTypes.INTERACT_ANIMATION, swingAnimationCodec());
    }

    private DataComponentNetworkCodecs() {
        throw new UnsupportedOperationException("DataComponentNetworkCodecs cannot be instantiated.");
    }

    private static <T> void register(final DataComponentType<T> type, final Codec<T> codec) {
        CODECS.put(type, codec);
    }

    @SuppressWarnings("unchecked")
    public static <T> @Nullable Codec<T> codec(final DataComponentType<T> type) {
        return (Codec<T>) CODECS.get(Objects.requireNonNull(type, "type"));
    }

    public static boolean hasCodec(final DataComponentType<?> type) {
        return CODECS.containsKey(Objects.requireNonNull(type, "type"));
    }

    public static <T> void writeErased(final PacketBuffer buf,
                                       final RegistryHolder frozen,
                                       final DataComponentType<T> type,
                                       final Object value) {

        final Codec<T> codec = codec(type);
        if (codec == null) {
            throw new IllegalStateException("No wire codec for component " + type);
        }
        codec.write(buf, frozen, type.valueType().cast(value));
    }

    private static Codec<Integer> varIntCodec() {
        return new Codec<>() {
            @Override
            public void write(final PacketBuffer buf, final RegistryHolder frozen, final Integer value) {
                buf.writeVarInt(value);
            }

            @Override
            public Integer read(final PacketBuffer buf, final RegistryHolder frozen) {
                return buf.readVarInt();
            }
        };
    }

    private static Codec<Boolean> booleanCodec() {
        return new Codec<>() {
            @Override
            public void write(final PacketBuffer buf, final RegistryHolder frozen, final Boolean value) {
                buf.writeBoolean(value);
            }

            @Override
            public Boolean read(final PacketBuffer buf, final RegistryHolder frozen) {
                return buf.readBoolean();
            }
        };
    }

    private static Codec<Key> identifierCodec() {
        return new Codec<>() {
            @Override
            public void write(final PacketBuffer buf, final RegistryHolder frozen, final Key value) {
                buf.writeKey(value);
            }

            @Override
            public Key read(final PacketBuffer buf, final RegistryHolder frozen) {
                return buf.readKey();
            }
        };
    }

    private static Codec<Component> textCodec() {
        return new Codec<>() {
            @Override
            public void write(final PacketBuffer buf, final RegistryHolder frozen, final Component value) {
                buf.writeComponent(value);
            }

            @Override
            public Component read(final PacketBuffer buf, final RegistryHolder frozen) {
                return buf.readComponent(MAX_COMPONENT_TEXT_LENGTH);
            }
        };
    }

    private static Codec<ItemLore> loreCodec() {
        return new Codec<>() {
            @Override
            public void write(final PacketBuffer buf, final RegistryHolder frozen, final ItemLore value) {
                buf.writeVarInt(value.size());
                for (final Component line : value.lines()) {
                    buf.writeComponent(line);
                }
            }

            @Override
            public ItemLore read(final PacketBuffer buf, final RegistryHolder frozen) {
                final int size = readBoundedLength(buf, ItemLore.MAX_LINES, "lore");

                final List<Component> lines = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    lines.add(buf.readComponent(MAX_COMPONENT_TEXT_LENGTH));
                }

                return new ItemLore(lines);
            }
        };
    }

    private static Codec<SwingAnimation> swingAnimationCodec() {
        return new Codec<>() {
            @Override
            public void write(final PacketBuffer buf, final RegistryHolder frozen, final SwingAnimation value) {
                buf.writeVarInt(networkId(value.type()));
                buf.writeVarInt(value.duration());
            }

            @Override
            public SwingAnimation read(final PacketBuffer buf, final RegistryHolder frozen) {
                final SwingAnimation.SwingAnimationType type = fromNetworkId(buf.readVarInt());
                final int duration = buf.readVarInt();
                return new SwingAnimation(type, duration);
            }
        };
    }

    private static int networkId(final SwingAnimation.SwingAnimationType type) {
        return switch (type) {
            case WHACK -> 1;
            case STAB -> 2;
        };
    }

    private static SwingAnimation.SwingAnimationType fromNetworkId(final int id) {
        return switch (id) {
            case 1 -> SwingAnimation.SwingAnimationType.WHACK;
            case 2 -> SwingAnimation.SwingAnimationType.STAB;
            default -> throw new DecoderException("Unknown swing animation type id: " + id);
        };
    }

    private static int readBoundedLength(final PacketBuffer buf, final int max, final String what) {
        final int size = buf.readVarInt();
        if (size < 0 || size > max) {
            throw new DecoderException("Implausible " + what + " length: " + size);
        }
        return size;
    }

    private static @Nullable Key entryOf(final RegistryHolder frozen, final Key registryKey, final int networkId) {
        final Registry registry = frozen.get(registryKey);
        if (registry == null || networkId < 0 || networkId >= registry.entries().size()) {
            return null;
        }
        return registry.entries().get(networkId);
    }
}
