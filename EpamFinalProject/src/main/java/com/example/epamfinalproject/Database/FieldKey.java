package com.example.epamfinalproject.Database;

public class FieldKey {

    //    User and Staff table fields
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    //    User fields
    public static final String USER_ID = "user_id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String PASSPORT = "passport_img";

    //   Staff table fields
    public static final String SHIP_ID = "ship_id";

    //    Ships table field
    public static final String SHIP_NAME = "name";
    public static final String PASSENGER_CAPACITY = "passenger_capacity";

    //    Route table fields
    public static final String DEPARTURE = "departure";
    public static final String DESTINATION = "destination";
    public static final String NUMBER_OF_PORTS = "number_of_ports";
    public static final String TRANSIT_TIME = "transit_time";

//    Order table fields
    public static final String ORDER_CRUISE = "cruise_id";
    public static final String ORDER_USER = "user_id";
    public static final String STATUS = "status";


//    Cruise table fields
    public static final String CRUISE_SHIP = "ship_id";
    public static final String CRUISE_ROUTE = "route_id";
    public static final String LEAVING = "start_date";
    public static final String ARRIVING = "end_date";
}
