package com.example.epamfinalproject.Controllers;

import com.example.epamfinalproject.Entities.*;
import com.example.epamfinalproject.Services.OrderService;
import com.oracle.wls.shaded.org.apache.xpath.operations.Or;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;

/**
 * Util class to set session parameters after each command
 * Also updates loggedUsers parameter if login/logout command executed
 */
public class  SessionUtility {

    /**
     * Sets ADMINISTRATOR parameters
     */
    static public void setUserParams(HttpServletRequest request, User user, List<User> users,
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
    static public void setUserParams(HttpServletRequest request, User user, List<Cruise> cruises) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.add(user.getLogin());
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
        session.setAttribute("cruises",cruises);
    }

    /**
     * Sets STAFF parameters
     */
    static public void setStaffParams(HttpServletRequest request, Staff staff, Ship ship){
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        session.setAttribute("staff",staff);
        session.setAttribute("ship",ship);
    }

    /**
     * Sets CRUISE parameters
     */
    static public void setCruiseParams(HttpServletRequest request,List<Cruise> cruises,
                                       List<Ship> ships, List<Staff> staff, List<Route> routes){
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("cruises",cruises);
        session.setAttribute("ships", ships);
        session.setAttribute("staff",staff);
        session.setAttribute("routes",routes);
    };

    /**
     * Sets ORDERS with their statuses after USER created one
     */
    static public void setSubmissions(HttpServletRequest request, User user, List<Order> orders){
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("orders", orders);
    }

    /**
     * Sets ORDERS with their statuses for ADMIN
     */
    static public void setSubmissions(HttpServletRequest request){
        OrderService orderService = new OrderService();
        HttpSession session = request.getSession();
        session.setAttribute("submissions", orderService.getAllOrders());
    }

}
