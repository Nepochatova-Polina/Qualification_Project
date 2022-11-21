package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.Route;

import java.util.List;

public interface RouteDAO {
    void createRoute(Route route);

    void updateRouteByID(long id, Route route);

    void deleteRouteByID(long id);

    Route findRouteByID(long id);

    List<Route>  findRouteByDeparture(String departure);

    List<Route> findRouteByDestination(String destination);

    List<Route> findRouteByTransitTime(int transitTime);

    List<Route> findRouteByDepartureAndDestination(String departure,String destination);


}
