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
package de.erethon.holographicmenus.hologram;

import de.erethon.holographicmenus.HolographicMenus;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.plugin.Plugin;

/**
 * @author Daniel Saukel
 */
public class HologramProviderManager {

    private HolographicMenus plugin;

    private Map<String, HologramWrapper> providers = new HashMap<>();

    public HologramProviderManager(HolographicMenus plugin) {
        this.plugin = plugin;
        registerProvider("HolographicDisplays", new HolographicDisplaysWrapper(plugin));
    }

    /**
     * Registers a new plugin that can provide holograms
     *
     * @param plugin
     * a plugin providing holograms
     * @param wrapper
     * an instance of the respective HologramWrapper implementation
     */
    public void registerProvider(Plugin plugin, HologramWrapper wrapper) {
        providers.put(plugin.getName(), wrapper);
    }

    private void registerProvider(String pluginName, HologramWrapper wrapper) {
        providers.put(pluginName, wrapper);
    }

    /**
     * @return
     * a HologramWrapper of an enabled provider plugin
     */
    public HologramWrapper getEnabled() {
        for (Entry<String, HologramWrapper> entry : providers.entrySet()) {
            if (plugin.getServer().getPluginManager().isPluginEnabled(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

}
