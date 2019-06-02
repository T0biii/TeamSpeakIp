package de.t0biii.ts.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import de.t0biii.ts.TeamSpeak;
import de.t0biii.ts.methods.HelpMessages;
import de.t0biii.ts.methods.files.Messages;

public class Ts implements CommandExecutor {

  private static TeamSpeak pl;

  public Ts(TeamSpeak pl) {
    Ts.pl = pl;
  }

  static String tsip = "";
  static YamlConfiguration cfg = Messages.getcfg();

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    
    if (pl.getConfig().getInt("ts3.port") == 9987) {
      tsip = pl.getConfig().getString("ts3.ip");
    } else {
      tsip = pl.getConfig().getString("ts3.ip") + ":" + pl.getConfig().getString("ts3.port");
    }
    
    if (sender instanceof Player) {
      final Player p = (Player) sender;
      if (args.length == 1) {
        // RELOAD COMMAND
        if (args[0].equalsIgnoreCase("rl")) {
          HelpMessages.sendReload(p, pl);
          // HELP COMMAND
        } else if (args[0].equalsIgnoreCase("help")) {
          HelpMessages.sendHelp(p, tsip);
          // UPDATE COMMAND
        } else if (args[0].equalsIgnoreCase("update")) {
          HelpMessages.sendUpdate(p, pl);
          // GET IP COMMAND
        } else if (args[0].equalsIgnoreCase("getip")) {
          HelpMessages.sendGetIP(p, tsip);
          // RL-Filter COMMAND
        } else if (args[0].equalsIgnoreCase("rl-filter")) {
          HelpMessages.sendReloadFiler(p, pl, tsip);
          // cache-off COMMAND
        } else if (args[0].equalsIgnoreCase("cache-off")) {
          HelpMessages.sendCacheOFF(p, pl, tsip);
          // cache-on COMMAND
        } else if (args[0].equalsIgnoreCase("cache-on")) {
          HelpMessages.sendCacheON(p, pl, tsip);
          // cache COMMAND
        } else if (args[0].equalsIgnoreCase("cache")) {
          HelpMessages.sendCache(p, pl);
          // List COMMAND
        } else if (args[0].equalsIgnoreCase("list")) {
          HelpMessages.sendList(p, pl, tsip);
          // Not Existing Command
        } else {
          HelpMessages.tsipsend(p, tsip);          
        }
        
        
        
        // if 1 < Args Lenght > 1
      } else {
        HelpMessages.tsipsend(p, tsip);
      }
      return true;
    } else {
      sender.sendMessage(cfg.getString("messages.konsole"));
    }
    return false;
  }

}
