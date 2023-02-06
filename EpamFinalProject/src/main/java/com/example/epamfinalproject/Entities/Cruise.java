package com.example.epamfinalproject.Entities;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cruise {
  private long id;
  private String name;
  private Ship ship;
  private Route route;
  private int price;
  private String deleted;
  private boolean confirmed;
  LocalDate startOfTheCruise;
  LocalDate endOfTheCruise;

  /**
   * Constructor - creating new object with specific values
   *
   * @param ship Cruise ship
   * @param route The route the ship takes
   * @param name Cruise title
   * @param price Price for one seat on the ship
   * @param startOfTheCruise Date of leaving
   * @param endOfTheCruise Day of arriving at destination
   */
  public Cruise(
      Ship ship,
      Route route,
      String name,
      int price,
      LocalDate startOfTheCruise,
      LocalDate endOfTheCruise) {
    this.ship = ship;
    this.route = route;
    this.name = name;
    this.price = price;
    this.startOfTheCruise = startOfTheCruise;
    this.endOfTheCruise = endOfTheCruise;
  }
}
