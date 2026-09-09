package fr.fidorial.moderation;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

import java.net.InetAddress;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * A service that manages bans.
 *
 * @since 0.1.0
 */
public interface BanManager {

    /**
     * Gets the active ban on a player identity.
     *
     * <p>An expired entry must not be returned; implementations are free to discard it.</p>
     *
     * @param uuid the player identity
     * @return the active ban, or empty when nothing bans the identity
     * @since 0.1.0
     */
    @Contract(pure = true)
    Optional<BanEntry.Profile> find(final UUID uuid);

    /**
     * Gets the active ban on a client address.
     *
     * <p>An expired entry must not be returned; implementations are free to discard it.</p>
     *
     * @param address the client address
     * @return the active ban, or empty when nothing bans the address
     * @since 0.1.0
     */
    @Contract(pure = true)
    Optional<BanEntry.Address> find(final InetAddress address);

    /**
     * Gets the active ban recorded under a name, matched case-insensitively.
     *
     * <p>Only entries carrying a recorded name can match, so this never resolves an address.</p>
     *
     * @param name the player name
     * @return the active ban, or empty
     * @since 0.1.0
     */
    @Contract(pure = true)
    Optional<BanEntry> findByName(final String name);

    /**
     * Gets whatever bans a connecting player, whether that is their identity or the address they
     * connect from.
     *
     * @param uuid    the player identity
     * @param address the address the client connects from, or {@code null} when unknown
     * @return the active ban, or empty when the player may connect
     * @since 0.1.0
     */
    @Contract(pure = true)
    default Optional<BanEntry> findAny(final UUID uuid, @Nullable final InetAddress address) {
        final Optional<BanEntry.Profile> byProfile = find(uuid);

        if (byProfile.isPresent() || address == null) {
            return byProfile.map(entry -> entry);
        }

        return find(address).map(entry -> entry);
    }

    /**
     * Checks whether a player identity is currently banned.
     *
     * @param uuid the player identity
     * @return {@code true} when the identity is banned
     * @since 0.1.0
     */
    @Contract(pure = true)
    default boolean isBanned(final UUID uuid) {
        return find(uuid).isPresent();
    }

    /**
     * Checks whether a client address is currently banned.
     *
     * @param address the client address
     * @return {@code true} when the address is banned
     * @since 0.1.0
     */
    @Contract(pure = true)
    default boolean isBanned(final InetAddress address) {
        return find(address).isPresent();
    }

    /**
     * Records a ban, replacing any existing entry for the same target.
     *
     * @param entry the ban to record
     * @return {@code true} when the target was not already banned
     * @since 0.1.0
     */
    boolean ban(BanEntry entry);

    /**
     * Lifts the ban on a player identity.
     *
     * @param uuid the player identity
     * @return {@code true} when a ban was actually lifted
     * @since 0.1.0
     */
    boolean pardon(final UUID uuid);

    /**
     * Lifts the ban on a client address.
     *
     * @param address the client address
     * @return {@code true} when a ban was actually lifted
     * @since 0.1.0
     */
    boolean pardon(final InetAddress address);

    /**
     * Gets the active bans.
     *
     * @return the bans, most recently issued first; expired entries are excluded
     * @since 0.1.0
     */
    @Contract(pure = true)
    Stream<BanEntry> bans();

    /**
     * Gets the active bans issued against a player identity.
     *
     * @return the bans, most recently issued first; expired entries are excluded
     * @since 0.1.0
     */
    @Contract(pure = true)
    default Stream<BanEntry.Profile> profileBans() {
        return bans().filter(BanEntry.Profile.class::isInstance).map(BanEntry.Profile.class::cast);
    }

    /**
     * Gets the active bans issued against a client address.
     *
     * @return the bans, most recently issued first; expired entries are excluded
     * @since 0.1.0
     */
    @Contract(pure = true)
    default Stream<BanEntry.Address> ipBans() {
        return bans().filter(BanEntry.Address.class::isInstance).map(BanEntry.Address.class::cast);
    }

    /**
     * Gets how many targets are currently banned.
     *
     * @return the number of active bans
     * @since 0.1.0
     */
    @Contract(pure = true)
    int totalBans();

    /**
     * Builds the message shown on the disconnect screen of a banned player.
     *
     * <p>This is the single place the message is produced: callers must not assemble their own,
     * so that an implementation replacing this one is actually the message players see.</p>
     *
     * @param entry the ban
     * @return the disconnect message
     * @since 0.1.0
     */
    @Contract(pure = true)
    Component disconnectMessage(final BanEntry entry);
}
