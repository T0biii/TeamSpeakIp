package me.t0biii.ts.commands;

import java.util.ArrayList;
import java.util.List;



import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import me.t0biii.ts.Main;

public class TsTapCompleter implements TabCompleter {
	
	@SuppressWarnings("unused")
	private Main plugin;
	
	public TsTapCompleter(Main main){
		this.plugin = main;
	}
	

	

 
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> tsnames = new ArrayList<String>();
		
		tsnames.add("help");
		tsnames.add("getIP");
		tsnames.add("list");
		tsnames.add("reload");
		tsnames.add("update");
		
		if(args.length == 1){
			return tsnames;
		}else{
	      tsnames.clear();
	      return tsnames;
		}
	}

}
