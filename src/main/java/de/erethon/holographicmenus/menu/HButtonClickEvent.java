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
package de.erethon.holographicmenus.menu;

import de.erethon.holographicmenus.hologram.Hologram;
import de.erethon.holographicmenus.player.HPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * This event fires when a button is touched.
 *
 * @author Daniel Saukel
 */
public class HButtonClickEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    private Hologram hologram;
    private HPlayer hPlayer;

    public HButtonClickEvent(Hologram hologram, HPlayer hPlayer) {
        this.hologram = hologram;
        this.hPlayer = hPlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * @return
     * the menu of the touched button;
     * null if none exists
     */
    public HMenu getMenu() {
        if (hologram.hasAssociatedHolograms()) {
            return hologram.getAssociatedHolograms().getMenu();
        } else {
            return null;
        }
    }

    /**
     * @return
     * the currently opened page of the touched menu
     * null if no menu exists
     */
    public HMenuPage getMenuPage() {
        if (getMenu() != null) {
            return getMenu().getMenuPages().get(getMenuPageCount() - 1);
        } else {
            return null;
        }
    }

    /**
     * @return
     * the number of opened page of the touched menu
     * -1 if no menu exists
     */
    public int getMenuPageCount() {
        if (hologram.hasAssociatedHolograms()) {
            return hologram.getAssociatedHolograms().getPage();
        } else {
            return -1;
        }
    }

    /**
     * @return
     * the button represented by the touched hologram
     */
    public HButton getButton() {
        return hologram.getButton();
    }

    /**
     * @return
     * the hologram that was touched
     */
    public Hologram getHologram() {
        return hologram;
    }

    /**
     * @return
     * the HolographicMenus player who clicked the button
     */
    public HPlayer getHPlayer() {
        return hPlayer;
    }

    /**
     * @return
     * the Bukkit Player who clicked the button
     */
    public Player getPlayer() {
        return hPlayer.getPlayer();
    }

}
