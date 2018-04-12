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

import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.misc.EnumUtil;
import de.erethon.holographicmenus.HolographicMenus;
import de.erethon.holographicmenus.hologram.HologramCollection;
import de.erethon.holographicmenus.player.HPlayer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
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
    private float rotationTolerance;
    private boolean followingOnMove;
    private Collection<HologramCollection> permas;

    public HMenu(String name, Type type, List<HMenuPage> menuPages, double distance, float rotationTolerance) {
        if (name.endsWith(".yml")) {
            name = name.substring(0, name.length() - 4);
        }
        this.name = name;
        this.type = type;
        if (type == Type.PUBLIC) {
            permas = new ArrayList<>();
        }
        this.menuPages = menuPages;
        this.distance = distance;
        this.rotationTolerance = rotationTolerance;
    }

    public HMenu(HolographicMenus plugin, String name, ConfigurationSection config) {
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

        distance = config.getDouble("distance", 1.75);
        rotationTolerance = (float) config.getDouble("rotationTolerance", 0);
        followingOnMove = type == Type.PRIVATE && config.getBoolean("followingOnMove", true);

        if (type == Type.PUBLIC) {
            permas = new ArrayList<>();
        }
        if (config.contains("permanentInstances") && type == Type.PUBLIC) {
            for (Object object : config.getList("permanentInstances")) {
                if (object instanceof Map) {
                    permas.add(open(plugin, 1, (Location) ((Map) object).get("location"), (Vector) ((Map) object).get("direction")));
                }
            }
        }
    }

    public HMenu(HolographicMenus plugin, File file) {
        this(plugin, file.getName(), YamlConfiguration.loadConfiguration(file));
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
     * @return
     * how many degrees a player may turn until the menu follows the movement
     */
    public float getRotationTolerance() {
        return rotationTolerance;
    }

    /**
     * @param distance
     * set the distance between the opener and the menu
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return
     * if the menu follows when a player moves
     */
    public boolean isFollowingOnMove() {
        return followingOnMove;
    }

    /**
     * @param followingOnMove
     * if the menu follows when a player moves
     */
    public void setFollowingOnMove(boolean followingOnMove) {
        this.followingOnMove = followingOnMove;
    }

    /**
     * @return
     * the instances of this menu that are spawned permanently
     * null if this is a PRIVATE menu
     */
    public Collection<HologramCollection> getPermanentInstances() {
        return permas;
    }

    /* Actions */
    /**
     * @return
     * the menuPage as a ConfigurationSection
     */
    public FileConfiguration serialize() {
        YamlConfiguration config = new YamlConfiguration();

        config.set("type", type.toString());
        config.set("distance", distance);
        config.set("rotationTolerance", rotationTolerance);
        config.set("followingOnMove", followingOnMove);

        for (HMenuPage page : menuPages) {
            config.set("menuPages." + menuPages.indexOf(page), page.serialize());
        }

        Map<Integer, ConfigurationSection> buttons = new HashMap<>();
        int i = 0;
        for (HButton button : this.buttons) {
            buttons.put(i, button.serialize());
            i++;
        }
        config.set("staticButtons", buttons);

        List<Map<String, Object>> permasSerialized = new ArrayList<>();
        permas.forEach(m -> permasSerialized.add(m.serialize()));
        config.set("permanentInstances", permasSerialized);

        return config;
    }

    public void save() throws IOException {
        File file = new File(HolographicMenus.MENUS, name + ".yml");
        if (!file.exists()) {
            file.createNewFile();
        }
        serialize().save(file);
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
     * @param page
     * the page to open
     * @param location
     * the Location where the menu will be opened
     * @param direction
     * the direction to set the buttons
     * @param viewers
     * the players that can see the holograms
     * @return
     * a HologramCollection of all spawned Holograms
     */
    public HologramCollection open(HolographicMenus plugin, int page, Location location, Vector direction, Player... viewers) {
        HologramCollection associated = new HologramCollection(plugin, this, page, location, direction);
        buttons.forEach(b -> associated.add(b.open(plugin.getHologramProvider(), location, direction, type == Type.PRIVATE ? viewers : null)));

        if (menuPages.size() >= page && page >= 1) {
            associated.addAll(menuPages.get(page - 1).open(plugin.getHologramProvider(), location, direction, type == Type.PRIVATE ? viewers : null));
        }
        associated.get().forEach(h -> h.setAssociatedHolograms(associated));
        for (Player player : viewers) {
            HPlayer hPlayer = plugin.getHPlayerCache().getByPlayer(player);
            if (hPlayer.hasOpenedMenu()) {
                hPlayer.getOpenedHolograms().deleteAll();
            }
            hPlayer.setOpenedHolograms(associated);
        }
        return associated;
    }

    /**
     * Opens the menu; saves it persistently
     *
     * @param plugin
     * the plugin instance
     * @param page
     * the page to open
     * @param location
     * the Location where the menu will be opened
     * @param direction
     * the direction to set the buttons
     * @return
     * a HologramCollection of all spawned Holograms;
     * null if the menu is PRIVATE
     */
    public HologramCollection openPermanently(HolographicMenus plugin, int page, Location location, Vector direction) {
        if (type != Type.PUBLIC) {
            return null;
        }
        HologramCollection opened = open(plugin, page, location, direction);
        permas.add(opened);
        try {
            save();
        } catch (IOException exception) {
            MessageUtil.log(plugin, "&4Could not save menu " + name + ".");
            exception.printStackTrace();
        }
        return opened;
    }

}
