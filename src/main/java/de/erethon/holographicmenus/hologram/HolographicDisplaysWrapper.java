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

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import de.erethon.holographicmenus.HolographicMenus;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class HolographicDisplaysWrapper implements HologramWrapper {

    private HolographicMenus plugin;

    public HolographicDisplaysWrapper(HolographicMenus plugin) {
        this.plugin = plugin;
    }

    @Override
    public de.erethon.holographicmenus.hologram.Hologram createHologram(Location location, String label, Player... viewers) {
        Hologram hdHolo = createHologram(viewers, location);
        de.erethon.holographicmenus.hologram.Hologram hmHolo = new de.erethon.holographicmenus.hologram.Hologram(plugin, location, hdHolo);
        TextLine line = hdHolo.appendTextLine(label);
        line.setTouchHandler(new TouchHandler() {
            @Override
            public void onTouch(Player player) {
                hmHolo.click(plugin.getHPlayerCache().getByPlayer(player));
            }
        });
        return hmHolo;
    }

    @Override
    public de.erethon.holographicmenus.hologram.Hologram createHologram(Location location, ItemStack item, Player... viewers) {
        Hologram hdHolo = createHologram(viewers, location);
        de.erethon.holographicmenus.hologram.Hologram hmHolo = new de.erethon.holographicmenus.hologram.Hologram(plugin, location, hdHolo);
        ItemLine line = hdHolo.appendItemLine(item);
        line.setTouchHandler(new TouchHandler() {
            @Override
            public void onTouch(Player player) {
                hmHolo.click(plugin.getHPlayerCache().getByPlayer(player));
            }
        });
        return hmHolo;
    }

    public Hologram createHologram(Player[] viewers, Location location) {
        Hologram hologram = HologramsAPI.createHologram(plugin, location);
        if (viewers != null) {
            hologram.getVisibilityManager().setVisibleByDefault(false);
            for (Player player : viewers) {
                hologram.getVisibilityManager().showTo(player);
            }
        }
        return hologram;
    }

    @Override
    public void deleteHologram(de.erethon.holographicmenus.hologram.Hologram hologram) {
        ((Hologram) hologram.getRawHologram()).delete();
    }

    @Override
    public void moveHologram(de.erethon.holographicmenus.hologram.Hologram hologram, Location location) {
        ((Hologram) hologram.getRawHologram()).teleport(location);
    }

}
