package fr.fidorial.registrygen.model;

import java.net.URI;
import java.util.Objects;

/**
 * The ServerDownload record represents the metadata required to download a specific
 * version of a Minecraft server JAR file. This includes the Minecraft version, the
 * download URL, and the SHA-1 checksum for verifying the integrity of the downloaded file.
 *
 * @since 0.1.0
 */
public record ServerDownload(String version, URI url, String sha1) {

    public ServerDownload {

        Objects.requireNonNull(version, "version");
        Objects.requireNonNull(url, "url");
        Objects.requireNonNull(sha1, "sha1");

        if (version.isBlank()) {
            throw new IllegalArgumentException("Minecraft version cannot be blank.");
        }

        if (sha1.isBlank()) {
            throw new IllegalArgumentException("Server JAR SHA-1 cannot be blank.");
        }
    }
}
