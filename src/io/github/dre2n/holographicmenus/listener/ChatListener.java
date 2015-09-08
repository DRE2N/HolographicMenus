// WORK IN PROGRESS!

package io.github.dre2n.holographicmenus.listener;

import io.github.dre2n.holographicmenus.HolographicMenus;
import io.github.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	String inputText = "";

	@EventHandler
	void onChat(AsyncPlayerChatEvent event) {
		String text = event.getMessage();
		Player player = event.getPlayer();
		String inputType = HolographicMenus.inputTypes.get(player);

		if (!inputType.equals("chat")) {

			if (text.equalsIgnoreCase("-")) {
				HolographicMenus.inputTypes.put(player, "chat");
				return;

			} else if (text.equalsIgnoreCase("new")
					|| text.equalsIgnoreCase("n")) {
				inputText = "";

			} else if (text.equalsIgnoreCase("+")) {
				HolographicMenus.inputTypes.put(player, "chat");

				if (inputType.equals("settings_head")) {
					player.performCommand("hm settings " + player.getName()
							+ " highlight " + inputText);
					HolographicMenus.inputTypes.put(player,
							"settings_highlight");

					VariableUtil.sendMessage("inputwanted.highlight", player);

				} else if (inputType.equals("settings_highlight")) {
					player.performCommand("hm settings " + player.getName()
							+ " highlight " + inputText);
					HolographicMenus.inputTypes.put(player, "settings_text");

					VariableUtil.sendMessage("inputwanted.text", player);
				} else if (inputType.equals("settings_text")) {
					player.performCommand("hm settings " + player.getName()
							+ " highlight " + inputText);
					HolographicMenus.inputTypes.put(player, "chat");

					VariableUtil.sendMessage("inputwanted.finished", player);
				}
				return;

			} else if (text.equalsIgnoreCase("ok")) {
				HolographicMenus.inputTypes.put(player, "chat");

				if (inputType.equals("settings_head")) {
					player.performCommand("hm settings " + player.getName()
							+ " highlight " + inputText);

				} else if (inputType.equals("settings_highlight")) {
					player.performCommand("hm settings " + player.getName()
							+ " highlight " + inputText);

				} else if (inputType.equals("settings_text")) {
					player.performCommand("hm settings " + player.getName()
							+ " highlight " + inputText);
				}
				return;

			} else {
				inputText = inputText + text;

				player.sendMessage(inputText);
				VariableUtil.sendMessage("inputwanted.help", player);
			}

		}
		event.setCancelled(true);
	}

}
