package me.Tobias.ts;



import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class join implements Listener{
	

	
	public static Ts pl = Ts.instance;
	public String link = "§bhttp://dev.bukkit.org/bukkit-plugins/teamspeak-ip/";
	public String prefix = "§a[§6TeamSpeakIP§a]§b ";
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		if(Ts.update){
		if(p.isOp() || p.hasPermission("ts.update"))
		{
			if(pl.getConfig().getBoolean("options.updateinfo"))
			{
			p.sendMessage(prefix + "An update is available: " + Ts.name + ", a " + Ts.type + " for " + Ts.version + " do /tsupdate");
			
		}
		}
	}
		}
	
}