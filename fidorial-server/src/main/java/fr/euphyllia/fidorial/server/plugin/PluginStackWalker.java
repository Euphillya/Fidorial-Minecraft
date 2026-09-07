package fr.euphyllia.fidorial.server.plugin;

import org.jspecify.annotations.Nullable;

import java.lang.StackWalker.StackFrame;

public final class PluginStackWalker {

    private static final StackWalker WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    private PluginStackWalker() {
    }

    public static @Nullable String firstPluginCaller() {
        return WALKER.walk(frames -> frames
                        .map(StackFrame::getDeclaringClass)
                        .map(Class::getClassLoader)
                        .filter(PluginClassLoader.class::isInstance)
                        .map(PluginClassLoader.class::cast)
                        .map(PluginClassLoader::pluginId)
                        .findFirst())
                .orElse(null);
    }
}
