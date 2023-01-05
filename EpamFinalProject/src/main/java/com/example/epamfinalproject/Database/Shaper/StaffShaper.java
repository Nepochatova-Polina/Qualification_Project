package com.example.epamfinalproject.Database.Shaper;

import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Entities.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffShaper implements DataShaper<Staff> {
    @Override
    public Staff shapeData(ResultSet resultSet) throws SQLException {
        Staff staff = new Staff();
        staff.setId(resultSet.getLong(FieldKey.ENTITY_ID));
        staff.setFirstName(resultSet.getString(FieldKey.FIRST_NAME));
        staff.setLastName(resultSet.getString(FieldKey.LAST_NAME));
        staff.setShip_id(resultSet.getLong(FieldKey.CRUISE_SHIP_ID));
        return staff;
    }

    @Override
    public List<Staff> shapeDataToList(ResultSet resultSet) throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        Staff staff = new Staff();
        if(resultSet.next()){
            staff.setId(resultSet.getLong(FieldKey.ENTITY_ID));
            staff.setFirstName(resultSet.getString(FieldKey.FIRST_NAME));
            staff.setLastName(resultSet.getString(FieldKey.LAST_NAME));
            staff.setShip_id(resultSet.getLong(FieldKey.CRUISE_SHIP_ID));
            staffList.add(staff);
        }
        return staffList;
    }
}
