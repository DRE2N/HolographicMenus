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
package de.erethon.holographicmenus.player;

import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public class HPlayerCache {

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
        return getByPlayer(player, true);
    }

    /**
     * @param player
     * a Bukkit Player object
     * @param create
     * if a new instance shall be created if none is cached
     * @return
     * the matching HPlayer wrapper instance
     */
    public HPlayer getByPlayer(Player player, boolean create) {
        for (HPlayer hPlayer : players) {
            if (hPlayer.getUniqueId().equals(player.getUniqueId())) {
                return hPlayer;
            }
        }
        if (!create) {
            return null;
        }
        HPlayer hPlayer = new HPlayer(player);
        players.add(hPlayer);
        return hPlayer;
    }

}
