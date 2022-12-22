package com.example.epamfinalproject.Database.Queries;

public class StaffQueries {
    public static final String REGISTER_STAFF_QUERY = "insert into staff(first_name, last_name, ship_id) values (?,?,?)";

    public static final String UPDATE_STAFF_BY_ID_QUERY = "update staff set first_name = ?,last_name = ?,ship_id = ? where id = ?";

    public static final String DELETE_STAFF_BY_ID_QUERY = "delete from staff where id = ?";

    public static final String DELETE_STAFF_BY_SHIP_ID_QUERY = "delete from staff where ship_id = ?";

    public static final String GET_STAFF_BY_ID_QUERY = "select * from staff where id = ?";

    public static final String GET_STAFF_BY_SHIP_ID_QUERY = "select * from staff where ship_id = ?";

    public static final String GET_ALL_STAFF_QUERY = "select * from staff";

}
