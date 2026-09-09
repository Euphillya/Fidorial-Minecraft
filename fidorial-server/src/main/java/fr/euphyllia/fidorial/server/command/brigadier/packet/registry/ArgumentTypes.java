package fr.euphyllia.fidorial.server.command.brigadier.packet.registry;

import com.mojang.brigadier.arguments.ArgumentType;
import fr.euphyllia.fidorial.server.command.brigadier.argument.chat.ComponentArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.chat.HexColorArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.chat.NamedColorArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.chat.StyleArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.custom.ForcedSuggestionsArgumentType;
import fr.euphyllia.fidorial.server.command.brigadier.argument.entity.EntityArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.entity.UuidArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.generic.TimeArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.item.ItemArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.item.ItemPredicateArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.item.component.SwingAnimationTypeArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.location.AngleArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.location.BlockPositionArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.location.DimensionArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.location.Vec3Argument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.nbt.NbtDataArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.player.GameModeArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.player.PlayerProfileArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.primitive.BoolArgumentRegistrar;
import fr.euphyllia.fidorial.server.command.brigadier.argument.primitive.DoubleArgumentRegistrar;
import fr.euphyllia.fidorial.server.command.brigadier.argument.primitive.FloatArgumentRegistrar;
import fr.euphyllia.fidorial.server.command.brigadier.argument.primitive.IntegerArgumentRegistrar;
import fr.euphyllia.fidorial.server.command.brigadier.argument.primitive.LongArgumentRegistrar;
import fr.euphyllia.fidorial.server.command.brigadier.argument.primitive.StringArgumentRegistrar;
import fr.euphyllia.fidorial.server.command.brigadier.argument.range.RangeArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.resource.KeyArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.resource.ResourceArgument;
import fr.euphyllia.fidorial.server.command.brigadier.argument.resource.ResourceKeyArgument;
import fr.euphyllia.fidorial.server.registry.data.ArgumentTypeIds;

public final class ArgumentTypes {

    private ArgumentTypes() {
    }

    public static void bootstrap() {
        register(new BoolArgumentRegistrar(), ArgumentTypeIds.BOOL_ARGUMENT_ID);
        register(new FloatArgumentRegistrar(), ArgumentTypeIds.FLOAT_ARGUMENT_ID);
        register(new DoubleArgumentRegistrar(), ArgumentTypeIds.DOUBLE_ARGUMENT_ID);
        register(new IntegerArgumentRegistrar(), ArgumentTypeIds.INTEGER_ARGUMENT_ID);
        register(new LongArgumentRegistrar(), ArgumentTypeIds.LONG_ARGUMENT_ID);
        register(new StringArgumentRegistrar(), ArgumentTypeIds.STRING_ARGUMENT_ID);
        register(new EntityArgument.Info(), ArgumentTypeIds.ENTITY_ARGUMENT_ID);
        register(new PlayerProfileArgument.Info(), ArgumentTypeIds.GAME_PROFILE_ARGUMENT_ID);
        register(new BlockPositionArgument.Info(), ArgumentTypeIds.BLOCK_POS_ARGUMENT_ID);
        register(new Vec3Argument.Info(), ArgumentTypeIds.VEC3_ARGUMENT_ID);
        register(new ItemArgument.Info(), ArgumentTypeIds.ITEM_STACK_ARGUMENT_ID);
        register(new ItemPredicateArgument.Info(), ArgumentTypeIds.ITEM_PREDICATE_ARGUMENT_ID);
        register(new NamedColorArgument.Info(), ArgumentTypeIds.TEAM_COLOR_ARGUMENT_ID);
        register(new HexColorArgument.Info(), ArgumentTypeIds.HEX_COLOR_ARGUMENT_ID);
        register(new ComponentArgument.Info(), ArgumentTypeIds.COMPONENT_ARGUMENT_ID);
        register(new StyleArgument.Info(), ArgumentTypeIds.STYLE_ARGUMENT_ID);
        register(new NbtDataArgument.Info(), ArgumentTypeIds.NBT_PATH_ARGUMENT_ID);
        register(new AngleArgument.Info(), ArgumentTypeIds.ANGLE_ARGUMENT_ID);
        register(new KeyArgument.Info(), ArgumentTypeIds.RESOURCE_LOCATION_ARGUMENT_ID);
        register(new RangeArgument.Ints.Info(), ArgumentTypeIds.INT_RANGE_ARGUMENT_ID);
        register(new RangeArgument.Floats.Info(), ArgumentTypeIds.FLOAT_RANGE_ARGUMENT_ID);
        register(new DimensionArgument.Info(), ArgumentTypeIds.DIMENSION_ARGUMENT_ID);
        register(new GameModeArgument.Info(), ArgumentTypeIds.GAMEMODE_ARGUMENT_ID);
        register(new TimeArgument.Info(), ArgumentTypeIds.TIME_ARGUMENT_ID);
        register(new ResourceArgument.Info<>(), ArgumentTypeIds.RESOURCE_ARGUMENT_ID);
        register(new ResourceKeyArgument.Info<>(), ArgumentTypeIds.RESOURCE_KEY_ARGUMENT_ID);
        register(new SwingAnimationTypeArgument.Info(), ArgumentTypeIds.SWING_ANIMATION_ARGUMENT_ID);
        register(new UuidArgument.Info(), ArgumentTypeIds.UUID_ARGUMENT_ID);

        ArgumentTypeRegistry.register(new ForcedSuggestionsArgumentType.Info()); // custom arguments
    }

    private static <A extends ArgumentType<?>> void register(final ArgumentTypeRegistrar<A, ?> registrar, final int networkId) {
        ArgumentTypeRegistry.register(registrar);
        NetworkArgumentIds.register(networkId, registrar);
    }
}
