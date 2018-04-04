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

import de.erethon.commons.command.DRECommandCache;
import de.erethon.holographicmenus.HolographicMenus;

/**
 * An enumeration of all command instances.
 *
 * @author Daniel Saukel
 */
public class HCommandCache extends DRECommandCache {

    public CancelInputCommand cancelInput;
    public HelpCommand help;
    public InfoCommand info;
    public MainCommand main;
    public OpenPermanentlyCommand openPermanently;
    public ReloadCommand reload;

    public HCommandCache(HolographicMenus plugin) {
        super("holographicmenus", plugin);
        cancelInput = new CancelInputCommand(plugin);
        help = new HelpCommand(plugin);
        info = new InfoCommand(plugin);
        main = new MainCommand(plugin);
        openPermanently = new OpenPermanentlyCommand(plugin);
        reload = new ReloadCommand(plugin);
        addCommand(cancelInput);
        addCommand(help);
        addCommand(info);
        addCommand(main);
        addCommand(openPermanently);
        addCommand(reload);
    }

}
