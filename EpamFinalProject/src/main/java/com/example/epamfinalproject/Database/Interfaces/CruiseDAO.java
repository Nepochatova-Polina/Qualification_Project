package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.Cruise;

import java.util.List;

public interface CruiseDAO {
  void createCruise(Cruise cruise);

  void updateCruiseByID(Cruise cruise, long id);

  void deleteCruiseByID(long id);

  void confirmCruiseByID(long id);

  int getNumberOfActualCruises(String query);

  Cruise getCruiseByID(long id);

  List<Cruise> getAllCruisesForPage(String query);

}
