package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionPool;
import com.example.epamfinalproject.Database.Interfaces.StaffDAO;
import com.example.epamfinalproject.Database.Queries.StaffQueries;
import com.example.epamfinalproject.Entities.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class Staff_Implementation implements StaffDAO {
    private static final Logger log = Logger.getLogger(User_Implementation.class.getName());
    private static PreparedStatement preparedStatement;


    @Override
    public void registerStaff(Staff staff) {
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(StaffQueries.REGISTER_STAFF_QUERY);
            preparedStatement.setString(1, staff.getFirstName());
            preparedStatement.setString(2, staff.getLastName());
            preparedStatement.setLong(3, staff.getShip_id());
            if (preparedStatement.executeUpdate() <= 0) {
                log.warn("Cannot register staff.");
            }
        } catch (SQLException e) {
            log.warn("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Error closing connection");
            }
        }
    }

    @Override
    public void updateStaffByID(Staff staff, long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(StaffQueries.UPDATE_STAFF_BY_ID_QUERY);
            preparedStatement.setString(1, staff.getFirstName());
            preparedStatement.setString(2, staff.getLastName());
            preparedStatement.setLong(3, staff.getShip_id());
            preparedStatement.setLong(4, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warn("Cannot update staff.");
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.warn("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Error closing connection");
            }
        }
    }

    @Override
    public void deleteStaffByID(long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(StaffQueries.DELETE_STAFF_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warn("Cannot delete staff.");
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.warn("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Error closing connection");
            }
        }
    }

    @Override
    public void deleteStaffByShipID(long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(StaffQueries.DELETE_STAFF_BY_SHIP_ID_QUERY);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warn("Cannot delete staff.");
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.warn("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Error closing connection");
            }
        }
    }

    @Override
    public Staff getStaffByID(long id) {
        Staff staff = new Staff();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(StaffQueries.GET_STAFF_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                staff.setId(resultSet.getLong(1));
                staff.setFirstName(resultSet.getString(2));
                staff.setLastName(resultSet.getString(3));
                staff.setShip_id(resultSet.getLong(4));
            }
        } catch (SQLException e) {
            log.warn("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Error closing connection");
            }
        }
        return staff;
    }

    @Override
    public List<Staff> getAllStaff() {
        Staff staff = new Staff();
        List<Staff> staffList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(StaffQueries.GET_ALL_STAFF_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                staff.setId(resultSet.getLong(1));
                staff.setFirstName(resultSet.getString(2));
                staff.setLastName(resultSet.getString(3));
                staff.setShip_id(resultSet.getLong(4));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            log.warn("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Error closing connection");
            }
        }
        return staffList;
    }

    @Override
    public List<Staff> getAllStaffByShipID(long id) {
        Staff staff = new Staff();
        List<Staff> staffList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(StaffQueries.GET_STAFF_BY_SHIP_ID_QUERY);
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
            log.warn("Problems with connection:" + e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Error closing connection");
            }
        }
        return staffList;
    }
}
