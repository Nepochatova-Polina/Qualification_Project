package com.example.epamfinalproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cruise {
    private long id;
    private Ship ship;
    private Route route;
    LocalDate startOfTheCruise;
    LocalDate endOfTheCruise;

    public Cruise(Ship ship, Route route, LocalDate startOfTheCruise, LocalDate endOfTheCruise) {
        this.ship = ship;
        this.route = route;
        this.startOfTheCruise = startOfTheCruise;
        this.endOfTheCruise = endOfTheCruise;
    }
}
