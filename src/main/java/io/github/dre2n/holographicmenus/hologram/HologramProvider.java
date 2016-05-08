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
package io.github.dre2n.holographicmenus.hologram;

import org.bukkit.Bukkit;

/**
 * @author Daniel Saukel
 */
public enum HologramProvider {

    HOLOGRAPHIC_DISPLAYS("HolographicDisplays", new HolographicDisplaysWrapper());

    private String name;
    private HologramWrapper wrapper;

    HologramProvider(String name, HologramWrapper wrapper) {
        this.name = name;
        this.wrapper = wrapper;
    }

    /**
     * @return
     * the name of the provider plugin
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     * an instance of the wrapper class
     */
    public HologramWrapper getWrapper() {
        return wrapper;
    }

    /* Statics */
    public static HologramProvider getProvider() {
        for (HologramProvider provider : values()) {
            if (Bukkit.getPluginManager().isPluginEnabled(provider.getName())) {
                return provider;
            }
        }

        return null;
    }

}
