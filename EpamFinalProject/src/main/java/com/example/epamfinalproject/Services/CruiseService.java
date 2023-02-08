package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Interfaces.CruiseDAO;
import com.example.epamfinalproject.Entities.Cruise;
import java.util.List;

public class CruiseService {

  private final CruiseDAO cruiseDAO;

  public CruiseService(CruiseDAO cruiseDAO) {
    this.cruiseDAO = cruiseDAO;
  }

  public void createCruise(Cruise cruise) {
    cruiseDAO.createCruise(cruise);
  }

  public void updateCruiseByID(Cruise cruise, long id) {
    cruiseDAO.updateCruiseByID(cruise, id);
  }

  public void deleteCruiseByID(long id) {
    cruiseDAO.deleteCruiseByID(id);
  }

  public void confirmCruiseByID(long id) {
    cruiseDAO.confirmCruiseByID(id);
  }

  public List<Cruise> getAllCruisesForPage(String query) {
    return cruiseDAO.getAllCruisesForPage(query);
  }

  public int getNumberOfActualCruises(String query) {
    return cruiseDAO.getNumberOfActualCruises(query);
  }

  public Cruise getCruiseByID(long id) {
    return cruiseDAO.getCruiseByID(id);
  }
}
