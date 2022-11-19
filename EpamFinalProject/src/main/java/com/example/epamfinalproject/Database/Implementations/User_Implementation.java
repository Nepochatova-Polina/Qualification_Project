package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionDB;
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

    private static final String USER_BY_ID_QUERY = "select * from users where users.id = ?";

    private static final String ROLE_BY_ID_QUERY = "select role from user_role where user_role.id = ?";

    private static final String ROLE_BY_VALUE_QUERY = "select id from user_role where user_role.role = ?";

    private static final String USER_BY_ROLE_PASSENGER_QUERY =
            "select * from users inner join user_role ur on ur.id = users.role_id where role = 'passenger'";

    private static final String USER_BY_ROLE_STAFF_QUERY = "" +
            "select * from users inner join user_role ur on ur.id = users.role_id where role = 'staff'";

    private static final String GET_ALL_USERS_QUERY = "select * from users";

    private static final String CHECK_USER_QUERY = "select * from users where users.login = ? and users.password = ?";

    private static final String ADD_USER_QUERY = "insert into users(first_name, last_name, login, password, role_id) values (?,?,?,?,?);";

    private static final String EDIT_USER_QUERY = "update users set first_name =?, last_name=?, login = ?, role_id = ? where id = ?";

    private static final String DELETE_USER_QUERY = "delete from users where id = ?";

    @Override
    public void registerUser(User user) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(ADD_USER_QUERY);
            prepareStatement.setString(1, user.getFirstName());
            prepareStatement.setString(2, user.getLastName());
            prepareStatement.setString(3, user.getLogin());
            prepareStatement.setString(4, user.getPassword());
            if (user.getRole() == UserRole.PASSENGER) {
                prepareStatement.setInt(5, 1);
            } else {
                prepareStatement.setInt(5, 2);
            }
            if (prepareStatement.executeUpdate() <= 0) {
                log.warning("Cannot register user.");
            }
            connectionDB.stop();
        } catch (SQLException e) {
            log.warning("Problems with connection");
            log.warning(e.toString());
        }
    }

    @Override
    public User getUser(String login, String password) {
        User user = null;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(CHECK_USER_QUERY);
            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);
            ResultSet resultSet = prepareStatement.executeQuery();
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
            connectionDB.stop();
        } catch (SQLException e) {
            log.warning("Problems with connection");
            log.warning(e.toString());
        }
        return user;
    }

    @Override
    public List<User> getPassengerUsers() {
        List<User> users = new ArrayList<>();
        User user;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(USER_BY_ROLE_PASSENGER_QUERY);
            ResultSet resultSet = prepareStatement.executeQuery();
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
            connectionDB.stop();
        } catch (SQLException e) {
            log.warning("Problems with connection");
            log.warning(e.toString());
        }
        return users;
    }

    @Override
    public List<User> getStaffUsers() {
        List<User> users = new ArrayList<>();
        User user;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(USER_BY_ROLE_STAFF_QUERY);
            ResultSet resultSet = prepareStatement.executeQuery();
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
            connectionDB.stop();
            log.info("List of Staff created and filled with " + users.size() + "users");
        } catch (SQLException e) {
            log.warning("Problems with connection");
            log.warning(e.toString());
        }
        return users;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        User user;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_USERS_QUERY);
            ResultSet resultSet = prepareStatement.executeQuery();
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
            connectionDB.stop();
        } catch (SQLException e) {
            log.warning("Problems with connection");
            log.warning(e.toString());
        }
        return users;
    }

    @Override
    public User getUser(int id) {
        User user = null;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(USER_BY_ID_QUERY);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
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
            connectionDB.stop();
        } catch (SQLException e) {
            log.warning("Problems with connection");
            log.warning(e.toString());
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement prepareStatement = connection.prepareStatement(EDIT_USER_QUERY);
            prepareStatement.setString(1, user.getFirstName());
            prepareStatement.setString(2, user.getLastName());
            prepareStatement.setString(3, user.getLogin());
            int role_id = getRoleID(user.getRole().toString());
            if (role_id != 0)
                prepareStatement.setInt(4, role_id);
            prepareStatement.setInt(5, user.getId());

            if (prepareStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Error while committing. User won't be updated");
            }
            connection.commit();
            connection.setAutoCommit(true);
            connectionDB.stop();
            log.info("All changes committed");
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
    }

    @Override
    public void deleteUserByID(User user) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement prepareStatement = connection.prepareStatement(DELETE_USER_QUERY);
            prepareStatement.setInt(1, user.getId());
            if (prepareStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Error while committing. User won't be deleted");
            }
            connection.commit();
            connection.setAutoCommit(true);
            connectionDB.stop();
            log.info("All changes committed");
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
    }

    public UserRole getRoleValue(int id) {
        String role;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(ROLE_BY_ID_QUERY);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString(1);
                if (role.equalsIgnoreCase(UserRole.PASSENGER.toString())) {
                    return UserRole.PASSENGER;
                }
                return UserRole.STAFF;
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
        return UserRole.PASSENGER;
    }

    public int getRoleID(String role) {
        int id = 0;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(ROLE_BY_VALUE_QUERY);
            prepareStatement.setString(1, role);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
        return id;
    }
}
