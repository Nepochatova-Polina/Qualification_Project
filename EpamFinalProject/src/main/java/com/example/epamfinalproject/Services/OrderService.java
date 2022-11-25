package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Implementations.Order_Implementation;
import com.example.epamfinalproject.Database.Interfaces.OrderDAO;
import com.example.epamfinalproject.Entities.Order;

import java.util.List;

public class OrderService {

    public static void createOrder(Order order) {
        OrderDAO orderDAO = new Order_Implementation();
        orderDAO.createOrder(order);
    }

    public static void updateOrderByID(Order order, long id) {
        OrderDAO orderDAO = new Order_Implementation();
        orderDAO.updateOrderByID(order, id);
    }

    public static void deleteOrderByID(long id) {
        OrderDAO orderDAO = new Order_Implementation();
        orderDAO.deleteOrderByID(id);
    }

    public static void deleteOrderByUserID(long id) {
        OrderDAO orderDAO = new Order_Implementation();
        orderDAO.deleteOrderByUserID(id);
    }

    public static void deleteOrderByShipID(long id) {
        OrderDAO orderDAO = new Order_Implementation();
        orderDAO.deleteOrderByShipID(id);
    }

    public static Order getOrderByID(long id) {
        OrderDAO orderDAO = new Order_Implementation();
        return orderDAO.getOrderByID(id);
    }

    public static Order getOrderByUserID(long id) {
        OrderDAO orderDAO = new Order_Implementation();
        return orderDAO.getOrderByUserID(id);
    }

    public static List<Order> getOrdersByShipID(long id) {
        OrderDAO orderDAO = new Order_Implementation();
        return orderDAO.getOrdersByShipID(id);
    }

}
