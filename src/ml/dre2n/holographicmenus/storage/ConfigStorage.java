package ml.dre2n.holographicmenus.storage;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.Plugin;

import ml.dre2n.holographicmenus.file.FileUtil;

public class ConfigStorage extends FileUtil {
	
	public static ConfigStorage config;
	
	public ConfigStorage(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
		CONFIG_HEADER = "HolographicMenus Configuration";
	}
	
	public static ConfigStorage getData() {
		return config;
	}
	
	public static void saveData() {
		try {
			getData().save();
		} catch (InvalidConfigurationException exception) {
			exception.printStackTrace();
		}
	}
	
	public HashMap<String, String> menus_main = new HashMap<String, String>();
	public String menus_main_pages = "3";
	public String menus_main_texts_head = "%head%Main Menu - %highlight%%page% / %maxpages%";
	public String menus_main_texts_switch = "%head%%play% %play% %play%";
	public String menus_main_texts_close = "%highlight%%play%%head%Close";
	public String menus_main_texts_page_1_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_1_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_1_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_2_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_2_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_2_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_3_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_3_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_3_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_4_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_4_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_4_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_5_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_5_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_5_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_6_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_6_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_6_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_7_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_7_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_7_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_8_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_8_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_8_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_9_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_9_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_9_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_10_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_10_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_10_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_11_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_11_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_11_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_12_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_12_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_12_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_13_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_13_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_13_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_14_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_14_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_14_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_15_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_15_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_15_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_16_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_16_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_16_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_17_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_17_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_17_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_18_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_18_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_18_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_19_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_19_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_19_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_texts_page_20_button1 = "%highlight%%play%%text%Button 1";
	public String menus_main_texts_page_20_button2 = "%highlight%%play%%text%Button 2";
	public String menus_main_texts_page_20_button3 = "%highlight%%play%%text%Button 3";
	public String menus_main_commands_page_1_button1 = "say Button 1";
	public String menus_main_commands_page_1_button2 = "say Button 2";
	public String menus_main_commands_page_1_button3 = "say Button 3";
	public String menus_main_commands_page_2_button1 = "say Button 1";
	public String menus_main_commands_page_2_button2 = "say Button 2";
	public String menus_main_commands_page_2_button3 = "say Button 3";
	public String menus_main_commands_page_3_button1 = "say Button 1";
	public String menus_main_commands_page_3_button2 = "say Button 2";
	public String menus_main_commands_page_3_button3 = "say Button 3";
	public String menus_main_commands_page_4_button1 = "say Button 1";
	public String menus_main_commands_page_4_button2 = "say Button 2";
	public String menus_main_commands_page_4_button3 = "say Button 3";
	public String menus_main_commands_page_5_button1 = "say Button 1";
	public String menus_main_commands_page_5_button2 = "say Button 2";
	public String menus_main_commands_page_5_button3 = "say Button 3";
	public String menus_main_commands_page_6_button1 = "say Button 1";
	public String menus_main_commands_page_6_button2 = "say Button 2";
	public String menus_main_commands_page_6_button3 = "say Button 3";
	public String menus_main_commands_page_7_button1 = "say Button 1";
	public String menus_main_commands_page_7_button2 = "say Button 2";
	public String menus_main_commands_page_7_button3 = "say Button 3";
	public String menus_main_commands_page_8_button1 = "say Button 1";
	public String menus_main_commands_page_8_button2 = "say Button 2";
	public String menus_main_commands_page_8_button3 = "say Button 3";
	public String menus_main_commands_page_9_button1 = "say Button 1";
	public String menus_main_commands_page_9_button2 = "say Button 2";
	public String menus_main_commands_page_9_button3 = "say Button 3";
	public String menus_main_commands_page_10_button1 = "say Button 1";
	public String menus_main_commands_page_10_button2 = "say Button 2";
	public String menus_main_commands_page_10_button3 = "say Button 3";
	public String menus_main_commands_page_11_button1 = "say Button 1";
	public String menus_main_commands_page_11_button2 = "say Button 2";
	public String menus_main_commands_page_11_button3 = "say Button 3";
	public String menus_main_commands_page_12_button1 = "say Button 1";
	public String menus_main_commands_page_12_button2 = "say Button 2";
	public String menus_main_commands_page_12_button3 = "say Button 3";
	public String menus_main_commands_page_13_button1 = "say Button 1";
	public String menus_main_commands_page_13_button2 = "say Button 2";
	public String menus_main_commands_page_13_button3 = "say Button 3";
	public String menus_main_commands_page_14_button1 = "say Button 1";
	public String menus_main_commands_page_14_button2 = "say Button 2";
	public String menus_main_commands_page_14_button3 = "say Button 3";
	public String menus_main_commands_page_15_button1 = "say Button 1";
	public String menus_main_commands_page_15_button2 = "say Button 2";
	public String menus_main_commands_page_15_button3 = "say Button 3";
	public String menus_main_commands_page_16_button1 = "say Button 1";
	public String menus_main_commands_page_16_button2 = "say Button 2";
	public String menus_main_commands_page_16_button3 = "say Button 3";
	public String menus_main_commands_page_17_button1 = "say Button 1";
	public String menus_main_commands_page_17_button2 = "say Button 2";
	public String menus_main_commands_page_17_button3 = "say Button 3";
	public String menus_main_commands_page_18_button1 = "say Button 1";
	public String menus_main_commands_page_18_button2 = "say Button 2";
	public String menus_main_commands_page_18_button3 = "say Button 3";
	public String menus_main_commands_page_19_button1 = "say Button 1";
	public String menus_main_commands_page_19_button2 = "say Button 2";
	public String menus_main_commands_page_19_button3 = "say Button 3";
	public String menus_main_commands_page_20_button1 = "say Button 1";
	public String menus_main_commands_page_20_button2 = "say Button 2";
	public String menus_main_commands_page_20_button3 = "say Button 3";
	
}
