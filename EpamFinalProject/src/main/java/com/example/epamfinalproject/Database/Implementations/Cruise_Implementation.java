package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionPool;
import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Database.Interfaces.CruiseDAO;
import com.example.epamfinalproject.Database.Queries.CruiseQueries;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Route;
import com.example.epamfinalproject.Entities.Ship;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Cruise_Implementation implements CruiseDAO {
    private static final Logger log = Logger.getLogger(Order_Implementation.class.getName());
    PreparedStatement preparedStatement;


    @Override
    public void createCruise(Cruise cruise) {
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.CREATE_CRUISE_QUERY);
            preparedStatement.setLong(1, cruise.getShip().getId());
            preparedStatement.setLong(2, cruise.getRoute().getId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(cruise.getStartOfTheCruise()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(cruise.getEndOfTheCruise()));
            if (preparedStatement.executeUpdate() <= 0) {
                log.warn("Cannot add cruise information.");
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
    public void updateCruiseByID(Cruise cruise, long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CruiseQueries.UPDATE_CRUISE_BY_ID_QUERY);
            preparedStatement.setLong(1, cruise.getShip().getId());
            preparedStatement.setLong(2, cruise.getRoute().getId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(cruise.getStartOfTheCruise()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(cruise.getEndOfTheCruise()));
            preparedStatement.setLong(5, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warn("Cannot update order information.");
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
    public void deleteCruiseByID(long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CruiseQueries.DELETE_CRUISE_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warn("Cannot update order information.");
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
    public Cruise getCruiseByID(long id) {
        Cruise cruise = new Cruise();
        Ship ship;
        Route route;
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_CRUISE_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                cruise.setId(resultSet.getLong(FieldKey.ID));
                cruise.setStartOfTheCruise(LocalDate.parse(resultSet.getString(FieldKey.LEAVING)));
                cruise.setEndOfTheCruise((LocalDate.parse(resultSet.getString(FieldKey.ARRIVING))));

                route = new Route(resultSet.getLong(FieldKey.CRUISE_ROUTE),
                        resultSet.getString(FieldKey.DEPARTURE),
                        resultSet.getString(FieldKey.DESTINATION),
                        resultSet.getInt(FieldKey.NUMBER_OF_PORTS),
                        resultSet.getInt(FieldKey.TRANSIT_TIME));

                ship = new Ship(resultSet.getLong(FieldKey.SHIP_ID),
                        resultSet.getString(FieldKey.SHIP_NAME),
                        resultSet.getInt(FieldKey.PASSENGER_CAPACITY));

                cruise.setShip(ship);
                cruise.setRoute(route);
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
        return cruise;
    }

    @Override
    public List<Cruise> getCruisesByShipID(long id) {
        List<Cruise> cruiseList = new ArrayList<>();
        Cruise cruise = new Cruise();
        Ship ship;
        Route route;
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_CRUISE_BY_SHIP_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            String x = "";
            if (resultSet.next()) {

                cruise.setId(resultSet.getLong(FieldKey.ID));
                cruise.setStartOfTheCruise(LocalDate.parse(resultSet.getString(FieldKey.LEAVING)));
                cruise.setEndOfTheCruise((LocalDate.parse(resultSet.getString(FieldKey.ARRIVING))));

                route = new Route(resultSet.getLong(FieldKey.CRUISE_ROUTE),
                        resultSet.getString(FieldKey.DEPARTURE),
                        resultSet.getString(FieldKey.DESTINATION),
                        resultSet.getInt(FieldKey.NUMBER_OF_PORTS),
                        resultSet.getInt(FieldKey.TRANSIT_TIME));

                ship = new Ship(resultSet.getLong(FieldKey.SHIP_ID),
                        resultSet.getString(FieldKey.SHIP_NAME),
                        resultSet.getInt(FieldKey.PASSENGER_CAPACITY));

                cruise.setShip(ship);
                cruise.setRoute(route);
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
        return cruiseList;
    }
}
