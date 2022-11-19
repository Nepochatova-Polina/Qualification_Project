package com.example.epamfinalproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CruiseShip {
    private int passengerCapacity;
    private Route route;
    private int numberOfPorts;
    Date startOfTheCruise;
    Date endOfTheCruise;
    List <User> staff;
}