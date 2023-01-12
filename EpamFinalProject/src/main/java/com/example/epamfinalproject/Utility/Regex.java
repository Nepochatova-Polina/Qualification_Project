package com.example.epamfinalproject.Utility;

/**
 * The class stores patterns for validating fields that the user enters
 */
public class Regex {

    //User SignUpCommand Regex
    public static final String USER_LOGIN = "^[A-Za-z0-9_]{4,}$";
    public static final String USER_FIRSTNAME = "^[A-ZЄІЇА-Я][a-zа-яёєії]+$";
    public static final String USER_LASTNAME = "^[A-ZЄІЇА-Я][a-zа-яёєії]+$";

    //Ship CreateCommand Regex
    public static final String SHIP_NAME = "^[A-Za-z0-9- [ \\t]]{3,}$";

    //Route CreateCommand Regex
    public static final String ROUTE_DEPARTURE = "^[A-Za-z0-9-]{3,}$";
    public static final String ROUTE_DESTINATION = "^[A-Za-z0-9-]{3,}$";

    //Cruise CreateCommand Regex
    public static final String CRUISE_NAME = "^[A-Za-z0-9- [ \\t]]{3,}$";
}
