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
package de.erethon.holographicmenus.player;

import de.erethon.commons.misc.EnumUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import static org.bukkit.permissions.PermissionDefault.*;

/**
 * @author Daniel Saukel
 */
public enum HPermission {

    // Main nodes
    HELP("help", TRUE),
    INFO("info", TRUE),
    MENU("menu", TRUE),
    PERMA_MENU("permamenu", OP),
    RELOAD("reload", OP),
    // Kits
    ADMINISTRATOR("*", OP),
    PLAYER("player", TRUE, HELP, INFO, MENU);

    public static final String PREFIX = "holographicmenus.";

    private String node;
    private PermissionDefault isDefault;
    private List<HPermission> children = new ArrayList<>();

    HPermission(String node, PermissionDefault isDefault) {
        this.node = node;
        this.isDefault = isDefault;
    }

    HPermission(String node, PermissionDefault isDefault, HPermission... children) {
        this(node, isDefault);
        this.children = Arrays.asList(children);
    }

    /**
     * @return the permission node String
     */
    public String getNode() {
        return PREFIX + node;
    }

    /**
     * @return if a player has the node by default
     */
    public PermissionDefault isDefault() {
        return isDefault;
    }

    /**
     * @return if the node has children
     */
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    /**
     * @return the child permissions
     */
    public List<HPermission> getChildren() {
        return children;
    }

    /**
     * @param node
     * the node String, with or without "dxl."
     * @return
     * the HPermissions value
     */
    public static HPermission getByNode(String node) {
        for (HPermission permission : values()) {
            if (permission.getNode().equals(node) || permission.node.equals(node)) {
                return permission;
            }
        }

        return null;
    }

    /**
     * @param permission
     * the permission to check
     * @return if the player has the permission
     */
    public static boolean hasPermission(CommandSender sender, HPermission permission) {
        if (sender.hasPermission(permission.getNode())) {
            return true;
        }

        for (HPermission parent : HPermission.values()) {
            if (parent.getChildren().contains(permission) && sender.hasPermission(parent.getNode())) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param permission
     * the permission to check
     * @return if the player has the permission
     */
    public static boolean hasPermission(CommandSender sender, String permission) {
        if (sender.hasPermission(permission)) {
            return true;
        }

        HPermission hPermission = null;
        if (EnumUtil.isValidEnum(HPermission.class, permission)) {
            hPermission = HPermission.valueOf(permission);

        } else if (HPermission.getByNode(permission) != null) {
            hPermission = HPermission.getByNode(permission);
        }

        if (hPermission == null) {
            return false;
        }

        for (HPermission parent : HPermission.values()) {
            if (parent.getChildren().contains(hPermission) && sender.hasPermission(parent.getNode())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Registers the permissions.
     */
    public static void register() {
        for (HPermission permission : values()) {
            Bukkit.getPluginManager().addPermission(new Permission(permission.getNode(), permission.isDefault()));
        }
    }

}
