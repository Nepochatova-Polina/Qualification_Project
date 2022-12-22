package com.example.epamfinalproject.Database.Queries;

public class ShipQueries {
    public static final String REGISTER_SHIP_QUERY = "insert into ships(route_id, number_of_ports,passenger_capacity, start_date, end_date) values (?,?,?,?,?)";

    public static final String GET_SHIP_BY_ID_QUERY = "select * from ships where id = ?";

    public static final String UPDATE_SHIP_BY_ID_QUERY = "update ships set route_id = ?,number_of_ports=?,passenger_capacity=?,start_date=?,end_date=? where id = ?";

    public static final String DELETE_SHIP_BY_ID_QUERY = "delete from ships where id = ? ";

    public static final String GET_SHIPS_BY_ROUTE_ID_QUERY = "select * from ships where route_id = ?";

    public static final String GET_SHIPS_BY_START_DATE_QUERY = "select * from ships where start_date = ?";

    public static final String GET_SHIPS_BY_END_DATE_QUERY = "select * from ships where end_date = ?";

}
