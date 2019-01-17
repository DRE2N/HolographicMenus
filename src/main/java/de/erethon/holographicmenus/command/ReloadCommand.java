/*
 * Copyright (C) 2016-2019 Daniel Saukel
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
import org.bukkit.command.CommandSender;

/**
 * @author Daniel Saukel
 */
public class ReloadCommand extends DRECommand {

    private HolographicMenus plugin;

    public ReloadCommand(HolographicMenus plugin) {
        this.plugin = plugin;
        setCommand("reload");
        setMinArgs(0);
        setMaxArgs(0);
        setHelp(HMessage.HELP_RELOAD.getMessage());
        setPermission(HPermission.RELOAD.getNode());
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        plugin.loadCore();
        MessageUtil.sendMessage(sender, HMessage.CMD_RELOAD_DONE.getMessage());
    }

}
