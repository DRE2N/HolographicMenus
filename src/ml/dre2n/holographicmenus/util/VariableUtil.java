package ml.dre2n.holographicmenus.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import ml.dre2n.holographicmenus.storage.ConfigStorage;
import ml.dre2n.holographicmenus.storage.DataStorage;

public class VariableUtil {
	
	public static String replaceVariables(String message, Player player) {
		String uuid = player.getUniqueId().toString();
		String message001 = message.replaceAll("%head%", DataStorage.getData().style_head.get(uuid));
		String message002 = message001.replaceAll("%maxpages%", ConfigStorage.getData().menus_main_pages);
		String message003 = message002.replaceAll("%highlight%", DataStorage.getData().style_highlight.get(uuid));
		String message004 = message003.replaceAll("%text%", DataStorage.getData().style_text.get(uuid));
		
		String message101 = message004.replaceAll("%play%", "\u25b6");
		String message102 = message101.replaceAll("%copyright%", "\u00a9");
		String message103 = message102.replaceAll("%registered%", "\u00ae");
		String message104 = message103.replaceAll("%square%", "\u00b2");
		String message105 = message104.replaceAll("%cubic%", "\u00b3");
		String message106 = message105.replaceAll("%gbp%", "\u00a3");
		String message107 = message106.replaceAll("%Auml%", "\u00c4");
		String message108 = message107.replaceAll("%Ouml%", "\u00d6");
		String message109 = message108.replaceAll("%Uuml%", "\u00dc");
		String message110 = message109.replaceAll("%auml%", "\u00e4");
		String message111 = message110.replaceAll("%ouml%", "\u00f6");
		String message112 = message111.replaceAll("%uuml%", "\u00fc");
		String message113 = message112.replaceAll("%Agra%", "\u00c0");
		String message114 = message113.replaceAll("%Aaig%", "\u00c1");
		String message115 = message114.replaceAll("%Acir%", "\u00c2");
		String message116 = message115.replaceAll("%Egra%", "\u00c8");
		String message117 = message116.replaceAll("%Eaig%", "\u00c9");
		String message118 = message117.replaceAll("%Ecir%", "\u00ca");
		String message119 = message118.replaceAll("%Cced%", "\u00c7");
		String message120 = message119.replaceAll("%Etre%", "\u00cb");
		String message121 = message120.replaceAll("%Itre%", "\u00cf");
		String message122 = message121.replaceAll("%agra%", "\u00e0");
		String message123 = message122.replaceAll("%aaig%", "\u00e1");
		String message124 = message123.replaceAll("%acir%", "\u00e2");
		String message125 = message124.replaceAll("%egra%", "\u00e8");
		String message126 = message125.replaceAll("%eaig%", "\u00e9");
		String message127 = message126.replaceAll("%ecir%", "\u00ea");
		String message128 = message127.replaceAll("%cced%", "\u00e7");
		String message129 = message128.replaceAll("%etre%", "\u00eb");
		String message130 = message129.replaceAll("%itre%", "\u00ef");
		String message131 = message130.replaceAll("%sz%", "\u00df");
		String message132 = message131.replaceAll("%ae%", "\u00e6");
		String message133 = message132.replaceAll("%oe%", "\u0153");
		String message134 = message133.replaceAll("%eur%", "\u20ac");
		String message135 = message134.replaceAll("%boxtop%", "\u2580");
		String message136 = message135.replaceAll("%boxbottom%", "\u2584");
		String message137 = message136.replaceAll("%boxleft%", "\u258c");
		String message138 = message137.replaceAll("%boxright%", "\u2590");
		String message139 = message138.replaceAll("%boxdot1%", "\u2591");
		String message140 = message139.replaceAll("%boxdot2%", "\u2592");
		String message141 = message140.replaceAll("%boxdot3%", "\u2593");
		String message142 = message141.replaceAll("%koppa%", "\u03df");
		String message143 = message142.replaceAll("%dei%", "\u03ee");
		String message144 = message143.replaceAll("%eternity%", "\u058d");
		String message145 = message144.replaceAll("%sajdah%", "\u06e9");
		String message146 = message145.replaceAll("%trademark%", "\u2122");
		String message147 = message146.replaceAll("%boxfull%", "\u2588");
		String message148 = message147.replaceAll("%squareblack%", "\u25a0");
		String message149 = message148.replaceAll("%squarewhite%", "\u25a1");
		String message150 = message149.replaceAll("%squareblacktiny%", "\u25aa");
		String message151 = message150.replaceAll("%squarewhitetiny%", "\u25ab");
		String message152 = message151.replaceAll("%left%", "\u2190");
		String message153 = message152.replaceAll("%up%", "\u2191");
		String message154 = message153.replaceAll("%right%", "\u2192");
		String message155 = message154.replaceAll("%down%", "\u2193");
		String message156 = message155.replaceAll("%leftright%", "\u2194");
		String message157 = message156.replaceAll("%updown%", "\u2195");
		String message158 = message157.replaceAll("%muuuch%", "\u221e");
		String message159 = message158.replaceAll("%stripe%", "\u25ac");
		String message160 = message159.replaceAll("%playup%", "\u25b2");
		String message161 = message160.replaceAll("%playright%", "\u25ba");
		String message162 = message161.replaceAll("%playdown%", "\u25bc");
		String message163 = message162.replaceAll("%playleft%", "\u25c4");
		String message164 = message163.replaceAll("%rhomb%", "\u25ca");
		String message165 = message164.replaceAll("%circle%", "\u25cb");
		String message166 = message165.replaceAll("%point%", "\u25cf");
		String message167 = message166.replaceAll("%smileywhite%", "\u263a");
		String message168 = message167.replaceAll("%smileyblack%", "\u263b");
		String message169 = message168.replaceAll("%sun%", "\u263c");
		String message170 = message169.replaceAll("%female%", "\u2640");
		String message171 = message170.replaceAll("%male%", "\u2642");
		String message172 = message171.replaceAll("%spade%", "\u2660");
		String message173 = message172.replaceAll("%clubs%", "\u2663");
		String message174 = message173.replaceAll("%heart%", "\u2665");
		String message175 = message174.replaceAll("%diamonds%", "\u2666");
		String message176 = message175.replaceAll("%quaver%", "\u266a");
		String message177 = message176.replaceAll("%2quavers%", "\u266b");
		
		String message201 = message177.replaceAll("&1", "\u00a71");
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
		return commandVariables(message221, player);
	}
	
	public static String commandVariables(String message, Player player) {
		String name = player.getName();
		String uuid = player.getUniqueId().toString();
		String health = String.valueOf(player.getHealth());
		Location location = player.getLocation();
		String message001 = message.replaceAll("%name%", name);
		String message002 = message001.replaceAll("%uuid%", uuid);
		String message003 = message002.replaceAll("%health%", health);
		String message004 = message003.replaceAll("%coords%", "X: " + location.getX() + ", Y: " + location.getY() + ". Z: " + location.getZ());
		return message004;
	}
	
	public static String pageVariable(String message, int page) {
		return message.replaceAll("%page%", String.valueOf(page));
	}
	
}
