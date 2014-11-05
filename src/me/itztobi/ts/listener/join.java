package me.itztobi.ts.listener;



import java.util.logging.Logger;

import me.itztobi.ts.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class join implements Listener{
	

	public static Main pl = Main.instance;
	public String prefix = "§a[§6TeamSpeakIP§a]§b ";
	Logger log = Bukkit.getLogger();
	
	//JoinEvent
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		if(Main.update)
	  {
		if(p.isOp() || p.hasPermission("ts.update"))
		{
			if(pl.getConfig().getBoolean("options.updateinfo"))
			{
			p.sendMessage(prefix + "An update is available: " + Main.name + ", a " + Main.type + " for " + Main.version + " do /tsupdate");
			}
		}
	  }//else if(Main.update == false){
		//log.info("[TeamSpeakIP] is up to date.");
		  
	  //}
	}
	
}

