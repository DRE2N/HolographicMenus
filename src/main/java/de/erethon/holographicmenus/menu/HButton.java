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
import de.erethon.holographicmenus.hologram.Hologram;
import de.erethon.holographicmenus.hologram.HologramWrapper;
import de.erethon.holographicmenus.util.Placeholder;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * This class contains properties that apply to one button.
 * HButton represents one button as fetched from the scripts.
 * It does NOT represent a spawned hologram.
 * An instance of HButton may be held by an instance of HMenuPage or, if it is a static button, HMenu.
 *
 * @author Daniel Saukel
 */
public class HButton {

    public enum Type {
        TITLE,
        BUTTON,
        FIRST_PAGE,
        PREVIOUS_PAGE,
        NEXT_PAGE,
        LAST_PAGE,
        CLOSE
    }

    private String label;
    private Type type;
    private String command;
    private String sound;
    private double x;
    private double y;

    public HButton(String label, Type type, String command, String sound, double x, double y) {
        this.label = label;
        this.type = type;
        this.command = command;
        this.sound = sound;
        this.x = x;
        this.y = y;
    }

    public HButton(ConfigurationSection config) {
        if (config.contains("label")) {
            label = config.getString("label");
        }

        if (config.contains("type")) {
            if (EnumUtil.isValidEnum(Type.class, config.getString("type"))) {
                type = Type.valueOf(config.getString("type"));
            }
        }

        if (config.contains("command")) {
            command = config.getString("command");
        }

        if (config.contains("sound")) {
            sound = config.getString("sound");
        }

        if (config.contains("x")) {
            x = config.getDouble("x");
        }

        if (config.contains("y")) {
            y = config.getDouble("y");
        }
    }

    /* Getters and setters */
    /**
     * @param player
     * the player to replace the placeholders
     * @return
     * the button text; placeholders are replaced.
     */
    public String getLabel(Player player) {
        return Placeholder.parse(player, label);
    }

    /**
     * @param label
     * the button text to set
     */
    public void setLabel(String label) {
        this.label = label;
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
     * the command to execute
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command
     * the command to set
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return
     * the sound to play
     */
    public String getSound() {
        return sound;
    }

    /**
     * @param sound
     * the sound name to set
     */
    public void setSound(String sound) {
        this.sound = sound;
    }

    /**
     * @return
     * the relative X value of the button
     */
    public double getX() {
        return x;
    }

    /**
     * @param x
     * the relative X value of the button to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return
     * the relative X value of the button
     */
    public double getY() {
        return x;
    }

    /**
     * @param y
     * the relative Y value of the button to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /* Actions */
    /**
     * @return
     * the button as a ConfigurationSection
     */
    public ConfigurationSection serialize() {
        YamlConfiguration config = new YamlConfiguration();

        config.set("label", label);
        config.set("type", type);
        config.set("command", command);
        config.set("sound", sound);
        config.set("x", x);
        config.set("y", y);

        return config;
    }

    /**
     * @param provider
     * the HologramWrapper of the loaded hologram provider plugin
     * @param viewers
     * the players that can see the holograms
     * @param location
     * the location where the menu will open
     * @param direction
     * the facing direction
     * @return
     * the created Hologram
     */
    public Hologram open(HologramWrapper provider, Location anchor, Vector direction, Player[] viewers) {
        Hologram hologram = provider.createHologram(getLocation(anchor, direction), getLabel(viewers[0]), viewers);
        hologram.setButton(this);
        return hologram;
    }

    public Location getLocation(Location anchor, Vector direction) {
        Vector orthogonal = direction.getCrossProduct(new Vector(0, 1, 0)).multiply(x);
        Vector position = direction.clone().setY(0).add(orthogonal);
        return anchor.clone().add(0, y, 0).add(position);
    }

}
