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
package io.github.dre2n.holographicmenus.menu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.Vector;

/**
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
    public ConfigurationSection toConfig() {
        YamlConfiguration config = new YamlConfiguration();

        HashSet<ConfigurationSection> buttons = new HashSet<>();
        for (HButton button : this.buttons) {
            buttons.add(button.toConfig());
        }
        config.set("buttons", buttons);

        return config;
    }

    /**
     * @param location
     * the Location to open the menu
     * @param direction
     * the direction to set the buttons
     */
    public void open(Location location, Vector direction) {
        for (HButton button : buttons) {
            button.open(location, direction);
        }
    }

}
