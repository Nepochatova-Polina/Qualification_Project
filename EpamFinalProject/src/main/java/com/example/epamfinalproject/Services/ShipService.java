package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Implementations.Ship_Implementation;
import com.example.epamfinalproject.Database.Interfaces.ShipDAO;
import com.example.epamfinalproject.Entities.Ship;

import java.time.LocalDate;
import java.util.List;

public class ShipService {
    public static void registerShip(Ship ship) {
        ShipDAO shipDAO = new Ship_Implementation();
        shipDAO.registerShip(ship);
    }

    public static void updateShipByID(Ship ship, long id) {
        ShipDAO shipDAO = new Ship_Implementation();
        shipDAO.updateShipByID(ship, id);
    }

    public static void deleteShipByID(long id) {
        ShipDAO shipDAO = new Ship_Implementation();
        shipDAO.deleteShipByID(id);
    }

    public static Ship getShipByID(long id) {
        ShipDAO shipDAO = new Ship_Implementation();
        return shipDAO.getShipByID(id);
    }

    public static List<Ship> getShipByRouteID(long id) {
        ShipDAO shipDAO = new Ship_Implementation();
        return shipDAO.getShipsByRouteID(id);
    }

    public static List<Ship> getShipsByStartDate(LocalDate startDate) {
        ShipDAO shipDAO = new Ship_Implementation();
        return shipDAO.getShipsByStartDate(startDate);
    }

    public static List<Ship> getShipsByEndDate(LocalDate endDate) {
        ShipDAO shipDAO = new Ship_Implementation();
        return shipDAO.getShipsByEndDate(endDate);
    }

}
