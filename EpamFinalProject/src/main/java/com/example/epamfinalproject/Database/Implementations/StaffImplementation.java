package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionPool;
import com.example.epamfinalproject.Database.Interfaces.StaffDAO;
import com.example.epamfinalproject.Database.Queries.StaffQueries;
import com.example.epamfinalproject.Database.Shaper.DataShaper;
import com.example.epamfinalproject.Database.Shaper.StaffShaper;
import com.example.epamfinalproject.Entities.Staff;
import com.example.epamfinalproject.Utility.Constants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class StaffImplementation implements StaffDAO {
  private static final Logger log = Logger.getLogger(StaffImplementation.class.getName());
  private PreparedStatement preparedStatement;
  DataShaper<Staff> staffShaper = new StaffShaper();

  @Override
  public void registerStaff(Staff staff) {
    try (Connection connection = ConnectionPool.getConnection()) {
      preparedStatement = connection.prepareStatement(StaffQueries.REGISTER_STAFF_QUERY);
      preparedStatement.setString(1, staff.getFirstName());
      preparedStatement.setString(2, staff.getLastName());
      preparedStatement.setLong(3, staff.getShipId());
      if (preparedStatement.executeUpdate() <= 0) {
        log.warn("Cannot register staff.");
      }
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
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
      preparedStatement.setLong(3, staff.getShipId());
      preparedStatement.setLong(4, id);
      if (preparedStatement.executeUpdate() <= 0) {
        connection.rollback();
        log.warn("Cannot update staff.");
      }
      connection.commit();
      connection.setAutoCommit(true);
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
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
        staff = staffShaper.shapeData(resultSet);
      }
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
      }
    }
    return staff;
  }

  @Override
  public List<Staff> getAllStaff() {
    List<Staff> staffList = new ArrayList<>();
    try (Connection connection = ConnectionPool.getConnection()) {
      preparedStatement = connection.prepareStatement(StaffQueries.GET_ALL_STAFF_QUERY);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet != null) {
        staffList = staffShaper.shapeDataToList(resultSet);
      }
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
      }
    }
    return staffList;
  }

  @Override
  public List<Staff> getAllStaffByShipID(long id) {
    List<Staff> staffList = new ArrayList<>();
    try (Connection connection = ConnectionPool.getConnection()) {
      preparedStatement = connection.prepareStatement(StaffQueries.GET_STAFF_BY_SHIP_ID_QUERY);
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet != null) {
        staffList = staffShaper.shapeDataToList(resultSet);
      }
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
      }
    }
    return staffList;
  }
}
