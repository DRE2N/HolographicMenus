package ml.dre2n.holographicmenus.cmd;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.task.InitializeHoloTask;

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
			Bukkit.getScheduler().runTask(HolographicMenus.getPlugin(), new InitializeHoloTask(player, "main"));
			return true;
		}
		return false;
	}
	
}
