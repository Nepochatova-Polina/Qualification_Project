package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionPool;
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
            preparedStatement.setLong(4, id);
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
            String x = "";
            if (resultSet.next()) {

                cruise.setId(resultSet.getLong(1));
                cruise.setStartOfTheCruise(LocalDate.parse(resultSet.getString(4)));
                cruise.setEndOfTheCruise((LocalDate.parse(resultSet.getString(5))));

                route = new Route(resultSet.getLong(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10));

                ship = new Ship(resultSet.getLong(11),
                        resultSet.getString(12),
                        resultSet.getInt(13));

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

                cruise.setId(resultSet.getLong(1));
                cruise.setStartOfTheCruise(LocalDate.parse(resultSet.getString(4)));
                cruise.setEndOfTheCruise((LocalDate.parse(resultSet.getString(5))));

                route = new Route(resultSet.getLong(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10));

                ship = new Ship(resultSet.getLong(11),
                        resultSet.getString(12),
                        resultSet.getInt(13));

                cruise.setShip(ship);
                cruise.setRoute(route);
                cruiseList.add(cruise);

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
