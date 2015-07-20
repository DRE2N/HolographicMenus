package ml.dre2n.holographicmenus.task;

import java.util.HashMap;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.storage.ConfigStorage;
import ml.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;

public class ManagePageTask implements Runnable {
	
	Plugin plugin = HolographicMenus.plugin;
	
	FileConfiguration config = ConfigStorage.getData();
	
	HashMap<Player, HashMap<String, Integer>> lastPages = HolographicMenus.lastPages;
	
	// Variables
	Player player;
	String uuid;
	String type;
	
	int page;
	int pages;
	
	Hologram hologramHead;
	Hologram hologramLine1;
	Hologram hologramLine2;
	Hologram hologramLine3;
	Hologram hologramSwitch;
	Hologram hologramClose;
	
	// TEXT LINES
	// Config path
	String textHeadPath;
	String textLine1Path;
	String textLine2Path;
	String textLine3Path;
	String textSwitchPath;
	String textClosePath;
	
	// Get raw message from config
	String textHeadRaw;
	String textLine1Raw;
	String textLine2Raw;
	String textLine3Raw;
	String textSwitchRaw;
	String textCloseRaw;
	
	// Replace variables
	String textHeadEdited;
	String textLine1Edited;
	String textLine2Edited;
	String textLine3Edited;
	String textSwitchEdited;
	String textCloseEdited;
	
	
	// Apply variables to this class
	public ManagePageTask(Player player, String type, Hologram hologramHead, Hologram hologramLine1, Hologram hologramLine2, Hologram hologramLine3, Hologram hologramSwitch, Hologram hologramClose) {
		this.player = player;
		this.uuid = player.getUniqueId().toString();
		
		this.type = type;
		this.pages = Integer.parseInt(config.getString("menus." + type + ".pages"));
		
		this.hologramHead = hologramHead;
		this.hologramLine1 = hologramLine1;
		this.hologramLine2 = hologramLine2;
		this.hologramLine3 = hologramLine3;
		this.hologramSwitch = hologramSwitch;
		this.hologramClose = hologramClose;
		
		// TEXT LINES that don't depend on the page
		// Config path
		this.textSwitchPath = "menus." + type + ".texts.switch";
		this.textClosePath = "menus." + type + ".texts.close";
		
		// Get raw message from config
		this.textSwitchRaw = config.getString(textSwitchPath);
		this.textCloseRaw = config.getString(textClosePath);
		
		// Replace variables
		this.textSwitchEdited = VariableUtil.replaceVariables(textSwitchRaw, player);
		this.textCloseEdited = VariableUtil.replaceVariables(textCloseRaw, player);
		
		// TEXT LINES that DO depend on the page
		setVariables();
	}
	
	// Task
	@Override
	public void run() {
		// Set text to the head line
		@SuppressWarnings("unused")
		final TextLine textHead = hologramHead.appendTextLine(textHeadEdited);
		
		// Setup the content of the page
		setPage();
		
		// Set text to the switch button
		TextLine textSwitch = hologramSwitch.appendTextLine(textSwitchEdited);
		
		// Set text to the close button
		TextLine textClose = hologramClose.appendTextLine(textCloseEdited);
		
		// Apply a touch handler to the switch button
		textSwitch.setTouchHandler(new TouchHandler() {
			// If a player touchs it
			@Override
			public void onTouch(Player player) {
				// Remove all content from the holograms, but DO NOT delete them
				hologramHead.removeLine(0);
				hologramLine1.removeLine(0);
				hologramLine2.removeLine(0);
				hologramLine3.removeLine(0);
				
				// Check if the current page is the highest page of the menu
				if (page >= pages) {
					// Set page number to zero
					page = 0;
				}
				
				// Increase page number by one
				lastPages.get(player).put(type, page + 1);
				
				// Update variables
				setVariables();
				
				// Setup the hologram head once again with the updated page number
				@SuppressWarnings("unused")
				final TextLine textHead = hologramHead.appendTextLine(textHeadEdited);
				
				// Setup the new page
				setPage();
			}
		});
		
		// Apply a touch handler to the close button
		textClose.setTouchHandler(new TouchHandler() {
			// If a player touchs it
			@Override
			public void onTouch(Player player) {
				// Delete all of the holograms of our menu
				hologramHead.delete();
				hologramLine1.delete();
				hologramLine2.delete();
				hologramLine3.delete();
				hologramSwitch.delete();
				hologramClose.delete();
			}
		});
	}
	
	void setVariables() {
		// Current page
		this.page = lastPages.get(player).get(type);
		
		// TEXT LINES
		// Config path
		this.textHeadPath = "menus." + type + ".texts.head";
		this.textLine1Path = "menus." + type + ".texts.page." + page + ".button1";
		this.textLine2Path = "menus." + type + ".texts.page." + page + ".button2";
		this.textLine3Path = "menus." + type + ".texts.page." + page + ".button3";
		
		// Get raw message from config
		this.textHeadRaw = config.getString(textHeadPath);
		this.textLine1Raw = config.getString(textLine1Path);
		this.textLine2Raw = config.getString(textLine2Path);
		this.textLine3Raw = config.getString(textLine3Path);
		
		// Replace variables
		this.textHeadEdited = VariableUtil.pageVariable(textHeadRaw, player, page);
		this.textLine1Edited = VariableUtil.replaceVariables(textLine1Raw, player);
		this.textLine2Edited = VariableUtil.replaceVariables(textLine2Raw, player);
		this.textLine3Edited = VariableUtil.replaceVariables(textLine3Raw, player);
	}
	
	// Setup the content of the page
	private void setPage() {
		// Set text to the lines
		TextLine textLine1 = hologramLine1.appendTextLine(textLine1Edited);
		TextLine textLine2 = hologramLine2.appendTextLine(textLine2Edited);
		TextLine textLine3 = hologramLine3.appendTextLine(textLine3Edited);
		
		// Apply touch handlers to the lines
		Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerTask(player, type, textLine1, "1"));
		Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerTask(player, type, textLine2, "2"));
		Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerTask(player, type, textLine3, "3"));
	}
	
}
