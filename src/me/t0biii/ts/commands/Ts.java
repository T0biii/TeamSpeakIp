package me.t0biii.ts.commands;

import java.io.File;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import me.t0biii.ts.TeamSpeak;
import me.t0biii.ts.methods.JsonMessage;
import me.t0biii.ts.methods.SendHelp;
import me.t0biii.ts.methods.Updater;
import me.t0biii.ts.methods.Updater.UpdateResult;


public class Ts implements CommandExecutor{
	static String tsip = "";
	public static TeamSpeak pl = TeamSpeak.instance;
	static File file = new File("plugins/TeamspeakIP/messages.yml");
	static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{ 
		
		if(pl.getConfig().getInt("ts3.port") == 9987){
			tsip = pl.getConfig().getString("ts3.ip");
		}else{
			 tsip = pl.getConfig().getString("ts3.ip") + ":" + pl.getConfig().getString("ts3.port");
		}
		
		if(sender instanceof Player)
		{
			final Player p = (Player) sender;
			if(args.length == 1)
            { 
			if(args[0].equalsIgnoreCase("rl"))
			{					
				//RELOAD COMMAND
				if(p.hasPermission("ts.reload"))
				{
				 pl.reloadConfig();
				 prefixsend(p);
				 p.sendMessage("");
				 p.sendMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.reload")));
       		     p.sendMessage("");
       		     prefixsend(p);		  
				}else
				{
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',  cfg.getString("messages.no-permission")));
				}
				//HELP COMMAND
			}else if(args[0].equalsIgnoreCase("help")){
				SendHelp sh = new SendHelp();
				sh.sendHelp(p);
             /**
              * UPDATE COMMAND    
              */
			}else if(args[0].equalsIgnoreCase("update")){
				if(p.hasPermission("ts.update") || p.isOp()){		
					if(pl.updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE){
						prefixsend(p);
						p.sendMessage("");
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l"+cfg.getString("messages.update-info")));
						p.sendMessage("§2Download Link:");
						p.sendMessage(ChatColor.BLUE+ pl.updater.getLatestFileLink());
						p.sendMessage("");
						prefixsend(p);
					}else if(pl.updater.getResult() != UpdateResult.UPDATE_AVAILABLE){
						prefixsend(p);
						p.sendMessage("");
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',  cfg.getString("messages.no-update")));
						p.sendMessage("");
						prefixsend(p);
					}
				}		
			}
			else if(args[0].equalsIgnoreCase("getip")){
				prefixsend(p);	
				p.sendMessage("");
				{
					JsonMessage jm = new JsonMessage();
					jm.append("§1§lClick This")
					.setClickAsSuggestCmd(ChatColor.translateAlternateColorCodes('&',  tsip))
					.setHoverAsTooltip(ChatColor.translateAlternateColorCodes('&', tsip)).save().send(p);
				}
				p.sendMessage("");
				prefixsend(p);		
				
			}else if(args[0].equalsIgnoreCase("rl-filter")){
				if(p.isOp() || p.hasPermission("ts.filter")){
				pl.fi.loadFilter();
				prefixsend(p);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.reloadfilter")));
				prefixsend(p);
				}else{
					tsipsend(p);
				}

				
			}else if(args[0].equalsIgnoreCase("cache-off")){
				if(p.isOp() || p.hasPermission("ts.cache")){
				pl.getConfig().set("options.realtime", true);
				prefixsend(p);
				p.sendMessage("§3Live data §2Activated. \n§3Cache §cDisabled.");
				prefixsend(p);
				}else{
					tsipsend(p);
				}

			}else if(args[0].equalsIgnoreCase("cache-on")){
				if(p.isOp() || p.hasPermission("ts.cache")){
				pl.getConfig().set("options.realtime", false);
				prefixsend(p);
				p.sendMessage("§3Live data §cDisabled. \n§3Cache §2Activated.");
				prefixsend(p);					
				}else{
					tsipsend(p);
				}

			}else if(args[0].equalsIgnoreCase("cache")){
				if(p.isOp() || p.hasPermission("ts.cache")){
				 pl.ca.cachetoconfig(pl.api);
				 prefixsend(p);
				 {
					 JsonMessage jm = new JsonMessage();
					 jm.append("§cDer Cache wurde neu befüllt").save().send(p);;
				 }
				 prefixsend(p);
				}

			}else if(args[0].equalsIgnoreCase("list")){
				if(pl.error){
					p.sendMessage("§cTeamspeak is unreachable!");
				}else{
					try{
						File cachefile = new File("plugins/TeamspeakIP/cache.yml");
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cachefile);
						File filterfile = new File("plugins/TeamspeakIP/filter.yml");
						YamlConfiguration fcfg = YamlConfiguration.loadConfiguration(filterfile);
						int anzahl = cfg.getInt("ts.anzahl");
						int max = cfg.getInt("ts.max");
						List<String> filter = fcfg.getStringList("ignore");
						List<String> cachelist = cfg.getStringList("ts.cache");
						
				if(!pl.getConfig().getBoolean("options.realtime")){
					prefixsend(p);	
					p.sendMessage(ChatColor.AQUA+"Teamspeak: "+ tsip + " §cCached");
					p.sendMessage(ChatColor.AQUA+"Online: §2"+ (anzahl) +" of " +max);
					p.sendMessage(ChatColor.AQUA+"List of People:");
					for(String Users : cachelist){
						if(!filter.contains(Users)){
							p.sendMessage("§2"+Users);
						}
					}
					prefixsend(p);	
				}else{
					prefixsend(p);	
					p.sendMessage(ChatColor.AQUA+"Teamspeak: "+ tsip + " §2Realtime");
					p.sendMessage(ChatColor.AQUA+"Online: §2"+ (pl.api.getClients().size()) +" of " + pl.api.getHostInfo().getTotalMaxClients());
					p.sendMessage(ChatColor.AQUA+"List of People:");
					for (Client c : pl.api.getClients()) {
						if(!filter.contains(c.getNickname())){		
							p.sendMessage("§2"+c.getNickname());
						}
					}
					prefixsend(p);
				}
				}catch(Exception e){
					p.sendMessage(ChatColor.AQUA+"Online: §2- of -" );
					p.sendMessage(ChatColor.AQUA+"List of People:");
					p.sendMessage("§4Not enough permissions");
					prefixsend(p);
				}
				}				
			}else{
				tsipsend(p);
			}
            }else 
            {
            	 tsipsend(p);
            } 
			return true;
			} else  {
				sender.sendMessage(cfg.getString("messages.konsole"));
			}
		return false;
	}
	
	public static void tsipsend(Player p){
		prefixsend(p);
 		p.sendMessage("");
 		{
 			JsonMessage jm = new JsonMessage();
 			jm.append(ChatColor.translateAlternateColorCodes('&',  cfg.getString("messages.ts3")))
 			.setHoverAsTooltip(ChatColor.translateAlternateColorCodes('&', tsip))
 			.setClickAsSuggestCmd(ChatColor.translateAlternateColorCodes('&',  tsip)).save().send(p);
 		}   
 		p.sendMessage("");
 		prefixsend(p);	
	}
	
	public static void prefixsend(Player p){
		p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
	}
}