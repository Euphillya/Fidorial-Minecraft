package fr.euphyllia.fidorial.testplugin.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import fr.euphyllia.fidorial.testplugin.TestPlugin;
import fr.euphyllia.fidorial.testplugin.terrain.TestBiomes;
import fr.fidorial.command.CommandSender;
import fr.fidorial.command.CommandSource;
import fr.fidorial.command.argument.ArgumentTypes;
import fr.fidorial.world.biome.BiomeDefinition;
import fr.fidorial.world.biome.BiomeRegistry;
import fr.fidorial.world.environment.Attribute;
import fr.fidorial.world.environment.EnvironmentAttributes;
import net.kyori.adventure.key.Key;
import org.jspecify.annotations.Nullable;

import java.util.stream.Collectors;

import static fr.fidorial.command.Commands.argument;
import static fr.fidorial.command.Commands.literal;

public final class BiomeCommand {

    private static final String SAMPLE_JSON = """
            {
              "has_precipitation": true,
              "temperature": 0.35,
              "downfall": 0.8,
              "effects": {
                "water_color": "#4a6bd6",
                "grass_color": "#79c05a"
              },
              "attributes": {
                "minecraft:visual/sky_color": "#7ba4ff",
                "minecraft:visual/water_fog_end_distance": {
                  "modifier": "multiply",
                  "argument": 0.6
                },
                "minecraft:gameplay/can_pillager_patrol_spawn": false
              }
            }
            """;

    private static TestPlugin plugin;

    public BiomeCommand(final TestPlugin plugin) {
        BiomeCommand.plugin = plugin;
    }

    public LiteralCommandNode<CommandSource> create() {
        return literal("testbiome")
                .then(literal("list").executes(BiomeCommand::list))
                .then(literal("count").executes(BiomeCommand::count))
                .then(literal("json").executes(BiomeCommand::fromJson))
                .then(literal("info")
                        .then(argument("key", ArgumentTypes.key())
                                .executes(ctx -> info(ctx, ctx.getArgument("key", Key.class)))))
                .then(literal("register")
                        .then(argument("key", ArgumentTypes.key())
                                .executes(ctx -> register(ctx, ctx.getArgument("key", Key.class)))))
                .then(literal("unregister")
                        .then(argument("key", ArgumentTypes.key())
                                .executes(ctx -> unregister(ctx, ctx.getArgument("key", Key.class)))))
                .build();
    }

    private static BiomeRegistry biomes() {
        return plugin.server().biomes();
    }

