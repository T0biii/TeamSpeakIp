package me.t0biii.ts.Methods;

import me.t0biii.ts.Main;

public class ConfigManager {
	
	  //Import
	  private Main plugin;
	  public ConfigManager(Main plugin)
	  {
		  this.plugin = plugin; 
	  }	
	  
	 //conf set up
	 public void loadConfig()
	 {
		 //Nachrichten
   	 this.plugin.getConfig().options().header("Plugin by T0biii!\nChange at your own risk");
   	 this.plugin.getConfig().addDefault("messages.reload", "§3Reload Erfolgreich!");
   	 this.plugin.getConfig().addDefault("messages.ts3", "§3TeamSpeak3: §DeineIPvomTS.de");
   	 this.plugin.getConfig().addDefault("messages.konsole", "Dieser Befehl kann nur von einen Spieler ausgefürt werden!");
   	 this.plugin.getConfig().addDefault("messages.no-permission", "Du Hast keine Rechte dafür!"); 
   	 this.plugin.getConfig().addDefault("messages.ip", "192.168.178.1:9987");
   	 
   	 this.plugin.getConfig().addDefault("ts3.ip", "127.0.0.1");
   	 this.plugin.getConfig().addDefault("ts3.port", 9987);
   	 this.plugin.getConfig().addDefault("ts3.queryport", 10011);
   	 this.plugin.getConfig().addDefault("ts3.querylogin.name", "serveradmin");
   	 this.plugin.getConfig().addDefault("ts3.querylogin.pw", "YOUR_QUERY_PASSWORD");
   	 
   	 
   	 //True or false
   	 this.plugin.getConfig().set("options.Titels", true);
   	 this.plugin.getConfig().set("ts3.use", false);
   	  
   	 

   	 
   	 
   	 this.plugin.getConfig().options().copyDefaults(true);
	 }
	 
}