package com.example.epamfinalproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ship {
    private long id;
    private String name;
    private int passengerCapacity;
    List <User> staff;

    public Ship(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
    }

    public Ship(long id, String name, int passengerCapacity) {
        this.id = id;
        this.name = name;
        this.passengerCapacity = passengerCapacity;
    }
}