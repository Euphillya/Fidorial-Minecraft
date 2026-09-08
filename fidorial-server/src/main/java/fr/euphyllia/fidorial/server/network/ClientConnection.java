package fr.euphyllia.fidorial.server.network;

import com.google.common.net.InetAddresses;
import fr.euphyllia.fidorial.auth.EncryptionUtils;
import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.entity.player.ServerPlayer;
import fr.euphyllia.fidorial.server.network.codec.CipherDecoder;
import fr.euphyllia.fidorial.server.network.codec.CipherEncoder;
import fr.euphyllia.fidorial.server.network.codec.CompressionDecoder;
import fr.euphyllia.fidorial.server.network.codec.CompressionEncoder;
import fr.euphyllia.fidorial.server.network.listener.ConfigurationPacketHandler;
import fr.euphyllia.fidorial.server.network.listener.HandshakePacketHandler;
import fr.euphyllia.fidorial.server.network.listener.LoginPacketHandler;
import fr.euphyllia.fidorial.server.network.listener.PlayPacketHandler;
import fr.euphyllia.fidorial.server.network.listener.StatusPacketHandler;
import fr.euphyllia.fidorial.server.network.protocol.ProtocolMap;
import fr.euphyllia.fidorial.server.network.protocol.catalog.ConfigurationClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.ServerboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.common.ClientboundResourcePackPopPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.common.ClientboundResourcePackPushPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.login.ClientboundLoginDisconnectPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundDisconnectPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play.ClientboundKeepAlivePacket;
import fr.euphyllia.fidorial.server.world.ServerWorld;
import fr.fidorial.entity.PlayerProfile;
import fr.fidorial.entity.RespawnPoint;
import fr.fidorial.event.player.PlayerRespawnEvent;
import fr.fidorial.protocol.PacketListener;
import fr.fidorial.protocol.ServerboundPacket;
import fr.fidorial.storage.player.PlayerDataStorage;
import fr.fidorial.translation.TranslationStore;
import fr.fidorial.world.Location;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.resource.ResourcePackCallback;
import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.resource.ResourcePackStatus;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// implement pointers and dialog methods in the future from audience
public final class ClientConnection extends SimpleChannelInboundHandler<ByteBuf> implements Audience {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(ClientConnection.class);

    private static final Executor VIRTUAL_THREAD_EXECUTOR = Executors.newVirtualThreadPerTaskExecutor();

    private static final int KEEP_ALIVE_INTERVAL_SECONDS = 10;
    private static final int LATENCY_SMOOTHING = 3;

    private final FidorialServer server;
    private final ProtocolMap protocol;

    private ChannelHandlerContext ctx;
    private ConnectionState state;
    private PacketListener listener;

    private int clientProtocol;
    private @Nullable String username;
    private @Nullable PlayerProfile profile;
    private @Nullable ServerPlayer player;
    private int displayedSkinParts = 0x7F; // toutes les couches activees par defaut
    private @Nullable String forwardedAddress;
    private Locale locale = TranslationStore.defaultLocale();
    private @Nullable ScheduledFuture<?> keepAliveTask;

    private volatile long pendingKeepAliveId;
    private volatile int latencyMillis;

    public ClientConnection(final FidorialServer server) {
        this.server = server;
        this.protocol = server.protocolMap();
        this.state = ConnectionState.HANDSHAKE;
        this.listener = new HandshakePacketHandler(this);
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final ByteBuf raw) {
        final PacketBuffer buf = new PacketBuffer(raw);
        final int packetId = buf.readVarInt();

        final Key name = protocol.serverboundName(state, packetId);
        if (name == null) {
            LOGGER.trace("Unknown packet {} 0x{} (ignored)", state, Integer.toHexString(packetId));
            return;
        }
        final ServerboundPacket packet = ServerboundPackets.decode(state, name, buf);
        if (packet == null) {
            LOGGER.trace("{}: {} received (unmanaged, ignored)", state, name);
            return;
        }
        packet.handle(listener);
    }

    public void setState(final ConnectionState newState) {
        this.state = newState;
        this.listener = createListener(newState);
        this.listener.onEnter();
    }

    private PacketListener createListener(final ConnectionState newState) {
        return switch (newState) {
            case HANDSHAKE -> new HandshakePacketHandler(this);
            case STATUS -> new StatusPacketHandler(this);
            case LOGIN -> new LoginPacketHandler(this);
            case CONFIGURATION -> new ConfigurationPacketHandler(this);
            case PLAY -> new PlayPacketHandler(this);
        };
    }

    public void send(final ClientboundPacket packet) {
        final ChannelFuture future = write(packet);
        if (future != null) {
            future.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
        }
    }

    public void sendAndClose(final ClientboundPacket packet) {
        final ChannelFuture future = write(packet);
        if (future != null) {
            future.addListener(ChannelFutureListener.CLOSE);
        } else {
            close();
        }
    }

