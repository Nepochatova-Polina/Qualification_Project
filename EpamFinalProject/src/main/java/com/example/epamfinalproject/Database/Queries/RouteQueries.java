package com.example.epamfinalproject.Database.Queries;

public class RouteQueries {
    public static final String CREATE_ROUTE_QUERY = "insert into route(departure, destination, distance, transit_time) values (?,?,?,?)";

    public static final String UPDATE_ROUTE_QUERY = "update route set departure = ?,destination = ?,distance = ?,transit_time= ? where id = ?";

    public static final String DELETE_ROUTE_QUERY = "delete from route where id = ?";

    public static final String GET_ROUTE_BY_ID_QUERY = "select * from route where route.id = ?";

    public static final String GET_ROUTE_BY_DEPARTURE_QUERY = "select * from route where departure = ?";

    public static final String GET_ROUTE_BY_DESTINATION_QUERY = "select * from route where destination = ?";

    public static final String GET_ROUTE_BY_DEPARTURE_AND_DESTINATION_QUERY = "select * from route where departure = ? and destination = ?";

    public static final String GET_ROUTE_BY_TRANSIT_TIME_QUERY = "select * from route where transit_time = ?";
}
