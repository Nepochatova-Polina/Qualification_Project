package com.example.epamfinalproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
  private long id;
  private String firstName;
  private String lastName;
  private long shipId;

  /**
   * Constructor - creating new object with specific values
   *
   * @param firstName First name of staff
   * @param lastName Last name of staff
   * @param shipId ID of the ship on which the staff is listed
   */
  public Staff(String firstName, String lastName, long shipId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.shipId = shipId;
  }
}
