package me.t0biii.ts.Methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.t0biii.ts.commands.Ts;

/**
* Created by Tobias on 06.01.2017.
*/
public class SendHelp {
	
	public void sendHelp(Player p){
		if(p.hasPermission("ts.help") || p.isOp() ){
			 Ts.prefixsend(p);	
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
		  //   p.sendMessage(ChatColor.YELLOW+"/ts rl-filter" + ChatColor.GRAY + "  | Reload the Filter list");
		     {
		    	 JsonMessage jm = new JsonMessage();
		    	 jm.append(ChatColor.YELLOW+"/ts rl-filter" + ChatColor.GRAY + "  | Reload the Filter list")
		    	 .setClickAsExecuteCmd("/ts rl-filter")
		    	 .setHoverAsTooltip("/ts rl-filter").save().send(p);
		     }
		   //  p.sendMessage(ChatColor.YELLOW+"/ts cache-off" + ChatColor.GRAY + "   | Disable the cache");
		     {
		    	 JsonMessage jm = new JsonMessage();
		    	 jm.append(ChatColor.YELLOW+"/ts cache-off" + ChatColor.GRAY + "   | Disable the cache")
		    	 .setClickAsExecuteCmd("/ts cache-off")
		    	 .setHoverAsTooltip("/ts cache-off").save().send(p); 
		     }
		  //   p.sendMessage(ChatColor.YELLOW+"/ts cache-on" + ChatColor.GRAY + "   | Activated the cache");
		     {
		    	 JsonMessage jm = new JsonMessage();
		    	 jm.append(ChatColor.YELLOW+"/ts cache-on" + ChatColor.GRAY + "   | Activated the cache")
		    	 .setClickAsExecuteCmd("/ts cache-on")
		    	 .setHoverAsTooltip("/ts cache-on").save().send(p);
		     }
            p.sendMessage("");
            Ts.prefixsend(p);	
		}else{
			Ts.tsipsend(p);
		}	  
	}
}