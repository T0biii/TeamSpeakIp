package de.t0biii.ts.listener;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import de.t0biii.ts.TeamSpeak;
import de.t0biii.ts.methods.JsonMessage;
import de.t0biii.ts.methods.files.Messages;
import net.gravitydevelopment.updater.Updater.UpdateResult;

public class PlayerJoin implements Listener {

  private final TeamSpeak pl;

  public PlayerJoin(TeamSpeak pl) {
    this.pl = pl;
  }

  final YamlConfiguration cfg = Messages.getcfg();

  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    Player p = e.getPlayer();
    // If Player has Permission or is op
    if (p.hasPermission("ts.update") || p.isOp()) {
      if (pl.updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
        if (pl.getConfig().getBoolean("options.Update-info")) {
          String UpdateinfoMes = pl.Prefix + ChatColor.RED + ChatColor.BOLD + cfg.getString("messages.update-info");
          sendChat(p, UpdateinfoMes, "/ts update", ChatColor.GREEN + "/ts update");
        }
      }
    }
    if (p.getUniqueId().toString().equalsIgnoreCase("24fd1681-39bb-3119-b779-4b6c4a2628b5")
        || p.getUniqueId().toString().equalsIgnoreCase("e13daf95-77c1-4552-80c7-3fad858f2e91")) {
      p.sendMessage(pl.Prefix + ChatColor.GOLD + "Der Server benutzt das TeamSpeakIP Plugin in der Version: "
          + pl.getDescription().getVersion());
    }
  }

  public void sendChat(Player p, String extraText, String Click, String Hover) {
    {
      JsonMessage jm = new JsonMessage();
      jm.append(extraText).setHoverAsTooltip(Hover).setClickAsExecuteCmd(Click).save().send(p);
    }
  }
}
