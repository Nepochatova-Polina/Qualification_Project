package com.example.epamfinalproject.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionDB {
    private static final ConnectionDB connectionDB = new ConnectionDB();
    private static Connection connection;
    private static Logger log;

    private ConnectionDB() {
        String url = "jdbc:postgresql://localhost:15435/postgres";
        String login = "user";
        String password = "password";
        log = Logger.getLogger(ConnectionDB.class.getName());
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.warning("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
        log.info("PostgreSQL JDBC Driver successfully connected");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            log.warning("Connection Failed");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            log.info("You successfully connected to database now");
        } else {
            log.warning("Failed to make connection to database");
        }
    }

    public  Connection getConnection() {return connection;}

    public static  ConnectionDB getConnectionDB() {
        if(connectionDB == null)return new ConnectionDB();
        return connectionDB;
    }

    public void stop() throws SQLException {
        connection.close();
    }

    public void commitAndClose() {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void rollbackAndClose() {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}