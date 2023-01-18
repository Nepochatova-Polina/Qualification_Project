package com.example.epamfinalproject.Database.Queries;

public class RouteQueries {
    public static final String CREATE_ROUTE_QUERY = "insert into routes(departure, destination, transit_time) values (?,?,?)";

    public static final String UPDATE_ROUTE_QUERY = "update routes set departure = ?,destination = ?,transit_time= ? where id = ?";

    public static final String DELETE_ROUTE_QUERY = "delete from routes where id = ?";

    public static final String GET_ROUTE_BY_ID_QUERY = "select * from routes where id = ?";

    public static final String GET_ROUTE_BY_DEPARTURE_QUERY = "select * from routes where departure = ?";

    public static final String GET_ROUTE_BY_DESTINATION_QUERY = "select * from routes where destination = ?";

    public static final String GET_ROUTE_BY_DEPARTURE_AND_DESTINATION_QUERY = "select * from routes where departure = ? and destination = ?";

    public static final String GET_ROUTE_BY_TRANSIT_TIME_QUERY = "select * from routes where transit_time = ?";
    public static final String GET_ALL_ROUTES_QUERY = "select * from routes";
    public static final String GET_ROUTE_BY_ALL_PARAMETERS_QUERY = "select  * from routes where departure = ? and destination = ? and transit_time = ?";
}
