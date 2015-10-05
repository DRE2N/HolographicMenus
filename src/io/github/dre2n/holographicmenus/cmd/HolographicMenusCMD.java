package io.github.dre2n.holographicmenus.cmd;

import io.github.dre2n.holographicmenus.HolographicMenus;
import io.github.dre2n.holographicmenus.storage.CommandStorage;
import io.github.dre2n.holographicmenus.storage.ConfigStorage;
import io.github.dre2n.holographicmenus.storage.DataStorage;
import io.github.dre2n.holographicmenus.storage.LanguageStorage;
import io.github.dre2n.holographicmenus.storage.MenuStorage;
import io.github.dre2n.holographicmenus.util.OfflinePlayerUtil;
import io.github.dre2n.holographicmenus.util.VariableUtil;

import java.util.HashMap;

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
	FileConfiguration lang = LanguageStorage.getData();

	HashMap<Player, String> inputTypes = HolographicMenus.inputTypes;

	// This will be fired if our command is executed.
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		// Let's get a player entity from the command sender.
		Player player = null;
		String uuid = null;
		if (sender instanceof Player) {
			player = (Player) sender;
			uuid = player.getUniqueId().toString();
		}

		if (commandlabel.equalsIgnoreCase("holographicmenus") || commandlabel.equalsIgnoreCase("hm")) {

			// Check if no arguments are entered
			if (args.length == 0) {
				// This is a custom method to send a message to the player,
				// check io.github.dre2n.holographicmenus.util.VariableUtil.
				VariableUtil.sendMessage("hm.main.welcome", sender);

				// Some information are only needed if the sender has the
				// permission to use them.
				if (sender.hasPermission("holographicmenus.settings")) {
					VariableUtil.sendMessage("hm.main.settings", sender);
				}

				VariableUtil.sendMessage("hm.main.infos", sender);

				if (sender.hasPermission("holographicmenus.reload")) {
					VariableUtil.sendMessage("hm.main.reload", sender);
				}
				return true;
				// Check for /hm settings command
			} else if (args[0].equalsIgnoreCase("settings") || args[0].equalsIgnoreCase("s")) {

				// Check if the sender has permission to use the command
				if (sender.hasPermission("holographicmenus.settings")) {

					// Nothing but /hm s?
					if (args.length == 1 && sender instanceof Player) {
						// So, the player wants to use the chat feature to set
						// up his style step by step.
						// First of all, we give him the instructions how to do
						// so.
						VariableUtil.sendMessage("inputwanted.head", sender);
						VariableUtil.sendMessage("inputwanted.help", sender);

						// And of course, we change his input type from chat to
						// settings.
						inputTypes.put(player, "settings_highlight");

						// The right amount of arguments for direct setup?
					} else if (args.length == 4) {

						// Overwrite uuid
						if (OfflinePlayerUtil.getUniqueIdFromName(args[1]) != null && sender.hasPermission("holographicmenus.settings.others")) {
							uuid = OfflinePlayerUtil.getUniqueIdFromName(args[1]).toString();
						}

						// Check if the sender used a valid style
						if (args[2].equalsIgnoreCase("head") || args[2].equalsIgnoreCase("highlight") || args[2].equalsIgnoreCase("text")) {
							// Write the new style to data.yml and save it
							data.set("style." + args[2] + "." + uuid, args[3]);
							DataStorage.saveData();

							VariableUtil.sendMessage("hm.settings.success", sender);

							// Check if the user wants to change his language
						} else if (args[2].equalsIgnoreCase("language") && lang.contains(args[3].toLowerCase())) {
							// Write the new language to data.yml and save it
							data.set("language." + uuid, args[3]);
							DataStorage.saveData();

							VariableUtil.sendMessage("hm.settings.success", sender);
							// Oooouuuuuh nooooouuuuuu, you're doin' it so wrong
							// >:[]
						} else {
							VariableUtil.sendMessage("hm.settings.chat", sender);
							VariableUtil.sendMessage("hm.settings.command", sender);
						}

					} else {
						VariableUtil.sendMessage("hm.settings.chat", sender);
						VariableUtil.sendMessage("hm.settings.command", sender);
					}

					// If the first permission check of "settings" failed
				} else {
					VariableUtil.sendMessage("error.noPermission", sender);
				}

				// Check for Reload command
			} else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("r")) {
				// If the sender has permission to do it...
				if (sender.hasPermission("holographicmenus.reload")) {
					VariableUtil.sendMessage("hm.reload.reloading", sender);
					// tackle it...
					try {
						CommandStorage.commands.reload();
					} catch (InvalidConfigurationException exception) {
						VariableUtil.sendMessage("%highlight%commands.yml ", "hm.reload.fail", sender);
					}

					try {
						ConfigStorage.config.reload();
					} catch (InvalidConfigurationException exception) {
						VariableUtil.sendMessage("%highlight%config.yml ", "hm.reload.fail", sender);
					}

					try {
						DataStorage.data.reload();
					} catch (InvalidConfigurationException exception) {
						VariableUtil.sendMessage("%highlight%data.yml ", "hm.reload.fail", sender);
					}

					try {
						MenuStorage.menus.reload();
					} catch (InvalidConfigurationException exception) {
						VariableUtil.sendMessage("%highlight%menus.yml ", "hm.reload.fail", sender);
					}

					try {
						LanguageStorage.lang.reload();
					} catch (InvalidConfigurationException exception) {
						VariableUtil.sendMessage("%highlight%lang.yml ", "hm.reload.fail", sender);
					}

					// Permission check failed
				} else {
					VariableUtil.sendMessage("error.noPermission", sender);
				}

				// Check for /hm v
			} else if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")) {
				sender.sendMessage(VariableUtil.replaceVariables("%text%HolographicMenus %highlight%v" + version, sender));
				sender.sendMessage(VariableUtil.replaceVariables("%text%%copyright% 2015 Daniel Saukel, DRE2N-Team", sender));// <=
				                                                                                                              // That's
				                                                                                                              // me
				                                                                                                              // :)
				sender.sendMessage(VariableUtil.replaceVariables("&e&nhttp://dre2n.github.io", sender));

				// Handle command syntax errors
			} else {
				VariableUtil.sendMessage("hm.main.welcome", sender);
				VariableUtil.sendMessage("hm.main.settings", sender);
				VariableUtil.sendMessage("hm.main.infos", sender);
			}
			return true;
		}
		return false;
	}

}
