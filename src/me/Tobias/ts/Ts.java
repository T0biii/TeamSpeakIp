package me.Tobias.ts;

import java.util.logging.Logger;

import me.Tobias.ts.commands.*;


import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Ts extends JavaPlugin
{
    public static Ts instance;
	Logger log = Bukkit.getLogger();
	
	public void onDisable() 
    {
          log.info("[TeamSpeakIp] Plugin disabeld ");
    }
    
    public void onEnable()
    {
    	 /* Metrics */
        try {
            
            Metrics metrics = new Metrics(this);
            metrics.start();
            
        } catch(Exception ex) {
        	// Failed to submit the stats :-(
        }
        
        
    	 log.info("[TeamSpeakIp] Plugin enabeld ");
    	 
    	 instance = this;
    	 this.getCommand("ts").setExecutor(new ts());
    	 @SuppressWarnings("unused")
		PluginManager pm = this.getServer().getPluginManager();
    	 
    	 this.getConfig().options().header("Plugin by Tobias_the_best_!                 "+"                          Change at your own risk");
    	 this.getConfig().addDefault("messages.reload", "§3Reload Erfolgreich!");
    	 this.getConfig().addDefault("messages.ts3", "§2Die TS3 IP von <ServerName> lautet: §1TobiasTheBest.de");
    	 this.getConfig().addDefault("messages.konsole", "Dieser Befehl kann nur von einen Spieler ausgefürt werden!");
    	 this.getConfig().set("options.effects", true);
    	 this.getConfig().set("options.sounds", true);
    	 this.getConfig().options().copyDefaults(true);
    	 this.saveConfig();
    }
   
}
