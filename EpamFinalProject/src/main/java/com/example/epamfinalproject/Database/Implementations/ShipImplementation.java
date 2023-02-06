package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionPool;
import com.example.epamfinalproject.Database.Interfaces.ShipDAO;
import com.example.epamfinalproject.Database.Queries.ShipQueries;
import com.example.epamfinalproject.Database.Shaper.DataShaper;
import com.example.epamfinalproject.Database.Shaper.ShipShaper;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Utility.Constants;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class ShipImplementation implements ShipDAO {
  private static final Logger log = Logger.getLogger(ShipImplementation.class.getName());
  private PreparedStatement preparedStatement;
  DataShaper<Ship> shipShaper = new ShipShaper();

  @Override
  public void registerShip(Ship ship) {
    try (Connection connection = ConnectionPool.getConnection()) {
      preparedStatement = connection.prepareStatement(ShipQueries.REGISTER_SHIP_QUERY);
      preparedStatement.setString(1, ship.getName());
      preparedStatement.setInt(2, ship.getPassengerCapacity());
      if (preparedStatement.executeUpdate() <= 0) {
        log.warn("Cannot register ship.");
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
  public Ship getShipByName(String name) {
    Ship ship = new Ship();
    try (Connection connection = ConnectionPool.getConnection()) {
      preparedStatement = connection.prepareStatement(ShipQueries.GET_SHIP_BY_NAME_QUERY);
      preparedStatement.setString(1, name);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        ship = shipShaper.shapeData(resultSet);
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
    return ship;
  }

  @Override
  public List<Ship> getAllShips() {
    List<Ship> shipList = new ArrayList<>();
    try (Connection connection = ConnectionPool.getConnection()) {
      preparedStatement = connection.prepareStatement(ShipQueries.GET_ALL_SHIPS_QUERY);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet != null) {
        shipList = shipShaper.shapeDataToList(resultSet);
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
    return shipList;
  }

  @Override
  public void updateShipByID(Ship ship, long id) {
    try (Connection connection = ConnectionPool.getConnection()) {
      connection.setAutoCommit(false);
      preparedStatement = connection.prepareStatement(ShipQueries.UPDATE_SHIP_BY_ID_QUERY);
      preparedStatement.setString(1, ship.getName());
      preparedStatement.setInt(2, ship.getPassengerCapacity());
      preparedStatement.setLong(3, id);
      if (preparedStatement.executeUpdate() <= 0) {
        connection.rollback();
        log.warn("Cannot update ship.");
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
}
