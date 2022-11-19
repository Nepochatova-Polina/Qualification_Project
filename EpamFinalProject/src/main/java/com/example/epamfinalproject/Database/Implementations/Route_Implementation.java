package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.Interfaces.RouteDAO;
import com.example.epamfinalproject.Entities.Route;

public class Route_Implementation implements RouteDAO {

    @Override
    public void createRoute(Route route) {

    }

    @Override
    public void updateRouteByID(long id, Route route) {

    }

    @Override
    public void deleteRouteByID(long id) {

    }

    @Override
    public Route findRouteByID(long id) {
        return null;
    }

    @Override
    public Route findRouteByDeparture(String departure) {
        return null;
    }

    @Override
    public Route findRouteByDestination(String destination) {
        return null;
    }

    @Override
    public Route findRouteByTransitTime(int transitTime) {
        return null;
    }
}
