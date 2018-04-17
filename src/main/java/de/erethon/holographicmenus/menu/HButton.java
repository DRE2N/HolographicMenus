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
import de.erethon.commons.misc.NumberUtil;
import de.erethon.holographicmenus.HolographicMenus;
import de.erethon.holographicmenus.hologram.Hologram;
import de.erethon.holographicmenus.hologram.HologramCollection;
import de.erethon.holographicmenus.hologram.HologramWrapper;
import de.erethon.holographicmenus.player.HPermission;
import de.erethon.holographicmenus.player.HPlayer;
import de.erethon.holographicmenus.util.Placeholder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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

    private interface ClickHandler {
        default void onClick(HolographicMenus plugin, HPlayer player, Hologram hologram) {
        }
    }

    public enum Type implements ClickHandler {
        /**
         * A button that is not supposed to be touched.
         */
        TITLE,
        /**
         * A button that runs a command when touched.
         */
        BUTTON {
            @Override
            public void onClick(HolographicMenus plugin, HPlayer player, Hologram hologram) {
                HButton button = hologram.getButton();
                if (button.getCommand() != null) {
                    if (button.hasCommandVariables()) {
                        player.setPendingCommand(button);
                    } else {
                        player.getPlayer().performCommand(button.getCommand());
                    }
                }
            }
        },
        /**
         * Link button to the very first page.
         */
        FIRST_PAGE {
            @Override
            public void onClick(HolographicMenus plugin, HPlayer player, Hologram hologram) {
                HologramCollection associated = hologram.getAssociatedHolograms();
                associated.getMenu().open(plugin, 1, associated.getLocation(), associated.getDirection(), player.getPlayer());
            }
        },
        /**
         * Link button to the page before the currently opened one.
         */
        PREVIOUS_PAGE {
            @Override
            public void onClick(HolographicMenus plugin, HPlayer player, Hologram hologram) {
                HologramCollection associated = hologram.getAssociatedHolograms();
                int previous = associated.getPage() - 1;
                int last = associated.getMenu().getMenuPages().size();
                associated.getMenu().open(plugin, previous < 1 ? last : previous, associated.getLocation(), associated.getDirection(), player.getPlayer());
            }
        },
        /**
         * Link button to the page after the currently opened one.
         */
        NEXT_PAGE {
            @Override
            public void onClick(HolographicMenus plugin, HPlayer player, Hologram hologram) {
                HologramCollection associated = hologram.getAssociatedHolograms();
                int next = associated.getPage() + 1;
                int last = associated.getMenu().getMenuPages().size();
                associated.getMenu().open(plugin, next > last ? 1 : next, associated.getLocation(), associated.getDirection(), player.getPlayer());
            }
        },
        /**
         * Link button to the very last page.
         */
        LAST_PAGE {
            @Override
            public void onClick(HolographicMenus plugin, HPlayer player, Hologram hologram) {
                HologramCollection associated = hologram.getAssociatedHolograms();
                associated.getMenu().open(plugin, associated.getMenu().getMenuPages().size(), associated.getLocation(), associated.getDirection(), player.getPlayer());
            }
        };
    }

    private String label;
    private ItemStack item;
    private Type type;
    private String command;
    private int commandVariables = -1;
    private List<String> varMsgs;
    private String sound;
    private String permission;
    private boolean closeMenu;
    private double x;
    private double y;

    HButton(HButtonBuilder builder) {
        label = builder.label != null ? builder.label : new String();
        item = builder.item;
        type = builder.type;
        command = builder.command;
        varMsgs = builder.varMsgs;
        sound = builder.sound;
        permission = builder.permission;
        closeMenu = builder.closeMenu;
        x = builder.x;
        y = builder.y;
    }

    public HButton(String label, Type type, double x, double y) {
        this.label = label;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public HButton(ItemStack item, Type type, double x, double y) {
        this.label = new String();
        this.item = item;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public HButton(ConfigurationSection config) {
        label = config.getString("label", new String());
        item = deserializeItemStack(config.getString("item"));
        type = EnumUtil.getEnum(Type.class, config.getString("type", null));
        if (type == null) {
            type = Type.BUTTON;
        }
        command = config.getString("command", null);
        varMsgs = config.getStringList("variableMessages");
        sound = config.getString("sound", null);
        permission = config.getString("permission", null);
        closeMenu = config.getBoolean("closeMenu", false);
        x = config.getDouble("x", 0);
        y = config.getDouble("y", 0);
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
     * @return item
     * the button item
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * @param item
     * the button item to set
     */
    public void setItem(ItemStack item) {
        this.item = item;
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
     * if the command contains command variables that are to be replaced with chat input
     */
    public boolean hasCommandVariables() {
        return command != null && command.contains("%v1%");
    }

    /**
     * @return
     * the amount of variables that are to be replaced with chat input
     */
    public int getCommandVariables() {
        if (command == null) {
            return 0;
        }
        if (commandVariables != -1) {
            return commandVariables;
        }
        commandVariables = 1;
        while (true) {
            String v = "%v" + commandVariables + "%";
            if (command.contains(v)) {
                commandVariables++;
            } else {
                commandVariables--;
                return commandVariables;
            }
        }
    }

    /**
     * @return
     * the messages sent to a player to ask for command arguments
     */
    public List<String> getVariableMessages() {
        return varMsgs;
    }

    /**
     * @param i
     * the command argument number
     * @return
     * the message to sent to a player to ask for the command argument at index i - 1
     */
    public String getVariableMessage(int i) {
        return varMsgs.get(i - 1);
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
     * the permission to see the button
     */
    public boolean hasPermission() {
        return permission != null;
    }

    /**
     * @return
     * the permission to see the button
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission
     * the permission to set
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * @return
     * if the button closes the menu
     */
    public boolean isClosingMenu() {
        return closeMenu;
    }

    /**
     * @param closeMenu
     * if the button shall close the menu
     */
    public void setClosingMenu(boolean closeMenu) {
        this.closeMenu = closeMenu;
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
        config.set("item", serializeItemStack(item));
        config.set("type", type.toString());
        config.set("command", command);
        config.set("variableMessages", varMsgs);
        config.set("sound", sound);
        config.set("permission", permission);
        config.set("closeMenu", closeMenu);
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
        Collection<Player> allowedViewers = null;
        if (viewers != null && viewers.length != 0) {
            if (hasPermission()) {
                allowedViewers = new ArrayList<>();
                for (Player viewer : viewers) {
                    if (HPermission.hasPermission(viewer, getPermission())) {
                        allowedViewers.add(viewer);
                    }
                }
            } else {
                allowedViewers = new ArrayList<>(Arrays.asList(viewers));
            }
        }
        Player opener = viewers != null && viewers.length != 0 ? viewers[0] : null;
        Hologram hologram = null;
        if (item != null) {
            hologram = provider.createHologram(getLocation(anchor, direction), item, allowedViewers);
        } else {
            hologram = provider.createHologram(getLocation(anchor, direction), getLabel(opener), allowedViewers);
        }
        hologram.setButton(this);
        return hologram;
    }

    public Location getLocation(Location anchor, Vector direction) {
        Vector orthogonal = getCrossProduct(direction, new Vector(0, 1, 0)).multiply(x);
        Vector position = direction.clone().setY(0).add(orthogonal);
        return anchor.clone().add(0, y, 0).add(position);
    }

    /**
     * The Bukkit method didn't exist in earlier versions :(
     *
     * @param v1
     * a vector
     * @param v2
     * another vector
     * @return
     * the cross product of the vectors
     */
    private static Vector getCrossProduct(Vector v1, Vector v2) {
        double x = v1.getY() * v2.getZ() - v2.getY() * v1.getZ();
        double y = v1.getZ() * v2.getX() - v2.getZ() * v1.getX();
        double z = v1.getX() * v2.getY() - v2.getX() * v1.getY();
        return new Vector(x, y, z);
    }

    @Deprecated
    public static String serializeItemStack(ItemStack item) {
        if (item == null) {
            return null;
        }
        return item.getDurability() != 0 ? item.getType().name() + ":" + item.getDurability() : item.getType().name();
    }

    @Deprecated
    public static ItemStack deserializeItemStack(String string) {
        if (string == null) {
            return null;
        }
        String[] args = string.split(":");
        if (args.length >= 1) {
            Material material = EnumUtil.getEnumIgnoreCase(Material.class, args[0]);
            if (material == null) {
                return null;
            }
            if (args.length == 2) {
                return new ItemStack(material, 1, (short) NumberUtil.parseInt(args[1]));
            } else if (args.length == 1) {
                return new ItemStack(material);
            }
        }
        return null;
    }

}
