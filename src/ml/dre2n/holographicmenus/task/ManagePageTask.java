package ml.dre2n.holographicmenus.task;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.storage.ConfigStorage;
import ml.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;

public class ManagePageTask implements Runnable {
	
	Plugin plugin = HolographicMenus.getPlugin();
	
	Player player = null;
	String uuid = null;
	String type = "main";
	int page = 1;
	int pages = Integer.parseInt(ConfigStorage.getData().menus_main_pages);
	
	Hologram hologramHead = null;
	Hologram hologramLine1 = null;
	Hologram hologramLine2 = null;
	Hologram hologramLine3 = null;
	Hologram hologramSwitch = null;
	Hologram hologramClose = null;
	
	public ManagePageTask(Player player, String type, Hologram hologramHead, Hologram hologramLine1, Hologram hologramLine2, Hologram hologramLine3, Hologram hologramSwitch, Hologram hologramClose) {
		this.player = player;
		this.uuid = player.getUniqueId().toString();
		this.type = type;
		this.page = HolographicMenus.lastPages.get(player).get(type);
		this.hologramHead = hologramHead;
		this.hologramLine1 = hologramLine1;
		this.hologramLine2 = hologramLine2;
		this.hologramLine3 = hologramLine3;
		this.hologramSwitch = hologramSwitch;
		this.hologramClose = hologramClose;
	}
	
	@Override
	public void run() {
		@SuppressWarnings("unused")
		final TextLine textHead = hologramHead.appendTextLine(VariableUtil.replaceVariables(VariableUtil.pageVariable(ConfigStorage.getData().menus_main_texts_head, page), player));
		setPage();
		TextLine textSwitch = hologramSwitch.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_switch, player));
		TextLine textClose = hologramClose.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_close, player));
		textSwitch.setTouchHandler(new TouchHandler() {
			@Override
			public void onTouch(Player player) {
				hologramHead.removeLine(0);
				hologramLine1.removeLine(0);
				hologramLine2.removeLine(0);
				hologramLine3.removeLine(0);
				if (page >= pages) {
					page = 0;
				}
				HolographicMenus.lastPages.get(player).put(type, page + 1);
				page = HolographicMenus.lastPages.get(player).get(type);
				@SuppressWarnings("unused")
				final TextLine textHead = hologramHead.appendTextLine(VariableUtil.replaceVariables(VariableUtil.pageVariable(ConfigStorage.getData().menus_main_texts_head, page), player));
				setPage();
			}
		});
		textClose.setTouchHandler(new TouchHandler() {
			@Override
			public void onTouch(Player player) {
				hologramHead.delete();
				hologramLine1.delete();
				hologramLine2.delete();
				hologramLine3.delete();
				hologramSwitch.delete();
				hologramClose.delete();
			}
		});
	}
	
	void setPage() {
		TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(plugin.getConfig().getString("menus." + type + ".texts.page." + page + ".button1"), player));
		TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(plugin.getConfig().getString("menus." + type + ".texts.page." + page + ".button2"), player));
		TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(plugin.getConfig().getString("menus." + type + ".texts.page." + page + ".button3"), player));
		Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerTask(player, type, textLine1, "1"));
		Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerTask(player, type, textLine2, "2"));
		Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerTask(player, type, textLine3, "3"));
	}
	
}
