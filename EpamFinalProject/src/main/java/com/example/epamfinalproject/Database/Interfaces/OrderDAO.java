package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.Order;

import java.util.List;

public interface OrderDAO {

    void createOrder(Order order);

    void updateOrderByID(Order order, long id);

    void deleteOrderByID(long id);

    Order findOrderByID(long id);

    Order findOrderByUserID(long id);

    List<Order> findOrdersByShipID(long id);

    long findStatusID(String status);

    String findStatusByID(long id);
//    List<Order> findOrdersByStatus(long id);
//    List<Order> findOrdersByStatus(String status);
}
