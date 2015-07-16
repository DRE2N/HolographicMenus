package ml.dre2n.holographicmenus.storage;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.Plugin;

import ml.dre2n.holographicmenus.file.FileUtil;

public class DataStorage extends FileUtil {
	
	public static DataStorage data;
	
	public DataStorage(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "data.yml");
		CONFIG_HEADER = "HolographicMenus Data";
	}
	
	public static DataStorage getData() {
		return data;
	}
	
	public static void saveData() {
		try {
			getData().save();
		} catch (InvalidConfigurationException exception) {
			exception.printStackTrace();
		}
	}
	
	public static void initializePlayerData(String uuid) {
		getData().style_head.put(uuid, "\u00a76");
		getData().style_highlight.put(uuid, "\u00a74");
		getData().style_text.put(uuid, "\u00a73");
		saveData();
	}
	
	public HashMap<String, String> style_head = new HashMap<String, String>();
	public HashMap<String, String> style_highlight = new HashMap<String, String>();
	public HashMap<String, String> style_text = new HashMap<String, String>();
	
}
