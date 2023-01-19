package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Interfaces.ShipDAO;
import com.example.epamfinalproject.Entities.Ship;
import java.util.List;

public class ShipService {

  private final ShipDAO shipDAO;

  public ShipService(ShipDAO shipDAO) {
    this.shipDAO = shipDAO;
  }

  public void registerShip(Ship ship) {
    shipDAO.registerShip(ship);
  }

  public void updateShipByID(Ship ship, long id) {
    shipDAO.updateShipByID(ship, id);
  }

  public Ship getShipByName(String name) {
    return shipDAO.getShipByName(name);
  }

  public List<Ship> getAllShips() {
    return shipDAO.getAllShips();
  }
}
