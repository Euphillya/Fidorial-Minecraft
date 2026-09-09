package fr.euphyllia.fidorial.server.inventory;

import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.fidorial.inventory.Container;
import fr.fidorial.world.BlockPos;
import net.kyori.adventure.text.Component;

public final class EnderChestMenu extends ContainerMenu {

    private final BlockPos position;

    public EnderChestMenu(final ServerPlayer player, final int windowId, final BlockPos position) {
        super(player, windowId);
        this.position = position;
    }

    public BlockPos position() {
        return position;
    }

    @Override
    protected Container top() {
        return player.enderChest();
    }

    @Override
    public int menuTypeId(final RegistryHolder frozen) {
        return MenuTypes.genericChest3Rows(frozen);
    }

    @Override
    public Component title() {
        return Component.translatable("container.enderchest");
    }
}
