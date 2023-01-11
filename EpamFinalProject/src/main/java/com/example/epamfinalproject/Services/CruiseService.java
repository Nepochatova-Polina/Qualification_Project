package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Implementations.Cruise_Implementation;
import com.example.epamfinalproject.Database.Interfaces.CruiseDAO;
import com.example.epamfinalproject.Entities.Cruise;

import java.time.LocalDate;
import java.util.List;

public class CruiseService {
    public void createCruise(Cruise cruise) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        cruiseDAO.createCruise(cruise);
    }

    public void updateCruiseByID(Cruise cruise, long id) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        cruiseDAO.updateCruiseByID(cruise, id);
    }

    public void deleteCruiseByID(long id) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        cruiseDAO.deleteCruiseByID(id);
    }

    public List<Cruise> getAllCruises() {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getAllCruises();
    }

    public List<Cruise> getActualCruises(int limit, int offset) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getActualCruises(limit,offset);
    }

    public int getNumberOfActualCruises() {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getNumberOfActualCruises();
    }

    public Cruise getCruiseByID(long id) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getCruiseByID(id);
    }

    public List<Cruise> getCruisesByShipID(long id) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getCruisesByShipID(id);
    }

    public List<Cruise> getAllCruisesByDuration(int duration) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getAllCruisesByDuration(duration);
    }

    public List<Cruise> getAllCruisesAfterDate(LocalDate date) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getAllCruisesAfterDate(date);
    }

    public List<Cruise> getAllCruisesBeforeDate(LocalDate date) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getAllCruisesBeforeDate(date);
    }

    public List<Cruise> getAllCruisesBetweenTwoDates(LocalDate start, LocalDate end) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getAllCruisesBetweenTwoDates(start, end);
    }

    public List<Cruise> getAllCruisesByStartAndDuration(LocalDate start, int duration) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getAllCruisesByStartAndDuration(start, duration);
    }

    public List<Cruise> getAllCruisesByEndAndDuration(LocalDate end, int duration) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getAllCruisesByEndAndDuration(end, duration);
    }

    public List<Cruise> getAllCruisesByDatesAndDuration(LocalDate start, LocalDate end, int duration) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getAllCruisesByDatesAndDuration(start, end, duration);
    }
}
