package me.t0biii.ts.methods;

import me.t0biii.ts.TeamSpeak;

public class ConfigManager {
	
	  //Import
	  private TeamSpeak plugin;
	  public ConfigManager(TeamSpeak plugin){ this.plugin = plugin; }	
	  
	 /**
	  * Conf set up
	  */
	   
	 public void loadConfig()
	 {
	 	 
	 /**
	  * Nachrichten
	  */
   	 this.plugin.getConfig().options().header("Plugin by T0biii!\nChange at your own risk");
 
   	 /**
   	  * TS3 Zugangs Daten
   	  */
   	 this.plugin.getConfig().addDefault("ts3.ip", "127.0.0.1");
   	 this.plugin.getConfig().addDefault("ts3.port", 9987);
   	 this.plugin.getConfig().addDefault("ts3.queryport", 10011);
   	 this.plugin.getConfig().addDefault("ts3.querylogin.name", "serveradmin");
   	 this.plugin.getConfig().addDefault("ts3.querylogin.pw", "YOUR_QUERY_PASSWORD");
   	 this.plugin.getConfig().addDefault("ts3.queryname", "TeamspeakIP");
   	 
   	  
   	 /**
   	  * Optionen
   	  */
   	 this.plugin.getConfig().addDefault("options.realtime.activated", true);
   	 this.plugin.getConfig().addDefault("options.realtime.update", 60);
   	 this.plugin.getConfig().addDefault("options.Update-info", true);
   	 this.plugin.getConfig().addDefault("options.Metrics", true);

   	 
   	 
   	 this.plugin.getConfig().options().copyDefaults(true);
	 }
	 
}