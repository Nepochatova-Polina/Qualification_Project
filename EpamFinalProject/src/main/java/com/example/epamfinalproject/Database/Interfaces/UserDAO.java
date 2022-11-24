package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.User;

import java.util.List;

public interface UserDAO {
    void registerUser(User user);

    User getUser(String name, String password);

    User getUser(int id);

    List<User> getPassengerUsers();

    List<User> getStaffUsers();

    List<User> getAllUsers();

    void deleteUserByID(User user);

    void updateUserByID(User user, long id);
}
