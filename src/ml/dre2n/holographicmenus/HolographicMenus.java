package ml.dre2n.holographicmenus;

import java.util.HashMap;
import java.util.logging.Logger;

import ml.dre2n.holographicmenus.cmd.HolographicMenusCMD;
import ml.dre2n.holographicmenus.cmd.MenuCMD;
import ml.dre2n.holographicmenus.listener.JoinListener;
import ml.dre2n.holographicmenus.storage.ConfigStorage;
import ml.dre2n.holographicmenus.storage.DataStorage;
import ml.dre2n.holographicmenus.storage.LanguageStorage;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class HolographicMenus extends JavaPlugin {
	public final Logger logger;
	
	public HolographicMenus() {
		super();
	    this.logger = Logger.getLogger("Minecraft");
	}
	
	public static Plugin plugin;
	
	public static HashMap<Player, HashMap<String, Integer>> lastPages;
	public static HashMap<Player, String> inputTypes;
	
	public void onEnable() {
		lastPages = new HashMap<Player, HashMap<String, Integer>>();
		inputTypes = new HashMap<Player, String>();
		try {
			plugin = this;
			getCommand("holographicmenus").setExecutor(new HolographicMenusCMD());
			getCommand("menu").setExecutor(new MenuCMD());
			getServer().getPluginManager().registerEvents(new JoinListener(), this);
			getLogger().info("HolographicMenus " + getDescription().getVersion() + " for HolographicDisplays 2.x and Bukkit 1.7.9+ loaded succesfully!");
			ConfigStorage.config = new ConfigStorage(this);
			DataStorage.data = new DataStorage(this);
			LanguageStorage.lang = new LanguageStorage(this);
			ConfigStorage.config.init();
			DataStorage.data.init();
			LanguageStorage.lang.init();
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public void onDisable() {
		plugin = null;
	}
	
}
