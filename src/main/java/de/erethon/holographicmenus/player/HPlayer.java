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

import de.erethon.commons.chat.BaseComponent;
import de.erethon.commons.chat.ClickEvent;
import de.erethon.commons.chat.ClickEvent.Action;
import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.chat.TextComponent;
import de.erethon.commons.compatibility.CompatibilityHandler;
import de.erethon.commons.player.PlayerWrapper;
import de.erethon.holographicmenus.config.HMessage;
import de.erethon.holographicmenus.hologram.HologramCollection;
import de.erethon.holographicmenus.menu.HButton;
import java.util.UUID;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public class HPlayer implements PlayerWrapper {

    private Player player;
    private HologramCollection openedMenu;
    private HButton clickedButton;
    private String pendingCommand;
    private int pendingCommandVariables;
    private int currentlyReplacedVariable;

    public HPlayer(Player player) {
        this.player = player;
        pendingCommandVariables = 0;
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
     * the menu the player has opened,
     * null if none
     */
    public HologramCollection getOpenedHolograms() {
        return openedMenu;
    }

    /**
     * @param holograms
     * a HologramCollection that holds the opened buttons
     */
    public void setOpenedHolograms(HologramCollection holograms) {
        openedMenu = holograms;
    }

    /**
     * @return
     * if the player has a pending command
     */
    public boolean hasPendingCommand() {
        return pendingCommand != null;
    }

    /**
     * @return
     * the command the player executes later
     */
    public String getPendingCommand() {
        return pendingCommand;
    }

    /**
     * @param button
     * the button of that the command is to be fetched
     */
    public void setPendingCommand(HButton button) {
        clickedButton = button;
        if (button == null) {
            pendingCommand = null;
        } else {
            pendingCommand = button.getCommand();
            pendingCommandVariables = button.getCommandVariables();
            currentlyReplacedVariable = 1;
            sendPendingCommandMessage();
        }
    }

    public void sendPendingCommandMessage() {
        String msg = clickedButton != null ? clickedButton.getVariableMessage(currentlyReplacedVariable) : HMessage.PENDING_ENTER_COMMAND_VARIABLE.getMessage(String.valueOf(currentlyReplacedVariable));
        MessageUtil.sendMessage(player, msg);
        if (CompatibilityHandler.getInstance().isSpigot()) {
            BaseComponent[] comps = TextComponent.fromLegacyText(HMessage.PENDING_CANCEL.getMessage());
            for (BaseComponent comp : comps) {
                comp.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/holographicmenus cancelInput"));
            }
            MessageUtil.sendMessage(player, comps);
        } else {
            MessageUtil.sendMessage(player, HMessage.PENDING_CANCEL_NO_SPIGOT.getMessage());
        }
    }

    /**
     * @return
     * the amount of pending command variables to replace
     */
    public int getPendingCommandVariables() {
        return pendingCommandVariables;
    }

    /**
     * Replaces the current command variable with the input String
     *
     * @param input
     * the chat input
     */
    public boolean replaceCommandVariable(String input) {
        if (input.contains(" ")) {
            return false;
        }
        pendingCommand = pendingCommand.replace("%v" + currentlyReplacedVariable + "%", input);
        currentlyReplacedVariable++;
        pendingCommandVariables--;
        if (pendingCommandVariables > 0) {
            sendPendingCommandMessage();
        }
        return true;
    }

}
