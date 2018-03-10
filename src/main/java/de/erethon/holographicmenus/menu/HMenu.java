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

import de.erethon.commons.misc.EnumUtil;
import de.erethon.holographicmenus.HolographicMenus;
import de.erethon.holographicmenus.hologram.Hologram;
import de.erethon.holographicmenus.hologram.HologramCollection;
import de.erethon.holographicmenus.player.HPlayer;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * This class contains properties that apply to the menu as a whole.
 * HMenu represents one menu type as fetched from the scripts.
 * It does NOT represent a collection of spawned holograms.
 *
 * @author Daniel Saukel
 */
public class HMenu {

    public enum Type {
        PUBLIC,
        PRIVATE
    }

    private String name;
    private Type type;
    private List<HMenuPage> menuPages = new ArrayList<>();
    private Set<HButton> buttons = new HashSet<>();
    private double distance;

    public HMenu(String name, Type type, List<HMenuPage> menuPages, double distance) {
        if (name.endsWith(".yml")) {
            name = name.substring(0, name.length() - 4);
        }
        this.name = name;
        this.type = type;
        this.menuPages = menuPages;
        this.distance = distance;
    }

    public HMenu(String name, ConfigurationSection config) {
        if (name.endsWith(".yml")) {
            name = name.substring(0, name.length() - 4);
        }
        this.name = name;

        if (config.contains("type")) {
            if (EnumUtil.isValidEnum(Type.class, config.getString("type"))) {
                type = Type.valueOf(config.getString("type"));
            }
        }

        if (config.contains("menuPages")) {
            for (String page : config.getConfigurationSection("menuPages").getKeys(false)) {
                menuPages.add(new HMenuPage(config.getConfigurationSection("menuPages." + page)));
            }
        }

        if (config.contains("staticButtons")) {
            for (String button : config.getConfigurationSection("staticButtons").getKeys(false)) {
                buttons.add(new HButton(config.getConfigurationSection("staticButtons." + button)));
            }
        }

        if (config.contains("distance")) {
            distance = config.getDouble("distance");
        }
    }

    public HMenu(File file) {
        this(file.getName(), YamlConfiguration.loadConfiguration(file));
    }

    /* Getters and setters */
    /**
     * @return
     * the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     * the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type
     * the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return
     * a Set of all menuPages
     */
    public List<HMenuPage> getMenuPages() {
        return menuPages;
    }

    /**
     * @param menuPages
     * the menuPages to add
     */
    public void addMenuPage(HMenuPage... menuPages) {
        this.menuPages.addAll(Arrays.asList(menuPages));
    }

    /**
     * @param menuPages
     * the menuPages to remove
     */
    public void removeMenuPage(HMenuPage... menuPages) {
        this.menuPages.removeAll(Arrays.asList(menuPages));
    }

    /**
     * @return
     * a Set of all buttons
     */
    public Set<HButton> getStaticButtons() {
        return buttons;
    }

    /**
     * @param buttons
     * the buttons to add
     */
    public void addStaticButton(HButton... buttons) {
        this.buttons.addAll(Arrays.asList(buttons));
    }

    /**
     * @param buttons
     * the buttons to remove
     */
    public void removeStaticButton(HButton... buttons) {
        this.buttons.removeAll(Arrays.asList(buttons));
    }

    /**
     * @return
     * the distance between the opener and the menu
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance
     * set the distance between the opener and the menu
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /* Actions */
    /**
     * @return
     * the menuPage as a ConfigurationSection
     */
    public ConfigurationSection serialize() {
        YamlConfiguration config = new YamlConfiguration();

        config.set("type", type);
        config.set("distance", distance);

        for (HMenuPage page : menuPages) {
            config.set("menuPages." + menuPages.indexOf(page), page.serialize());
        }

        HashSet<ConfigurationSection> buttons = new HashSet<>();
        for (HButton button : this.buttons) {
            buttons.add(button.serialize());
        }
        config.set("staticButtons", buttons);

        return config;
    }

    /**
     * @param player
     * the opener
     */
    public void open(HolographicMenus plugin, HPlayer player) {
        open(plugin, player.getPlayer());
    }

    /**
     * @param player
     * the opener
     */
    public void open(HolographicMenus plugin, Player player) {
        open(plugin, player, 1);
    }

    /**
     * @param player
     * the opener
     * @param page
     * the page to open
     */
    public void open(HolographicMenus plugin, HPlayer player, int page) {
        open(plugin, player.getPlayer(), page);
    }

    /**
     * @param player
     * the opener
     * @param page
     * the page to open
     */
    public void open(HolographicMenus plugin, Player player, int page) {
        open(plugin, page, player.getEyeLocation(), player.getEyeLocation().getDirection().multiply(distance), player);
    }

    /**
     * @param plugin
     * the plugin instance
     * @param viewers
     * the players that can see the holograms
     * @param page
     * the page to open
     * @param location
     * the Location where the menu will be opened
     * @param direction
     * the direction to set the buttons
     * @return
     * a HologramCollection of all spawned Holograms
     */
    public HologramCollection open(HolographicMenus plugin, int page, Location location, Vector direction, Player... viewers) {
        HologramCollection associated = new HologramCollection(this, page);
        buttons.forEach(b -> associated.add(b.open(plugin.getHologramProvider(), location, direction, type == Type.PRIVATE ? viewers : null)));

        if (menuPages.size() >= page && page >= 1) {
            associated.addAll(menuPages.get(page - 1).open(plugin.getHologramProvider(), location, direction, type == Type.PRIVATE ? viewers : null));
        }
        associated.get().forEach(h -> h.setAssociatedHolograms(associated));
        for (Player player : viewers) {
            plugin.getHPlayerCache().getByPlayer(player).setOpenedHolograms(associated);
        }
        return associated;
    }

}
