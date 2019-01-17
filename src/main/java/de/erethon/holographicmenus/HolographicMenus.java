/*
 * Copyright (C) 2016-2019 Daniel Saukel
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
package de.erethon.holographicmenus;

import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.compatibility.Internals;
import de.erethon.commons.config.MessageConfig;
import de.erethon.commons.javaplugin.DREPlugin;
import de.erethon.commons.javaplugin.DREPluginSettings;
import de.erethon.holographicmenus.command.HCommandCache;
import de.erethon.holographicmenus.config.HConfig;
import de.erethon.holographicmenus.config.HMessage;
import de.erethon.holographicmenus.hologram.HologramProviderManager;
import de.erethon.holographicmenus.hologram.HologramWrapper;
import de.erethon.holographicmenus.menu.HMenuCache;
import de.erethon.holographicmenus.player.HPermission;
import de.erethon.holographicmenus.player.HPlayerCache;
import de.erethon.holographicmenus.player.PlayerListener;
import java.io.File;

/**
 * The main class of HolographicMenus
 *
 * @author Daniel Saukel
 */
public class HolographicMenus extends DREPlugin {

    private static HolographicMenus instance;

    public static File LANGUAGES;
    public static File MENUS;

    private HConfig config;
    private HologramProviderManager providers;
    private HologramWrapper hologramProvider;
    private HMenuCache menus;
    private HCommandCache commands;
    private HPlayerCache players;

    public HolographicMenus() {
        /*
         * ##########################
         * ####~DREPluginSettings####
         * ##########################
         * #~Internals~##~~~INDEP~~~#
         * #~SpigotAPI~##~~~false~~~#
         * #~~~~UUID~~~##~~~false~~~#
         * #~~Economy~~##~~~false~~~#
         * #Permissions##~~~false~~~#
         * #~~Metrics~~##~~~true~~~~#
         * #Resource ID##~~~~9389~~~#
         * ##########################
         */

        settings = new DREPluginSettings(false, false, false, false, true, 9389, Internals.INDEPENDENT);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        providers = new HologramProviderManager(this);
        initFolders();
        if (!loadHologramProvider()) {
            MessageUtil.log(this, "&4[SEVERE] No hologram provider found! Disabling plugin...");
            instance = null;
            getServer().getPluginManager().disablePlugin(this);
        }
        HPermission.register();
        loadCore();
        loadHPlayerCache();
        manager.registerEvents(new PlayerListener(this), this);
    }

    public void initFolders() {
        getDataFolder().mkdir();
        LANGUAGES = new File(getDataFolder(), "languages");
        if (!LANGUAGES.exists()) {
            LANGUAGES.mkdir();
        }

        MENUS = new File(getDataFolder(), "menus");
        if (!MENUS.exists()) {
            MENUS.mkdir();
        }
    }

    public void loadCore() {
        loadMessageConfig(new File(LANGUAGES, "english.yml"));
        loadHConfig(new File(getDataFolder(), "config.yml"));
        loadMessageConfig(new File(LANGUAGES, config.getLanguage() + ".yml"));
        loadMenuCache();
        loadHCommandCache();
    }

    /* Getters and loaders */
    /**
     * @return the plugin instance
     */
    public static HolographicMenus getInstance() {
        return instance;
    }

    /**
     * @return
     * the loaded instance of HConfig
     */
    public HConfig getHConfig() {
        return config;
    }

    /**
     * load / reload a new instance of HConfig
     */
    public void loadHConfig(File file) {
        config = new HConfig(file);
    }

    /**
     * @return
     * the loaded HologramWrapper
     */
    public HologramWrapper getHologramProvider() {
        return hologramProvider;
    }

    /**
     * load / reload a HologramWrapper
     */
    public boolean loadHologramProvider() {
        hologramProvider = providers.getEnabled();
        return hologramProvider != null;
    }

    /**
     * load / reload a new instance of MessageConfig
     */
    public void loadMessageConfig(File file) {
        messageConfig = new MessageConfig(HMessage.class, file);
    }

    /**
     * @return
     * the loaded instance of HCommandCache
     */
    @Override
    public HCommandCache getCommandCache() {
        return commands;
    }

    /**
     * load / reload a new instance of HCommandCache
     */
    public void loadHCommandCache() {
        commands = new HCommandCache(this);
        commands.register(this);
    }

    /**
     * return
     * the loaded instance of HMenuCache
     */
    public HMenuCache getHMenuCache() {
        return menus;
    }

    /**
     * load / reload the menus
     */
    public void loadMenuCache() {
        menus = new HMenuCache(this, MENUS);
    }

    /**
     * return
     * the loaded instance of HPlayerCache
     */
    public HPlayerCache getHPlayerCache() {
        return players;
    }

    /**
     * load / reload HPlayerCache
     */
    public void loadHPlayerCache() {
        players = new HPlayerCache();
    }

}
