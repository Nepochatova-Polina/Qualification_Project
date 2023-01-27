package com.example.epamfinalproject.Database.Queries;

import com.example.epamfinalproject.Utility.Constants;

/** All Queries for Cruise Table */
public class CruiseQueries {

  private CruiseQueries() {}

  public static final String CONFIRM_CRUISE_BY_ID_QUERY =
      "update cruises set confirmed = true where id = ?";

  public static final String CREATE_CRUISE_QUERY =
      "insert into cruises(ship_id, route_id,cruise_name, price, start_date,end_date) "
          + "values (?,?,?,?,?,?)";

  public static final String UPDATE_CRUISE_BY_ID_QUERY =
      "update cruises set ship_id =?, route_id=?,cruise_name=?, price = ?, start_date=?,end_date=?"
          + " where id = ?";

  public static final String GET_CRUISE_BY_ID =
      "select * from cruises "
          + "inner join routes r on r.id = cruises.route_id "
          + "inner join ships s on s.id = cruises.ship_id "
          + " where cruises.id = ?";

  public static final String GET_CRUISE_BY_SHIP_ID =
      "select * from cruises "
          + "inner join routes r on r.id = cruises.route_id "
          + "inner join ships s on s.id = cruises.ship_id "
          + " where cruises.ship_id = ?";

  public static final String GET_ALL_ACTUAL_CRUISES_FOR_FIRST_PAGE_QUERY =
      "select * from cruises "
          + "inner join routes r on r.id = cruises.route_id "
          + "inner join ships s on s.id = cruises.ship_id "
          + " where start_date > now()"
          + " and deleted = false "
          + "limit "
          + Constants.PAGE_SIZE
          + " offset "
          + 0;

  public static final String GET_ALL_ACTUAL_CRUISES_FOR_FIRST_PAGE_ADMIN_QUERY =
      "select * from cruises "
          + "inner join routes r on r.id = cruises.route_id "
          + "inner join ships s on s.id = cruises.ship_id "
          + " where start_date > now()"
          + " and deleted = false "
          + " and confirmed = false "
          + "limit "
          + Constants.PAGE_SIZE
          + " offset "
          + 0;

  public static final String GET_ALL_CRUISES_FOR_FIRST_PAGE_QUERY =
      "select * from cruises "
          + "inner join routes r on r.id = cruises.route_id "
          + "inner join ships s on s.id = cruises.ship_id "
          + "limit "
          + Constants.PAGE_SIZE
          + " offset "
          + 0;

  public static final String GET_ALL_ACTUAL_CRUISES_QUERY =
      "select * from cruises "
          + "inner join routes r on r.id = cruises.route_id "
          + "inner join ships s on s.id = cruises.ship_id "
          + "where start_date >= now() and deleted = false";

  public static final String GET_NUMBER_OF_ACTUAL_CRUISES =
      "select count(id) from cruises where start_date >= now() and deleted = false";

  public static final String DELETE_CRUISE_BY_ID_QUERY =
      "update cruises set deleted = true where id = ?";
}
