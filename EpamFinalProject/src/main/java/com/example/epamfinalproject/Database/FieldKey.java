package com.example.epamfinalproject.Database;

/**
 * Defines fields names<p></p>
 * These names are used as rows for Database tables, and as entities parameters on JSP pages
 */
public class FieldKey {

    //User and Staff table fields
    public static final String ENTITY_ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    //User fields
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String CONFIRM_PASSWORD = "confirmPassword";
    public static final String ROLE = "role";
    public static final String PASSPORT = "passport_img";


    //Ships table field
    public static final String SHIP_NAME = "name";
    public static final String PASSENGER_CAPACITY = "passenger_capacity";

    //Route table fields
    public static final String DEPARTURE = "departure";
    public static final String DESTINATION = "destination";
    public static final String NUMBER_OF_PORTS = "number_of_ports";
    public static final String TRANSIT_TIME = "transit_time";

    //Order table fields
    public static final String ORDER_CRUISE_ID = "cruise_id";
    public static final String ORDER_USER_ID = "user_id";
    public static final String ORDER_SEATS = "number_of_seats";
    public static final String ORDER_TOTAL_PRICE = "price";
    public static final String ORDER_STATUS = "status";


    //Cruise table fields
    public static final String CRUISE_SHIP_ID = "ship_id";
    public static final String CRUISE_ROUTE_ID = "route_id";
    public static final String CRUISE_NAME = "cruise_name";
    public static final String CRUISE_PRICE = "price";
    public static final String CRUISE_LEAVING = "start_date";
    public static final String CRUISE_ARRIVING = "end_date";
    public static final String CRUISE_DURATION = "duration";
}
