package me.Tobias.ts;

import java.util.logging.Logger;

import me.Tobias.ts.Updater.UpdateResult;
import me.Tobias.ts.commands.*;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Ts extends JavaPlugin
{
	public String prefix = "[TeamSpeakIP] ";
    public static Ts instance;
	Logger log = Bukkit.getLogger();
	
	public void onDisable() 
    {
          log.info(prefix +"Plugin disabeld ");       
    }
    
    public void onEnable()
    {
    	if(this.getConfig().getBoolean("options.updater")){
    	  Updater updater = new Updater(this, 70774, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false); // Start Updater but just do a version check
    	  if(updater.getResult() == UpdateResult.UPDATE_AVAILABLE){
    		  log.info(prefix+"New version available! "+ updater.getLatestName());
    	  }
    	 
    	  
    	  
    	}
    	/* Metrics */
    	if(this.getConfig().getBoolean("options.Metrics")){
    	try {
            
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch(Exception ex) {
        	// Failed to submit the stats :-(
        }
    	}
        
    	 log.info(prefix+"Plugin enabeld ");
    	 
    	 instance = this;
    	 this.getCommand("ts").setExecutor(new ts());
    	 PluginManager pm = this.getServer().getPluginManager();
    	 pm.registerEvents(new join(), this);
    	 this.getConfig().options().header("Plugin by Tobias_the_best_!                 "+"                          Change at your own risk");
    	 this.getConfig().addDefault("messages.reload", "§3Reload Erfolgreich!");
    	 this.getConfig().addDefault("messages.ts3", "§2Die TS3 IP von <ServerName> lautet: §1TobiasTheBest.de");
    	 this.getConfig().addDefault("messages.konsole", "Dieser Befehl kann nur von einen Spieler ausgefürt werden!");
    	 this.getConfig().set("options.effects", true);
    	 this.getConfig().set("options.sounds", true);
    	 this.getConfig().set("options.Metrics", true);
    	 this.getConfig().set("options.updater", true);
    	 this.getConfig().options().copyDefaults(true);
    	 this.saveConfig();
    }
   
}
