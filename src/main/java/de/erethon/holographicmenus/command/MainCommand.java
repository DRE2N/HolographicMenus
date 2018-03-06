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
import de.erethon.holographicmenus.menu.HMenu;
import de.erethon.holographicmenus.menu.HMenuCache;
import de.erethon.holographicmenus.player.HPermission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public class MainCommand extends DRECommand {

    private HolographicMenus plugin;
    private HMenuCache menus;

    public MainCommand(HolographicMenus plugin) {
        this.plugin = plugin;
        menus = plugin.getHMenuCache();
        setCommand("main");
        setMinArgs(0);
        setMaxArgs(1);
        setHelp(HMessage.HELP_MENU.getMessage());
        setPermission(HPermission.MENU.getNode());
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        HMenu menu = null;
        if (args.length == 1) {
            menu = menus.getByName(args[0]);
        } else {
            menu = menus.getMainMenu();
        }
        if (menu != null) {
            menu.open((Player) sender);
        } else {
            MessageUtil.sendMessage(sender, HMessage.ERROR_NO_SUCH_MENU.getMessage(args.length == 1 ? args[0] : plugin.getHConfig().getMainMenuName()));
        }
    }

}
