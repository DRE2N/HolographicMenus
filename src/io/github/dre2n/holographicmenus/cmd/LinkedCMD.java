package io.github.dre2n.holographicmenus.cmd;

import java.util.List;

import io.github.dre2n.holographicmenus.storage.CommandStorage;
import io.github.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class LinkedCMD implements CommandExecutor {
	
	static Server server = Bukkit.getServer();
	
	static FileConfiguration commands = CommandStorage.getData();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		Player player = (Player) sender;
		
		if (commandlabel.equalsIgnoreCase("linked") || commandlabel.equalsIgnoreCase("li") || commandlabel.equalsIgnoreCase("l")) {
			
			if (args.length >= 2) {
				
				// Permission check
				if (sender.hasPermission("holographicmenus.linked." + args[0] + "." + args[1])) {
					
					// Check command
					if (commands.contains(args[1])) {
						
						// Console command
						if (args[0].equalsIgnoreCase("console")) {
							execute(player, args[1], true);
							
						// Player command; as OP
						} else if (args[0].equalsIgnoreCase("op")) {
							execute(player, args[1], false);
							
						// Player command; default
						} else {
							execute(player, args[1]);
						}
						
					// Command doesn't exist
					} else {
						VariableUtil.sendMessage("error.noCommand", sender);
					}
					
				// Permission check failed
				} else {
					VariableUtil.sendMessage("error.noPermission", sender);
				}
				
			// Wrong length
			} else {
				VariableUtil.sendMessage("linked", sender);
			}
			
		}
		return false;
	}
	
	public static void execute(Player player, String command) {
		@SuppressWarnings("unchecked")
		List<String> commandList = (List<String>) commands.getList(command);
		
		for (String commandName : commandList) {
			player.performCommand(VariableUtil.commandVariables(commandName, player));
		}
	}
	
	public static void execute(Player player, String command, boolean asConsole) {
		@SuppressWarnings("unchecked")
		List<String> commandList = (List<String>) commands.getList(command);
		
		if (asConsole) {
			
			for (String commandName : commandList) {
				server.dispatchCommand(server.getConsoleSender(), VariableUtil.commandVariables(commandName, player));
			}
			
		} else {
			
			for (String commandName : commandList) {
				player.setOp(true);
				player.performCommand(VariableUtil.commandVariables(commandName, player));
				player.setOp(false);
			}
			
		}
	}
	
}
