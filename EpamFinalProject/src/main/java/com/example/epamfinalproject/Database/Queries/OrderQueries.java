package com.example.epamfinalproject.Database.Queries;

public class OrderQueries {
    public static final String CREATE_ORDER_QUERY = "insert into orders(cruise_id, user_id, status) values (?,?,?)";

    public static final String UPDATE_ORDER_BY_ID_QUERY = "update orders set cruise_id = ?,user_id = ?,status = ? where id = ?";

    public static final String DELETE_ORDER_BY_ID_QUERY = " delete from orders where id = ?";

    public static final String DELETE_ORDER_BY_USER_ID_QUERY = " delete from orders where user_id = ?";

    public static final String DELETE_ORDER_BY_SHIP_ID_QUERY = " delete from orders where cruise_id = ?";


    public static final String GET_ORDER_BY_USER_ID = "select * from orders where user_id = ?";

    public static final String GET_ORDER_BY_SHIP_ID = "select * from orders where cruise_id = ?";
}
