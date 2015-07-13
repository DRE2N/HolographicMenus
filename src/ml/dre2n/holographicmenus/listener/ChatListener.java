package ml.dre2n.holographicmenus.listener;

import java.util.HashMap;

import ml.dre2n.holographicmenus.storage.DataStorage;
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
		String uuid = player.getUniqueId().toString();
		HashMap<String, String> inputTypes = DataStorage.getData().inputType;
		String inputType = inputTypes.get(uuid);
		if (!(inputType.equals("chat"))) {
			if (text.equalsIgnoreCase("-")) {
				inputTypes.put(uuid, "chat");
				return;
			} else if (text.equalsIgnoreCase("new") || text.equalsIgnoreCase("n")) {
				inputText = "";
			} else if (text.equalsIgnoreCase("+")) {
				inputTypes.put(uuid, "chat");
				if (inputType.equals("settings_head")) {
					player.performCommand("hm settings " + player.getName() + " highlight " + inputText);
					inputTypes.put(uuid, "settings_highlight");
					player.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().inputwanted_highlight, uuid));
				} else if (inputType.equals("settings_highlight")) {
					player.performCommand("hm settings " + player.getName() + " highlight " + inputText);
					inputTypes.put(uuid, "settings_text");
					player.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().inputwanted_text, uuid));
				} else if (inputType.equals("settings_text")) {
					player.performCommand("hm settings " + player.getName() + " highlight " + inputText);
					inputTypes.put(uuid, "chat");
					player.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().inputwanted_finished, uuid));
				}
				return;
			} else if (text.equalsIgnoreCase("ok")) {
				inputTypes.put(uuid, "chat");
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
				player.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().inputwanted_help, uuid));
			}
		}
		event.setCancelled(true);
	}
	
}
