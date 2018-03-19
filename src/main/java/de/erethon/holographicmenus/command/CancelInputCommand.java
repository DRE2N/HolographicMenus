/*
 * Copyright (C) 2016-2018 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.erethon.holographicmenus.command;

import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.command.DRECommand;
import de.erethon.holographicmenus.HolographicMenus;
import de.erethon.holographicmenus.config.HMessage;
import de.erethon.holographicmenus.player.HPermission;
import de.erethon.holographicmenus.player.HPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public class CancelInputCommand extends DRECommand {

    private HolographicMenus plugin;

    public CancelInputCommand(HolographicMenus plugin) {
        this.plugin = plugin;
        setCommand("cancelInput");
        setMinArgs(0);
        setMaxArgs(0);
        setHelp(HMessage.HELP_CANCEL_INPUT.getMessage());
        setPermission(HPermission.MENU.getNode());
        setPlayerCommand(true);
        setConsoleCommand(false);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        HPlayer player = plugin.getHPlayerCache().getByPlayer((Player) sender, false);
        if (player == null || !player.hasPendingCommand()) {
            MessageUtil.sendMessage(sender, HMessage.ERROR_NO_PENDING_CMD.getMessage());
            return;
        }
        player.setPendingCommand(null);
        MessageUtil.sendMessage(sender, HMessage.PENDING_CANCELLED.getMessage());
    }

}
