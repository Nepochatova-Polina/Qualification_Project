package com.example.epamfinalproject.Controllers.Servlets;

import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.User;
import com.example.epamfinalproject.Services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@WebServlet(value = "/registration", name = "registrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("signUp.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        log.info("Received data from the registration form.");
        int errorCount = 0;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (!Objects.equals(password, confirmPassword)) {
            errorCount++;
            request.setAttribute("passwordError", "Confirmation error! Passwords are not equal");
        }
        if (UserService.getUserByUsername(username) != null) {
            errorCount++;
            request.setAttribute("usernameError", "Username Error! User with same username has already created");
        }
        if (errorCount == 0) {
            UserService.registerUser(new User
                    .UserBuilder()
                    .firstName(request.getParameter("firstname"))
                    .lastName(request.getParameter("lastname"))
                    .login(request.getParameter("username"))
                    .password(password)
                    .role(UserRole.CLIENT)
                    .build());

            log.info("Registration completed successfully! ");
            log.info("Redirecting to Client Account page.");
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("clientAccount.jsp");
            requestDispatcher.forward(request, response);
            response.setStatus(javax.servlet.http.HttpServletResponse.SC_OK);
        }
        request.getRequestDispatcher("signUp.jsp").forward(request, response);
    }
}
