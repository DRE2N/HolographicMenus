package ml.dre2n.holographicmenus.storage;

import java.io.File;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.file.FileUtil;

public class MenuStorage extends FileUtil {
	
	static Plugin plugin = HolographicMenus.plugin;
	
	// Easily get variables
	public static MenuStorage menus;
	
	// Set header and file name
	public MenuStorage(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "menus.yml");
		CONFIG_HEADER = "HolographicMenus Menus";
	}
	
	// To get data by path, not by the variable listed here
	public static FileConfiguration getData() {
		File menuFile = new File(plugin.getDataFolder(), "menus.yml");
		FileConfiguration menus = YamlConfiguration.loadConfiguration(menuFile);
		return menus;
	}
	
	// Easily save file
	public static void saveData() {
		try {
			menus.save();
		} catch (InvalidConfigurationException exception) {
			exception.printStackTrace();
		}
	}
	
	// ~~~ MENUS / MAIN MENU ~~~
	// The amount of pages that are to be used.
	public String main_pages = "3";
	
	// Texts of static buttons
	public String main_texts_head = "%head%Main Menu - %highlight%%page% / %maxpages%";
	public String main_texts_switch = "%head%%play% %play% %play%";
	public String main_texts_close = "%highlight%%play%%head%Close";
	
	// Text of configurable buttons
	public String main_texts_page_1_button1 = "%highlight%%play%%text%Button 1";
	public String main_texts_page_1_button2 = "%highlight%%play%%text%Button 2";
	public String main_texts_page_1_button3 = "%highlight%%play%%text%Button 3";
	
	public String main_texts_page_2_button1 = "%highlight%%play%%text%Button 1";
	public String main_texts_page_2_button2 = "%highlight%%play%%text%Button 2";
	public String main_texts_page_2_button3 = "%highlight%%play%%text%Button 3";
	
	public String main_texts_page_3_button1 = "%highlight%%play%%text%Button 1";
	public String main_texts_page_3_button2 = "%highlight%%play%%text%Button 2";
	public String main_texts_page_3_button3 = "%highlight%%play%%text%Button 3";
	
	// Commands binded to configurable buttons
	public String main_commands_page_1_button1 = "say Button 1";
	public String main_commands_page_1_button2 = "say Button 2";
	public String main_commands_page_1_button3 = "say Button 3";
	
	public String main_commands_page_2_button1 = "say Button 1";
	public String main_commands_page_2_button2 = "say Button 2";
	public String main_commands_page_2_button3 = "say Button 3";
	
	public String main_commands_page_3_button1 = "say Button 1";
	public String main_commands_page_3_button2 = "say Button 2";
	public String main_commands_page_3_button3 = "say Button 3";
	
	// Positions
	public double main_positions_distance = 2;
	
	public double main_positions_head = 3;
	public double main_positions_switch = 1.2;
	public double main_positions_close = 0.6;
	
	public double main_positions_button1 = 2.6;
	public double main_positions_button2 = 2.2;
	public double main_positions_button3 = 1.8;
	
}
