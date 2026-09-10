package fr.euphyllia.fidorial.testplugin.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import fr.euphyllia.fidorial.testplugin.TestPlugin;
import fr.fidorial.command.CommandSender;
import fr.fidorial.command.CommandSource;
import fr.fidorial.entity.Player;
import fr.fidorial.item.ItemDefaults;
import fr.fidorial.item.ItemStack;
import fr.fidorial.item.component.ItemLore;
import fr.fidorial.item.component.SwingAnimation;
import fr.fidorial.item.data.DataComponentMap;
import fr.fidorial.item.data.DataComponentTypes;
import fr.fidorial.registry.keys.ItemKeys;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.Collections;
import java.util.List;

import static fr.fidorial.command.Commands.literal;

public final class ItemCommand {
    private static TestPlugin plugin;
    public static final Key BEE_NEST = ItemKeys.BEE_NEST.key();
    private static final int HIVE_MIN_TICKS = 60;

    public ItemCommand(final TestPlugin plugin) {
        ItemCommand.plugin = plugin;
    }

    public LiteralCommandNode<CommandSource> create() {
        return literal("item")
                .then(literal("give").executes(ItemCommand::give))
                .then(literal("info").executes(ItemCommand::info))
                .then(literal("damage").executes(ItemCommand::damage))
                .then(literal("edit").executes(ItemCommand::edit))
                .build();
    }

    private static int give(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();
        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        final ItemStack sword = ItemStack.builder(ItemKeys.NETHERITE_SWORD.key())
                .edit(components -> {
                    components.set(DataComponentTypes.CUSTOM_NAME, Component.text("Fidorial's Fang", NamedTextColor.LIGHT_PURPLE)
                            .decoration(TextDecoration.ITALIC, false));
                    components.set(DataComponentTypes.ITEM_NAME, Component.text("Legendary sword"));
                    components.set(DataComponentTypes.DAMAGE, 80);
                    components.set(DataComponentTypes.MAX_DAMAGE, 250);
                    components.set(DataComponentTypes.ATTACK_ANIMATION, new SwingAnimation(SwingAnimation.SwingAnimationType.STAB, 2000));
                    final ItemLore lore = new ItemLore(List.of(
                            Component.text("A legendary sword forged in the depths of the Nether."),
                            Component.text("It is said to possess immense power and durability.")
                    ));
                    components.set(DataComponentTypes.LORE, lore);
                })
                .build();

        player.inventory().set(player.selectedSlot(), sword);
        player.updateInventory();

        plugin.msg(player, "[TestPlugin] You receive " + sword.translationKey());
        return Command.SINGLE_SUCCESS;
    }

    private static int info(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();
        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        final ItemStack held = player.inventory().get(player.selectedSlot());

        if (held.isEmpty()) {
            plugin.msg(player, "[TestPlugin] You aren't holding anything..");
            return Command.SINGLE_SUCCESS;
        }
        final DataComponentMap components = held.components();
        final Component itemName = components.get(DataComponentTypes.ITEM_NAME);
        final ItemLore lore = components.getOrDefault(DataComponentTypes.LORE, ItemLore.EMPTY);
        final int damage = components.getOrDefault(DataComponentTypes.DAMAGE, -1);
        final int maxDamage = components.getOrDefault(DataComponentTypes.MAX_DAMAGE, -1);
        final Component customName = components.get(DataComponentTypes.CUSTOM_NAME);
        final SwingAnimation customAnimation = components.get(DataComponentTypes.ATTACK_ANIMATION);

        player.sendMessage(Component.text("[TestPlugin] Item info:"));
        player.sendMessage(Component.text("  - Translation key: " + held.translationKey()));
        player.sendMessage(Component.text("  - Item name: " + (itemName != null ? itemName : "<none>")));
        for (final Component line : lore.lines()) {
            player.sendMessage(Component.text("    - Lore line: " + line));
        }
        player.sendMessage(Component.text("  - Lore: "+ (lore != null ? lore.lines() : "<none>")));
        player.sendMessage(Component.text("  - Damage: " + (damage != -1 ? damage : "<none>")));
        player.sendMessage(Component.text("  - Max damage: " + (maxDamage != -1 ? maxDamage : "<none>")));
        player.sendMessage(Component.text("  - Custom name: " + (customName != null ? customName : "<none>")));
        player.sendMessage(Component.text("  - Attack animation : " + (customAnimation != null ? customAnimation.toString() : "<none>")));



        return Command.SINGLE_SUCCESS;
    }

    private static int edit(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();
        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        final int slot = player.selectedSlot();
        final ItemStack held = player.inventory().get(slot);

        if (held.isEmpty()) {
            plugin.msg(player, "[TestPlugin] You aren't holding anything..");
            return Command.SINGLE_SUCCESS;
        }


        final ItemStack edited = held.edit(components -> {
            components.set(DataComponentTypes.CUSTOM_NAME, Component.text("Renamed via edit", NamedTextColor.GOLD)
                    .decoration(TextDecoration.ITALIC, false));
        });

        player.inventory().set(slot, edited);
        player.updateInventory();

        ItemLore lore = edited.components().getOrDefault(DataComponentTypes.LORE, ItemLore.EMPTY);
        final List<Component> loreLines = lore.lines();
        if (loreLines.isEmpty()) {
            lore = new ItemLore(Collections.singletonList(Component.text("This item has been edited!")));
            final ItemLore finalLore = lore;
            edited.edit(components -> components.set(DataComponentTypes.LORE, finalLore));
        }

        return Command.SINGLE_SUCCESS;
    }

    private static int damage(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();
        if (!(sender instanceof final Player player)) {
            plugin.msg(sender, "<red>[TestPlugin] Run this command in-game.</red>");
            return Command.SINGLE_SUCCESS;
        }

        final int slot = player.selectedSlot();
        final ItemStack held = player.inventory().get(slot);

        final int maxDamage = ItemDefaults.maxDamage(held.id(), held);

        if (held.isEmpty() || maxDamage <= 0) {
            plugin.msg(player, "[TestPlugin] This item cannot be damaged..");
            return Command.SINGLE_SUCCESS;
        }

        final ItemStack damaged = held.edit(
                components ->{
                    final int currentDamage = components.getOrDefault(DataComponentTypes.DAMAGE, 0);
                    components.set(DataComponentTypes.DAMAGE, currentDamage + 1);
                });
        player.inventory().set(slot, damaged);

        final int currentDamage = damaged.components().getOrDefault(DataComponentTypes.DAMAGE, 0);
        plugin.msg(player, "[TestPlugin] Damaged item: " + damaged.translationKey() + " (Damage: " + currentDamage + "/" + maxDamage + ")");

        return Command.SINGLE_SUCCESS;
    }
}
