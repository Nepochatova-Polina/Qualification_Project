package com.example.epamfinalproject.Controllers.Servlets;

import com.example.epamfinalproject.Controllers.MessageKeys;
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

import static com.example.epamfinalproject.Utility.Validation.validateUserFields;


@WebServlet(value = "/signUp", name = "signUpServlet")
public class SignUpServlet extends HttpServlet {
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

        String firsName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!Objects.equals(password, confirmPassword)) {
            request.getSession().setAttribute("message", MessageKeys.SIGN_UP_INVALID+".password");
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
            return;
        }
        boolean result = validateUserFields(new User.UserBuilder()
                .firstName(firsName)
                .lastName(lastName)
                .login(login)
                .password(password).build());
        if(!result){
            request.getSession().setAttribute("message", MessageKeys.SIGN_UP_INVALID);
        }
        UserService userService = new UserService();
        User user = userService.getUserByLogin(login);
        if (user != null) {
                return;
            }

        userService.registerUser(new User
                .UserBuilder()
                .firstName(firsName)
                .lastName(lastName)
                .login(login)
                .password(password)
                .role(UserRole.CLIENT)
                .build());

        log.info("Registration completed successfully! ");
        log.info("Redirecting to Client Account page.");
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("clientAccount.jsp");
        requestDispatcher.forward(request, response);
        response.setStatus(javax.servlet.http.HttpServletResponse.SC_OK);

        request.getRequestDispatcher("signUp.jsp").forward(request, response);
    }
}
