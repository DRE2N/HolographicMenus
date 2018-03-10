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

import de.erethon.holographicmenus.menu.HMenu;
import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.util.Vector;

/**
 * HologramCollection represents the collectivity of all components
 * of a menu page (including all static buttons) in their spawned form as a hologram.
 * It does NOT represent a button as fetched from config.
 *
 * @author Daniel Saukel
 */
public class HologramCollection {

    private Collection<Hologram> holograms;
    private HMenu menu;
    private int page;

    public HologramCollection(Collection<Hologram> holograms) {
        this.holograms = holograms;
    }

    public HologramCollection(HMenu menu, int page) {
        holograms = new ArrayList<>();
        this.menu = menu;
        this.page = page;
    }

    /* Getters and setters */
    /**
     * @return
     * the HologramCollection as a java.util.Collection
     */
    public Collection<Hologram> get() {
        return holograms;
    }

    /**
     * @return
     * the menu the player has opened
     */
    public HMenu getMenu() {
        return menu;
    }

    /**
     * @return
     * the menu the player has opened
     */
    public void setMenu(HMenu menu, int page) {
        this.menu = menu;
        this.page = page;
    }

    /**
     * @return
     * the opened menu page
     */
    public int getPage() {
        return page;
    }

    /**
     * @return
     * the menu page the player has opened
     */
    public void setPage(int page) {
        this.page = page;
    }

    /* Actions  */
    public void add(Hologram hologram) {
        holograms.add(hologram);
    }

    public void remove(Hologram hologram) {
        holograms.remove(hologram);
    }

    public void addAll(Collection<Hologram> holograms) {
        this.holograms.addAll(holograms);
    }

    public void removeAll(Collection<Hologram> holograms) {
        this.holograms.removeAll(holograms);
    }

    public boolean contains(Hologram hologram) {
        return holograms.contains(hologram);
    }

    /**
     * Deletes all holograms
     */
    public void deleteAll() {
        holograms.forEach(h -> h.delete());
    }

}
