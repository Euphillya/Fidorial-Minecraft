package fr.euphyllia.fidorial.server.network.listener;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.adventure.ClickCallbackManager;
import fr.euphyllia.fidorial.server.network.ClientConnection;
import fr.euphyllia.fidorial.server.network.ConnectionState;
import fr.euphyllia.fidorial.server.network.protocol.catalog.ConfigurationClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.common.ClientboundResourcePackPushPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.configuration.ClientboundBrandPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.configuration.ClientboundCodeOfConductPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.configuration.ClientboundFinishConfigurationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.configuration.ClientboundRegistryDataPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.configuration.ClientboundSelectKnownPacksPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.configuration.ClientboundUpdateTagsPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.listener.ConfigurationPacketListener;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.common.ServerboundClientInformationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.configuration.ServerboundAcceptCodeOfConductPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.configuration.ServerboundCustomClickActionPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.configuration.ServerboundFinishConfigurationPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.configuration.ServerboundResourcePackPacket;
import fr.euphyllia.fidorial.server.network.protocol.packet.serverbound.configuration.ServerboundSelectKnownPacksPacket;
import fr.euphyllia.fidorial.server.registry.Registry;
import fr.euphyllia.fidorial.server.registry.RegistryHolder;
import fr.euphyllia.fidorial.server.registry.biome.FidorialBiomeRegistry;
import fr.euphyllia.fidorial.server.registry.dialog.FidorialDialogRegistry;
import fr.euphyllia.fidorial.server.registry.dimension.FidorialDimensionTypeRegistry;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import static fr.euphyllia.fidorial.server.VersionConstants.MINECRAFT_VERSION_ID;

public final class ConfigurationPacketHandler implements ConfigurationPacketListener {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(ConfigurationPacketHandler.class);

    private final ClientConnection connection;
    private final FidorialServer server;
    private volatile boolean awaitingResourcePackResponse = false;
    private final Object codeOfConductLock = new Object();
    private boolean clientInformationReceived = false;
    private boolean codeOfConductPending = false;
    private volatile boolean awaitingCodeOfConduct = false;

    public ConfigurationPacketHandler(final ClientConnection connection) {
        this.connection = connection;
        this.server = connection.server();
    }

    @Override
    public void onEnter() {
        LOGGER.debug("{} enters the Configuration phase", connection.username());
        if (!server.protocolMap().isAvailable()) {
            LOGGER.error(
                    "Protocol table missing: unable to configure {}.",
                    connection.username());
            connection.close();
            return;
        }
        connection.send(new ClientboundBrandPacket(FidorialServer.getInstance().brandName()));
        if (sendResourcePackIfConfigured()) {
            awaitingResourcePackResponse = true;
        } else {
            proceedToCodeOfConduct();
        }
    }

    private void proceedToKnownPacks() {
        connection.send(new ClientboundSelectKnownPacksPacket("minecraft", "core", MINECRAFT_VERSION_ID));
    }

    private void proceedToCodeOfConduct() {
        if (!server.codeOfConduct().enabled()) {
            proceedToKnownPacks();
            return;
        }
        synchronized (codeOfConductLock) {
            codeOfConductPending = true;
        }
        sendCodeOfConductIfReady();
    }

    private void sendCodeOfConductIfReady() {
        final String contents;
        synchronized (codeOfConductLock) {
            if (!codeOfConductPending || !clientInformationReceived) {
                return;
            }
            contents = server.codeOfConduct().contentFor(connection.locale());
            codeOfConductPending = false;
            if (contents != null) {
                awaitingCodeOfConduct = true;
            }
        }
        if (contents == null) {
            LOGGER.warn(
                    "No Code of Conduct available for {} ({}); skipping the screen.",
                    connection.username(),
                    connection.locale());
            proceedToKnownPacks();
            return;
        }
        LOGGER.debug("Sending the Code of Conduct to {} ({})", connection.username(), connection.locale());
        connection.send(new ClientboundCodeOfConductPacket(contents));
    }

    @Override
    public void handleAcceptCodeOfConduct(final ServerboundAcceptCodeOfConductPacket packet) {
        if (!awaitingCodeOfConduct) {
            LOGGER.debug("{} accepted a Code of Conduct that was never sent; ignored.", connection.username());
            return;
        }
        awaitingCodeOfConduct = false;
        LOGGER.info("{} acknowledged the Code of Conduct", connection.username());
        proceedToKnownPacks();
    }

