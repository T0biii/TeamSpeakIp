package me.t0biii.ts.Methods;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import me.t0biii.ts.TeamSpeak;

/**
* Created by Tobias on 06.01.2017.
*/
public class Languages {
	  @SuppressWarnings("unused")
	private TeamSpeak plugin;
	  public Languages(TeamSpeak plugin)
	  {
		  this.plugin = plugin; 
	  }	
	  
	public  void loadLanguages(){
		File file = new File("plugins/TeamspeakIP/languages/Languages.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(!file.exists()){
			try {
				file.mkdir();
				file.createNewFile();
			} catch (IOException e) {
			}
		}
		
		save(file, cfg);
		
	}
	
	private void save(File file, YamlConfiguration cfg){
		try {
			cfg.save(file);
		} catch (IOException e) {
		}
	}
}