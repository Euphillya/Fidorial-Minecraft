package fr.fidorial.moderation;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

import java.net.InetAddress;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a ban.
 *
 * @since 0.1.0
 */
public sealed interface BanEntry permits BanEntry.Profile, BanEntry.Address {

    DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT).withZone(ZoneId.systemDefault());


    /**
     * Gets the name recorded for display, which is the last known name of the player the ban was
     * issued against.
     *
     * @return the name, or {@code null} when none was recorded
     * @since 0.1.0
     */
    @Contract(pure = true)
    @Nullable String name();

    /**
     * Gets why the target is banned.
     *
     * @return the reason, or {@code null} when none was given
     * @since 0.1.0
     */
    @Contract(pure = true)
    @Nullable Component reason();

    /**
     * Gets who issued the ban.
     *
     * <p>This is an identity rather than a name, so an issuer who later changes name is still the
     * same issuer. A ban the server itself issued has no identity to record.</p>
     *
     * @return the identity of the issuer, or {@code null} when the server issued the ban
     * @since 0.1.0
     */
    @Contract(pure = true)
    @Nullable UUID source();

    /**
     * Gets when the ban was issued.
     *
     * @return the creation timestamp
     * @since 0.1.0
     */
    @Contract(pure = true)
    Instant created();

    /**
     * Gets when the ban lifts.
     *
     * @return the expiry timestamp, or {@code null} for a permanent ban
     * @since 0.1.0
     */
    @Contract(pure = true)
    @Nullable Instant expires();

    /**
     * Gets the label for what the ban applies to.
     *
     * @return the text identifying the target in messages
     * @since 0.1.0
     */
    @Contract(pure = true)
    String label();

    /**
     * Checks whether this ban never lifts on its own.
     *
     * @return {@code true} when the ban has no expiry
     * @since 0.1.0
     */
    @Contract(pure = true)
    default boolean permanent() {
        return expires() == null;
    }

    /**
     * Checks whether the expiry has already passed.
     *
     * <p>An expired entry is not an active ban. A {@link BanManager} is expected to stop
     * reporting it, and may discard it.</p>
     *
     * @return {@code true} when the ban has run out
     * @since 0.1.0
     */
    @Contract(pure = true)
    default boolean expired() {
        final Instant expires = expires();

        return expires != null && expires.isBefore(Instant.now());
    }

    /**
     * Gets how long is left before the ban lifts.
     *
     * @return the remaining time, or empty for a permanent ban; {@link Duration#ZERO} once the
     * expiry has passed
     * @since 0.1.0
     */
    @Contract(pure = true)
    default Optional<Duration> remaining() {
        final Instant expires = expires();

        if (expires == null) {
            return Optional.empty();
        }

        final Duration left = Duration.between(Instant.now(), expires);

        return Optional.of(left.isNegative() ? Duration.ZERO : left);
    }

    /**
     * Gets why the target is banned.
     *
     * @return the reason, or empty when none was given
     * @since 0.1.0
     */
    @Contract(pure = true)
    default Optional<Component> describeReason() {
        return Optional.ofNullable(reason());
    }

    /**
     * Gets when the ban was issued, as a readable date.
     *
     * @return the creation date
     * @since 0.1.0
     */
    @Contract(pure = true)
    default String createdLabel() {
        return FORMAT.format(created());
    }

    /**
     * Gets when the ban lifts, as a readable date.
     *
     * @return the expiry date, or an empty string for a permanent ban
     * @since 0.1.0
     */
    @Contract(pure = true)
    default String expiresLabel() {
        final Instant expires = expires();

        return expires == null ? "" : FORMAT.format(expires);
    }

    /**
     * A ban on a player identity.
     *
     * @param uuid  the banned identity
     * @param name    the last known name of the player, or {@code null}
     * @param reason  why the player is banned, or {@code null}
     * @param source  the identity of who issued the ban, or {@code null} for the server
     * @param created when the ban was issued
     * @param expires when the ban lifts, or {@code null} for a permanent ban
     * @since 0.1.0
     */
    record Profile(
            UUID uuid,
            @Nullable String name,
            @Nullable Component reason,
            @Nullable UUID source,
            Instant created,
            @Nullable Instant expires
    ) implements BanEntry {

        public Profile {
            Objects.requireNonNull(uuid, "uuid");
            Objects.requireNonNull(created, "created");
        }

        /**
         * Creates a ban on an identity that never lifts.
         *
         * @param uuid   the identity to ban
         * @param name   the name to record for display, or {@code null}
         * @param reason why the player is banned, or {@code null}
         * @param source the identity of who is issuing the ban, or {@code null} for the server
         * @return the ban
         * @since 0.1.0
         */
        @Contract(pure = true)
        public static Profile permanent(
                final UUID uuid,
                @Nullable final String name,
                @Nullable final Component reason,
                @Nullable final UUID source
        ) {
            return until(uuid, name, reason, source, null);
        }

        /**
         * Creates a ban on an identity lasting the given amount of time, counted from now.
         *
         * @param uuid     the identity to ban
         * @param name     the name to record for display, or {@code null}
         * @param reason   why the player is banned, or {@code null}
         * @param source   who is issuing the ban
         * @param duration how long the ban lasts
         * @return the ban
         * @since 0.1.0
         */
        @Contract(pure = true)
        public static Profile lasting(
                final UUID uuid,
                @Nullable final String name,
                @Nullable final Component reason,
                @Nullable final UUID source,
                final Duration duration
        ) {
            Objects.requireNonNull(duration, "duration");

            final Instant created = Instant.now();

            return new Profile(uuid, name, reason, source, created, created.plus(duration));
        }

        /**
         * Creates a ban on an identity lifting at the given instant.
         *
         * @param uuid    the identity to ban
         * @param name    the name to record for display, or {@code null}
         * @param reason  why the player is banned, or {@code null}
         * @param source  who is issuing the ban
         * @param expires when the ban lifts, or {@code null} for a permanent ban
         * @return the ban
         * @since 0.1.0
         */
        @Contract(pure = true)
        public static Profile until(
                final UUID uuid,
                @Nullable final String name,
                @Nullable final Component reason,
                @Nullable final UUID source,
                @Nullable final Instant expires
        ) {
            return new Profile(uuid, name, reason, source, Instant.now(), expires);
        }

        /**
         * {@inheritDoc}
         *
         * <p>The recorded name is preferred, since a raw identity means nothing to a moderator.</p>
         */
        @Contract(pure = true)
        @Override
        public String label() {
            return name != null ? name : uuid.toString();
        }
    }

    /**
     * A ban on a client address.
     *
     * @param address the banned address
     * @param name    the last known name of the player it was issued against, or {@code null}
     * @param reason  why the address is banned, or {@code null}
     * @param source  the identity of who issued the ban, or {@code null} for the server
     * @param created when the ban was issued
     * @param expires when the ban lifts, or {@code null} for a permanent ban
     * @since 0.1.0
     */
    record Address(
            InetAddress address,
            @Nullable String name,
            @Nullable Component reason,
            @Nullable UUID source,
            Instant created,
            @Nullable Instant expires
    ) implements BanEntry {

        public Address {
            Objects.requireNonNull(address, "address");
            Objects.requireNonNull(created, "created");
        }

        /**
         * Creates a ban on an address that never lifts.
         *
         * @param address the address to ban
         * @param name    the name to record for display, or {@code null}
         * @param reason  why the address is banned, or {@code null}
         * @param source  who is issuing the ban
         * @return the ban
         * @since 0.1.0
         */
        @Contract(pure = true)
        public static Address permanent(
                final InetAddress address,
                @Nullable final String name,
                @Nullable final Component reason,
                @Nullable final UUID source
        ) {
            return until(address, name, reason, source, null);
        }

        /**
         * Creates a ban on an address lasting the given amount of time, counted from now.
         *
         * @param address  the address to ban
         * @param name     the name to record for display, or {@code null}
         * @param reason   why the address is banned, or {@code null}
         * @param source   who is issuing the ban
         * @param duration how long the ban lasts
         * @return the ban
         * @since 0.1.0
         */
        @Contract(pure = true)
        public static Address lasting(
                final InetAddress address,
                @Nullable final String name,
                @Nullable final Component reason,
                @Nullable final UUID source,
                final Duration duration
        ) {
            Objects.requireNonNull(duration, "duration");

            final Instant created = Instant.now();

            return new Address(address, name, reason, source, created, created.plus(duration));
        }

        /**
         * Creates a ban on an address lifting at the given instant.
         *
         * @param address the address to ban
         * @param name    the name to record for display, or {@code null}
         * @param reason  why the address is banned, or {@code null}
         * @param source  who is issuing the ban
         * @param expires when the ban lifts, or {@code null} for a permanent ban
         * @return the ban
         * @since 0.1.0
         */
        @Contract(pure = true)
        public static Address until(
                final InetAddress address,
                @Nullable final String name,
                @Nullable final Component reason,
                @Nullable final UUID source,
                @Nullable final Instant expires
        ) {
            return new Address(address, name, reason, source, Instant.now(), expires);
        }

        /**
         * {@inheritDoc}
         *
         * <p>The address is what is banned, so it is what messages show. The recorded name is only
         * a note about who the ban was issued against, and several players can sit behind one
         * address.</p>
         */
        @Contract(pure = true)
        @Override
        public String label() {
            return address.getHostAddress();
        }
    }
}
