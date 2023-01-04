package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Implementations.Staff_Implementation;
import com.example.epamfinalproject.Database.Interfaces.StaffDAO;
import com.example.epamfinalproject.Entities.Staff;

import java.util.List;

public class StaffService {
    public  void registerStaff(Staff staff) {
        StaffDAO staffDAO = new Staff_Implementation();
        staffDAO.registerStaff(staff);
    }

    public  void updateStaffByID(Staff staff, long id) {
        StaffDAO staffDAO = new Staff_Implementation();
        staffDAO.updateStaffByID(staff, id);
    }

    public  void deleteStaffByID(long id) {
        StaffDAO staffDAO = new Staff_Implementation();
        staffDAO.deleteStaffByID(id);
    }

    public  void deleteStaffByShipID(long id) {
        StaffDAO staffDAO = new Staff_Implementation();
        staffDAO.deleteStaffByShipID(id);
    }

    public  Staff getStaffByID(long id) {
        StaffDAO staffDAO = new Staff_Implementation();
        return staffDAO.getStaffByID(id);
    }

    public  List<Staff> getAllStaff() {
        StaffDAO staffDAO = new Staff_Implementation();
        return staffDAO.getAllStaff();
    }

    public  List<Staff> getStaffByShipID(long id) {
        StaffDAO staffDAO = new Staff_Implementation();
        return staffDAO.getAllStaffByShipID(id);
    }

}
