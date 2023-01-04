package com.example.epamfinalproject.Database.Shaper;

import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Entities.*;
import com.example.epamfinalproject.Entities.Enums.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class orderShaper implements DataShaper<Order> {
    DataShaper<Cruise> cruiseShaper = new CruiseShaper();
    DataShaper<User> userShaper = new UserShaper();

    @Override
    public Order shapeData(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong(FieldKey.ENTITY_ID));
        order.setCruise(cruiseShaper.shapeData(resultSet));
        order.setUser(userShaper.shapeData(resultSet));
        order.setStatus(Status.fromString(resultSet.getString(FieldKey.ORDER_STATUS)));
        return order;
    }

    @Override
    public List<Order> shapeDataToList(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        Order order = new Order();
        if(resultSet.next()) {
            order.setId(resultSet.getLong(FieldKey.ENTITY_ID));
            order.setCruise(cruiseShaper.shapeData(resultSet));
            order.setUser(userShaper.shapeData(resultSet));
            order.setStatus(Status.fromString(resultSet.getString(FieldKey.ORDER_STATUS)));
            orderList.add(order);
        }
        return orderList;
    }
}
