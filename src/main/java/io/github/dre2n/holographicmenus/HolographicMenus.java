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
package io.github.dre2n.holographicmenus;

import io.github.dre2n.commons.compatibility.Internals;
import io.github.dre2n.commons.config.MessageConfig;
import io.github.dre2n.commons.javaplugin.BRPlugin;
import io.github.dre2n.commons.javaplugin.BRPluginSettings;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import io.github.dre2n.holographicmenus.command.HCommands;
import io.github.dre2n.holographicmenus.config.HMessages;
import io.github.dre2n.holographicmenus.hologram.HologramProvider;
import io.github.dre2n.holographicmenus.hologram.HologramWrapper;
import io.github.dre2n.holographicmenus.menu.HMenus;
import java.io.File;

/**
 * @author Daniel Saukel
 */
public class HolographicMenus extends BRPlugin {

    private static HolographicMenus instance;

    public static File LANGUAGES;
    public static File MENUS;

    private HologramProvider hologramProvider;
    private MessageConfig messageConfig;
    private HMenus menus;
    private HCommands hCommands;

    public HolographicMenus() {
        /*
         * ##########################
         * ####~BRPluginSettings~####
         * ##########################
         * #~Internals~##~~~INDEP~~~#
         * #~SpigotAPI~##~~~false~~~#
         * #~~~~UUID~~~##~~~false~~~#
         * #~~Economy~~##~~~false~~~#
         * #Permissions##~~~false~~~#
         * #~~Metrics~~##~~~true~~~~#
         * ##########################
         */

        settings = new BRPluginSettings(false, false, false, false, true, 9389, Internals.INDEPENDENT);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        initFolders();

        if (!loadHologramProvider()) {
            MessageUtil.log(this, "&4[SEVERE] No hologram provider found! Disabling plugin...");
            instance = null;
            getServer().getPluginManager().disablePlugin(this);
        }
        loadMessageConfig(new File(LANGUAGES, "english.yml"));
        loadMenus();
        loadHCommands();
    }

    public void initFolders() {
        LANGUAGES = new File(getDataFolder(), "languages");
        if (!LANGUAGES.exists()) {
            LANGUAGES.mkdir();
        }

        MENUS = new File(getDataFolder(), "menus");
        if (!MENUS.exists()) {
            MENUS.mkdir();
        }
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
     * the loaded HologramWrapper
     */
    public HologramWrapper getHologramWrapper() {
        return hologramProvider.getWrapper();
    }

    /**
     * load / reload a HologramProvider
     */
    public boolean loadHologramProvider() {
        hologramProvider = HologramProvider.getProvider();
        return hologramProvider != null;
    }

    /**
     * @return the loaded instance of MessageConfig
     */
    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    /**
     * load / reload a new instance of MessageConfig
     */
    public void loadMessageConfig(File file) {
        messageConfig = new MessageConfig(HMessages.class, file);
    }

    /**
     * @return the loaded instance of HCommands
     */
    @Override
    public HCommands getCommands() {
        return hCommands;
    }

    /**
     * load / reload a new instance of HCommands
     */
    public void loadHCommands() {
        hCommands = new HCommands(this);
        hCommands.register(this);
    }

    /**
     * return
     * the loaded instance of HMenus
     */
    public HMenus getHMenus() {
        return menus;
    }

    /**
     * load / reload the menus
     */
    public void loadMenus() {
        menus = new HMenus(MENUS);
    }

}
