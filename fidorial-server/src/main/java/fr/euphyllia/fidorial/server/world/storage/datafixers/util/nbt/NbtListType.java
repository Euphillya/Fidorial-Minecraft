package fr.euphyllia.fidorial.server.world.storage.datafixers.util.nbt;

import ca.spottedleaf.converter.types.ListType;
import ca.spottedleaf.converter.types.MapType;
import ca.spottedleaf.converter.types.ObjectType;
import ca.spottedleaf.converter.types.TypeUtil;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.ByteArrayBinaryTag;
import net.kyori.adventure.nbt.ByteBinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.DoubleBinaryTag;
import net.kyori.adventure.nbt.FloatBinaryTag;
import net.kyori.adventure.nbt.IntArrayBinaryTag;
import net.kyori.adventure.nbt.IntBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.LongArrayBinaryTag;
import net.kyori.adventure.nbt.LongBinaryTag;
import net.kyori.adventure.nbt.NumberBinaryTag;
import net.kyori.adventure.nbt.ShortBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Objects;

public final class NbtListType extends ListType {

    private final ArrayList<BinaryTag> list;

    public NbtListType() {
        this.list = new ArrayList<>();
    }

    public NbtListType(final int capacity) {
        this.list = new ArrayList<>(capacity);
    }

    private NbtListType(final ArrayList<BinaryTag> list) {
        this.list = list;
    }

    public static NbtListType of(final ListBinaryTag tag) {
        final ArrayList<BinaryTag> copy = new ArrayList<>(tag.size());
        for (final BinaryTag element : tag) {
            copy.add(element);
        }
        return new NbtListType(copy);
    }

    public ListBinaryTag toListTag() {
        return ListBinaryTag.from(list);
    }

    @Override
    public TypeUtil<?> getTypeUtil() {
        return NbtTypeUtil.INSTANCE;
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public boolean equals(final @Nullable Object o) {
        return this == o || (o instanceof final NbtListType other && list.equals(other.list));
    }

    @Override
    public String toString() {
        return "NbtListType" + list;
    }

    @Override
    public NbtListType createEmptyList() {
        return new NbtListType();
    }

    @Override
    public NbtMapType createEmptyMap() {
        return new NbtMapType();
    }

    @Override
    public NbtListType copy() {
        final ArrayList<BinaryTag> copy = new ArrayList<>(list);
        return new NbtListType(copy);
    }

    @Override
    public ObjectType getUniformType() {
        ObjectType current = null;
        for (BinaryTag binaryTag : list) {
            final ObjectType tagType = NbtTypeUtil.INSTANCE.getTypeBase(binaryTag);
            if (current == null) {
                current = tagType;
            } else if (tagType != current) {
                return ObjectType.MIXED;
            }
        }
        return current == null ? ObjectType.NONE : current;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void remove(final int index) {
        list.remove(index);
    }

    @Override
    public @Nullable Object getGenericAndRemove(final int index) {
        return NbtTypeUtil.INSTANCE.baseToGeneric(list.remove(index));
    }

    @Override
    public @Nullable Object getGeneric(final int index) {
        return NbtTypeUtil.INSTANCE.baseToGeneric(list.get(index));
    }

    @Override
    public void setGeneric(final int index, final Object to) {
        list.set(index, NbtTypeUtil.INSTANCE.genericToBase(Objects.requireNonNull(to, "to may not be null")));
    }

    @Override
    public Number getNumber(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at index " + index + ", got " + tag);
        }
        return boxedValue(n);
    }

    @Override
    public @Nullable Number getNumber(final int index, final @Nullable Number dfl) {
        return list.get(index) instanceof final NumberBinaryTag n ? boxedValue(n) : dfl;
    }

    private static Number boxedValue(final NumberBinaryTag n) {
        return switch (n) {
            case ByteBinaryTag b -> b.value();
            case ShortBinaryTag s -> s.value();
            case IntBinaryTag i -> i.value();
            case LongBinaryTag l -> l.value();
            case FloatBinaryTag f -> f.value();
            case DoubleBinaryTag d -> d.value();
        };
    }

    @Override
    public BigInteger getBigInteger(final int index) {
        throw new UnsupportedOperationException("NBT has no BigInteger tag");
    }

    @Override
    public @Nullable BigInteger getBigInteger(final int index, final @Nullable BigInteger dfl) {
        throw new UnsupportedOperationException("NBT has no BigInteger tag");
    }

    @Override
    public void setBigInteger(final int index, final BigInteger to) {
        throw new UnsupportedOperationException("NBT has no BigInteger tag");
    }

    @Override
    public void addBigInteger(final BigInteger i) {
        throw new UnsupportedOperationException("NBT has no BigInteger tag");
    }

    @Override
    public void addBigInteger(final int index, final BigInteger i) {
        throw new UnsupportedOperationException("NBT has no BigInteger tag");
    }

    @Override
    public BigDecimal getBigDecimal(final int index) {
        throw new UnsupportedOperationException("NBT has no BigDecimal tag");
    }

    @Override
    public @Nullable BigDecimal getBigDecimal(final int index, final @Nullable BigDecimal dfl) {
        throw new UnsupportedOperationException("NBT has no BigDecimal tag");
    }

    @Override
    public void setBigDecimal(final int index, final BigDecimal to) {
        throw new UnsupportedOperationException("NBT has no BigDecimal tag");
    }

    @Override
    public void addBigDecimal(final BigDecimal d) {
        throw new UnsupportedOperationException("NBT has no BigDecimal tag");
    }

    @Override
    public void addBigDecimal(final int index, final BigDecimal d) {
        throw new UnsupportedOperationException("NBT has no BigDecimal tag");
    }

    @Override
    public byte getByte(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at index " + index + ", got " + tag);
        }
        return n.byteValue();
    }

