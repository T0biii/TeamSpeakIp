package me.t0biii.ts.Methods;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.t0biii.ts.Main;

public class Cache {
	
	  private Main plugin;
	  public Cache(Main plugin)
	  {
		  this.plugin = plugin; 
	  }	
	
	
	public File file = new File("plugins/TeamSpeakIP" ,"cache.yml");
	public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public void loadCache() {
		cfg.options().copyDefaults(true);
		cfg.options().header("Plugin by T0biii!\nChange at your own risk");
		
		cfg.addDefault("ts3.maxclients", 32);
		cfg.addDefault("ts3.clients", 0);
	//	cfg.addDefault("ts3.clientslist", TSCacher.clientlistname);
		
		//cfg.options().copyDefaults();
		savecfg();
	}
	
	
	public void savecfg() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	  

	 
}