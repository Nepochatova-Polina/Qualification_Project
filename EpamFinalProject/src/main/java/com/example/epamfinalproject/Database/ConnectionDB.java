package com.example.epamfinalproject.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionDB {
    private static final ConnectionDB connectionDB = new ConnectionDB();
    private static Connection connection;
    private static final String url = "jdbc:postgresql://localhost:15435/postgres";
    private static final String login = "user";
    private static final String password = "password";

    private ConnectionDB() {

        Logger log = Logger.getLogger(ConnectionDB.class.getName());
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.warning("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            return;
        }
        log.info("PostgreSQL JDBC Driver successfully connected");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            log.warning("ConnectionPool Failed");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            log.info("You successfully connected to database now");
        } else {
            log.warning("Failed to make connection to database");
        }
    }

    public Connection getConnection() throws SQLException {
        if(connection.isClosed()){
            connection = DriverManager.getConnection(url, login, password);
        }
        return connection;
    }

    public static ConnectionDB getConnectionDB() {
        return connectionDB;
    }

    public void stop() throws SQLException {
        connection.close();
    }

}