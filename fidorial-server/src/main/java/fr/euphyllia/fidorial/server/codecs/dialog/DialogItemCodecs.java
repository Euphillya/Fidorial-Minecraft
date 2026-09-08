package fr.euphyllia.fidorial.server.codecs.dialog;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.fidorial.item.ItemStack;
import fr.fidorial.item.component.ItemLore;
import fr.fidorial.item.data.DataComponentMap;
import fr.fidorial.item.data.DataComponentTypes;
import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.Optional;

import static fr.euphyllia.fidorial.server.codecs.CommonCodecs.KEY_CODEC;
import static fr.euphyllia.fidorial.server.codecs.adventure.ComponentCodecs.COMPONENT_CODEC;

public class DialogItemCodecs {

    private static final MapCodec<ItemComponents> COMPONENTS_CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    COMPONENT_CODEC.optionalFieldOf("custom_name")
                            .forGetter(ItemComponents::customName),
                    COMPONENT_CODEC.optionalFieldOf("item_name")
                            .forGetter(ItemComponents::itemName),
                    COMPONENT_CODEC.listOf().optionalFieldOf("lore")
                            .forGetter(ItemComponents::lore)
            ).apply(instance, ItemComponents::new));

    static final Codec<ItemStack> ITEM_STACK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            KEY_CODEC.fieldOf("id").forGetter(ItemStack::id),
            Codec.INT.optionalFieldOf("count", 1).forGetter(ItemStack::count),
            COMPONENTS_CODEC.codec().optionalFieldOf("components")
                    .forGetter(stack -> {
                        final ItemComponents components = ItemComponents.of(stack);
                        return components.isEmpty() ? Optional.empty() : Optional.of(components);
                    })
    ).apply(instance, (id, count, components) -> {
        final ItemComponents resolved = components.orElse(ItemComponents.EMPTY);
        final DataComponentMap.Builder builder = DataComponentMap.builder()
                .setIfPresent(DataComponentTypes.CUSTOM_NAME, resolved.customName().orElse(null))
                .setIfPresent(DataComponentTypes.ITEM_NAME, resolved.itemName().orElse(null))
                .setIfPresent(DataComponentTypes.LORE, resolved.lore().map(ItemLore::of).orElse(null));
        return new ItemStack(
                id,
                count,
                builder.build()
        );
    }));

    private DialogItemCodecs() {
        throw new UnsupportedOperationException("DialogItemCodecs cannot be instantiated.");
    }

    private record ItemComponents(
            Optional<Component> customName,
            Optional<Component> itemName,
            Optional<List<Component>> lore
    ) {

        static final ItemComponents EMPTY = new ItemComponents(Optional.empty(), Optional.empty(), Optional.empty());

        static ItemComponents of(final ItemStack stack) {
            final Component customName = stack.components().get(DataComponentTypes.CUSTOM_NAME);
            final Component itemName = stack.components().get(DataComponentTypes.ITEM_NAME);
            final ItemLore lore = stack.components().get(DataComponentTypes.LORE);
            final List<Component> loreList = lore != null ? lore.lines() : null;
            return new ItemComponents(
                    Optional.ofNullable(customName),
                    Optional.ofNullable(itemName),
                    Optional.ofNullable(loreList)
            );
        }

        boolean isEmpty() {
            return customName.isEmpty() && itemName.isEmpty() && lore.isEmpty();
        }
    }
}
