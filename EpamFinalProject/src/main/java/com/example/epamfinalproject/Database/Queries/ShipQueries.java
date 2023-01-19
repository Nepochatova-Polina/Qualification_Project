package com.example.epamfinalproject.Database.Queries;

/** A;; Queries for Ship table */
public class ShipQueries {

  private ShipQueries() {}

  public static final String REGISTER_SHIP_QUERY =
      "insert into ships(name,passenger_capacity) values (?,?)";

  public static final String GET_SHIP_BY_NAME_QUERY = "select * from ships where name = ?";

  public static final String UPDATE_SHIP_BY_ID_QUERY =
      "update ships set name=?,passenger_capacity=? where id = ?";

  public static final String GET_ALL_SHIPS_QUERY = "select * from ships";
}
