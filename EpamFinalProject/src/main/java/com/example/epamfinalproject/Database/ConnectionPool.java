package com.example.epamfinalproject.Database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class ConnectionPool {
  private static final Logger log = Logger.getLogger(ConnectionPool.class.getName());

  private static final ComboPooledDataSource cpds = new ComboPooledDataSource();

  static {
    try {
      Class.forName("org.postgresql.Driver");
      cpds.setDriverClass("org.postgresql.Driver");
      cpds.setJdbcUrl("jdbc:postgresql://localhost:15435/postgres");
      cpds.setUser("user");
      cpds.setPassword("password");
    } catch (PropertyVetoException e) {
      log.warn("Problems with connection:" + e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("There is no postgresql Driver in Tomcat. Please edit the configuration");
    }
  }

  public static Connection getConnection() throws SQLException {
    return cpds.getConnection();
  }

  public static void setHostPort(String host, int port) {
    cpds.setJdbcUrl("jdbc:postgresql://" + host + ":" + port + "/postgres");
  }

  private ConnectionPool() {}
}
