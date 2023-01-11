package com.example.epamfinalproject.Controllers;

import com.example.epamfinalproject.Entities.*;
import com.example.epamfinalproject.Services.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;

/**
 * Util class to set session parameters after each command
 * Also updates loggedUsers parameter if login/logout command executed
 */
public class SessionUtility {

    /**
     * Sets ADMINISTRATOR parameters
     */
    static public void setParamsForAdmin(HttpServletRequest request, User user, List<User> users,
                                         List<Cruise> cruises, List<Order> orders, List<Staff> staff, List<Ship> ships,
                                         List<Route> routes) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.add(user.getLogin());
        session.setAttribute("users", users);
        session.setAttribute("cruises", cruises);
        session.setAttribute("orders", orders);
        session.setAttribute("staff", staff);
        session.setAttribute("ships", ships);
        session.setAttribute("routes", routes);
    }

    /**
     * Sets CLIENT parameters
     */
    static public void setParamsForClient(HttpServletRequest request, User user, List<Cruise> cruises, List<Order> orders) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.add(user.getLogin());
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
        session.setAttribute("cruises", cruises);
        session.setAttribute("orders", orders);
    }


    /**
     * Sets CRUISE parameters
     */
    static public void setCruisesParams(HttpServletRequest request, List<Cruise> cruises) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("cruises", cruises);
    }

    ;

    /**
     * Set CRUISE information which User chose
     */
    static public void setCruiseParamsForClient(HttpServletRequest request, User user, Cruise cruise, int freeSeats, List<Order> orders) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("cruise", cruise);
        session.setAttribute("freeSeats", freeSeats);
        session.setAttribute("orders",orders);
    }

    /**
     * Sets ORDER with it status after USER created one
     */
    static public void setOrdersForClient(HttpServletRequest request, User user, List<Order> orders) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("orders", orders);
    }

    /**
     * Sets ORDERS with their statuses for ADMIN
     */
    static public void setOrdersForAdmin(HttpServletRequest request) {
        OrderService orderService = new OrderService();
        HttpSession session = request.getSession();
        session.setAttribute("orders", orderService.getAllOrders());
    }

}
