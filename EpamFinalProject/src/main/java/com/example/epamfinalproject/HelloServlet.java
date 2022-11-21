package com.example.epamfinalproject;

import com.example.epamfinalproject.Database.Implementations.Route_Implementation;
import com.example.epamfinalproject.Database.Implementations.User_Implementation;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.Route;
import com.example.epamfinalproject.Entities.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Route> routeList;
        Route_Implementation route_implementation = new Route_Implementation();
//        route_implementation.createRoute(new Route("Tunis","Palermo",500,2));
//        Route route = route_implementation.findRouteByID(1);
//        System.out.println(route.getDistance());

        routeList = route_implementation.findRouteByDeparture("Tunis");
        System.out.println("DEPARTURE:");
        System.out.println("size:"+routeList.size());
        for (Route value : routeList) {
            System.out.println(value.getDistance());
        }
        routeList = route_implementation.findRouteByDestination("Palermo");
        System.out.println("DESTINATION:");
        System.out.println("size:"+routeList.size());
        for (Route value : routeList) {
            System.out.println(value.getDistance());
        }

        routeList = route_implementation.findRouteByDepartureAndDestination("Tunis","Palermo");
        System.out.println("DEPARTURE DESTINATION:");
        System.out.println("size:"+routeList.size());
        for (Route value : routeList) {
            System.out.println(value.getDistance());
        }

    }


    public void destroy() {
    }
}