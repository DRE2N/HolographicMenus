/*
 * Copyright (C) 2016-2018 Daniel Saukel
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
package de.erethon.holographicmenus.config;

import de.erethon.commons.config.DREConfig;
import java.io.File;

/**
 * Represents the main config.yml.
 *
 * @author Daniel Saukel
 */
public class HConfig extends DREConfig {

    public static final int CONFIG_VERSION = 0;

    private String language = "english";
    private String mainMenu = "main";

    public HConfig(File file) {
        super(file, CONFIG_VERSION);

        if (initialize) {
            initialize();
        }
        load();
    }

    /* Getters */
    /**
     * @return
     * the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @return
     * the name of the main menu
     */
    public String getMainMenuName() {
        return mainMenu;
    }

    /* Actions */
    @Override
    public void initialize() {
        if (!config.contains("language")) {
            config.set("language", language);
        }

        if (!config.contains("mainMenu")) {
            config.set("mainMenu", mainMenu);
        }

        save();
    }

    @Override
    public void load() {
        language = config.getString("language", language);
        mainMenu = config.getString("mainMenu", mainMenu);
    }

}
