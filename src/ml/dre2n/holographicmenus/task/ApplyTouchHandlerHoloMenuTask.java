package ml.dre2n.holographicmenus.task;

import ml.dre2n.holographicmenus.storage.ConfigStorage;
import ml.dre2n.holographicmenus.storage.DataStorage;
import ml.dre2n.holographicmenus.util.VariableUtil;

import org.bukkit.entity.Player;

import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;

public class ApplyTouchHandlerHoloMenuTask implements Runnable {
	
	TextLine text = null;
	String type = null;
	Player player = null;
	
	public ApplyTouchHandlerHoloMenuTask(TextLine text, String type, Player player) {
		this.text = text;
		this.type = type;
		this.player = player;
	}
	
	@Override
	public void run() {
		final String uuid = player.getUniqueId().toString();
		if (type == "line1") {
			text.setTouchHandler(new TouchHandler() {
				@Override
				public void onTouch(Player player) {
					int lastPage = DataStorage.getData().lastPage.get(uuid);
					if (lastPage == 1) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_1_button1, uuid));
					} else if (lastPage == 2) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_2_button1, uuid));
					} else if (lastPage == 3) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_3_button1, uuid));
					} else if (lastPage == 4) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_4_button1, uuid));
					} else if (lastPage == 5) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_5_button1, uuid));
					} else if (lastPage == 6) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_6_button1, uuid));
					} else if (lastPage == 7) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_7_button1, uuid));
					} else if (lastPage == 8) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_8_button1, uuid));
					} else if (lastPage == 9) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_9_button1, uuid));
					} else if (lastPage == 10) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_10_button1, uuid));
					} else if (lastPage == 11) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_11_button1, uuid));
					} else if (lastPage == 12) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_12_button1, uuid));
					} else if (lastPage == 13) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_13_button1, uuid));
					} else if (lastPage == 14) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_14_button1, uuid));
					} else if (lastPage == 15) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_15_button1, uuid));
					} else if (lastPage == 16) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_16_button1, uuid));
					} else if (lastPage == 17) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_17_button1, uuid));
					} else if (lastPage == 18) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_18_button1, uuid));
					} else if (lastPage == 19) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_19_button1, uuid));
					} else if (lastPage == 20) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_20_button1, uuid));
					}
				}
			});
		}
		if (type == "line2") {
			text.setTouchHandler(new TouchHandler() {
				@Override
				public void onTouch(Player player) {
					int lastPage = DataStorage.getData().lastPage.get(uuid);
					if (lastPage == 1) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_1_button2, uuid));
					} else if (lastPage == 2) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_2_button2, uuid));
					} else if (lastPage == 3) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_3_button2, uuid));
					} else if (lastPage == 4) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_4_button2, uuid));
					} else if (lastPage == 5) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_5_button2, uuid));
					} else if (lastPage == 6) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_6_button2, uuid));
					} else if (lastPage == 7) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_7_button2, uuid));
					} else if (lastPage == 8) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_8_button2, uuid));
					} else if (lastPage == 9) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_9_button2, uuid));
					} else if (lastPage == 10) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_10_button2, uuid));
					} else if (lastPage == 11) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_11_button2, uuid));
					} else if (lastPage == 12) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_12_button2, uuid));
					} else if (lastPage == 13) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_13_button2, uuid));
					} else if (lastPage == 14) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_14_button2, uuid));
					} else if (lastPage == 15) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_15_button2, uuid));
					} else if (lastPage == 16) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_16_button2, uuid));
					} else if (lastPage == 17) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_17_button2, uuid));
					} else if (lastPage == 18) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_18_button2, uuid));
					} else if (lastPage == 19) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_19_button2, uuid));
					} else if (lastPage == 20) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_20_button2, uuid));
					}
				}
			});
		}
		if (type == "line3") {
			text.setTouchHandler(new TouchHandler() {
				@Override
				public void onTouch(Player player) {
					int lastPage = DataStorage.getData().lastPage.get(uuid);
					if (lastPage == 1) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_1_button3, uuid));
					} else if (lastPage == 2) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_2_button3, uuid));
					} else if (lastPage == 3) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_3_button3, uuid));
					} else if (lastPage == 4) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_4_button3, uuid));
					} else if (lastPage == 5) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_5_button3, uuid));
					} else if (lastPage == 6) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_6_button3, uuid));
					} else if (lastPage == 7) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_7_button3, uuid));
					} else if (lastPage == 8) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_8_button3, uuid));
					} else if (lastPage == 9) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_9_button3, uuid));
					} else if (lastPage == 10) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_10_button3, uuid));
					} else if (lastPage == 11) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_11_button3, uuid));
					} else if (lastPage == 12) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_12_button3, uuid));
					} else if (lastPage == 13) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_13_button3, uuid));
					} else if (lastPage == 14) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_14_button3, uuid));
					} else if (lastPage == 15) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_15_button3, uuid));
					} else if (lastPage == 16) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_16_button3, uuid));
					} else if (lastPage == 17) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_17_button3, uuid));
					} else if (lastPage == 18) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_18_button3, uuid));
					} else if (lastPage == 19) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_19_button3, uuid));
					} else if (lastPage == 20) {
						player.performCommand(VariableUtil.replaceVariables(ConfigStorage.getData().menus_main_commands_page_20_button3, uuid));
					}
				}
			});
		}
	}

}
