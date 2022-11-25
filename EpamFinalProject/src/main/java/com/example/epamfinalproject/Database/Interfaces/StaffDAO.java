package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.Staff;

import java.util.List;

public interface StaffDAO {
    void registerStaff(Staff staff);

    void deleteStaffByID(long id);

    void deleteStaffByShipID(long id);

    void updateStaffByID(Staff staff, long id);

    Staff getStaffByID(long id);

    List<Staff> getAllStaff();

    List<Staff> getAllStaffByShipID();
}
