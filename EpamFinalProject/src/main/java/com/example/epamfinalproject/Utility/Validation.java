package com.example.epamfinalproject.Utility;

import com.example.epamfinalproject.Entities.*;

import java.util.regex.Pattern;

public class Validation {

    public static boolean validateUserFields(User user) {
        return Pattern.compile(Regex.USER_LOGIN).matcher(user.getLogin()).find() &&
                Pattern.compile(Regex.USER_FIRSTNAME).matcher(user.getFirstName()).find() &&
                Pattern.compile(Regex.USER_LASTNAME).matcher(user.getLastName()).find();
    }
    public static boolean validateStaffFields(Staff staff) {
        return Pattern.compile(Regex.USER_FIRSTNAME).matcher(staff.getFirstName()).find() &&
                Pattern.compile(Regex.USER_LASTNAME).matcher(staff.getLastName()).find();
    }

    public static boolean validateShipFields(Ship ship) {
        return Pattern.compile(Regex.SHIP_NAME).matcher(ship.getName()).find();
    }

    public static boolean validateRouteFields(Route route) {
        return Pattern.compile(Regex.ROUTE_DEPARTURE).matcher(route.getDeparture()).find() &&
                Pattern.compile(Regex.ROUTE_DESTINATION).matcher(route.getDestination()).find();
    }
    public static boolean validateCruiseFields(Cruise cruise){
        return Pattern.compile(Regex.CRUISE_NAME).matcher(cruise.getName()).find();
    }
}
