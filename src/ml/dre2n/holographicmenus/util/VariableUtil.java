package ml.dre2n.holographicmenus.util;

import ml.dre2n.holographicmenus.storage.ConfigStorage;
import ml.dre2n.holographicmenus.storage.DataStorage;

public class VariableUtil {
	
	public static String replaceVariables(String message, String uuid) {
		String message001 = message.replaceAll("%head%", DataStorage.getData().style_head.get(uuid));
		String message002 = message001.replaceAll("%maxpages%", ConfigStorage.getData().menus_main_pages);
		String message003 = message002.replaceAll("%highlight%", DataStorage.getData().style_highlight.get(uuid));
		String message004 = message003.replaceAll("%text%", DataStorage.getData().style_text.get(uuid));
		String message101 = message004.replaceAll("%play%", "\u25b6");
		String message201 = message101.replaceAll("&1", "\u00a71");
		String message202 = message201.replaceAll("&2", "\u00a72");
		String message203 = message202.replaceAll("&3", "\u00a73");
		String message204 = message203.replaceAll("&4", "\u00a74");
		String message205 = message204.replaceAll("&5", "\u00a75");
		String message206 = message205.replaceAll("&6", "\u00a76");
		String message207 = message206.replaceAll("&7", "\u00a77");
		String message208 = message207.replaceAll("&8", "\u00a78");
		String message209 = message208.replaceAll("&9", "\u00a79");
		String message210 = message209.replaceAll("&a", "\u00a7a");
		String message211 = message210.replaceAll("&b", "\u00a7b");
		String message212 = message211.replaceAll("&c", "\u00a7c");
		String message213 = message212.replaceAll("&d", "\u00a7d");
		String message214 = message213.replaceAll("&e", "\u00a7e");
		String message215 = message214.replaceAll("&f", "\u00a7f");
		String message216 = message215.replaceAll("&k", "\u00a7k");
		String message217 = message216.replaceAll("&l", "\u00a7l");
		String message218 = message217.replaceAll("&m", "\u00a7m");
		String message219 = message218.replaceAll("&n", "\u00a7n");
		String message220 = message219.replaceAll("&o", "\u00a7o");
		String message221 = message220.replaceAll("&r", "\u00a7r");
		return message221;
	}
	
	public static String pageVariable(String message, int page) {
		return message.replaceAll("%page%", String.valueOf(page));
	}
	
}
