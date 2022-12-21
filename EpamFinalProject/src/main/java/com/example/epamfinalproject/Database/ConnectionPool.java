package com.example.epamfinalproject.Database;

import com.example.epamfinalproject.Database.Implementations.Route_Implementation;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class ConnectionPool {
    private static final Logger log = Logger.getLogger(Route_Implementation.class.getName());

    private static final ComboPooledDataSource cpds = new ComboPooledDataSource();
    private static final String url = "jdbc:postgresql://localhost:15435/postgres";
    private static final String login = "user";
    private static final String password = "password";
    static {
        try {
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl("jdbc:postgresql://localhost:15435/postgres");
            cpds.setUser("user");
            cpds.setPassword("password");
        } catch (PropertyVetoException e) {
            log.warn("Problems with connection:" + e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }

    private ConnectionPool(){}
}
