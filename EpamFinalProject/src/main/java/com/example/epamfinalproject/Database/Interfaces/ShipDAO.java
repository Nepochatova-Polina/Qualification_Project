package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.Ship;

import java.util.Date;
import java.util.List;

public interface ShipDAO {

    void createShip(Ship ship);

    Ship findShipByID(long id);

    void updateShipByID(Ship ship,long id);

    void deleteShipByID(long id);

    List<Ship> findShipsByRouteID(long id);

    List<Ship> findShipsByStartDate(Date startDate);

    List<Ship> findShipsByEndDate(Date endDate);

}
