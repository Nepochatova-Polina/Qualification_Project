package com.example.epamfinalproject.Entities;

import java.util.Date;
import java.util.List;


public class CruiseShip {
    private int passengerCapacity;
    private Route route;
    private int numberOfPorts;
    Date startOfTheCruise;
    Date endOfTheCruise;
    List <User> staff;

    public CruiseShip(int passengerCapacity, Route route, int numberOfPorts, Date startOfTheCruise, Date endOfTheCruise, List<User> staff) {
        this.passengerCapacity = passengerCapacity;
        this.route = route;
        this.numberOfPorts = numberOfPorts;
        this.startOfTheCruise = startOfTheCruise;
        this.endOfTheCruise = endOfTheCruise;
        this.staff = staff;
    }

    public CruiseShip() {
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getNumberOfPorts() {
        return numberOfPorts;
    }

    public void setNumberOfPorts(int numberOfPorts) {
        this.numberOfPorts = numberOfPorts;
    }

    public Date getStartOfTheCruise() {
        return startOfTheCruise;
    }

    public void setStartOfTheCruise(Date startOfTheCruise) {
        this.startOfTheCruise = startOfTheCruise;
    }

    public Date getEndOfTheCruise() {
        return endOfTheCruise;
    }

    public void setEndOfTheCruise(Date endOfTheCruise) {
        this.endOfTheCruise = endOfTheCruise;
    }

    public List<User> getStaff() {
        return staff;
    }

    public void setStaff(List<User> staff) {
        this.staff = staff;
    }
}
