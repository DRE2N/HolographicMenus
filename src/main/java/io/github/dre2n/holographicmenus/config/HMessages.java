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
package io.github.dre2n.holographicmenus.config;

import io.github.dre2n.commons.config.Messages;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import io.github.dre2n.holographicmenus.HolographicMenus;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Daniel Saukel
 */
public enum HMessages implements Messages {

    CMD_MAIN_HELP("Cmd_Main_Help", "&7Type in &o/hm help&r&7 for further information."),
    CMD_MAIN_LOADED("Cmd_Main_Loaded", "&eHolographicDisplays: &o[&v1]"),
    CMD_MAIN_WELCOME("Cmd_Main_Welcome", "&7Welcome to &4HolographicMenus"),
    CMD_RELOAD_DONE("Cmd_Reload_Done", "&7Successfully reloaded HolographicMenus."),
    ERROR_NO_SUCH_PLAYER("Error_NoSuchPlayer", "&4The player &6&v1&4 does not exist!"),
    HELP_CMD_HELP("Help_Cmd_Help", "/hm help [page] - Shows the help page"),
    HELP_CMD_MAIN("Help_Cmd_Main", "/hm - General status information");

    private String identifier;
    private String message;

    HMessages(String identifier, String message) {
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
        return HolographicMenus.getInstance().getMessageConfig().getMessage(this, args);
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
        MessageUtil.log(HolographicMenus.getInstance(), getMessage());
    }

    /* Statics */
    /**
     * @param identifer
     * the identifer to set
     */
    public static Messages getByIdentifier(String identifier) {
        for (Messages message : values()) {
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
        for (HMessages message : values()) {
            config.set(message.getIdentifier(), message.message);
        }

        return config;
    }

}
