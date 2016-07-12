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

import io.github.dre2n.commons.command.BRCommands;
import io.github.dre2n.commons.javaplugin.BRPlugin;

/**
 * An enumeration of all command instances.
 *
 * @author Daniel Saukel
 */
public class HCommands extends BRCommands {

    public static HelpCommand HELP = new HelpCommand();
    public static MainCommand MAIN = new MainCommand();

    public HCommands(BRPlugin plugin) {
        super("holographicmenus", plugin,
                HELP,
                MAIN
        );
    }

}
