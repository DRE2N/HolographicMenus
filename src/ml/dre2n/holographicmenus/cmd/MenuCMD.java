package ml.dre2n.holographicmenus.cmd;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.storage.ConfigStorage;
import ml.dre2n.holographicmenus.task.InitializeHoloTask;
import ml.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MenuCMD implements CommandExecutor {
	
	Plugin plugin = HolographicMenus.plugin;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		// Let's get a player entity from the command sender.
		Player player = (Player) sender;
		
		if (commandlabel.equalsIgnoreCase("menu") || commandlabel.equalsIgnoreCase("m")) {
			
			// Open a custom menu, if the sender enters a menu name and has permission to do so
			if (args.length >= 1 && sender.hasPermission("holographicmenus.menu." + args[0])) {
				
				// Check if menu exists
				if (ConfigStorage.getData().getString("menus." + args[0] + ".pages") != null) {
					Bukkit.getScheduler().runTask(plugin, new InitializeHoloTask(player, args[0]));
					
				// Menu doesn't exist
				} else {
					VariableUtil.sendMessage("error.noMenu", player);
				}
				
			// If no arguments are entered, just open the main menu
			} else if (args.length == 0 && sender.hasPermission("holographicmenus.menu.main")) {
				Bukkit.getScheduler().runTask(plugin, new InitializeHoloTask(player, "main"));
				
			// Permission check failed
			} else {
				VariableUtil.sendMessage("error.noPermission", player);
			}
			return true;
		}
		return false;
	}
	
}
