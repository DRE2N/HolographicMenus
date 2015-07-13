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
	
	String version = HolographicMenus.getPlugin().getDescription().getVersion();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		Player player = (Player) sender;
		String uuid = player.getUniqueId().toString();
		if (commandlabel.equalsIgnoreCase("holographicmenus") || commandlabel.equalsIgnoreCase("hm")) {
			if (args.length == 0) {
				sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_main_welcome, uuid));
				if (sender.hasPermission("holographicmenus.settings")) {
					sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_main_settings, uuid));
				}
				sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_main_infos, uuid));
				if (sender.hasPermission("holographicmenus.reload")) {
					sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_main_reload, uuid));
				}
			} else if (args[0].equalsIgnoreCase("settings") || args[0].equalsIgnoreCase("s")) {
				if (sender.hasPermission("holographicmenus.settings")) {
					if (args.length == 1) {
						sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().inputwanted_head, uuid));
						sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().inputwanted_help, uuid));
						DataStorage.getData().inputType.put(uuid, "settings_highlight");
						DataStorage.saveData();
					} else if (args.length == 4) {
						if (!(args[1].equals(player.getName()) && sender.hasPermission("holographicmenus.settings.others"))) {
							uuid = OfflinePlayerUtil.getUniqueIdFromName(args[1]).toString();
						}
						if (args[2].equalsIgnoreCase("head") || args[2].equalsIgnoreCase("highlight") || args[2].equalsIgnoreCase("text")) {
							if (args[2].equalsIgnoreCase("head")) {
								DataStorage.getData().style_head.put(uuid, args[3]);
							} else if (args[2].equalsIgnoreCase("highlight")) {
								DataStorage.getData().style_highlight.put(uuid, args[3]);
							} else if (args[2].equalsIgnoreCase("text")) {
								DataStorage.getData().style_text.put(uuid, args[3]);
							}
							DataStorage.saveData();
							sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_settings_success, uuid).replaceAll("%uuid%", uuid));
						} else {
							sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_settings_chat, uuid));
							sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_settings_command, uuid));
						}
					} else {
						sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_settings_chat, uuid));
						sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_settings_command, uuid));
					}
				} else {
					sender.sendMessage(VariableUtil.replaceVariables(ChatColor.RED + LanguageStorage.getData().nopermission, uuid));
				}
			} else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("r")) {
				if (sender.hasPermission("holographicmenus.reload")) {
					sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_reload_reloading, uuid));
					try {
						ConfigStorage.getData().reload();
					} catch (InvalidConfigurationException e) {
						sender.sendMessage(VariableUtil.replaceVariables("%highlight%config.yml " + LanguageStorage.getData().hm_reload_fail, uuid));
					}
					try {
						DataStorage.getData().reload();
					} catch (InvalidConfigurationException e) {
						sender.sendMessage(VariableUtil.replaceVariables("%highlight%data.yml " + LanguageStorage.getData().hm_reload_fail, uuid));
					}
					try {
						LanguageStorage.getData().reload();
					} catch (InvalidConfigurationException e) {
						sender.sendMessage(VariableUtil.replaceVariables("%highlight%lang.yml " + LanguageStorage.getData().hm_reload_fail, uuid));
					}
				} else {
					sender.sendMessage(VariableUtil.replaceVariables(ChatColor.RED + LanguageStorage.getData().nopermission, uuid));
				}
			} else if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")) {
				sender.sendMessage(ChatColor.DARK_AQUA + "HolographicMenus " + ChatColor.DARK_RED + "v" + version);
				sender.sendMessage(ChatColor.DARK_AQUA + "(c) 2015 Daniel Saukel, DRE2N-Team");
				sender.sendMessage(ChatColor.DARK_AQUA + "http://www.dre2n.ml");
			} else {
				sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_main_welcome, uuid));
				sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_main_settings, uuid));
				sender.sendMessage(VariableUtil.replaceVariables(LanguageStorage.getData().hm_main_infos, uuid));
			}
			return true;
		}
		return false;
	}
	
}
