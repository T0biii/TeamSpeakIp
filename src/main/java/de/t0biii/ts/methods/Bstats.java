package de.t0biii.ts.methods;


import java.util.concurrent.Callable;
import org.bstats.bukkit.Metrics;
import de.t0biii.ts.TeamSpeak;

public class Bstats {
  private final TeamSpeak plugin;

  public Bstats(TeamSpeak plugin) {
    this.plugin = plugin;
  }

  public void customCharts(Metrics bstats) {
    final int queryport = plugin.getConfig().getInt("ts3.queryport");
    final int ts3port = plugin.getConfig().getInt("ts3.port");


    bstats.addCustomChart(new Metrics.SimplePie("realtime_data", () -> {
      String realtime = plugin.getConfig().getString("options.realtime.activated");
      if (realtime.equalsIgnoreCase("true") || realtime.equalsIgnoreCase("false")) {
        return realtime;
      }
      return "unknow";
    }));

    bstats.addCustomChart(new Metrics.SimplePie("default_query_port", () -> {
      if (queryport == 10011) {
        return "true";
      } else {
        return "false";
      }
    }));

    bstats.addCustomChart(new Metrics.SimplePie("default_voice_port", () -> {
      if (ts3port == 9987) {
        return "true";
      } else {
        return "false";
      }
    }));

    bstats.addCustomChart(new Metrics.SimplePie("update-info", () -> {
      String updateinfo = plugin.getConfig().getString("options.Update-info");
      if (updateinfo.equalsIgnoreCase("true") || updateinfo.equalsIgnoreCase("false")) {
        return updateinfo;
      }
      return "unknow";
    }));

    bstats.addCustomChart(new Metrics.SimplePie("default_queryname", () -> {
      String queryname = plugin.getConfig().getString("ts3.queryname");
      if (queryname != null) {
        if (queryname.equalsIgnoreCase("TeamspeakIP")) {
          return "true";
        } else {
          return "false";
        }
      } else {
        return "unknown";
      }

    }));
  }
}
