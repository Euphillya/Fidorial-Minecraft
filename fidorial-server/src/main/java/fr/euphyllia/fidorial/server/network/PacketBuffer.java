package fr.euphyllia.fidorial.server.network;

import fr.euphyllia.fidorial.server.network.nbt.NbtIo;
import fr.fidorial.registry.RegistryKey;
import fr.fidorial.world.BlockPos;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.BinaryTagTypes;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.Nullable;

import java.util.BitSet;
import java.util.UUID;

public final class PacketBuffer {

    private final ByteBuf buf;

    public PacketBuffer(final ByteBuf buf) {
        this.buf = buf;
    }

    public ByteBuf nettyBuf() {
        return buf;
    }

    public int readableBytes() {
        return buf.readableBytes();
    }

    public int readVarInt() {
        return VarInts.readVarInt(buf);
    }

    public PacketBuffer writeVarInt(final int value) {
        VarInts.writeVarInt(buf, value);
        return this;
    }

    public long readVarLong() {
        return VarInts.readVarLong(buf);
    }

    public PacketBuffer writeVarLong(final long value) {
        VarInts.writeVarLong(buf, value);
        return this;
    }

    public BitSet readFixedBitSet(final int bits) {
        final int bytes = (bits + 7) / 8;

        final byte[] data = new byte[bytes];
        buf.readBytes(data);

        return BitSet.valueOf(data);
    }

    public PacketBuffer writeFixedBitSet(final BitSet bitSet, final int bits) {
        final int bytes = (bits + 7) / 8;
        final byte[] data = new byte[bytes];

        final byte[] source = bitSet.toByteArray();
        System.arraycopy(source, 0, data, 0, Math.min(source.length, data.length));

        buf.writeBytes(data);
        return this;
    }

    public boolean readBoolean() {
        return buf.readBoolean();
    }

    public byte readByte() {
        return buf.readByte();
    }

    public int readUByte() {
        return buf.readUnsignedByte();
    }

    public short readShort() {
        return buf.readShort();
    }

    public int readUShort() {
        return buf.readUnsignedShort();
    }

    public int readInt() {
        return buf.readInt();
    }

    public long readLong() {
        return buf.readLong();
    }

    public float readFloat() {
        return buf.readFloat();
    }

    public double readDouble() {
        return buf.readDouble();
    }

    public @Nullable BinaryTag readSizedOptionalNbt(final int maxBytes) {
        final int size = readVarInt();
        if (size < 0 || size > maxBytes) {
            throw new DecoderException("NBT payload too large: " + size);
        }

        if (buf.readableBytes() < size) {
            throw new DecoderException("NBT payload truncated: expected " + size + " bytes, got " + buf.readableBytes());
        }

        final ByteBuf slice = buf.readSlice(size);
        if (!slice.isReadable()) {
            return null;
        }

        if (slice.getByte(slice.readerIndex()) == BinaryTagTypes.END.id()) {
            slice.skipBytes(1);
            if (slice.isReadable()) {
                throw new DecoderException("Empty NBT payload has " + slice.readableBytes() + " unread bytes");
            }
            return null;
        }

        final BinaryTag nbt = NbtIo.readNbt(slice, maxBytes);
        if (slice.isReadable()) {
            throw new DecoderException("NBT payload has " + slice.readableBytes() + " unread bytes");
        }

        return nbt;
    }

    public BinaryTag readSizedNbt(final int maxBytes) {
        final int size = readVarInt();
        if (size < 0 || size > maxBytes) {
            throw new DecoderException("NBT payload too large: " + size);
        }

        if (buf.readableBytes() < size) {
            throw new DecoderException("NBT payload truncated: expected " + size + " bytes, got " + buf.readableBytes());
        }

        final ByteBuf slice = buf.readSlice(size);
        final BinaryTag nbt = NbtIo.readNbt(slice, maxBytes);
        if (slice.isReadable()) {
            throw new DecoderException("NBT payload has " + slice.readableBytes() + " unread bytes");
        }

        return nbt;
    }

    public PacketBuffer writeBoolean(final boolean v) {
        buf.writeBoolean(v);
        return this;
    }

    public PacketBuffer writeByte(final int v) {
        buf.writeByte(v);
        return this;
    }

    public PacketBuffer writeShort(final int v) {
        buf.writeShort(v);
        return this;
    }

    public PacketBuffer writeInt(final int v) {
        buf.writeInt(v);
        return this;
    }

    public PacketBuffer writeLong(final long v) {
        buf.writeLong(v);
        return this;
    }

    public PacketBuffer writeFloat(final float v) {
        buf.writeFloat(v);
        return this;
    }

    public PacketBuffer writeDouble(final double v) {
        buf.writeDouble(v);
        return this;
    }

    public String readString(final int maxLength) {
        return VarInts.readString(buf, maxLength);
    }

    public PacketBuffer writeString(final String value) {
        VarInts.writeString(buf, value);
        return this;
    }

    public Key readKey() {
        final String read = this.readString(32767);
        return Key.key(read);
    }

    public PacketBuffer writeKey(final Key key) {
        this.writeString(key.asString());
        return this;
    }

    public PacketBuffer writeKeyArray(final Key[] keys) {
        writeVarInt(keys.length);
        for (final Key key : keys) {
            writeKey(key);
        }
        return this;
    }

