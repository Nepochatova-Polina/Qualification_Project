package com.example.epamfinalproject.Utility;

import com.example.epamfinalproject.Entities.*;

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
     * Sets Session parameters for ADMINISTRATOR user
     *
     * @param request
     * @param user    administrator info
     * @param users   list of all users in system
     * @param cruises all actual cruises
     * @param orders  all unconfirmed orders
     * @param staff   all staff of the Company
     * @param ships   all ships of the Company
     * @param routes  all routes of the Company
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
     * Sets Session parameters for CLIENT user
     *
     * @param request
     * @param user    client info
     * @param cruises all actual cruises
     * @param orders  all User's orders
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
     * Sets Session parameter after creating new Cruise
     *
     * @param request
     * @param cruises all actual cruises
     */
    static public void setCruisesParams(HttpServletRequest request, List<Cruise> cruises) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("cruises", cruises);
    }

    /**
     * Sets Session parameters after creating new Ship
     *
     * @param request
     * @param cruise   The Cruise selected by the administrator for editing
     */
    static public void setCruiseParamsForAdmin(HttpServletRequest request, Cruise cruise) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("cruise", cruise);
    }

    /**
     * Sets Session parameters after creating new Staff
     *
     * @param request
     * @param staff   all staff of the Company
     */
    static public void setStaffParams(HttpServletRequest request, List<Staff> staff) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("staff", staff);
    }
    /**
     * Set information about the Cruise that the user has chosen
     *
     * @param request
     * @param user    Client info
     */
    static public void setCruiseParamsForClient(HttpServletRequest request, User user, Cruise cruise, int freeSeats) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("cruise", cruise);
        session.setAttribute("freeSeats", freeSeats);
    }

    /**
     * Sets ORDER with it status after CLIENT created one or after ADMINISTRATOR confirm one
     *
     * @param request
     * @param orders  list of orders
     */
    static public void setOrders(HttpServletRequest request, List<Order> orders) {
        HttpSession session = request.getSession();
        session.setAttribute("orders", orders);
    }

}
