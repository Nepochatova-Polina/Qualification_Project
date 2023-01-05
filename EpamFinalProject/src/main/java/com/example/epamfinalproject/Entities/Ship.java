package com.example.epamfinalproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
     * @param name              Name of the ship
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
     * @param id                primary Key for Ship
     * @param name              Name of the ship
     * @param passengerCapacity Total number of seats
     * @see Ship#Ship(String, int)
     */
    public Ship(long id, String name, int passengerCapacity) {
        this.id = id;
        this.name = name;
        this.passengerCapacity = passengerCapacity;
    }

}