    @Override
    public void handleResourcePackResponse(final ServerboundResourcePackPacket packet) {
        connection.notifyResourcePackResponse(packet.id(), packet.status());

        final boolean terminalFailure = switch (packet.status()) {
            case SUCCESSFULLY_LOADED, ACCEPTED, DOWNLOADED -> false;
            default -> true;
        };

        if (terminalFailure && server.config().resourcePackForced()) {
            connection.disconnect(Component.translatable("multiplayer.requiredTexturePrompt.disconnect"));
            return;
        }

        if (awaitingResourcePackResponse) {
            awaitingResourcePackResponse = false;
            proceedToCodeOfConduct();
        }
    }

    @Override
    public void handleSelectKnownPacks(final ServerboundSelectKnownPacksPacket packet) {
        if (awaitingCodeOfConduct) {
            LOGGER.debug("{} sent Known Packs before accepting the Code of Conduct; ignored.", connection.username());
            return;
        }
        LOGGER.debug("Known Packs client received -> sending registers");
        sendRegistries();
        sendTags();
        connection.send(new ClientboundFinishConfigurationPacket());
    }

    private void sendRegistries() {
        final RegistryHolder dynamic = server.dynamicRegistries();
        if (dynamic.isEmpty()) {
            LOGGER.warn("No dynamic registry to send (GeneratedRegistryData is empty).");
            return;
        }

        for (final Registry reg : dynamic.all()) {
            if (reg.name().asString().contains("minecraft:enchantment")) { // Todo
                continue;
            }
            if (reg.name().equals(FidorialBiomeRegistry.REGISTRY_NAME)
                    || reg.name().equals(FidorialDialogRegistry.REGISTRY_NAME)
                    || reg.name().equals(FidorialDimensionTypeRegistry.REGISTRY_NAME)) {
                continue;
            }
            connection.send(ClientboundRegistryDataPacket.knownOnly(reg.name(), reg.entries()));
        }

        connection.send(new ClientboundRegistryDataPacket(
                FidorialBiomeRegistry.REGISTRY_NAME,
                server.biomeRegistry().networkEntries()));

        connection.send(new ClientboundRegistryDataPacket(
                FidorialDialogRegistry.REGISTRY_NAME,
                server.dialogs().networkEntries()));

        connection.send(new ClientboundRegistryDataPacket(
                FidorialDimensionTypeRegistry.REGISTRY_NAME,
                server.dimensionTypes().networkEntries()));
    }

    private void sendTags() {
        connection.send(new ClientboundUpdateTagsPacket(
                server.dynamicRegistries(), server.biomeRegistry(), server.dialogs(), server.dimensionTypes()));
    }

    private boolean sendResourcePackIfConfigured() {
        final String url = server.config().resourcePackUrl();
        if (url == null || url.isBlank()) {
            return false;
        }
        // the constructor of ServerConfig already guarantees this
        final UUID id = Objects.requireNonNull(server.config().resourcePackId(), "resourcePackId must be set when resourcePackUrl is");
        connection.send(new ClientboundResourcePackPushPacket(
                ConfigurationClientboundPackets.RESOURCE_PACK_PUSH,
                id,
                url,
                server.config().resourcePackHash() == null ? "" : server.config().resourcePackHash(),
                server.config().resourcePackForced(),
                server.config().resourcePackPrompt()));
        return true;
    }

    @Override
    public void handleFinishConfiguration(final ServerboundFinishConfigurationPacket packet) {
        connection.setState(ConnectionState.PLAY);
    }

    @Override
    public void handleCustomClickAction(final ServerboundCustomClickActionPacket packet) {
        if (!(packet.payload() instanceof final CompoundBinaryTag nbt)) {
            LOGGER.debug("{} sent a non-compound click callback payload for {}: {}",
                    connection.username(), packet.id(), packet.payload());
            return;
        }
        final UUID uuid;
        try {
            uuid = ClickCallbackManager.uuidFromPayload(nbt);
        } catch (final IllegalArgumentException e) {
            LOGGER.debug("{} sent an invalid click callback payload for {}", connection.username(), packet.id(), e);
            return;
        }
        server.clickCallbacksManager().handleClick(connection, packet.id(), uuid);
    }

    @Override
    public void handleClientInformation(final ServerboundClientInformationPacket packet) {
        connection.setLocale(Locale.forLanguageTag(packet.language().replace('_', '-')));
        connection.setDisplayedSkinParts(packet.displayedSkinParts());
        synchronized (codeOfConductLock) {
            clientInformationReceived = true;
        }
        sendCodeOfConductIfReady();
    }
}
