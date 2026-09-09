package fr.euphyllia.fidorial.server.codecs.container;

import ca.spottedleaf.converter.types.MapType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.euphyllia.fidorial.server.codecs.item.ItemStackCodecs;
import fr.euphyllia.fidorial.server.world.storage.datafixers.DataFixerType;
import fr.euphyllia.fidorial.server.world.storage.datafixers.registry.DataFixersRegistry;
import fr.euphyllia.fidorial.server.world.storage.datafixers.util.nbt.NbtMapType;
import fr.fidorial.inventory.Container;
import fr.fidorial.item.ItemStack;
import fr.fidorial.item.data.DataComponentMap;
import io.papermc.adventurex.nbt.dfu.BinaryTagOps;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static fr.euphyllia.fidorial.server.codecs.CommonCodecs.KEY_CODEC;

public final class ContainerCodecs {

    private ContainerCodecs() {
    }

    private record SlotEntry(int slot, ItemStack stack) {
    }

    private static final MapCodec<SlotEntry> SLOT_ENTRY_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.BYTE.fieldOf("Slot").forGetter(e -> (byte) e.slot()),
            KEY_CODEC.fieldOf("id").forGetter(e -> e.stack().id()),
            Codec.INT.fieldOf("count").forGetter(e -> e.stack().count()),
            ItemStackCodecs.COMPONENT_MAP_CODEC.optionalFieldOf("components", DataComponentMap.EMPTY)
                    .forGetter(e -> e.stack().components())
    ).apply(instance, (slot, id, count, components) ->
            new SlotEntry(slot & 0xFF, new ItemStack(id, count, components))));

    public static <C extends Container> Codec<C> containerCodec(final Supplier<C> factory) {
        return SLOT_ENTRY_CODEC.codec().listOf().xmap(
                entries -> {
                    final C container = factory.get();
                    for (final SlotEntry entry : entries) {
                        if (entry.slot() < container.size() && entry.stack().count() > 0) {
                            container.set(entry.slot(), entry.stack());
                        }
                    }
                    return container;
                },
                container -> {
                    final List<SlotEntry> entries = new ArrayList<>();
                    for (int slot = 0; slot < container.size(); slot++) {
                        final ItemStack stack = container.get(slot);
                        if (!stack.isEmpty()) {
                            entries.add(new SlotEntry(slot, stack));
                        }
                    }
                    return entries;
                }
        );
    }

    public static <C extends Container> byte[] encode(
            final String rootName,
            final String listFieldName,
            final int dataVersion,
            final Codec<C> containerCodec,
            final C container
    ) throws IOException {
        final CompoundBinaryTag body = (CompoundBinaryTag) containerCodec.fieldOf(listFieldName).codec()
                .encodeStart(BinaryTagOps.binaryTagOps(), container)
                .getOrThrow(msg -> new IOException("Failed to encode " + rootName + ": " + msg));
        final CompoundBinaryTag root = body.putInt("DataVersion", dataVersion);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BinaryTagIO.writer().writeNamed(Map.entry(rootName, root), baos);
        return baos.toByteArray();
    }

    public static <C extends Container> C decode(final byte[] payload, final String listFieldName, final Codec<C> containerCodec, final DataFixerType fixerType) throws IOException {
        CompoundBinaryTag root = BinaryTagIO.reader().readNamed(new ByteArrayInputStream(payload)).getValue();

        final int sourceVersion = root.getInt("DataVersion");
        final int latest = DataFixersRegistry.latestDataFixerVersion();
        if (sourceVersion < latest) {
            final MapType fixed = DataFixersRegistry.update(fixerType, NbtMapType.of(root), sourceVersion);
            root = ((NbtMapType) fixed).toCompound();
        }

        return containerCodec.fieldOf(listFieldName).codec()
                .parse(BinaryTagOps.binaryTagOps(), root)
                .getOrThrow(msg -> new IOException("Failed to decode " + listFieldName + ": " + msg));
    }
}
