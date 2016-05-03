package me.t0biii.ts.commands;

import java.net.ConnectException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import de.navo.jsonchatlib.JSONChatClickEventType;
import de.navo.jsonchatlib.JSONChatColor;
import de.navo.jsonchatlib.JSONChatExtra;
import de.navo.jsonchatlib.JSONChatFormat;
import de.navo.jsonchatlib.JSONChatHoverEventType;
import de.navo.jsonchatlib.JSONChatMessage;
import me.t0biii.ts.Main;
import me.t0biii.ts.Methods.TitleManager;

@SuppressWarnings("unused")
public class ts implements CommandExecutor{
	/**
	 *  tittle <player> title <Text>
     *  tittle <player> subtitle <Text>
	 */

	public static Main pl = Main.instance;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{	
		boolean error = false;
		final TS3Config config = new TS3Config();
		final TS3Query query = new TS3Query(config);
		
		if(sender instanceof Player)
		{
			final Player p = (Player) sender;
			World w = p.getWorld();
				
			if(args.length == 1)
            { 
			if(args[0].equalsIgnoreCase("reload"))
			{					
				//RELOAD COMMAND
				if(p.hasPermission("ts.reload"))
				{
				 pl.reloadConfig();
				 p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
				 p.sendMessage("");
       		     p.sendMessage(pl.getConfig().getString("messages.reload"));
       		     p.sendMessage("");
       		     p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");		  
				}else
				{
					p.sendMessage(pl.getConfig().getString("messages.no-permission"));
				}

				//HELP COMMAND
			}else if(args[0].equalsIgnoreCase("help")){
				if(p.hasPermission("ts.help") || p.isOp() ){
					 p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
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
					 JSONChatExtra extra111 = new JSONChatExtra(ChatColor.YELLOW+"/ts update" + ChatColor.GRAY +"  | AutoUpdate start", JSONChatColor.YELLOW, Arrays.asList(JSONChatFormat.BOLD));
					 extra111.setClickEvent(JSONChatClickEventType.SUGGEST_COMMAND, "/ts update");
					 extra111.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "/ts update");
					 message111.addExtra(extra111);
			         message111.sendToPlayer(p.getPlayer());
	               
	                 p.sendMessage("");
					 p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
				}else{
					tsipsend(p);
				}	 

             /**
              * UPDATE COMMAND    
              */
			}else if(args[0].equalsIgnoreCase("update")){
				//entfernt!
			}
			else if(args[0].equalsIgnoreCase("getip")){
				p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
				p.sendMessage("");
				
		        JSONChatMessage message = new JSONChatMessage("" , JSONChatColor.AQUA, null);
		        JSONChatExtra extra = new JSONChatExtra("Click This", JSONChatColor.BLUE, Arrays.asList(JSONChatFormat.BOLD));
		        extra.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, pl.getConfig().getString("messages.ip"));
		        extra.setClickEvent(JSONChatClickEventType.SUGGEST_COMMAND, pl.getConfig().getString("messages.ip"));
		        message.addExtra(extra);
		        message.sendToPlayer(p.getPlayer()); 
				
				p.sendMessage("");
				p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");	
				
			}else if(args[0].equalsIgnoreCase("list")){
				
				config.setHost(pl.getConfig().getString("ts3.ip"));
				config.setQueryPort(pl.getConfig().getInt("ts3.queryport"));
				config.setDebugLevel(Level.OFF);
				config.setLoginCredentials(pl.getConfig().getString("ts3.querylogin.name"), pl.getConfig().getString("ts3.querylogin.pw"));
				try{
				query.connect();
				}catch(Exception e){
					p.sendMessage(ChatColor.RED+pl.prefix+"Can´t connect to Teamspeak!");
				//	Bukkit.getLogger().info(pl.prefix+"Can´t connect to Teamspeak!");
					error = true;
				}
				final TS3Api api = query.getApi();
				if(!error){	
					try{
					api.selectVirtualServerByPort(pl.getConfig().getInt("ts3.port"));
					api.setNickname("TeamspeakIP");
					error = false;
					}catch(Exception e){
						p.sendMessage(ChatColor.RED+pl.prefix+"Can´t connect to Teamspeak!");
					}						
				}
				if(error){			
				}else{
					// Get all channels and map their channel IDs to them
				List<Channel> channels = api.getChannels();
				Map<Integer, Channel> channelMap = new HashMap<>(channels.size());
				for (Channel channel : channels) {
					channelMap.put(channel.getId(), channel);
				}
				// List all clients in the console
				p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
				p.sendMessage(ChatColor.AQUA+"Teamspeak: "+pl.getConfig().getString("ts3.ip")+":"+pl.getConfig().getString("ts3.port"));
				p.sendMessage(ChatColor.AQUA+"Online: §2"+ (api.getClients().size()- 1) +" of " +api.getHostInfo().getTotalMaxClients());
				p.sendMessage(ChatColor.AQUA+"List of People:");
				for (Client c : api.getClients()) {
					// Get the client's channel
					Channel channel = channelMap.get(c.getChannelId());
					if(c.getNickname().equals("TeamspeakIP")){		
					}else{
						p.sendMessage("§2"+c.getNickname());
					}
				}
				p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
				// We're done, disconnect!
				api.logout();
				query.exit();
				}				
			}	
            }else if(pl.getConfig().getBoolean("options.Titels")) {
            	
            	  String Pname = p.getName();
            	  TitleManager.sendTitles(p, pl.getConfig().getString("messages.ts3"), "", 20, 3*20, 20);
     
            }else 
            {
            	 tsipsend(p);
            } 
			return true;
			} else
			{
				sender.sendMessage(pl.getConfig().getString("messages.konsole"));	
			}
		return false;
	}

	private void tsipsend(Player p){
		p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
 		p.sendMessage("");
 		  
 	   	JSONChatMessage message = new JSONChatMessage("", null, null);  
        JSONChatExtra extra = new JSONChatExtra(pl.getConfig().getString("messages.ts3"), JSONChatColor.BLUE, Arrays.asList(JSONChatFormat.BOLD));
        extra.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, pl.getConfig().getString("messages.ip"));
        extra.setClickEvent(JSONChatClickEventType.SUGGEST_COMMAND, pl.getConfig().getString("messages.ip"));
        message.addExtra(extra);
        message.sendToPlayer(p.getPlayer());
        
 		p.sendMessage("");
 		p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
 		

 		
	}
	
		
}
