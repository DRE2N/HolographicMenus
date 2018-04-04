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
package de.erethon.holographicmenus.menu;

import de.erethon.holographicmenus.hologram.Hologram;
import de.erethon.holographicmenus.hologram.HologramWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * This class contains properties that apply to one page of a menu as a whole.
 * HMenuPage represents one menu page type as fetched from the scripts.
 * It does NOT represent a collection of spawned holograms.
 *
 * @author Daniel Saukel
 */
public class HMenuPage {

    private Set<HButton> buttons = new HashSet<>();

    public HMenuPage(Set<HButton> buttons) {
        this.buttons = buttons;
    }

    public HMenuPage(ConfigurationSection config) {
        if (config.contains("buttons")) {
            for (String button : config.getConfigurationSection("buttons").getKeys(false)) {
                buttons.add(new HButton(config.getConfigurationSection("buttons." + button)));
            }
        }
    }

    /**
     * @return
     * a Set of all buttons
     */
    public Set<HButton> getButtons() {
        return buttons;
    }

    /**
     * @param buttons
     * the buttons to add
     */
    public void addButton(HButton... buttons) {
        this.buttons.addAll(Arrays.asList(buttons));
    }

    /**
     * @param buttons
     * the buttons to remove
     */
    public void removeButton(HButton... buttons) {
        this.buttons.removeAll(Arrays.asList(buttons));
    }

    /* Actions */
    public ConfigurationSection serialize() {
        YamlConfiguration config = new YamlConfiguration();

        Map<Integer, ConfigurationSection> buttons = new HashMap<>();
        int i = 0;
        for (HButton button : this.buttons) {
            buttons.put(i, button.serialize());
            i++;
        }
        config.set("buttons", buttons);

        return config;
    }

    /**
     * @param provider
     * the HologramWrapper of the loaded hologram provider plugin
     * @param location
     * the Location to open the menu
     * @param direction
     * the direction to set the buttons
     * @param viewers
     * the players that can see the holograms
     * @return
     * a Collection of all spawned Holograms
     */
    public Collection<Hologram> open(HologramWrapper provider, Location location, Vector direction, Player[] viewers) {
        Collection<Hologram> associated = new ArrayList<>();
        buttons.forEach(b -> associated.add(b.open(provider, location, direction, viewers)));
        return associated;
    }

}
