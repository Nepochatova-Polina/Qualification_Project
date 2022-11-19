package com.example.epamfinalproject;

import com.example.epamfinalproject.Database.Implementations.User_Implementation;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User_Implementation implementation = new User_Implementation();
        User user1 = new User.UserBuilder()
                .firstName("Ivav")
                .lastName("ivanov")
                .login("Ivan_Ivanov")
                .role(UserRole.PASSENGER)
                .build();
//        List<User> user = implementation.getPassengerUsers();
        User user2 = implementation.getUser("hooks","jfikwhfj");
        System.out.println(user2.getId());
    }

    public void destroy() {
    }
}