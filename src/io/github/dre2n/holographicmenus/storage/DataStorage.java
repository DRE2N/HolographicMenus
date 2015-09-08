package io.github.dre2n.holographicmenus.storage;

import io.github.dre2n.holographicmenus.HolographicMenus;
import io.github.dre2n.holographicmenus.file.FileUtil;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class DataStorage extends FileUtil {

	static Plugin plugin = HolographicMenus.plugin;

	// Easily get variables
	public static DataStorage data;

	// Set header and file name
	public DataStorage(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "data.yml");
		CONFIG_HEADER = "HolographicMenus Data";
	}

	// To get data by path, not by the variable listed here
	public static FileConfiguration getData() {
		File dataFile = new File(plugin.getDataFolder(), "data.yml");
		FileConfiguration data = YamlConfiguration.loadConfiguration(dataFile);
		return data;
	}

	// Easily save file
	public static void saveData() {
		try {
			data.save();
		} catch (InvalidConfigurationException exception) {
			exception.printStackTrace();
		}
	}

	// Set player data to avoid NullPointerException if he does not do that
	// himself
	public static void initializePlayerData(String uuid) {
		data.style_head.put(uuid, "\u00a76");
		data.style_highlight.put(uuid, "\u00a74");
		data.style_text.put(uuid, "\u00a73");
		saveData();
	}

	// Stores: "player uuid: language name"
	public HashMap<String, String> language = new HashMap<String, String>();

	// Store "player uuid: colour / formatting codes"
	public HashMap<String, String> style_head = new HashMap<String, String>();
	public HashMap<String, String> style_highlight = new HashMap<String, String>();
	public HashMap<String, String> style_text = new HashMap<String, String>();

}
