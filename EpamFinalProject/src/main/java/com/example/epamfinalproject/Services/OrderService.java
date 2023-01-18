package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Implementations.Order_Implementation;
import com.example.epamfinalproject.Database.Interfaces.OrderDAO;
import com.example.epamfinalproject.Entities.Enums.Status;
import com.example.epamfinalproject.Entities.Order;

import java.util.List;

public class OrderService {

    public void createOrder(Order order) {
        OrderDAO orderDAO = new Order_Implementation();
        orderDAO.createOrder(order);
    }

    public void updateOrderByID(Order order, long id) {
        OrderDAO orderDAO = new Order_Implementation();
        orderDAO.updateOrderByID(order, id);
    }

    public void deleteOrderByID(long id) {
        OrderDAO orderDAO = new Order_Implementation();
        orderDAO.deleteOrderByID(id);
    }

    public void deleteOrderByUserID(long id) {
        OrderDAO orderDAO = new Order_Implementation();
        orderDAO.deleteOrderByUserID(id);
    }

    public void deleteOrderByShipID(long id) {
        OrderDAO orderDAO = new Order_Implementation();
        orderDAO.deleteOrderByShipID(id);
    }

    public Order getOrderByID(long id) {
        OrderDAO orderDAO = new Order_Implementation();
        return orderDAO.getOrderByID(id);
    }

    public List<Order> getAllOrders() {
        OrderDAO orderDAO = new Order_Implementation();
        return orderDAO.getAllOrders();
    }
    public List<Order> getAllUnconfirmedOrders() {
        OrderDAO orderDAO = new Order_Implementation();
        return orderDAO.getAllUnconfirmedOrders();
    }

    public List<Order> getOrdersByUserID(long id) {
        OrderDAO orderDAO = new Order_Implementation();
        return orderDAO.getOrdersByUserID(id);
    }

    public List<Order> getOrdersByShipID(long id) {
        OrderDAO orderDAO = new Order_Implementation();
        return orderDAO.getOrdersByShipID(id);
    }
    public int getBookedSeatsByCruiseID(long id){
        OrderDAO orderDAO = new Order_Implementation();
        return orderDAO.getBookedSeatsByCruiseID(id);
    }

    public void confirmOrderByID(long id){
        OrderDAO orderDAO = new Order_Implementation();
        orderDAO.confirmOrderByID(id);
    }

}
