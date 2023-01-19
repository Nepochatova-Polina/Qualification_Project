package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Interfaces.StaffDAO;
import com.example.epamfinalproject.Entities.Staff;
import java.util.List;

public class StaffService {

  private final StaffDAO staffDAO;

  public StaffService(StaffDAO staffDAO) {
    this.staffDAO = staffDAO;
  }

  public void registerStaff(Staff staff) {
    staffDAO.registerStaff(staff);
  }

  public void updateStaffByID(Staff staff, long id) {
    staffDAO.updateStaffByID(staff, id);
  }

  public Staff getStaffByID(long id) {
    return staffDAO.getStaffByID(id);
  }

  public List<Staff> getAllStaff() {
    return staffDAO.getAllStaff();
  }

  public List<Staff> getStaffByShipID(long id) {
    return staffDAO.getAllStaffByShipID(id);
  }
}
