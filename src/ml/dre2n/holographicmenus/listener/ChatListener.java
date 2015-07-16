package ml.dre2n.holographicmenus.listener;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.storage.LanguageStorage;
import ml.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
	
	String inputText = "";
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		String text = event.getMessage();
		Player player = event.getPlayer();
		String inputType = HolographicMenus.inputTypes.get(player);
		if (!(inputType.equals("chat"))) {
			if (text.equalsIgnoreCase("-")) {
				HolographicMenus.inputTypes.put(player, "chat");
				return;
			} else if (text.equalsIgnoreCase("new") || text.equalsIgnoreCase("n")) {
				inputText = "";
			} else if (text.equalsIgnoreCase("+")) {
				HolographicMenus.inputTypes.put(player, "chat");
				if (inputType.equals("settings_head")) {
					player.performCommand("hm settings " + player.getName() + " highlight " + inputText);
					HolographicMenus.inputTypes.put(player, "settings_highlight");
					player.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().inputwanted_highlight, player));
				} else if (inputType.equals("settings_highlight")) {
					player.performCommand("hm settings " + player.getName() + " highlight " + inputText);
					HolographicMenus.inputTypes.put(player, "settings_text");
					player.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().inputwanted_text, player));
				} else if (inputType.equals("settings_text")) {
					player.performCommand("hm settings " + player.getName() + " highlight " + inputText);
					HolographicMenus.inputTypes.put(player, "chat");
					player.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().inputwanted_finished, player));
				}
				return;
			} else if (text.equalsIgnoreCase("ok")) {
				HolographicMenus.inputTypes.put(player, "chat");
				if (inputType.equals("settings_head")) {
					player.performCommand("hm settings " + player.getName() + " highlight " + inputText);
				} else if (inputType.equals("settings_highlight")) {
					player.performCommand("hm settings " + player.getName() + " highlight " + inputText);
				} else if (inputType.equals("settings_text")) {
					player.performCommand("hm settings " + player.getName() + " highlight " + inputText);
				}
				return;
			} else {
				inputText = inputText + text;
				player.sendMessage(inputText);
				player.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().inputwanted_help, player));
			}
		}
		event.setCancelled(true);
	}
	
}
