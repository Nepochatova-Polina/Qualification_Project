package com.example.epamfinalproject.Database.Queries;

public class OrderQueries {
    public static final String CREATE_ORDER_QUERY = "insert into orders(cruise_id, user_id,number_of_seats,price, status) values (?,?,?,?,?)";

    public static final String UPDATE_ORDER_BY_ID_QUERY = "update orders set cruise_id = ?,user_id = ?, number_of_seats = ?, price = ?, status = ? where id = ?";

    public static final String DELETE_ORDER_BY_ID_QUERY = " delete from orders where id = ?";

    public static final String DELETE_ORDER_BY_USER_ID_QUERY = " delete from orders where user_id = ?";

    public static final String DELETE_ORDER_BY_SHIP_ID_QUERY = " delete from orders where cruise_id = ?";

    public static final String GET_ORDER_BY_ID = "select * from orders " +
                                                            "inner join cruises c on c.id = orders.cruise_id " +
                                                            "inner join routes r on r.id = c.route_id " +
                                                            "inner join ships s on s.id = c.ship_id " +
                                                            "inner join users u on u.id = orders.user_id " +
                                                            "where orders.id = ?";
    public static final String GET_ORDER_BY_USER_ID = "select * from orders where user_id = ?";

    public static final String GET_ORDER_BY_SHIP_ID = "select * from orders where cruise_id = ?";

    public static final String GET_ALL_ORDERS_QUERY = "select * from orders";
}
