package ml.dre2n.holographicmenus.listener;

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
		if (!(DataStorage.getData().lastPage.containsKey(uuid)) || !(DataStorage.getData().inputType.containsKey(uuid))
				|| !(DataStorage.getData().style_head.containsKey(uuid)) || !(DataStorage.getData().style_highlight.containsKey(uuid))
				|| !(DataStorage.getData().style_text.containsKey(uuid))) {
			DataStorage.initializePlayerData(uuid);
		}
	}
	
}
