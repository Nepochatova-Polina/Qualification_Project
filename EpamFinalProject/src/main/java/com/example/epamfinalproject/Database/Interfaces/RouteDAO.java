package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.Route;

import java.util.List;

public interface RouteDAO {
    void createRoute(Route route);

    void updateRouteByID(long id, Route route);

    void deleteRouteByID(long id);

    Route getRouteByID(long id);

    List<Route> getRouteByDeparture(String departure);

    List<Route> getRouteByDestination(String destination);

    List<Route> getRouteByTransitTime(int transitTime);

    List<Route> getRouteByDepartureAndDestination(String departure, String destination);


}
