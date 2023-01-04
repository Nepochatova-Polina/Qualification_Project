package com.example.epamfinalproject.Database.Shaper;

import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Entities.Route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteShaper implements DataShaper<Route>{
    @Override
    public Route shapeData(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getInt(FieldKey.ENTITY_ID));
        route.setDeparture(resultSet.getString(FieldKey.DEPARTURE));
        route.setDestination(resultSet.getString(FieldKey.DESTINATION));
        route.setNumberOfPorts(resultSet.getInt(FieldKey.NUMBER_OF_PORTS));
        route.setTransitTime(resultSet.getInt(FieldKey.TRANSIT_TIME));
        return route;
    }

    @Override
    public List<Route> shapeDataToList(ResultSet resultSet) throws SQLException {
        List<Route> routeList = new ArrayList<>();
        Route route = new Route();
        if (resultSet.next()) {
            route.setId(resultSet.getInt(FieldKey.ENTITY_ID));
            route.setDeparture(resultSet.getString(FieldKey.DEPARTURE));
            route.setDestination(resultSet.getString(FieldKey.DESTINATION));
            route.setNumberOfPorts(resultSet.getInt(FieldKey.NUMBER_OF_PORTS));
            route.setTransitTime(resultSet.getInt(FieldKey.TRANSIT_TIME));
            routeList.add(route);
        }
        return routeList;    }
}
