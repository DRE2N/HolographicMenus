package io.github.dre2n.holographicmenus;

import java.util.HashMap;

import io.github.dre2n.holographicmenus.cmd.HolographicMenusCMD;
import io.github.dre2n.holographicmenus.cmd.LinkedCMD;
import io.github.dre2n.holographicmenus.cmd.MenuCMD;
import io.github.dre2n.holographicmenus.listener.ChatListener;
import io.github.dre2n.holographicmenus.listener.JoinListener;
import io.github.dre2n.holographicmenus.listener.MoveListener;
import io.github.dre2n.holographicmenus.storage.CommandStorage;
import io.github.dre2n.holographicmenus.storage.ConfigStorage;
import io.github.dre2n.holographicmenus.storage.DataStorage;
import io.github.dre2n.holographicmenus.storage.LanguageStorage;
import io.github.dre2n.holographicmenus.storage.MenuStorage;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class HolographicMenus extends JavaPlugin {
	
	public static Plugin plugin;
	
	// Store data that doesn't need to be stored permanently
	public static HashMap<Player, HashMap<String, Integer>> lastPages;
	public static HashMap<Player, String> inputTypes;
	public static HashMap<Player, String> menuTypes;
	
	// When the server starts
	@Override
	public void onEnable() {
		// Initialize variables
		plugin = this;
		
		lastPages = new HashMap<Player, HashMap<String, Integer>>();
		inputTypes = new HashMap<Player, String>();
		menuTypes = new HashMap<Player, String>();
		
		// Initialize files
		try {
			CommandStorage.commands = new CommandStorage(this);
			ConfigStorage.config = new ConfigStorage(this);
			DataStorage.data = new DataStorage(this);
			LanguageStorage.lang = new LanguageStorage(this);
			MenuStorage.menus = new MenuStorage(this);
			
			CommandStorage.commands.init();
			ConfigStorage.config.init();
			DataStorage.data.init();
			LanguageStorage.lang.init();
			MenuStorage.menus.init();
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		
		// Register commands
		getCommand("holographicmenus").setExecutor(new HolographicMenusCMD());
		getCommand("linked").setExecutor(new LinkedCMD());
		getCommand("menu").setExecutor(new MenuCMD());
		
		// Register listeners
		getServer().getPluginManager().registerEvents(new ChatListener(), this);
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		
		// Register MoveListener if enabled
		if (ConfigStorage.config.followOnMove) {
			getServer().getPluginManager().registerEvents(new MoveListener(), this);
			getLogger().info("Warning: You enabled that his menu follows the player whenever he moves. This will probably cause lags.");
		}
		
		// I'm alive!
		getLogger().info("HolographicMenus " + getDescription().getVersion() + " for HolographicDisplays 2.x and Bukkit 1.7.9+ loaded succesfully!");
	}
	
	// When the server shuts down
	@Override
	public void onDisable() {
		plugin = null;
	}
	
}
