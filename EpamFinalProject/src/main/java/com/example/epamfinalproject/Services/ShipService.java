package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Implementations.Ship_Implementation;
import com.example.epamfinalproject.Database.Interfaces.ShipDAO;
import com.example.epamfinalproject.Entities.Ship;

import java.util.List;


public class ShipService {
    public void registerShip(Ship ship) {
        ShipDAO shipDAO = new Ship_Implementation();
        shipDAO.registerShip(ship);
    }

    public void updateShipByID(Ship ship, long id) {
        ShipDAO shipDAO = new Ship_Implementation();
        shipDAO.updateShipByID(ship, id);
    }

    public void deleteShipByID(long id) {
        ShipDAO shipDAO = new Ship_Implementation();
        shipDAO.deleteShipByID(id);
    }

    public Ship getShipByID(long id) {
        ShipDAO shipDAO = new Ship_Implementation();
        return shipDAO.getShipByID(id);
    }

    public List<Ship> getAllShips() {
        ShipDAO shipDAO = new Ship_Implementation();
        return shipDAO.getAllShips();
    }


}
