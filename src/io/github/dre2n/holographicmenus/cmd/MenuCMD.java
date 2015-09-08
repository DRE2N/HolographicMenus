package io.github.dre2n.holographicmenus.cmd;

import io.github.dre2n.holographicmenus.HolographicMenus;
import io.github.dre2n.holographicmenus.storage.MenuStorage;
import io.github.dre2n.holographicmenus.task.InitializeHoloTask;
import io.github.dre2n.holographicmenus.util.VariableUtil;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

public class MenuCMD implements CommandExecutor {

	Plugin plugin = HolographicMenus.plugin;
	Server server = Bukkit.getServer();

	FileConfiguration menus = MenuStorage.getData();

	Player player;
	String type;

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String commandlabel, String[] args) {
		// Let's get a player entity from the command sender.
		player = (Player) sender;

		if (commandlabel.equalsIgnoreCase("menu")
				|| commandlabel.equalsIgnoreCase("m")) {

			// Open a menu for another player
			if (args[0].equalsIgnoreCase("open") && args.length >= 3
					&& sender.hasPermission("holographicmenus.openclose")) {
				player = server.getPlayer(args[1]);
				type = args[2];

				openMenu();

				// Close a menu for another player
			} else if (args[0].equalsIgnoreCase("close") && args.length == 2
					&& sender.hasPermission("holographicmenus.openclose")) {
				player = server.getPlayer(args[1]);

				closeMenu();

				// Open a custom menu, if the sender enters a menu name and has
				// permission to do so
			} else if (args.length >= 1
					&& sender.hasPermission("holographicmenus.menu." + args[0])) {
				type = args[0];

				openMenu();

				// If no arguments are entered, just open the main menu
			} else if (args.length == 0
					&& sender.hasPermission("holographicmenus.menu.main")) {
				type = "main";

				openMenu();

				// Permission check failed
			} else {
				VariableUtil.sendMessage("error.noPermission", player);
			}

			return true;
		}
		return false;
	}

	void openMenu() {

		// Check if menu exists
		if (menus.getString(type + ".pages") != null) {
			Bukkit.getScheduler().runTask(plugin,
					new InitializeHoloTask(player, type));

			// Menu doesn't exist
		} else {
			VariableUtil.sendMessage("error.noMenu", player);
		}

	}

	void closeMenu() {
		// All holograms created by this plugin
		Collection<Hologram> allHolograms = HologramsAPI.getHolograms(plugin);

		// Check all of them...
		for (Hologram hologram2 : allHolograms) {
			Hologram hologram = hologram2;

			if (hologram.getVisibilityManager().isVisibleTo(player)) {
				// ...and delete the hologram if it is visible only to the
				// player.
				hologram.delete();
			}

		}

	}

}
