package me.t0biii.ts;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.Metrics;

import me.t0biii.ts.Methods.ConfigManager;
import me.t0biii.ts.commands.TsTapCompleter;
import me.t0biii.ts.commands.ts;

@SuppressWarnings("unused")
public class Main extends JavaPlugin{

	public String prefix = "[TeamSpeakIP] ";
	public String updateURL = "http://dev.bukkit.org/bukkit-plugins/teamspeak-ip/files/";
	public static Main instance;
	public ConfigManager cm = new ConfigManager(this);
	Logger log = Bukkit.getLogger();

	
	@Override
	public void onDisable() {
		log.info(prefix +"Plugin disabeld.");
	}

	
	@Override
	public void onEnable() {
		instance = this;
		
		cm.loadConfig();
      	saveConfig();
    	
      	this.getCommand("ts").setExecutor(new ts());
     	this.getCommand("ts").setTabCompleter(new TsTapCompleter(this));
	
        try {
            Metrics metrics = new Metrics();
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }
	
	
	}
}
