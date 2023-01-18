package com.example.epamfinalproject.Database.Implementations;

import com.example.epamfinalproject.Database.ConnectionPool;
import com.example.epamfinalproject.Database.Interfaces.CruiseDAO;
import com.example.epamfinalproject.Database.Queries.CruiseQueries;
import com.example.epamfinalproject.Database.Shaper.CruiseShaper;
import com.example.epamfinalproject.Database.Shaper.DataShaper;
import com.example.epamfinalproject.Entities.Cruise;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Cruise_Implementation implements CruiseDAO {
    private static final Logger log = Logger.getLogger(Cruise_Implementation.class.getName());
    PreparedStatement preparedStatement;
    DataShaper<Cruise> cruiseShaper = new CruiseShaper();


    @Override
    public void createCruise(Cruise cruise) {
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.CREATE_CRUISE_QUERY);
            preparedStatement.setLong(1, cruise.getShip().getId());
            preparedStatement.setLong(2, cruise.getRoute().getId());
            preparedStatement.setString(3, cruise.getName());
            preparedStatement.setInt(4, cruise.getPrice());
            preparedStatement.setDate(5, Date.valueOf(cruise.getStartOfTheCruise()));
            preparedStatement.setDate(6, Date.valueOf(cruise.getEndOfTheCruise()));
            if (preparedStatement.executeUpdate() <= 0) {
                log.warn("Cannot add cruise information.");
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
    public void updateCruiseByID(Cruise cruise, long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
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
                log.warn("Cannot update order information.");
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
    public void deleteCruiseByID(long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CruiseQueries.DELETE_CRUISE_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() <= 0) {
                connection.rollback();
                log.warn("Cannot delete order information.");
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
    public int getNumberOfActualCruises() {
        int rowsCount = 0;
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_NUMBER_OF_ACTUAL_CRUISES);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rowsCount = resultSet.getInt(1);
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
        return rowsCount;
    }

    @Override
    public Cruise getCruiseByID(long id) {
        Cruise cruise = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_CRUISE_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cruise = cruiseShaper.shapeData(resultSet);
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
        return cruise;
    }

    @Override
    public List<Cruise> getCruisesByShipID(long id) {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_CRUISE_BY_SHIP_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                cruiseList = cruiseShaper.shapeDataToList(resultSet);
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
        return cruiseList;
    }

    @Override
    public List<Cruise> getAllCruisesForPage(int limit, int offset) {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_ALL_CRUISES_FOR_PAGE_QUERY);
            preparedStatement.setInt(1,limit);
            preparedStatement.setInt(2,offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                cruiseList = cruiseShaper.shapeDataToList(resultSet);
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
        return cruiseList;
    }

    @Override
    public List<Cruise> getActualCruisesForPage(int limit, int offset) {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_ALL_ACTUAL_CRUISES_FOR_PAGE_QUERY);
            preparedStatement.setLong(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                cruiseList = cruiseShaper.shapeDataToList(resultSet);
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
        return cruiseList;
    }

    @Override
    public List<Cruise> getActualCruises() {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_ALL_ACTUAL_CRUISES_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                cruiseList = cruiseShaper.shapeDataToList(resultSet);
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
        return cruiseList;
    }

    @Override
    public List<Cruise> getAllCruisesByDuration(int duration) {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_ALL_CRUISES_BY_DURATION_QUERY);
            preparedStatement.setInt(1, duration);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                cruiseList = cruiseShaper.shapeDataToList(resultSet);
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
        return cruiseList;
    }

    @Override
    public List<Cruise> getAllCruisesAfterDate(LocalDate date) {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_ALL_CRUISES_AFTER_DATE);
            preparedStatement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                cruiseList = cruiseShaper.shapeDataToList(resultSet);
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
        return cruiseList;
    }

    @Override
    public List<Cruise> getAllCruisesBeforeDate(LocalDate date) {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_ALL_CRUISES_BEFORE_DATE);
            preparedStatement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                cruiseList = cruiseShaper.shapeDataToList(resultSet);
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
        return cruiseList;
    }

    @Override
    public List<Cruise> getAllCruisesBetweenTwoDates(LocalDate start, LocalDate end) {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_ALL_CRUISES_BETWEEN_DATES_QUERY);
            preparedStatement.setDate(1, Date.valueOf(start));
            preparedStatement.setDate(2, Date.valueOf(end));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                cruiseList = cruiseShaper.shapeDataToList(resultSet);
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
        return cruiseList;
    }

    @Override
    public List<Cruise> getAllCruisesByStartAndDuration(LocalDate start, int duration) {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_CRUISES_BY_START_AND_DURATION_QUERY);
            preparedStatement.setDate(1, Date.valueOf(start));
            preparedStatement.setInt(2, duration);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                cruiseList = cruiseShaper.shapeDataToList(resultSet);
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
        return cruiseList;
    }

    @Override
    public List<Cruise> getAllCruisesByEndAndDuration(LocalDate end, int duration) {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_CRUISES_BY_END_AND_DURATION_QUERY);
            preparedStatement.setDate(1, Date.valueOf(end));
            preparedStatement.setInt(2, duration);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                cruiseList = cruiseShaper.shapeDataToList(resultSet);
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
        return cruiseList;
    }

    @Override
    public List<Cruise> getAllCruisesByDatesAndDuration(LocalDate start, LocalDate end, int duration) {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(CruiseQueries.GET_CRUISES_BY_DATES_AND_DURATION_QUERY);
            preparedStatement.setDate(1, Date.valueOf(start));
            preparedStatement.setDate(2, Date.valueOf(end));
            preparedStatement.setInt(3, duration);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                cruiseList = cruiseShaper.shapeDataToList(resultSet);
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
        return cruiseList;
    }

}
