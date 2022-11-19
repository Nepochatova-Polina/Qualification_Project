package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.Route;

public interface RouteDAO {
    void createRoute(Route route);

    void updateRouteByID(long id, Route route);

    void deleteRouteByID(long id);

    Route findRouteByID(long id);

    Route findRouteByDeparture(String departure);

    Route findRouteByDestination(String destination);

    Route findRouteByTransitTime(int transitTime);
}
