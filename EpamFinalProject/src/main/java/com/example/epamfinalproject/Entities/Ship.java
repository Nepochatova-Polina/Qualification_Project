package com.example.epamfinalproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ship {
    private long id;
    private int passengerCapacity;
    private long route_id;
    private int numberOfPorts;
    LocalDate startOfTheCruise;
    LocalDate endOfTheCruise;
    List <User> staff;

    public Ship(int passengerCapacity, long route_id, int numberOfPorts, LocalDate startOfTheCruise, LocalDate endOfTheCruise) {
        this.passengerCapacity = passengerCapacity;
        this.route_id = route_id;
        this.numberOfPorts = numberOfPorts;
        this.startOfTheCruise = startOfTheCruise;
        this.endOfTheCruise = endOfTheCruise;
    }
}