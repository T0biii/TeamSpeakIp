package me.t0biii.ts.methods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.configuration.file.YamlConfiguration;

import me.t0biii.ts.TeamSpeak;

/**
* Created by Tobias on 05.01.2017.
*/
public class Filter {
	
	@SuppressWarnings("unused")
	private TeamSpeak plugin;
	  public Filter(TeamSpeak plugin)
	  {
		  this.plugin = plugin; 
	  }	
	  
	public  void loadFilter(){
		File file = new File("plugins/TeamspeakIP/filter.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
		ArrayList<String> list = new ArrayList<>();	
		list.add("TeamspeakIP");
		cfg.addDefault("ignore", list);
		cfg.options().copyDefaults(true);
		save(file, cfg);
	}
	
	public void save(File file, YamlConfiguration cfg){
		try {
			cfg.save(file);
		} catch (IOException e) {
		}	
	}

}
