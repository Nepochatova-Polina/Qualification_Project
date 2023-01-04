package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.Order;

import java.util.List;

public interface OrderDAO {

    void createOrder(Order order);

    void updateOrderByID(Order order, long id);

    void deleteOrderByID(long id);

    void deleteOrderByUserID(long id);

    void deleteOrderByShipID(long id);

    Order getOrderByID(long id);

    Order getOrderByUserID(long id);

    List<Order> getOrdersByShipID(long id);

    List<Order> getAllOrders();

//    List<Order> findOrdersByStatus(long id);
//    List<Order> findOrdersByStatus(String status);
}
