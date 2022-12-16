package com.example.epamfinalproject.Controllers.Servlets;

import com.example.epamfinalproject.Entities.User;
import com.example.epamfinalproject.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet(value = "/login", name = "loginServlet")
public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        request.setAttribute("message", "Error?  maybe");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        log.info("Received data from the registration.");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, String> messages = new HashMap<>();

        if (username == null || username.isEmpty()) {
            messages.put("username", "Please enter username");
        }else if (password == null || password.isEmpty()) {
            messages.put("password", "Please enter password");
        }
        if (messages.isEmpty()) {
            User user = UserService.getUserByName(request.getParameter("username"), request.getParameter("password"));
            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            } else {
                messages.put("login", "Unknown login, please try again");
            }
        }
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
