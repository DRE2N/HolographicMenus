package io.github.dre2n.holographicmenus.listener;

import io.github.dre2n.holographicmenus.HolographicMenus;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

public class MoveListener implements Listener {

	Plugin plugin = HolographicMenus.plugin;

	// Continuous check for player moves
	@EventHandler
	void onMove(PlayerMoveEvent event) {
		// Get the player who moves
		Player player = event.getPlayer();

		// This is the location he moved from...
		Location oldPlayerLocation = event.getFrom();
		double oldPlayerX = oldPlayerLocation.getX();
		double oldPlayerY = oldPlayerLocation.getY();
		double oldPlayerZ = oldPlayerLocation.getZ();

		// ...and this is the location he moved to.
		Location newLocation = event.getTo();
		double newPlayerX = newLocation.getX();
		double newPlayerY = newLocation.getY();
		double newPlayerZ = newLocation.getZ();
		World newWorld = newLocation.getWorld();

		// The difference between his new and his old location
		double difX = newPlayerX - oldPlayerX;
		double difY = newPlayerY - oldPlayerY;
		double difZ = newPlayerZ - oldPlayerZ;

		// All holograms created by this plugin
		Collection<Hologram> allHolograms = HologramsAPI.getHolograms(plugin);

		// Check all of them...
		for (Hologram hologram2 : allHolograms) {
			Hologram hologram = hologram2;

			if (hologram.getVisibilityManager().isVisibleTo(player)) {
				// ...and do THIS if a hologram is visible only to the player:
				// First of all, let's get the location values of our menu
				// piece.
				double oldHologramX = hologram.getX();
				double oldHologramY = hologram.getY();
				double oldHologramZ = hologram.getZ();

				// Now, let's create new variables that contain the matching
				// location value of the menu AND the distance the player
				// moved...
				double newHologramX = oldHologramX + difX;
				double newHologramY = oldHologramY + difY;
				double newHologramZ = oldHologramZ + difZ;

				// ...and teleport the menu piece to the new location.
				hologram.teleport(newWorld, newHologramX, newHologramY,
						newHologramZ);
			}

		}

	}

}
