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
package io.github.dre2n.holographicmenus.player;

import io.github.dre2n.commons.util.EnumUtil;
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
public enum HPermissions {

    // Main nodes
    HELP("help", TRUE),
    MAIN("main", TRUE),
    PORTAL("portal", OP),
    RELOAD("reload", OP),
    // Kits
    ADMINISTRATOR("*", OP),
    PLAYER("player", TRUE, HELP, MAIN);

    public static final String PREFIX = "dxl.";

    private String node;
    private PermissionDefault isDefault;
    private List<HPermissions> children = new ArrayList<>();

    HPermissions(String node, PermissionDefault isDefault) {
        this.node = node;
        this.isDefault = isDefault;
    }

    HPermissions(String node, PermissionDefault isDefault, HPermissions... children) {
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
    public List<HPermissions> getChildren() {
        return children;
    }

    /**
     * @param node
     * the node String, with or without "dxl."
     * @return
     * the DPermissions value
     */
    public static HPermissions getByNode(String node) {
        for (HPermissions permission : values()) {
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
    public static boolean hasPermission(CommandSender sender, HPermissions permission) {
        if (sender.hasPermission(permission.getNode())) {
            return true;
        }

        for (HPermissions parent : HPermissions.values()) {
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

        HPermissions dPermission = null;
        if (EnumUtil.isValidEnum(HPermissions.class, permission)) {
            dPermission = HPermissions.valueOf(permission);

        } else if (HPermissions.getByNode(permission) != null) {
            dPermission = HPermissions.getByNode(permission);
        }

        if (dPermission == null) {
            return false;
        }

        for (HPermissions parent : HPermissions.values()) {
            if (parent.getChildren().contains(dPermission) && sender.hasPermission(parent.getNode())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Registers the permissions.
     */
    public static void register() {
        for (HPermissions permission : values()) {
            Bukkit.getPluginManager().addPermission(new Permission(permission.getNode(), permission.isDefault()));
        }
    }

}
