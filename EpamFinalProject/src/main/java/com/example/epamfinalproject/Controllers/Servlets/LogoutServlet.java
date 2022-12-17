package com.example.epamfinalproject.Controllers.Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(value = "/logout", name = "logoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LogoutServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Received logout request.");
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        Cookie loginCookie = new javax.servlet.http.Cookie("user", "");
        Cookie passwordCookie = new javax.servlet.http.Cookie("password", "");
        Cookie roleCookie = new javax.servlet.http.Cookie("role", "");
        loginCookie.setPath("/");
        passwordCookie.setPath("/");
        roleCookie.setPath("/");
        loginCookie.setMaxAge(0);
        passwordCookie.setMaxAge(0);
        roleCookie.setMaxAge(0);
        response.addCookie(loginCookie);
        response.addCookie(passwordCookie);
        response.addCookie(roleCookie);
        response.sendRedirect("http://localhost:8080/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
