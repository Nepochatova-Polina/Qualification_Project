package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionDB;
import com.example.epamfinalproject.Database.Interfaces.OrderDAO;
import com.example.epamfinalproject.Entities.Enums.Status;
import com.example.epamfinalproject.Entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Order_Implementation implements OrderDAO {
    private static final Logger log = Logger.getLogger(Order_Implementation.class.getName());
    PreparedStatement preparedStatement;

    private static final String CREATE_ORDER_QUERY = "insert into orders(ship_id, user_id, status) values (?,?,?)";
    private static final String UPDATE_ORDER_BY_ID_QUERY = "update orders set ship_id = ?,user_id = ?,status = ? where id = ?";
    private static final String DELETE_ORDER_BY_ID_QUERY = " delete from orders where id = ?";
    private static final String DELETE_ORDER_BY_USER_ID_QUERY = " delete from orders where user_id = ?";
    private static final String DELETE_ORDER_BY_SHIP_ID_QUERY = " delete from orders where ship_id = ?";
    private static final String GET_ORDER_BY_ID = "select * from orders where id = ?";
    private static final String GET_ORDER_BY_USER_ID = "select * from orders where user_id = ?";
    private static final String GET_ORDER_BY_SHIP_ID = "select * from orders where ship_id = ?";


    @Override
    public void createOrder(Order order) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(CREATE_ORDER_QUERY);
            preparedStatement.setLong(1, order.getShipID());
            preparedStatement.setLong(2, order.getUserID());
            preparedStatement.setString(3, order.getStatus().toString());
//            TODO Image Blob processing
            if (preparedStatement.executeUpdate() <= 0) {
                log.warning("Cannot add order information.");
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
    public void updateOrderByID(Order order, long id) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_ORDER_BY_ID_QUERY);
            preparedStatement.setLong(1, order.getShipID());
            preparedStatement.setLong(2, order.getUserID());
            preparedStatement.setString(3, order.getStatus().toString());
            preparedStatement.setLong(4, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Cannot update order information.");
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
    public void deleteOrderByID(long id) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Cannot update order information.");
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
    public void deleteOrderByUserID(long id) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_USER_ID_QUERY);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Cannot update order information.");
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
    public void deleteOrderByShipID(long id) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_SHIP_ID_QUERY);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Cannot update order information.");
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
    public Order getOrderByID(long id) {
        Order order = new Order();
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                order.setShipID(resultSet.getLong(2));
                order.setUserID(resultSet.getLong(3));
                order.setStatus(Status.fromString(resultSet.getString(4)));
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
        return order;
    }

    @Override
    public Order getOrderByUserID(long id) {
        Order order = new Order();
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ORDER_BY_USER_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                order.setShipID(resultSet.getLong(2));
                order.setUserID(resultSet.getLong(3));
                order.setStatus(Status.fromString(resultSet.getString(4)));
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
        return order;
    }

    @Override
    public List<Order> getOrdersByShipID(long id) {
        List<Order> orderList = new ArrayList<>();
        Order order = new Order();
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ORDER_BY_SHIP_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                order.setShipID(resultSet.getLong(2));
                order.setUserID(resultSet.getLong(3));
                order.setStatus(Status.fromString(resultSet.getString(4)));
                orderList.add(order);
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
        return orderList;
    }
}
