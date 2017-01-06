package me.t0biii.ts.listener;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.t0biii.ts.TeamSpeak;
import me.t0biii.ts.Methods.JsonMessage;
import me.t0biii.ts.Methods.Updater;

public class PlayerJoin implements Listener{
	File file = new File("plugins/TeamspeakIP/messages.yml");
	YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	@SuppressWarnings("unused")
	private static TeamSpeak pl = TeamSpeak.instance;
	private TeamSpeak plugin;
	
	public PlayerJoin(TeamSpeak plugin) {
		this.plugin = plugin;
	}
	 
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		//If Player has Permission or is op
		if(p.hasPermission("ts.update") || p.isOp()){
			if(this.plugin.updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE){
				if(this.plugin.getConfig().getBoolean("options.Update-info")){
					String UpdateinfoMes = this.plugin.Prefix+"�4"+cfg.getString("messages.update-info");
					sendChat(p, UpdateinfoMes, "/ts update", "�a/ts update");
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