    @Override
    public byte getByte(final int index, final byte dfl) {
        return list.get(index) instanceof final NumberBinaryTag n ? n.byteValue() : dfl;
    }

    @Override
    public void setByte(final int index, final byte to) {
        list.set(index, ByteBinaryTag.byteBinaryTag(to));
    }

    @Override
    public void addByte(final byte b) {
        list.add(ByteBinaryTag.byteBinaryTag(b));
    }

    @Override
    public void addByte(final int index, final byte b) {
        list.add(index, ByteBinaryTag.byteBinaryTag(b));
    }

    @Override
    public short getShort(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at index " + index + ", got " + tag);
        }
        return n.shortValue();
    }

    @Override
    public short getShort(final int index, final short dfl) {
        return list.get(index) instanceof final NumberBinaryTag n ? n.shortValue() : dfl;
    }

    @Override
    public void setShort(final int index, final short to) {
        list.set(index, ShortBinaryTag.shortBinaryTag(to));
    }

    @Override
    public void addShort(final short s) {
        list.add(ShortBinaryTag.shortBinaryTag(s));
    }

    @Override
    public void addShort(final int index, final short s) {
        list.add(index, ShortBinaryTag.shortBinaryTag(s));
    }

    @Override
    public int getInt(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at index " + index + ", got " + tag);
        }
        return n.intValue();
    }

    @Override
    public int getInt(final int index, final int dfl) {
        return list.get(index) instanceof final NumberBinaryTag n ? n.intValue() : dfl;
    }

    @Override
    public void setInt(final int index, final int to) {
        list.set(index, IntBinaryTag.intBinaryTag(to));
    }

    @Override
    public void addInt(final int i) {
        list.add(IntBinaryTag.intBinaryTag(i));
    }

    @Override
    public void addInt(final int index, final int i) {
        list.add(index, IntBinaryTag.intBinaryTag(i));
    }

    @Override
    public long getLong(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at index " + index + ", got " + tag);
        }
        return n.longValue();
    }

    @Override
    public long getLong(final int index, final long dfl) {
        return list.get(index) instanceof final NumberBinaryTag n ? n.longValue() : dfl;
    }

    @Override
    public void setLong(final int index, final long to) {
        list.set(index, LongBinaryTag.longBinaryTag(to));
    }

    @Override
    public void addLong(final long l) {
        list.add(LongBinaryTag.longBinaryTag(l));
    }

    @Override
    public void addLong(final int index, final long l) {
        list.add(index, LongBinaryTag.longBinaryTag(l));
    }

    @Override
    public float getFloat(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at index " + index + ", got " + tag);
        }
        return n.floatValue();
    }

    @Override
    public float getFloat(final int index, final float dfl) {
        return list.get(index) instanceof final NumberBinaryTag n ? n.floatValue() : dfl;
    }

    @Override
    public void setFloat(final int index, final float to) {
        list.set(index, FloatBinaryTag.floatBinaryTag(to));
    }

    @Override
    public void addFloat(final float f) {
        list.add(FloatBinaryTag.floatBinaryTag(f));
    }

    @Override
    public void addFloat(final int index, final float f) {
        list.add(index, FloatBinaryTag.floatBinaryTag(f));
    }

    @Override
    public double getDouble(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at index " + index + ", got " + tag);
        }
        return n.doubleValue();
    }

    @Override
    public double getDouble(final int index, final double dfl) {
        return list.get(index) instanceof final NumberBinaryTag n ? n.doubleValue() : dfl;
    }

    @Override
    public void setDouble(final int index, final double to) {
        list.set(index, DoubleBinaryTag.doubleBinaryTag(to));
    }

    @Override
    public void addDouble(final double d) {
        list.add(DoubleBinaryTag.doubleBinaryTag(d));
    }

    @Override
    public void addDouble(final int index, final double d) {
        list.add(index, DoubleBinaryTag.doubleBinaryTag(d));
    }

    @Override
    public byte[] getBytes(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final ByteArrayBinaryTag t)) {
            throw new IllegalStateException("Expected a byte array tag at index " + index + ", got " + tag);
        }
        return t.value();
    }

    @Override
    public byte @Nullable [] getBytes(final int index, final byte @Nullable [] dfl) {
        return list.get(index) instanceof final ByteArrayBinaryTag t ? t.value() : dfl;
    }

    @Override
    public void setBytes(final int index, final byte[] to) {
        list.set(index, ByteArrayBinaryTag.byteArrayBinaryTag(Objects.requireNonNull(to, "to may not be null")));
    }

    @Override
    public void addByteArray(final byte[] arr) {
        list.add(ByteArrayBinaryTag.byteArrayBinaryTag(Objects.requireNonNull(arr, "arr may not be null")));
    }

    @Override
    public void addByteArray(final int index, final byte[] arr) {
        list.add(index, ByteArrayBinaryTag.byteArrayBinaryTag(Objects.requireNonNull(arr, "arr may not be null")));
    }

    @Override
    public short[] getShorts(final int index) {
        throw new UnsupportedOperationException("NBT has no short array tag");
    }

    @Override
    public short @Nullable [] getShorts(final int index, final short @Nullable [] dfl) {
        throw new UnsupportedOperationException("NBT has no short array tag");
    }

    @Override
    public void setShorts(final int index, final short[] to) {
        throw new UnsupportedOperationException("NBT has no short array tag");
    }

    @Override
    public void addShortArray(final short[] arr) {
        throw new UnsupportedOperationException("NBT has no short array tag");
    }

    @Override
    public void addShortArray(final int index, final short[] arr) {
        throw new UnsupportedOperationException("NBT has no short array tag");
    }

    @Override
    public int[] getInts(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final IntArrayBinaryTag t)) {
            throw new IllegalStateException("Expected an int array tag at index " + index + ", got " + tag);
        }
        return t.value();
    }

    @Override
    public int @Nullable [] getInts(final int index, final int @Nullable [] dfl) {
        return list.get(index) instanceof final IntArrayBinaryTag t ? t.value() : dfl;
    }

    @Override
    public void setInts(final int index, final int[] to) {
        list.set(index, IntArrayBinaryTag.intArrayBinaryTag(Objects.requireNonNull(to, "to may not be null")));
    }

    @Override
    public void addIntArray(final int[] arr) {
        list.add(IntArrayBinaryTag.intArrayBinaryTag(Objects.requireNonNull(arr, "arr may not be null")));
    }

    @Override
    public void addIntArray(final int index, final int[] arr) {
        list.add(index, IntArrayBinaryTag.intArrayBinaryTag(Objects.requireNonNull(arr, "arr may not be null")));
    }

    @Override
    public long[] getLongs(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final LongArrayBinaryTag t)) {
            throw new IllegalStateException("Expected a long array tag at index " + index + ", got " + tag);
        }
        return t.value();
    }

    @Override
    public long @Nullable [] getLongs(final int index, final long @Nullable [] dfl) {
        return list.get(index) instanceof final LongArrayBinaryTag t ? t.value() : dfl;
    }

    @Override
    public void setLongs(final int index, final long[] to) {
        list.set(index, LongArrayBinaryTag.longArrayBinaryTag(Objects.requireNonNull(to, "to may not be null")));
    }

    @Override
    public void addLongArray(final long[] arr) {
        list.add(LongArrayBinaryTag.longArrayBinaryTag(Objects.requireNonNull(arr, "arr may not be null")));
    }

    @Override
    public void addLongArray(final int index, final long[] arr) {
        list.add(index, LongArrayBinaryTag.longArrayBinaryTag(Objects.requireNonNull(arr, "arr may not be null")));
    }

    @Override
    public NbtListType getList(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final ListBinaryTag t)) {
            throw new IllegalStateException("Expected a list tag at index " + index + ", got " + tag);
        }
        return NbtListType.of(t);
    }

    @Override
    public @Nullable NbtListType getList(final int index, final @Nullable ListType dfl) {
        return list.get(index) instanceof final ListBinaryTag t ? NbtListType.of(t) : (NbtListType) dfl;
    }

    @Override
    public void setList(final int index, final ListType to) {
        list.set(index, ((NbtListType) Objects.requireNonNull(to, "to may not be null")).toListTag());
    }

    @Override
    public void addList(final ListType listValue) {
        list.add(((NbtListType) Objects.requireNonNull(listValue, "listValue may not be null")).toListTag());
    }

    @Override
    public void addList(final int index, final ListType listValue) {
        list.add(index, ((NbtListType) Objects.requireNonNull(listValue, "listValue may not be null")).toListTag());
    }

    @Override
    public NbtMapType getMap(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final CompoundBinaryTag t)) {
            throw new IllegalStateException("Expected a compound tag at index " + index + ", got " + tag);
        }
        return NbtMapType.of(t);
    }

    @Override
    public @Nullable NbtMapType getMap(final int index, final @Nullable MapType dfl) {
        return list.get(index) instanceof final CompoundBinaryTag t ? NbtMapType.of(t) : (NbtMapType) dfl;
    }

    @Override
    public void setMap(final int index, final MapType to) {
        list.set(index, ((NbtMapType) Objects.requireNonNull(to, "to may not be null")).toCompound());
    }

    @Override
    public void addMap(final MapType mapValue) {
        list.add(((NbtMapType) Objects.requireNonNull(mapValue, "mapValue may not be null")).toCompound());
    }

    @Override
    public void addMap(final int index, final MapType mapValue) {
        list.add(index, ((NbtMapType) Objects.requireNonNull(mapValue, "mapValue may not be null")).toCompound());
    }

    @Override
    public String getString(final int index) {
        final BinaryTag tag = list.get(index);
        if (!(tag instanceof final StringBinaryTag t)) {
            throw new IllegalStateException("Expected a string tag at index " + index + ", got " + tag);
        }
        return t.value();
    }

    @Override
    public @Nullable String getString(final int index, final @Nullable String dfl) {
        return list.get(index) instanceof final StringBinaryTag t ? t.value() : dfl;
    }

    @Override
    public void setString(final int index, final String to) {
        list.set(index, StringBinaryTag.stringBinaryTag(Objects.requireNonNull(to, "to may not be null")));
    }

    @Override
    public void addString(final String string) {
        list.add(StringBinaryTag.stringBinaryTag(Objects.requireNonNull(string, "string may not be null")));
    }

    @Override
    public void addString(final int index, final String string) {
        list.add(index, StringBinaryTag.stringBinaryTag(Objects.requireNonNull(string, "string may not be null")));
    }

    @Override
    public void addGeneric(final Object value) {
        list.add(NbtTypeUtil.INSTANCE.genericToBase(Objects.requireNonNull(value, "value may not be null")));
    }
}
