package ml.dre2n.holographicmenus.task;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.storage.ConfigStorage;
import ml.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.entity.Player;

import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;

public class ApplyTouchHandlerTask implements Runnable {
	
	Player player = null;
	String type = "main";
	TextLine text = null;
	String line = null;
	
	public ApplyTouchHandlerTask(Player player, String type, TextLine text, String line) {
		this.player = player;
		this.type = type;
		this.text = text;
		this.line = line;
	}
	
	@Override
	public void run() {
		final int page = HolographicMenus.lastPages.get(player).get(type);
		text.setTouchHandler(new TouchHandler() {
			@Override
			public void onTouch(Player player) {
				String command = VariableUtil.commandVariables(ConfigStorage.getData().getString("menus." + type + ".commands.page." + page + ".button" + line), player);
				player.performCommand(command);
				HolographicMenus.plugin.getLogger().info(player.getName() + " executed command '" + command + "' (" + type + "page" + page + ", " + type + ").");
			}
		});
	}
	
}
