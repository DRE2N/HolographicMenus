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

import de.erethon.holographicmenus.HolographicMenus;
import de.erethon.holographicmenus.menu.HMenu;
import de.erethon.holographicmenus.player.HPlayer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * HologramCollection represents the collectivity of all components
 * of a menu page (including all static buttons) in their spawned form as a hologram.
 * It does NOT represent a menu or menu page as fetched from config.
 *
 * @author Daniel Saukel
 */
public class HologramCollection {

    private HolographicMenus plugin;

    private Collection<Hologram> holograms;
    private HMenu menu;
    private int page;
    private Location location;
    private Vector direction;

    public HologramCollection(HolographicMenus plugin, HMenu menu, int page, Location location, Vector direction) {
        this.plugin = plugin;
        holograms = new ArrayList<>();
        this.menu = menu;
        this.page = page;
        this.location = location;
        this.direction = direction;
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

    /**
     * @return
     * a copy of the location that matchs the opener's eye loation
     */
    public Location getLocation() {
        return location.clone();
    }

    /**
     * @return
     * a copy of the vector that matchs the opener's view direction
     */
    public Vector getDirection() {
        return direction;
    }

    /* Actions  */
    public Map<String, Object> serialize() {
        Map<String, Object> serialized = new HashMap<>();
        serialized.put("location", location);
        serialized.put("direction", direction);
        return serialized;
    }

    /**
     * @param anchor
     * a fix point for the holograms
     * @param direction
     * the direction to orientate the holograms
     */
    public void moveAll(Location anchor, Vector direction) {
        location = anchor;
        this.direction = direction;
        holograms.forEach(h -> h.move(h.getButton().getLocation(anchor, direction)));
    }

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
        for (HPlayer player : plugin.getHPlayerCache().getPlayers()) {
            if (player.getOpenedHolograms() == this) {
                player.setOpenedHolograms(null);
            }
        }
        for (Hologram hologram : holograms.toArray(new Hologram[]{})) {
            hologram.delete();
        }
    }

}
