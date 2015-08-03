package ml.dre2n.holographicmenus.task;

import java.util.HashMap;
import java.util.logging.Logger;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.storage.MenuStorage;
import ml.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;

public class ApplyTouchHandlerTask implements Runnable {
	
	Plugin plugin = HolographicMenus.plugin;
	Logger logger = plugin.getLogger();
	
	FileConfiguration menus = MenuStorage.getData();
	
	HashMap<Player, HashMap<String, Integer>> lastPages = HolographicMenus.lastPages;
	
	// Variables
	Player player;
	
	String type;
	
	TextLine text;
	String line;
	
	int page;
	
	// COMMAND
	// Config path
	String commandPath;
	
	// Get raw command from config
	String commandRaw;
	
	// Replace variables
	String commandEdited;
	
	// Apply variables to this class
	public ApplyTouchHandlerTask(Player player, String type, TextLine text, String line) {
		this.player = player;
		
		this.type = type;
		
		this.text = text;
		this.line = line;
		
		page = lastPages.get(player).get(type);
		
		// COMMMAND
		// Config path
		commandPath = type + ".commands.page." + page + ".button" + line;
		
		// Get raw command from config
		commandRaw = menus.getString(commandPath);
		
		// Replace variables
		commandEdited = VariableUtil.commandVariables(commandRaw, player);
	}
	
	// Task
	@Override
	public void run() {
		// Apply a new touch handler to the TextLine
		text.setTouchHandler(new TouchHandler() {
			// If a player touchs it
			@Override
			public void onTouch(Player player) {
				// Force the player to execute the command
				player.performCommand(commandEdited);
				
				// Log
				logger.info(player.getName() + " executed command '" + commandEdited + "' (" + type + ", page" + page + ", button" + line + ").");
			}
		});
		
	}
	
}
