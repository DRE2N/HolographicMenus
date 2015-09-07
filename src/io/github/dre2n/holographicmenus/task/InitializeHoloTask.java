package io.github.dre2n.holographicmenus.task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import io.github.dre2n.holographicmenus.HolographicMenus;
import io.github.dre2n.holographicmenus.storage.MenuStorage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
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
	
	FileConfiguration menus = MenuStorage.getData();
	
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
		// Variables
		HashMap<String, Integer> lastPage = lastPages.get(player);
		
		double menuPositionDistance = menus.getDouble(type + ".positions.distance");
		double menuPositionHead = menus.getDouble(type + ".positions.head");
		double menuPositionLine1 = menus.getDouble(type + ".positions.button1");
		double menuPositionLine2 = menus.getDouble(type + ".positions.button2");
		double menuPositionLine3 = menus.getDouble(type + ".positions.button3");
		double menuPositionSwitch = menus.getDouble(type + ".positions.switch");
		double menuPositionClose = menus.getDouble(type + ".positions.close");
		
		// Prevent NullPointerException: Add a last page value to the opened menu if it doesn't exist
		if (!(lastPage.containsKey(type))) {
			lastPage.put(type, 1);
		}
		
		// Delete the player's old menus
		deleteOldHolos();
		
		// Get vector
		Location playerLocation = player.getLocation();
		Vector vector = playerLocation.getDirection().multiply(menuPositionDistance);
		
		// Get menu location in front of the player's eyes
		Location eyeLocation = player.getEyeLocation();
		Location menuLocation = eyeLocation.add(vector);
		
		// Split menu location into its values sothat we can modify them
		World menuWorld = menuLocation.getWorld();
		double menuX = menuLocation.getX();
		double menuY = playerLocation.getY();
		double menuZ = menuLocation.getZ();
		
		// Set locations for each menu with a different height
		Location menuHead = new Location(menuWorld, menuX, menuY + menuPositionHead, menuZ);
		Location menuLine1 = new Location(menuWorld, menuX, menuY + menuPositionLine1, menuZ);
		Location menuLine2 = new Location(menuWorld, menuX, menuY + menuPositionLine2, menuZ);
		Location menuLine3 = new Location(menuWorld, menuX, menuY + menuPositionLine3, menuZ);
		Location menuSwitch = new Location(menuWorld, menuX, menuY + menuPositionSwitch, menuZ);
		Location menuClose = new Location(menuWorld, menuX, menuY + menuPositionClose, menuZ);
		
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
