package ml.dre2n.holographicmenus.storage;

import java.io.File;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.file.FileUtil;

public class ConfigStorage extends FileUtil {
	
	// Easily get variables
	public static ConfigStorage config;
	
	// Set header and file name
	public ConfigStorage(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
		CONFIG_HEADER = "HolographicMenus Configuration";
	}
	
	// To get data by path, not by the variable listed here
	public static FileConfiguration getData() {
		File configFile = new File(HolographicMenus.plugin.getDataFolder(), "config.yml");
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
	
	// ~~~ GLOBAL CONFIGURATION ~~~
	// The languaged used if no personal language is set.
	public String defaultLang = "english";
	
	// Whether a menu shall follow the player or not.
	public boolean followOnMove = false;
	
	// ~~~ MENUS / MAIN MENU ~~~
	// The amount of pages that are to be used.
	public String menus_main_pages = "3";
	
	// Texts of static buttons
	public String menus_main_texts_head = "%head%Main Menu - %highlight%%page% / %maxpages%";
	public String menus_main_texts_switch = "%head%%play% %play% %play%";
	public String menus_main_texts_close = "%highlight%%play%%head%Close";
	
	// Text of configurable buttons
	public String menus_main_texts_page_1_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_1_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_1_button3 = "%highlight%%play%%text%Button 3";
	
	public String menus_main_texts_page_2_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_2_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_2_button3 = "%highlight%%play%%text%Button 3";
	
	public String menus_main_texts_page_3_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_3_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_3_button3 = "%highlight%%play%%text%Button 3";
	
	// Commands binded to configurable buttons
	public String menus_main_commands_page_1_button1 = "say Button 1";
	public String menus_main_commands_page_1_button2 = "say Button 2";
	public String menus_main_commands_page_1_button3 = "say Button 3";
	
	public String menus_main_commands_page_2_button1 = "say Button 1";
	public String menus_main_commands_page_2_button2 = "say Button 2";
	public String menus_main_commands_page_2_button3 = "say Button 3";
	
	public String menus_main_commands_page_3_button1 = "say Button 1";
	public String menus_main_commands_page_3_button2 = "say Button 2";
	public String menus_main_commands_page_3_button3 = "say Button 3";
	
}
