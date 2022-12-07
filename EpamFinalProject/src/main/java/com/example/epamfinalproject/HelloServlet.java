package com.example.epamfinalproject;

import com.example.epamfinalproject.Database.Implementations.User_Implementation;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User_Implementation ui = new User_Implementation();
        ui.registerUser(new User.UserBuilder()
                .firstName("Anna")
                .lastName("Olsef")
                .role(UserRole.PASSENGER)
                .login("anna_olsef")
                .password("olga12656")
                .build());

        request.setAttribute("message", "<script>alert('Hello gold')</script>");
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/hello.jsp");
        requestDispatcher.forward(request, response);

        response.setStatus(HttpServletResponse.SC_OK);

    }

}