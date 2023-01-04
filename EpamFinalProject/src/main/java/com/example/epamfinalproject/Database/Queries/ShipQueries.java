package com.example.epamfinalproject.Database.Queries;

public class ShipQueries {
    public static final String REGISTER_SHIP_QUERY = "insert into ships(name,passenger_capacity) values (?,?)";

    public static final String GET_SHIP_BY_ID_QUERY = "select * from ships where id = ?";

    public static final String UPDATE_SHIP_BY_ID_QUERY = "update ships set name=?,passenger_capacity=? where id = ?";

    public static final String DELETE_SHIP_BY_ID_QUERY = "delete from ships where id = ? ";

    public static final String GET_SHIPS_BY_ROUTE_ID_QUERY = "select * from ships where route_id = ?";

    public static final String GET_ALL_SHIPS_QUERY = "select * from ships";
}
