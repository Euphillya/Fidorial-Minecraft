import org.jspecify.annotations.NullMarked;

/**
 * Public API for writing Fidorial plugins.
 *
 * <p>Fidorial has no Forge, no Fabric and no Mixin. A plugin implements
 * {@link fr.fidorial.plugin.Plugin}, receives a
 * {@link fr.fidorial.plugin.PluginContext} on load, then <em>subscribes to
 * events</em> and <em>registers services</em> from there.
 *
 * <h2>Getting started</h2>
 * <ul>
 *   <li>{@link fr.fidorial.plugin} — plugin entry point and context</li>
 *   <li>{@link fr.fidorial.event} — observe and cancel server events</li>
 *   <li>{@link fr.fidorial.service} — replace default server behaviour</li>
 *   <li>{@link fr.fidorial.entity.mob} — bend the built-in mobs, or write new ones</li>
 *   <li>{@link fr.fidorial.scheduler} — hand long work off the region threads</li>
 *   <li>{@link fr.fidorial.dialog} — show modal windows and read what players fill in</li>
 *   <li>{@link fr.fidorial.registry.keys} — generated keys for blocks, items and more</li>
 * </ul>
 *
 * <h2>Threading</h2>
 * <p>The world is split into independent 32×32-chunk regions, each ticking at
 * 20 TPS on its own thread. Event listeners run on the thread of the region
 * owning the block or entity — never block inside one, hand long work to the
 * {@linkplain fr.fidorial.scheduler scheduler}.
 *
 * <h2>Nullability</h2>
 * <p>This module is {@link org.jspecify.annotations.NullMarked}: every type is
 * non-null unless explicitly annotated {@code @Nullable}.
 *
 * @see <a href="https://fidorial.euphyllia.moe">Documentation</a>
 * @see <a href="https://github.com/Euphillya/Fidorial">GitHub</a>
 */
@NullMarked
module fr.fidorial {
    exports fr.fidorial.attribute;
    exports fr.fidorial.combat;
    exports fr.fidorial.command;
    exports fr.fidorial.dialog;
    exports fr.fidorial.entity.ai;
    exports fr.fidorial.entity.mob;
    exports fr.fidorial.entity;
    exports fr.fidorial.event.entity;
    exports fr.fidorial.event.player;
    exports fr.fidorial.event.server;
    exports fr.fidorial.event;
    exports fr.fidorial.inventory;
    exports fr.fidorial.permission;
    exports fr.fidorial.plugin;
    exports fr.fidorial.registry.data;
    exports fr.fidorial.registry.keys;
    exports fr.fidorial.registry;
    exports fr.fidorial.scheduler;
    exports fr.fidorial.service;
    exports fr.fidorial.sound;
    exports fr.fidorial.status;
    exports fr.fidorial.storage.player;
    exports fr.fidorial.translation;
    exports fr.fidorial.world.block.data.type;
    exports fr.fidorial.world.block.data;
    exports fr.fidorial.world.block;
    exports fr.fidorial.world.fluid;
    exports fr.fidorial.world.generation;
    exports fr.fidorial.world.time;
    exports fr.fidorial.world.weather;
    exports fr.fidorial.world;
    exports fr.fidorial;
    exports fr.fidorial.command.argument;
    exports fr.fidorial.command.argument.resolvers.selector;
    exports fr.fidorial.command.argument.predicate;
    exports fr.fidorial.command.argument.range;
    exports fr.fidorial.command.argument.resolvers;
    exports fr.fidorial.world.entity;
    exports fr.fidorial.protocol;
    exports fr.fidorial.world.light;
    exports fr.fidorial.testing;
    exports fr.fidorial.testing.annotation;
    exports fr.fidorial.moderation;
    exports fr.fidorial.command.argument.custom;
    exports fr.fidorial.world.biome;
    exports fr.fidorial.world.environment;
    exports fr.fidorial.world.dimension;
    exports fr.fidorial.world.dimension.types;
    exports fr.fidorial.item;
    exports fr.fidorial.item.data;
    exports fr.fidorial.item.component;

    requires com.google.common;
    requires com.google.gson;
    requires java.desktop;
    requires net.kyori.adventure.api;
    requires net.kyori.adventure.key;
    requires net.kyori.adventure.nbt;
    requires net.kyori.adventure.text.logger.slf4j;
    requires net.kyori.adventure.text.minimessage;

    requires static org.jetbrains.annotations;
    requires static org.jspecify;
}
