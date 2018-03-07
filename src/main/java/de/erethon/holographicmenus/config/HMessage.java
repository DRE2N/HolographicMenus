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
package de.erethon.holographicmenus.config;

import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.config.Message;
import de.erethon.commons.javaplugin.DREPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Daniel Saukel
 */
public enum HMessage implements Message {

    CMD_INFO_HELP("cmd.info.help", "&7Type in &o/hm help&r&7 for further information."),
    CMD_INFO_LOADED("cmd.info.loaded", "&eHolographicDisplays: &o[&v1]"),
    CMD_INFO_WELCOME("cmd.info.welcome", "&7Welcome to &4HolographicMenus"),
    CMD_RELOAD_DONE("cmd.reload.done", "&7Successfully reloaded HolographicMenus."),
    ERROR_NO_SUCH_MENU("error.noSuchMenu", "&4The menu &6&v1&4 does not exist."),
    ERROR_NO_SUCH_PLAYER("error.noSuchPlayer", "&4The player &6&v1&4 does not exist."),
    HELP_HELP("help.help", "/hm help [page] - Shows the help page"),
    HELP_INFO("help.info", "/hm info - General status information"),
    HELP_MENU("help.menu", "/hm menu ([name]) - Opens a holographic menu");

    private String identifier;
    private String message;

    HMessage(String identifier, String message) {
        this.identifier = identifier;
        this.message = message;
    }

    /* Getters and setters */
    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getMessage() {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public String getMessage(String... args) {
        return DREPlugin.getInstance().getMessageConfig().getMessage(this, args);
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /* Actions */
    /**
     * Sends the message to the console.
     */
    public void debug() {
        MessageUtil.log(DREPlugin.getInstance(), getMessage());
    }

    /* Statics */
    /**
     * @param identifer
     * the identifer to set
     */
    public static Message getByIdentifier(String identifier) {
        for (Message message : values()) {
            if (message.getIdentifier().equals(identifier)) {
                return message;
            }
        }

        return null;
    }

    /**
     * @return a FileConfiguration containing all messages
     */
    public static FileConfiguration toConfig() {
        FileConfiguration config = new YamlConfiguration();
        for (HMessage message : values()) {
            config.set(message.getIdentifier(), message.message);
        }

        return config;
    }

}
