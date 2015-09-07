package io.github.dre2n.holographicmenus.storage;

import java.io.File;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import io.github.dre2n.holographicmenus.HolographicMenus;
import io.github.dre2n.holographicmenus.file.FileUtil;

public class CommandStorage extends FileUtil {
	
	static Plugin plugin = HolographicMenus.plugin;
	
	// Easily get variables
	public static CommandStorage commands;
	
	// Set header and file name
	public CommandStorage(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "commands.yml");
		CONFIG_HEADER = "HolographicMenus Commands\n\n\nexample:\n  - hm v\n  - version";
	}
	
	// To get data by path, not by the variable listed here
	public static FileConfiguration getData() {
		File commandFile = new File(plugin.getDataFolder(), "commands.yml");
		FileConfiguration commands = YamlConfiguration.loadConfiguration(commandFile);
		return commands;
	}
	
	// Easily save file
	public static void saveData() {
		try {
			commands.save();
		} catch (InvalidConfigurationException exception) {
			exception.printStackTrace();
		}
	}
	
}
