package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Implementations.Route_Implementation;
import com.example.epamfinalproject.Database.Interfaces.RouteDAO;
import com.example.epamfinalproject.Entities.Route;

import java.util.List;

public class RouteService {
    public void createRoute(Route route) {
        RouteDAO routeDAO = new Route_Implementation();
        routeDAO.createRoute(route);
    }

    public void updateRouteByID(Route route, long id) {
        RouteDAO routeDAO = new Route_Implementation();
        routeDAO.updateRouteByID(id, route);
    }

    public void deleteRouteByID(long id) {
        RouteDAO routeDAO = new Route_Implementation();
        routeDAO.deleteRouteByID(id);
    }
    public List<Route> getAllRoutes() {
        RouteDAO routeDAO = new Route_Implementation();
        return routeDAO.getAllRoutes();
    }
    public Route getRouteByID(long id) {
        RouteDAO routeDAO = new Route_Implementation();
        return routeDAO.getRouteByID(id);
    }

    public List<Route> getRouteByDeparture(String depature) {
        RouteDAO routeDAO = new Route_Implementation();
        return routeDAO.getRoutesByDeparture(depature);
    }

    public List<Route> getRouteByDestination(String destination) {
        RouteDAO routeDAO = new Route_Implementation();
        return routeDAO.getRoutesByDestination(destination);
    }

    public List<Route> getRouteByTransitTime(int transitTime) {
        RouteDAO routeDAO = new Route_Implementation();
        return routeDAO.getRoutesByTransitTime(transitTime);
    }

    public List<Route> getRouteByDepartureAndDestination(String departure, String destination) {
        RouteDAO routeDAO = new Route_Implementation();
        return routeDAO.getRoutesByDepartureAndDestination(departure, destination);
    }

}
