package ml.dre2n.holographicmenus.storage;

import java.io.File;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import ml.dre2n.holographicmenus.HolographicMenus;
import ml.dre2n.holographicmenus.file.FileUtil;

public class LanguageStorage extends FileUtil {
	
	static Plugin plugin = HolographicMenus.plugin;
	
	// Easily get variables
	public static LanguageStorage lang;
	
	// Set header and file name
	public LanguageStorage(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "lang.yml");
		CONFIG_HEADER = "HolographicMenus Language";
	}
	
	// To get data by path, not by the variable listed here
	public static FileConfiguration getData() {
		File langFile = new File(plugin.getDataFolder(), "lang.yml");
		FileConfiguration lang = YamlConfiguration.loadConfiguration(langFile);
		return lang;
	}
	
	// Easily save file
	public static void saveData() {
		try {
			lang.save();
		} catch (InvalidConfigurationException exception) {
			exception.printStackTrace();
		}
	}
	
	// English
	public String english_inputwanted_finished = "%text%That's it. Now you've got your own personalized, shiny holo menu :)";
	public String english_inputwanted_help = "%text%Enter arguments. Use %highlight%+ %text%to go on, %highlight%ok%text% to accept and get back into chat, %highlight%new %text%to delete your input and %highlight%- %text%to close.";
	public String english_inputwanted_head = "%text%Okay, so let's setup your style step by step. First of all, enter a colour code you want for head texts.";
	public String english_inputwanted_highlight = "%text%Well done, now type in your style for highlighted texts.";
	public String english_inputwanted_text = "%text%We've nearly finished, just the style for standard texts is still missing.";
	
	public String english_hm_main_welcome = "%text%Welcome to HolographicMenus.";
	public String english_hm_main_settings = "%text%Enter %highlight%/hm settings,s (help,h,?)%text% to change your settings.";
	public String english_hm_main_infos = "%text%Enter %highlight%/hm version,v %text%to see general plugin information.";
	public String english_hm_main_reload = "%text%Enter %highlight%/hm reload,r %text%to reload storage files.";
	
	public String english_hm_reload_fail = "%text%couldn't be loaded properly. I'm sorry ;_; Please try http://www.yamllint.com/ to fix your file and reload once again.";
	public String english_hm_reload_reloading = "%text%Reloading files...";
	
	public String english_hm_settings_chat = "%text%Enter %highlight%/hm settings,s %text%to change your settings step by step.";
	public String english_hm_settings_command = "%text%Enter %highlight%/hm settings,s [player] [head|highlight|text] [colour code] %text%to setup the settings directly.";
	public String english_hm_settings_success = "%text%Settings successfully changed for UUID %highlight%%uuid%%text%.";
	
	public String english_error_noPermission = "%text%Sorry, but you do not have permission to do this.";
	public String english_error_noMenu = "%text%Sorry, but this menu does not exist.";
	
	// German
	public String german_inputwanted_finished = "%text%Das war's. Jetzt hast Du Dein pers%ouml%nliches, wunderh%uuml%bsches Men%uuml% :)";
	public String german_inputwanted_help = "%text%Gebe Argumente ein. Benutze %highlight%+ %text%um fortzufahren, %highlight%ok%text% zum Akzeptieren und in den Chat zur%uuml%ckkehren, %highlight%new %text%um die bisherige Eingabe zu l%ouml%schen und %highlight%- %text%zum Schlie%sz%en.";
	public String german_inputwanted_head = "%text%Okay, lass uns Deinen Stil Schritt f%uuml%r Schritt einstellen. Gebe erstmal einen Colour Code f%uuml%r %Uuml%berschriften ein.";
	public String german_inputwanted_highlight = "%text%Gute Arbeit, gebe jetzt Deinen Colour Code f%uuml%r hervorgehobene Texte ein.";
	public String german_inputwanted_text = "%text%Wir haben's bald, es fehlt blo%sz% noch der Code f%uuml%r die Standardtexte.";
	
	public String german_hm_main_welcome = "%text%Willkommen bei HolographicMenus.";
	public String german_hm_main_settings = "%text%Gebe %highlight%/hm settings,s (help,h,?)%text% ein, um Deine Einstellungen zu bearbeiten.";
	public String german_hm_main_infos = "%text%Gebe %highlight%/hm version,v %text%ein, um ein paar Infos zum Plugin zu erhalten.";
	public String german_hm_main_reload = "%text%Gebe %highlight%/hm reload,r %text%um die Config Files zu reloaden.";
	
	public String german_hm_reload_fail = "%text%konnte nicht richtig geladen werden. Tut mir leid ;_; Bitte versuch http://www.yamllint.com/, um Dein File zu fixen und reloade nochmal.";
	public String german_hm_reload_reloading = "%text%Lade Files...";
	
	public String german_hm_settings_chat = "%text%Gebe %highlight%/hm settings,s %text%ein, um Deine Einstellungen Schritt f%uuml%r Schritt zu bearbeiten.";
	public String german_hm_settings_command = "%text%Gebe %highlight%/hm settings,s [player] [head|highlight|text] [colour code] %text%ein, um die Einstellungen direkt zu bearbeiten.";
	public String german_hm_settings_success = "%text%Einstellungen f%uuml% die UUID %highlight%%uuid%%text% erfolgreich angepasst.";
	
	public String german_error_noPermission = "%text%Tut mir leid, aber Du hast nicht die Rechte, das zu tun.";
	public String german_error_noMenu = "%text%Tut mir leid, aber dieses Men%uuml% existiert nicht.";
	
	// TODO: French
	public String french_inputwanted_finished = "%text%C'est %cced%a. Alors, tu as ton menu magnifique et personnalis%eaig% :)";
	public String french_inputwanted_help = "%text%Enter arguments. Use %highlight%+ %text%to go on, %highlight%ok%text% to accept and get back into chat, %highlight%new %text%to delete your input and %highlight%- %text%to close.";
	public String french_inputwanted_head = "%text%Okay, so let's setup your style step by step. First of all, enter a colour code you want for head texts.";
	public String french_inputwanted_highlight = "%text%Well done, now type in your style for highlighted texts.";
	public String french_inputwanted_text = "%text%We've nearly finished, just the style for standard texts is still missing.";
	
	public String french_hm_main_welcome = "%text%Welcome to HolographicMenus.";
	public String french_hm_main_settings = "%text%Enter %highlight%/hm settings,s (help,h,?)%text% to change your settings.";
	public String french_hm_main_infos = "%text%Enter %highlight%/hm version,v %text%to see general plugin information.";
	public String french_hm_main_reload = "%text%Enter %highlight%/hm reload,r %text%to reload storage files.";
	
	public String french_hm_reload_fail = "%text%couldn't be loaded properly. I'm sorry ;_; Please try http://www.yamllint.com/ to fix your file and reload once again.";
	public String french_hm_reload_reloading = "%text%Reloading files...";
	
	public String french_hm_settings_chat = "%text%Enter %highlight%/hm settings,s %text%to change your settings step by step.";
	public String french_hm_settings_command = "%text%Enter %highlight%/hm settings,s [player] [head|highlight|text] [colour code] %text%to setup the settings directly.";
	public String french_hm_settings_success = "%text%Settings successfully changed for UUID %highlight%%uuid%%text%.";
	
	public String french_error_noPermission = "%text%Sorry, but you do not have permission to do this.";
	public String french_error_noMenu = "%text%Je suis d%eaig%sol%eaig%, mais le menu n'existe pas.";
	
}
