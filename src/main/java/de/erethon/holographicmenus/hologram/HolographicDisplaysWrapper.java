/*
 * Copyright (C) 2016-2017 Daniel Saukel
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

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class HolographicDisplaysWrapper extends HologramWrapper {

    @Override
    public int createHologram(Set<Player> viewers, Location location, String label) {
        Hologram hologram = createHologram(viewers, location);
        hologram.appendTextLine(label);

        holograms.add(hologram);
        return holograms.indexOf(hologram);
    }

    @Override
    public int createHologram(Set<Player> viewers, Location location, ItemStack item) {
        Hologram hologram = createHologram(viewers, location);
        hologram.appendItemLine(item);

        holograms.add(hologram);
        return holograms.indexOf(hologram);
    }

    public Hologram createHologram(Set<Player> viewers, Location location) {
        Hologram hologram = HologramsAPI.createHologram(plugin, location);
        if (viewers != null) {
            hologram.getVisibilityManager().setVisibleByDefault(false);
            for (Player player : viewers) {
                hologram.getVisibilityManager().showTo(player);
            }
        }
        return hologram;
    }

}
