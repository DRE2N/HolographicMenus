package ml.dre2n.holographicmenus.cmd;

import java.util.HashMap;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.storage.ConfigStorage;
import ml.dre2n.holographicmenus.storage.DataStorage;
import ml.dre2n.holographicmenus.storage.LanguageStorage;
import ml.dre2n.holographicmenus.util.OfflinePlayerUtil;
import ml.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class HolographicMenusCMD implements CommandExecutor {
	
	Plugin plugin = HolographicMenus.plugin;
	String version = plugin.getDescription().getVersion();
	
	FileConfiguration data = DataStorage.getData();
	
	HashMap<Player, String> inputTypes = HolographicMenus.inputTypes;
	
	// This will be fired if our command is executed.
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		// Let's get a player entity from the command sender.
		Player player = (Player) sender;
		String uuid = player.getUniqueId().toString();
		
		if (commandlabel.equalsIgnoreCase("holographicmenus") || commandlabel.equalsIgnoreCase("hm")) {
			
			// Check if no arguments are entered
			if (args.length == 0) {
				// This is a custom method to send a message to the player, check ml.dre2n.holographicmenus.util.VariableUtil.
				VariableUtil.sendMessage("hm.main.welcome", player);
				
				// Some information are only needed if the sender has the permission to use them.
				if (sender.hasPermission("holographicmenus.settings")) {
					VariableUtil.sendMessage("hm.main.settings", player);
				}
				
				VariableUtil.sendMessage("hm.main.infos", player);
				
				if (sender.hasPermission("holographicmenus.reload")) {
					VariableUtil.sendMessage("hm.main.reload", player);
				}
			// Check for /hm settings command
			} else if (args[0].equalsIgnoreCase("settings") || args[0].equalsIgnoreCase("s")) {
				
				// Check if the sender has permission to use the command
				if (sender.hasPermission("holographicmenus.settings")) {
					
					// Nothing but /hm s?
					if (args.length == 1) {
						// So, the player wants to use the chat feature to set up his style step by step.
						// First of all, we give him the instructions how to do so.
						VariableUtil.sendMessage("inputwanted.head", player);
						VariableUtil.sendMessage("inputwanted.help", player);
						
						// And of course, we change his input type from chat to settings.
						inputTypes.put(player, "settings_highlight");
						
					// The right amount of arguments for direct setup?
					} else if (args.length == 4) {
						
						// What, he tries to modify another player's settings and doesn't have the right to do so?
						if (!(args[1].equals(player.getName()) && sender.hasPermission("holographicmenus.settings.others"))) {
							// Let's ignore what he typed in and make him modify his own style instead.
							uuid = OfflinePlayerUtil.getUniqueIdFromName(args[1]).toString();
						}
						
						// Check if the sender used a valid style
						if (args[2].equalsIgnoreCase("head") || args[2].equalsIgnoreCase("highlight") || args[2].equalsIgnoreCase("text")) {
							
							// Write the new stile to data.yml and save it
							data.set("style." + args[2] + "." + uuid, args[3]);
							DataStorage.saveData();
							
							VariableUtil.sendMessage("hm.settings.success", player);
							
						// Oooouuuuuh nooooouuuuuu, you're doin' it so wrong >:[]
						} else {
							VariableUtil.sendMessage("hm.settings.chat", player);
							VariableUtil.sendMessage("hm.settings.command", player);
						}
						
					} else {
						VariableUtil.sendMessage("hm.settings.chat", player);
						VariableUtil.sendMessage("hm.settings.command", player);
					}
					
				// If the first permission check of "settings" failed
				} else {
					VariableUtil.sendMessage("error.noPermission", player);
				}
				
			// Check for Reload command
			} else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("r")) {
				// If the sender has permission to do it...
				if (sender.hasPermission("holographicmenus.reload")) {
					VariableUtil.sendMessage("hm.reload.reloading", player);
					// tackle it...
					try {
						ConfigStorage.config.reload();
					} catch (InvalidConfigurationException exception) {
						VariableUtil.sendMessage("%highlight%config.yml ", "hm.reload.fail", player);
					}
					
					try {
						DataStorage.data.reload();
					} catch (InvalidConfigurationException exception) {
						VariableUtil.sendMessage("%highlight%data.yml ", "hm.reload.fail", player);
					}
					
					try {
						LanguageStorage.lang.reload();
					} catch (InvalidConfigurationException exception) {
						VariableUtil.sendMessage("%highlight%lang.yml ", "hm.reload.fail", player);
					}
					
				// Permission check failed
				} else {
					VariableUtil.sendMessage("error.noPermission", player);
				}
				
			//Check for /hm v
			} else if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")) {
				sender.sendMessage(VariableUtil.replaceVariables("%text%HolographicMenus %highlight%v" + version, player));
				sender.sendMessage(VariableUtil.replaceVariables("%text%%copyright% 2015 Daniel Saukel, DRE2N-Team", player));// <= That's me :)
				sender.sendMessage(VariableUtil.replaceVariables("&e&mhttp://www.dre2n.ml", player));
				
			// Handle command syntax errors
			} else {
				VariableUtil.sendMessage("hm.main.welcome", player);
				VariableUtil.sendMessage("hm.main.settings", player);
				VariableUtil.sendMessage("hm.main.infos", player);
			}
			return true;
		}
		return false;
	}
	
}
