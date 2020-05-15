package de.t0biii.ts.methods.files;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import de.t0biii.ts.TeamSpeak;

/**
 * Created by Tobias on 15.03.2017.
 */
public class DBManager {

  // @SuppressWarnings("unused")
  // private TeamSpeak pl;
  // public DBManager(TeamSpeak pl){
  // this.pl = pl;
  // }

  private static Connection connection;
  private static final String DB_PATH = "plugins/TeamSpeakIP/cache.db";
  static {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      System.err.println(TeamSpeak.getInstance().prefix + "Fehler beim Laden des JDBC-Treibers");
      e.printStackTrace();
    }
  }

  /**
   * Check connections to the DB
   */
  public void check() {
    try {
      if (connection == null) {
        connect();
      } else if (connection.isClosed()) {
        connect();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Connect to the DB
   */
  public void connect() {
    try {
      if (connection != null)
        return;

      connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS TS3IP (value, max, min);");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        if (!connection.isClosed() && connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }));
  }

  // Check if value Exists
  private boolean exists() {
    try {
      PreparedStatement ps = connection.prepareStatement("SELECT value FROM TS3IP WHERE value = ?");
      ps.setInt(1, 1);
      ResultSet rs = ps.executeQuery();
      boolean r = rs.next();
      rs.close();
      return r;
    } catch (SQLException e) {
      e.printStackTrace();

    }
    return false;
  }

  /**
   * Update the DB with new Data
   * 
   * @param max Max Clients
   * @param min Clients online
   * @param cache Clientslist
   */
  public void update(int max, int min, List<String> cache) {
    try {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("DROP TABLE IF EXISTS TS3Cache");
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS TS3Cache (cache);");
      {
        if (!exists()) {
          PreparedStatement ps =
              connection.prepareStatement("INSERT INTO TS3IP (value, max, min) VALUES (?, ?, ?);");
          ps.setInt(1, 1);
          ps.setInt(2, max);
          ps.setInt(3, min);
          ps.executeUpdate();
        } else {
          PreparedStatement ps =
              connection.prepareStatement("UPDATE TS3IP SET max = ?, min = ? WHERE value = ?");
          ps.setInt(1, max);
          ps.setInt(2, min);
          ps.setInt(3, 1);
          ps.executeUpdate();
        }
      }
      {
        for (String a : cache) {
          PreparedStatement ps =
              connection.prepareStatement("INSERT INTO TS3Cache (cache) VALUES (?);");
          ps.setString(1, a);
          ps.executeUpdate();
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the Arraylist of Teamspeak3 Clients
   * 
   * @return ArrayList
   */
  public static ArrayList<String> getArray() {
    PreparedStatement ps;
    ArrayList<String> list = new ArrayList<>();
    try {
      ps = connection.prepareStatement("SELECT * FROM TS3Cache");
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        list.add(rs.getString("cache"));
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Returns the int form the spalte
   * 
   * @param spalte in the DB
   * @return int
   */
  public static int getInt(String spalte) {
    PreparedStatement ps;
    int output = -1;
    try {
      ps = connection.prepareStatement("SELECT * FROM TS3IP WHERE value = ?");
      ps.setInt(1, 1);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        output = rs.getInt(spalte);
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return output;
  }
}