    public void disconnect(final Component reason) {
        if (state == ConnectionState.LOGIN) {
            sendAndClose(ClientboundLoginDisconnectPacket.ofComponent(reason));
        } else {
            LOGGER.info(
                    Component.text("Disconnection of ")
                            .append(Component.text(username == null ? "<unknown>" : username)) // shouldn't ever be null but idea doesn't want to shut up
                            .append(Component.text(": "))
                            .append(reason)
            );
            sendAndClose(new ClientboundDisconnectPacket(reason));
        }
    }

    private @Nullable ChannelFuture write(final ClientboundPacket packet) {
        if (!isActive()) {
            return null;
        }

        final ByteBuf out = ctx.alloc().buffer();
        try {
            final PacketBuffer p = new PacketBuffer(out);
            p.writeVarInt(protocol.clientboundId(state, packet.name()));
            packet.write(p);
        } catch (final Throwable t) {
            out.release();
            throw t;
        }
        return ctx.writeAndFlush(out);
    }

    public void close() {
        ctx.close();
    }

    /**
     * Checks whether this connection is still usable.
     *
     * @return {@code true} if the underlying channel is open and connected
     */
    public boolean isActive() {
        return ctx != null && ctx.channel().isActive();
    }

    public void execute(final Runnable task) {
        ctx.channel().eventLoop().execute(task);
    }

    public void installEncryption(final SecretKey key) throws GeneralSecurityException {
        ctx.pipeline()
                .addBefore(
                        "frame-decoder",
                        "cipher-decoder",
                        new CipherDecoder(EncryptionUtils.createStreamCipher(Cipher.DECRYPT_MODE, key)));
        ctx.pipeline()
                .addBefore(
                        "frame-decoder",
                        "cipher-encoder",
                        new CipherEncoder(EncryptionUtils.createStreamCipher(Cipher.ENCRYPT_MODE, key)));
    }

    public void installCompression(final int threshold) {
        ctx.pipeline().addBefore("handler", "decompress", new CompressionDecoder(threshold));
        ctx.pipeline().addBefore("handler", "compress", new CompressionEncoder(threshold));
    }

    public void startKeepAlive() {
        keepAliveTask = ctx.channel()
                .eventLoop()
                .scheduleAtFixedRate(
                        this::sendKeepAlive,
                        KEEP_ALIVE_INTERVAL_SECONDS,
                        KEEP_ALIVE_INTERVAL_SECONDS,
                        TimeUnit.SECONDS);
    }

    private void sendKeepAlive() {
        final long id = System.currentTimeMillis();
        pendingKeepAliveId = id;
        send(new ClientboundKeepAlivePacket(id));
    }

    public void acknowledgeKeepAlive(final long id) {
        if (id != pendingKeepAliveId || id == 0L) {
            return;
        }
        pendingKeepAliveId = 0L;

        final long sample = Math.clamp(System.currentTimeMillis() - id, 0L, Integer.MAX_VALUE);
        final int previous = latencyMillis;
        latencyMillis = previous == 0
                ? (int) sample
                : (int) ((previous * (long) LATENCY_SMOOTHING + sample) / (LATENCY_SMOOTHING + 1));
    }

    public void pauseKeepAlive() {
        if (keepAliveTask != null) {
            keepAliveTask.cancel(false);
            keepAliveTask = null;
        }
    }

    public int ping() {
        return latencyMillis;
    }

    @Override
    public void handlerRemoved(final ChannelHandlerContext ctx) {
        if (keepAliveTask != null) {
            keepAliveTask.cancel(false);
        }
        try {
            listener.onDisconnect();
        } catch (final Throwable t) {
            LOGGER.error("Error during onDisconnect", t);
        }
        server.removePlayerConnection(this);
        saveInventoryOnDisconnect();
    }

    public void enterConfiguration() {
        execute(() -> {
            if (listener instanceof final PlayPacketHandler play) {
                play.enterConfiguration();
            }
        });
    }

    public CompletableFuture<Void> saveInventoryOnDisconnect() {
        final ServerPlayer disconnecting = this.player;
        if (disconnecting == null) {
            return CompletableFuture.completedFuture(null);
        }
        this.player = null;
        return CompletableFuture.runAsync(() -> {
            try {
                server.playerInventoryStorage().save(disconnecting.uuid(), disconnecting.inventory());
                server.playerEnderChestStorage().save(disconnecting.uuid(), disconnecting.enderChest());
                final RespawnPoint point = disconnecting.respawnPoint();
                server.playerDataStorage().save(disconnecting.uuid(), new PlayerDataStorage.PlayerData(
                        disconnecting.gameMode(),
                        point == null ? null : point.world().key(),
                        point == null ? null : point.location()));
                LOGGER.debug("Inventory + Ender Chest and data for {} saved", disconnecting.name());
            } catch (final Exception e) {
                LOGGER.error("Unable to save inventory for {}", disconnecting.name(), e);
            }
        }, VIRTUAL_THREAD_EXECUTOR);
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
        LOGGER.debug("Connexion closed", cause);
        ctx.close();
    }

