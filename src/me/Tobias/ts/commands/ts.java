package me.Tobias.ts.commands;

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

import me.Tobias.ts.Ts;

public class ts implements CommandExecutor{

	
	

	public static Ts pl = Ts.instance;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
            if(args.length == 1)
            { 
			if(args[0].equalsIgnoreCase("reload"))
			{
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
			}
			
	
            }else
        {
		  World w = p.getWorld();
		  p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
		  p.sendMessage("");
		  p.sendMessage(pl.getConfig().getString("messages.ts3"));
		  p.sendMessage("");
		  p.sendMessage(ChatColor.YELLOW+"[]================"+ChatColor.GOLD +" TeamSpeak " +ChatColor.YELLOW+"===============[]");
		  if(pl.getConfig().getBoolean("options.effects"))
		  {
      	  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 35, 100));
      	  p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 10));
      	  p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 40, 100));
      	  w.playEffect(p.getPlayer().getLocation(), Effect.POTION_BREAK, 10);
		  }
      	  if(pl.getConfig().getBoolean("options.sounds"))
      	  {
          w.playSound(p.getPlayer().getLocation(), Sound.NOTE_PIANO, 25,1);
      	  w.playSound(p.getPlayer().getLocation(), Sound.ARROW_HIT, 25,1);
      	  }		  
          
          }
          
			return true;
			} else
			{
				sender.sendMessage(pl.getConfig().getString("messages.konsole"));

			}
		return false;
	}

		
}
