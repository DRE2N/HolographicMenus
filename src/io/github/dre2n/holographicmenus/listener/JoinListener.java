package io.github.dre2n.holographicmenus.listener;

import io.github.dre2n.holographicmenus.HolographicMenus;
import io.github.dre2n.holographicmenus.storage.DataStorage;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

	DataStorage data = DataStorage.data;

	HashMap<Player, HashMap<String, Integer>> lastPages = HolographicMenus.lastPages;

	// Continuous check for player joins
	@EventHandler
	void onJoin(PlayerJoinEvent event) {

		// Get the player who joins the server
		Player player = event.getPlayer();
		String uuid = player.getUniqueId().toString();

		// Check if he has NOT a custom style
		if (!data.style_head.containsKey(uuid)
				|| !data.style_highlight.containsKey(uuid)
				|| !data.style_text.containsKey(uuid)) {
			// Trigger the initializer to give him the standard style sothat no
			// NullPointerExceptions occur when he opens a menu
			DataStorage.initializePlayerData(uuid);
		}

		// Check if isn't in the last pages list
		if (!lastPages.containsKey(player)) {
			// Setup a HashMap<String, Integer> we can put into lastPages
			HashMap<String, Integer> lastPage = new HashMap<String, Integer>();
			lastPage.put("main", 1);

			// Give him a a last page to prevent NPEs
			lastPages.put(player, lastPage);
		}

	}

}
