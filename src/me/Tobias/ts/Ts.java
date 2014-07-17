package me.Tobias.ts;

import java.util.logging.Logger;

import me.Tobias.ts.Updater.ReleaseType;
import me.Tobias.ts.Updater.UpdateResult;
import me.Tobias.ts.commands.*;


import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


@SuppressWarnings("unused")
public class Ts extends JavaPlugin implements CommandExecutor
{
	
	
	
	public static boolean update = false;
	public static String name = "";
	public static ReleaseType type = null;
	public static String version = "";
	public static String link = "";
	public static String link2 = "http://dev.bukkit.org/bukkit-plugins/teamspeak-ip/";
	public String prefix = "[TeamSpeakIP] ";
    public static Ts instance;
	Logger log = Bukkit.getLogger();
	
	public void onDisable() 
    {
          log.info(prefix +"Plugin disabeld ");       
    }
    
    public void onEnable()
    { 	
        on();
        log.info(prefix+"Plugin enabeld "); 	 
    }
        
    public void on(){
       	 instance = this;
      	 this.getCommand("ts").setExecutor(new ts());
      	 this.getCommand("tsupdate").setExecutor(new tsupdate());
      	 PluginManager pm = this.getServer().getPluginManager();
      	 pm.registerEvents(new join(), this);
      	 this.getConfig().options().header("Plugin by Tobias_the_best_!                 "+"                          Change at your own risk");
      	 this.getConfig().addDefault("messages.reload", "§3Reload Erfolgreich!");
      	 this.getConfig().addDefault("messages.ts3", "§3TeamSpeak3: §6TobiasTheBest.de");
      	 this.getConfig().addDefault("messages.konsole", "Dieser Befehl kann nur von einen Spieler ausgefürt werden!");
      	 this.getConfig().addDefault("messages.no-permission", "Du Hast keine Rechte dafür");
      	 this.getConfig().set("options.effects", true);
      	 this.getConfig().set("options.sounds", true);
      	 this.getConfig().set("options.Metrics", true);
      	 this.getConfig().set("options.update-check", true);
      	 this.getConfig().set("options.updateinfo", true);
      	 this.getConfig().options().copyDefaults(true);
      	 this.saveConfig();
    	Updater();
    }
             //updater
             public void Updater(){
        	 if(this.getConfig().getBoolean("options.update-check")){
           	  Updater updater = new Updater(this, 70774, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false); // Start Updater but just do a version check
           	  update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE; // Determine if there is an update ready for us
           	  name = updater.getLatestName(); // Get the latest name
           	  version = updater.getLatestGameVersion(); // Get the latest game version
           	  type = updater.getLatestType(); // Get the latest file's type
           	  link = updater.getLatestFileLink(); // Get the latest link
           	  if(update){
           		  log.info(prefix + "New version available! " + name);
           		  log.info(prefix + "Download at " + link2);  	
           	  }
           	}
         }//update ende
             public void froce(){
            	 Updater updater = new Updater(this, 70774, this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true); // Go straight to downloading, and announce progress to console.
             }
}
