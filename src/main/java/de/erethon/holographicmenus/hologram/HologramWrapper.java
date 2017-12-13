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
package de.erethon.holographicmenus.hologram;

import de.erethon.holographicmenus.HolographicMenus;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public abstract class HologramWrapper {

    protected static HolographicMenus plugin = HolographicMenus.getInstance();

    protected List<Object> holograms = new ArrayList<>();

    /**
     * @param id
     * the hologram identifier
     */
    public void deleteHologram(int id) {
        holograms.remove(id);
    }

    /* Abstracts */
    /**
     * @param viewers
     * the Players who can see the menu. null means public
     * @param location
     * the Location where the menu shall be opened
     * @param label
     * the text String to show
     * @return
     * a hologram ID which can be used to delete the hologram later
     */
    public abstract int createHologram(Set<Player> viewers, Location location, String label);

    /**
     * @param viewers
     * the players who can see the menu. null means public
     * @param location
     * the location where the menu shall be opened
     * @param item
     * the ItemStack to show (instead of a text)
     * @return
     * a hologram ID which can be used to delete the hologram later
     */
    public abstract int createHologram(Set<Player> viewers, Location location, ItemStack item);

}
