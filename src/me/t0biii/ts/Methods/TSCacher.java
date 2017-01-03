package me.t0biii.ts.Methods;

import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import me.t0biii.ts.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Created by Tobias on 03.01.2017. 
 */
public class TSCacher {

    public static Main pl = Main.instance;
    
    public static Cache ca  = new Cache(pl);
    public static int MaxClients;
    public static int Clients;
    public static ArrayList<String> clientlistname;
    
    
    public static void tsCache(){
    	
      try {
            Clients = (pl.api.getClients().size() - 1);
            MaxClients = pl.api.getHostInfo().getTotalMaxClients();  
            for (Client c : pl.api.getClients()) {
                clientlistname.add(c.getNickname());
            }
            File file = new File("plugins/TeamSpeakIP" ,"cache2.yml");
        	FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        	cfg.options().copyDefaults(true);
        	if(!file.exists()){
        		file.createNewFile();
        	}
          List<String> list = cfg.getStringList("ts3.clientslist");
          for(String a : list){
        	  list.remove(a);
          }
          for(String a : clientlistname){
        	  list.add(a);
          }
          cfg.set("ts3.clientlist", list);
          
  		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
          
          
//            for(String c : clientlistname){
//				if(!c.contains("TeamspeakIP")){		
//					p.sendMessage("§2"+c.getNickname());
//				}
//            }
            
        } catch (Exception e) {


        }
    }
}
