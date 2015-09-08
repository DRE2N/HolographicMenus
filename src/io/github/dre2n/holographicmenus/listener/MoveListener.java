package io.github.dre2n.holographicmenus.listener;

import io.github.dre2n.holographicmenus.HolographicMenus;
import io.github.dre2n.holographicmenus.task.InitializeHoloTask;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class MoveListener implements Listener {

	Plugin plugin = HolographicMenus.plugin;

	HashMap<Player, String> menuTypes = HolographicMenus.menuTypes;

	// Continuous check for player moves
	@EventHandler
	void onMove(PlayerMoveEvent event) {
		// Get the player who moves
		Player player = event.getPlayer();

		// Reopen menu
		Bukkit.getScheduler().runTask(plugin,
				new InitializeHoloTask(player, menuTypes.get(player)));
	}

}
