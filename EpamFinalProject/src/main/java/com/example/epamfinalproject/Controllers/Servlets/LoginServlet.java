package com.example.epamfinalproject.Controllers.Servlets;

import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.User;
import com.example.epamfinalproject.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Logger;

@WebServlet(value = "/login", name = "loginServlet")
public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        log.info("Received data from the login form.");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = new UserService();
            User user = userService.getUserByName(login, password);
            if (user != null) {
                log.info("Received info about the user in the servlet.");
                Cookie loginCookie = new Cookie("user", user.getLogin());
                Cookie passwordCookie = new Cookie("password", password);
                Cookie roleCookie = new Cookie("role", user.getRole().toString());
                loginCookie.setPath("/");
                passwordCookie.setPath("/");
                roleCookie.setPath("/");
                response.addCookie(loginCookie);
                response.addCookie(passwordCookie);
                response.addCookie(roleCookie);

                log.info("Redirecting to " + user.getRole().toString());
                if (user.getRole() == UserRole.CLIENT) {
                    response.sendRedirect("reservation.jsp");
                } else {
                    response.sendRedirect("/AdminServlet");
                }
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                log.info("User doesn't exist");
            }

        request.getSession().setAttribute("message", MessageKeys.LOGIN_INVALID);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
