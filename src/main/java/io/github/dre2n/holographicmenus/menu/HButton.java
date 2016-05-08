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

import io.github.dre2n.commons.util.EnumUtil;
import io.github.dre2n.holographicmenus.HolographicMenus;
import io.github.dre2n.holographicmenus.hologram.HologramWrapper;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * @author Daniel Saukel
 */
public class HButton {

    protected static HologramWrapper hologramWrapper = HolographicMenus.getInstance().getHologramWrapper();

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
    private double x;
    private double y;

    public HButton(String label, Type type, String command, double x, double y) {
        this.label = label;
        this.type = type;
        this.command = command;
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

        if (config.contains("x")) {
            x = config.getDouble("x");
        }

        if (config.contains("y")) {
            y = config.getDouble("y");
        }
    }

    /* Getters and setters */
    /**
     * @return
     * the button text; color codes are replaced.
     */
    public String getLabel() {
        return ChatColor.translateAlternateColorCodes('&', label);
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
    public ConfigurationSection toConfig() {
        YamlConfiguration config = new YamlConfiguration();

        config.set("label", label);
        config.set("type", type);
        config.set("command", command);
        config.set("x", x);
        config.set("y", y);

        return config;
    }

    /**
     * @param location
     * the location where the menu will open
     * @param direction
     * the facing direction
     */
    public void open(Location anchor, Vector direction) {
        Vector orthogonal = direction.getCrossProduct(new Vector(0, 1, 0)).multiply(x);
        Vector position = direction.setY(0).add(orthogonal);
        Location location = anchor.clone().add(0, y, 0).add(position);
        hologramWrapper.createHologram(null, location, getLabel());
    }

    /**
     * @param player
     * the Player who clicked the button
     */
    public void click(Player player) {
        switch (type) {
            case BUTTON:
                player.performCommand(command);
                break;

            case FIRST_PAGE:
                // FIRST_PAGE
                break;

            case PREVIOUS_PAGE:
                // Previous page
                break;

            case NEXT_PAGE:
                // Next page
                break;

            case LAST_PAGE:
                // Last page
                break;

            case CLOSE:
                //close;
                break;
        }
    }

}
