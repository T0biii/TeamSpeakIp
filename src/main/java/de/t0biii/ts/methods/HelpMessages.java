package de.t0biii.ts.methods;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.t0biii.ts.TeamSpeak;
import de.t0biii.ts.methods.files.DBManager;
import de.t0biii.ts.methods.files.Filter;
import de.t0biii.ts.methods.files.Messages;
import net.gravitydevelopment.updater.Updater.UpdateResult;

/**
 * Created by T0biii on 21.07.2017.
 */
public class HelpMessages {

  private static final YamlConfiguration cfg = Messages.getcfg();

  public static void sendReload(Player p, TeamSpeak pl) {
    if (p.hasPermission("ts.reload")) {
      pl.reloadConfig();
      prefixsend(p);
      p.sendMessage("");
      p.sendMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.reload")));
      p.sendMessage("");
      prefixsend(p);
    } else {
      p.sendMessage(
          ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.no-permission")));
    }
  }

  public static void sendUpdate(Player p, TeamSpeak pl) {
    String updateSite = pl.getDescription().getWebsite();
    if (p.hasPermission("ts.update") || p.isOp()) {
      if (pl.updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
        prefixsend(p);
        p.sendMessage("");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',
            "&4&l" + cfg.getString("messages.update-info")));
        p.sendMessage("&2Download Link:");
        p.sendMessage(ChatColor.BLUE + pl.updater.getLatestFileLink());
        p.sendMessage("");
        prefixsend(p);
      } else if (pl.updater.getResult() != UpdateResult.UPDATE_AVAILABLE) {
        prefixsend(p);
        p.sendMessage("");
        JsonMessage jm = new JsonMessage();
        jm.append(ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.no-update")))
            .setClickAsURL(updateSite).setHoverAsTooltip(updateSite).save().send(p);
        p.sendMessage("");
        prefixsend(p);
      }
    }
  }

