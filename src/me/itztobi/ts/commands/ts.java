package me.itztobi.ts.commands;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.spigotmc.ProtocolInjector.PacketTitle;
import org.spigotmc.ProtocolInjector.PacketTitle.Action;

import me.itztobi.ts.Main;
import me.itztobi.ts.Methods.ParticleEffects;

@SuppressWarnings("unused")
public class ts implements CommandExecutor{

	private boolean Titels = true;
	
	

	public static Main pl = Main.instance;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Player cp = (Player) sender;
		final CraftPlayer cPlayer = (CraftPlayer) cp;
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			World w = p.getWorld();
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
			
	
            }else if(cPlayer.getHandle().playerConnection.networkManager.getVersion() >= 47) {
            	
            	if(pl.getConfig().getBoolean("options.Titels")){
            	//1.8
            	IChatBaseComponent title_text = ChatSerializer.a("{text:\""+pl.getConfig().getString("messages.ts3")+"\"}");
                PacketTitle title = new PacketTitle(Action.TITLE, title_text);
        	    cPlayer.getHandle().playerConnection.sendPacket(title);

        	Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){

				@Override
				public void run() {
		             cPlayer.getHandle().playerConnection.sendPacket(new PacketTitle(Action.RESET));
					
				}
        		
        	}, 5*20L);
            	}else {//if false
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
           		  
                 	  //Effects
                 	  if(pl.getConfig().getBoolean("effect.AIR_BUBBLE")){
                 	      ParticleEffects.sendToPlayer(ParticleEffects.AIR_BUBBLE, p, p.getLocation(), 0, 3, 0, 2, 3); 
                 	   // ParticleEffects.sendToPlayer(effect, player, location, offsetX, offsetY, offsetZ, speed, count)
                 	  }
                 	  
                 	  if(pl.getConfig().getBoolean("effect.ANGRY_VILLAGER")){
                         ParticleEffects.sendToPlayer(ParticleEffects.ANGRY_VILLAGER, p, p.getLocation(), 0, 3, 0, 2, 3); }
                   
                 	  if(pl.getConfig().getBoolean("effect.CLOUD")){
                         ParticleEffects.sendToPlayer(ParticleEffects.CLOUD, p, p.getLocation(), 0, 3, 0, 2, 3); }
                 	  
                 	  if(pl.getConfig().getBoolean("effect.CRITICAL_HIT")){
                         ParticleEffects.sendToPlayer(ParticleEffects.CRITICAL_HIT, p, p.getLocation(), 0, 3, 0, 2, 3); }      	
                 	  
                 	  if(pl.getConfig().getBoolean("effect.DEPTH_SUSPEND")){
                         ParticleEffects.sendToPlayer(ParticleEffects.DEPTH_SUSPEND, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.DRIP_LAVA")){
                         ParticleEffects.sendToPlayer(ParticleEffects.DRIP_LAVA, p, p.getLocation(), 0, 3, 0, 2, 3); }
                 	  
                 	  if(pl.getConfig().getBoolean("effect.DRIP_WATER")){
                         ParticleEffects.sendToPlayer(ParticleEffects.DRIP_WATER, p, p.getLocation(), 0, 3, 0, 2, 3); }
                 	  
                 	  if(pl.getConfig().getBoolean("effect.ENCHANTMENT_TABLE")){
                         ParticleEffects.sendToPlayer(ParticleEffects.ENCHANTMENT_TABLE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.ENDER")){
                         ParticleEffects.sendToPlayer(ParticleEffects.ENDER, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.EXPLODE")){
                         ParticleEffects.sendToPlayer(ParticleEffects.EXPLODE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.FIRE")){
                         ParticleEffects.sendToPlayer(ParticleEffects.FIRE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.FIREWORK_SPARK")){
                         ParticleEffects.sendToPlayer(ParticleEffects.FIREWORK_SPARK, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.FOOTSTEP")){
                         ParticleEffects.sendToPlayer(ParticleEffects.FOOTSTEP, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.GREEN_SPARKLE")){
                         ParticleEffects.sendToPlayer(ParticleEffects.GREEN_SPARKLE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.HEART")){
                         ParticleEffects.sendToPlayer(ParticleEffects.HEART, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.HUGE_EXPLODE")){
                         ParticleEffects.sendToPlayer(ParticleEffects.HUGE_EXPLODE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.ICONCRACK")){
                         ParticleEffects.sendToPlayer(ParticleEffects.ICONCRACK, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.INSTANT_SPELL")){
                         ParticleEffects.sendToPlayer(ParticleEffects.INSTANT_SPELL, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.LARGE_EXPLODE")){
                         ParticleEffects.sendToPlayer(ParticleEffects.LARGE_EXPLODE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.LARGE_SMOKE")){
                         ParticleEffects.sendToPlayer(ParticleEffects.LARGE_SMOKE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.LAVA_SPARK")){
                         ParticleEffects.sendToPlayer(ParticleEffects.LAVA_SPARK, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.MAGIC_CRITICAL_HIT")){
                         ParticleEffects.sendToPlayer(ParticleEffects.MAGIC_CRITICAL_HIT, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.MOB_SPELL")){
                         ParticleEffects.sendToPlayer(ParticleEffects.MOB_SPELL, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.MOB_SPELL_AMBIENT")){
                         ParticleEffects.sendToPlayer(ParticleEffects.MOB_SPELL_AMBIENT, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	  
                 	  if(pl.getConfig().getBoolean("effect.NOTE_BLOCK")){
                         ParticleEffects.sendToPlayer(ParticleEffects.NOTE_BLOCK, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
                 	     	  
                 	  if(pl.getConfig().getBoolean("effect.REDSTONE_DUST")){
                 	      ParticleEffects.sendToPlayer(ParticleEffects.REDSTONE_DUST, p, p.getLocation(), 0, 3, 0, 2, 3); }
           	     	  
           	      if(pl.getConfig().getBoolean("effect.SLIME")){
           	          ParticleEffects.sendToPlayer(ParticleEffects.SLIME, p, p.getLocation(), 0, 3, 0, 2, 3); }
              	  
                     if(pl.getConfig().getBoolean("effect.SNOW_DIG")){  
                   	 ParticleEffects.sendToPlayer(ParticleEffects.SNOW_DIG, p, p.getLocation(), 0, 3, 0, 2, 3); }
                   
                     if(pl.getConfig().getBoolean("effect.SNOWBALL_HIT")){
                        ParticleEffects.sendToPlayer(ParticleEffects.SNOWBALL_HIT, p, p.getLocation(), 0, 3, 0, 2, 3); }
                   
                     if(pl.getConfig().getBoolean("effect.SPLASH")){
                        ParticleEffects.sendToPlayer(ParticleEffects.SPLASH, p, p.getLocation(), 0, 3, 0, 2, 3); }
                   
                     if(pl.getConfig().getBoolean("effect.SUSPEND")){
                        ParticleEffects.sendToPlayer(ParticleEffects.SUSPEND, p, p.getLocation(), 0, 3, 0, 2, 3); }
                   
                     if(pl.getConfig().getBoolean("effect.TILECRACK")){
                        ParticleEffects.sendToPlayer(ParticleEffects.TILECRACK, p, p.getLocation(), 0, 3, 0, 2, 3); }
                   
                     if(pl.getConfig().getBoolean("effect.TOWN_AURA")){
                        ParticleEffects.sendToPlayer(ParticleEffects.TOWN_AURA, p, p.getLocation(), 0, 3, 0, 2, 3); }
           	      	  
                 	  //Sounds
                 	  if(pl.getConfig().getBoolean("options.sounds"))
                 	  {
                     w.playSound(p.getPlayer().getLocation(), Sound.NOTE_PIANO, 25,1);
                 	  w.playSound(p.getPlayer().getLocation(), Sound.ARROW_HIT, 25,1);
                 	  }
            	}
         
        //Funktioniert mit der Spigot ab Build #1647
            	
            }else 
            { //Unter 1.8
		  
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
		  
      	  //Effects
      	  if(pl.getConfig().getBoolean("effect.AIR_BUBBLE")){
      	      ParticleEffects.sendToPlayer(ParticleEffects.AIR_BUBBLE, p, p.getLocation(), 0, 3, 0, 2, 3); 
      	   // ParticleEffects.sendToPlayer(effect, player, location, offsetX, offsetY, offsetZ, speed, count)
      	  }
      	  
      	  if(pl.getConfig().getBoolean("effect.ANGRY_VILLAGER")){
              ParticleEffects.sendToPlayer(ParticleEffects.ANGRY_VILLAGER, p, p.getLocation(), 0, 3, 0, 2, 3); }
        
      	  if(pl.getConfig().getBoolean("effect.CLOUD")){
              ParticleEffects.sendToPlayer(ParticleEffects.CLOUD, p, p.getLocation(), 0, 3, 0, 2, 3); }
      	  
      	  if(pl.getConfig().getBoolean("effect.CRITICAL_HIT")){
              ParticleEffects.sendToPlayer(ParticleEffects.CRITICAL_HIT, p, p.getLocation(), 0, 3, 0, 2, 3); }      	
      	  
      	  if(pl.getConfig().getBoolean("effect.DEPTH_SUSPEND")){
              ParticleEffects.sendToPlayer(ParticleEffects.DEPTH_SUSPEND, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.DRIP_LAVA")){
              ParticleEffects.sendToPlayer(ParticleEffects.DRIP_LAVA, p, p.getLocation(), 0, 3, 0, 2, 3); }
      	  
      	  if(pl.getConfig().getBoolean("effect.DRIP_WATER")){
              ParticleEffects.sendToPlayer(ParticleEffects.DRIP_WATER, p, p.getLocation(), 0, 3, 0, 2, 3); }
      	  
      	  if(pl.getConfig().getBoolean("effect.ENCHANTMENT_TABLE")){
              ParticleEffects.sendToPlayer(ParticleEffects.ENCHANTMENT_TABLE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.ENDER")){
              ParticleEffects.sendToPlayer(ParticleEffects.ENDER, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.EXPLODE")){
              ParticleEffects.sendToPlayer(ParticleEffects.EXPLODE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.FIRE")){
              ParticleEffects.sendToPlayer(ParticleEffects.FIRE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.FIREWORK_SPARK")){
              ParticleEffects.sendToPlayer(ParticleEffects.FIREWORK_SPARK, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.FOOTSTEP")){
              ParticleEffects.sendToPlayer(ParticleEffects.FOOTSTEP, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.GREEN_SPARKLE")){
              ParticleEffects.sendToPlayer(ParticleEffects.GREEN_SPARKLE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.HEART")){
              ParticleEffects.sendToPlayer(ParticleEffects.HEART, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.HUGE_EXPLODE")){
              ParticleEffects.sendToPlayer(ParticleEffects.HUGE_EXPLODE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.ICONCRACK")){
              ParticleEffects.sendToPlayer(ParticleEffects.ICONCRACK, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.INSTANT_SPELL")){
              ParticleEffects.sendToPlayer(ParticleEffects.INSTANT_SPELL, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.LARGE_EXPLODE")){
              ParticleEffects.sendToPlayer(ParticleEffects.LARGE_EXPLODE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.LARGE_SMOKE")){
              ParticleEffects.sendToPlayer(ParticleEffects.LARGE_SMOKE, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.LAVA_SPARK")){
              ParticleEffects.sendToPlayer(ParticleEffects.LAVA_SPARK, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.MAGIC_CRITICAL_HIT")){
              ParticleEffects.sendToPlayer(ParticleEffects.MAGIC_CRITICAL_HIT, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.MOB_SPELL")){
              ParticleEffects.sendToPlayer(ParticleEffects.MOB_SPELL, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.MOB_SPELL_AMBIENT")){
              ParticleEffects.sendToPlayer(ParticleEffects.MOB_SPELL_AMBIENT, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	  
      	  if(pl.getConfig().getBoolean("effect.NOTE_BLOCK")){
              ParticleEffects.sendToPlayer(ParticleEffects.NOTE_BLOCK, p, p.getLocation(), 0, 3, 0, 2, 3); }      	  
      	     	  
      	  if(pl.getConfig().getBoolean("effect.REDSTONE_DUST")){
      	      ParticleEffects.sendToPlayer(ParticleEffects.REDSTONE_DUST, p, p.getLocation(), 0, 3, 0, 2, 3); }
	     	  
	      if(pl.getConfig().getBoolean("effect.SLIME")){
	          ParticleEffects.sendToPlayer(ParticleEffects.SLIME, p, p.getLocation(), 0, 3, 0, 2, 3); }
   	  
          if(pl.getConfig().getBoolean("effect.SNOW_DIG")){  
        	 ParticleEffects.sendToPlayer(ParticleEffects.SNOW_DIG, p, p.getLocation(), 0, 3, 0, 2, 3); }
        
          if(pl.getConfig().getBoolean("effect.SNOWBALL_HIT")){
             ParticleEffects.sendToPlayer(ParticleEffects.SNOWBALL_HIT, p, p.getLocation(), 0, 3, 0, 2, 3); }
        
          if(pl.getConfig().getBoolean("effect.SPLASH")){
             ParticleEffects.sendToPlayer(ParticleEffects.SPLASH, p, p.getLocation(), 0, 3, 0, 2, 3); }
        
          if(pl.getConfig().getBoolean("effect.SUSPEND")){
             ParticleEffects.sendToPlayer(ParticleEffects.SUSPEND, p, p.getLocation(), 0, 3, 0, 2, 3); }
        
          if(pl.getConfig().getBoolean("effect.TILECRACK")){
             ParticleEffects.sendToPlayer(ParticleEffects.TILECRACK, p, p.getLocation(), 0, 3, 0, 2, 3); }
        
          if(pl.getConfig().getBoolean("effect.TOWN_AURA")){
             ParticleEffects.sendToPlayer(ParticleEffects.TOWN_AURA, p, p.getLocation(), 0, 3, 0, 2, 3); }
	      	  
      	  //Sounds
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
