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
import me.t0biii.ts.Methods.JsonMessage;
import me.t0biii.ts.Methods.Updater;
import me.t0biii.ts.Methods.Updater.UpdateResult;


public class ts implements CommandExecutor{
	String tsip = "";
	public static TeamSpeak pl = TeamSpeak.instance;
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
       		     p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("messages.reload")));
       		     p.sendMessage("");
       		     prefixsend(p);		  
				}else
				{
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',  pl.getConfig().getString("messages.no-permission")));
				}
				//HELP COMMAND
			}else if(args[0].equalsIgnoreCase("help")){
				if(p.hasPermission("ts.help") || p.isOp() ){
					 prefixsend(p);	
					 p.sendMessage("");
				   //p.sendMessage(ChatColor.YELLOW+"/ts" + ChatColor.GRAY +"               | The TS IP appears");
					 {
						 JsonMessage jm = new JsonMessage();
						 jm.append(ChatColor.YELLOW+"/ts" + ChatColor.GRAY +"               | The TS IP appears")
						 .setHoverAsTooltip("/ts")
						 .setClickAsExecuteCmd("/ts").save().send(p);
					 }
			       //p.sendMessage(ChatColor.YELLOW+"/ts help" + ChatColor.GRAY +"      | Shows you this Page");
			         {
			        	 JsonMessage jm = new JsonMessage();
			        	 jm.append(ChatColor.YELLOW+"/ts help" + ChatColor.GRAY +"      | Shows you this Page")
			        	 .setHoverAsTooltip("/ts help")
			        	 .setClickAsExecuteCmd("/ts help").save().send(p);
			         }    
				       //p.sendeMessage(ChatColor.YELLOW+"/ts getIP"+ ChatColor.GRAY+"    |  This shows the IP in the Chat");
			         {
			        	 JsonMessage jm = new JsonMessage();
			        	 jm.append(ChatColor.YELLOW+"/ts getIP" + ChatColor.GRAY +"      | This shows the IP in the Chat")
			        	 .setHoverAsTooltip("/ts getIP")
			        	 .setClickAsExecuteCmd("/ts getIP").save().send(p);
			         }
	               //p.sendMessage(ChatColor.YELLOW+"/ts reload" + ChatColor.GRAY +"  | Config Reload");
				     {
				    	 JsonMessage jm = new JsonMessage();
				     	jm.append(ChatColor.YELLOW+"/ts rl" + ChatColor.GRAY +"  | Config Reload")
				     	.setHoverAsTooltip("/ts rl")
				     	.setClickAsExecuteCmd("/ts rl").save().send(p);
				     }		         
	               //p.sendMessage(ChatColor.YELLOW+"/ts update" + ChatColor.GRAY +"  | Get Update Link");
				     {
				    	 JsonMessage jm = new JsonMessage();
			         	jm.append(ChatColor.YELLOW+"/ts update" + ChatColor.GRAY +"  | Get Update Link")
			         	.setHoverAsTooltip("/ts update")
			         	.setClickAsSuggestCmd("/ts update").save().send(p); 
				     }
	               //p.sendMessage(ChatColor.YELLOW+"/ts List" + ChatColor.Gray + "  | Online List");
				     {
				    	 JsonMessage jm = new JsonMessage();
				    	 jm.append(ChatColor.YELLOW+"/ts list"+ ChatColor.GRAY +"  | Online List")
				    	 .setHoverAsTooltip("/ts list")
				    	 .setClickAsExecuteCmd("/ts list").save().send(p); 
				     }
				     p.sendMessage(ChatColor.YELLOW+"/ts add-filter <args>" + ChatColor.GRAY + "  | Add a Name to the Filter list");
				     {
				    	
				     }
				     p.sendMessage(ChatColor.YELLOW+"/ts rl-filter" + ChatColor.GRAY + "  | Reload the Filter list");
				     {
				    	 
				     }
	                 p.sendMessage("");
	                 prefixsend(p);	
				}else{
					tsipsend(p);
				}	  
             /**
              * UPDATE COMMAND    
              */
			}else if(args[0].equalsIgnoreCase("update")){
				if(p.hasPermission("ts.update") || p.isOp()){		
					if(pl.updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE){
						prefixsend(p);
						p.sendMessage("");
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l"+pl.getConfig().getString("messages.update-info")));
						p.sendMessage("§2Download Link:");
						p.sendMessage(ChatColor.BLUE+ pl.updater.getLatestFileLink());
						p.sendMessage("");
						prefixsend(p);
					}else if(pl.updater.getResult() != UpdateResult.UPDATE_AVAILABLE){
						prefixsend(p);
						p.sendMessage("");
					
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',  pl.getConfig().getString("messages.no-update")));
					
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
					.setClickAsSuggestCmd(ChatColor.translateAlternateColorCodes('&',  pl.getConfig().getString("messages.ip")))
					.setHoverAsTooltip(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("messages.ip"))).save().send(p);	
				}
				p.sendMessage("");
				prefixsend(p);		
				
			}else if(args[0].equalsIgnoreCase("rl-filter")){
				pl.fi.loadFilter();
				prefixsend(p);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("messages.reloadfilter")));
				prefixsend(p);
				
			}else if(args[0].equalsIgnoreCase("cache")){
				 pl.ca.cachetoconfig(pl.api);
				 prefixsend(p);
				 {
					 JsonMessage jm = new JsonMessage();
					 jm.append("§cDer Cache wurde neu befüllt").save().send(p);;
				 }
				 prefixsend(p);
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
						int minusfilter = 0;
						List<String> filter = fcfg.getStringList("ignore");
						List<String> cachelist = cfg.getStringList("ts.cache");
						
				if(!pl.getConfig().getBoolean("options.realtime")){
					for(String Users : cachelist){
						if(filter.contains(Users)){
							minusfilter++;
						}
					}
					prefixsend(p);	
					p.sendMessage(ChatColor.AQUA+"Teamspeak: "+ tsip + " §cCached");
					p.sendMessage(ChatColor.AQUA+"Online: §2"+ (anzahl- minusfilter) +" of " +max);
					p.sendMessage(ChatColor.AQUA+"List of People:");
					for(String Users : cachelist){
						if(!filter.contains(Users)){
							p.sendMessage("§2"+Users);
						}
					}
					prefixsend(p);	
				}else{
					for(Client c : pl.api.getClients()){
						if(filter.contains(c.getNickname())){
							minusfilter++;
						}
					}
					prefixsend(p);	
					p.sendMessage(ChatColor.AQUA+"Teamspeak: "+ tsip + " §2Realtime");
					p.sendMessage(ChatColor.AQUA+"Online: §2"+ (pl.api.getClients().size()- minusfilter) +" of " + pl.api.getHostInfo().getTotalMaxClients());
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
				sender.sendMessage(pl.getConfig().getString("messages.konsole"));	
			}
		return false;
	}
	
	private void tsipsend(Player p){
		prefixsend(p);	
 		p.sendMessage("");
 		{
 			JsonMessage jm = new JsonMessage();
 			jm.append(ChatColor.translateAlternateColorCodes('&',  pl.getConfig().getString("messages.ts3")))
 			.setHoverAsTooltip(ChatColor.translateAlternateColorCodes('&', tsip))
 			.setClickAsSuggestCmd(ChatColor.translateAlternateColorCodes('&',  tsip)).save().send(p);
 		}   
 		p.sendMessage("");
 		prefixsend(p);	
	}
	
	public void prefixsend(Player p){
		p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
	}
}