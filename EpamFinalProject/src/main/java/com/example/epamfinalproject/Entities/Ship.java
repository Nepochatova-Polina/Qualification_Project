package com.example.epamfinalproject.Entities;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ship {
  private long id;
  private String name;
  private int passengerCapacity;
  List<Staff> staff;

  /**
   * Constructor - creating new object with specific values
   *
   * @param name Name of the ship
   * @param passengerCapacity Total number of seats
   * @see Ship#Ship(long, String, int)
   */
  public Ship(String name, int passengerCapacity) {
    this.name = name;
    this.passengerCapacity = passengerCapacity;
  }

  /**
   * Constructor - creating new object with specific values
   *
   * @param id primary Key for Ship
   * @param name Name of the ship
   * @param passengerCapacity Total number of seats
   * @see Ship#Ship(String, int)
   */
  public Ship(long id, String name, int passengerCapacity) {
    this.id = id;
    this.name = name;
    this.passengerCapacity = passengerCapacity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ship ship = (Ship) o;
    return this.getId() == ship.getId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, passengerCapacity);
  }
}
