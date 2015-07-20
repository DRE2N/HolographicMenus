package ml.dre2n.holographicmenus.task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import ml.dre2n.holographicmenus.HolographicMenus;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;

public class InitializeHoloTask implements Runnable {

	Plugin plugin = HolographicMenus.plugin;
	
	HashMap<Player, HashMap<String, Integer>> lastPages = HolographicMenus.lastPages;
	HashMap<Player, String> menuTypes = HolographicMenus.menuTypes;
	
	// Variables
	Player player;
	String type;
	
	// Apply variables to this class
	public InitializeHoloTask(Player player, String type) {
		this.player = player;
		
		this.type = type;
		menuTypes.put(player, type);
	}
	
	// Task
	@Override
	public void run() {
		
		// Prevent NullPointerException: Add a last page value to the opened menu if it doesn't exist
		if (!(lastPages.get(player).containsKey(type))) {
			lastPages.get(player).put(type, 1);
		}
		
		// Delete the player's old menus
		deleteOldHolos();
		
		// Get vector
		Location playerLocation = player.getLocation();
		Vector vector = playerLocation.getDirection().multiply(2.0);
		
		// Get menu location in front of the player's eyes
		Location eyeLocation = player.getEyeLocation();
		Location menuLocation = eyeLocation.add(vector);
		
		// Split menu location into its values sothat we can modify them
		World menuWorld = menuLocation.getWorld();
		double menuX = menuLocation.getX();
		double menuY = playerLocation.getY();
		double menuZ = menuLocation.getZ();
		
		// Set locations for each menu with a different height
		Location menuHead = new Location(menuWorld, menuX, menuY + 3, menuZ);
		Location menuLine1 = new Location(menuWorld, menuX, menuY + 2.6, menuZ);
		Location menuLine2 = new Location(menuWorld, menuX, menuY + 2.2, menuZ);
		Location menuLine3 = new Location(menuWorld, menuX, menuY + 1.8, menuZ);
		Location menuSwitch = new Location(menuWorld, menuX, menuY + 1.2, menuZ);
		Location menuClose = new Location(menuWorld, menuX, menuY + 0.6, menuZ);
		
		// Finally, create the menus
		final Hologram hologramHead = HologramsAPI.createHologram(plugin, menuHead);
		final Hologram hologramLine1 = HologramsAPI.createHologram(plugin, menuLine1);
		final Hologram hologramLine2 = HologramsAPI.createHologram(plugin, menuLine2);
		final Hologram hologramLine3 = HologramsAPI.createHologram(plugin, menuLine3);
		final Hologram hologramSwitch = HologramsAPI.createHologram(plugin, menuSwitch);
		final Hologram hologramClose = HologramsAPI.createHologram(plugin, menuClose);
		
		// Get visibility managers of each menu
		VisibilityManager visibilityManagerHead = hologramHead.getVisibilityManager();
		VisibilityManager visibilityManagerLine1 = hologramLine1.getVisibilityManager();
		VisibilityManager visibilityManagerLine2 = hologramLine2.getVisibilityManager();
		VisibilityManager visibilityManagerLine3 = hologramLine3.getVisibilityManager();
		VisibilityManager visibilityManagerSwitch = hologramSwitch.getVisibilityManager();
		VisibilityManager visibilityManagerClose = hologramClose.getVisibilityManager();
		
		// Show menu to the player who opened it
		visibilityManagerHead.showTo(player);
		visibilityManagerLine1.showTo(player);
		visibilityManagerLine2.showTo(player);
		visibilityManagerLine3.showTo(player);
		visibilityManagerSwitch.showTo(player);
		visibilityManagerClose.showTo(player);
		
		// Hide menu to everyone else
		visibilityManagerHead.setVisibleByDefault(false);
		visibilityManagerLine1.setVisibleByDefault(false);
		visibilityManagerLine2.setVisibleByDefault(false);
		visibilityManagerLine3.setVisibleByDefault(false);
		visibilityManagerSwitch.setVisibleByDefault(false);
		visibilityManagerClose.setVisibleByDefault(false);
		
		// Set pages
		Bukkit.getScheduler().runTask(plugin, new ManagePageTask(player, type, hologramHead, hologramLine1, hologramLine2, hologramLine3, hologramSwitch, hologramClose));
		
	}
	
	// Delete the player's old menu
	void deleteOldHolos() {
		// All holograms created by this plugin
		Collection<Hologram> allHolograms = HologramsAPI.getHolograms(plugin);
		
		// Check all of them...
	    for (Iterator<Hologram> iterator = allHolograms.iterator(); iterator.hasNext();) {
	        Hologram hologram = (Hologram) iterator.next();
	        
	        if (hologram.getVisibilityManager().isVisibleTo(player)) {
	        	// ...and delete the hologram if it is visible only to the player.
	        	hologram.delete();
	        }
	        
	    }
	    
	}
	
}
