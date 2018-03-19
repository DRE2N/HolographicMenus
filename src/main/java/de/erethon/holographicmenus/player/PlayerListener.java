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
package de.erethon.holographicmenus.player;

import de.erethon.holographicmenus.HolographicMenus;
import de.erethon.holographicmenus.hologram.HologramCollection;
import de.erethon.holographicmenus.menu.HMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * @author Daniel Saukel
 */
public class PlayerListener implements Listener {

    private HolographicMenus plugin;
    private HPlayerCache players;

    public PlayerListener(HolographicMenus plugin) {
        this.plugin = plugin;
        players = plugin.getHPlayerCache();
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        HPlayer hPlayer = players.getByPlayer(player, false);
        if (hPlayer == null) {
            return;
        }
        HologramCollection opened = hPlayer.getOpenedHolograms();
        if (opened == null) {
            return;
        }
        HMenu menu = opened.getMenu();
        if (menu.isFollowingOnMove()) {
            Vector direction = player.getEyeLocation().getDirection().multiply(menu.getDistance());
            opened.moveAll(player.getEyeLocation(), direction);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        HPlayer hPlayer = players.getByPlayer(event.getPlayer(), false);
        if (hPlayer == null || !hPlayer.hasPendingCommand()) {
            return;
        }
        event.setCancelled(true);
        hPlayer.replaceCommandVariable(event.getMessage());
        if (hPlayer.getPendingCommandVariables() == 0) {
            String cmd = hPlayer.getPendingCommand();
            hPlayer.setPendingCommand(null);
            new BukkitRunnable() {
                @Override
                public void run() {
                    hPlayer.getPlayer().performCommand(cmd);
                }
            }.runTask(plugin);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        HPlayer quitting = players.getByPlayer(event.getPlayer(), false);
        if (quitting == null) {
            return;
        }
        if (quitting.hasOpenedMenu()) {
            quitting.getOpenedHolograms().deleteAll();
        }
        players.getPlayers().remove(quitting);
    }

}
