package de.t0biii.ts.listener;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.t0biii.ts.TeamSpeak;
import de.t0biii.ts.methods.JsonMessage;
import de.t0biii.ts.methods.Updater;
import de.t0biii.ts.methods.files.Messages;

public class PlayerJoin implements Listener{

	private TeamSpeak pl;

	public PlayerJoin(TeamSpeak pl){
		this.pl = pl;
	}

	YamlConfiguration cfg = Messages.getcfg();

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		// If Player has Permission or is op
		if (pl.getConfig().getBoolean("options.Update-info")){
			if (p.hasPermission("ts.update") || p.isOp()){
				if (pl.updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE){
					String UpdateinfoMes = pl.Prefix + "§4§l" + cfg.getString("messages.update-info");
					sendChat(p, UpdateinfoMes, "/ts update", "§a/ts update");
				}
			}
		}
		//My UUIDs for Info
		if (p.getUniqueId().toString().equalsIgnoreCase("24fd1681-39bb-3119-b779-4b6c4a2628b5")
				|| p.getUniqueId().toString().equalsIgnoreCase("e13daf95-77c1-4552-80c7-3fad858f2e91")){
			p.sendMessage(pl.Prefix + "§6Der Server benutzt mein Plugin in der Version: " + pl.getDescription().getVersion());
		}
	}

	public void sendChat(Player p, String extraText, String Click, String Hover){
		{
			JsonMessage jm = new JsonMessage();
			jm.append(extraText).setHoverAsTooltip(Hover).setClickAsExecuteCmd(Click).save().send(p);
		}
	}
}