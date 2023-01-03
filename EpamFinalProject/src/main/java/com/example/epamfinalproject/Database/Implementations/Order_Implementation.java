package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionPool;
import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Database.Interfaces.OrderDAO;
import com.example.epamfinalproject.Database.Queries.OrderQueries;
import com.example.epamfinalproject.Entities.*;
import com.example.epamfinalproject.Entities.Enums.Status;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order_Implementation implements OrderDAO {
    private static final Logger log = Logger.getLogger(Order_Implementation.class.getName());
    PreparedStatement preparedStatement;


    @Override
    public void createOrder(Order order) {
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(OrderQueries.CREATE_ORDER_QUERY);
            preparedStatement.setLong(1, order.getCruise().getId());
            preparedStatement.setLong(2, order.getUser().getId());
            preparedStatement.setString(3, order.getStatus().toString());
//            TODO Image Blob processing
            if (preparedStatement.executeUpdate() <= 0) {
                log.warn("Cannot add order information.");
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
    public void updateOrderByID(Order order, long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(OrderQueries.UPDATE_ORDER_BY_ID_QUERY);
            preparedStatement.setLong(1, order.getCruise().getId());
            preparedStatement.setLong(2, order.getUser().getId());
            preparedStatement.setString(3, order.getStatus().toString());
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
    public void deleteOrderByID(long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(OrderQueries.DELETE_ORDER_BY_ID_QUERY);
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
    public void deleteOrderByUserID(long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(OrderQueries.DELETE_ORDER_BY_USER_ID_QUERY);
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
    public void deleteOrderByShipID(long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(OrderQueries.DELETE_ORDER_BY_SHIP_ID_QUERY);
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
    public Order getOrderByID(long id) {
        Order order = new Order();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(OrderQueries.GET_ORDER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                order.setCruise(createCruise(resultSet));
                order.setUser(createUser(resultSet));
                order.setStatus(Status.fromString(resultSet.getString(4)));

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
        return order;
    }

    @Override
    public Order getOrderByUserID(long id) {
        Order order = new Order();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(OrderQueries.GET_ORDER_BY_USER_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                order.setCruise(createCruise(resultSet));
                order.setUser(createUser(resultSet));
                order.setStatus(Status.fromString(resultSet.getString(4)));
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
        return order;
    }

    @Override
    public List<Order> getOrdersByShipID(long id) {
        List<Order> orderList = new ArrayList<>();
        Order order = new Order();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(OrderQueries.GET_ORDER_BY_SHIP_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                order.setCruise(createCruise(resultSet));
                order.setUser(createUser(resultSet));
                order.setStatus(Status.fromString(resultSet.getString(4)));
                orderList.add(order);
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
        return orderList;
    }

    private Cruise createCruise(ResultSet resultSet) throws SQLException {
        Cruise cruise = new Cruise();
        Route route;
        Ship ship;
        cruise.setId(resultSet.getLong("cruise_id"));
        cruise.setStartOfTheCruise(LocalDate.parse(resultSet.getString("start_date")));
        cruise.setEndOfTheCruise((LocalDate.parse(resultSet.getString("end_date"))));

        route = new Route(resultSet.getLong("route_id"),
                resultSet.getString("departure"),
                resultSet.getString("destination"),
                resultSet.getInt("number_of_ports"),
                resultSet.getInt("transit_time"));

        ship = new Ship(resultSet.getLong("ship_id"),
                resultSet.getString("name"),
                resultSet.getInt("passenger_capacity"));

        cruise.setShip(ship);
        cruise.setRoute(route);

        return cruise;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user;
        user = new User
                .UserBuilder()
                .id(resultSet.getInt(FieldKey.USER_ID))
                .firstName(resultSet.getString(FieldKey.FIRST_NAME))
                .lastName(resultSet.getString(FieldKey.LAST_NAME))
                .login(resultSet.getString(FieldKey.LOGIN))
                .password(resultSet.getString(FieldKey.PASSWORD))
                .role(UserRole.fromString(resultSet.getString(FieldKey.ROLE)))
                .build();
        return user;
    }
}
