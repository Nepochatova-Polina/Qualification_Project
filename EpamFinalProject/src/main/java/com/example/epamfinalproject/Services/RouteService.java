package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Implementations.Route_Implementation;
import com.example.epamfinalproject.Database.Interfaces.RouteDAO;
import com.example.epamfinalproject.Entities.Route;

import java.util.List;

public class RouteService {
    public static void createRoute(Route route) {
        RouteDAO routeDAO = new Route_Implementation();
        routeDAO.createRoute(route);
    }

    public static void updateRouteByID(Route route, long id) {
        RouteDAO routeDAO = new Route_Implementation();
        routeDAO.updateRouteByID(id, route);
    }

    public static void deleteRouteByID(long id) {
        RouteDAO routeDAO = new Route_Implementation();
        routeDAO.deleteRouteByID(id);
    }

    public static Route getRouteByID(long id) {
        RouteDAO routeDAO = new Route_Implementation();
        return routeDAO.getRouteByID(id);
    }

    public static List<Route> getRouteByDeparture(String depature) {
        RouteDAO routeDAO = new Route_Implementation();
        return routeDAO.getRouteByDeparture(depature);
    }

    public static List<Route> getRouteByDestination(String destination) {
        RouteDAO routeDAO = new Route_Implementation();
        return routeDAO.getRouteByDestination(destination);
    }

    public static List<Route> getRouteByTransitTime(int transitTime) {
        RouteDAO routeDAO = new Route_Implementation();
        return routeDAO.getRouteByTransitTime(transitTime);
    }

    public static List<Route> getRouteByDepartureAndDestination(String departure, String destination) {
        RouteDAO routeDAO = new Route_Implementation();
        return routeDAO.getRouteByDepartureAndDestination(departure, destination);
    }

}
