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

import de.erethon.holographicmenus.menu.HButton.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.inventory.ItemStack;

/**
 * Builder class for buttons.
 *
 * @author Daniel Saukel
 */
public class HButtonBuilder {

    String label;
    ItemStack item;
    Type type = Type.BUTTON;
    String command;
    List<String> varMsgs = new ArrayList<>();
    String sound;
    String permission;
    boolean closeMenu = false;
    Double x;
    Double y;

    /**
     * @param label
     * the text to show
     */
    public HButtonBuilder(String label) {
        this.label = label;
    }

    /**
     * @param item
     * an item to show instead of text
     */
    public HButtonBuilder(ItemStack item) {
        this.item = item;
    }

    /**
     * If this method is not called, the BUTTON type will be used
     *
     * @param type
     * the button type to set
     */
    public HButtonBuilder type(Type type) {
        this.type = type;
        return this;
    }

    /**
     * @param command
     * the command to perform when the button is touched
     */
    public HButtonBuilder cmd(String command) {
        this.command = command;
        return this;
    }

    /**
     * @param messages
     * the messages to send to ask for chat input
     */
    public HButtonBuilder varMsgs(String... messages) {
        varMsgs.addAll(Arrays.asList(messages));
        return this;
    }

    /**
     * @param sound
     * the sound to play when the button is touched
     */
    public HButtonBuilder sound(String sound) {
        this.sound = sound;
        return this;
    }

    /**
     * @param node
     * the permission node a player needs to see this button
     */
    public HButtonBuilder perm(String node) {
        permission = node;
        return this;
    }

    /**
     * @param closeMenu
     * if the menu should be closed when the button is touched
     */
    public HButtonBuilder close(boolean closeMenu) {
        this.closeMenu = closeMenu;
        return this;
    }

    /**
     * @param x
     * the X value
     * @param y
     * the y value
     */
    public HButtonBuilder pos(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public HButton build() {
        boolean content = label != null || item != null;
        boolean pos = x != null && y != null;
        if (content && pos) {
            return new HButton(this);
        } else {
            throw new IllegalStateException(content ? "No position specified" : "No button content specified");
        }
    }

}
