package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionDB;
import com.example.epamfinalproject.Database.Interfaces.RouteDAO;
import com.example.epamfinalproject.Entities.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Route_Implementation implements RouteDAO {
    private static final Logger log = Logger.getLogger(Route_Implementation.class.getName());
    private static PreparedStatement preparedStatement;

    private static final String CREATE_ROUTE_QUERY = "insert into route(departure, destination, distance, transit_time) values (?,?,?,?)";
    private static final String UPDATE_ROUTE_QUERY = "update route set departure = ?,destination = ?,distance = ?,transit_time= ? where id = ?";
    private static final String DELETE_ROUTE_QUERY = "delete from route where id = ?";
    private static final String GET_ROUTE_BY_ID_QUERY = "select * from route where route.id = ?";
    private static final String GET_ROUTE_BY_DEPARTURE_QUERY = "select * from route where departure = ?";
    private static final String GET_ROUTE_BY_DESTINATION_QUERY = "select * from route where destination = ?";
    private static final String GET_ROUTE_BY_DEPARTURE_AND_DESTINATION_QUERY = "select * from route where departure = ? and destination = ?";
    private static final String GET_ROUTE_BY_TRANSIT_TIME_QUERY = "select * from route where transit_time = ?";

    @Override
    public void createRoute(Route route) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(CREATE_ROUTE_QUERY);
            preparedStatement.setString(1, route.getDeparture());
            preparedStatement.setString(2, route.getDestination());
            preparedStatement.setInt(3, route.getDistance());
            preparedStatement.setInt(4, route.getTransitTime());
            if (preparedStatement.executeUpdate() <= 0) {
                log.warning("Cannot create route.");
            }
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }

    }

    @Override
    public void updateRouteByID(long id, Route route) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_ROUTE_QUERY);
            preparedStatement.setString(1, route.getDeparture());
            preparedStatement.setString(2, route.getDestination());
            preparedStatement.setInt(3, route.getDistance());
            preparedStatement.setInt(4, route.getTransitTime());
            preparedStatement.setLong(4, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Cannot update route information.");
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
            log.warning(e.toString());
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
    }

    @Override
    public void deleteRouteByID(long id) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_ROUTE_QUERY);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Cannot delete route information.");
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
    }

    @Override
    public Route getRouteByID(long id) {
        Route route = new Route();
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ROUTE_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                route.setId(resultSet.getInt(1));
                route.setDeparture(resultSet.getString(2));
                route.setDestination(resultSet.getString(3));
                route.setDistance(resultSet.getInt(4));
                route.setTransitTime(resultSet.getInt(5));
            }
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
        return route;
    }

    @Override
    public List<Route> getRouteByDeparture(String departure) {
        List<Route> routeList = new ArrayList<>();
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ROUTE_BY_DEPARTURE_QUERY);
            preparedStatement.setString(1, departure);
            ResultSet resultSet = preparedStatement.executeQuery();
            routeList = collectData(resultSet);
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
        return routeList;
    }

    @Override
    public List<Route> getRouteByDestination(String destination) {
        List<Route> routeList = new ArrayList<>();
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ROUTE_BY_DESTINATION_QUERY);
            preparedStatement.setString(1, destination);
            ResultSet resultSet = preparedStatement.executeQuery();
            routeList = collectData(resultSet);
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
        return routeList;
    }

    @Override
    public List<Route> getRouteByTransitTime(int transitTime) {
        List<Route> routeList = new ArrayList<>();
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ROUTE_BY_TRANSIT_TIME_QUERY);
            preparedStatement.setInt(1, transitTime);
            ResultSet resultSet = preparedStatement.executeQuery();
            routeList = collectData(resultSet);
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
        return routeList;
    }

    @Override
    public List<Route> getRouteByDepartureAndDestination(String departure, String destination) {
        List<Route> routeList = new ArrayList<>();
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ROUTE_BY_DEPARTURE_AND_DESTINATION_QUERY);
            preparedStatement.setString(1, departure);
            preparedStatement.setString(2, destination);
            ResultSet resultSet = preparedStatement.executeQuery();
            routeList = collectData(resultSet);
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
        return routeList;
    }

    private List<Route> collectData(ResultSet resultSet) throws SQLException {
        List<Route> routeList = new ArrayList<>();
        Route route = new Route();
        if (resultSet.next()) {
            route.setId(resultSet.getLong(1));
            route.setDeparture(resultSet.getString(2));
            route.setDestination(resultSet.getString(3));
            route.setDistance(resultSet.getInt(4));
            route.setTransitTime(resultSet.getInt(5));
            routeList.add(route);
        }
        log.info("List of Routes was created and filled");
        return routeList;
    }

}
