package ml.dre2n.holographicmenus.task;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.storage.ConfigStorage;
import ml.dre2n.holographicmenus.storage.DataStorage;
import ml.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;

public class ManagePageHoloMenuTask implements Runnable {
	
	Plugin plugin = HolographicMenus.getPlugin();
	
	Player player = null;
	String uuid = null;
	int page = 1;
	int pages = Integer.parseInt(ConfigStorage.getData().menus_main_pages);
	
	Hologram hologramHead = null;
	Hologram hologramLine1 = null;
	Hologram hologramLine2 = null;
	Hologram hologramLine3 = null;
	Hologram hologramSwitch = null;
	Hologram hologramClose = null;
	
	public ManagePageHoloMenuTask(Player player, Hologram hologramHead, Hologram hologramLine1, Hologram hologramLine2, Hologram hologramLine3, Hologram hologramSwitch, Hologram hologramClose) {
		this.player = player;
		this.uuid = player.getUniqueId().toString();
		this.page = DataStorage.getData().lastPage.get(uuid);
		this.hologramHead = hologramHead;
		this.hologramLine1 = hologramLine1;
		this.hologramLine2 = hologramLine2;
		this.hologramLine3 = hologramLine3;
		this.hologramSwitch = hologramSwitch;
		this.hologramClose = hologramClose;
	}
	
	@Override
	public void run() {
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
				int lastPage = DataStorage.getData().lastPage.get(uuid);
				if (lastPage >= pages) {
					lastPage = 0;
				}
				DataStorage.getData().lastPage.put(uuid, lastPage + 1);
				DataStorage.saveData();
				page = DataStorage.getData().lastPage.get(uuid);
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
		if (page == 1) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_1_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_1_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_1_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 2) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_2_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_2_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_2_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 3) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_3_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_3_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_3_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 4) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_4_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_4_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_4_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 5) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_5_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_5_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_5_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 6) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_6_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_6_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_6_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 7) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_7_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_7_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_7_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 8) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_8_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_8_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_8_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 9) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_9_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_9_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_9_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 10) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_10_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_10_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_10_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 11) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_11_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_11_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_11_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 12) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_12_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_12_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_12_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 13) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_13_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_13_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_13_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 14) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_14_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_14_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_14_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 15) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_15_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_15_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_15_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 16) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_16_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_16_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_16_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 17) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_17_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_17_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_17_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 18) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_18_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_18_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_18_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 19) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_19_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_19_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_19_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		} else if (page == 20) {
			TextLine textLine1 = hologramLine1.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_20_button1, player));
			TextLine textLine2 = hologramLine2.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_20_button2, player));
			TextLine textLine3 = hologramLine3.appendTextLine(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_texts_page_20_button3, player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine1, "line1", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine2, "line2", player));
			Bukkit.getScheduler().runTask(plugin, new ApplyTouchHandlerHoloMenuTask(textLine3, "line3", player));
		}
	}
	
}
