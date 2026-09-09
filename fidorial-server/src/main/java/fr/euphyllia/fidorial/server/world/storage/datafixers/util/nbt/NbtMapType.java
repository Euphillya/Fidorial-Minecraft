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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public final class NbtMapType extends MapType {

    private final LinkedHashMap<String, BinaryTag> map;

    public NbtMapType() {
        this.map = new LinkedHashMap<>();
    }

    private NbtMapType(final LinkedHashMap<String, BinaryTag> map) {
        this.map = map;
    }

    public static NbtMapType of(final CompoundBinaryTag tag) {
        final LinkedHashMap<String, BinaryTag> copy = new LinkedHashMap<>(tag.size());
        tag.forEach(entry -> copy.put(entry.getKey(), entry.getValue()));
        return new NbtMapType(copy);
    }

    public CompoundBinaryTag toCompound() {
        return CompoundBinaryTag.from(map);
    }

    @Override
    public TypeUtil<?> getTypeUtil() {
        return NbtTypeUtil.INSTANCE;
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public boolean equals(final @Nullable Object o) {
        return this == o || (o instanceof final NbtMapType other && map.equals(other.map));
    }

    @Override
    public String toString() {
        return "NbtMapType" + map;
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
    public NbtMapType copy() {
        final LinkedHashMap<String, BinaryTag> copy = new LinkedHashMap<>(map);
        return new NbtMapType(copy);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keys() {
        return map.keySet();
    }

    @Override
    public boolean rename(final String fromKey, final String toKey) {
        final BinaryTag value = map.remove(fromKey);
        if (value == null) {
            return false;
        }
        map.put(toKey, value);
        return true;
    }

    @Override
    public boolean renameKeys(final Function<String, @Nullable String> renamer) {
        boolean changed = false;
        final LinkedHashMap<String, BinaryTag> renamed = new LinkedHashMap<>();
        for (final Map.Entry<String, BinaryTag> entry : map.entrySet()) {
            final String newKey = renamer.apply(entry.getKey());
            if (newKey != null && !newKey.equals(entry.getKey())) {
                renamed.put(newKey, entry.getValue());
                changed = true;
            } else {
                renamed.put(entry.getKey(), entry.getValue());
            }
        }
        if (changed) {
            map.clear();
            map.putAll(renamed);
        }
        return changed;
    }

    @Override
    public boolean hasKey(final String key) {
        return map.containsKey(key);
    }

    @Override
    public boolean hasKey(final String key, final ObjectType type) {
        final BinaryTag tag = map.get(key);
        if (tag == null) {
            return false;
        }
        final ObjectType actual = NbtTypeUtil.INSTANCE.getTypeBase(tag);
        return actual == type || (type == ObjectType.NUMBER && actual != null && actual.isNumber());
    }

    @Override
    public void remove(final String key) {
        map.remove(key);
    }

    @Override
    public @Nullable Object getGenericAndRemove(final String key) {
        return NbtTypeUtil.INSTANCE.baseToGeneric(map.remove(key));
    }

    @Override
    public @Nullable Object getGeneric(final String key) {
        return NbtTypeUtil.INSTANCE.baseToGeneric(map.get(key));
    }

    @Override
    public @Nullable Object getGeneric(final String key, final @Nullable Object dfl) {
        final BinaryTag tag = map.get(key);
        return tag == null ? dfl : NbtTypeUtil.INSTANCE.baseToGeneric(tag);
    }

    @Override
    public void setGeneric(final String key, final Object value) {
        map.put(Objects.requireNonNull(key, "key may not be null"), NbtTypeUtil.INSTANCE.genericToBase(value));
    }

    @Override
    public Number getNumber(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at key " + key + ", got " + tag);
        }
        return boxedValue(n);
    }

    @Override
    public @Nullable Number getNumber(final String key, final @Nullable Number dfl) {
        return map.get(key) instanceof final NumberBinaryTag n ? boxedValue(n) : dfl;
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
    public BigInteger getBigInteger(final String key) {
        throw new UnsupportedOperationException("NBT has no BigInteger tag");
    }

    @Override
    public @Nullable BigInteger getBigInteger(final String key, final @Nullable BigInteger dfl) {
        throw new UnsupportedOperationException("NBT has no BigInteger tag");
    }

    @Override
    public void setBigInteger(final String key, final BigInteger val) {
        throw new UnsupportedOperationException("NBT has no BigInteger tag");
    }

    @Override
    public BigDecimal getBigDecimal(final String key) {
        throw new UnsupportedOperationException("NBT has no BigDecimal tag");
    }

    @Override
    public @Nullable BigDecimal getBigDecimal(final String key, final @Nullable BigDecimal dfl) {
        throw new UnsupportedOperationException("NBT has no BigDecimal tag");
    }

    @Override
    public void setBigDecimal(final String key, final BigDecimal val) {
        throw new UnsupportedOperationException("NBT has no BigDecimal tag");
    }

    @Override
    public boolean getBoolean(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final ByteBinaryTag b)) {
            throw new IllegalStateException("Expected a byte tag at key " + key + ", got " + tag);
        }
        return b.value() != 0;
    }

    @Override
    public boolean getBoolean(final String key, final boolean dfl) {
        return map.get(key) instanceof final ByteBinaryTag b ? b.value() != 0 : dfl;
    }

    @Override
    public void setBoolean(final String key, final boolean val) {
        setByte(key, (byte) (val ? 1 : 0));
    }

    @Override
    public byte getByte(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at key " + key + ", got " + tag);
        }
        return n.byteValue();
    }

    @Override
    public byte getByte(final String key, final byte dfl) {
        return map.get(key) instanceof final NumberBinaryTag n ? n.byteValue() : dfl;
    }

    @Override
    public void setByte(final String key, final byte val) {
        map.put(key, ByteBinaryTag.byteBinaryTag(val));
    }

    @Override
    public short getShort(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at key " + key + ", got " + tag);
        }
        return n.shortValue();
    }

    @Override
    public short getShort(final String key, final short dfl) {
        return map.get(key) instanceof final NumberBinaryTag n ? n.shortValue() : dfl;
    }

    @Override
    public void setShort(final String key, final short val) {
        map.put(key, ShortBinaryTag.shortBinaryTag(val));
    }

    @Override
    public int getInt(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at key " + key + ", got " + tag);
        }
        return n.intValue();
    }

    @Override
    public int getInt(final String key, final int dfl) {
        return map.get(key) instanceof final NumberBinaryTag n ? n.intValue() : dfl;
    }

    @Override
    public void setInt(final String key, final int val) {
        map.put(key, IntBinaryTag.intBinaryTag(val));
    }

    @Override
    public long getLong(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at key " + key + ", got " + tag);
        }
        return n.longValue();
    }

    @Override
    public long getLong(final String key, final long dfl) {
        return map.get(key) instanceof final NumberBinaryTag n ? n.longValue() : dfl;
    }

    @Override
    public void setLong(final String key, final long val) {
        map.put(key, LongBinaryTag.longBinaryTag(val));
    }

    @Override
    public float getFloat(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at key " + key + ", got " + tag);
        }
        return n.floatValue();
    }

    @Override
    public float getFloat(final String key, final float dfl) {
        return map.get(key) instanceof final NumberBinaryTag n ? n.floatValue() : dfl;
    }

    @Override
    public void setFloat(final String key, final float val) {
        map.put(key, FloatBinaryTag.floatBinaryTag(val));
    }

    @Override
    public double getDouble(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final NumberBinaryTag n)) {
            throw new IllegalStateException("Expected a numeric tag at key " + key + ", got " + tag);
        }
        return n.doubleValue();
    }

    @Override
    public double getDouble(final String key, final double dfl) {
        return map.get(key) instanceof final NumberBinaryTag n ? n.doubleValue() : dfl;
    }

    @Override
    public void setDouble(final String key, final double val) {
        map.put(key, DoubleBinaryTag.doubleBinaryTag(val));
    }

    @Override
    public byte[] getBytes(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final ByteArrayBinaryTag t)) {
            throw new IllegalStateException("Expected a byte array tag at key " + key + ", got " + tag);
        }
        return t.value();
    }

    @Override
    public byte @Nullable [] getBytes(final String key, final byte @Nullable [] dfl) {
        return map.get(key) instanceof final ByteArrayBinaryTag t ? t.value() : dfl;
    }

    @Override
    public void setBytes(final String key, final byte[] val) {
        map.put(key, ByteArrayBinaryTag.byteArrayBinaryTag(val));
    }

    @Override
    public short[] getShorts(final String key) {
        throw new UnsupportedOperationException("NBT has no short array tag");
    }

    @Override
    public short @Nullable [] getShorts(final String key, final short @Nullable [] dfl) {
        throw new UnsupportedOperationException("NBT has no short array tag");
    }

    @Override
    public void setShorts(final String key, final short[] val) {
        throw new UnsupportedOperationException("NBT has no short array tag");
    }

    @Override
    public int[] getInts(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final IntArrayBinaryTag t)) {
            throw new IllegalStateException("Expected an int array tag at key " + key + ", got " + tag);
        }
        return t.value();
    }

    @Override
    public int @Nullable [] getInts(final String key, final int @Nullable [] dfl) {
        return map.get(key) instanceof final IntArrayBinaryTag t ? t.value() : dfl;
    }

    @Override
    public void setInts(final String key, final int[] val) {
        map.put(key, IntArrayBinaryTag.intArrayBinaryTag(val));
    }

    @Override
    public long[] getLongs(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final LongArrayBinaryTag t)) {
            throw new IllegalStateException("Expected a long array tag at key " + key + ", got " + tag);
        }
        return t.value();
    }

    @Override
    public long @Nullable [] getLongs(final String key, final long @Nullable [] dfl) {
        return map.get(key) instanceof final LongArrayBinaryTag t ? t.value() : dfl;
    }

    @Override
    public void setLongs(final String key, final long[] val) {
        map.put(key, LongArrayBinaryTag.longArrayBinaryTag(val));
    }

    @Override
    public NbtListType getListUnchecked(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final ListBinaryTag t)) {
            throw new IllegalStateException("Expected a list tag at key " + key + ", got " + tag);
        }
        return NbtListType.of(t);
    }

    @Override
    public @Nullable NbtListType getListUnchecked(final String key, final @Nullable ListType dfl) {
        return map.get(key) instanceof final ListBinaryTag t ? NbtListType.of(t) : (NbtListType) dfl;
    }

    @Override
    public void setList(final String key, final ListType val) {
        map.put(key, ((NbtListType) val).toListTag());
    }

    @Override
    public NbtMapType getMap(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final CompoundBinaryTag t)) {
            throw new IllegalStateException("Expected a compound tag at key " + key + ", got " + tag);
        }
        return NbtMapType.of(t);
    }

    @Override
    public @Nullable NbtMapType getMap(final String key, final @Nullable MapType dfl) {
        return map.get(key) instanceof final CompoundBinaryTag t ? NbtMapType.of(t) : (NbtMapType) dfl;
    }

    @Override
    public void setMap(final String key, final MapType val) {
        map.put(key, ((NbtMapType) val).toCompound());
    }

    @Override
    public String getString(final String key) {
        final BinaryTag tag = map.get(key);
        if (!(tag instanceof final StringBinaryTag t)) {
            throw new IllegalStateException("Expected a string tag at key " + key + ", got " + tag);
        }
        return t.value();
    }

    @Override
    public @Nullable String getString(final String key, final @Nullable String dfl) {
        return map.get(key) instanceof final StringBinaryTag t ? t.value() : dfl;
    }

    @Override
    public void setString(final String key, final String val) {
        map.put(key, StringBinaryTag.stringBinaryTag(val));
    }
}
