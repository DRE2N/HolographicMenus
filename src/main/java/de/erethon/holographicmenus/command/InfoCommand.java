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

import static de.erethon.commons.chat.FatLetter.*;
import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.command.DRECommand;
import de.erethon.holographicmenus.HolographicMenus;
import de.erethon.holographicmenus.config.HMessage;
import de.erethon.holographicmenus.player.HPermission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;

/**
 * @author Daniel Saukel
 */
public class InfoCommand extends DRECommand {

    private HolographicMenus plugin;

    public InfoCommand(HolographicMenus plugin) {
        this.plugin = plugin;
        setCommand("info");
        setMinArgs(0);
        setMaxArgs(0);
        setHelp(HMessage.HELP_INFO.getMessage());
        setPermission(HPermission.INFO.getNode());
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        PluginManager plugins = Bukkit.getServer().getPluginManager();

        String holographicdisplays = new String();
        if (plugins.getPlugin("HolographicDisplays") != null) {
            holographicdisplays = plugins.getPlugin("HolographicDisplays").getDescription().getVersion();
        }

        MessageUtil.sendCenteredMessage(sender, "&6" + H[0] + M[4]);
        MessageUtil.sendCenteredMessage(sender, "&6" + H[1] + M[4]);
        MessageUtil.sendCenteredMessage(sender, "&6" + H[2] + M[4]);
        MessageUtil.sendCenteredMessage(sender, "&6" + H[3] + M[4]);
        MessageUtil.sendCenteredMessage(sender, "&6" + H[4] + M[4]);
        MessageUtil.sendCenteredMessage(sender, "&b&l###### " + HMessage.CMD_INFO_WELCOME.getMessage() + "&7 v" + plugin.getDescription().getVersion() + " &b&l######");
        MessageUtil.sendCenteredMessage(sender, HMessage.CMD_INFO_LOADED.getMessage(holographicdisplays));
        MessageUtil.sendCenteredMessage(sender, HMessage.CMD_INFO_HELP.getMessage());
        MessageUtil.sendCenteredMessage(sender, "&7\u00a92016-2018 Daniel Saukel; lcsd. under GPLv3.");
    }

}
