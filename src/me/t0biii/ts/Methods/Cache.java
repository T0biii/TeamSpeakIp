package me.t0biii.ts.Methods;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.configuration.file.YamlConfiguration;
import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import me.t0biii.ts.TeamSpeak;

/**
* Created by Tobias on 05.01.2017.
*/
public class Cache {
	  @SuppressWarnings("unused")
	private TeamSpeak plugin;
	  public Cache(TeamSpeak plugin)
	  {
		  this.plugin = plugin; 
	  }	
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	  
	public  void loadCache(TS3Api api){
		File file = new File("plugins/TeamspeakIP/cache.yml");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
		cachetoconfig(api);
	}
	
	public void cachetoconfig(TS3Api api){
		File file = new File("plugins/TeamspeakIP/cache.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		ArrayList<String> list = new ArrayList<>();	
		for (Client c : api.getClients()) {
			list.add(c.getNickname());
		}
		int i = api.getClients().size();
		int t = api.getHostInfo().getTotalMaxClients();
		cfg.set("ts.time", df.format(dt));
		cfg.set("ts.anzahl", i);
		cfg.set("ts.max", t);
		cfg.set("ts.cache", list);
		try {
			cfg.save(file);
		} catch (IOException e) {
		}	
	}
}