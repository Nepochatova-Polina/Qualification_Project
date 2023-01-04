package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.Route;

import java.util.List;

public interface RouteDAO {
    void createRoute(Route route);

    void updateRouteByID(long id, Route route);

    void deleteRouteByID(long id);

    Route getRouteByID(long id);

    List<Route> getAllRoutes();

    List<Route> getRoutesByDeparture(String departure);

    List<Route> getRoutesByDestination(String destination);

    List<Route> getRoutesByTransitTime(int transitTime);

    List<Route> getRoutesByDepartureAndDestination(String departure, String destination);


}
