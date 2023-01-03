package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionPool;
import com.example.epamfinalproject.Database.Interfaces.ShipDAO;
import com.example.epamfinalproject.Database.Queries.ShipQueries;
import com.example.epamfinalproject.Entities.Ship;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ship_Implementation implements ShipDAO {
    private static final Logger log = Logger.getLogger(User_Implementation.class.getName());
    private static PreparedStatement preparedStatement;


    @Override
    public void registerShip(Ship ship) {
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(ShipQueries.REGISTER_SHIP_QUERY);
            preparedStatement.setString(1, ship.getName());
            preparedStatement.setInt(2, ship.getPassengerCapacity());
            if (preparedStatement.executeUpdate() <= 0) {
                log.warn("Cannot register shipDTO.");
            }
        } catch (SQLException e) {
            log.warn("Problems with connection:" + e);
        } finally {
//            try {
//                preparedStatement.close();
//            } catch (SQLException e) {
//                log.warn("Error closing connection");
//            }
        }
    }

    @Override
    public Ship getShipByID(long id) {
        Ship ship = new Ship();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(ShipQueries.GET_SHIP_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ship.setId(resultSet.getLong(1));
                ship.setName(resultSet.getString(2));
                ship.setPassengerCapacity(resultSet.getInt(3));
            }
        } catch (SQLException e) {
            log.warn("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Error closing connection");
            }
        }
        return ship;
    }

    @Override
    public void updateShipByID(Ship ship, long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ShipQueries.UPDATE_SHIP_BY_ID_QUERY);
            preparedStatement.setString(1, ship.getName());
            preparedStatement.setInt(2, ship.getPassengerCapacity());
            preparedStatement.setLong(3, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warn("Cannot update ship.");
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.warn("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Error closing connection");
            }
        }
    }

    @Override
    public void deleteShipByID(long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ShipQueries.DELETE_SHIP_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warn("Cannot delete ship.");
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.warn("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Error closing connection");
            }
        }
    }

}
