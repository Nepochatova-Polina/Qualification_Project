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
    DataShaper<Ship> shipShaper = new ShipShaper();
    DataShaper<Route> routeShaper = new RouteShaper();

    @Override
    public Cruise shapeData(ResultSet resultSet) throws SQLException {
        Cruise cruise = new Cruise();
        Ship ship;
        Route route;
        cruise.setId(resultSet.getLong(FieldKey.ENTITY_ID));
        cruise.setStartOfTheCruise(LocalDate.parse(resultSet.getString(FieldKey.CRUISE_LEAVING)));
        cruise.setEndOfTheCruise((LocalDate.parse(resultSet.getString(FieldKey.CRUISE_ARRIVING))));

        route = routeShaper.shapeData(resultSet);
        ship = shipShaper.shapeData(resultSet);

        StaffService staffService = new StaffService();
        ship.setStaff(staffService.getStaffByShipID(resultSet.getLong(FieldKey.SHIP_ID)));

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
            cruise.setStartOfTheCruise(LocalDate.parse(resultSet.getString(FieldKey.CRUISE_LEAVING)));
            cruise.setEndOfTheCruise((LocalDate.parse(resultSet.getString(FieldKey.CRUISE_ARRIVING))));

            route = routeShaper.shapeData(resultSet);
            ship = shipShaper.shapeData(resultSet);

            StaffService staffService = new StaffService();
            ship.setStaff(staffService.getStaffByShipID(resultSet.getLong(FieldKey.SHIP_ID)));

            cruise.setShip(ship);
            cruise.setRoute(route);
            cruiseList.add(cruise);
        }
        return cruiseList;
    }
}
