package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.Cruise;

import java.time.LocalDate;
import java.util.List;

public interface CruiseDAO {
    void createCruise(Cruise cruise);

    void updateCruiseByID(Cruise cruise, long id);

    void deleteCruiseByID(long id);

    int getNumberOfActualCruises();

    Cruise getCruiseByID(long id);

    List<Cruise> getCruisesByShipID(long id);

    List<Cruise> getAllCruises();

    List<Cruise> getActualCruises(int limit,int offset);

    List<Cruise> getAllCruisesByDuration(int duration);

    List<Cruise> getAllCruisesAfterDate(LocalDate date);

    List<Cruise> getAllCruisesBeforeDate(LocalDate date);

    List<Cruise> getAllCruisesBetweenTwoDates(LocalDate start, LocalDate end);

    List<Cruise> getAllCruisesByStartAndDuration(LocalDate start, int duration);

    List<Cruise> getAllCruisesByEndAndDuration(LocalDate end, int duration);

    List<Cruise> getAllCruisesByDatesAndDuration(LocalDate start, LocalDate end, int duration);

}
