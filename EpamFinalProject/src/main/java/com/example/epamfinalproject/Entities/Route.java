package com.example.epamfinalproject.Entities;

public class Route {
    private String departure;
    private String destination;
    private int distance;
//    The ship is going to be in transit 7 days
    private int transitTime;

    public Route(String departure, String destination, int distance, int transitTime) {
        this.departure = departure;
        this.destination = destination;
        this.distance = distance;
        this.transitTime = transitTime;
    }

    public Route() {
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTransitTime() {
        return transitTime;
    }

    public void setTransitTime(int transitTime) {
        this.transitTime = transitTime;
    }
}
