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
import de.erethon.holographicmenus.menu.HButton;
import de.erethon.holographicmenus.player.HPlayer;
import java.util.Collection;

/**
 * This class wraps hologram objects created by a hologram provider plugin.
 * Hologram represents one button in its spawned form as a hologram.
 * It does NOT represent a button as fetched from config.
 *
 * @author Daniel Saukel
 */
public class Hologram {

    private HolographicMenus plugin;
    private HologramWrapper provider;

    private HButton button;
    private Collection<Hologram> associated;
    private Object hologram;

    public Hologram(HolographicMenus plugin, Object hologram) {
        this.plugin = plugin;
        provider = plugin.getHologramProvider();
        this.hologram = hologram;
    }

    /**
     * @return
     * the button that formed this hologram
     */
    public HButton getButton() {
        return button;
    }

    /**
     * @param button
     * the button that formed this hologram
     */
    public void setButton(HButton button) {
        this.button = button;
    }

    /**
     * @return
     * if the hologram has associated holograms
     */
    public boolean hasAssociatedHolograms() {
        return associated != null;
    }

    /**
     * @return
     * a Collection of Holograms containing the associated holograms.
     * These may be other holograms of the same menu.
     */
    public Collection<Hologram> getAssociatedHolograms() {
        return associated;
    }

    /**
     * @param associated
     * a Collection of Holograms containing the associated holograms.
     * These may be other holograms of the same menu.
     */
    public void setAssociatedHolograms(Collection<Hologram> associated) {
        this.associated = associated;
    }

    /**
     * @return
     * the raw hologram created by the HologramWrapper
     */
    public Object getRawHologram() {
        return hologram;
    }

    /**
     * Deletes this hologram
     */
    public void delete() {
        provider.deleteHologram(this);
    }

    /**
     * @param player
     * the HPlayer who clicked the button
     */
    public void click(HPlayer player) {
        if (button == null) {
            return;
        }

        switch (button.getType()) {
            case BUTTON:
                if (button.getCommand() != null) {
                    player.getPlayer().performCommand(button.getCommand());
                }
                break;

            case FIRST_PAGE:
                if (player.hasOpenedMenu()) {
                    player.getOpenedMenu().open(plugin, player, 1);
                }
                break;

            case PREVIOUS_PAGE:
                if (player.hasOpenedMenu()) {
                    int previous = player.getOpenedPage() - 1;
                    int last = player.getOpenedMenu().getMenuPages().size();
                    player.getOpenedMenu().open(plugin, player, previous < 1 ? last : previous);
                }
                break;

            case NEXT_PAGE:
                if (player.hasOpenedMenu()) {
                    int next = player.getOpenedPage() + 1;
                    int last = player.getOpenedMenu().getMenuPages().size();
                    player.getOpenedMenu().open(plugin, player, next > last ? 1 : next);
                }
                break;

            case LAST_PAGE:
                if (player.hasOpenedMenu()) {
                    player.getOpenedMenu().open(plugin, player, player.getOpenedMenu().getMenuPages().size());
                }
                break;
        }

        if (button.getType() != HButton.Type.BUTTON && button.getType() != HButton.Type.TITLE) {
            if (hasAssociatedHolograms()) {
                associated.forEach(h -> h.delete());
            } else {
                delete();
            }
        }
    }

}
