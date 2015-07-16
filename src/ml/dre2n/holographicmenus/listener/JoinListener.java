package ml.dre2n.holographicmenus.listener;

import java.util.HashMap;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.storage.DataStorage;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinListener implements Listener {
	
	@EventHandler
	void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String uuid = player.getUniqueId().toString();
		if (!(DataStorage.getData().style_head.containsKey(uuid)) || !(DataStorage.getData().style_highlight.containsKey(uuid)) || !(DataStorage.getData().style_text.containsKey(uuid))) {
			DataStorage.initializePlayerData(uuid);
		}
		if (!(HolographicMenus.lastPages.containsKey(player))) {
			HashMap<String, Integer> lastPage = new HashMap<String, Integer>();
			lastPage.put("main", 1);
			HolographicMenus.lastPages.put(player, lastPage);
		}
	}
	
}