    public <T> RegistryKey<T> readRegistryKey() {
        final Key key = this.readKey();
        return RegistryKey.of(key);
    }

    public PacketBuffer writeRegistryKey(final RegistryKey<?> key) {
        this.writeKey(key.key());
        return this;
    }

    public byte[] readByteArray(final int maxLength) {
        return VarInts.readByteArray(buf, maxLength);
    }

    public byte @Nullable [] readOptionalByteArray(final int maxLength) {
        if (!readBoolean()) {
            return null;
        }
        return VarInts.readByteArray(buf, maxLength);
    }

    public byte[] readRemainingBytes() {
        final byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);
        return data;
    }

    public PacketBuffer writeByteArray(final byte[] data) {
        VarInts.writeByteArray(buf, data);
        return this;
    }

    public PacketBuffer writeComponent(final Component message) {
        VarInts.writeComponent(buf, message);
        return this;
    }

    public Component readComponent(final int maxLength) {
        return VarInts.readComponent(buf, maxLength);
    }

    public PacketBuffer writeNbt(final @Nullable CompoundBinaryTag nbt) {
        if (nbt == null) {
            buf.writeByte(BinaryTagTypes.END.id());
            return this;
        }

        NbtIo.writeNbt(buf, nbt);
        return this;
    }

    public PacketBuffer writeRawBytes(final byte[] data) {
        buf.writeBytes(data);
        return this;
    }

    public PacketBuffer writeAngle(final float degrees) {
        buf.writeByte((int) (degrees * 256f / 360f));
        return this;
    }

    private static final double LP_VEC3_ABS_MAX = 1.7179869183E10;
    private static final double LP_VEC3_ABS_MIN = 3.051944088384301E-5;
    private static final double LP_VEC3_MAX_QUANTIZED = 32766.0;

    public PacketBuffer writeLpVec3(double x, double y, double z) {
        x = lpSanitize(x);
        y = lpSanitize(y);
        z = lpSanitize(z);
        final double max = Math.max(Math.abs(x), Math.max(Math.abs(y), Math.abs(z)));
        if (max < LP_VEC3_ABS_MIN) {
            buf.writeByte(0);
            return this;
        }
        final long scale = (long) Math.ceil(max);
        final boolean continuation = (scale & 0b11L) != scale;
        final long flags = continuation ? (scale & 0b11L) | 0b100L : scale;
        final long packed = flags | lpPack(x / scale) << 3 | lpPack(y / scale) << 18 | lpPack(z / scale) << 33;
        buf.writeByte((int) packed);
        buf.writeByte((int) (packed >> 8));
        buf.writeInt((int) (packed >> 16));
        if (continuation) {
            writeVarInt((int) (scale >> 2));
        }
        return this;
    }

    private static double lpSanitize(final double value) {
        return Double.isNaN(value) ? 0.0 : Math.clamp(value, -LP_VEC3_ABS_MAX, LP_VEC3_ABS_MAX);
    }

    private static long lpPack(final double value) {
        return Math.round((value * 0.5 + 0.5) * LP_VEC3_MAX_QUANTIZED);
    }


    public double[] readLpVec3() {
        final int first = readUByte();
        if (first == 0) {
            return new double[]{0.0, 0.0, 0.0};
        }
        final long second = readUByte();
        final long rest = readInt() & 0xFFFFFFFFL;
        final long packed = first | second << 8 | rest << 16;

        final long flags = packed & 0b111L;
        final long scale = (flags & 0b100L) != 0
                ? ((long) readVarInt() << 2) | (flags & 0b11L)
                : flags;

        return new double[]{
                lpUnpack(packed >> 3, scale),
                lpUnpack(packed >> 18, scale),
                lpUnpack(packed >> 33, scale)};
    }

    private static double lpUnpack(final long packed, final long scale) {
        final long quantized = packed & 0x7FFFL;
        return (quantized / LP_VEC3_MAX_QUANTIZED - 0.5) * 2.0 * scale;
    }

    public UUID readUuid() {
        return new UUID(buf.readLong(), buf.readLong());
    }

    public PacketBuffer writeUuid(final UUID uuid) {
        buf.writeLong(uuid.getMostSignificantBits());
        buf.writeLong(uuid.getLeastSignificantBits());
        return this;
    }

    public BlockPos readPosition() {
        final long packed = buf.readLong();
        final int x = (int) (packed >> 38);
        final int y = (int) (packed << 52 >> 52);
        final int z = (int) (packed << 26 >> 38);
        return new BlockPos(x, y, z);
    }

    public PacketBuffer writePosition(final int x, final int y, final int z) {
        final long packed = ((long) (x & 0x3FFFFFF) << 38) | ((long) (z & 0x3FFFFFF) << 12) | (y & 0xFFF);
        buf.writeLong(packed);
        return this;
    }

    public PacketBuffer writeVarIntArray(final int[] values) {
        VarInts.writeVarInt(buf, values.length);
        for (final int v : values) VarInts.writeVarInt(buf, v);
        return this;
    }

    public PacketBuffer writeLongArray(final long[] values) {
        VarInts.writeVarInt(buf, values.length);
        for (final long v : values) buf.writeLong(v);
        return this;
    }

    public PacketBuffer writeBitSet(final long[] words) {
        return writeByteArray(BitSet.valueOf(words).toByteArray());
    }
}