    private static int list(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();
        final BiomeRegistry biomes = biomes();

        if (biomes.definitions().isEmpty()) {
            plugin.msg(sender, "<yellow>No biome is defined by the server.");
            return Command.SINGLE_SUCCESS;
        }

        plugin.msg(sender, "<gold>Biomes defined by the server:");
        for (final BiomeDefinition biome : biomes.definitions()) {
            plugin.msg(sender, "<gray> - <white>%s <gray>(network id <aqua>%d<gray>)"
                    .formatted(biome.key().asString(), biomes.networkId(biome.key())));
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int count(final CommandContext<CommandSource> ctx) {
        final BiomeRegistry biomes = biomes();
        plugin.msg(ctx.getSource().sender(),
                "<gold>%d biomes registered, <aqua>%d<gold> of them defined here. Fallback: <white>%s"
                        .formatted(biomes.totalRegistered(), biomes.definitions().size(),
                                biomes.fallback().asString()));
        return Command.SINGLE_SUCCESS;
    }

    private static int info(final CommandContext<CommandSource> ctx, final Key key) {
        final CommandSender sender = ctx.getSource().sender();
        final BiomeRegistry biomes = biomes();

        if (!biomes.contains(key)) {
            plugin.msg(sender, "<red>Unknown biome: " + key.asString());
            return 0;
        }

        plugin.msg(sender, "<gold>%s <gray>- network id <aqua>%d<gray>, custom: <white>%s"
                .formatted(key.asString(), biomes.networkId(key), biomes.isCustom(key)));

        biomes.definition(key).ifPresentOrElse(
                biome -> describe(sender, biome),
                () -> plugin.msg(sender,
                        "<gray>Vanilla biome: the client resolves it from its own pack, "
                                + "the server only keeps the key."));

        return Command.SINGLE_SUCCESS;
    }

    private static void describe(final CommandSender sender, final BiomeDefinition biome) {
        plugin.msg(sender, "<gray>  temperature <white>%s<gray> (%s), downfall <white>%s<gray>, precipitation <white>%s"
                .formatted(biome.temperature(), biome.temperatureModifier().id(),
                        biome.downfall(), biome.hasPrecipitation()));

        plugin.msg(sender, "<gray>  water <white>#%06x<gray>, grass <white>%s<gray>, modifier <white>%s"
                .formatted(biome.effects().waterColor(),
                        hex(biome.effects().grassColor()),
                        biome.effects().grassColorModifier().id()));

        final EnvironmentAttributes attributes = biome.attributes();
        if (attributes.isEmpty()) {
            plugin.msg(sender, "<gray>  no environment attribute");
            return;
        }

        plugin.msg(sender, "<gray>  sky <white>%s<gray>, fog <white>%s<gray>, water fog <white>%s"
                .formatted(attribute(attributes.skyColor()),
                        attribute(attributes.fogColor()),
                        attribute(attributes.waterFogColor())));

        if (!attributes.ambientParticles().isEmpty()) {
            plugin.msg(sender, "<gray>  particles <white>%s".formatted(
                    attributes.ambientParticles().stream()
                            .map(particle -> particle.type().asString() + " @ " + particle.probability())
                            .collect(Collectors.joining(", "))));
        }

        if (attributes.ambientSounds() != null) {
            plugin.msg(sender, "<gray>  ambient sounds present");
        }

        final Attribute<Float> distance = attributes.waterFogEndDistance();
        if (distance != null) {
            plugin.msg(sender, "<gray>  water fog distance <white>%s<gray> via <white>%s"
                    .formatted(distance.value(), distance.modifier().id()));
        }
    }

    private static int register(final CommandContext<CommandSource> ctx, final Key key) {
        final CommandSender sender = ctx.getSource().sender();
        final BiomeRegistry biomes = biomes();

        if (biomes.contains(key)) {
            plugin.msg(sender, "<red>" + key.asString() + " already exists.");
            return 0;
        }

        final BiomeDefinition clone = BiomeDefinition.builder(TestBiomes.VOLCANIC_PLAINS).key(key).build();
        biomes.register(clone);

        plugin.msg(sender, "<green>%s registered (network id %d). Reconnect to see it."
                .formatted(key.asString(), biomes.networkId(key)));
        return Command.SINGLE_SUCCESS;
    }

    private static int unregister(final CommandContext<CommandSource> ctx, final Key key) {
        final CommandSender sender = ctx.getSource().sender();

        try {
            if (!biomes().unregister(key)) {
                plugin.msg(sender, "<red>No biome registered under " + key.asString());
                return 0;
            }
        } catch (final IllegalArgumentException refused) {
            plugin.msg(sender, "<red>" + refused.getMessage());
            return 0;
        }

        plugin.msg(sender, "<green>%s removed. Every following network id shifted down by one."
                .formatted(key.asString()));
        return Command.SINGLE_SUCCESS;
    }

    private static int fromJson(final CommandContext<CommandSource> ctx) {
        final CommandSender sender = ctx.getSource().sender();
        final Key key = Key.key("fidorial", "datapack_sample");
        final BiomeRegistry biomes = biomes();

        if (biomes.contains(key)) {
            biomes.unregister(key);
        }

        try {
            final BiomeDefinition biome = biomes.registerFromJson(key, SAMPLE_JSON);
            plugin.msg(sender, "<green>JSON read: %s, temperature %s, sky %s"
                    .formatted(biome.key().asString(), biome.temperature(),
                            attribute(biome.attributes().skyColor())));
        } catch (final IllegalArgumentException invalid) {
            plugin.msg(sender, "<red>JSON rejected: " + invalid.getMessage());
            return 0;
        }

        return Command.SINGLE_SUCCESS;
    }

    private static String attribute(final @Nullable Attribute<Integer> attribute) {
        return attribute == null ? "inherited" : "#%06x".formatted(attribute.value());
    }

    private static String hex(final @Nullable Integer color) {
        return color == null ? "computed" : "#%06x".formatted(color);
    }
}
