package de.t0biii.ts.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.t0biii.ts.TeamSpeak;
import de.t0biii.ts.methods.HelpMessages;
import de.t0biii.ts.methods.files.Messages;

public class Ts implements CommandExecutor{

	private static TeamSpeak pl;

	public Ts(TeamSpeak pl){
		Ts.pl = pl;
	}

	static String tsip = "";
	static YamlConfiguration cfg = Messages.getcfg();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (pl.getConfig().getInt("ts3.port") == 9987){
			tsip = pl.getConfig().getString("ts3.ip");
		} else{
			tsip = pl.getConfig().getString("ts3.ip") + ":" + pl.getConfig().getString("ts3.port");
		}
		if (sender instanceof Player){
			final Player p = (Player) sender;
			if (args.length == 1){
				switch (args[0].toLowerCase()) {
				// RELOAD COMMAND
				case "rl":
					HelpMessages.sendReload(p, pl);
					break;
				// HELP COMMAND
				case "help":
					HelpMessages.sendHelp(p, tsip);
					break;
				// UPDATE COMMAND
				case "update":
					HelpMessages.sendUpdate(p, pl);
					break;
				// GET IP COMMAND
				case "getip":
					HelpMessages.sendGetIP(p, tsip);
					break;
				// rl-filter COMMAND
				case "rl-filter":
					HelpMessages.sendReloadFiler(p, pl, tsip);
					break;
				// cache-off COMMAND
				case "cache-off":
					HelpMessages.sendCacheOFF(p, pl, tsip);
					break;
				// cache-on COMMAND
				case "cache-on":
					HelpMessages.sendCacheON(p, pl, tsip);
					break;
				// cache COMMAND
				case "cache":
					HelpMessages.sendCache(p, pl);
					break;
				// List COMMAND
				case "list":
					HelpMessages.sendList(p, pl, tsip);
					break;
				default:
					HelpMessages.tsipsend(p, tsip);
					break;
				}	
			//  if 1 < Args Lenght > 1
			} else{
				HelpMessages.tsipsend(p, tsip);
			}
			return true;
		} else{
			sender.sendMessage(cfg.getString("messages.konsole"));
		}
		return false;
	}

}