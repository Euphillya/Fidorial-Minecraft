package fr.euphyllia.fidorial.server.world.storage.datafixers.util.nbt;

import ca.spottedleaf.converter.types.ObjectType;
import ca.spottedleaf.converter.types.TypeUtil;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.ByteArrayBinaryTag;
import net.kyori.adventure.nbt.ByteBinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.DoubleBinaryTag;
import net.kyori.adventure.nbt.EndBinaryTag;
import net.kyori.adventure.nbt.FloatBinaryTag;
import net.kyori.adventure.nbt.IntArrayBinaryTag;
import net.kyori.adventure.nbt.IntBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.LongArrayBinaryTag;
import net.kyori.adventure.nbt.LongBinaryTag;
import net.kyori.adventure.nbt.ShortBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import org.jspecify.annotations.Nullable;

public final class NbtTypeUtil extends TypeUtil<BinaryTag> {

    public static final NbtTypeUtil INSTANCE = new NbtTypeUtil();

    @Override
    public NbtListType createEmptyList() {
        return new NbtListType();
    }

    @Override
    public NbtMapType createEmptyMap() {
        return new NbtMapType();
    }

    @Override
    @Nullable public Object convertFromBaseToGeneric(final @Nullable BinaryTag input, final TypeUtil<?> to) {
        return baseToGeneric(input);
    }

    @Override
    public Object convertGenericToGeneric(final Object valueGeneric, final TypeUtil<?> to) {
        throw new UnsupportedOperationException("Cross TypeUtil conversion not implemented");
    }

    @Override
    @Nullable public Object baseToGeneric(final @Nullable BinaryTag input) {
        if (input == null || input == EndBinaryTag.endBinaryTag()) {
            return null;
        }
        return switch (input) {
            case CompoundBinaryTag c -> NbtMapType.of(c);
            case ListBinaryTag l -> NbtListType.of(l);
            case ByteBinaryTag b -> b.value();
            case ShortBinaryTag s -> s.value();
            case IntBinaryTag i -> i.value();
            case LongBinaryTag l -> l.value();
            case FloatBinaryTag f -> f.value();
            case DoubleBinaryTag d -> d.value();
            case StringBinaryTag s -> s.value();
            case ByteArrayBinaryTag b -> b.value();
            case IntArrayBinaryTag i -> i.value();
            case LongArrayBinaryTag l -> l.value();
            default -> throw new IllegalStateException("Unhandled tag type: " + input.type());
        };
    }

    @Override
    public BinaryTag genericToBase(final @Nullable Object input) {
        if (input == null) {
            return EndBinaryTag.endBinaryTag();
        }
        return switch (input) {
            case NbtMapType m -> m.toCompound();
            case NbtListType l -> l.toListTag();
            case Byte b -> ByteBinaryTag.byteBinaryTag(b);
            case Short s -> ShortBinaryTag.shortBinaryTag(s);
            case Integer i -> IntBinaryTag.intBinaryTag(i);
            case Long l -> LongBinaryTag.longBinaryTag(l);
            case Float f -> FloatBinaryTag.floatBinaryTag(f);
            case Double d -> DoubleBinaryTag.doubleBinaryTag(d);
            case String s -> StringBinaryTag.stringBinaryTag(s);
            case byte[] b -> ByteArrayBinaryTag.byteArrayBinaryTag(b);
            case int[] i -> IntArrayBinaryTag.intArrayBinaryTag(i);
            case long[] l -> LongArrayBinaryTag.longArrayBinaryTag(l);
            default -> throw new IllegalStateException("Cannot convert to BinaryTag: " + input.getClass());
        };
    }

    @Override
    public boolean isCompatibleNumber(final Number number) {
        return number instanceof Byte || number instanceof Short || number instanceof Integer
                || number instanceof Long || number instanceof Float || number instanceof Double;
    }

    @Override
    public boolean isCompatibleArray(final Object array) {
        return array instanceof byte[] || array instanceof int[] || array instanceof long[];
    }

    @Override
    @Nullable public ObjectType getTypeBase(final BinaryTag value) {
        return switch (value) {
            case ByteBinaryTag _ -> ObjectType.BYTE;
            case ShortBinaryTag _ -> ObjectType.SHORT;
            case IntBinaryTag _ -> ObjectType.INT;
            case LongBinaryTag _ -> ObjectType.LONG;
            case FloatBinaryTag _ -> ObjectType.FLOAT;
            case DoubleBinaryTag _ -> ObjectType.DOUBLE;
            case StringBinaryTag _ -> ObjectType.STRING;
            case ByteArrayBinaryTag _ -> ObjectType.BYTE_ARRAY;
            case IntArrayBinaryTag _ -> ObjectType.INT_ARRAY;
            case LongArrayBinaryTag _ -> ObjectType.LONG_ARRAY;
            case CompoundBinaryTag _ -> ObjectType.MAP;
            case ListBinaryTag _ -> ObjectType.LIST;
            default -> null;
        };
    }

    @Override
    public Object deepCopy(final BinaryTag base) {
        return base;
    }
}
