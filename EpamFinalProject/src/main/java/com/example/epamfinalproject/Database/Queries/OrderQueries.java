package com.example.epamfinalproject.Database.Queries;

/** All Queries for Order Table */
public class OrderQueries {

  private OrderQueries() {}

  public static final String CREATE_ORDER_QUERY =
      "insert into orders(cruise_id, user_id, status) values (?,?,?)";

  public static final String GET_ORDER_BY_USER_ID =
      "select * from orders"
          + " inner join cruises c on c.id = orders.cruise_id  "
          + "inner join routes r on r.id = c.route_id "
          + "inner join ships s on s.id = c.ship_id"
          + " inner join users u on u.id = orders.user_id  "
          + "where user_id = ?";

  public static final String GET_ALL_UNCONFIRMED_ORDERS_QUERY =
      "select * from orders"
          + " inner join cruises c on c.id = orders.cruise_id  "
          + "inner join routes r on r.id = c.route_id "
          + "inner join ships s on s.id = c.ship_id"
          + " inner join users u on u.id = orders.user_id where orders.status = 'pending'";

  public static final String GET_BOOKED_SEATS_BY_CRUISE_ID_QUERY =
      "select count(*) from orders where cruise_id = ?";

  public static final String CONFIRM_ORDER_BY_ID = "update orders set status = 'paid' where id = ?";
}
