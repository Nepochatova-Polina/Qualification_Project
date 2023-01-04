package com.example.epamfinalproject.Database.Shaper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Current Interface helps to collect data from ResultSet and fit it into Class Object.
 * shapeData function implementations are not supposed to move cursor if the resultSet
 * using resultSet.next() method? but only extract data from current row
 * shapeDataToList implementations return list filled with resultSet data
 */

public interface DataShaper<T> {

    T shapeData(ResultSet resultSet) throws SQLException;

    List<T> shapeDataToList(ResultSet resultSet) throws SQLException;

}
