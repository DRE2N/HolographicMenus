package ml.dre2n.holographicmenus.cmd;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.storage.ConfigStorage;
import ml.dre2n.holographicmenus.storage.DataStorage;
import ml.dre2n.holographicmenus.storage.LanguageStorage;
import ml.dre2n.holographicmenus.util.OfflinePlayerUtil;
import ml.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public class HolographicMenusCMD implements CommandExecutor {
	
	String version = HolographicMenus.plugin.getDescription().getVersion();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		Player player = (Player) sender;
		String uuid = player.getUniqueId().toString();
		if (commandlabel.equalsIgnoreCase("holographicmenus") || commandlabel.equalsIgnoreCase("hm")) {
			if (args.length == 0) {
				VariableUtil.sendMessage("hm.main.welcome", player);
				if (sender.hasPermission("holographicmenus.settings")) {
					VariableUtil.sendMessage("hm.main.settings", player);
				}
				VariableUtil.sendMessage("hm.main.infos", player);
				if (sender.hasPermission("holographicmenus.reload")) {
					VariableUtil.sendMessage("hm.main.reload", player);
				}
			} else if (args[0].equalsIgnoreCase("settings") || args[0].equalsIgnoreCase("s")) {
				if (sender.hasPermission("holographicmenus.settings")) {
					if (args.length == 1) {
						VariableUtil.sendMessage("inputwanted.head", player);
						VariableUtil.sendMessage("inputwanted.help", player);
						HolographicMenus.inputTypes.put(player, "settings_highlight");
					} else if (args.length == 4) {
						if (!(args[1].equals(player.getName()) && sender.hasPermission("holographicmenus.settings.others"))) {
							uuid = OfflinePlayerUtil.getUniqueIdFromName(args[1]).toString();
						}
						if (args[2].equalsIgnoreCase("head") || args[2].equalsIgnoreCase("highlight") || args[2].equalsIgnoreCase("text")) {
							if (args[2].equalsIgnoreCase("head")) {
								DataStorage.data.style_head.put(uuid, args[3]);
							} else if (args[2].equalsIgnoreCase("highlight")) {
								DataStorage.data.style_highlight.put(uuid, args[3]);
							} else if (args[2].equalsIgnoreCase("text")) {
								DataStorage.data.style_text.put(uuid, args[3]);
							}
							DataStorage.saveData();
							VariableUtil.sendMessage("hm.settings.success", player);
						} else {
							VariableUtil.sendMessage("hm.settings.chat", player);
							VariableUtil.sendMessage("hm.settings.command", player);
						}
					} else {
						VariableUtil.sendMessage("hm.settings.chat", player);
						VariableUtil.sendMessage("hm.settings.command", player);
					}
				} else {
					VariableUtil.sendMessage("noPermission", player);
				}
			} else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("r")) {
				if (sender.hasPermission("holographicmenus.reload")) {
					VariableUtil.sendMessage("hm.reload.reloading", player);
					try {
						ConfigStorage.config.reload();
					} catch (InvalidConfigurationException e) {
						VariableUtil.sendMessage("%highlight%config.yml ", "hm.reload.fail", player);
					}
					try {
						DataStorage.data.reload();
					} catch (InvalidConfigurationException e) {
						VariableUtil.sendMessage("%highlight%data.yml ", "hm.reload.fail", player);
					}
					try {
						LanguageStorage.lang.reload();
					} catch (InvalidConfigurationException e) {
						VariableUtil.sendMessage("%highlight%lang.yml ", "hm.reload.fail", player);
					}
				} else {
					VariableUtil.sendMessage("noPermission", player);
				}
			} else if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")) {
				sender.sendMessage(ChatColor.DARK_AQUA + "HolographicMenus " + ChatColor.DARK_RED + "v" + version);
				sender.sendMessage(ChatColor.DARK_AQUA + "(c) 2015 Daniel Saukel, DRE2N-Team");
				sender.sendMessage(ChatColor.DARK_AQUA + "http://www.dre2n.ml");
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
