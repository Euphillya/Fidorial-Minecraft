/*
 * This file is part of spark.
 *
 *  Copyright (c) lucko (Luck) <luck@lucko.me>
 *  Copyright (c) contributors
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.euphyllia.fidorial.server.spark;

import fr.fidorial.command.CommandSender;
import fr.fidorial.entity.Player;
import me.lucko.spark.common.command.sender.AbstractCommandSender;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public final class SparkCommandSender extends AbstractCommandSender<CommandSender> {

    public SparkCommandSender(final CommandSender delegate) {
        super(delegate);
    }

    @Override
    public String getName() {
        return this.delegate.name();
    }

    @Override
    public @Nullable UUID getUniqueId() {
        if (this.delegate instanceof final Player player) {
            return player.uuid();
        }
        return null;
    }

    @Override
    public void sendMessage(final Component message) {
        this.delegate.sendMessage(message);
    }

    @Override
    public boolean hasPermission(final String permission) {
        if (this.delegate.isOperator()) {
            return true;
        }
        return this.delegate.hasPermission(permission);
    }
}
