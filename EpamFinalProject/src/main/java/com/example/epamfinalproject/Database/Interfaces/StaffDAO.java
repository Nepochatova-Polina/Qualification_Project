package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.Staff;
import java.util.List;

public interface StaffDAO {
  void registerStaff(Staff staff);

  List<Staff> getAllStaff();

  List<Staff> getAllStaffByShipID(long id);
}
