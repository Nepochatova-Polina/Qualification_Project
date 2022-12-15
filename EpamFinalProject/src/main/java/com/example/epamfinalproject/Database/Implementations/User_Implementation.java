package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionDB;
import com.example.epamfinalproject.Database.Encryptor;
import com.example.epamfinalproject.Database.Interfaces.UserDAO;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.User;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class User_Implementation implements UserDAO {
    private static final Logger log = Logger.getLogger(User_Implementation.class.getName());
    private static PreparedStatement preparedStatement;

    private static final String GET_USER_BY_ID_QUERY = "select * from users where users.id = ?";
    private static final String GET_USER_BY_ROLE_PASSENGER_QUERY = "select * from users where role = 'passenger'";
    private static final String GET_USER_BY_ROLE_ADMIN_QUERY = "select * from users  where role = 'administrator'";
    private static final String GET_ALL_USERS_QUERY = "select * from users";
    private static final String CHECK_USER_QUERY = "select * from users where users.login = ? and users.password = ?";
    private static final String REGISTER_USER_QUERY = "insert into users(first_name, last_name, login, password, role) values (?,?,?,?,?);";
    private static final String UPDATE_USER_QUERY = "update users set first_name =?, last_name=?, login = ?, role = ? where id = ?";
    private static final String UPDATE_USER_PASSPORT_QUERY = "update users set passport_img = ? where id = ?";
    private static final String DELETE_USER_QUERY = "delete from users where id = ?";
    private static final String GET_USER_PASSPORT_QUERY = "select passport_img from users where id = ?";

    @Override
    public void registerUser(User user) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(REGISTER_USER_QUERY);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, Encryptor.encrypt(user.getPassword()));
            preparedStatement.setString(5, user.getRole().toString());
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
            preparedStatement.setString(2, Encryptor.encrypt(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User
                        .UserBuilder()
                        .id(resultSet.getInt(1))
                        .firstName(resultSet.getString(2))
                        .lastName(resultSet.getString(3))
                        .login(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .role(UserRole.valueOf(resultSet.getString(6)))
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
                        .role(UserRole.valueOf(resultSet.getString(6)))
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
                        .role(UserRole.valueOf(resultSet.getString(6)))
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
                        .role(UserRole.valueOf(resultSet.getString(6)))
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
                        .role(UserRole.valueOf(resultSet.getString(6)))
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
            preparedStatement.setString(4, user.getRole().toString());
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
    public void updateUserPassport(long id, InputStream image, long length) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_USER_PASSPORT_QUERY);
            preparedStatement.setBinaryStream(1, image, length);
            preparedStatement.setLong(2, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Error while committing. Image won't be updated");
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

    public Blob getPassport(long id) {
        Blob blob = null;
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_USER_PASSPORT_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                blob = resultSet.getBlob(1);
            }
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
        return blob;
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
}
