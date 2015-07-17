package ml.dre2n.holographicmenus.task;

import java.util.Collection;
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
	
	Player player = null;
	String type = null;
	
	Plugin plugin = HolographicMenus.plugin;
	
	public InitializeHoloTask(Player player, String type) {
		this.player = player;
		this.type = type;
	}
	
	@Override
	public void run() {
		if (!(HolographicMenus.lastPages.get(player).containsKey(type))) {
			HolographicMenus.lastPages.get(player).put(type, 1);
		}
		deleteOldHolos();
		Location playerLocation = player.getLocation();
		Location eyeLocation = player.getEyeLocation();
		Vector vector = player.getLocation().getDirection().multiply(2.0);
		Location menuLocation = eyeLocation.add(vector);
		World menuWorld = menuLocation.getWorld();
		double menuX = menuLocation.getX();
		double menuY = playerLocation.getY();
		double menuZ = menuLocation.getZ();
		Location menuHead = new Location(menuWorld, menuX, menuY + 3, menuZ);
		Location menuLine1 = new Location(menuWorld, menuX, menuY + 2.6, menuZ);
		Location menuLine2 = new Location(menuWorld, menuX, menuY + 2.2, menuZ);
		Location menuLine3 = new Location(menuWorld, menuX, menuY + 1.8, menuZ);
		Location menuSwitch = new Location(menuWorld, menuX, menuY + 1.2, menuZ);
		Location menuClose = new Location(menuWorld, menuX, menuY + 0.6, menuZ);
		final Hologram hologramHead = HologramsAPI.createHologram(plugin, menuHead);
		final Hologram hologramLine1 = HologramsAPI.createHologram(plugin, menuLine1);
		final Hologram hologramLine2 = HologramsAPI.createHologram(plugin, menuLine2);
		final Hologram hologramLine3 = HologramsAPI.createHologram(plugin, menuLine3);
		final Hologram hologramSwitch = HologramsAPI.createHologram(plugin, menuSwitch);
		final Hologram hologramClose = HologramsAPI.createHologram(plugin, menuClose);
		VisibilityManager visibilityManagerHead = hologramHead.getVisibilityManager();
		VisibilityManager visibilityManagerLine1 = hologramLine1.getVisibilityManager();
		VisibilityManager visibilityManagerLine2 = hologramLine2.getVisibilityManager();
		VisibilityManager visibilityManagerLine3 = hologramLine3.getVisibilityManager();
		VisibilityManager visibilityManagerSwitch = hologramSwitch.getVisibilityManager();
		VisibilityManager visibilityManagerClose = hologramClose.getVisibilityManager();
		visibilityManagerHead.showTo(player);
		visibilityManagerLine1.showTo(player);
		visibilityManagerLine2.showTo(player);
		visibilityManagerLine3.showTo(player);
		visibilityManagerSwitch.showTo(player);
		visibilityManagerClose.showTo(player);
		visibilityManagerHead.setVisibleByDefault(false);
		visibilityManagerLine1.setVisibleByDefault(false);
		visibilityManagerLine2.setVisibleByDefault(false);
		visibilityManagerLine3.setVisibleByDefault(false);
		visibilityManagerSwitch.setVisibleByDefault(false);
		visibilityManagerClose.setVisibleByDefault(false);
		Bukkit.getScheduler().runTask(plugin, new ManagePageTask(player, type, hologramHead, hologramLine1, hologramLine2, hologramLine3, hologramSwitch, hologramClose));
	}
	
	void deleteOldHolos() {
		Collection<Hologram> allHolograms = HologramsAPI.getHolograms(plugin);
	    for (Iterator<Hologram> iterator = allHolograms.iterator(); iterator.hasNext();) {
	        Hologram hologram = (Hologram) iterator.next();
	        if (hologram.getVisibilityManager().isVisibleTo(player)) {
	        	hologram.delete();
	        }
	    }
	}
	
}
