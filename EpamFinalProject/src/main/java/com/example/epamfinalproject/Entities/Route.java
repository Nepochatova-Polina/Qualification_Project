package com.example.epamfinalproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    private long id;
    private String departure;
    private String destination;
    private int distance;
    private int transitTime;

    public Route(String departure, String destination, int distance, int transitTime) {
        this.departure = departure;
        this.destination = destination;
        this.distance = distance;
        this.transitTime = transitTime;
    }
}
