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
package de.erethon.holographicmenus.menu;

import io.github.dre2n.commons.misc.FileUtil;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Daniel Saukel
 */
public class HMenus {

    private Set<HMenu> menus = new HashSet<>();

    public HMenus(File folder) {
        for (File file : FileUtil.getFilesForFolder(folder)) {
            menus.add(new HMenu(file));
        }
    }

    /**
     * @return
     * the menus
     */
    public Set<HMenu> getMenus() {
        return menus;
    }

    /**
     * @param name
     * the name of the menu
     * @return
     * the menu
     */
    public HMenu getByName(String name) {
        for (HMenu menu : menus) {
            if (menu.getName().equalsIgnoreCase(name)) {
                return menu;
            }
        }

        return null;
    }

}
