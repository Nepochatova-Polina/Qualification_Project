package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.Ship;

import java.time.LocalDate;
import java.util.List;

public interface ShipDAO {

    void registerShip(Ship ship);

    void updateShipByID(Ship ship, long id);

    void deleteShipByID(long id);

    Ship getShipByID(long id);

    Ship getShipByName(String name);

    List<Ship> getAllShips();

    List<Ship> getShipsByDates(LocalDate start, LocalDate end);

}
