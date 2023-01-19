package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Interfaces.CruiseDAO;
import com.example.epamfinalproject.Entities.Cruise;
import java.time.LocalDate;
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

  public List<Cruise> getAllCruisesForPage(int limit, int offset) {
    return cruiseDAO.getAllCruisesForPage(limit, offset);
  }

  public List<Cruise> getActualCruisesForPage(int limit, int offset) {
    return cruiseDAO.getActualCruisesForPage(limit, offset);
  }

  public List<Cruise> getActualCruises() {
    return cruiseDAO.getActualCruises();
  }

  public int getNumberOfActualCruises() {
    return cruiseDAO.getNumberOfActualCruises();
  }

  public Cruise getCruiseByID(long id) {
    return cruiseDAO.getCruiseByID(id);
  }

  public List<Cruise> getCruisesByShipID(long id) {
    return cruiseDAO.getCruisesByShipID(id);
  }

  public List<Cruise> getAllCruisesByDuration(int duration) {
    return cruiseDAO.getAllCruisesByDuration(duration);
  }

  public List<Cruise> getAllCruisesAfterDate(LocalDate date) {
    return cruiseDAO.getAllCruisesAfterDate(date);
  }

  public List<Cruise> getAllCruisesBeforeDate(LocalDate date) {
    return cruiseDAO.getAllCruisesBeforeDate(date);
  }

  public List<Cruise> getAllCruisesBetweenTwoDates(LocalDate start, LocalDate end) {
    return cruiseDAO.getAllCruisesBetweenTwoDates(start, end);
  }

  public List<Cruise> getAllCruisesByStartAndDuration(LocalDate start, int duration) {
    return cruiseDAO.getAllCruisesByStartAndDuration(start, duration);
  }

  public List<Cruise> getAllCruisesByEndAndDuration(LocalDate end, int duration) {
    return cruiseDAO.getAllCruisesByEndAndDuration(end, duration);
  }

  public List<Cruise> getAllCruisesByDatesAndDuration(
      LocalDate start, LocalDate end, int duration) {
    return cruiseDAO.getAllCruisesByDatesAndDuration(start, end, duration);
  }
}
