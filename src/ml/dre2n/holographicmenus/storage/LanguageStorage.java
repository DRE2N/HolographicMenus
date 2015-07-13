package ml.dre2n.holographicmenus.storage;

import java.io.File;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.Plugin;

import ml.dre2n.holographicmenus.file.FileUtil;

public class LanguageStorage extends FileUtil {
	
	public static LanguageStorage lang;
	
	public LanguageStorage(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "lang.yml");
		CONFIG_HEADER = "HolographicMenus Language";
	}
	
	public static LanguageStorage getData() {
		return lang;
	}
	
	public static void saveData() {
		try {
			getData().save();
		} catch (InvalidConfigurationException exception) {
			exception.printStackTrace();
		}
	}
	
	public String inputwanted_finished = "%text%That's it. Now you've got your own personalized, shiny holo menu :)";
	public String inputwanted_help = "%text%Enter arguments. Use %highlight%+ %text%to go on, %highlight%ok%text% to accept and get back into chat, %highlight%new %text%to delete your input and %highlight%- %text%to close.";
	public String inputwanted_head = "%text%Okay, so let's setup your style step by step. First of all, enter a colour code you want for head texts.";
	public String inputwanted_highlight = "%text%Well done, now type in your style for highlighted texts.";
	public String inputwanted_text = "%text%We've nearly finished, just the style for standard texts is still missing.";
	public String hm_main_welcome = "%text%Welcome to HolographicMenus.";
	public String hm_main_settings = "%text%Enter %highlight%/hm settings,s (help,h,?)%text% to change your settings.";
	public String hm_main_infos = "%text%Enter %highlight%/hm version,v %text%to see general plugin information.";
	public String hm_main_reload = "%text%Enter %highlight%/hm reload,r %text%to reload storage files.";
	public String hm_reload_fail = "%text%couldn't be loaded properly. I'm sorry ;_; Please try http://www.yamllint.com/ to fix your file and reload once again.";
	public String hm_reload_reloading = "%text%Reloading files...";
	public String hm_settings_chat = "%text%Enter %highlight%/hm settings,s %text%to change your settings step by step.";
	public String hm_settings_command = "%text%Enter %highlight%/hm settings,s [player] [head|highlight|text] [colour code] %text%to setup the settings directly.";
	public String hm_settings_success = "%text%Settings successfully changed for UUID %highlight%%uuid%%text%.";
	public String nopermission = "%text%Sorry, but you do not have permission to do this.";
	
}
