package me.t0biii.ts.methods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
	public Cache(TeamSpeak plugin){ this.plugin = plugin; }	
	   
	public  void loadCache(TS3Api api){
		File file = getFile();
		YamlConfiguration cfg = getcfg();
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
		cfg.options().header("Dont touch this File!!! Plugins Update this file automatically!");
		save(file, cfg);
		cachetoconfig(api);
	}
	
	public void cachetoconfig(TS3Api api){
		File file = getFile();
		YamlConfiguration cfg = getcfg();
		ArrayList<String> list = new ArrayList<>();
		for (Client c : api.getClients()) {
			list.add(c.getNickname());
		}
		int i = api.getClients().size();
		int t = api.getHostInfo().getTotalMaxClients();
		cfg.set("ts.anzahl", i);
		cfg.set("ts.max", t);
		cfg.set("ts.cache", list);
		save(file, cfg);
	}
	
	private void save(File file, YamlConfiguration cfg){
		try {
			cfg.save(file);
		} catch (IOException e) {
		}
	}
	public File getFile(){
		File file = new File("plugins/TeamSpeakIP/cache.yml");
		return file;
	}
	public YamlConfiguration getcfg(){
		File file = getFile();
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		return cfg;
	}
}