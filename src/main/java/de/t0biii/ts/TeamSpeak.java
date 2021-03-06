package de.t0biii.ts;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.t0biii.ts.commands.Ts;
import de.t0biii.ts.commands.TsTapCompleter;
import de.t0biii.ts.listener.PlayerJoin;
import de.t0biii.ts.methods.Bstats;
import de.t0biii.ts.methods.files.ConfigManager;
import de.t0biii.ts.methods.files.DBManager;
import de.t0biii.ts.methods.files.Filter;
import de.t0biii.ts.methods.files.Messages;
import net.gravitydevelopment.updater.Updater;
import net.gravitydevelopment.updater.Updater.UpdateType;

public class TeamSpeak extends JavaPlugin {

  /*
   * TS3 Prefix and Updater id
   */
  public String prefix = "[TeamSpeakIP] ";
  public String Prefix =
      ChatColor.DARK_GRAY
          + "["
          + ChatColor.GOLD
          + ChatColor.BOLD
          + "TeamSpeakIP"
          + ChatColor.RESET
          + ChatColor.DARK_GREEN
          + "] "
          + ChatColor.WHITE;
  public Updater updater;

  public static TeamSpeak instance;
  private final ConfigManager cm = new ConfigManager(this);
  private final Messages ms = new Messages(this);
  public Filter fi = new Filter(this);
  public Logger log = Bukkit.getLogger();
  private final DBManager db = new DBManager();
  /*
   * TS Api
   */
  public boolean error = false;
  public final TS3Config config = new TS3Config();
  public final TS3Query query = new TS3Query(config);
  public final TS3Api api = query.getApi();

  /*
   * Load TS3 Login data
   */
  String host = getConfig().getString("ts3.ip");
  int Queryport = getConfig().getInt("ts3.queryport");
  int ts3port = getConfig().getInt("ts3.port");
  String queryname = getConfig().getString("ts3.querylogin.name");
  String querypw = getConfig().getString("ts3.querylogin.pw");
  String querydisplayname = getConfig().getString("ts3.queryname");
  boolean ssh = false;
  /*
   * Disable Part
   */
  @Override
  public void onDisable() {
    if (!error) {
      api.logout();
      query.exit();
    }
    log.info(prefix + "Plugin disabled.");
  }

  /*
   * Enable Part
   */
  @Override
  public void onEnable() {
    PluginManager pm = Bukkit.getPluginManager();
    instance = this;

    /*
     * Config load and save
     */
    cm.loadConfig();
    saveConfig();
    ms.loadMessages();
    fi.loadFilter();
    db.connect();

    /*
     * TS Command and TapCompleter
     */
    this.getCommand("ts").setExecutor(new Ts(this));
    this.getCommand("ts").setTabCompleter(new TsTapCompleter());

    /*
     * Register Events
     */
    pm.registerEvents(new PlayerJoin(this), this);

    /*
     * TS3 Connect
     */
    try {
      config.setHost(host);
      config.setQueryPort(Queryport);
      query.connect();
      log.info(prefix + "Connected to Teamspeak!");
    } catch (Exception e) {
      log.info(prefix + "Can't connect to Teamspeak Server (" + host + ":" + Queryport + ")!");
      error = true;
    }
    if (!error) {
      try {
        api.login(queryname, querypw);
        api.selectVirtualServerByPort(ts3port);
        api.setNickname(querydisplayname);
      } catch (Exception e) {
        error = true;
        log.info(prefix + "Can't connect to Teamspeak Server (" + host + ":" + ts3port + ")!");
      }
    }

    /*
     * Metrics start
     */
    if (getConfig().getBoolean("options.Metrics")) {
      int bstatsID = 203;
      Metrics metrics = new Metrics(this, bstatsID);
      startBstat(metrics);
    }

    /*
     * Updater
     */
    int uid = 70774;
    updater = new Updater(this, uid, UpdateType.NO_DOWNLOAD);

    /*
     * Start Auto Chache
     */
    int interval = getConfig().getInt("options.realtime.update");
    if (!error) {
      Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this::dbupdate, 20L, interval * 20L);
    }
  }

  // Update Database
  public void dbupdate() {
    db.check();

    int max = api.getHostInfo().getTotalMaxClients();
    int min = api.getClients().size();
    ArrayList<String> list = new ArrayList<>();
    for (Client c : api.getClients()) {
      list.add(c.getNickname());
    }
    db.update(max, min, list);
  }
  // Metrics start

  // Default QueryPort / and Default Teamspeak3 Port
  public void startBstat(Metrics metrics) {
    new Bstats(this).customCharts(metrics);
  }

  // Retrun Instance
  public static TeamSpeak getInstance() {
    return instance;
  }

  public TS3ApiAsync getTS3ApiAsync() {
    return query.getAsyncApi();
  }
}
