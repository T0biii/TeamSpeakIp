package de.t0biii.ts.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TsTapCompleter implements TabCompleter{
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){
		// Set Strings
		String rlperm = "ts.reload";
		String hperm = "ts.help";
		String uperm = "ts.update";
		String cperm = "ts.cache";
		String fperm = "ts.filter";
		String[] cache = new String[3];
		String [] cache2 = {"cache", "cache-on", "cache-off"};
		cache[0] = "cache";
		cache[1] = "cache-on";
		cache[2] = "cache-off";
		String rl = "rl";
		String help = "help";
		String update = "update";
		String rlfilter = "rl-filter";
		String getip = "getIP";
		String list = "list";
		// Create Second ArrayLists
		ArrayList<String> tsnames = new ArrayList<String>();
		ArrayList<String> tsnames2 = new ArrayList<String>();

		final Player p = (Player) sender;
		if (sender instanceof Player){
			if (p.isOp() || p.hasPermission(rlperm))
				tsnames.add(rl);
			if (p.isOp() || p.hasPermission(hperm))
				tsnames.add(help);
			if (p.isOp() || p.hasPermission(uperm))
				tsnames.add(update);
			if (p.isOp() || p.hasPermission(cperm)){
				tsnames.add(cache2[0]);
				tsnames.add(cache2[1]);
				tsnames.add(cache2[2]);
			}
			if (p.isOp() || p.hasPermission(fperm))
				tsnames.add(rlfilter);
			tsnames.add(getip);
			tsnames.add(list);
		}

		if (args.length == 1){
			if (!args[0].equals("")){
				for (String ts : tsnames){
					if (ts.toLowerCase().startsWith(args[0].toLowerCase())){
						if (p.hasPermission(fperm) && ts.equalsIgnoreCase(rlfilter)){
							tsnames2.add(ts);
						} else if (p.hasPermission(cperm) && (ts.equalsIgnoreCase(cache2[0])
								|| ts.equalsIgnoreCase(cache2[1]) || ts.equalsIgnoreCase(cache2[2]))){
							tsnames2.add(ts);
						} else if (p.hasPermission(uperm) && ts.equalsIgnoreCase(update)){
							tsnames2.add(ts);
						} else if (p.hasPermission(rlperm) && ts.equalsIgnoreCase(rl)){
							tsnames2.add(ts);
						} else if (p.hasPermission(hperm) && ts.equalsIgnoreCase(help)){
							tsnames2.add(ts);
						} else{
							tsnames2.add(ts);
						}
					}
				}
			} else{
				return tsnames;
			}
			return tsnames2;
		} else{
			tsnames.clear();
			return tsnames;
		}
	}
}