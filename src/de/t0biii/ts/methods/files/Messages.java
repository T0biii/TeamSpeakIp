package de.t0biii.ts.methods.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import de.t0biii.ts.TeamSpeak;

/**
 * Created by Tobias on 06.01.2017.
 */
public class Messages{
	private TeamSpeak plugin;

	public Messages(TeamSpeak plugin){
		this.plugin = plugin;
	}

	public void loadMessages(){
		File file = getFile();
		YamlConfiguration cfg = getcfg();
		if (!file.exists()){
			try{
				file.createNewFile();
				cfg.options().header("Plugin by T0biii!");
			} catch (IOException e){
				plugin.log.info(plugin.prefix + "Cant Create Messages.YML");
			}
		}
		cfg.options().copyDefaults(true);
		/**
		 * Messages
		 */
		cfg.addDefault("messages.reload", "&3Reload Erfolgreich!");
		cfg.addDefault("messages.reloadfilter", "&3Reload von Filter Erfolgreich!");
		cfg.addDefault("messages.ts3", "&3TeamSpeak3: &2DeineIPvomTS.de");
		cfg.addDefault("messages.konsole", "Dieser Befehl kann nur von einen Spieler ausgefuert werden!");
		cfg.addDefault("messages.no-permission", "&4Du Hast keine Rechte dafuer!");
		cfg.addDefault("messages.update-info", "Neues Update ist verfuegbar!");
		cfg.addDefault("messages.no-update", "&aDu hast bereits die neuste Version!");
		cfg.addDefault("messages.cachenew", "&2&lDer Cache wurde neu befüllt");
		// Save
		save(file, cfg);
	}

	/**
	 * @param file
	 *            The File you want save
	 * @param cfg
	 *            The YamlConfiguration you want save
	 */
	private void save(File file, YamlConfiguration cfg){
		try{
			cfg.save(file);
		} catch (IOException e){
		}
	}

	public static File getFile(){
		File file = new File("plugins/TeamSpeakIP/messages.yml");
		return file;
	}

	public static YamlConfiguration getcfg(){
		File file = getFile();
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		return cfg;
	}
}
