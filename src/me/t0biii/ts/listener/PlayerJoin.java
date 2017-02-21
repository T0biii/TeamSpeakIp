package me.t0biii.ts.listener;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.t0biii.ts.TeamSpeak;
import me.t0biii.ts.methods.JsonMessage;
import me.t0biii.ts.methods.Updater;
import me.t0biii.ts.methods.files.Messages;

public class PlayerJoin implements Listener{
	
	private TeamSpeak pl;
	public PlayerJoin(TeamSpeak pl){ this.pl = pl; }	
	Messages me = new Messages(pl);
	YamlConfiguration cfg = me.getcfg();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		//If Player has Permission or is op
		if(p.hasPermission("ts.update") || p.isOp()){
			if(pl.updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE){
				if(pl.getConfig().getBoolean("options.Update-info")){
					String UpdateinfoMes = pl.Prefix+"§4"+cfg.getString("messages.update-info");
					sendChat(p, UpdateinfoMes, "/ts update", "§a/ts update");
				}
			}
		}
	}
	
	public void sendChat(Player p,String extraText ,String Click, String Hover ){	
		{
       	 JsonMessage jm = new JsonMessage();
       	 jm.append(extraText)
       	 .setHoverAsTooltip(Hover)
       	 .setClickAsExecuteCmd(Click).save().send(p);
        }
	}
}