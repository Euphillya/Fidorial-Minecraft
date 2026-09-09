package fr.fidorial.event.player;

import fr.fidorial.entity.Entity;
import fr.fidorial.entity.Player;
import fr.fidorial.event.entity.EntityDeathEvent;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.Nullable;

/**
 * @since 0.1.0
 */
public final class PlayerDeathEvent extends EntityDeathEvent {

    private @Nullable Component deathMessage;

    public PlayerDeathEvent(final Player player, final @Nullable Entity killer, final @Nullable Component deathMessage) {
        super(player, killer);
        this.deathMessage = deathMessage;
    }

    /**
     * @return the message broadcast to the server, or {@code null} if the death stays silent
     */
    public @Nullable Component deathMessage() {
        return deathMessage;
    }

    /**
     * @param deathMessage the message to broadcast instead, or {@code null} to broadcast nothing
     */
    public void setDeathMessage(final @Nullable Component deathMessage) {
        this.deathMessage = deathMessage;
    }
}
