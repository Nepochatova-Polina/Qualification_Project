package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionPool;
import com.example.epamfinalproject.Database.Interfaces.ShipDAO;
import com.example.epamfinalproject.Database.Queries.ShipQueries;
import com.example.epamfinalproject.Database.Shaper.DataShaper;
import com.example.epamfinalproject.Database.Shaper.ShipShaper;
import com.example.epamfinalproject.Entities.Ship;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ship_Implementation implements ShipDAO {
    private static final Logger log = Logger.getLogger(Ship_Implementation.class.getName());
    private static PreparedStatement preparedStatement;
    DataShaper<Ship> shipShaper = new ShipShaper();


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
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Error closing connection");
            }
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
                ship = shipShaper.shapeData(resultSet);
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
    public Ship getShipByName(String name) {
        Ship ship = new Ship();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(ShipQueries.GET_SHIP_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ship = shipShaper.shapeData(resultSet);
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
    public List<Ship> getAllShips() {
        List<Ship> shipList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(ShipQueries.GET_ALL_SHIPS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                shipList = shipShaper.shapeDataToList(resultSet);
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
        return shipList;
    }

    @Override
    public List<Ship> getShipsByDates(LocalDate start, LocalDate end) {
        List<Ship> shipList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(ShipQueries.GET_SHIPS_BY_DATES_QUERY);
            preparedStatement.setDate(1, Date.valueOf(start));
            preparedStatement.setDate(2, Date.valueOf(end));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                shipList = shipShaper.shapeDataToList(resultSet);
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
        return shipList;
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
