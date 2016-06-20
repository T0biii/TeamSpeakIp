package me.t0biii.ts.listener;

import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.navo.jsonchatlib.JSONChatClickEventType;
import de.navo.jsonchatlib.JSONChatColor;
import de.navo.jsonchatlib.JSONChatExtra;
import de.navo.jsonchatlib.JSONChatFormat;
import de.navo.jsonchatlib.JSONChatHoverEventType;
import de.navo.jsonchatlib.JSONChatMessage;
import me.t0biii.ts.Main;
import me.t0biii.ts.Methods.Updater;

public class PlayerJoin implements Listener{
	
	
	@SuppressWarnings("unused")
	private static Main pl = Main.instance;
	
	
	private Main plugin;
	
	public PlayerJoin(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		//If Player has Permission or is op
		if(p.hasPermission("ts.update") || p.isOp()){
			if(this.plugin.updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE){
				if(this.plugin.getConfig().getBoolean("options.Update-info")){			
					JSONChatMessage message = new JSONChatMessage("", null, null);
					JSONChatExtra extra = new JSONChatExtra(this.plugin.Prefix+"§4"+ this.plugin.getConfig().getString("messages.update-info") , JSONChatColor.YELLOW, Arrays.asList(JSONChatFormat.BOLD));
					extra.setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/ts update");
					extra.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "§a/ts update");
					message.addExtra(extra);
					message.sendToPlayer(p);
				}
			}
			
		}
	}
	
	
}