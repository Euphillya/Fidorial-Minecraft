package fr.euphyllia.fidorial.server.network.protocol.packet.clientbound.play;

import com.mojang.brigadier.Message;
import fr.euphyllia.fidorial.server.network.PacketBuffer;
import fr.euphyllia.fidorial.server.network.protocol.catalog.PlayClientboundPackets;
import fr.euphyllia.fidorial.server.network.protocol.packet.ClientboundPacket;
import fr.fidorial.command.MessageComponentSerializer;
import net.kyori.adventure.key.Key;
import org.jspecify.annotations.Nullable;

import java.util.List;

public record ClientboundCommandSuggestionsPacket(int id, int start, int length, List<Entry> entries)
        implements ClientboundPacket {

    @Override
    public Key name() {
        return PlayClientboundPackets.COMMAND_SUGGESTIONS;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarInt(id);
        buf.writeVarInt(start);
        buf.writeVarInt(length);

        buf.writeVarInt(entries.size());

        for (Entry entry : entries) {
            buf.writeString(entry.text());

            if (entry.tooltip() != null) {
                buf.writeBoolean(true);
                buf.writeComponent(MessageComponentSerializer.message().deserialize(entry.tooltip()));
            } else {
                buf.writeBoolean(false);
            }
        }
    }

    public record Entry(String text, @Nullable Message tooltip) {
    }
}
