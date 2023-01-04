package com.example.epamfinalproject.Database.Shaper;

import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Entities.Ship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipShaper implements DataShaper<Ship> {
    @Override
    public Ship shapeData(ResultSet resultSet) throws SQLException {
        Ship ship = new Ship();
        ship.setId(resultSet.getLong(FieldKey.ENTITY_ID));
        ship.setName(resultSet.getString(FieldKey.SHIP_NAME));
        ship.setPassengerCapacity(resultSet.getInt(FieldKey.PASSENGER_CAPACITY));
        return ship;
    }

    @Override
    public List<Ship> shapeDataToList(ResultSet resultSet) throws SQLException {
        List<Ship> shipList = new ArrayList<>();
        if (resultSet.next()) {
            Ship ship = new Ship();
            ship.setId(resultSet.getLong(FieldKey.ENTITY_ID));
            ship.setName(resultSet.getString(FieldKey.SHIP_NAME));
            ship.setPassengerCapacity(resultSet.getInt(FieldKey.PASSENGER_CAPACITY));
            shipList.add(ship);
        }
        return shipList;
    }
}
