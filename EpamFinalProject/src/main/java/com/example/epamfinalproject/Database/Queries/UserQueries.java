package com.example.epamfinalproject.Database.Queries;

public class UserQueries {
    public static final String GET_USER_BY_ID_QUERY = "select * from users where users.id = ?";

    public static final String GET_USER_BY_USERNAME_QUERY = "select * from users where users.login = ?";

    public static final String GET_USER_BY_ROLE_CLIENT_QUERY = "select * from users where role = 'client'";

    public static final String GET_USER_BY_ROLE_ADMIN_QUERY = "select * from users  where role = 'administrator'";

    public static final String GET_ALL_USERS_QUERY = "select * from users";

    public static final String CHECK_USER_QUERY = "select * from users where users.login = ? and users.password = ?";

    public static final String REGISTER_USER_QUERY = "insert into users(first_name, last_name, login, password, role) values (?,?,?,?,?);";

    public static final String UPDATE_USER_QUERY = "update users set first_name =?, last_name=?, login = ?, role = ? where id = ?";

    public static final String UPDATE_USER_PASSPORT_QUERY = "update users set passport_img = ? where id = ?";

    public static final String DELETE_USER_QUERY = "delete from users where id = ?";

    public static final String GET_USER_PASSPORT_QUERY = "select passport_img from users where id = ?";
}
