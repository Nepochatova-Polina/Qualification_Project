package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionPool;
import com.example.epamfinalproject.Database.Interfaces.CruiseDAO;
import com.example.epamfinalproject.Database.Interfaces.OrderDAO;
import com.example.epamfinalproject.Database.Queries.OrderQueries;
import com.example.epamfinalproject.Entities.*;
import com.example.epamfinalproject.Entities.Enums.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.UserService;
import org.apache.log4j.Logger;

import javax.management.relation.Role;

public class Order_Implementation implements OrderDAO {
    private static final Logger log = Logger.getLogger(Order_Implementation.class.getName());
    PreparedStatement preparedStatement;
    public static final String GET_ORDER_BY_ID = "select * from orders where orders.id = ?";

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
        Cruise cruise;
        Ship ship;
        Route route;
        User user;
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cruise = CruiseService.getCruiseByID(resultSet.getLong(2));
                user = UserService.getUserByID(resultSet.getLong(3));

                order.setId(resultSet.getLong(1));
                order.setCruise(cruise);
                order.setUser(user);
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
//                order.setCruiseID(resultSet.getLong(2));
//                order.setUserID(resultSet.getLong(3));
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
//                order.setCruiseID(resultSet.getLong(2));
//                order.setUserID(resultSet.getLong(3));
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
}
