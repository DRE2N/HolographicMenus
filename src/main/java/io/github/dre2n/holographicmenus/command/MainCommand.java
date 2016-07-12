/*
 * Copyright (C) 2016 Daniel Saukel
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
package io.github.dre2n.holographicmenus.command;

import io.github.dre2n.commons.command.BRCommand;
import static io.github.dre2n.commons.util.messageutil.FatLetters.*;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import io.github.dre2n.holographicmenus.HolographicMenus;
import io.github.dre2n.holographicmenus.config.HMessages;
import io.github.dre2n.holographicmenus.player.HPermissions;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;

/**
 * @author Daniel Saukel
 */
public class MainCommand extends BRCommand {

    HolographicMenus plugin = HolographicMenus.getInstance();

    public MainCommand() {
        setCommand("main");
        setHelp(HMessages.HELP_CMD_MAIN.getMessage());
        setPermission(HPermissions.MAIN.getNode());
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        PluginManager plugins = Bukkit.getServer().getPluginManager();

        String holographicdisplays = "";
        if (plugins.getPlugin("HolographicDisplays") != null) {
            holographicdisplays = plugins.getPlugin("HolographicDisplays").getDescription().getVersion();
        }

        MessageUtil.sendCenteredMessage(sender, "&6" + H[0] + M[4]);
        MessageUtil.sendCenteredMessage(sender, "&6" + H[1] + M[4]);
        MessageUtil.sendCenteredMessage(sender, "&6" + H[2] + M[4]);
        MessageUtil.sendCenteredMessage(sender, "&6" + H[3] + M[4]);
        MessageUtil.sendCenteredMessage(sender, "&6" + H[4] + M[4]);
        MessageUtil.sendCenteredMessage(sender, "&b&l###### " + HMessages.CMD_MAIN_WELCOME.getMessage() + "&7 v" + plugin.getDescription().getVersion() + " &b&l######");
        MessageUtil.sendCenteredMessage(sender, HMessages.CMD_MAIN_LOADED.getMessage(holographicdisplays));
        MessageUtil.sendCenteredMessage(sender, HMessages.CMD_MAIN_HELP.getMessage());
        MessageUtil.sendCenteredMessage(sender, "&7\u00a92016 Daniel Saukel; lcsd. under GPLv3.");
    }

}
