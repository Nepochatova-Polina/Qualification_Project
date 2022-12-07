package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionDB;
import com.example.epamfinalproject.Database.Encryptor;
import com.example.epamfinalproject.Database.Interfaces.UserDAO;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class User_Implementation implements UserDAO {
    private static final Logger log = Logger.getLogger(User_Implementation.class.getName());
    private static PreparedStatement preparedStatement;

    private static final String GET_USER_BY_ID_QUERY = "select * from users where users.id = ?";
    private static final String GET_ROLE_BY_ID_QUERY = "select role from user_role where user_role.id = ?";
    private static final String GET_ROLE_BY_VALUE_QUERY = "select id from user_role where user_role.role = ?";
    private static final String GET_USER_BY_ROLE_PASSENGER_QUERY ="select * from users inner join user_role ur on ur.id = users.role_id where role = 'passenger'";
    private static final String GET_USER_BY_ROLE_ADMIN_QUERY = "select * from users inner join user_role ur on ur.id = users.role_id where role = 'administrator'";
    private static final String GET_ALL_USERS_QUERY = "select * from users";
    private static final String CHECK_USER_QUERY = "select * from users where users.login = ? and users.password = ?";
    private static final String REGISTER_USER_QUERY = "insert into users(first_name, last_name, login, password, role_id) values (?,?,?,?,?);";
    private static final String UPDATE_USER_QUERY = "update users set first_name =?, last_name=?, login = ?, role_id = ? where id = ?";
    private static final String DELETE_USER_QUERY = "delete from users where id = ?";

    @Override
    public void registerUser(User user) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(REGISTER_USER_QUERY);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, Encryptor.encrypt(user.getPassword()));
            if (user.getRole() == UserRole.PASSENGER) {
                preparedStatement.setInt(5, 1);
            } else {
                preparedStatement.setInt(5, 2);
            }
            if (preparedStatement.executeUpdate() <= 0) {
                log.warning("Cannot register user.");
            }
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
    }

    @Override
    public User getUserByName(String login, String password) {
        User user = null;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(CHECK_USER_QUERY);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User
                        .UserBuilder()
                        .id(resultSet.getInt(1))
                        .firstName(resultSet.getString(2))
                        .lastName(resultSet.getString(3))
                        .login(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .role(getRoleValue(resultSet.getInt(6)))
                        .build();
            }
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
        return user;
    }

    @Override
    public List<User> getPassengerUsers() {
        List<User> users = new ArrayList<>();
        User user;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_USER_BY_ROLE_PASSENGER_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User
                        .UserBuilder()
                        .id(resultSet.getInt(1))
                        .firstName(resultSet.getString(2))
                        .lastName(resultSet.getString(3))
                        .login(resultSet.getString(4))
                        .role(getRoleValue(resultSet.getInt(6)))
                        .build();

                users.add(user);
            }
            log.info("List of Passengers created and filled with " + users.size() + " users");
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
        return users;
    }

    @Override
    public List<User> getAdministratorUsers() {
        List<User> users = new ArrayList<>();
        User user;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_USER_BY_ROLE_ADMIN_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User
                        .UserBuilder()
                        .id(resultSet.getInt(1))
                        .firstName(resultSet.getString(2))
                        .lastName(resultSet.getString(3))
                        .login(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .role(getRoleValue(resultSet.getInt(6)))
                        .build();
                users.add(user);
            }
            log.info("List of Staff created and filled with " + users.size() + "users");
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
        return users;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        User user;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ALL_USERS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User
                        .UserBuilder()
                        .id(resultSet.getInt(1))
                        .firstName(resultSet.getString(2))
                        .lastName(resultSet.getString(3))
                        .login(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .role(getRoleValue(resultSet.getInt(6)))
                        .build();
                users.add(user);
            }
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
        return users;
    }

    @Override
    public User getUserByID(long id) {
        User user = null;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_USER_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User
                        .UserBuilder()
                        .id(resultSet.getInt(1))
                        .firstName(resultSet.getString(2))
                        .lastName(resultSet.getString(3))
                        .login(resultSet.getString(4))
                        .role(getRoleValue(resultSet.getInt(6)))
                        .build();
            }
            log.info("User was found");
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
        return user;
    }

    @Override
    public void updateUserByID(User user, long id) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            int role_id = getRoleID(user.getRole().toString());
            if (role_id != 0)
                preparedStatement.setInt(4, role_id);
            preparedStatement.setLong(5, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Error while committing. User won't be updated");
            }
            connection.commit();
            connection.setAutoCommit(true);
            log.info("All changes committed");
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
    }

    @Override
    public void deleteUserByID(long id) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_USER_QUERY);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Error while committing. User won't be deleted");
            }
            connection.commit();
            connection.setAutoCommit(true);
            connectionDB.stop();
            log.info("All changes committed");
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
    }

    public UserRole getRoleValue(int id) {
        String role;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ROLE_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString(1);
                    return UserRole.fromString(role);
            }
        } catch (SQLException e) {
            log.warning("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
        return UserRole.PASSENGER;
    }

    public int getRoleID(String role) {
        int id = 0;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ROLE_BY_VALUE_QUERY);
            preparedStatement.setString(1, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }finally {
            try {
                preparedStatement.close();
                connectionDB.stop();
            } catch (SQLException e) {
                log.warning("Error closing connection");
            }
        }
        return id;
    }

}
