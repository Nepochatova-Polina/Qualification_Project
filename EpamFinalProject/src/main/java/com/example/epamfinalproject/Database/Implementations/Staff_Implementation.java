package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionDB;
import com.example.epamfinalproject.Database.Interfaces.StaffDAO;
import com.example.epamfinalproject.Entities.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Staff_Implementation implements StaffDAO {
    private static final Logger log = Logger.getLogger(User_Implementation.class.getName());
    private static PreparedStatement preparedStatement;
    private static final String REGISTER_STAFF_QUERY = "insert into staff(first_name, last_name, ship_id) values (?,?,?)";
    private static final String UPDATE_STAFF_BY_ID_QUERY = "update staff set first_name = ?,last_name = ?,ship_id = ? where id = ?";
    private static final String DELETE_STAFF_BY_ID_QUERY = "delete from staff where id = ?";
    private static final String DELETE_STAFF_BY_SHIP_ID_QUERY = "delete from staff where ship_id = ?";
    private static final String GET_STAFF_BY_ID_QUERY = "";
    private static final String GET_STAFF_BY_SHIP_ID_QUERY = "";
    private static final String GET_ALL_STAFF_QUERY = "select * from staff";


    @Override
    public void registerStaff(Staff staff) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(REGISTER_STAFF_QUERY);
            preparedStatement.setString(1, staff.getFirstName());
            preparedStatement.setString(2, staff.getLastName());
            preparedStatement.setLong(3, staff.getShip_id());
            if (preparedStatement.executeUpdate() <= 0) {
                log.warning("Cannot register staff.");
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
    public void updateStaffByID(Staff staff, long id) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_STAFF_BY_ID_QUERY);
            preparedStatement.setString(1, staff.getFirstName());
            preparedStatement.setString(2, staff.getLastName());
            preparedStatement.setLong(3, staff.getShip_id());
            preparedStatement.setLong(4, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Cannot update staff.");
            }
            connection.commit();
            connection.setAutoCommit(true);
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
    public void deleteStaffByID(long id) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_STAFF_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Cannot delete staff.");
            }
            connection.commit();
            connection.setAutoCommit(true);
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
    public void deleteStaffByShipID(long id) {
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_STAFF_BY_SHIP_ID_QUERY);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warning("Cannot delete staff.");
            }
            connection.commit();
            connection.setAutoCommit(true);
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
    public Staff getStaffByID(long id) {
        Staff staff = new Staff();
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_STAFF_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                staff.setId(resultSet.getLong(1));
                staff.setFirstName(resultSet.getString(2));
                staff.setLastName(resultSet.getString(3));
                staff.setShip_id(resultSet.getLong(4));
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
        return staff;
    }

    @Override
    public List<Staff> getAllStaff() {
        Staff staff = new Staff();
        List<Staff> staffList = new ArrayList<>();
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ALL_STAFF_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                staff.setId(resultSet.getLong(1));
                staff.setFirstName(resultSet.getString(2));
                staff.setLastName(resultSet.getString(3));
                staff.setShip_id(resultSet.getLong(4));
                staffList.add(staff);
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
        return staffList;
    }

    @Override
    public List<Staff> getAllStaffByShipID(long id) {
        Staff staff = new Staff();
        List<Staff> staffList = new ArrayList<>();
        ConnectionDB connectionDB = ConnectionDB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            preparedStatement = connection.prepareStatement(GET_STAFF_BY_SHIP_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                staff.setId(resultSet.getLong(1));
                staff.setFirstName(resultSet.getString(2));
                staff.setLastName(resultSet.getString(3));
                staff.setShip_id(resultSet.getLong(4));
                staffList.add(staff);
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
        return staffList;
    }
}
