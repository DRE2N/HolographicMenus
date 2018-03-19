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
import org.bukkit.Location;

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
    private HologramCollection associated;
    private Location location;
    private Object hologram;

    public Hologram(HolographicMenus plugin, Location location, Object hologram) {
        this.plugin = plugin;
        provider = plugin.getHologramProvider();
        this.location = location;
        this.hologram = hologram;
    }

    /* Getters and setters */
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
     * a HologramCollection containing the associated holograms.
     * These may be other holograms of the same menu.
     */
    public HologramCollection getAssociatedHolograms() {
        return associated;
    }

    /**
     * @param associated
     * a HologramCollection containing the associated holograms.
     * These may be other holograms of the same menu.
     */
    public void setAssociatedHolograms(HologramCollection associated) {
        this.associated = associated;
    }

    /**
     * @return
     * a copy of the location of the hologram
     */
    public Location getLocation() {
        return location.clone();
    }

    /**
     * @return
     * the raw hologram created by the HologramWrapper
     */
    public Object getRawHologram() {
        return hologram;
    }

    /* Actions */
    /**
     * Moves the hologram to the location
     *
     * @param location
     * the target location
     */
    public void move(Location location) {
        this.location = location;
        provider.moveHologram(this, location);
    }

    /**
     * Deletes this hologram
     */
    public void delete() {
        if (associated != null) {
            associated.remove(this);
        }
        provider.deleteHologram(this);
    }

    /**
     * @param player
     * the HPlayer who clicked the button
     */
    public void click(HPlayer player) {
        if (button == null || associated == null) {
            return;
        }

        if (button.getSound() != null) {
            player.getPlayer().playSound(player.getPlayer().getLocation(), button.getSound(), 1, 1);
        }

        switch (button.getType()) {
            case BUTTON:
                if (button.getCommand() != null) {
                    if (button.hasCommandVariables()) {
                        player.setPendingCommand(button);
                    } else {
                        player.getPlayer().performCommand(button.getCommand());
                    }
                }
                break;

            case FIRST_PAGE:
                if (player.hasOpenedMenu()) {
                    associated.getMenu().open(plugin, player, 1);
                }
                break;

            case PREVIOUS_PAGE:
                if (player.hasOpenedMenu()) {
                    int previous = associated.getPage() - 1;
                    int last = associated.getMenu().getMenuPages().size();
                    associated.getMenu().open(plugin, player, previous < 1 ? last : previous);
                }
                break;

            case NEXT_PAGE:
                if (player.hasOpenedMenu()) {
                    int next = associated.getPage() + 1;
                    int last = associated.getMenu().getMenuPages().size();
                    associated.getMenu().open(plugin, player, next > last ? 1 : next);
                }
                break;

            case LAST_PAGE:
                if (player.hasOpenedMenu()) {
                    associated.getMenu().open(plugin, player, associated.getMenu().getMenuPages().size());
                }
                break;
        }

        if (button.isClosingMenu()) {
            if (hasAssociatedHolograms()) {
                associated.deleteAll();
            } else {
                delete();
            }
        }
    }

}
