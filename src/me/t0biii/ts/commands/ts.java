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
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import de.navo.jsonchatlib.JSONChatClickEventType;
import de.navo.jsonchatlib.JSONChatColor;
import de.navo.jsonchatlib.JSONChatExtra;
import de.navo.jsonchatlib.JSONChatFormat;
import de.navo.jsonchatlib.JSONChatHoverEventType;
import de.navo.jsonchatlib.JSONChatMessage;
import me.t0biii.ts.Main;
import me.t0biii.ts.Methods.TSCacher;
import me.t0biii.ts.Methods.Updater;
import me.t0biii.ts.Methods.Updater.UpdateResult;


public class ts implements CommandExecutor{

	public static Main pl = Main.instance;
	
	@SuppressWarnings("unused")
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
					 JSONChatMessage message = new JSONChatMessage("", null, null);
					 JSONChatExtra extra = new JSONChatExtra(ChatColor.YELLOW+"/ts" + ChatColor.GRAY +"               | The TS IP appears", JSONChatColor.YELLOW, Arrays.asList(JSONChatFormat.BOLD));
					 extra.setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/ts");
					 extra.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "/ts");
					 message.addExtra(extra);
			         message.sendToPlayer(p.getPlayer());
			       //p.sendMessage(ChatColor.YELLOW+"/ts help" + ChatColor.GRAY +"      | Shows you this Page");
			         JSONChatMessage message1 = new JSONChatMessage("", null, null);
					 JSONChatExtra extra1 = new JSONChatExtra(ChatColor.YELLOW+"/ts help" + ChatColor.GRAY +"      | Shows you this Page", JSONChatColor.YELLOW, Arrays.asList(JSONChatFormat.BOLD));
					 extra1.setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/ts help");
					 extra1.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "/ts help");
					 message1.addExtra(extra1);
			         message1.sendToPlayer(p.getPlayer());         
				       //p.sendeMessage(ChatColor.YELLOW+"/ts getIP"+ ChatColor.GRAY+"    |  KlickTOgetIP");
					 JSONChatMessage message2 = new JSONChatMessage("", null, null);
					 JSONChatExtra extra2 = new JSONChatExtra(ChatColor.YELLOW+"/ts getIP" + ChatColor.GRAY +"      | This shows the ip in the Chat", JSONChatColor.YELLOW, Arrays.asList(JSONChatFormat.BOLD));
					 extra2.setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/ts getIP");
					 extra2.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "/ts getIP");
					 message2.addExtra(extra2);
				     message2.sendToPlayer(p.getPlayer());
	               //p.sendMessage(ChatColor.YELLOW+"/ts reload" + ChatColor.GRAY +"  | Config Reload");
			         JSONChatMessage message11 = new JSONChatMessage("", null, null);
					 JSONChatExtra extra11 = new JSONChatExtra(ChatColor.YELLOW+"/ts reload" + ChatColor.GRAY +"  | Config Reload", JSONChatColor.YELLOW, Arrays.asList(JSONChatFormat.BOLD));
					 extra11.setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/ts reload");
					 extra11.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "/ts reload");
					 message11.addExtra(extra11);
			         message11.sendToPlayer(p.getPlayer());
	               //p.sendMessage(ChatColor.YELLOW+"/ts update" + ChatColor.GRAY +"  | AutoUpdate start");
			         JSONChatMessage message111 = new JSONChatMessage("", null, null);
					 JSONChatExtra extra111 = new JSONChatExtra(ChatColor.YELLOW+"/ts update" + ChatColor.GRAY +"  | Update Link", JSONChatColor.YELLOW, Arrays.asList(JSONChatFormat.BOLD));
					 extra111.setClickEvent(JSONChatClickEventType.SUGGEST_COMMAND, "/ts update");
					 extra111.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "/ts update");
					 message111.addExtra(extra111);
			         message111.sendToPlayer(p.getPlayer());
	               //p.sendMessage(ChatColor.YELLOW+"/ts List" + ChatColor.Gray + "  | Online List");
			         JSONChatMessage message4 = new JSONChatMessage("", null, null);
			         JSONChatExtra extra4 = new JSONChatExtra(ChatColor.YELLOW+"/ts list"+ ChatColor.GRAY +"  | Online List" , JSONChatColor.YELLOW, Arrays.asList(JSONChatFormat.BOLD));
			         extra4.setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/ts list");
			         extra4.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "/ts list");
			         message4.addExtra(extra4);
			         message4.sendToPlayer(p.getPlayer());

			         
	                 p.sendMessage("");
	                 prefixsend(p);	
				}else{
					tsipsend(p);
				}	  
             /**
              * UPDATE COMMAND    
              */
			}else if(args[0].equalsIgnoreCase("update")){
				
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
			else if(args[0].equalsIgnoreCase("getip")){
				prefixsend(p);	
				p.sendMessage("");
				
		        JSONChatMessage message = new JSONChatMessage("" , JSONChatColor.AQUA, null);
		        JSONChatExtra extra = new JSONChatExtra("Click This", JSONChatColor.BLUE, Arrays.asList(JSONChatFormat.BOLD));
		        extra.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT,ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("messages.ip")));
		        extra.setClickEvent(JSONChatClickEventType.SUGGEST_COMMAND,ChatColor.translateAlternateColorCodes('&',  pl.getConfig().getString("messages.ip")));
		        message.addExtra(extra);
		        message.sendToPlayer(p.getPlayer()); 
				
				p.sendMessage("");
				prefixsend(p);		
				
			}else if(args[0].equalsIgnoreCase("chache")){
				TSCacher.tsCache();
				p.sendMessage(""+TSCacher.Clients +"-" +TSCacher.MaxClients);
				
			}else if(args[0].equalsIgnoreCase("list")){
			
				if(pl.error){
					p.sendMessage("§cTeamspeak is unreachable!");
				}else{
				// Get all channels and map their channel IDs to them
					try{
							
				List<Channel> channels = pl.api.getChannels();
				Map<Integer, Channel> channelMap = new HashMap<>(channels.size());
				for (Channel channel : channels) {
					channelMap.put(channel.getId(), channel);
				}
				// List all clients in the console
				prefixsend(p);	
				p.sendMessage(ChatColor.AQUA+"Teamspeak: "+pl.getConfig().getString("messages.ip"));
				p.sendMessage(ChatColor.AQUA+"Online: §2"+ (pl.api.getClients().size()- 1) +" of " + pl.api.getHostInfo().getTotalMaxClients());
				p.sendMessage(ChatColor.AQUA+"List of People:");
				for (Client c : pl.api.getClients()) {
					// Get the client's channel
					Channel channel = channelMap.get(c.getChannelId());
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
 		  
 	   	JSONChatMessage message = new JSONChatMessage("", null, null);  
        JSONChatExtra extra = new JSONChatExtra(ChatColor.translateAlternateColorCodes('&',  pl.getConfig().getString("messages.ts3")), JSONChatColor.BLUE, Arrays.asList(JSONChatFormat.BOLD));
        extra.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, ChatColor.translateAlternateColorCodes('&',  pl.getConfig().getString("messages.ip")));
        extra.setClickEvent(JSONChatClickEventType.SUGGEST_COMMAND, ChatColor.translateAlternateColorCodes('&',  pl.getConfig().getString("messages.ip")));
        message.addExtra(extra);
        message.sendToPlayer(p.getPlayer());
        
      
        
 		p.sendMessage("");
 		prefixsend(p);	
 		
	}
	
	public void prefixsend(Player p){
		p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
	}

}
