package com.example.epamfinalproject.Database.Queries;

public class CruiseQueries {
    public static final String CREATE_CRUISE_QUERY = "insert into cruises(ship_id, route_id,cruise_name, price, start_date,end_date) values (?,?,?,?,?,?)";

    public static final String UPDATE_CRUISE_BY_ID_QUERY = "update cruises set ship_id =?, route_id=?,cruise_name=?, price = ? & start_date=?,end_date=? where id = ?";

    public static final String DELETE_CRUISE_BY_ID_QUERY = "delete from cruises where id = ?";

    public static final String GET_CRUISE_BY_ID = "select * from cruises " +
            "inner join routes r on r.id = cruises.route_id " +
            "inner join ships s on s.id = cruises.ship_id " +
            " where cruises.id = ?";

    public static final String GET_CRUISE_BY_SHIP_ID = "select * from cruises " +
            "inner join routes r on r.id = cruises.route_id " +
            "inner join ships s on s.id = cruises.ship_id " +
            " where cruises.ship_id = ?";

    public static final String GET_ALL_CRUISES_QUERY = "select * from cruises " +
            "inner join routes r on r.id = cruises.route_id " +
            "inner join ships s on s.id = cruises.ship_id ";

    public static final String GET_ALL_CRUISES_BY_DURATION_QUERY = "select * from cruises" +
            " inner join routes r on r.id = cruises.route_id " +
            "inner join ships s on s.id = cruises.ship_id " +
            "where r.transit_time = ? and start_date >= now()";

    public static final String GET_ALL_CRUISES_AFTER_DATE = "select * from cruises " +
            "inner join ships s on s.id = cruises.ship_id " +
            "inner join routes r on r.id = cruises.route_id " +
            "where start_date >= ?";

    public static final String GET_ALL_CRUISES_BEFORE_DATE = "select * from cruises " +
            "inner join routes r on r.id = cruises.route_id " +
            "inner join ships s on s.id = cruises.ship_id " +
            "where start_date >= now() and  end_date <= ?";

    public static final String GET_ALL_CRUISES_BETWEEN_DATES_QUERY = "select * from cruises " +
            "inner join ships s on s.id = cruises.ship_id " +
            "inner join routes r on r.id = cruises.route_id " +
            "where start_date >= ? and end_date <= ?";

    public static final String GET_CRUISES_BY_START_AND_DURATION_QUERY = "select * from cruises " +
            "inner join routes r on r.id = cruises.route_id " +
            "inner join ships s on s.id = cruises.ship_id " +
            "where start_date >= ? and r.transit_time = ?";

    public static final String GET_CRUISES_BY_END_AND_DURATION_QUERY = "select * from cruises " +
            "inner join routes r on r.id = cruises.route_id " +
            "inner join ships s on s.id = cruises.ship_id " +
            "where end_date >= ? and r.transit_time = ?";

    public static final String GET_CRUISES_BY_DATES_AND_DURATION_QUERY = "select * from cruises " +
            "inner join routes r on r.id = cruises.route_id " +
            "inner join ships s on s.id = cruises.ship_id " +
            "where start_date >= ? and end_date <= ? and r.transit_time = ?";

    public static final String GET_ALL_ACTUAL_CRUISES_FOR_PAGE_QUERY = "select * from cruises "+
            "inner join routes r on r.id = cruises.route_id " +
            "inner join ships s on s.id = cruises.ship_id "+
            "where start_date >= now() limit ? offset ?";

    public static final String GET_ALL_ACTUAL_CRUISES_QUERY = "select * from cruises "+
            "inner join routes r on r.id = cruises.route_id " +
            "inner join ships s on s.id = cruises.ship_id "+
            "where start_date >= now()";
    public static final String GET_NUMBER_OF_ACTUAL_CRUISES = "select count(id) from cruises where start_date >= now()";
}
