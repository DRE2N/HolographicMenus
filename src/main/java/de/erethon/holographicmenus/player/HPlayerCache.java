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

import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Daniel Saukel
 */
public class HPlayerCache implements Listener {

    private Collection<HPlayer> players = new ArrayList<>();

    /**
     * @return
     * A Collection of all players that are online as HPlayers
     */
    public Collection<HPlayer> getPlayers() {
        return players;
    }

    /**
     * Creates new HPlayer if no instance exists
     *
     * @param player
     * a Bukkit Player object
     * @return
     * the matching HPlayer wrapper instance
     */
    public HPlayer getByPlayer(Player player) {
        for (HPlayer hPlayer : players) {
            if (hPlayer.getUniqueId().equals(player.getUniqueId())) {
                return hPlayer;
            }
        }
        HPlayer hPlayer = new HPlayer(player);
        players.add(hPlayer);
        return hPlayer;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        HPlayer quitting = null;
        for (HPlayer player : players) {
            if (player.getUniqueId().equals(event.getPlayer().getUniqueId())) {
                quitting = player;
            }
        }
        if (quitting != null) {
            players.remove(quitting);
        }
    }

}
