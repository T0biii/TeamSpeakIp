package me.t0biii.ts.Methods;

import me.t0biii.ts.Main;


public class ConfigManager {
	
	  //Import
	  private Main plugin;
	  public ConfigManager(Main plugin)
	  {
		  this.plugin = plugin; 
	  }	
	  
	 /**
	  * Conf set up
	  */
	  
	 public void loadConfig()
	 {
	 	 
	 /**
	  * Nachrichten
	  */
   	 this.plugin.getConfig().options().header("Plugin by T0biii!\nChange at your own risk");
   	 
   	 this.plugin.getConfig().addDefault("messages.reload", "&3Reload Erfolgreich!");
   	 this.plugin.getConfig().addDefault("messages.ts3", "&3TeamSpeak3: &2DeineIPvomTS.de");
   	 this.plugin.getConfig().addDefault("messages.ip", "127.0.0.1:9987");
   	 this.plugin.getConfig().addDefault("messages.konsole", "Dieser Befehl kann nur von einen Spieler ausgefuert werden!");
   	 this.plugin.getConfig().addDefault("messages.no-permission", "&4Du Hast keine Rechte dafür!"); 
   	 this.plugin.getConfig().addDefault("messages.update-info", "Neues Update ist verfuegbar!");
   	 this.plugin.getConfig().addDefault("messages.no-update", "&aDu hast bereits die neuste Version!");
   	 /**
   	  * TS3 Zugangs Daten
   	  */
   	 this.plugin.getConfig().addDefault("ts3.ip", "127.0.0.1");
   	 this.plugin.getConfig().addDefault("ts3.port", 9987);
   	 this.plugin.getConfig().addDefault("ts3.queryport", 10011);
   	 this.plugin.getConfig().addDefault("ts3.querylogin.name", "serveradmin");
   	 this.plugin.getConfig().addDefault("ts3.querylogin.pw", "YOUR_QUERY_PASSWORD");
   	 
   	  
   	 /**
   	  * Optionen
   	  */
   	 this.plugin.getConfig().addDefault("options.Update-info", true);
   	 this.plugin.getConfig().addDefault("options.Metrics", true);

   	 
   	 
   	 this.plugin.getConfig().options().copyDefaults(true);
	 }
	 
}