package de.t0biii.ts.methods;


import java.util.concurrent.Callable;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.YamlConfiguration;
import de.t0biii.ts.methods.files.Messages;

public class Bstats {

  private static YamlConfiguration cfg = Messages.getcfg();

  public static void customCharts(Metrics bstats) {
    final int queryport = cfg.getInt("ts3.queryport");
    final int ts3port = cfg.getInt("ts3.port");
    final String queryname = cfg.getString("ts3.queryname");


    bstats.addCustomChart(new Metrics.SimplePie("realtime_data", new Callable<String>() {
      @Override
      public String call() throws Exception {
        String realtime = cfg.getString("options.realtime.activated");
        if (realtime.equalsIgnoreCase("true") || realtime.equalsIgnoreCase("false")) {
          return realtime;
        }
        return "unknow";
      }
    }));

    bstats.addCustomChart(new Metrics.SimplePie("default_query_port", new Callable<String>() {
      @Override
      public String call() throws Exception {
        if (queryport == 10011) {
          return "true";
        } else {
          return "false";
        }
      }
    }));

    bstats.addCustomChart(new Metrics.SimplePie("default_voice_port", new Callable<String>() {
      @Override
      public String call() throws Exception {
        if (ts3port == 9987) {
          return "true";
        } else {
          return "false";
        }
      }
    }));

    bstats.addCustomChart(new Metrics.SimplePie("update-info", new Callable<String>() {
      @Override
      public String call() throws Exception {
        String updateinfo = cfg.getString("options.Update-info");
        if (updateinfo.equalsIgnoreCase("true") || updateinfo.equalsIgnoreCase("false")) {
          return updateinfo;
        }
        return "unknow";
      }
    }));

    bstats.addCustomChart(new Metrics.SimplePie("default_queryname", new Callable<String>() {
      @Override
      public String call() throws Exception {
        if (queryname != null) {
          if (queryname.equalsIgnoreCase("TeamspeakIP")) {
            return "true";
          } else {
            return "false";
          }
        } else {
          return "unknow";
        }


      }
    }));


  }
}