  public static void sendGetIP(Player p, String tsip) {
    prefixsend(p);
    p.sendMessage("");
    {
      JsonMessage jm = new JsonMessage();
      jm.append(ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.getIP")))
          .setClickAsSuggestCmd(ChatColor.translateAlternateColorCodes('&', tsip))
          .setHoverAsTooltip(ChatColor.translateAlternateColorCodes('&', tsip)).save().send(p);
    }
    p.sendMessage("");
    prefixsend(p);
  }

  public static void sendReloadFiler(Player p, TeamSpeak pl, String tsip) {
    if (p.isOp() || p.hasPermission("ts.filter")) {
      pl.fi.loadFilter();
      prefixsend(p);
      p.sendMessage("");
      p.sendMessage(
          ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.reloadfilter")));
      p.sendMessage("");
      prefixsend(p);
    } else {
      tsipsend(p, tsip);
    }
  }

  public static void sendCacheOFF(Player p, TeamSpeak pl, String tsip) {
    if (p.isOp() || p.hasPermission("ts.cache")) {
      if (!pl.getConfig().getBoolean("options.realtime.activated")) {
        pl.getConfig().set("options.realtime.activated", true);
        pl.saveConfig();
        prefixsend(p);
        p.sendMessage("");
        p.sendMessage(ChatColor.DARK_AQUA + "Live data "+ ChatColor.DARK_GREEN +"activated.");
        p.sendMessage(ChatColor.DARK_AQUA + "Cache "+ ChatColor.RED +"disabled.");
        p.sendMessage("");
        prefixsend(p);
      } else {
        prefixsend(p);
        p.sendMessage("");
        p.sendMessage(ChatColor.DARK_AQUA + "Live data is already "+ ChatColor.DARK_GREEN +"activated");
        p.sendMessage("");
        prefixsend(p);
      }
    } else {
      tsipsend(p, tsip);
    }
  }

  public static void sendCacheON(Player p, TeamSpeak pl, String tsip) {
    if (p.isOp() || p.hasPermission("ts.cache")) {
      if (pl.getConfig().getBoolean("options.realtime.activated")) {
        pl.getConfig().set("options.realtime.activated", false);
        pl.saveConfig();
        prefixsend(p);
        p.sendMessage("");
        p.sendMessage(ChatColor.DARK_AQUA + "Live data " + ChatColor.RED + "disabled.");
        p.sendMessage(ChatColor.DARK_AQUA+ "Cache " + ChatColor.DARK_GREEN + "activated.");
        p.sendMessage("");
        prefixsend(p);
      } else {
        prefixsend(p);
        p.sendMessage("");
        p.sendMessage(ChatColor.DARK_AQUA + "Live data is already "+ ChatColor.RED +"disabled.s");
        p.sendMessage("");
        prefixsend(p);
      }
    } else {
      tsipsend(p, tsip);
    }
  }

  public static void sendCache(Player p, TeamSpeak pl) {
    if (p.isOp() || p.hasPermission("ts.cache")) {
      pl.dbupdate();
      prefixsend(p);
      {
        JsonMessage jm = new JsonMessage();
        jm.append(ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.cachenew")))
            .save().send(p);
      }
      prefixsend(p);
    }
  }

  public static void sendList(Player p, TeamSpeak pl, String tsip) {
    if (pl.error) {
      p.sendMessage(ChatColor.RED + "Teamspeak is unreachable!");
    } else {
      try {
        // Get Values from Database
        YamlConfiguration filtercfg = Filter.getcfg();
        int anzahl = DBManager.getInt("min");
        int max = DBManager.getInt("max");
        List<String> cachelist = DBManager.getArray();
        List<String> filter = filtercfg.getStringList("ignore");

        if (!pl.getConfig().getBoolean("options.realtime.activated")) {
          prefixsend(p);
          p.sendMessage(ChatColor.AQUA + "Teamspeak: " + tsip + ChatColor.RED + " Cached");
          p.sendMessage(ChatColor.AQUA + "Online: " + ChatColor.DARK_GREEN + (anzahl) + " of " + max);
          p.sendMessage(ChatColor.AQUA + "List of People: ");
          for (String Users : cachelist) {
            if (!filter.contains(Users)) {
              p.sendMessage(ChatColor.DARK_GREEN + Users);
            }
          }
          prefixsend(p);
        } else {
          prefixsend(p);
          p.sendMessage(ChatColor.AQUA + "Teamspeak: " + tsip + ChatColor.DARK_GREEN + " Realtime");
          p.sendMessage(ChatColor.AQUA + "Online: " + ChatColor.DARK_GREEN + (pl.api.getClients().size()) + " of "
              + pl.api.getHostInfo().getTotalMaxClients());
          p.sendMessage(ChatColor.AQUA + "List of People:");
          for (Client c : pl.api.getClients()) {
            if (!filter.contains(c.getNickname())) {
              p.sendMessage(ChatColor.DARK_GREEN + c.getNickname());
            }
          }
          prefixsend(p);
        }
      } catch (Exception e) {
        p.sendMessage(ChatColor.AQUA + "Online: "+ ChatColor.DARK_GREEN +"- of -");
        p.sendMessage(ChatColor.AQUA + "List of People:");
        p.sendMessage(ChatColor.RED + "Not enough Teamspeak3 permissions");
        prefixsend(p);
      }
    }
  }

  public static void sendHelp(Player p, String tsip) {
    if (p.hasPermission("ts.help") || p.isOp()) {
      prefixsend(p);
      p.sendMessage("");
      int tabSize = 15;
      // p.sendMessage(ChatColor.YELLOW+"/ts" + ChatColor.GRAY +" | The TS
      // IP appears");
      {
        JsonMessage jm = new JsonMessage();
        jm.append(
            ChatColor.YELLOW + Tab.fFS("/ts", tabSize) + ChatColor.GRAY + "| The TS IP appears")
            .setHoverAsTooltip("/ts").setClickAsExecuteCmd("/ts").save().send(p);
      }
      // p.sendMessage(ChatColor.YELLOW+"/ts help" + ChatColor.GRAY +" |
      // Shows you this Page");
      {
        JsonMessage jm = new JsonMessage();
        jm.append(ChatColor.YELLOW + Tab.fFS("/ts help", tabSize) + ChatColor.GRAY
            + "| Shows you this Page").setHoverAsTooltip("/ts help")
            .setClickAsExecuteCmd("/ts help").save().send(p);
      }
      // p.sendeMessage(ChatColor.YELLOW+"/ts getIP"+ ChatColor.GRAY+" |
      // This shows the IP in the Chat");
      {
        JsonMessage jm = new JsonMessage();
        jm.append(ChatColor.YELLOW + Tab.fFS("/ts getIP", tabSize) + ChatColor.GRAY
            + "| This shows the IP in the Chat").setHoverAsTooltip("/ts getIP")
            .setClickAsExecuteCmd("/ts getIP").save().send(p);
      }
      // p.sendMessage(ChatColor.YELLOW+"/ts reload" + ChatColor.GRAY +" |
      // Config Reload");
      {
        JsonMessage jm = new JsonMessage();
        jm.append(
            ChatColor.YELLOW + Tab.fFS("/ts rl", tabSize) + ChatColor.GRAY + "| Config Reload")
            .setHoverAsTooltip("/ts rl").setClickAsExecuteCmd("/ts rl").save().send(p);
      }
      // p.sendMessage(ChatColor.YELLOW+"/ts update" + ChatColor.GRAY +" |
      // Get Update Link");
      {
        JsonMessage jm = new JsonMessage();
        jm.append(ChatColor.YELLOW + Tab.fFS("/ts update", tabSize) + ChatColor.GRAY
            + "| Get Update Link").setHoverAsTooltip("/ts update")
            .setClickAsSuggestCmd("/ts update").save().send(p);
      }
      // p.sendMessage(ChatColor.YELLOW+"/ts List" + ChatColor.Gray + " |
      // Online List");
      {
        JsonMessage jm = new JsonMessage();
        jm.append(
            ChatColor.YELLOW + Tab.fFS("/ts list", tabSize) + ChatColor.GRAY + "| Online List")
            .setHoverAsTooltip("/ts list").setClickAsExecuteCmd("/ts list").save().send(p);
      }
      // p.sendMessage(ChatColor.YELLOW+"/ts rl-filter" + ChatColor.GRAY +
      // " | Reload the Filter list");
      {
        JsonMessage jm = new JsonMessage();
        jm.append(ChatColor.YELLOW + Tab.fFS("/ts rl-filter", tabSize) + ChatColor.GRAY
            + "| Reload the Filter list").setClickAsExecuteCmd("/ts rl-filter")
            .setHoverAsTooltip("/ts rl-filter").save().send(p);
      }
      // p.sendMessage(ChatColor.YELLOW+"/ts cache-off" + ChatColor.GRAY +
      // " | Disable the cache");
      {
        JsonMessage jm = new JsonMessage();
        jm.append(ChatColor.YELLOW + Tab.fFS("/ts cache-off", tabSize) + ChatColor.GRAY
            + "| Disable the cache").setClickAsExecuteCmd("/ts cache-off")
            .setHoverAsTooltip("/ts cache-off").save().send(p);
      }
      // p.sendMessage(ChatColor.YELLOW+"/ts cache-on" + ChatColor.GRAY +
      // " | Activated the cache");
      {
        JsonMessage jm = new JsonMessage();
        jm.append(ChatColor.YELLOW + Tab.fFS("/ts cache-on", tabSize) + ChatColor.GRAY
            + "| Activated the cache").setClickAsExecuteCmd("/ts cache-on")
            .setHoverAsTooltip("/ts cache-on").save().send(p);
      }
      // p.sendMessage(ChatColor.YELLOW+"/ts cache" + ChatColor.GRAY + " |
      // Renew the cache");
      {
        JsonMessage jm = new JsonMessage();
        jm.append(
            ChatColor.YELLOW + Tab.fFS("/ts cache", tabSize) + ChatColor.GRAY + "| Renew the cache")
            .setClickAsExecuteCmd("/ts cache").setHoverAsTooltip("/ts cache").save().send(p);
      }
      p.sendMessage("");
      prefixsend(p);
    } else {
      tsipsend(p, tsip);
    }
  }

  private static void prefixsend(Player p) {
    p.sendMessage(ChatColor.YELLOW + "[]================" + ChatColor.GOLD + ChatColor.BOLD
        + " TeamSpeak " + ChatColor.RESET + ChatColor.YELLOW + "===============[]");
  }

  public static void tsipsend(Player p, String tsip) {
    prefixsend(p);
    p.sendMessage("");
    {
      JsonMessage jm = new JsonMessage();
      jm.append(ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.ts3")))
          .setHoverAsTooltip(ChatColor.translateAlternateColorCodes('&', tsip))
          .setClickAsSuggestCmd(ChatColor.translateAlternateColorCodes('&', tsip)).save().send(p);
    }
    p.sendMessage("");
    prefixsend(p);
  }
}
