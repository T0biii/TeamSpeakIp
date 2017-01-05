package me.t0biii.ts.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.t0biii.ts.TeamSpeak;

public class TsTapCompleter implements TabCompleter {
	
	@SuppressWarnings("unused")
	private TeamSpeak plugin;
	
	public TsTapCompleter(TeamSpeak main){
		this.plugin = main;
	}
	

	

 
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> tsnames = new ArrayList<String>();
		
		if(sender instanceof Player){
			final Player p = (Player) sender;
			if(p.isOp() || p.hasPermission("ts.reload"))
			tsnames.add("rl");
			
			if(p.isOp() || p.hasPermission("ts.help"))
			tsnames.add("help");
			
			if(p.isOp() || p.hasPermission("ts.update"))
			tsnames.add("update");
			
			if(p.isOp() || p.hasPermission("ts.cache"))
			tsnames.add("cache");
			
			tsnames.add("add-filter");
			tsnames.add("rl-filter");
			
			
			tsnames.add("getIP");
			tsnames.add("list");
		}
		
		if(args.length == 1){
			return tsnames;
		}else{
	      tsnames.clear();
	      return tsnames;
		}
	}

}
