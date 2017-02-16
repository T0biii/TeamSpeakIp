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
	
	private TeamSpeak plugin;
	  public Filter(TeamSpeak plugin)
	  {
		  this.plugin = plugin; 
	  }	
	  
	public  void loadFilter(){
		File file = getFile();
		YamlConfiguration cfg = getcfg();
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
		ArrayList<String> list = new ArrayList<>();	
		list.add(plugin.getConfig().getString("ts3.queryname"));
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
	public File getFile(){
		File file = new File(plugin.path + "filter.yml");
		return file;
	}
	public YamlConfiguration getcfg(){
		File file = getFile();
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		return cfg;
	}
}
