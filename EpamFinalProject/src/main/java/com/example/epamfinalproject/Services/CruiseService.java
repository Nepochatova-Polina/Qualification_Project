package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Implementations.Cruise_Implementation;
import com.example.epamfinalproject.Database.Interfaces.CruiseDAO;
import com.example.epamfinalproject.Entities.Cruise;

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
    public Cruise getCruiseByID(long id) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getCruiseByID(id);
    }

    public List<Cruise> getCruisesByShipID(long id) {
        CruiseDAO cruiseDAO = new Cruise_Implementation();
        return cruiseDAO.getCruisesByShipID(id);
    }

}
