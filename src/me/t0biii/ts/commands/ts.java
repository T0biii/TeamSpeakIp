package me.t0biii.ts.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import me.t0biii.ts.TeamSpeak;
import me.t0biii.ts.Methods.JsonMessage;
import me.t0biii.ts.Methods.Updater;
import me.t0biii.ts.Methods.Updater.UpdateResult;


public class ts implements CommandExecutor{

	public static TeamSpeak pl = TeamSpeak.instance;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{	
		
		
		if(sender instanceof Player)
		{
			final Player p = (Player) sender;
			if(args.length == 1)
            { 
			if(args[0].equalsIgnoreCase("reload"))
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
				     	jm.append(ChatColor.YELLOW+"/ts reload" + ChatColor.GRAY +"  | Config Reload")
				     	.setHoverAsTooltip("/ts reload")
				     	.setClickAsExecuteCmd("/ts reload").save().send(p);
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
				
			}else if(args[0].equalsIgnoreCase("cache")){
				 
				
			}else if(args[0].equalsIgnoreCase("list")){
			
				if(pl.error){
					p.sendMessage("§cTeamspeak is unreachable!");
				}else{
				
					try{			
				prefixsend(p);	
				p.sendMessage(ChatColor.AQUA+"Teamspeak: "+pl.getConfig().getString("messages.ip"));
				p.sendMessage(ChatColor.AQUA+"Online: §2"+ (pl.api.getClients().size()- 1) +" of " + pl.api.getHostInfo().getTotalMaxClients());
				p.sendMessage(ChatColor.AQUA+"List of People:");
				for (Client c : pl.api.getClients()) {
					// Get the client's channel	
					if(!c.getNickname().equals("TeamspeakIP")){		
						p.sendMessage("§2"+c.getNickname());
					}
				}
				prefixsend(p);		

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
 			.setHoverAsTooltip(ChatColor.translateAlternateColorCodes('&',  pl.getConfig().getString("messages.ip")))
 			.setClickAsSuggestCmd(ChatColor.translateAlternateColorCodes('&',  pl.getConfig().getString("messages.ip"))).save().send(p);
 		}   
 		p.sendMessage("");
 		prefixsend(p);	
 		
	}
	
	public void prefixsend(Player p){
		p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
	}

}
