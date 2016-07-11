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
import io.github.dre2n.commons.javaplugin.BRPlugin;
import io.github.dre2n.commons.javaplugin.BRPluginSettings;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import io.github.dre2n.holographicmenus.hologram.HologramProvider;
import io.github.dre2n.holographicmenus.hologram.HologramWrapper;
import io.github.dre2n.holographicmenus.menu.HButton;
import io.github.dre2n.holographicmenus.menu.HMenu;
import io.github.dre2n.holographicmenus.menu.HMenuPage;
import io.github.dre2n.holographicmenus.menu.HMenus;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public class HolographicMenus extends BRPlugin {

    private static HolographicMenus instance;

    public static File HOLOGRAM_FOLDER;

    private HologramProvider hologramProvider;
    private HMenus menus;

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
        HOLOGRAM_FOLDER = new File(getDataFolder(), "menus");
        HOLOGRAM_FOLDER.mkdirs();

        if (!loadHologramProvider()) {
            MessageUtil.log(this, "&4[SEVERE] No hologram provider found! Disabling plugin...");
            instance = null;
            getServer().getPluginManager().disablePlugin(this);
        }

        loadMenus();
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
        menus = new HMenus(HOLOGRAM_FOLDER);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        new HMenu("TEST", HMenu.Type.PRIVATE, Arrays.asList(new HMenuPage(new HashSet<>(Arrays.asList(new HButton("BUTTON 1", HButton.Type.TITLE, null, 0.4, 0.7), new HButton("BUTTON 2", HButton.Type.TITLE, null, -0.4, 0.4))))), 2.0).open((Player) sender);
        return true;
    }

}
