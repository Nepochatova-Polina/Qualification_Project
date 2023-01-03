package com.example.epamfinalproject.Database.Queries;

public class CruiseQueries {
    public static final String CREATE_CRUISE_QUERY = "insert into cruises(ship_id, route_id, start_date,end_date) values (?,?,?,?)";

    public static final String UPDATE_CRUISE_BY_ID_QUERY = "update cruises set ship_id =?, route_id=?, start_date=?,end_date=? where id = ?";

    public static final String DELETE_CRUISE_BY_ID_QUERY = "delete from cruises where id = ?";

    public static final String GET_CRUISE_BY_ID = "select * from cruises " +
                                                                            "inner join routes r on r.id = cruises.route_id " +
                                                                            "inner join ships s on s.id = cruises.ship_id " +
                                                                            " where cruises.id = ?";

    public static final String GET_CRUISE_BY_SHIP_ID = "select * from cruises " +
                                                                                "inner join routes r on r.id = cruises.route_id " +
                                                                                "inner join ships s on s.id = cruises.ship_id " +
                                                                                " where cruises.ship_id = ?";
}
