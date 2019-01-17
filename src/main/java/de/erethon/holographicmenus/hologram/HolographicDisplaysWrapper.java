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
package de.erethon.holographicmenus.hologram;

import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import de.erethon.holographicmenus.HolographicMenus;
import java.util.Collection;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class HolographicDisplaysWrapper implements HologramWrapper, Listener {

    private HolographicMenus plugin;

    public HolographicDisplaysWrapper(HolographicMenus plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public de.erethon.holographicmenus.hologram.Hologram createHologram(Location location, String label, Collection<Player> viewers) {
        Hologram hdHolo = createHologram(viewers, location);
        de.erethon.holographicmenus.hologram.Hologram hmHolo = new de.erethon.holographicmenus.hologram.Hologram(plugin, location, hdHolo);
        TextLine line = hdHolo.appendTextLine(label);
        line.setTouchHandler(p -> hmHolo.click(plugin.getHPlayerCache().getByPlayer(p)));
        return hmHolo;
    }

    @Override
    public de.erethon.holographicmenus.hologram.Hologram createHologram(Location location, ItemStack item, Collection<Player> viewers) {
        Hologram hdHolo = createHologram(viewers, location);
        de.erethon.holographicmenus.hologram.Hologram hmHolo = new de.erethon.holographicmenus.hologram.Hologram(plugin, location, hdHolo);
        ItemLine line = hdHolo.appendItemLine(item);
        line.setTouchHandler(p -> hmHolo.click(plugin.getHPlayerCache().getByPlayer(p)));
        return hmHolo;
    }

    public Hologram createHologram(Collection<Player> viewers, Location location) {
        Hologram hologram = HologramsAPI.createHologram(plugin, location);
        if (viewers != null) {
            hologram.getVisibilityManager().setVisibleByDefault(false);
            viewers.forEach(hologram.getVisibilityManager()::showTo);
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

    // This method cancels the interact event if a button is touched sothat protection plugins don't send error messages.
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteractEntityLowest(PlayerInteractEntityEvent event) {
        Hologram hologram = getByEntity(event.getRightClicked());
        if (hologram != null && HologramsAPI.getHolograms(plugin).contains(hologram)) {
            event.setCancelled(true);
        }
    }

    // This methods un-cancels the event again sothat touch handlers get invoked.
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractEntityHighest(PlayerInteractEntityEvent event) {
        Hologram hologram = getByEntity(event.getRightClicked());
        if (hologram != null && HologramsAPI.getHolograms(plugin).contains(hologram)) {
            event.setCancelled(false);
        }
    }

    private static Hologram getByEntity(Entity entity) {
        return HolographicDisplays.getNMSManager().getNMSEntityBase(entity).getHologramLine().getParent();
    }

}
