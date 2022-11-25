package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Implementations.Staff_Implementation;
import com.example.epamfinalproject.Database.Interfaces.StaffDAO;
import com.example.epamfinalproject.Entities.Staff;

import java.util.List;

public class StaffService {
    public static void registerStaff(Staff staff) {
        StaffDAO staffDAO = new Staff_Implementation();
        staffDAO.registerStaff(staff);
    }

    public static void updateStaffByID(Staff staff, long id) {
        StaffDAO staffDAO = new Staff_Implementation();
        staffDAO.updateStaffByID(staff, id);
    }

    public static void deleteStaffByID(long id) {
        StaffDAO staffDAO = new Staff_Implementation();
        staffDAO.deleteStaffByID(id);
    }

    public static void deleteStaffByShipID(long id) {
        StaffDAO staffDAO = new Staff_Implementation();
        staffDAO.deleteStaffByShipID(id);
    }

    public static Staff getStaffByID(long id) {
        StaffDAO staffDAO = new Staff_Implementation();
        return staffDAO.getStaffByID(id);
    }

    public static List<Staff> getAllStaff() {
        StaffDAO staffDAO = new Staff_Implementation();
        return staffDAO.getAllStaff();
    }

    public static List<Staff> getStaffByShipID(long id) {
        StaffDAO staffDAO = new Staff_Implementation();
        return staffDAO.getAllStaffByShipID(id);
    }

}
