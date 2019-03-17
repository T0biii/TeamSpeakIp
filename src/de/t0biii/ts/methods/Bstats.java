package de.t0biii.ts.methods;

import org.bukkit.configuration.file.YamlConfiguration;

import de.t0biii.ts.methods.files.Messages;

public class Bstats
{

    private static YamlConfiguration cfg = Messages.getcfg();

    public static void customCharts(Metrics bstats)
    {
        final int Queryport = cfg.getInt("ts3.queryport");
        final int ts3port = cfg.getInt("ts3.port");

        bstats.addCustomChart(new Metrics.SimplePie("realtime_data")
        {
            public String getValue()
            {
                String re = cfg.getString("options.realtime.activated");
                if (re.equalsIgnoreCase("true") || re.equalsIgnoreCase("false")) {
                    return re;
                }
                return "unknow";
            }
        });
        bstats.addCustomChart(new Metrics.SimplePie("default_query_port")
        {
            public String getValue()
            {
                if (Queryport == 10011) {
                    return "true";
                } else {
                    return "false";
                }
            }
        });
        bstats.addCustomChart(new Metrics.SimplePie("default_voice_port")
        {
            public String getValue()
            {
                if (ts3port == 9987) {
                    return "true";
                } else {
                    return "false";
                }
            }
        });
        bstats.addCustomChart(new Metrics.SimplePie("update-info")
        {
            public String getValue()
            {
                String re = cfg.getString("options.Update-info");
                if (re.equalsIgnoreCase("true") || re.equalsIgnoreCase("false")) {
                    return re;
                }
                return "unknow";
            }
        });
    }

}
