/*
 * Copyright (C) 2016-2017 Daniel Saukel
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

import io.github.dre2n.commons.command.DRECommand;
import io.github.dre2n.commons.misc.NumberUtil;
import io.github.dre2n.commons.chat.MessageUtil;
import de.erethon.holographicmenus.HolographicMenus;
import de.erethon.holographicmenus.config.HMessage;
import de.erethon.holographicmenus.player.HPermission;
import java.util.ArrayList;
import java.util.Set;
import org.bukkit.command.CommandSender;

/**
 * @author Frank Baumann, Daniel Saukel
 */
public class HelpCommand extends DRECommand {

    HolographicMenus plugin = HolographicMenus.getInstance();

    public HelpCommand() {
        setCommand("help");
        setMinArgs(0);
        setMaxArgs(1);
        setHelp(HMessage.HELP_CMD_HELP.getMessage());
        setPermission(HPermission.HELP.getNode());
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        Set<DRECommand> hCommandList = plugin.getCommandCache().getCommands();
        ArrayList<DRECommand> toSend = new ArrayList<>();

        int page = 1;
        if (args.length == 2) {
            page = NumberUtil.parseInt(args[1], 1);
        }
        int send = 0;
        int max = 0;
        int min = 0;
        for (DRECommand hCommand : hCommandList) {
            send++;
            if (send >= page * 5 - 4 && send <= page * 5) {
                min = page * 5 - 4;
                max = page * 5;
                toSend.add(hCommand);
            }
        }

        MessageUtil.sendPluginTag(sender, plugin);
        MessageUtil.sendCenteredMessage(sender, "&4&l[ &6" + min + "-" + max + " &4/&6 " + send + " &4|&6 " + page + " &4&l]");

        for (DRECommand hCommand : toSend) {
            MessageUtil.sendMessage(sender, "&b" + hCommand.getCommand() + "&7 - " + hCommand.getHelp());
        }
    }

}
