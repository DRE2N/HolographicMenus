package ml.dre2n.holographicmenus.cmd;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.task.InitializeHoloTask;
import ml.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
		Player player = (Player) sender;
		if (commandlabel.equalsIgnoreCase("menu") || commandlabel.equalsIgnoreCase("m")) {
			if (args.length >= 1 && sender.hasPermission("holographicmenus.menu." + args[0])) {
				Bukkit.getScheduler().runTask(HolographicMenus.plugin, new InitializeHoloTask(player, args[0]));
			} else if (args.length == 0 && sender.hasPermission("holographicmenus.menu.main")) {
				Bukkit.getScheduler().runTask(HolographicMenus.plugin, new InitializeHoloTask(player, "main"));
			} else {
				VariableUtil.sendMessage("noPermission", player);
			}
			return true;
		}
		return false;
	}
	
}
