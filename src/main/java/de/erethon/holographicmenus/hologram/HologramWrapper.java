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
package de.erethon.holographicmenus.hologram;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public interface HologramWrapper {

    /**
     * @param location
     * the Location where the menu shall be opened
     * @param label
     * the text String to show
     * @param viewers
     * the Players who can see the menu. null means public
     * @return
     * a HolographicMenus hologram
     */
    public Hologram createHologram(Location location, String label, Player... viewers);

    /**
     * @param location
     * the location where the menu shall be opened
     * @param item
     * the ItemStack to show (instead of a text)
     * @param viewers
     * the players who can see the menu. null means public
     * @return
     * a HolographicMenus hologram
     */
    public Hologram createHologram(Location location, ItemStack item, Player... viewers);

    /**
     * @param hologram
     * a HolographicMenus hologram wrapper object created trough a HologramWrapper
     */
    public void deleteHologram(Hologram hologram);

}
