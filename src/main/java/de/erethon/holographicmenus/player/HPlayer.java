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

import de.erethon.commons.player.PlayerWrapper;
import de.erethon.holographicmenus.menu.HMenu;
import java.util.UUID;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public class HPlayer implements PlayerWrapper {

    private Player player;
    private HMenu openedMenu;
    private int openedPage;

    public HPlayer(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    /**
     * @return
     * if the player has opened a menu
     */
    public boolean hasOpenedMenu() {
        return openedMenu != null;
    }

    /**
     * @return
     * the menu the player has opened
     */
    public HMenu getOpenedMenu() {
        return openedMenu;
    }

    /**
     * @return
     * the menu the player has opened
     */
    public void setOpenedMenu(HMenu menu, int page) {
        openedMenu = menu;
        openedPage = page;
    }

    /**
     * @return
     * the opened menu page
     */
    public int getOpenedPage() {
        return openedPage;
    }

    /**
     * @return
     * the menu page the player has opened
     */
    public void setOpenedPage(int page) {
        openedPage = page;
    }

}
