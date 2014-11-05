package me.itztobi.ts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import me.itztobi.ts.Methods.Metrics;
import me.itztobi.ts.Methods.Updater;
import me.itztobi.ts.Methods.ConfigManager;
import me.itztobi.ts.Methods.Updater.ReleaseType;
import me.itztobi.ts.Methods.Updater.UpdateResult;
import me.itztobi.ts.commands.*;
import me.itztobi.ts.listener.join;


import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


@SuppressWarnings("unused")
public class Main extends JavaPlugin 
{
	
	public ConfigManager cm = new ConfigManager(this);
	public static boolean update = false;
	public static String name = "";
	public static ReleaseType type = null;
	public static String version = "";
	public static String link = "";
	public static String link2 = "http://dev.bukkit.org/bukkit-plugins/teamspeak-ip/";
	public String prefix = "[TeamSpeakIP] ";
    public static Main instance;
	Logger log = Bukkit.getLogger();
	//Disable part
	public void onDisable() 
    {
          log.info(prefix +"Plugin disabeld.");       
    }
	//Enable part
    public void onEnable()
    { 	
    	//Metrics start
    	if(this.getConfig().getBoolean("options.Metrics"))
    	{
    	try {
    	    Metrics metrics = new Metrics(this);
    	    metrics.start();
    	} catch (IOException e) {
    	    // Failed to submit the stats :-(
    	}
    	}
    	
        startup();
        Updater();
        log.info(prefix+"Plugin enabeld."); 	 
    }
        
  
          //Load Coammnds and Listener and Config
	     public void startup()
	     {
       	 instance = this;
      	 this.getCommand("ts").setExecutor(new ts());
      	 this.getCommand("tsupdate").setExecutor(new tsupdate());
      	 PluginManager pm = this.getServer().getPluginManager();
      	 pm.registerEvents(new join(), this); 
         cm.loadConfig();
       	 saveConfig();
         }
	     
         //Check version
         public void Updater()
         {
        	if(this.getConfig().getBoolean("options.update-check"))
        	{
           	  Updater updater = new Updater(this, 70774, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false); // Start Updater but just do a version check
           	  update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE; // Determine if there is an update ready for us
           	  name = updater.getLatestName(); // Get the latest name
           	  version = updater.getLatestGameVersion(); // Get the latest game version
           	  type = updater.getLatestType(); // Get the latest file's type
           	  link = updater.getLatestFileLink(); // Get the latest link
           	  if(update)
           	  {
           		  log.info(prefix + "New version available! " + name);
           		  log.info(prefix + "Download at " + link2 +" or go InGame");  	
           	  }else if (update == false){
           		  log.info(prefix + "is up to date.");
           	  }
           	}
         }
         
         
         //Download version    
         public void froce()
         {
            	 Updater updater = new Updater(this, 70774, this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true); // Go straight to downloading, and announce progress to console.
         }
}
