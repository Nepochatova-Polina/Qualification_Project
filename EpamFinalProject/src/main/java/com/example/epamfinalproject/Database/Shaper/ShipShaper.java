package com.example.epamfinalproject.Database.Shaper;

import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Entities.Ship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipShaper implements DataShaper<Ship> {
    /**
     * @param resultSet result of SQL query execution
     * @return new instance of Ship class filled with resultSet data
     * @throws SQLException if param is empty or some field does not exist
     */
    @Override
    public Ship shapeData(ResultSet resultSet) throws SQLException {
        Ship ship = new Ship();
        ship.setId(resultSet.getLong(FieldKey.ENTITY_ID));
        ship.setName(resultSet.getString(FieldKey.SHIP_NAME));
        ship.setPassengerCapacity(resultSet.getInt(FieldKey.PASSENGER_CAPACITY));
        return ship;
    }

    /**
     * @param resultSet result of SQL query execution
     * @return list filled with new instances of Ship class filled with resultSet data
     * @throws SQLException if param is empty or some field does not exist
     */
    @Override
    public List<Ship> shapeDataToList(ResultSet resultSet) throws SQLException {
        List<Ship> shipList = new ArrayList<>();
        while (resultSet.next()) {
            Ship ship = new Ship();
            ship.setId(resultSet.getLong(FieldKey.ENTITY_ID));
            ship.setName(resultSet.getString(FieldKey.SHIP_NAME));
            ship.setPassengerCapacity(resultSet.getInt(FieldKey.PASSENGER_CAPACITY));
            shipList.add(ship);
        }
        return shipList;
    }
}
