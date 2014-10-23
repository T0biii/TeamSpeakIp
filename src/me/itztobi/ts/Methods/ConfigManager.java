package me.itztobi.ts.Methods;

import me.itztobi.ts.Main;

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
   	 this.plugin.getConfig().options().header("Plugin by itzTobi_!\nChange at your own risk");
   	 this.plugin.getConfig().addDefault("messages.reload", "§3Reload Erfolgreich!");
   	 this.plugin.getConfig().addDefault("messages.ts3", "§3TeamSpeak3: §6TobiasTheBest.de");
   	 this.plugin.getConfig().addDefault("messages.konsole", "Dieser Befehl kann nur von einen Spieler ausgefürt werden!");
   	 this.plugin.getConfig().addDefault("messages.no-permission", "Du Hast keine Rechte dafür"); 
   	 
   	 this.plugin.getConfig().set("options.effects", true);
   	 this.plugin.getConfig().set("options.sounds", true);
   	 this.plugin.getConfig().set("options.Metrics", true);
   	 this.plugin.getConfig().set("options.update-check", true);
   	 this.plugin.getConfig().set("options.updateinfo", true);
   	 
     this.plugin.getConfig().set("effect.AIR_BUBBLE", false);
     this.plugin.getConfig().set("effect.ANGRY_VILLAGER", false);
     this.plugin.getConfig().set("effect.CLOUD", false);
     this.plugin.getConfig().set("effect.CRITICAL_HIT", false);
     this.plugin.getConfig().set("effect.DEPTH_SUSPEND", false);
     this.plugin.getConfig().set("effect.DRIP_LAVA", false);
     this.plugin.getConfig().set("effect.DRIP_WATER", false);
     this.plugin.getConfig().set("effect.ENCHANTMENT_TABLE", false);
     this.plugin.getConfig().set("effect.ENDER", false);
     this.plugin.getConfig().set("effect.EXPLODE", false);
     this.plugin.getConfig().set("effect.FIRE", false);
     this.plugin.getConfig().set("effect.FIREWORK_SPARK", false);
     this.plugin.getConfig().set("effect.FOOTSTEP", false);
     this.plugin.getConfig().set("effect.GREEN_SPARKLE", false);
     this.plugin.getConfig().set("effect.HEART", false);
     this.plugin.getConfig().set("effect.HUGE_EXPLODE", false);
     this.plugin.getConfig().set("effect.ICONCRACK", false);
     this.plugin.getConfig().set("effect.INSTANT_SPELL", false);
     this.plugin.getConfig().set("effect.LARGE_EXPLODE", false);
     this.plugin.getConfig().set("effect.LARGE_SMOKE", false);
     this.plugin.getConfig().set("effect.LAVA_SPARK", false);
     this.plugin.getConfig().set("effect.MAGIC_CRITICAL_HIT", false);
     this.plugin.getConfig().set("effect.MOB_SPELL", false);
     this.plugin.getConfig().set("effect.MOB_SPELL_AMBIENT", false);
     this.plugin.getConfig().set("effect.NOTE_BLOCK", false);
     this.plugin.getConfig().set("effect.REDSTONE_DUST", false);
     this.plugin.getConfig().set("effect.SLIME", false);
     this.plugin.getConfig().set("effect.SNOW_DIG", false);
     this.plugin.getConfig().set("effect.SNOWBALL_HIT", false);
     this.plugin.getConfig().set("effect.SPLASH", false);
     this.plugin.getConfig().set("effect.SUSPEND", false);
     this.plugin.getConfig().set("effect.TILECRACK", false);
     this.plugin.getConfig().set("effect.TOWN_AURA", false);
   	 
   	 
   	 
   	 
   	 
   	 
   	 this.plugin.getConfig().options().copyDefaults(true);
	 }
	 
}