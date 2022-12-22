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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ship_Implementation implements ShipDAO {
    private static final Logger log = Logger.getLogger(User_Implementation.class.getName());
    private static PreparedStatement preparedStatement;


    @Override
    public void registerShip(Ship ship) {
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(ShipQueries.REGISTER_SHIP_QUERY);
            preparedStatement.setLong(1, ship.getRoute_id());
            preparedStatement.setInt(2, ship.getNumberOfPorts());
            preparedStatement.setInt(3, ship.getPassengerCapacity());
            preparedStatement.setDate(4, java.sql.Date.valueOf(ship.getStartOfTheCruise()));
            preparedStatement.setDate(5, java.sql.Date.valueOf(ship.getEndOfTheCruise()));
            if (preparedStatement.executeUpdate() <= 0) {
                log.warn("Cannot register ship.");
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
                ship.setId(resultSet.getLong(1));
                ship.setRoute_id(resultSet.getLong(2));
                ship.setNumberOfPorts(resultSet.getInt(3));
                ship.setPassengerCapacity(resultSet.getInt(4));
                ship.setStartOfTheCruise(LocalDate.parse(resultSet.getString(5)));
                ship.setEndOfTheCruise(LocalDate.parse(resultSet.getString(6)));
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
            preparedStatement.setLong(1, ship.getRoute_id());
            preparedStatement.setInt(2, ship.getNumberOfPorts());
            preparedStatement.setInt(3, ship.getPassengerCapacity());
            preparedStatement.setDate(4, java.sql.Date.valueOf(ship.getStartOfTheCruise()));
            preparedStatement.setDate(5, java.sql.Date.valueOf(ship.getEndOfTheCruise()));
            preparedStatement.setLong(6, id);
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

    @Override
    public List<Ship> getShipsByRouteID(long id) {
        List<Ship> shipList = new ArrayList<>();
        Ship ship = new Ship();
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ShipQueries.GET_SHIPS_BY_ROUTE_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ship.setId(resultSet.getLong(1));
                ship.setRoute_id(resultSet.getLong(2));
                ship.setNumberOfPorts(resultSet.getInt(3));
                ship.setPassengerCapacity(resultSet.getInt(4));
                ship.setStartOfTheCruise(LocalDate.parse(resultSet.getString(5)));
                ship.setEndOfTheCruise(LocalDate.parse(resultSet.getString(6)));
                shipList.add(ship);
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
    public List<Ship> getShipsByStartDate(LocalDate startDate) {
        List<Ship> shipList = new ArrayList<>();
        Ship ship = new Ship();
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ShipQueries.GET_SHIPS_BY_START_DATE_QUERY);
            preparedStatement.setDate(1, java.sql.Date.valueOf(startDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ship.setId(resultSet.getLong(1));
                ship.setRoute_id(resultSet.getLong(2));
                ship.setNumberOfPorts(resultSet.getInt(3));
                ship.setPassengerCapacity(resultSet.getInt(4));
                ship.setStartOfTheCruise(LocalDate.parse(resultSet.getString(5)));
                ship.setEndOfTheCruise(LocalDate.parse(resultSet.getString(6)));
                shipList.add(ship);
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
    public List<Ship> getShipsByEndDate(LocalDate endDate) {
        List<Ship> shipList = new ArrayList<>();
        Ship ship = new Ship();
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ShipQueries.GET_SHIPS_BY_END_DATE_QUERY);
            preparedStatement.setDate(1, java.sql.Date.valueOf(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ship.setId(resultSet.getLong(1));
                ship.setRoute_id(resultSet.getLong(2));
                ship.setNumberOfPorts(resultSet.getInt(3));
                ship.setPassengerCapacity(resultSet.getInt(4));
                ship.setStartOfTheCruise(LocalDate.parse(resultSet.getString(5)));
                ship.setEndOfTheCruise(LocalDate.parse(resultSet.getString(6)));
//                List<Staff> staff =
                shipList.add(ship);
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
}
