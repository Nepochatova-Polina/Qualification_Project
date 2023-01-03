package com.example.epamfinalproject.Controllers;

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
     * Sets ADMIN parameters
     */
    static public void setUserParams(HttpServletRequest request, User user, List<User> users, List<Ship> ship) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.add(user.getLogin());
        session.setAttribute("users", users);
        session.setAttribute("ships", ship);
//        session.setAttribute("faculties", faculties);
//        session.setAttribute("submissions", submissions);
//        session.setAttribute("subjects", subjects);
    }

    /**
     * Sets CLIENT parameters
     */
    static public void setUserParams(HttpServletRequest request,User user, List<Order> orders) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.add(user.getLogin());
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
//        session.setAttribute("faculties", faculties);
    }

    /**
     * Sets STAFF parameters
     */
    static public void setStaffParams(HttpServletRequest request, Staff staff, Ship ship){
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
    }

    /**
     * Sets SHIP parameters
     */
    static public void setShipParams(HttpServletRequest request, Ship ship, List<Staff> staff, Route route){
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("ship", ship);
        session.setAttribute("staff",staff);
        session.setAttribute("route",route);
    };
}
