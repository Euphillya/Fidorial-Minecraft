package fr.euphyllia.fidorial.testplugin.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import fr.euphyllia.fidorial.testplugin.TestPlugin;
import fr.euphyllia.fidorial.testplugin.mob.BullMobs;
import fr.euphyllia.fidorial.testplugin.mob.CompanionMobs;
import fr.fidorial.command.CommandSource;
import fr.fidorial.entity.Player;
import fr.fidorial.entity.mob.Mob;
import net.kyori.adventure.key.Key;

import java.util.Optional;

import static fr.fidorial.command.Commands.literal;

public final class CustomMobCommand {

    private final TestPlugin plugin;

    public CustomMobCommand(final TestPlugin plugin) {
        this.plugin = plugin;
    }

    public LiteralCommandNode<CommandSource> create() {
        return literal("testmob")
                .then(literal("bull").executes(this::bull))
                .then(literal("companion").executes(this::companion))
                .build();
    }

    private int bull(final CommandContext<CommandSource> ctx) {
        return summon(ctx, BullMobs.BULL);
    }

    private int companion(final CommandContext<CommandSource> ctx) {
        return summon(ctx, CompanionMobs.COMPANION);
    }

    private int summon(
            final CommandContext<CommandSource> ctx,
            final Key key
    ) {
        if (!(ctx.getSource().sender() instanceof final Player player)) {
            plugin.msg(
                    ctx.getSource().sender(),
                    "<red>[TestPlugin] Cette commande doit être exécutée en jeu.</red>"
            );
            return Command.SINGLE_SUCCESS;
        }

        final Optional<Mob> mob = plugin.server()
                .mobs()
                .spawn(key, player.world(), player.location());

        if (mob.isEmpty()) {
            plugin.msg(
                    player,
                    "<red>[TestPlugin] Impossible d'invoquer <white>"
                            + key.asString()
                            + "<red>."
            );
            return 0;
        }

        plugin.msg(
                player,
                "<yellow>[TestPlugin] Invocation de <white>"
                        + key.asString()
                        + "<yellow>..."
        );

        return Command.SINGLE_SUCCESS;
    }
}
