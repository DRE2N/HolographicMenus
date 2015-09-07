package io.github.dre2n.holographicmenus.storage;

import java.io.File;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import io.github.dre2n.holographicmenus.HolographicMenus;
import io.github.dre2n.holographicmenus.file.FileUtil;

public class ConfigStorage extends FileUtil {
	
	static Plugin plugin = HolographicMenus.plugin;
	
	// Easily get variables
	public static ConfigStorage config;
	
	// Set header and file name
	public ConfigStorage(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
		CONFIG_HEADER = "HolographicMenus Configuration";
	}
	
	// To get data by path, not by the variable listed here
	public static FileConfiguration getData() {
		File configFile = new File(plugin.getDataFolder(), "config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
		return config;
	}
	
	// Easily save file
	public static void saveData() {
		try {
			config.save();
		} catch (InvalidConfigurationException exception) {
			exception.printStackTrace();
		}
	}
	
	// The language used if no personal language is set.
	public String defaultLang = "english";
	
	// Whether a menu shall follow the player or not.
	public boolean followOnMove = false;
	
}
