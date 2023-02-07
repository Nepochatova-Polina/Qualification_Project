package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.HikariConnectionPool;
import com.example.epamfinalproject.Database.Interfaces.CruiseDAO;
import com.example.epamfinalproject.Database.Queries.CruiseQueries;
import com.example.epamfinalproject.Database.Queries.RouteQueries;
import com.example.epamfinalproject.Database.Queries.ShipQueries;
import com.example.epamfinalproject.Database.Shaper.CruiseShaper;
import com.example.epamfinalproject.Database.Shaper.DataShaper;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Services.StaffService;
import com.example.epamfinalproject.Utility.Constants;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class CruiseImplementation implements CruiseDAO {
  private static final Logger log = Logger.getLogger(CruiseImplementation.class.getName());

  PreparedStatement preparedStatement;
  DataShaper<Cruise> cruiseShaper = new CruiseShaper(new StaffService(new StaffImplementation()));

  @Override
  public void createCruise(Cruise cruise) {
    long shipID = 0;
    long routeID = 0;
    try (Connection connection = HikariConnectionPool.getConnection()) {
      connection.setAutoCommit(false);
      preparedStatement = connection.prepareStatement(ShipQueries.REGISTER_SHIP_QUERY_RETURNING_ID);
      preparedStatement.setString(1, cruise.getShip().getName());
      preparedStatement.setInt(2, cruise.getShip().getPassengerCapacity());
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) shipID = resultSet.getLong(1);

      preparedStatement = connection.prepareStatement(RouteQueries.CREATE_ROUTE_QUERY_RETURNING_ID);
      preparedStatement.setString(1, cruise.getRoute().getDeparture());
      preparedStatement.setString(2, cruise.getRoute().getDestination());
      preparedStatement.setInt(3, cruise.getRoute().getTransitTime());
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) routeID = resultSet.getLong(1);

      preparedStatement = connection.prepareStatement(CruiseQueries.CREATE_CRUISE_QUERY);
      preparedStatement.setLong(1, shipID);
      preparedStatement.setLong(2, routeID);
      preparedStatement.setString(3, cruise.getName());
      preparedStatement.setInt(4, cruise.getPrice());
      preparedStatement.setDate(5, Date.valueOf(cruise.getStartOfTheCruise()));
      preparedStatement.setDate(6, Date.valueOf(cruise.getEndOfTheCruise()));
      if (preparedStatement.executeUpdate() <= 0) {
        log.warn("Cannot add cruise information.");
        connection.rollback();
      }
      connection.commit();
      connection.setAutoCommit(true);
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);

    } finally {
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
        }
      }
    }
  }

  @Override
  public void updateCruiseByID(Cruise cruise, long id) {
    try (Connection connection = HikariConnectionPool.getConnection()) {
      connection.setAutoCommit(false);
      preparedStatement = connection.prepareStatement(CruiseQueries.UPDATE_CRUISE_BY_ID_QUERY);
      preparedStatement.setLong(1, cruise.getShip().getId());
      preparedStatement.setLong(2, cruise.getRoute().getId());
      preparedStatement.setString(3, cruise.getName());
      preparedStatement.setInt(4, cruise.getPrice());
      preparedStatement.setDate(5, java.sql.Date.valueOf(cruise.getStartOfTheCruise()));
      preparedStatement.setDate(6, java.sql.Date.valueOf(cruise.getEndOfTheCruise()));
      preparedStatement.setLong(7, id);
      if (preparedStatement.executeUpdate() <= 0) {
        connection.rollback();
        log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
      }
      connection.commit();
      connection.setAutoCommit(true);
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);
    } finally {
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
        }
      }
    }
  }

  @Override
  public void deleteCruiseByID(long id) {
    try (Connection connection = HikariConnectionPool.getConnection()) {
      connection.setAutoCommit(false);
      preparedStatement = connection.prepareStatement(CruiseQueries.DELETE_CRUISE_BY_ID_QUERY);
      preparedStatement.setLong(1, id);
      if (preparedStatement.executeUpdate() <= 0) {
        connection.rollback();
        log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
      }
      connection.commit();
      connection.setAutoCommit(true);
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);
    } finally {
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
        }
      }
    }
  }

  @Override
  public void confirmCruiseByID(long id) {
    try (Connection connection = HikariConnectionPool.getConnection()) {
      connection.setAutoCommit(false);
      preparedStatement = connection.prepareStatement(CruiseQueries.CONFIRM_CRUISE_BY_ID_QUERY);
      preparedStatement.setLong(1, id);
      if (preparedStatement.executeUpdate() <= 0) {
        connection.rollback();
        log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
      }
      connection.commit();
      connection.setAutoCommit(true);
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);
    } finally {
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
        }
      }
    }
  }

  @Override
  public int getNumberOfActualCruises(String query) {
    int rowsCount = 0;
    try (Connection connection = HikariConnectionPool.getConnection()) {
      preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        rowsCount = resultSet.getInt(1);
      }
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);
    } finally {
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
        }
      }
    }
    return rowsCount;
  }

  @Override
  public Cruise getCruiseByID(long id) {
    Cruise cruise = null;
    try (Connection connection = HikariConnectionPool.getConnection()) {
      preparedStatement = connection.prepareStatement(CruiseQueries.GET_CRUISE_BY_ID);
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        cruise = cruiseShaper.shapeData(resultSet);
      }
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);
    } finally {
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
        }
      }
    }
    return cruise;
  }

  @Override
  public List<Cruise> getAllCruisesForPage(String query) {
    List<Cruise> cruiseList = new ArrayList<>();
    try (Connection connection = HikariConnectionPool.getConnection()) {
      preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet != null) {
        cruiseList = cruiseShaper.shapeDataToList(resultSet);
      }
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);
    } finally {
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
        }
      }
    }
    return cruiseList;
  }

  @Override
  public List<Cruise> getActualCruisesForPage(String query) {
    List<Cruise> cruiseList = new ArrayList<>();
    try (Connection connection = HikariConnectionPool.getConnection()) {
      preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet != null) {
        cruiseList = cruiseShaper.shapeDataToList(resultSet);
      }
    } catch (SQLException e) {
      log.warn(Constants.DATABASE_PROBLEM_WITH_CONNECTION + e);
    } finally {
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          log.warn(Constants.DATABASE_ERROR_CLOSING_CONNECTION);
        }
      }
    }
    return cruiseList;
  }
}
