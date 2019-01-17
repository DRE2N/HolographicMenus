/*
 * Copyright (C) 2016-2019 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.erethon.holographicmenus.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public enum Placeholder {

    PLAY("%play%", "\u25b6"),
    COPYRIGHT("%copyright%", "\u00a9"),
    REGISTERED("%registered%", "\u00ae"),
    SQUARE("%square%", "\u00b2"),
    CUBIC("%cubic%", "\u00b3"),
    POUND_STERLING("%gbp%", "\u00a3"),
    EURO("%eur%", "\u20ac"),
    BOX_TOP("%boxtop%", "\u2580"),
    BOX_BOTTOM("%boxbottom%", "\u2584"),
    BOX_LEFT("%boxleft%", "\u258c"),
    BOX_RIGHT("%boxright%", "\u2590"),
    BOX_DOT_1("%boxdot1%", "\u2591"),
    BOX_DOT_2("%boxdot2%", "\u2592"),
    BOX_DOT_3("%boxdot3%", "\u2593"),
    KOPPA("%koppa%", "\u03df"),
    DEI("%dei%", "\u03ee"),
    ETERNITY("%eternity%", "\u058d"),
    SAJDAH("%sajdah%", "\u06e9"),
    TRADEMARK("%trademark%", "\u2122"),
    BOX_FULL("%boxfull%", "\u2588"),
    SQUARE_BLACK("%squareblack%", "\u25a0"),
    SQUARE_WHITE("%squarewhite%", "\u25a1"),
    SQUARE_BLACK_TINY("%squareblacktiny%", "\u25aa"),
    SQUARE_WHITE_TINY("%squarewhitetiny%", "\u25ab"),
    LEFT("%left%", "\u2190"),
    UP("%up%", "\u2191"),
    RIGHT("%right%", "\u2192"),
    DOWN("%down%", "\u2193"),
    LEFT_RIGHT("%leftright%", "\u2194"),
    UP_DOWN("%updown%", "\u2195"),
    MUUUCH("%muuuch%", "\u221e"),
    STRIPE("%stripe%", "\u25ac"),
    PLAY_UP("%playup%", "\u25b2"),
    PLAY_RIGHT("%playright%", "\u25ba"),
    PLAY_DOWN("%playdown%", "\u25bc"),
    PLAY_LEFT("%playleft%", "\u25c4"),
    RHOMB("%rhomb%", "\u25ca"),
    CIRCLE("%circle%", "\u25cb"),
    POINT("%point%", "\u25cf"),
    SMILEY_WHITE("%smileywhite%", "\u263a"),
    SMILEY_BLACK("%smileyblack%", "\u263b"),
    SUN("%sun%", "\u263c"),
    FEMALE("%female%", "\u2640"),
    MALE("%male%", "\u2642"),
    SPADE("%spade%", "\u2660"),
    CLUBS("%clubs%", "\u2663"),
    HEART("%heart%", "\u2665"),
    DIAMONDS("%diamonds%", "\u2666"),
    QUAVER("%quaver%", "\u266a"),
    QUAVERS("%2quavers%", "\u266b");

    private String placeholder;
    private String replace;

    Placeholder(String placeholder, String replace) {
        this.placeholder = placeholder;
        this.replace = replace;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getReplaceString() {
        return replace;
    }

    public static String parse(Player player, String string) {
        string = ChatColor.translateAlternateColorCodes('&', string);
        for (Placeholder p : values()) {
            string = string.replace(p.placeholder, p.replace);
        }
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            string = PlaceholderAPI.setPlaceholders(player, string);
        }
        return string;
    }

}
