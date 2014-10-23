package me.itztobi.ts.listener;



import me.itztobi.ts.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class join implements Listener{
	

	public static Main pl = Main.instance;
	public String link = "§bhttp://dev.bukkit.org/bukkit-plugins/teamspeak-ip/";
	public String prefix = "§a[§6TeamSpeakIP§a]§b ";
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		if(Main.update){
		if(p.isOp() || p.hasPermission("ts.update"))
		{
			if(pl.getConfig().getBoolean("options.updateinfo"))
			{
			p.sendMessage(prefix + "An update is available: " + Main.name + ", a " + Main.type + " for " + Main.version + " do /tsupdate");
			
		}
		}
	}
		}
	
}