    public FidorialServer server() {
        return server;
    }

    public ConnectionState state() {
        return state;
    }

    public int clientProtocol() {
        return clientProtocol;
    }

    public void setClientProtocol(final int clientProtocol) {
        this.clientProtocol = clientProtocol;
    }

    public @Nullable String username() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public @Nullable String forwardedAddress() {
        return forwardedAddress;
    }

    /**
     * Gets the address this client is connecting from.
     *
     * <p>When a proxy is in front of the server, the address it forwarded takes precedence over the
     * socket's own, which would be the proxy.</p>
     *
     * @return the client address, or {@code "unknown"} if the channel has none
     */
    public String remoteAddress() {
        if (forwardedAddress != null) {
            return forwardedAddress;
        }
        final SocketAddress address = ctx.channel().remoteAddress();
        if (address instanceof final InetSocketAddress inet) {
            return inet.getHostString();
        }
        return address == null ? "unknown" : address.toString();
    }

    public InetAddress remoteInetAddress() {
        if (forwardedAddress != null) {
            return InetAddresses.forString(forwardedAddress);
        }

        final SocketAddress address = ctx.channel().remoteAddress();
        if (address instanceof final InetSocketAddress inet && inet.getAddress() != null) {
            return inet.getAddress();
        }

        throw new IllegalStateException("No IP address for this connection: " + address);
    }

    public void setForwardedAddress(final String forwardedAddress) {
        this.forwardedAddress = forwardedAddress;
    }

    public @Nullable PlayerProfile profile() {
        return profile;
    }

    public void setProfile(final PlayerProfile profile) {
        this.profile = profile;
    }

    public int displayedSkinParts() {
        return displayedSkinParts;
    }

    public void setDisplayedSkinParts(final int displayedSkinParts) {
        this.displayedSkinParts = displayedSkinParts;
    }

    public Locale locale() {
        return locale;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

    public @Nullable ServerPlayer player() {
        return player;
    }

    public void setPlayer(final ServerPlayer player) {
        this.player = player;
    }

    public boolean teleport(final ServerWorld target, final Location location) {
        if (listener instanceof final PlayPacketHandler play) {
            return play.teleport(target, location);
        }
        return false;
    }

    public boolean respawn(final PlayerRespawnEvent.Cause cause) {
        if (listener instanceof final PlayPacketHandler play) {
            return play.respawn(cause);
        }
        return false;
    }

    private final Map<UUID, ResourcePackCallback> pendingResourcePacks = new ConcurrentHashMap<>();

    @Override
    public void sendResourcePacks(final ResourcePackRequest request) {
        LOGGER.info("ClientConnection.sendResourcePacks called for {} (packs={})", username, request.packs().size());
        if (state != ConnectionState.CONFIGURATION && state != ConnectionState.PLAY) {
            return;
        }
        final Key pushName = state == ConnectionState.PLAY
                ? PlayClientboundPackets.RESOURCE_PACK_PUSH
                : ConfigurationClientboundPackets.RESOURCE_PACK_PUSH;

        final ResourcePackCallback cb = request.callback();
        request.packs().forEach(pack -> pendingResourcePacks.put(pack.id(), cb));

        for (final ResourcePackInfo pack : request.packs()) {
            send(new ClientboundResourcePackPushPacket(
                    pushName,
                    pack.id(),
                    pack.uri().toString(),
                    pack.hash(),
                    request.required(),
                    request.prompt()));
        }
    }

    @Override
    public void removeResourcePacks(final UUID id, final UUID... others) {
        popPack(id);
        for (final UUID other : others) {
            popPack(other);
        }
    }

    @Override
    public void clearResourcePacks() {
        popPack(null);
    }

    private void popPack(final @Nullable UUID id) {
        final Key popName = state == ConnectionState.PLAY
                ? PlayClientboundPackets.RESOURCE_PACK_POP
                : ConfigurationClientboundPackets.RESOURCE_PACK_POP;
        send(new ClientboundResourcePackPopPacket(popName, id));
    }

    public void notifyResourcePackResponse(final UUID id, final ResourcePackStatus status) {
        final ResourcePackCallback callback = status.intermediate()
                ? pendingResourcePacks.get(id)
                : pendingResourcePacks.remove(id);
        if (callback != null) {
            callback.packEventReceived(id, status, this.player != null ? this.player : this);
        }
    }

    private final List<Component> pendingMessages = new ArrayList<>();

    @Override
    public void sendMessage(final Component message) {
        if (state == ConnectionState.PLAY && this.player != null) {
            this.player.sendMessage(message);
            return;
        }
        // we're too early to send a message so just collect it
        pendingMessages.add(message);
    }

    public void flushPendingMessages() {
        if (pendingMessages.isEmpty()) {
            return;
        }
        final List<Component> queued = List.copyOf(pendingMessages);
        pendingMessages.clear();
        for (final Component message : queued) {
            sendMessage(message);
        }
    }
}
