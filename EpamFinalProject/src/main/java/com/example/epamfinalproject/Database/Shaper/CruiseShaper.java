package com.example.epamfinalproject.Database.Shaper;

import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Route;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Services.StaffService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CruiseShaper implements DataShaper<Cruise> {

    @Override
    public Cruise shapeData(ResultSet resultSet) throws SQLException {
        Cruise cruise = new Cruise();
        Ship ship;
        Route route;
        cruise.setId(resultSet.getLong(FieldKey.ENTITY_ID));
        cruise.setPrice(resultSet.getInt(FieldKey.CRUISE_PRICE));
        cruise.setStartOfTheCruise(LocalDate.parse(resultSet.getString(FieldKey.CRUISE_LEAVING)));
        cruise.setEndOfTheCruise((LocalDate.parse(resultSet.getString(FieldKey.CRUISE_ARRIVING))));

        route = routeShaper(resultSet);
        ship = shipShaper(resultSet);

        StaffService staffService = new StaffService();
        ship.setStaff(staffService.getStaffByShipID(resultSet.getLong(FieldKey.CRUISE_SHIP_ID)));

        cruise.setShip(ship);
        cruise.setRoute(route);

        return cruise;
    }

    @Override
    public List<Cruise> shapeDataToList(ResultSet resultSet) throws SQLException {
        List<Cruise> cruiseList = new ArrayList<>();
        Cruise cruise = new Cruise();
        Ship ship;
        Route route;
        if (resultSet.next()) {
            cruise.setId(resultSet.getLong(FieldKey.ENTITY_ID));
            cruise.setPrice(resultSet.getInt(FieldKey.CRUISE_PRICE));
            cruise.setStartOfTheCruise(LocalDate.parse(resultSet.getString(FieldKey.CRUISE_LEAVING)));
            cruise.setEndOfTheCruise((LocalDate.parse(resultSet.getString(FieldKey.CRUISE_ARRIVING))));

            route = routeShaper(resultSet);
            ship = shipShaper(resultSet);

            StaffService staffService = new StaffService();
            ship.setStaff(staffService.getStaffByShipID(resultSet.getLong(FieldKey.CRUISE_SHIP_ID)));

            cruise.setShip(ship);
            cruise.setRoute(route);
            cruiseList.add(cruise);
        }
        return cruiseList;
    }

    private Ship shipShaper(ResultSet resultSet) throws SQLException {
        Ship ship = new Ship();
        ship.setId(resultSet.getLong(FieldKey.CRUISE_SHIP_ID));
        ship.setName(resultSet.getString(FieldKey.SHIP_NAME));
        ship.setPassengerCapacity(resultSet.getInt(FieldKey.PASSENGER_CAPACITY));
        return ship;
    }
    private Route routeShaper(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getInt(FieldKey.CRUISE_ROUTE_ID));
        route.setDeparture(resultSet.getString(FieldKey.DEPARTURE));
        route.setDestination(resultSet.getString(FieldKey.DESTINATION));
        route.setNumberOfPorts(resultSet.getInt(FieldKey.NUMBER_OF_PORTS));
        route.setTransitTime(resultSet.getInt(FieldKey.TRANSIT_TIME));
        return route;
    }
}
